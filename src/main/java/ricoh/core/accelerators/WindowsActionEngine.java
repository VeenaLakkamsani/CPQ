package ricoh.core.accelerators;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;

public class WindowsActionEngine {

	public WiniumDriver driver = null;
	private int portNo = -1;
	private DesktopOptions appOptions = null;
	
	// Declare Loggers
	private final String logClassName = WindowsActionEngine.class.getName();

	// Declare log msg strings
	private final String msgClickSuccess = "Successfully Clicked On ";
	private final String msgClickFailure = "Unable To Click On ";
	private final String msgTypeSuccess = "Successfully Typed On ";
	private final String msgTypeFailure = "Unable To Type On ";
	private final String msgIsElementFoundSuccess = "Successfully Found Element ";
	private final String msgIsElementFoundFailure = "Unable To Found Element ";
	
	public WiniumDriver initializeWiniumDriver( DesktopOptions option){
		try {
            ServerSocket serverSocket=new ServerSocket(0);
            portNo=serverSocket.getLocalPort();
            serverSocket.close();
            
            String command=gblConstants.winiumDriverPath+" --port "+portNo;
            Runtime.getRuntime().exec(command);
            sleep(gblConstants.defSleepTime*10);
            
            System.setProperty("webdriver.winium.driver.desktop",gblConstants.winiumDriverPath);
            driver = new WiniumDriver(new URL("http://localhost:"+portNo), option);
            LogManager.logInfo(logClassName,  "Winium Server started on Port - "+portNo);
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.logException(e, logClassName, "Exception to initialize winium driver....");
        }
        return driver;
	}
	public void sleep(int time) {
		try{
			Thread.sleep(time);        	
		}catch(InterruptedException ie){
			System.out.println("Exception to sleep for " + time + " msec");
		}
	}
	public void closeDriver(){
		try{
			this.driver.quit();    		
			Thread.sleep(gblConstants.defSleepTime);
        	LogManager.logInfo(WebActionEngine.class.getName(), "Closed winium driver");
		}catch(Exception e){
    		LogManager.logException(e, WebActionEngine.class.getName(), "Exception to close winium driver");
		}
	}
	
	public boolean click(By locator, String locatorName) throws Exception {
		boolean status = false;
		try {            
			WebElement ele = this.driver.findElement(locator);
			if (ele.isDisplayed() && ele.isEnabled()){
				ele.click();
				status = true;
                LogManager.logInfo(logClassName, this.msgClickSuccess + locatorName);            
			}
		} catch (Exception e) {
			status = false;
            LogManager.logException(e, logClassName, this.msgClickFailure + locatorName);
			throw e;
		}
		return status;
	}

	public boolean click(String locator, String elementName) throws Exception {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			if (ele.isDisplayed() && ele.isEnabled()){
				ele.click();
				status = true;
                LogManager.logInfo(logClassName, this.msgClickSuccess + elementName);
			}
		} catch (Exception e) {
			status = false;
            LogManager.logException(e, logClassName, this.msgClickFailure + elementName);
			throw e;
		}
		return status;
	}    

	public boolean waitAndClick(String locator, String locatorName) throws Exception {
		boolean status = false;
		try {
			
			WebDriverWait wait = new WebDriverWait(driver,  gblConstants.wdWaitTimeout);
			By eleLocator = byLocator(locator);
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(eleLocator));

			if (ele.isDisplayed() && ele.isEnabled()){
				ele.click();
				status = true;
            	LogManager.logInfo(logClassName, this.msgClickSuccess + locatorName);
			}
		} catch (Exception e) {
			status = false;            
            LogManager.logException(e, logClassName, this.msgClickFailure + locatorName);
			throw e;
		}
		return status;
	}
	
	/**
	 * Get "By" object to locate element
	 *
	 * @param locator locator of element in xpath=locator; css=locator etc
	 * @return by object
	 */
	public By byLocator(final String locator) {
		String prefix = locator.substring(0, locator.indexOf('='));
		String suffix = locator.substring(locator.indexOf('=') + 1);

		switch (prefix) {
		case "xpath":
			return By.xpath(suffix);
		case "id":
			return By.id(suffix);
		case "name":
			return By.name(suffix);
		case "className":
			return By.className(suffix);
		default:
			return null;
		}
	}
	/**
	 * Binding to get Xpath, CSS, Link, Partial link element
	 *
	 * @param locator locator of element in xpath=locator; css=locator etc
	 * @return found WebElement
	 */
	public WebElement getElement(final String locator) {
		return getElement(locator, true);
	}
	/**
	 * @param locator          locator of element in xpath=locator; css=locator etc
	 * @param screenShotOnFail make screenshot on failed attempt
	 * @return found WebElement
	 */
	protected WebElement getElement(final String locator, boolean screenShotOnFail) {
		try {
			return driver.findElement(byLocator(locator));
		} catch (Exception e) {
			if (screenShotOnFail) ;
			throw e;
		}
	}
	
	public String getAttributeValue(final String locator, final String attribute){
		WebElement we = getElement(locator);
		return we.getAttribute(attribute);
	}
	
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
	            LogManager.logInfo(logClassName, "ElementFoundSuccess" + locatorName + " diplayed as " + expected);                
	        } else {
	            status = false;
	            LogManager.logError(logClassName, "ElementFoundFailure" + locatorName + "Not Displayed " + expected);
	        }        
	        return status;
	    }    
	 public boolean type(String locatorName, String testdata)  {
			boolean blnRes = false;
			WebElement ele = getElement(locatorName);
			type(ele, testdata);
	        LogManager.logInfo(WebActionEngine.class.getName(), "Successfully entered  " + testdata + " in element " + locatorName);

			if(ele.getAttribute("Name").trim().equals(testdata.trim())){
				blnRes = true;
			}else{
				blnRes = false;
			}
			return blnRes;
		}
	 
	 public void type(final WebElement we, String testdata)  {

			try {
				we.clear();
				we.sendKeys(testdata);
			} catch (Exception e) {
				throw e;
			}
		}
	 public List<WebElement> getAllElements(String locatorName)  {
			List<WebElement> elements = new ArrayList<WebElement>();
			try {
				elements = driver.findElements(byLocator(locatorName));
				return elements;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return elements;
		}
	 
	 /**
		 * @Purpose To Kill Process
		 * @Input Process to stop
		 * @Output boolean : Returns True if success
		 */
	 public boolean killProcess(String processName) {
	        boolean flag=false;
	        try {
	            Process pro = Runtime.getRuntime().exec("tasklist");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (line.startsWith(processName)) {
	                    Runtime.getRuntime().exec("Taskkill /IM "+processName+".exe /F");
	                    sleep(gblConstants.defSleepTime);
	                    flag=true;
	                }
	            }
	            if(flag) {
	                LogManager.logInfo(logClassName, processName+ " process is killed");
	            }else {
	                LogManager.logInfo(logClassName,  processName+ " process is NOT running");
	            }
	    
	        } catch (Exception e) {
	            flag=false;
	            e.printStackTrace();
	            LogManager.logException(e, logClassName, "Exception to kill process....");
	        }
	        
	        return flag;
	    }
	    
	    /**
		 * @Purpose To Capture screenshot
		 * @Input strReportsPath, fileName
		 * @Output boolean : Returns True if success
		 */
		public String captureScreenShot(String location, String fileName) {
			try {
				TakesScreenshot ts = (TakesScreenshot) this.driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				String epoch2 = String.valueOf(System.currentTimeMillis() / 1000);
				fileName = fileName + "_" + epoch2 + ".png";
				String dest = location + File.separator + gblConstants.screenshotDirName + File.separator + fileName;
				File destination = new File(dest);
				FileUtils.copyFile(source, destination);

				return fileName;
			} catch (IOException io) {
				LogManager.logException(io, logClassName, "Exception to capture screenshot");
				return "";
			} catch (Exception e) {
				LogManager.logException(e, logClassName, "Exception to capture screenshot");
				return "";
			}
		}	    
}
