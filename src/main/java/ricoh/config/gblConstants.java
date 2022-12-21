package ricoh.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class gblConstants {
	 
	public enum ComparisionType
	{
	    NOT_EQUAL, EQUAL, EQUAL_IGNORECASE, CONTAINS, SUBSTRING_OF;
	}
	
	public final static String logsDirName = "Logs";	
	public final static String apiLogsDirName = "APILogs";
	public final static String testDataDirName = "TestData";
	public final static String toolDirName = "tools";
	public final static String reportsDirName = "Reports";
	public final static String reportsArchiveDirName = "Reports_Archive";
	public final static String downloadsDirName = "Downloads";
	//public final static String extentReportsDirName = "ExtentReports";
	//public final static String cignitiReportsDirName = "CignitiReports";
	public final static String librayDirName = "commonLibs";
	public final static String screenshotDirName = "screenshots";
	public final static String configDirName = "configurations";
	public final static String logosDirName = "logos";
	public final static String cignitiLogoName = "Cigniti-logo.png";
	public final static String clientLogoName = "Client_logo.png";
	public final static String suiteConfigName = "SuiteConfig.json"; //"SuiteHarness.json";
	
	public final static String rootDir = getRootDir();
	public final static String projWorkingDir = projWorkingDir();
	public final static String currSuiteName = getSuiteName();
	public final static String logFolderPath = getLogFolderPath();;
	public final static String reportsPath = getRepFolderPath();
	public final static String reportsArchivePath = getRepArchivePath();
	public final static String downloadsPath = getRepFolderPath() + File.separator + downloadsDirName;
	public final static String firefoxDriverPath = getFirefoxDriver("");
	public final static String chromeDriverPath = getChromeDriver("");
	public final static String ieDriverPath = getIEDriver("");
	public final static SuiteConfigReader suiteConfig = new SuiteConfigReader();
	public final static String DBTEST = "TEST2";
	public final static String DBUAT = "UATE";
	public final static String DBSAND2 = "SAND2";
	//Wait Timeouts
	public static final String startTimeStamp = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss_SSS").format(new Date());
	public static final int defSleepTime = 2000; //Microseconds
	public static final int wdWaitTimeout =20; //seconds
	public static final int pageLoadTimeout = 60; //seconds
	public static final int implicitWaitTimeout = 30;	//Seconds
	
	//Mobile Co-ordinates
	public static final int CONFIRM_X = 720;
	public static final int CONFIRM_Y = 2230;
	public static final int GALLERY_X = 150;
	public static final int GALLERY_Y = 980;
	public static final int CAMERA_KEYEVENT = 24;
	
	//Database Constants
	public static final String dbDriver_Oracle =  "oracle.jdbc.driver.OracleDriver";
	public static final String dbDriver_SQLServer =  "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	/*public static final String dbName_OffenderConnect = "OC";
	public static final String dbName_Attachments = "AE";
	public static final String dbName_CreditBank = "CB";
	public static final String dbName_EMS = "EMS";
	public static final String dbName_IBanker = "IB";
	public static final String dbName_OMSQA56 = "QA_EVOOMS_56";*/
		
	public static final String replaceText = "<<ReplaceText>>";	
	
	//Project Names for suiteconfig Keys
	public static final String projRicoh = "RICOH PROJECT";	
	public static final String projIBP = "IBP";
	
	//Test Data Column Name for Record Alias
	public static final String colRecAlias = "RecAlias";
	
	
	public final static String winiumDriverPath = getWiniumDriverPath("");
	//public final static String winiumPortNum = suiteConfig.getWiniumPort("WiniumDriver");

	private static String getRootDir(){
		String strProjDir = System.getProperty("user.dir");
		return new File(strProjDir).getName();
	}
	
	private static String projWorkingDir() {
		return System.getProperty("user.dir");
	}
	
	private static String getSuiteName() {
		return new File(System.getProperty("user.dir")).getName();
	}
	
	private static String getLogFolderPath() {
		return System.getProperty("user.dir") + File.separator + logsDirName;
	}
	
	private static String getRepFolderPath() {
		return System.getProperty("user.dir") + File.separator + reportsDirName;
	}
	
	private static String getRepArchivePath() {
		return System.getProperty("user.dir") + File.separator + reportsArchiveDirName;
	}

	
	private static String getChromeDriver(String verNo){
		String driverPath = "";
		String hostOS = System.getProperty("os.name").trim();
		
		//Set path on windows OS
		if (hostOS.toLowerCase().contains("windows")){
			if (verNo.length() > 0) {
				driverPath = projWorkingDir + File.separator + toolDirName + File.separator + "ChromeDriver"
						+ File.separator + "Windows" + File.separator + verNo + File.separator + "chromedriver.exe";
			}else {
				driverPath = projWorkingDir + File.separator + toolDirName + File.separator + "ChromeDriver"
						+ File.separator + "Windows" + File.separator + "chromedriver.exe";
			}

			//Set path on Linux OS
		}else if (hostOS.toLowerCase().contains("linux")){
			if (verNo.length() > 0) {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "ChromeDriver"
						+ File.separator + "Linux" + File.separator + verNo + File.separator + "chromedriver.exe";				
			}else {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "ChromeDriver"
						+ File.separator + "Linux" + File.separator + "chromedriver.exe";				
			}		
			//Set path on Mac OS
		}else if (hostOS.toLowerCase().contains("mac")) {
			if (verNo.length() > 0) {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "ChromeDriver"
						+ File.separator + "Mac" + File.separator + verNo + File.separator + "chromedriver";				
			}else {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "ChromeDriver"
						+ File.separator + "Mac" + File.separator + "chromedriver";				
			}
		}
		
		//return driver exe path
		return driverPath;
	}
	
	private static String getFirefoxDriver(String verNo){
		String driverPath = "";
		String hostOS = System.getProperty("os.name").trim();
		
		//Set path on windows OS
		if (hostOS.toLowerCase().contains("windows")){
			if (verNo.length() > 0) {
				driverPath = projWorkingDir + File.separator + toolDirName + File.separator + "FirefoxDriver"
						+ File.separator + "Windows" + File.separator + verNo + File.separator + "geckodriver.exe";	
				
			}else {
				driverPath = projWorkingDir + File.separator + toolDirName + File.separator + "FirefoxDriver"
						+ File.separator + "Windows" + File.separator + "geckodriver.exe";				
			}

			//Set path on Linux OS
		}else if (hostOS.toLowerCase().contains("linux")){
			if (verNo.length() > 0) {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "FirefoxDriver"
						+ File.separator + "Linux" + File.separator + verNo + File.separator + "geckodriver.exe";				
			}else {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "FirefoxDriver"
						+ File.separator + "Linux" + File.separator + "geckodriver.exe";				
			}
			
			//Set path on Mac OS
		}else if (hostOS.toLowerCase().contains("mac")) {			
			if (verNo.length() > 0) {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "FirefoxDriver"
						+ File.separator + "Mac" + File.separator + verNo + File.separator + "geckodriver.exe";				
			}else {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "FirefoxDriver"
						+ File.separator + "Mac" + File.separator + "geckodriver.exe";				
			}
		}	
		
		//return driver exe path
		return driverPath;
	}
	
	
	private static String getIEDriver(String verNo){
		String driverPath = "";
		String hostOS = System.getProperty("os.name").trim();
		
		//Set path on windows OS
		if (hostOS.toLowerCase().contains("windows")){
			if (verNo.length() > 0) {
				driverPath = projWorkingDir + File.separator + toolDirName + File.separator + "IEDriver"
						+ File.separator + "Windows" + File.separator + verNo + File.separator + "IEDriverServer.exe";				
			}else {
				driverPath = toolDirName + File.separator + "IEDriver"
						+ File.separator + "Windows" + File.separator + "IEDriverServer.exe";				
			}

			//Set path on Linux OS
		}else if (hostOS.toLowerCase().contains("linux")){
			if (verNo.length() > 0) {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "IEDriver"
						+ File.separator + "Linux" + File.separator + verNo + File.separator + "IEDriverServer.exe";				
			}else {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "IEDriver"
						+ File.separator + "Linux" + File.separator + "IEDriverServer.exe";				
			}
			
			//Set path on Mac OS
		}else if (hostOS.toLowerCase().contains("mac")) {
			if (verNo.length() > 0) {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "IEDriver"
						+ File.separator + "Mac" + File.separator + verNo + File.separator + "IEDriverServer.exe";				
			}else {
				driverPath = rootDir + File.separator + toolDirName + File.separator + "IEDriver"
						+ File.separator + "Mac" + File.separator + "IEDriverServer.exe";				
			}
		}	
		
		//return driver exe path
		return driverPath;
	}	
	
	
	private static String getWiniumDriverPath(String verNo) {

        String driverPath = "";
        String hostOS = System.getProperty("os.name").trim();
        
        //Set path on windows OS
        if (hostOS.toLowerCase().contains("windows")){
            if (verNo.length() > 0) {
                driverPath = rootDir + File.separator + toolDirName + File.separator + "WiniumDriver"
                        + File.separator + "Windows" + File.separator + verNo + File.separator + "Winium.Desktop.Driver.exe";
            }else {
                driverPath = rootDir + File.separator + toolDirName + File.separator + "WiniumDriver"
                        + File.separator + "Windows" + File.separator + "Winium.Desktop.Driver.exe";
            }

            //Set path on Linux OS
        }else if (hostOS.toLowerCase().contains("linux")){
            if (verNo.length() > 0) {
                driverPath = rootDir + File.separator + toolDirName + File.separator + "WiniumDriver"
                        + File.separator + "Linux" + File.separator + verNo + File.separator + "Winium.Desktop.Driver.exe";                
            }else {
                driverPath = rootDir + File.separator + toolDirName + File.separator + "WiniumDriver"
                        + File.separator + "Linux" + File.separator + "Winium.Desktop.Driver.exe";                
            }        
            //Set path on Mac OS
        }else if (hostOS.toLowerCase().contains("mac")) {
            if (verNo.length() > 0) {
                driverPath = rootDir + File.separator + toolDirName + File.separator + "WiniumDriver"
                        + File.separator + "Mac" + File.separator + verNo + File.separator + "Winium.Desktop.Driver";                
            }else {
                driverPath = rootDir + File.separator + toolDirName + File.separator + "WiniumDriver"
                        + File.separator + "Mac" + File.separator + "Winium.Desktop.Driver";                
            }
        }
        
        //return driver exe path
        return driverPath;
    
        
    }

		  
}
