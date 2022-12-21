package ricoh.core.accelerators;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import ricoh.config.gblConstants;
import ricoh.config.gblConstants.ComparisionType;
import ricoh.core.logs.LogManager;
import ricoh.core.reports.ExtentManager;
import ricoh.web.pages.HomePage;
import ricohcore.testmanagement.TestRailsAPIAccess;

public abstract class AbstractTestEngine {

	//To store suite execution start time stamp
	public static String suiteStartTime = "";

	//Reporting references
	public static ExtentManager extentMngr=null;
	public ExtentTest tcReport = null;	

	//Test Rail references
	public static boolean blnLog2TestRail=false;
	public static TestRailsAPIAccess trAccess = null;	
	public String trTestPlanId = null;

	//System parameters
	public String hostName="";
	public String hostIP = "";
	public String osName = System.getProperty("os.name");

	//Web App execution params
	public String browserUnderTest = "";
	public String execEnv = null; 
	public static String osUnderTest = null;

	//Web API execution params
	public String apiUT="";
	public String apiServerUrl = "";	
	
	//Mobile execution params
	public String appiumVerNo = "";
	public String appiumServerUrl = "";
	public DesiredCapabilities appiumDesiredCaps  = new DesiredCapabilities();
	//Mobile platform execution params	
	public String deviceUnderTest = null;

	//App parameters
	public String appUnderTest = null;
	public String testEnvName = null;
	
	protected SoftAssert testngAssertion = new SoftAssert();
	
	/*
	 *purpose: To Initialize extent reports and update system info
	 *author-date : Naresh, Gorla / 03/19/2019
	 *reviewer-date:
	 */
	public void initializeReport() {

		try {
			suiteStartTime = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss_SSS").format(new Date());
	        extentMngr = new ExtentManager(gblConstants.currSuiteName + "_" + suiteStartTime, gblConstants.reportsPath);
	        extentMngr.setAnalysisStrategy(AnalysisStrategy.TEST);
	        extentMngr.configReportTitle(this.appUnderTest + "-Test Results");
	        extentMngr.configReportName(this.appUnderTest);
	        extentMngr.configTheme(Theme.STANDARD);

	    	//Capture testing host machine details
	        InetAddress myHost;
			myHost = InetAddress.getLocalHost(); 
	        hostName = myHost.getHostName();
	        hostIP = InetAddress.getLocalHost().getHostAddress();

	        //Update system information in extent reports        
	        extentMngr.setSystemInfo("Host O.S", this.osName);
	        extentMngr.setSystemInfo("Host Name - ", this.hostName);
	        extentMngr.setSystemInfo("Host I.P- ", this.hostIP);		

	        extentMngr.flushReport();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}

	
	/*
	 * Purpose: To update web application under test name & its environment details
	 *author-date : Naresh, Gorla / 03/19/2019
	 *reviewer-date: 
	 */
	public abstract void reportTestAppEnvironmentSummary();

	/*
	 * Purpose: To update web application under test name & its environment details
	 *author-date : Naresh, Gorla / 03/19/2019
	 *reviewer-date: 
	 */
	public void reportWebEnvironmentSummary() {
		extentMngr.setSystemInfo("Browser Under Test", this.browserUnderTest);
		extentMngr.setSystemInfo("App Under Test", this.appUnderTest);
        extentMngr.setSystemInfo("App Test Environment", testEnvName);
        extentMngr.setSystemInfo("App Url", gblConstants.suiteConfig.getTestAppUrl(this.appUnderTest, this.testEnvName));
        //extentMngr.setSystemInfo("App Version", gblConstants.suiteConfig.getTestAppVersion(this.appUnderTest, this.testEnvName));		
	}

	/*
	 * Purpose: To update web api environment under test
	 *author-date : Naresh, Gorla / 03/19/2019
	 *reviewer-date: 
	 */
	public void reportWebApiEnvironmentSummary() {
        extentMngr.setSystemInfo("App Under Test", this.appUnderTest);
        extentMngr.setSystemInfo("API Test Environment", this.testEnvName);
        extentMngr.setSystemInfo("API Server Url", gblConstants.suiteConfig.getTestAPIServerEndpoint(appUnderTest, testEnvName));		
	}

	
	/*
	 * Purpose: To launch browser under test for target execution environment
	 * author-date : Naresh, Gorla / 03/19/2019
	 * reviewer-date:
	 */
	public boolean launchBrowser(WebActionEngine webEngine) {

		try{
            System.out.println(this.execEnv);        
            //Start webEngine for local driver
            if (this.execEnv.trim().equalsIgnoreCase("local")) {
            	webEngine.setWebDriverForLocal(this.browserUnderTest);
            //Start webEngine for cloud driver
            }else if (this.execEnv.trim().contains("saucelabs")) {
            	webEngine.setRemoteWebDriverForCloudSauceLabs();
            //Start webEngine for Selenium grid
            }else if (this.execEnv.trim().contains("hub")) {
            	//webEngine.setWebdriverForGrid(this.execEnv.trim(), this.browserUnderTest);
            } else if (this.execEnv.trim().contains("snow")) {
            //	webEngine.setNormalWebDriverForLocal(this.browserUnderTest);
            } 		
    	}catch(Exception e){
    		//Log Exception to start a driver instance
    		reportStatus(Status.FAIL, "Exception to launch browser " + this.browserUnderTest);
    		this.tcReport.log(Status.FATAL, "Unabel to launch browser under test " + this.browserUnderTest);
    		e.printStackTrace();
    		throw new SkipException("Unable to start browser " + this.browserUnderTest);
    	}		
		return false;
	}

	

	/*
	 * Purpose: To launch browser under test for target execution environment
	 * author-date : Naresh, Gorla / 03/19/2019
	 * reviewer-date:
	 */	
	public boolean loadApplication(WebActionEngine webEngine) {

    	String appUrl = gblConstants.suiteConfig.getTestAppUrl(this.appUnderTest, this.testEnvName);
    	String appTitle = gblConstants.suiteConfig.getTestAppPageTitle(this.appUnderTest);
		boolean blnLoadPage = webEngine.loadPage(appUrl, appTitle);

		if(!blnLoadPage){
			reportStatus(Status.FAIL, "Unable to load Login Page, Skipping test... ");
			throw new SkipException("Unable to load " + appUrl);
		}
		return blnLoadPage;
	}
  
	/*
	 * public boolean loadSNOWApplication(WebActionEngine webEngine) {
	 * 
	 * String appUrl = gblConstants.suiteConfig.getTestAppUrl(this.appUnderTest,
	 * this.testEnvName); String appTitle =
	 * gblConstants.suiteConfig.getTestAppPageTitle(this.appUnderTest); boolean
	 * blnLoadPage = webEngine.loadSNOWPage(appUrl, appTitle);
	 * 
	 * if(!blnLoadPage){ reportStatus(Status.FAIL,
	 * "Unable to load SNOW Login Page, Skipping test... "); throw new
	 * SkipException("Unable to load " + appUrl); } return blnLoadPage; }
	 */


	/*
	 *Purpose: To get input execution parameters from command line or testng xml
	 *Author-date: Naresh, Gorla / 03/19/2019
	 *reviewer-date:  
	 */	
	protected String getExecutionParameter(ITestContext testContext, String paramName) {		
		String paramValue = null;
		try {
			//Check if execution parameter is passed through command line
			if (System.getProperty(paramName) !=null && System.getProperty(paramName).trim().length() > 0 ) {				
				paramValue = System.getProperty(paramName).trim();
			//Read execution parameter value from testng xml
			}else if (testContext.getCurrentXmlTest().getParameter(paramName) != null) {
				paramValue = testContext.getCurrentXmlTest().getParameter(paramName).trim();
			}							
		}catch(Exception e) {
			LogManager.logException(e, AbstractTestEngine.class.getName(), "Exception to read value of xml parameter " + paramName);
			e.printStackTrace();
		}
		return paramValue;
	}



	/*
	 * Purpose: To report status to current tcReport and debugLog
	 */
    public void reportStatus(Status result, String strLogMsg){

		//Update HTML Report
		this.tcReport.log(result, strLogMsg);

		//Update Logger
		switch (result) {
			case WARNING:
			case PASS:		
				testngAssertion.assertTrue(true, strLogMsg);
				LogManager.logInfo(result.name(), strLogMsg);
				break;
			case FATAL:				
			case FAIL:
				testngAssertion.assertTrue(false,strLogMsg);
				LogManager.logError(result.name(), strLogMsg);
				break;
			default:
		}
    } 

    

	/*
	 * Purpose: To report status to current tcReport and debugLog
	 */
   /* public void reportStatus(boolean blnRes, String successMsg, String failureMsg){
    	System.out.println("test");
		if (blnRes) {
			reportStatus(Status.PASS, successMsg);
			attachScreenshot(Status.PASS, successMsg); // Added to attach screen shot on pass
		}else {
			reportStatus(Status.FAIL, failureMsg);
			attachScreenshot(Status.FAIL, failureMsg); // Added to attach screen shot on failure
		}
    }        */ 
    

	/*
	 * Purpose: To report status status with a screenshot
	 * author-date : Naresh, Gorla / 03/19/2019
	 */   
    public void reportStatus(Status result, String strLogMsg, String strScreenShotName ){    		

		//Update HTML Report
    	reportStatus(result, strLogMsg);
    	attachScreenshot(result, strScreenShotName);
    }    

	/*
	 * Purpose: To report status status based on comparison type
	 * author-date : Naresh, Gorla / 03/19/2019
	 */       
    public Boolean reportStatus(String objName, String actual, ComparisionType Type, String expected, String strScreenShotName)  {
    	Boolean status = false;
    	switch (Type) {
    		case EQUAL :
    			if (actual.equals(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] equal to Expected ->[" + expected + "]", strScreenShotName);
    				status = true;
    			} else {
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not equal to Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case EQUAL_IGNORECASE:	
    			if (actual.equalsIgnoreCase(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] equal to ignorecase to Expected ->[" + expected + "]", strScreenShotName);
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not equal to ignorecase to Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case CONTAINS:	
    			if (actual.contains(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] contains Expected ->[" + expected + "]", strScreenShotName);
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not contains Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case SUBSTRING_OF:	
    			if (expected.contains(actual)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] is substring of Expected ->[" + expected + "]", strScreenShotName);
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] is not substring of Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case NOT_EQUAL:	
    			if (!actual.equals(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not equal to Expected ->[" + expected + "]", strScreenShotName);
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] equal to Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    	}
    	return status;
    }
    
	/*
	 * Purpose: To report status status based on comparison type
	 * author-date : Naresh, Gorla / 03/19/2019
	 */           
    public Boolean reportStatus(String objName, String actual, ComparisionType Type, String expected)  {
    	Boolean status = false;
    	String strScreenShotName;
    	if (objName == null || objName.isEmpty() )  {
    		objName = "";
    		Date date= new Date();
    		strScreenShotName = Long.toString(date.getTime());
    	} else {
    		strScreenShotName = objName;
    	}
    	switch (Type) {
    		case EQUAL :
    			if (actual.equals(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] equal to Expected ->[" + expected + "]",strScreenShotName);
    				status = true;
    			} else {
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not equal to Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case EQUAL_IGNORECASE:	
    			if (actual.equalsIgnoreCase(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] equal to ignorecase to Expected ->[" + expected + "]");
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not equal to ignorecase to Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case CONTAINS:	
    			if (actual.contains(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] contains Expected ->[" + expected + "]");
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not contains Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case SUBSTRING_OF:	
    			if (expected.contains(actual)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] is substring of Expected ->[" + expected + "]");
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] is not substring of Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    			
    		case NOT_EQUAL:	
    			if (!actual.equals(expected)) {
    				reportStatus(Status.PASS, "Verification of value [" + objName + "] : Actual ->[" + actual + "] not equal to Expected ->[" + expected + "]");
    				status = true;
    			} else{
    				reportStatus(Status.FAIL, "Verification of value [" + objName + "] : Actual ->[" + actual + "] equal to Expected ->[" + expected + "]", strScreenShotName);	
    				status = false;
    			} 
    			break;
    	}
    	return status;
    }    
    
	/*
	 * Purpose: To check for instances of given process and kill them
	 * author-date : Naresh, Gorla / 03/19/2019
	 */  
    public boolean killProcess(String processName) {
		boolean flag=false;
		
		try {
			if(osName.toUpperCase().contains("WINDOWS")) {
				//Get running proceses list
				Process pro = Runtime.getRuntime().exec("tasklist");
				BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
				String line;
				
				//Iterate through list and kill given process
				while ((line = reader.readLine()) != null) {
					if (line.startsWith(processName)) {
						Runtime.getRuntime().exec("Taskkill /IM "+processName+".exe /F");
						Thread.sleep(gblConstants.defSleepTime);
						flag=true;
					}
				}
				
				//Verify and report process
				if(flag) {
					LogManager.logInfo(AbstractTestEngine.class.getName(), processName+ " process is killed.");
				}else {
					LogManager.logInfo(AbstractTestEngine.class.getName(),  processName+ " process is NOT running");
				}				
			}
	
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
			LogManager.logException(e, AbstractTestEngine.class.getName(), "Exception to kill process....");
		}
		
		return flag;
	} 
    
	/*
	 * Purpose: To login application under test using default credentials from SuiteConfig.json
	 * author-date : Naresh, Gorla / 03/19/2019
	 */   
    public abstract boolean attachScreenshot(Status result, String screenshotName);

	/*
	 * Purpose: To initialize app execution env, browser, app under test, app test environment, device under, test rails plan
	 * author-date : Naresh, Gorla / 03/19/2019
	 */   
    public abstract void beforeSuite(String testBrowser, String execEnvName, String appTestEnv, String testRunName);

	/*
	 * Purpose: To report status to current tcReport and debugLog
	 * author-date : Satya, Gajula - 3/8/18
	 */   
    public abstract void afterSuite();


	/*
	 * Purpose: To initialize extent reports test case, corresponding to its test case id 
	 * author-date : Naresh, Gorla / 03/19/2019
	 */   
    public abstract void beforeTest(ITestContext testContext);
    
    //public abstract void beforeTest(ITestContext testContext, String testRailsTCId, String execEnvName, String appTestEnv, String trPlanId);

	/*
	 * Purpose: To report test case results back to the report 
	 * author-date : Naresh, Gorla / 03/19/2019
	 */   
   
	/*
	 * Purpose: To login application under test using default credentials from SuiteConfig.json
	 * author-date : Naresh, Gorla / 03/19/2019
	 */   
    public abstract boolean loginUsingDefaultUser();


	public void afterTest() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void reportStatus(boolean blnRes, String successMsg, String failureMsg){
    	try {
    		if (blnRes) {
    			reportStatus(Status.PASS, successMsg);
    			attachScreenshot(Status.PASS, successMsg); // Added to attach screen shot on pass
    		}else {
    			reportStatus(Status.FAIL, failureMsg);
    			String screenshotName = takeScreenshotforAlert(failureMsg);
    			//attachScreenshot(Status.FAIL, failureMsg); // Added to attach screen shot on failure
    			attachScreenshotonFailure(Status.FAIL, failureMsg+".png");
    		}
			
		} catch (Exception e) {
			reportStatus(Status.FAIL, failureMsg);
			String screenshotName = takeScreenshotforAlert(failureMsg);
			attachScreenshotonFailure(Status.FAIL, failureMsg);
		}
		
    }
    

	public boolean attachScreenshotonFailure(Status result, String screenshotName) {
		boolean blnRes = false;
		if (screenshotName.length() > 0) {
			
				blnRes = this.extentMngr.attachScreenshot(this.tcReport, result,
						screenshotName);
			
		}
		return blnRes;
	}
	public String takeScreenshotforAlert(String ssName){
		  try{
		  	
		  	String path = null;
		  	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  	  Rectangle screenRectangle = new Rectangle(screenSize);
		  	  Robot robot = new Robot();
		  	  BufferedImage image = robot.createScreenCapture(screenRectangle);
		  	  String dest = gblConstants.reportsPath + File.separator + gblConstants.screenshotDirName + File.separator + ssName+".png";
		  	  ImageIO.write(image, "png",new File(dest));
		  	  
		  	 path = dest;
		  		
		  		
		  	
		              return path;
		              
		  		}catch(Exception e)
		  		{
		  			System.out.println("Some exception occured while taking screenshot for driver." + e.getMessage());
		  			return "";
		  		}

		      }
}