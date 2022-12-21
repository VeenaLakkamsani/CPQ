package ricoh.web.engine;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.jacob.com.LibraryLoader;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;

public class OFSCWebActionEngine extends WebActionEngine{

	/**
     * @Purpose To wait while spinner loads
     * @Constraints 
     * @Output boolean : Returns True if element not present
     */
    public boolean waitWhileLoading(){
        boolean blnRes = false;
        try{
            final String imgSpinner_css = "css=img#spinner";
            blnRes = waitForElementNotPresent(imgSpinner_css,  gblConstants.wdWaitTimeout/4);
        }catch(StaleElementReferenceException se){
            blnRes = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return blnRes;
        
    }
    
    /**
     * @Purpose To get  required date  of current month 
     * @Constraints 
     * @Input integer  : required day ex: 1 gives start date of month
     * @Output boolean : Returns date in user required format 
     */
    public String getDayofCurrentMonth(int num,String dateFormate) throws Exception {
        Calendar c = Calendar.getInstance();   
        c.set(Calendar.DAY_OF_MONTH, num);
        DateFormat dateFormat = new SimpleDateFormat(dateFormate);
        String reqdate = dateFormat.format(c.getTime());
        return reqdate;
    }
    
    /**
     * @Purpose To get current date  in required format and time zone
     * @Constraints 
     * @Input String  : required format
     * @Output boolean : Returns date in user required format 
     *//*
    public String getCurrentDate(String dateFormate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat(dateFormate);
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        return currentDate;
    }*/
    
    /**
     * @Purpose To get current date  in required format and time zone
     * @Constraints 
     * @Input String  : required format
     * @Output boolean : Returns date in user required format 
     */
    public String getCurrentDate(String dateFormate,String timezone) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat(dateFormate);
		if (timezone != null) {
			dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		}
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        return currentDate;
    }
    
    /**
     * @Purpose To get checkbox status
     * @Constraints 
     * @Input String  : Locator and Locator name
     * @Output boolean : Returns true if selected or else false
     */
    
    public boolean getCheckboxStatus(String locator, String locatorName){
    	boolean blnRes = false;
    	WebElement ele = getElement(locator);    	
    	if (ele.isSelected()) {
    		blnRes = true;
    		LogManager.logInfo(OFSCWebActionEngine.class.getName(), "Successfully captured checkbox status of " + locatorName);
    	}
    	return blnRes;
    }
    
    /**
     * @Purpose To get Element presence 
     * @Constraints 
     * @Input String  : LocatorName and boolean presence
     * @Output boolean : Returns true | false
     */
    public boolean isElementPresent(String locatorName, boolean expected)  {
        boolean status = false;
        try {
            WebElement ele = getElement(locatorName);
            status = (ele.isDisplayed())? true : false;
        } catch (Exception e) {
            status = false;
        }
        if (!(expected ^ status)) {
            status = true;
            LogManager.logInfo(OFSCWebActionEngine.class.getName(), "ElementFoundSuccess" + locatorName + " diplayed as " + expected);                
        } else {
            status = false;
            LogManager.logError(OFSCWebActionEngine.class.getName(), "ElementFoundFailure" + locatorName + "Not Displayed " + expected);
        }        
        return status;
    }    
    
    /**
     * @Purpose To do mouse hover in sub menu 
     * @Constraints 
     * @Input String  : Parent Menu locator , Sub Menu locator and Locator Name
     * @Output boolean : Returns true | false
     */
   synchronized public boolean mouseHoverSubMenu(String parlocator, String sublocator,String locatorName) {
        boolean flag = false;
        try {
        	//Actions actions = new Actions(this.driver);
        	//this.driver.navigate().refresh();
        	WebElement mo = getElement(parlocator);
        	new Actions(this.driver).moveToElement(mo).perform();
        	sleep(gblConstants.defSleepTime*2);
           if(waitForElementPresent(sublocator)) {
            WebElement so = getElement(sublocator);
           new Actions(this.driver).moveToElement(so).perform();
           //.moveToElement(so).build().perform();
            LogManager.logInfo(OFSCWebActionEngine.class.getName(), "MouseOver action is performed on " + locatorName);
            flag = true;
            }
           else {
        	   LogManager.logError(OFSCWebActionEngine.class.getName(), "Unable to find     "+sublocator+" Sublocator");;
           }
        } catch (Exception e) {
        	flag = false;
        	e.printStackTrace();
        	LogManager.logException(e, OFSCWebActionEngine.class.getName(), "MouseOver action is not perform on " + locatorName);
        }
        return flag;
    }
   
  
	public boolean switchToLastFrame() {
		boolean flag=false;
		try {
			Thread.sleep(5000);	
			//this.webDriver.switchTo().defaultContent();
			
			int size=this.driver.findElements(By.tagName("iframe")).size();
			//if(size==size){
				//WebElement iframe =	this.webDriver.findElement(By.tagName("iframe"));
				this.driver.switchTo().frame(size-1);
				flag=true;
			//}
			
		}catch(Exception e) {
			flag=false;
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to switch to frame");
			e.printStackTrace();
		}
		return flag;
	}
	public boolean switchToIFrame() {
		boolean flag=false;
		try {
			Thread.sleep(5000);
			
			this.driver.switchTo().defaultContent();
			
			int size=this.driver.findElements(By.tagName("iframe")).size();
			if(size!=0){
				WebElement iframe =	this.driver.findElement(By.tagName("iframe"));
				this.driver.switchTo().frame(iframe);
				flag=true;
			}
			
		}catch(Exception e) {
			flag=false;
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to switch to frame");
			e.printStackTrace();
		}
		return flag;
	}
	public boolean switchToDefaultFrame(){
		boolean flag=false;
		try {
			Thread.sleep(5000);
			this.driver.switchTo().defaultContent();
		flag=true;
		}catch(Exception e) {
			flag=false;
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to switch to frame");
			e.printStackTrace();
		}
		return flag;
	}
	public boolean switchToNewWindow() {
		boolean flag=false;
		try {
			String oldTab =this.driver.getWindowHandle();
			ArrayList<String> tabs2 = new ArrayList<String> (this.driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
		    //driver.close();
		    //driver.switchTo().window(tabs2.get(0));
		}catch(Exception e) {
			flag=false;
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to switch to new window");
			e.printStackTrace();
		}
		return flag;
	}
	public String getwindowhandle() {
		String windowhandle=null;
		try {
			
			ArrayList<String> tabs2 = new ArrayList<String> (this.driver.getWindowHandles());
			windowhandle=tabs2.get(0);
		    //driver.close();
		    //driver.switchTo().window(tabs2.get(0));
		}catch(Exception e) {
			
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to switch to new window");
			e.printStackTrace();
		}
		return windowhandle;
	}
	public boolean closeAllOtherWindows(String openWindowHandle) {
		Set<String> allWindowHandles = this.driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				this.driver.switchTo().window(currentWindowHandle);
				this.driver.close();
			}
		}
		this.driver.switchTo().window(openWindowHandle);
		if (this.driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
    /**
     * @Purpose To Load JcobDll based on system bit size
     *  Note: This will be removed after update with winium
     */
    public void loadJcobDll() {
        String jacobDllVersionToUse;

         if (System.getProperty("sun.arch.data.model").contains("32"))
            jacobDllVersionToUse = "jacob-1.19-x86.dll";
         else 
            jacobDllVersionToUse = "jacob-1.19-x64.dll";

         File file = new File("library"+File.separator+"jcobdll", jacobDllVersionToUse);
         System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
    }
    

    
    /**
     * @Purpose To add Year to current date
     * @Constraints 
     * @Input String  : Parent Menu locator , Sub Menu locator and Locator Name
     * @Output boolean : Returns true | false
     */
    
    public  String addtoCurrentDate(int num, String adddateTo, String dateFormate, String timezone) throws Exception {
        Calendar c = Calendar.getInstance();
        switch(adddateTo){
		case "Year":
			c.add(Calendar.YEAR, num);
			break;
		case "Month":
			c.add(Calendar.MONTH, num);
			break;
		case "Day":
			c.add(Calendar.DAY_OF_MONTH, num);
			break;
	}
        DateFormat dateFormat = new SimpleDateFormat(dateFormate);
		if (timezone != null) {
			dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		}
        String StartDate = dateFormat.format(c.getTime());
        return StartDate;
    }

    
    /**
     * @Purpose To get Web Element By ID using Java Script
     * @Constraints 
     * @Input String  : ID value of Web Element
     * @Output WebElement : Returns respective WebElemnt 
     */
    
    public WebElement getWebElementusingJSbyID(String id) {
    	WebElement element = null;
    	
    	return (WebElement) ((JavascriptExecutor) driver).executeScript("return document.getElementById('"+id+"');",element);
    }

    
    /**
	* Purpose: To generate random string based on the given criteria 
	* Input: String Integer,AlphaNumeric
	* Output: String ; random string based on the input format
	* Author-Date: 14-Aug-2018
	* Reviewed-Date:
	*/
    public   String getRandom(String format,int number) {
        char[] charsString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] charsAlphaNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        char[] charsNum = "1234567890".toCharArray();
        char[] chars=null;
        if(format.equalsIgnoreCase("ALPHANUMERIC"))
        chars=charsAlphaNum;
        else if(format.equalsIgnoreCase("INT"))
               chars=charsNum;
        else
               chars=charsString;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
               char c = chars[random.nextInt(chars.length)];
               sb.append(c);
        }
        String output = sb.toString();
        //LogManager.logInfo(ICMTestEngine.class.getName(), "Random number is : " +output);
        return output;
  } 
    
    
    
    
/*    public boolean waitForElementPresent(String locator, int secs) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
            WebElement reqEle = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));            
            status = (reqEle != null) ? true : false;
			//this.reporter.SuccessReport("Checked element present", "Successfully checked element present " + locator, Driver);
			//this.extentMngr.logResult(extentTC, Status.PASS, "Successfully checked element present " + locator);
            LogManager.logInfo(ICMTestEngine.class.getName(), "Successfully checked element present " + locator);
		} catch (TimeoutException te) {
			LogManager.logInfo(ICMTestEngine.class.getName(), "Timeout to check element present " + locator);
			status = false;
		} */
/*		
		catch (StaleElementReferenceException se) {
		     System.out.println("Stale Element Reference Exception for Element" +locator);
			status = false;
		}
		
		catch (Exception e) {
        	e.printStackTrace();
			
		}
		return status;
	}*/
	
    /**
   	* Purpose: To wait for Page ready state
   	* Input: Driver instance
   	* Output: NA
   	*/
    public void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver,  gblConstants.implicitWaitTimeout);
        wait.until(pageLoadCondition);
    }
    synchronized public boolean mouseClick(String locator, String locatorName) {
		boolean flag = false;
		try {
			WebElement ele = getElement(locator);
			new Actions(this.driver).click(ele).build().perform();
			Thread.sleep(gblConstants.defSleepTime / 2);
			flag = true;
			LogManager.logInfo(WebActionEngine.class.getName(), "MouseOver action is performed on " + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, "OFSCWebAction engine", "MouseOver action is not perform on " + locatorName);
			return false;
		}
		return flag;
	}
    
    public String takeScreenshotforAlert(String ssName){
    try{
	
	String path = null;
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  Rectangle screenRectangle = new Rectangle(screenSize);
	  Robot robot = new Robot();
	  BufferedImage image = robot.createScreenCapture(screenRectangle);
	  String dest = gblConstants.reportsPath + File.separator + gblConstants.screenshotDirName + File.separator + ssName;
	  ImageIO.write(image, "png",new File(dest));
	  
	 path = dest+".png";
            return path;
            
		}catch(Exception e)
		{
			System.out.println("Some exception occured while taking screenshot for driver." + e.getMessage());
			return "";
		}

    }


    
}
