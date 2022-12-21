package ricoh.web.engine;


import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.AbstractTestEngine;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.pages.LoginPage;


public  class OFSCTestEngine extends AbstractTestEngine {

	public OFSCWebActionEngine ofscActionEngine = null;
	//public BusinessFunctions pgCommon ;
	//public WindowsActionEngine corningWinEngine = null;
	public boolean isWindows=false;
	public String UserName;
	public String Password;
	
	private final static Object loginsync= new Object(); 
	
	@BeforeSuite(alwaysRun=true)
	@Parameters({"Project","BrowserUT","ExecutionEnv", "AppTestEnv","testRunName"})
	public void beforeSuite(String project,String testBrowser, String execEnvName, String appTestEnv,String runName) {
    			
		this.execEnv = execEnvName;
		this.browserUnderTest = testBrowser;
		this.appUnderTest = project; 
		this.testEnvName = appTestEnv;
		//this.UserName = gblConstants.suiteConfig.getTestAppLoginUser(this.appUnderTest, this.testEnvName);
	//	this.Password = gblConstants.suiteConfig.getTestAppLoginPwd(this.appUnderTest, this.testEnvName);
		
		initializeReport();
//		reportTestAppEnvironmentSummary();
	}

	/*//@Override
	@AfterSuite(alwaysRun=true)
	public void afterSuite(WebActionEngine webEngine) {
		
		extentMngr.flushReport();
		//Clean up
		//killProcess("IEDriverServer.exe");
	}*/

	@BeforeTest
	@Parameters({"Project"})
	public void beforeTest(String project,ITestContext testContext) {
		
		this.execEnv = getExecutionParameter(testContext, "ExecutionEnv");
		this.browserUnderTest = getExecutionParameter(testContext, "BrowserUT");
		this.appUnderTest = project; 
		this.testEnvName = getExecutionParameter(testContext, "AppTestEnv");
		this.trTestPlanId = getExecutionParameter(testContext, "TestPlanId");
		
		this.tcReport = extentMngr.addTest(testContext.getCurrentXmlTest().getName(), " ");
		ofscActionEngine = new OFSCWebActionEngine();
		launchBrowser(ofscActionEngine);
		//if(project.equalsIgnoreCase("SNOW")) loadSNOWApplication(ofscActionEngine);
		//else 
		loadApplication(ofscActionEngine);
	}
	
	
	@Override
	 public boolean loginUsingDefaultUser() {
		boolean res = false ;	
		 synchronized(loginsync) { 
		String loginUser = gblConstants.suiteConfig.getTestAppLoginUser(this.appUnderTest, this.testEnvName);
		String loginPwd = gblConstants.suiteConfig.getTestAppLoginPwd(this.appUnderTest, this.testEnvName);
		LoginPage pgLogin = new LoginPage(this.ofscActionEngine);
		res= pgLogin.login(this.UserName , this.Password);
		 
		//return pgHome.waitForPage();
	       }
	return res ; 
	}
	
	@Override
	public void reportTestAppEnvironmentSummary() {
		reportWebEnvironmentSummary();
	}

	@Override
	public boolean attachScreenshot(Status result, String screenshotName) {
		boolean blnRes = false;
		if (screenshotName.length() > 0) {
			if (!isWindows) { // To take screenshot for both Web and Window
				blnRes = this.extentMngr.attachScreenshot(this.tcReport, result,
						this.ofscActionEngine.captureScreenShot(gblConstants.reportsPath, screenshotName));
			} else {
				//blnRes = this.extentMngr.attachScreenshot(this.tcReport, result,
						//this.corningWinEngine.captureScreenShot(gblConstants.reportsPath, screenshotName));
			}
		}
		return blnRes;
	}

	@Override
	@AfterSuite(alwaysRun=true)
	public void afterSuite() {

		extentMngr.flushReport();
		this.ofscActionEngine.closeDriver();
		
		//Clean up
		killProcess("Google Chrome");
		killProcess("Winium.Desktop.Driver");
		testngAssertion.assertAll();
	}	
	
/*	

	
/*	*//**
     * @purpose:  To ReportMultiple Test Case Reports
     * @ Input params tcreportsList: List of Extent Test Objects,blnresult ,Strings success and Failure messages
     * @
     *//*
*/	
	
	public void reportStatus(ExtentTest [] tcreportsList, boolean blnRes, String successMsg, String failureMsg) {

		Status currTCStatus = Status.INFO;
		String logMessage = "";
		if (blnRes) {
			currTCStatus = Status.PASS;
			logMessage = successMsg;
		} else {
			currTCStatus = Status.FAIL;
			logMessage = failureMsg;
		}

		for (ExtentTest currReport : tcreportsList) {
			if (currReport != null) {
				//reportStatus(currTCStatus, logMessage);
				currReport.log(currTCStatus, logMessage);
			}

			if (currTCStatus == Status.FAIL) {
				this.extentMngr.attachScreenshot(currReport, Status.FAIL,
						this.ofscActionEngine.captureScreenShot(gblConstants.reportsPath, failureMsg));
			}

		}
	}
		
		public void reportStatus(List<ExtentTest> tcreportsList, boolean blnRes, String successMsg, String failureMsg) {

			Status currTCStatus = Status.INFO;
			String logMessage = "";
			if (blnRes) {
				currTCStatus = Status.PASS;
				logMessage = successMsg;
			} else {
				currTCStatus = Status.FAIL;
				logMessage = failureMsg;
			}
 
			for (ExtentTest currReport : tcreportsList) {
				if (currReport != null) {
					//reportStatus(currTCStatus, logMessage);
					currReport.log(currTCStatus, logMessage);
				}

				if (currTCStatus == Status.FAIL) {
					this.extentMngr.attachScreenshot(currReport, Status.FAIL,
							this.ofscActionEngine.captureScreenShot(gblConstants.reportsPath, failureMsg));
				}

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

		@Override
		public void beforeSuite(String testBrowser, String execEnvName, String appTestEnv, String testRunName) {
			// to override abstract method
			
		}

		@Override
		public void beforeTest(ITestContext testContext) {
			// to override abstract method
			
		}

}
