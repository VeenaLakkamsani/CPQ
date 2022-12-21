package ricoh.core.accelerators;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class WebActionEngine {
    
	boolean res;
	// Declare Drivers
	public WebDriver driver = null;
	// public EventFiringWebDriver driver = null;
	public RemoteWebDriver remoteDriver = null;

	// Declare Loggers
	private final String logClassName = "ActionEngine";

	// Declare log msg strings
	private final String msgClickSuccess = "Successfully Clicked On ";
	private final String msgClickFailure = "Unable To Click On ";
	private final String msgTypeSuccess = "Successfully Typed On ";
	private final String msgTypeFailure = "Unable To Type On ";
	private final String msgIsElementFoundSuccess = "Successfully Found Element ";
	private final String msgIsElementFoundFailure = "Unable To Found Element ";

	/*
	 * public EventFiringWebDriver getDriver() { return this.driver; }
	 */

	public void closeDriver() {
		try {
			this.driver.quit();
			Thread.sleep(gblConstants.defSleepTime);
			LogManager.logInfo(WebActionEngine.class.getName(), "Closed web driver");
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Exception to close web driver");
		}
	}

	public RemoteWebDriver getRemoteDriver() {
		return this.remoteDriver;
	}

	public WebDriver getDriver() {
		// return this.webDriver;
		return this.driver;
	}

	/*
	 * public EventFiringWebDriver setWebdriverForGrid(String gridhuburl, String
	 * browser) throws IOException, InterruptedException { String os =
	 * (System.getProperty("OS") != null &&
	 * !"I don't care".equalsIgnoreCase(System.getProperty("OS"))) ?
	 * System.getProperty("OS").trim() : null;
	 * 
	 * switch (browser.trim().toLowerCase()) { case "firefox":
	 * System.setProperty("webdriver.gecko.driver", gblConstants.firefoxDriverPath);
	 * //FirefoxProfile profile = new FirefoxProfile();
	 * //profile.setEnableNativeEvents(true); FirefoxOptions fOptions = new
	 * FirefoxOptions(); if (os != null)
	 * fOptions.setCapability(CapabilityType.PLATFORM_NAME, os); //
	 * fOptions.setProfile(profile); webDriver = new RemoteWebDriver(new
	 * URL(gridhuburl), fOptions);
	 * 
	 * break; case "internet explorer": case "internetexplorer": case "ie":
	 * InternetExplorerOptions ieOptions = new InternetExplorerOptions();
	 * ieOptions.setCapability(InternetExplorerDriver.
	 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	 * ieOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
	 * true); ieOptions.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,
	 * true); ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,
	 * true);
	 * ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
	 * false); ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
	 * ieOptions.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,
	 * true); ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); if (os
	 * != null) ieOptions.setCapability(CapabilityType.PLATFORM_NAME, os);
	 * 
	 * // Clear browsing history try { Process p = Runtime.getRuntime().
	 * exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255"); p.waitFor(); }
	 * catch (Exception e) { System.out.println(e); }
	 * 
	 * File driverExe = new File(gblConstants.ieDriverPath);
	 * System.setProperty("webdriver.ie.driver", driverExe.getAbsolutePath());
	 * webDriver = new RemoteWebDriver(new URL(gridhuburl), ieOptions);
	 * Thread.sleep(1000); break;
	 * 
	 * case "chrome": //System.setProperty("webdriver.chrome.driver",
	 * gblConstants.chromeDriverPath); WebDriverManager.chromedriver().setup();
	 * ChromeOptions options = new ChromeOptions();
	 * options.addArguments("test-type");
	 * options.addArguments("--disable-extensions");
	 * options.addArguments("--dns-prefetch-disable");
	 * options.addArguments("--start-maximized");
	 * options.addArguments("--disable-infobars");
	 * 
	 * // added //Create prefs map to store all preferences Map<String, Object>
	 * prefs = new HashMap<String, Object>(); //Put this into prefs map to switch
	 * off browser notification
	 * prefs.put("profile.default_content_setting_values.notifications", 2);
	 * 
	 * options.setExperimentalOption("prefs", prefs); // added
	 * 
	 * if (os != null) options.setCapability(CapabilityType.PLATFORM_NAME, os);
	 * webDriver = new RemoteWebDriver(new URL(gridhuburl), options); break;
	 * 
	 * case "Safari": SafariOptions soptions = new SafariOptions(); if (os != null)
	 * soptions.setCapability(CapabilityType.PLATFORM_NAME, os); webDriver = new
	 * RemoteWebDriver(new URL(gridhuburl), soptions); } driver = new
	 * EventFiringWebDriver(this.webDriver); //WebDriverListener myListener = new
	 * WebDriverListener(); //driver.register(myListener);
	 * 
	 * return this.driver; }
	 * 
	 * public EventFiringWebDriver setWebDriverForLocal(String browser) throws
	 * IOException, InterruptedException {
	 * 
	 * switch (browser.trim().toLowerCase()) { case "firefox":
	 * System.setProperty("webdriver.gecko.driver", gblConstants.firefoxDriverPath);
	 * 
	 * FirefoxProfile ffProfile = new FirefoxProfile();
	 * ffProfile.setPreference("browser.download.dir", gblConstants.downloadsPath);
	 * ffProfile.setPreference("browser.download.folderList", 2);
	 * ffProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
	 * "application/pdf;"); ffProfile.setPreference("pdfjs.disabled", true);
	 * ffProfile.setPreference("pdfjs.enabledCache.state", false);
	 * 
	 * FirefoxOptions ffoptions = new FirefoxOptions().setProfile(ffProfile);
	 * 
	 * webDriver = new FirefoxDriver(ffoptions);
	 * 
	 * // webDriver = new FirefoxDriver();
	 * 
	 * break; case "internet explorer": case "internetexplorer": case "ie":
	 * 
	 * InternetExplorerOptions ieOptions = new InternetExplorerOptions();
	 * ieOptions.setCapability(InternetExplorerDriver.
	 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); //
	 * ieOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, //
	 * true); //
	 * ieOptions.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
	 * // ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
	 * // ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
	 * // false); // ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS,
	 * true); //
	 * ieOptions.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,
	 * // true); //
	 * ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, //
	 * true); //
	 * ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, //
	 * true); // ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); //
	 * ieOptions.setCapability(CapabilityType.PLATFORM_NAME,"Windows");
	 * 
	 * // Clear browsing history Process p = Runtime.getRuntime().
	 * exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255"); p.waitFor();
	 * 
	 * File driverExe = new File(gblConstants.ieDriverPath);
	 * System.setProperty("webdriver.ie.driver", driverExe.getAbsolutePath()); //
	 * System.setProperty("webdriver.ie.driver", gblConstants.ieDriverPath);
	 * webDriver = new InternetExplorerDriver(ieOptions); Thread.sleep(1000);
	 * 
	 * break;
	 * 
	 * case "chrome": //System.setProperty("webdriver.chrome.driver",
	 * gblConstants.chromeDriverPath); // DesiredCapabilities chromeCaps =
	 * DesiredCapabilities.chrome();
	 * WebDriverManager.chromedriver().clearResolutionCache().setup(); ChromeOptions
	 * options = new ChromeOptions(); options.setAcceptInsecureCerts(true);
	 * options.addArguments("test-type");
	 * options.addArguments("--disable-extensions");
	 * options.addArguments("--dns-prefetch-disable");
	 * options.addArguments("--start-maximized");
	 * options.addArguments("--disable-infobars");
	 * options.addArguments("--disable-notifications");
	 * options.addArguments("--start-maximized");
	 * 
	 * // chromeCaps.setCapability(ChromeOptions.CAPABILITY, options);
	 * 
	 * Map<String, Object> prefs = new LinkedHashMap<>();
	 * prefs.put("plugins.always_open_pdf_externally", true);
	 * prefs.put("download.default_directory", gblConstants.downloadsPath);
	 * options.setExperimentalOption("prefs", prefs);
	 * 
	 * // webDriver = new ChromeDriver(chromeCaps); webDriver = new
	 * ChromeDriver(options); break;
	 * 
	 * case "chromemobileapp": System.setProperty("webdriver.chrome.driver",
	 * gblConstants.chromeDriverPath); Map<String, String> mobileEmulation = new
	 * HashMap<>();
	 * 
	 * mobileEmulation.put("deviceName", "Nexus 5");
	 * 
	 * ChromeOptions chromeOptions = new ChromeOptions();
	 * 
	 * chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
	 * 
	 * webDriver = new ChromeDriver(chromeOptions); break;
	 * 
	 * case "Safari": webDriver = new SafariDriver();
	 * 
	 * 
	 * for(int i=1;i<=10;i++){
	 * 
	 * try{ WebDriver driver=new SafariDriver(); //WebDriver=new SafariDriver();
	 * 
	 * break; }catch(Exception e1){ Runtime.getRuntime().exec(
	 * "taskkill /F /IM Safari.exe"); Thread.sleep(3000);
	 * Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe" );
	 * Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
	 * 
	 * continue;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } driver = new EventFiringWebDriver(this.webDriver); //WebDriverListener
	 * myListener = new WebDriverListener(); //driver.register(myListener);
	 * 
	 * return this.driver; }
	 */
	public WebDriver setWebDriverForLocal(String browser) throws IOException, InterruptedException {

		switch (browser.trim().toLowerCase()) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", gblConstants.firefoxDriverPath);

			FirefoxProfile ffProfile = new FirefoxProfile();
			ffProfile.setPreference("browser.download.dir", gblConstants.downloadsPath);
			ffProfile.setPreference("browser.download.folderList", 2);
			ffProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;");
			ffProfile.setPreference("pdfjs.disabled", true);
			ffProfile.setPreference("pdfjs.enabledCache.state", false);

			FirefoxOptions ffoptions = new FirefoxOptions().setProfile(ffProfile);

			driver = new FirefoxDriver(ffoptions);

			// webDriver = new FirefoxDriver();

			break;
		case "internet explorer":
		case "internetexplorer":
		case "ie":

			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// ieOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
			// true);
			// ieOptions.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			// ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			// ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
			// false);
			// ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
			// ieOptions.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,
			// true);
			// ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS,
			// true);
			// ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS,
			// true);
			// ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// ieOptions.setCapability(CapabilityType.PLATFORM_NAME,"Windows");

			// Clear browsing history
			Process p = Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			p.waitFor();

			File driverExe = new File(gblConstants.ieDriverPath);
			System.setProperty("webdriver.ie.driver", driverExe.getAbsolutePath());
			// System.setProperty("webdriver.ie.driver", gblConstants.ieDriverPath);
			driver = new InternetExplorerDriver(ieOptions);
			Thread.sleep(1000);

			break;

		case "chrome":
			 System.setProperty("webdriver.chrome.driver", gblConstants.chromeDriverPath);
			// DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
			//WebDriverManager.chromedriver().clearResolutionCache().setup();
			/*ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			options.addArguments("test-type");
			options.addArguments("--disable-extensions");
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addArguments("--disable-gpu");
			// chromeCaps.setCapability(ChromeOptions.CAPABILITY, options);

			Map<String, Object> prefs = new LinkedHashMap<>();
			prefs.put("plugins.always_open_pdf_externally", true);
			prefs.put("download.default_directory", gblConstants.downloadsPath);
			options.setExperimentalOption("prefs", prefs);*/

			// webDriver = new ChromeDriver(chromeCaps);
			//driver = new ChromeDriver(options);
			driver = new ChromeDriver();
			break;

		case "chromemobileapp":
			System.setProperty("webdriver.chrome.driver", gblConstants.chromeDriverPath);
			Map<String, String> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", "Nexus 5");

			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			driver = new ChromeDriver(chromeOptions);
			break;

		case "Safari":
			driver = new SafariDriver();

			/*
			 * for(int i=1;i<=10;i++){
			 * 
			 * try{ WebDriver driver=new SafariDriver(); //WebDriver=new SafariDriver();
			 * 
			 * break; }catch(Exception e1){ Runtime.getRuntime().exec(
			 * "taskkill /F /IM Safari.exe"); Thread.sleep(3000);
			 * Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe" );
			 * Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
			 * 
			 * continue;
			 * 
			 * }
			 * 
			 * }
			 */
		}
		// driver = new EventFiringWebDriver(this.webDriver);
		// WebDriverListener myListener = new WebDriverListener();
		// driver.register(myListener);

		return this.driver;
	}

	public void setRemoteWebDriverForCloudSauceLabs() throws IOException, InterruptedException {
		// if (this.browser.equalsIgnoreCase("Safari")) {
		// DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		// desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,
		// this.browser);
		// desiredCapabilities.setCapability(CapabilityType.VERSION,
		// this.version);
		// desiredCapabilities.setCapability(CapabilityType.PLATFORM,
		// this.platform);
		// desiredCapabilities.setCapability("username", this.userName);
		// desiredCapabilities.setCapability("accessKey", this.accessKey);
		// desiredCapabilities.setCapability("accessKey", this.accessKey);
		// desiredCapabilities.setCapability("name", this.executedFrom + " - "
		// /*+ this.jobName + " - " + this.buildNumber*/ + this.platform + " - "
		// + this.browser);
		// URL commandExecutorUri = new
		// URL("http://ondemand.saucelabs.com/wd/hub");
		// for (int i = 1; i <= 10; i++) {
		//
		// try {
		// this.WebDriver = new RemoteWebDriver(commandExecutorUri,
		// desiredCapabilities);
		//
		// break;
		// } catch (Exception e1) {
		// Runtime.getRuntime().exec("taskkill /F /IM Safari.exe");
		// Thread.sleep(3000);
		// Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
		// Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
		//
		// continue;
		//
		// }
		// }
		// } else {
		//
		// DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		// desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,
		// this.browser);
		// desiredCapabilities.setCapability(CapabilityType.VERSION,
		// this.version);
		// desiredCapabilities.setCapability(CapabilityType.PLATFORM,
		// this.platform);
		// desiredCapabilities.setCapability("username", this.userName);
		// desiredCapabilities.setCapability("accessKey", this.accessKey);
		// desiredCapabilities.setCapability("accessKey", this.accessKey);
		// desiredCapabilities.setCapability("name", this.executedFrom + " - "
		// /*+ this.jobName + " - " + this.buildNumber*/ + this.platform + " - "
		// + this.browser);
		// URL commandExecutorUri = new
		// URL("http://ondemand.saucelabs.com/wd/hub");
		// this.WebDriver = new RemoteWebDriver(commandExecutorUri,
		// desiredCapabilities);
		// this.Driver = new EventFiringWebDriver(this.WebDriver);
		// WebDriverListener myListener = new WebDriverListener();
		// this.Driver.register(myListener);
		// }
	}

	public boolean loadPage(String pageUrl, String strExpTitle) {
		try {			
			this.driver.get(pageUrl);
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().pageLoadTimeout(gblConstants.pageLoadTimeout, TimeUnit.SECONDS);
			this.driver.manage().timeouts().implicitlyWait(gblConstants.implicitWaitTimeout, TimeUnit.SECONDS);
			String strActTitle = this.driver.getTitle().trim();                
			System.out.println("strActTitle = " + strActTitle);
			System.out.println("strExpTitle = " + strExpTitle);
			if(strActTitle.contains(strExpTitle)) res =true;
			else res=false;
			LogManager.logInfo(WebActionEngine.class.getName(), "Web Page with url "+pageUrl+" loaded successfully");
//			reportStatus(res, "Actual Page Title matched with expected Page Title "+strActTitle, "Actual Page Title hasn't matched with expected Page Title "+strActTitle);
			// if (strActTitle.equalsIgnoreCase(strExpTitle.trim())) {
			return true;
			/*
			 * } else { return false; }
			 */
		} catch (WebDriverException e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to load web page using url " + pageUrl);
			return false;
		}
	}

	public boolean selectByIndex(String locator, int index, String locatorName) {
		boolean blnRes = false;
		try {
			Select selElement = new Select(driver.findElement(By.xpath(locator)));
			selElement.selectByIndex(index);

			List<WebElement> options = selElement.getOptions();
			WebElement seletedOption = selElement.getFirstSelectedOption();

			for (int cntr = 0; cntr < options.size(); cntr++) {
				if (seletedOption.getText().trim().equalsIgnoreCase(options.get(cntr).getText().trim())) {
					LogManager.logInfo(WebActionEngine.class.getName(), "Successfully selected option "
							+ seletedOption.getText() + " using index " + index + " from " + locatorName);
					blnRes = true;
					Thread.sleep(1000);
					break;
				} else {
					blnRes = false;
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, logClassName, "Unable to select index " + index + " from " + locatorName);
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Failed to select option at index " + index + " from " + locatorName);
			blnRes = false;
		}
		return blnRes;
	}

	public boolean selectByString(String locator, String inde) {
		boolean blnRes = false;
		try {
			Select dropdown = new Select(getElement(locator));
			// Get all options
			List<WebElement> options = dropdown.getOptions();
			// Get the length
			System.out.println(options.size());
			// Loop to print one by one
			for (WebElement option : options) {
				if (option.getText().trim().contains(inde)) {
					option.click();
					blnRes = true;
					LogManager.logInfo(WebActionEngine.class.getName(), "Successfully selected option ");
					break;
				}
			}

		} catch (Exception e) {
			LogManager.logException(e, logClassName, "Unable to select value ");
			LogManager.logInfo(WebActionEngine.class.getName(), "Failed to select option value ");
			blnRes = false;
		}
		return blnRes;
	}

	public boolean selectByName(String locator, String optionName, String eleName) {
		boolean blnRes = false;

		try {

			Select selElement = new Select(getElement(locator));
			List<WebElement> options = selElement.getOptions();
			for (WebElement option : options) {
				if (option.getText().trim().equalsIgnoreCase(optionName.trim())) {
					option.click();
					Thread.sleep(1000);
					blnRes = true;
					LogManager.logInfo(WebActionEngine.class.getName(),
							"Successfully selected option - " + optionName + " from " + eleName);
					break;
				}
			}
			// selElement.selectByVisibleText(optionName);
		} catch (Exception e) {
			blnRes = false;
			e.printStackTrace();
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to select option " + optionName + " from " + eleName);
		}
		return blnRes;
	}

	public boolean selectByValue(String locator, String value) {
		boolean blnRes = false;

		try {
			Select selElement = new Select(getElement(locator));
			selElement.selectByValue(value);

			LogManager.logInfo(WebActionEngine.class.getName(), "Successfully selected option - " + value);
			blnRes = true;
			// selElement.selectByVisibleText(optionName);
		} catch (Exception e) {
			blnRes = false;
			e.printStackTrace();
			LogManager.logException(e, WebActionEngine.class.getName(), "Exception to select option " + value);
		}
		return blnRes;
	}

	public boolean click(By locator, String locatorName) throws Exception {
		boolean status = false;
		try {
			WebElement ele = this.driver.findElement(locator);
			scrollElementIntoView(ele);
			if (ele.isDisplayed() && ele.isEnabled()) {
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

	public boolean RefreshPage() throws Exception {
		boolean status = false;
		try {
			driver.navigate().refresh();
			status = true;
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure);
			// throw e;
		}
		return status;
	}

	public boolean UpLoadFile(String locator, String sFilePath) throws Exception {
		boolean status = false;
		try {

			WebElement addFile = driver.findElement(byLocator(locator));
			addFile.click();
			Thread.sleep(3000);
			// put path to your image in a clipboard
			StringSelection ss = new StringSelection(sFilePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			// imitate mouse events like ENTER, CTRL+C, CTRL+V
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			status = true;
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure);
			// throw e;
		}
		return status;
	}

	public boolean click(String locator, String elementName) throws Exception {
		boolean status = false;
		try {			
			WebElement ele = getElement(locator);
			highlight(ele);
			scrollElementIntoView(locator);
			highlight(locator);
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.click();
				Thread.sleep(1000);
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

	public boolean eleDisplayed(String locator, String elementName) throws Exception {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			if (ele.isDisplayed()) {
				scrollElementIntoView(locator);
				highlight(locator);
				status = true;
				Thread.sleep(1000);
				LogManager.logInfo(logClassName, this.msgIsElementFoundSuccess + elementName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgIsElementFoundFailure + elementName);
			throw e;
		}
		return status;
	}

	public boolean eleEnabled(String locator, String elementName) throws Exception {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			if (ele.isEnabled()) {
				status = true;
				LogManager.logInfo(logClassName, this.msgIsElementFoundSuccess + elementName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgIsElementFoundFailure + elementName);
			throw e;
		}
		return status;
	}

	// IsSelected
	public boolean eleSelected(String locator, String elementName) throws Exception {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			if (ele.isSelected()) {
				status = true;
				LogManager.logInfo(logClassName, this.msgIsElementFoundSuccess + elementName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgIsElementFoundFailure + elementName);
			throw e;
		}
		return status;
	}

	public void EleEnabled(String locator, String elementName) throws Exception {
		try {
			WebElement ele = getElement(locator);
			if (ele.isEnabled()) {
				ele.click();
			}
		} catch (Exception e) {
			throw e;
		}
	};

	public boolean waitAndClick(String locator, String locatorName) throws Exception {
		boolean status = false;
		try {
			//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
			WebDriverWait wait = new WebDriverWait(driver, gblConstants.wdWaitTimeout);
			By eleLocator = byLocator(locator);
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(eleLocator));

			if (ele.isDisplayed() && ele.isEnabled()) {
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

	public boolean isDynamicElementPresent(String locator, String locatorName, boolean expected) {
		boolean status = false;
		try {
			//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
			WebDriverWait wait = new WebDriverWait(driver, gblConstants.wdWaitTimeout);
			By eleLocator = byLocator(locator);
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(eleLocator));
			// WebElement ele = getElement(locatorName);
			status = (ele.isDisplayed()) ? true : false;
		} catch (Exception e) {
			status = false;
		}
		if (!(expected ^ status)) {
			status = true;
			LogManager.logInfo(OFSCWebActionEngine.class.getName(),
					"ElementFoundSuccess" + locatorName + " diplayed as " + expected);
		} else {
			status = false;
			LogManager.logError(OFSCWebActionEngine.class.getName(),
					"ElementFoundFailure" + locatorName + "Not Displayed " + expected);
		}
		return status;
	}

	public boolean waitAndType(String locator, String locatorName) throws Exception {
		boolean status = false;
		try {
			//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
			WebDriverWait wait = new WebDriverWait(driver, gblConstants.wdWaitTimeout);
			By eleLocator = byLocator(locator);
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(eleLocator));

			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.sendKeys(locatorName);
				status = true;
				LogManager.logInfo(logClassName, this.msgClickSuccess);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure);
			throw e;
		}
		return status;
	}

	/*
	 * public boolean webDriverWaitForTitle(String expectedTitle) throws Exception {
	 * boolean status = false; try {
	 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	 * Wait<WebDriver> fluentWait = new
	 * FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
	 * .pollingEvery(Duration.ofSeconds(5)) .ignoring(NoSuchElementException.class);
	 * //WebDriverWait wait = new WebDriverWait(driver, 500); status =
	 * fluentWait.until(ExpectedConditions.titleContains(expectedTitle)); status =
	 * true; LogManager.logInfo(logClassName,
	 * "Title loads successfully "+this.getDriver().getTitle() ); } catch (Exception
	 * e) { status = false; LogManager.logException(e, logClassName,
	 * "Timeout before title loads "+this.getDriver().getTitle()); throw e; } return
	 * status; }
	 */
	public boolean webDriverwaitAndClick(String locator, String locatorName) throws Exception {
		boolean status = false;
		try {
	//		Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
			WebDriverWait wait = new WebDriverWait(driver, gblConstants.wdWaitTimeout);
			By eleLocator = byLocator(locator);
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(eleLocator));
			ele.click();
			status = true;
			LogManager.logInfo(logClassName, this.msgClickSuccess + locatorName);
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure + locatorName);
			throw e;
		}
		return status;
	}

	public boolean clickUsingEnter(String locator, String elementName) throws Exception {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			scrollElementIntoView(locator);
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.sendKeys(Keys.ENTER);
				status = true;
				try {
					Thread.sleep(1000);
					if (ele.isDisplayed() && ele.isEnabled())
						ele.sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				} catch (Exception e) {

				}
				LogManager.logInfo(logClassName, this.msgClickSuccess + elementName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure + elementName);
			throw e;
		}
		return status;
	}

	public boolean clickUsingEnter(WebElement ele, String elementName) throws Exception {
		boolean status = false;
		try {
			//WebElement ele = getElement(locator);
			scrollElementIntoView(ele);
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.sendKeys(Keys.ENTER);
				status = true;
				try {
					Thread.sleep(1000);
					if (ele.isDisplayed() && ele.isEnabled())
						ele.sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				} catch (Exception e) {

				}
				LogManager.logInfo(logClassName, this.msgClickSuccess + elementName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure + elementName);
			throw e;
		}
		return status;
	}
	
	/**
	 * If more than one element present
	 *
	 * @param locator
	 * @param index
	 * @return
	 * @throws Throwable
	 */
	public boolean click(By locator, int index, String locatorName) {
		boolean status = false;
		try {
			WebElement ele = this.driver.findElements(locator).get(index);
			scrollElementIntoView(ele);
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.click();
				status = true;
				LogManager.logInfo(logClassName, this.msgClickSuccess + locatorName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgClickFailure + locatorName);
		}
		return status;
	}

	public boolean clickusingJavaScript(String locator) {
		boolean blnRes = false;
		try {
			Thread.sleep(1000);
			highlight(locator);
			Thread.sleep(gblConstants.defSleepTime);
			WebElement element = this.driver.findElement(byLocator(locator));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			
			LogManager.logInfo(logClassName, "Used java script executor to click element " + locator);
			blnRes = true;
		} catch (Exception e) {
			blnRes = false;
			LogManager.logError(logClassName, "Unable to click " + locator + " using java script executor" + locator);
		}
		//testObj.reportStatus(blnRes, "Clicked on "+locator, "Unable to click on "+locator);
		return blnRes;
	}

	public void runJavaScriptMethod(String exeJavaScript) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(exeJavaScript, "");
	}

	public boolean isElementPresent(String locator, String locatorName, boolean expected) {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			status = (ele.isDisplayed()) ? true : false;
		} catch (Exception e) {
			status = false;
		}
		if (!(expected ^ status)) {
			status = true;
			LogManager.logInfo(logClassName, this.msgIsElementFoundSuccess + locatorName + " displayed as " + expected);
		} else {
			status = false;
			LogManager.logError(logClassName,
					this.msgIsElementFoundFailure + locatorName + " displayed as " + expected);
		}
		return status;
	}

	/**
	 * Check If Field is Enabled
	 * 
	 * @param locator
	 * @param locatorName
	 * @return
	 */
	public boolean isElementEnabled(String locator, String locatorName, boolean expected) {
		boolean status = false;
		try {
			WebElement ele = getElement(locator);
			status = (ele.isEnabled()) ? true : false;

			if (!(expected ^ status)) {
				status = true;
				LogManager.logInfo(logClassName,
						this.msgIsElementFoundSuccess + locatorName + " enabled status as " + expected);
			} else {
				status = false;
				LogManager.logError(logClassName,
						this.msgIsElementFoundFailure + locatorName + " enabled status as " + expected);
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * Switch to frame
	 * 
	 * @return
	 */
	public boolean switchToIFrame() {
		boolean flag = false;
		try {
			WebElement iframe = this.driver.findElement(By.tagName("iframe"));
			this.driver.switchTo().frame(iframe);
			flag = true;
		} catch (Exception e) {
			flag = false;
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to swith to frame");
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Wait for js to load
	 * 
	 * @return
	 */
	public Boolean waitForJStoLoad() {

		boolean flag = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageLoadStatus = (String) js.executeScript("return document.readyState");
			if (pageLoadStatus.equals("complete")) {
				flag = true;
			} else {
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean type(By locator, String testdata, String locatorName) {
		boolean status = false;
		try {
			WebElement ele = driver.findElement(locator);
			ele.clear();
			ele.sendKeys(testdata);

			String strActValue = ele.getAttribute("value").trim();
			if (strActValue.equals(testdata)) {
				status = true;
				LogManager.logInfo(logClassName, this.msgTypeSuccess + locatorName);
			}
		} catch (Exception e) {
			status = false;
			LogManager.logException(e, logClassName, this.msgTypeFailure + locatorName);
		}
		return status;
	}

	public boolean setCheckbox(String locator, boolean blnEnabled, String locatorName) {
		boolean blnRes = false;
		WebElement ele = getElement(locator);
		if (ele.isSelected() ^ blnEnabled) {
			ele.click();
			blnRes = true;
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Successfully updated checkbox " + locatorName + " as " + blnEnabled);
		}
		return blnRes;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled into
	 * view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:link,menus etc..)
	 */
	synchronized public boolean mouseHover(String locator, String locatorName) {
		boolean flag = false;
		try {
			WebElement mo = getElement(locator);
			new Actions(this.driver).moveToElement(mo).build().perform();
			flag = true;
			LogManager.logInfo(WebActionEngine.class.getName(), "MouseOver action is performed on " + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, logClassName, "MouseOver action is not perform on " + locatorName);
			return false;
		}
		return flag;
	}

	public boolean actionsClick(String locator, String locatorName) {
		boolean flag = false;
		try {
			WebElement mo = getElement(locator);
			new Actions(this.driver).click(mo).build().perform();
			Thread.sleep(1000);
			flag = true;
			LogManager.logInfo(WebActionEngine.class.getName(), "MouseOver action is performed on " + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, logClassName, "MouseOver action is not performed on " + locatorName);
			return false;
		}
		return flag;
	}

	public boolean mouseRightClick(String locator) {
		boolean flag = false;
		try {
			WebElement mo = getElement(locator);
			new Actions(this.driver).contextClick(mo).perform();
			flag = true;
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Mouse Right Click action is performed on " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.logException(e, logClassName, "Mouse Right Click action is not performed on " + locator);
			return false;
		}
		return flag;
	}

	synchronized public boolean mouseDraw(String locator, String locatorName) {
		boolean flag = false;
		try {
			WebElement ele = getElement(locator);

			Actions builder = new Actions(this.driver);
			Action drawAction = builder.moveToElement(ele, 50, 50) // start
																	// point
					.click().moveByOffset(100, 60) // second point
					.doubleClick().build();
			drawAction.perform();

			flag = true;
			LogManager.logInfo(WebActionEngine.class.getName(), "MouseOver action is performed on " + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, logClassName, "MouseOver action is not perform on " + locatorName);
			return false;
		}
		return flag;
	}

	synchronized public boolean mouseDrawSignature(String locator, String locatorName) {
		boolean flag = false;
		try {
			WebElement ele = getElement(locator);

			Actions actionBuilder = new Actions(driver);
			Action drawOnCanvas = actionBuilder.contextClick().moveToElement(ele, 8, 8).clickAndHold()
					.moveByOffset(120, 120).moveByOffset(60, 70).moveByOffset(-140, -140).release().build();
			drawOnCanvas.perform();
			flag = true;
			LogManager.logInfo(WebActionEngine.class.getName(), "MouseOver action is performed on " + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, logClassName, "MouseOver action is not perform on " + locatorName);
			return false;
		}
		return flag;
	}

	public static String notepadFileRead() throws IOException {
		FileReader fr = new FileReader(
				"C:\\Users\\PPadeti\\Desktop\\RICOH_AutomationFrameWork\\RICOH_AutomationFrameWork\\Resources\\TechMailID.txt");
		BufferedReader br = new BufferedReader(fr);
		String x = "";
		while ((x = br.readLine()) != null) {
			System.out.println(x + "\n");
			break;

		}
		br.close();
		return x;
	}

	public String isFileDownloaded(String fileName) {
		String sFileName = "";
		String dirPath = System.getProperty("user.dir") + "\\Reports\\Downloads\\";
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files.length == 0 || files == null) {
			System.out.println("The directory is empty");

		} else {
			for (File listFile : files) {
				if (listFile.getName().contains(fileName)) {
					sFileName = listFile.getName();
					System.out.println(fileName + " is present");
					break;
				}

			}
		}
		return sFileName;
	}

	public void isFileDeleted(String fileName) {
		String dirPath = System.getProperty("user.dir") + "\\Reports\\Downloads\\" + fileName;
		File dir = new File(dirPath);
		dir.delete();
		System.out.println("Required file was deleted and file name is :----->  " + fileName);
	}

	public String readExcel(String fileName, String sheetName) throws IOException {
		String sExcelData = "";
		String sFileInfo = System.getProperty("user.dir") + "\\Reports\\Downloads\\" + fileName;
		File file = new File(sFileInfo);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook MSWbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			MSWbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			MSWbook = new HSSFWorkbook(inputStream);
		}
		Sheet MSWsheet = MSWbook.getSheet(sheetName);
		int val = 0;
		int rowCount = MSWsheet.getLastRowNum() - MSWsheet.getFirstRowNum();
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = MSWsheet.getRow(i);
			if (row == null) {
				val = val + 1;
				if (val == 1) {
					i = i + 1;
					row = MSWsheet.getRow(i);
				}
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					sExcelData = sExcelData + ";" + row.getCell(j).getStringCellValue();
				} else if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					sExcelData = sExcelData + ";" + row.getCell(j).getNumericCellValue();
				}
			}
			System.out.println();
		}
		return sExcelData;
	}
	/*
	 * public String CSVConvertIntoExcel(String fileName){ String sXLFileName="";
	 * String sCSVCLData=""; try { String[] sFileName=fileName.split("\\.");
	 * if(sFileName[1].equals("csv")){ sXLFileName=fileName.replace(".csv",".xlsx");
	 * } System.out.println("FileName is:------"+sXLFileName); CsvLoadOptions
	 * loadOptions = new CsvLoadOptions(); loadOptions.setSeparator(','); Converter
	 * converter = new
	 * Converter(System.getProperty("user.dir")+"\\Reports\\Downloads\\"+fileName,
	 * loadOptions); SpreadsheetConvertOptions options = new
	 * SpreadsheetConvertOptions(); options.setFormat(SpreadsheetFileType.Xlsx);
	 * converter.convert(System.getProperty("user.dir")+"\\Reports\\Downloads\\"+
	 * sXLFileName, options); sCSVCLData=readExcel(System.getProperty("user.dir")+
	 * "\\Reports\\Downloads\\"+sXLFileName,"Sheet1");
	 * System.out.println(sCSVCLData); } catch (Exception e) { e.printStackTrace();
	 * 
	 * } isFileDeleted(sXLFileName); return sCSVCLData; }
	 */

	public String pdfFileReader(String sFile) throws IOException {
		String sPdfData = "";
		// Loading an existing document
		String sFileInfo = System.getProperty("user.dir") + "\\Reports\\Downloads\\" + sFile;
		File file = new File(sFileInfo);
		PDDocument document = PDDocument.load(file);
		// Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();
		// Retrieving text from PDF document
		String text = pdfStripper.getText(document);
		System.out.println(text);
		sPdfData = text;
		// Closing the document
		document.close();
		return sPdfData;
	}

	/**
	 *
	 * @param reportDirectory - Path to the directory where the reports are download
	 *                        , i,e with ref to the project location
	 * @param reportName      - Name of the report file
	 * @param query           - to get the result set from the csv
	 * @return
	 */
	@SuppressWarnings("finally")
	public static ResultSet getReportData(String reportDirectory, String reportName, String query) {
		ResultSet results = null;
		try {
			Class.forName("org.relique.jdbc.csv.CsvDriver");
			Properties props = new Properties();
			props.put("separator", ",");
			Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + reportDirectory, props);
			Statement stmt = conn.createStatement();
			results = stmt.executeQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return results;
		}
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ie) {
			System.out.println("Exception to sleep for " + time + " msec");
		}
	}

	public boolean waitForElementPresent(By by, String locator, int secs) {
		boolean status = false;

		try {

			WebDriverWait wait = new WebDriverWait(this.driver, secs);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			/*
			 * ((JavascriptExecutor) Driver).executeScript(
			 * "arguments[0].scrollIntoView();", by);
			 */
			//long sec = secs.getSeconds();
			for (int i = 0; i < secs / 2; i++) {
				List<WebElement> elements = this.driver.findElements(by);
				if (elements.size() > 0) {
					status = true;
					return status;

				} else {
					this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
			}

		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for element " + locator + " to be present");
			return status;
		}

		return status;

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
		case "css":
			return By.cssSelector(suffix);
		case "link":
			return By.linkText(suffix);
		case "partLink":
			return By.partialLinkText(suffix);
		case "id":
			return By.id(suffix);
		case "name":
			return By.name(suffix);
		case "tag":
			return By.tagName(suffix);
		case "className":
			return By.className(suffix);
		default:
			return null;
		}
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
			e.printStackTrace();
			if (screenShotOnFail)
				;
			throw e;
		}
	}

	/**
	 * Takes screenshot with default name
	 *
	 * @return url (or path for local machine) to saved screenshot
	 */
	/*
	 * protected String takeScreenshot() { return
	 * takeScreenshot(randomStringTime(23)); }
	 * 
	 *//**
		 * Takes screenshot of current page. Screenshots are placed in /screenshots
		 * directory of project's root
		 *
		 * @param fileName name to give for screenshot.
		 * @return url (or path for local machine) to saved screenshot
		 */
	/*
	*//*
		 * protected String takeScreenshot(String fileName) { try { //Capture Screenshot
		 * TakesScreenshot driver = !getGrid() || getMobile() ? (TakesScreenshot) Driver
		 * : (TakesScreenshot) new Augmenter().augment(Driver);
		 * 
		 * File tempFile = driver.getScreenshotAs(OutputType.FILE);
		 * saveAllureScreenshot(driver.getScreenshotAs(OutputType.BYTES)); //Name and
		 * save file String path = getRelativeScreenshotPath(fileName); File
		 * screenShotFile = new File(path); FileUtils.copyFile(tempFile,
		 * screenShotFile);
		 * 
		 * String strace = ""; for (StackTraceElement el :
		 * Thread.currentThread().getStackTrace()) strace += el.toString() +
		 * System.lineSeparator() ; log.debug(strace);
		 * 
		 * 
		 * //Create link to screenshot String url = getScreenshotUrl(screenShotFile,
		 * fileName); log.info("SCREENSHOT: " + url); return url;
		 * 
		 * } catch (Exception e) { //Suppress exception no need to fail test
		 * log.warn("takeScreenshot failed:", e); return e.getMessage(); } }
		 * 
		 * 
		 *//**//**
				 * Composes url to screenshot that will be shown in build log In case of local
				 * build - result is screenshot local path In case of jenkins build - points to
				 * jenkins build artifacts
				 * <p>
				 * NOTE: url will become valid only after build is complete and results are
				 * archived
				 *
				 * @param screenShotFile
				 * @param fileName       name to give for screenshot.
				 * @return url (or path for local machine) to saved screenshot
				 *//**/
	/*
	 * String getScreenshotUrl(File screenShotFile, String fileName) throws
	 * IOException { String url = null; if (isLocal()) { url =
	 * screenShotFile.getCanonicalPath(); } else { url = System.getenv("BUILD_URL")
	 * + "/artifact/test_automation/" + getRelativeScreenshotPath(fileName); }
	 * 
	 * return url; }
	 * 
	 *//**/

	/**
	 * Composes relative path to screenshot file
	 *
	 * @param
	 * @return path
	 *//**/
	/*
	 * String getRelativeScreenshotPath(String fileName) { String path =
	 * "screenshots/" + getScreenShotsDir() + "/" +
	 * Thread.currentThread().getName().replaceAll("\\(|\\)", "") + "/" + fileName +
	 * ".png"; return path; }
	 */
	public boolean waitForElementPresent(String locator, int secs) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			WebElement reqEle = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));
			status = (reqEle != null) ? true : false;	
			
			LogManager.logInfo(logClassName, "Successfully checked element present " + locator);
			
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to check element present " + locator);
			status = false;
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for element " + locator + " to be present");
			e.printStackTrace();
			status = false;
		}	
		return status;
	}

	public boolean waitForWebElementPresent(WebElement element, int secs) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			WebElement reqEle = wait.until(ExpectedConditions.visibilityOf(element));
			status = (reqEle != null) ? true : false;
			// this.reporter.SuccessReport("Checked element present",
			// "Successfully checked element present " + locator, Driver);
			// this.extentMngr.logResult(extentTC, Status.PASS, "Successfully
			// checked element present " + locator);
			LogManager.logInfo(logClassName, "Successfully checked WebElement present " + element);
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to check WebElement present " + element);
			status = false;
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for WebElement " + element + " to be present");
			e.printStackTrace();
			status = false;
		}
		//testObj.reportStatus(status, "Successfull wait till WebElement is present "+element, "Failed to wait for WebElement "+element);
		return status;
	}

	public boolean waitForAllWebElements(List<WebElement> elements, int secs) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			List<WebElement> reqEle = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			status = (reqEle != null) ? true : false;
			// this.reporter.SuccessReport("Checked element present",
			// "Successfully checked element present " + locator, Driver);
			// this.extentMngr.logResult(extentTC, Status.PASS, "Successfully
			// checked element present " + locator);
			LogManager.logInfo(logClassName, "Successfully checked all WebElements present " + elements);
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to check all WebElements present " + elements);
			status = false;
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for all WebElements " + elements + " to be present");
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean waitForTitlePresent(String sExpectedTitle, int secs) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			Boolean res = wait.until(ExpectedConditions.titleContains(sExpectedTitle));
			status = (res != null) ? true : false;
			LogManager.logInfo(logClassName, "Successful wait till title " + sExpectedTitle + " is present");
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to check title present " + sExpectedTitle);
			status = false;
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for title " + sExpectedTitle + " to be present");
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean waitForElementNotPresent(String locator, int secs) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator(locator)));
			status = true;
			// this.reporter.SuccessReport("Checked element absence",
			// "Successfully checked element absence " + locator, Driver);
			// this.extentMngr.logResult(extentTC, Status.PASS, "Successfully
			// checked element absence " + locator);
			LogManager.logInfo(logClassName, "Successfully checked element absence " + locator);
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to check element absence " + locator);
		} catch (Exception e) {
			// this.reporter.failureReport("Checked element absence", "Failed -
			// " + locator + "present", Driver);
			// this.extentMngr.logResult(extentTC, Status.FAIL, "Failed to
			// checked element absence " + locator);
			LogManager.logException(e, logClassName, "Failed to checked element absence " + locator);
			e.printStackTrace();
		}
		return status;
	}

	public boolean waitForElementToBeClickable(String locator, int secs) {

		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
			flag = true;
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to wait for element " + locator + " to be clickable");
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for element " + locator + " to be clickable");
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean waitForElementToBeClickable(WebElement locator, int secs) {

		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			flag = true;
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to wait for element " + locator + " to be clickable");
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for element " + locator + " to be clickable");
			e.printStackTrace();
		}
		return flag;
	}
	

	public boolean waitForElementToHaveSpecifiedText(WebElement locator, String text, int secs) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.textToBePresentInElementValue(locator,text));
			flag = true;
		} catch (TimeoutException te) {
			LogManager.logInfo(logClassName, "Timeout to wait for element " + locator + " to be clickable");
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Exception to wait for element " + locator + " to be clickable");
			e.printStackTrace();
		}
		return flag;
	}
	
	public void waitForElementToBeClickable(String locator) {
	//	Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		waitForElementToBeClickable(locator, gblConstants.wdWaitTimeout);
	}

	/**
	 * Soft wait for visibility of element with default timeout
	 *
	 * @param locator locator of element to wait for
	 * @return true if element is present and visible / false otherwise
	 */
	protected boolean waitForElementPresent(final String locator) {
		//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		return waitForElementPresent(locator, gblConstants.wdWaitTimeout);
	}

	/**
	 * Wait until element is invisible/not present on the page with default timeout
	 *
	 * @param locator locator to element
	 */
	protected void waitForElementNotPresent(final String locator) {
		//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		waitForElementNotPresent(locator, gblConstants.wdWaitTimeout);
	}

	/**
	 * Wait for invisibility of specific object on page
	 *
	 * @param locator of object that we wait for invisibility
	 */
	protected void waitForInvisibility(final String locator) {
		//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		WebDriverWait wait = new WebDriverWait(driver, gblConstants.wdWaitTimeout);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator(locator)));
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to wait for element invisibility");
			// log.info("Try to wait little more (wait for invisibility)");
		}
	}

	/**
	 * Verifies whether element is present and displayed
	 *
	 * @param locator locator for element to verify
	 * @return true if present; false otherwise
	 */
	protected boolean isElementPresent(final String locator) {
		try {
			return isElementPresent(getElement(locator, false));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @Purpose To get Element presence
	 * @Constraints
	 * @Input String : LocatorName and boolean presence
	 * @Output boolean : Returns true | false
	 */
	public boolean isElementPresent(String locatorName, boolean expected) {
		boolean status = false;
		try {
			WebElement ele = getElement(locatorName);
			status = (ele.isDisplayed()) ? true : false;
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

	/**
	 * Binding to click the webElement
	 *
	 * @param we webElement to click
	 */
	protected void click(final WebElement we) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
			we.click();
		} catch (Exception e) {
			// log.error("Failed to click:", e);
			// takeScreenshot();
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to click button");
			throw e;
		}
	}

	/**
	 * Binding to click Xpath, CSS, Link, Partial link element
	 *
	 * @param locator locator of the element in format xpath=locator; css=locator
	 *                etc
	 */
	protected void click(final String locator) {
		click(getElement(locator));
	}

	/**
	 * Verifies whether element is displayed
	 *
	 * @param we webelement to verify
	 * @return true if present; false otherwise
	 */

	protected boolean isElementPresent(final WebElement we) {

		try {

			return we.isDisplayed();

		} catch (Exception e) {

			return false;

		}

	}

	public void textFieldClear(String locatorName) {

		try {
			WebElement ele = getElement(locatorName);
			ele.clear();
			// we.sendKeys(testdata);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to clear text");
			throw e;
		}
	}

	public void type(final WebElement we, String testdata) {

		try {
			highlight(we);			
			// ((JavascriptExecutor)
			// Driver).executeScript("arguments[0].scrollIntoView(true);", we);
			we.clear();
			we.sendKeys(testdata);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to enter text");
			throw e;
		}
	}

	public boolean type(String locatorName, String testdata) {
		boolean blnRes = false;
		WebElement ele = null;
		try {
			highlight(locatorName);
			//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
			this.waitForElementPresent(locatorName, gblConstants.wdWaitTimeout);
			ele = getElement(locatorName);
			type(ele, testdata);
			Thread.sleep(1000);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to enter text " + testdata + " in " + locatorName);
		}
		if (ele.getAttribute("value").trim().equals(testdata.trim())) {
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Successfully entered text " + testdata + " in element " + locatorName);
			blnRes = true;
		} else {
			LogManager.logWarining(WebActionEngine.class.getName(),
					"Unable to enter text " + testdata + " in element " + locatorName);
			blnRes = false;
		}
		return blnRes;
	}

	public boolean typeValue(String locatorName, String testdata) {
		boolean blnRes = true;
		//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		this.waitForElementPresent(locatorName, gblConstants.wdWaitTimeout);
		WebElement ele = getElement(locatorName);
		type(ele, testdata);
		return blnRes;
	}

	public void type(String locatorName, String testdata, boolean clear, boolean keyClear) {

		typeKeys(getElement(locatorName), testdata, clear, keyClear);
	}

	public void clickEnter(String locatorName) {
		try {
			WebElement ele = getElement(locatorName);
			scrollElementIntoView(ele);
			ele.sendKeys(Keys.ENTER);
			LogManager.logInfo(logClassName, this.msgClickSuccess + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to perform click  and enter");
			e.printStackTrace();
		}
	}

	public void clickTab(String locatorName) {
		try {
			WebElement ele = getElement(locatorName);
			//scrollElementIntoView(ele);
			ele.sendKeys(Keys.TAB);
			LogManager.logInfo(logClassName, this.msgClickSuccess + locatorName);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to perform click  and enter");
			e.printStackTrace();
		}
	}
	/**
	 * @param locatorName: webelement having multiple elements
	 * @return list of webelements
	 * @throws Throwable
	 */
	public List<WebElement> getAllElements(String locatorName) {

		List<WebElement> elements = new ArrayList<WebElement>();
		try {
			elements = driver.findElements(byLocator(locatorName));
			return elements;

		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
		return elements;
	}

	/**
	 * Binding to check Checkbox
	 *
	 * @param we webElement of checkbox to check
	 */
	protected void check(final WebElement we) {
		if (!we.isSelected()) {
			we.click();
		}
	}

	/**
	 * Binding to check checkbox
	 *
	 * @param locator locator of checkbox to check
	 */
	protected void check(final String locator) {
		check(getElement(locator));
	}

	/**
	 * Binding to clear text field and enter text
	 *
	 * @param we       webElement to type to
	 * @param value    value to type into the field
	 * @param clear    true to clear the field first; false to enter text without
	 *                 clearing field
	 * @param keyClear true to clean using keyboard shortcut; false without clean;
	 * @return webElement with edited text
	 */
	protected WebElement typeKeys(final WebElement we, final String value, final boolean clear,
			final boolean keyClear) {
		if (clear)
			we.clear();
		if (keyClear) {
			we.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			we.sendKeys(Keys.chord(Keys.DELETE));
		}
		we.sendKeys(value);
		return we;
	}

	public String getVisibleText(final String locator) {
		String text = "";
		try {
			WebElement ele = getElement(locator);
			text = ele.getText();
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Visisble Text from locator " + locator + " is " + text);
			return text;
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get visibile text");
			e.printStackTrace();
		}
		return "";
	}

	public String getAttributeValue(final String locator, final String attribute) {
		String value = "";
		WebElement we = getElement(locator);
		value = we.getAttribute(attribute);
		return value;
	}

	public String getAttributeValue(WebElement we, final String attribute) {
		String value = "";
		try {
			value = we.getAttribute(attribute);
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Able to get Attribute " + attribute + " value " + value + " for WebElement " + we);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get attribute value " + attribute + " for WebElement " + we);
			e.printStackTrace();
		}
		return value;
	}

	public String getTitle() {
		String title = "";
		try {
			title = driver.getTitle();
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Able to get Title " + title);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get Title " + title);
			e.printStackTrace();
		}
		return title;
	}
	
	public int GetRowCount(String locator) {
		int sRowVal = 0;
		List<WebElement> allElements = driver.findElements(byLocator(locator));
		return sRowVal = allElements.size();
	}

	public void TypeInFields(String value) {
		List<WebElement> allElements = driver
				.findElements(By.xpath("//*[@class='cp_field_text_component form-item' and @type='text']"));
		// int i = 0;
		for (int i = 0; i < allElements.size(); i++) {
			allElements.get(i).sendKeys(value);
		}

		/*
		 * for (WebElement Element : allElements) { i = i +1; Element.sendKeys(value); }
		 */
	}

	/**
	 * Binding to select item in dropdown which needs to be clicked for edit mode.
	 * Fills actual value and presses "TAB" to submit, otherwise value could be not
	 * saved
	 *
	 * @param clickE        webElement of the field to click
	 * @param selectLocator locator of the dropdown
	 * @param value         value to select
	 */
	protected void clickToSelect(final WebElement clickE, final String selectLocator, final String value) {
		click(clickE);
		selectDropDown(getElement(selectLocator), value);
		getElement(selectLocator).sendKeys(Keys.TAB);
	}

	/**
	 * Binding to select item in dropdown by value
	 *
	 * @param we     WebElement of the dropdown
	 * @param option value to select in the dropdown
	 */
	protected void selectDropDown(final WebElement we, final String option) {

		try {
			getSelect(we).selectByVisibleText(option);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to select option from dropdown");
			// takeScreenshot();
			throw e;
		}
	}

	protected void selectDropDown(By locator, String option, String locatorName) {

		try {
			//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
			WebDriverWait wait = new WebDriverWait(driver, gblConstants.wdWaitTimeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			new Select(driver.findElement(locator)).selectByVisibleText(option);
			// this.reporter.SuccessReport("Drop Down Selection " + locatorName,
			// "Successfully Selected " + option + " in drop down " +
			// locatorName, this.Driver);
			// this.extentMngr.logResult(extentTC, Status.PASS, "Successfully
			// Selected " + option + " in drop down " + locatorName);
			LogManager.logInfo(logClassName, "Successfully Selected " + option + " in drop down " + locatorName);
			// logger.log(Status.PASS, "Successfully Selected " + option + " in
			// drop down " + locatorName,
			// logger.addScreenCapture(capture(Driver, "DropdownPass")));
		} catch (Exception e) {
			// this.reporter.failureReport("Drop Down Selection " + locatorName,
			// "Failed to select value" + option + " in drop down " +
			// locatorName, this.Driver);
			// this.extentMngr.logResult(extentTC, Status.FAIL, "Failed to
			// select value" + option + " in drop down " + locatorName);
			LogManager.logException(e, logClassName,
					"Failed to select value" + option + " in drop down " + locatorName);
			throw e;
		}
	}

	protected Select getSelect(final WebElement we) {
		return new Select(we);
	}

	public void scrollElementIntoView(String locator) {
		try {
		highlight(locator);		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
		Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollElementIntoView(WebElement elementToScroll) {
		try {
			highlight(elementToScroll);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Able to scroll into view to webElement " + elementToScroll);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to scroll into view to webElement " + elementToScroll);
			e.printStackTrace();
		}
	}

	public String timeStamp() {
		/*
		 * java.util.Date today = new java.util.Date(); return new
		 * java.sql.Timestamp(today.getTime()).toString();
		 */
		Date now = new Date();
		String time = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH).format(now);
		System.out.println(time);
		return time;
	}

	public void typeUsingJavaScript(String locator, String data) {
		try {
			WebElement element = this.driver.findElement(byLocator(locator));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='" + data + "';", element);
			LogManager.logInfo(WebActionEngine.class.getName(),"Able to enter value "+data+" in webElement " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to enter value "+data+" in webElement " + locator);
		}
	}
	
	public void typeUsingJavaScript(WebElement element, String data) {
		try {			
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='" + data + "';", element);
			LogManager.logInfo(WebActionEngine.class.getName(),"Able to enter value "+data+" in webElement " + element);
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to enter value "+data+" in webElement " + element);
		}
	}

	public void typeUsingJavaScriptInHTMLInnerText(String locator, String data) {

		WebElement element = this.driver.findElement(byLocator(locator));
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		executor.executeScript("arguments[0].innerHTML = +data+'", element);

	}

	public void selectUsingJavaScript(String locator, String data) {
		try {
			WebElement element = this.driver.findElement(byLocator(locator));
			scrollElementIntoView(locator);
			Thread.sleep(1000);
			highlight(element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(
					"var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }",
					element, data);
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Successfully selected Value " + data + " from locator " + locator);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to select Value " + data + " from locator " + locator);
			e.printStackTrace();
		}
	}

	public String getHiddenTextValueByIDUsingJavaScript(String locatorID) {
		String value = "";
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			value = (String) executor.executeScript("return document.getElementById('" + locatorID + "').value");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Value from " + locatorID + " is " + value);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get value from id " + locatorID);
			e.printStackTrace();
		}
		return value;
	}

	public String getInnerTextByClassNameUsingJavaScript(String locatorID) {
		String value = "";
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			value = (String) executor
					.executeScript("return document.getElementsByClassName('" + locatorID + "')[0].innerText");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Inner Text value from " + locatorID + " is " + value);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get inner text value from id " + locatorID);
			e.printStackTrace();
		}
		return value;
	}
	
	public String getInnerTextUsingJavaScript(String locatorID) {
		String value = "";
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			value = (String) executor
					.executeScript("return document.querySelector('" + locatorID + "').innerText");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Inner Text value from " + locatorID + " is " + value);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get inner text value from id " + locatorID);
			e.printStackTrace();
		}
		return value;
	}
	
	public long getAllElementsJavaScript(String locatorID) {
		long value = 0;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			value = (long) executor.executeScript("return document.querySelectorAll('" + locatorID + "').length");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Total count from " + locatorID + " is " + value);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get Total count from id " + locatorID);
			e.printStackTrace();
		}
		return value;
	}
	
	public boolean clickUsingQuerySelectorJavaScript(String locatorID) {
		boolean res;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:yellow')");
			Thread.sleep(1000);			
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:null')");
			Thread.sleep(500);
			executor.executeScript("return document.querySelector('" + locatorID + "').click()");
			
			Thread.sleep(1000);
			res=true;
			LogManager.logInfo(WebActionEngine.class.getName(), "Clicked " + locatorID);
		} catch (Exception e) {
			res=false;
			LogManager.logException(e, WebActionEngine.class.getName(),"Unable to click id " + locatorID);
			e.printStackTrace();
		}	
		return res;
	}
	
	public boolean clearUsingQuerySelectorJavaScript(String locatorID) {
		boolean res;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:yellow')");
			Thread.sleep(1000);			
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:null')");
			Thread.sleep(500);
			executor.executeScript("return document.querySelector('" + locatorID + "').clear()");
			
			Thread.sleep(1000);
			res=true;
			LogManager.logInfo(WebActionEngine.class.getName(), "Cleared " + locatorID);
		} catch (Exception e) {
			res=false;
			LogManager.logException(e, WebActionEngine.class.getName(),"Unable to clear id " + locatorID);
			e.printStackTrace();
		}	
		return res;
	}
	
	public String getValueByIdUsingJavaScript(String locatorID) {
		String value = "";
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:yellow')");
			Thread.sleep(1000);			
			value = (String) executor.executeScript("return document.querySelector('"+locatorID+"').value");
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:null')");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Value from " + locatorID + " is " + value);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get value from id " + locatorID);
			e.printStackTrace();
		}
		return value;
	}

	public WebElement getWebElementUsingJavaScript(WebElement shadowRoot, String locatorID) {
		WebElement ele = null;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;				
			ele = (WebElement) executor.executeScript(shadowRoot+".querySelector('"+locatorID+"')");			
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Element fetched from " + locatorID);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get WebElement from " + locatorID);
			e.printStackTrace();
		}
		return ele;
	}
	public boolean setValueByIdUsingJavaScript(String locatorID,String sText) {
		boolean res;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:yellow')");
			Thread.sleep(1000);			
		    executor.executeScript("return document.querySelector('"+locatorID+"').value='"+sText+"'");
		    executor.executeScript("document.querySelector('" + locatorID + "').setAttribute('style', 'background:null')");
			Thread.sleep(1000);
			res=true;
			LogManager.logInfo(WebActionEngine.class.getName(), "Value Set in " + locatorID + " is " + sText);
		} catch (Exception e) {
			res=false;
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to get set text "+sText+" value in id " + locatorID);
			e.printStackTrace();
		}
	return res;
	}
	
	public void setTextValueByIDUsingJavaScript(String locatorID, String value) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.getElementById('" + locatorID + "').setAttribute('style', 'background:yellow')");
			Thread.sleep(1000);
			executor.executeScript("document.getElementById('" + locatorID + "').setAttribute('style', 'background:null')");
			executor.executeScript("return document.getElementById('" + locatorID + "').setAttribute('value','" + value + "')");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Value " + value + " entered in " + locatorID);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to enter value " + value + " in " + locatorID);
			e.printStackTrace();
		}
	}

	public void setTextAreaTagByIDUsingJavaScript(String locatorID, String value) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.getElementById('" + locatorID + "').setAttribute('style', 'background:yellow')");
			Thread.sleep(1000);
			executor.executeScript("document.getElementById('" + locatorID + "').setAttribute('style', 'background:null')");
			executor.executeScript("return document.getElementById('" + locatorID + "').textContent = '" + value + "'");
			Thread.sleep(1000);
			LogManager.logInfo(WebActionEngine.class.getName(), "Value " + value + " entered in " + locatorID);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to enter value " + value + " in " + locatorID);
			e.printStackTrace();
		}
	}

	// public void assertTrue(ExtentTest extentTC, boolean condition, String
	// message, String strStepName, String failedMessage, String passMessage)
	// throws Throwable {
	// if (!condition) {
	// //this.reporter.failureReport(strStepName, failedMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.FAIL, failedMessage);
	// this.extentMngr.attachScreenshot(extentTC, Status.FAIL,
	// captureScreenShot("assertTrue_Fail"));
	// LogManager.logError(logClassName, "Assertion failure with message " +
	// failedMessage);
	// Assert.assertTrue(condition, message);
	// } else {
	// this.extentMngr.logResult(extentTC, Status.PASS, failedMessage);
	// LogManager.logInfo(logClassName, "Assertion pass with message " +
	// passMessage);
	// //this.reporter.SuccessReport(strStepName, passMessage, Driver);
	// }
	// }
	//
	// public void assertFalse(ExtentTest extentTC, boolean condition, String
	// message, String strStepName, String failedMessage, String passMessage)
	// throws Throwable {
	// if (condition) {
	// //this.reporter.failureReport(strStepName, failedMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.FAIL, failedMessage);
	// this.extentMngr.attachScreenshot(extentTC, Status.FAIL,
	// captureScreenShot("assertFalse_Fail"));
	// LogManager.logError(logClassName, "Assertion failure with message " +
	// failedMessage);
	// Assert.assertFalse(condition, message);
	// } else {
	// //this.reporter.SuccessReport(strStepName, passMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.PASS, passMessage);
	// LogManager.logInfo(logClassName, "Assertion pass with message " +
	// passMessage);
	// }
	// }
	//
	// public void assertEqual(ExtentTest extentTC, String actual, String
	// expected, String message, String strStepName, String failedMessage,
	// String passMessage) throws Throwable {
	// if (!actual.equals(expected)) {
	// //this.reporter.failureReport(strStepName, failedMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.FAIL, failedMessage);
	// this.extentMngr.attachScreenshot(extentTC, Status.FAIL,
	// captureScreenShot("assertEqual_Fail"));
	// LogManager.logError(logClassName, "Assertion failure with message " +
	// failedMessage);
	// Assert.assertEquals(actual, expected, message);
	// } else {
	// //this.reporter.SuccessReport(strStepName, passMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.PASS, passMessage);
	// LogManager.logInfo(logClassName, "Assertion pass with message " +
	// passMessage);
	// }
	// }
	//
	// public void assertEqual(ExtentTest extentTC, int actual, int expected,
	// String message, String strStepName, String failedMessage, String
	// passMessage) throws Throwable {
	// if (actual == expected) {
	// //this.reporter.failureReport(strStepName, failedMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.FAIL, failedMessage);
	// this.extentMngr.attachScreenshot(extentTC, Status.FAIL,
	// captureScreenShot("assertEqual_Fail"));
	// LogManager.logError(logClassName, "Assertion failure with message " +
	// failedMessage);
	// Assert.assertEquals(actual, expected, message);
	// } else {
	// //this.reporter.SuccessReport(strStepName, passMessage, Driver);
	// this.extentMngr.logResult(extentTC, Status.PASS, passMessage);
	// LogManager.logInfo(logClassName, "Assertion pass with message " +
	// passMessage);
	// }
	// }

	public void highlight(String locator) {
		WebElement elem = getElement(locator);
		String bgcolor = elem.getCssValue("backgroundColor");
		//String borderColor = elem.getCssValue("border-color");
		try {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background:yellow')", elem);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='"+bgcolor+"'", elem);
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border-color='"+borderColor+"'", elem);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid yellow'", elem);
				//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid red'", elem);
				//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid blue'", elem);
				//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px solid green'", elem);	
	}

	public void highlight(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
	//	String borderColor = element.getCssValue("border-color");border:2px solid blue; 
		try {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background:yellow')", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='"+bgcolor+"'", element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border-color='"+borderColor+"'", element);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid yellow'", element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid red'", element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid blue'", element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px solid green'", element);
	}
	public void waitForGivenNumberOfWindowsToOpen(int expectedWindowsCount, int timeUnitsInSec)
			throws InterruptedException {
		int actualWindowsCount = driver.getWindowHandles().size();
		for (int i = 0; i < timeUnitsInSec; i++) {
			Thread.sleep(1000);
			if (expectedWindowsCount == actualWindowsCount)
				break;
		}
	}

	public String captureScreenShot(String strReportsPath, String strSSName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) this.driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String epoch2 = String.valueOf(System.currentTimeMillis() / 1000);
			// String strSSRelativePath = "/extent_reports/screenshots/" +
			// screenShotName + "_" + epoch2 + "_" + ".png";
			// String strSSRelativePath = "." + File.separator +
			// gblConstants.screenshotDirName +
			// File.separator + strSSName + "_" + epoch2 + "_" + ".png";
			if (strSSName.length() > 60) {
				strSSName = strSSName.substring(0, 60) + "_" + epoch2 + ".png";
			}
			strSSName = strSSName + "_" + epoch2 + ".png";
			String dest = strReportsPath + File.separator + gblConstants.screenshotDirName + File.separator + strSSName;
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);

			return strSSName;
		} catch (IOException io) {
			LogManager.logException(io, WebActionEngine.class.getName(), "Exception to capture screenshot");
			return "";
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Exception to capture screenshot");
			return "";
		}
	}

	public boolean switchToWindowContainingTitle(String expTitle, boolean refresh) {
		boolean blnRes = false;
		// Work around in OMS - Monitor for consistency
		if (refresh) {
			driver.manage().window().maximize();
		}
		System.out.println(driver.getWindowHandles().size());
		Iterator<String> wndHandles = driver.getWindowHandles().iterator();
		int ii = 0;
		try {
			while (wndHandles.hasNext()) {
				String wndHandle = wndHandles.next();
				String currWndTitle = "";
				this.driver.switchTo().window(wndHandle);
				do {
					Thread.sleep(2000);
					currWndTitle = this.driver.getTitle().trim();
				} while (currWndTitle != "");
				ii++;
				LogManager.logInfo(WebActionEngine.class.getName(), "Window Title -" + ii + "->  " + currWndTitle);
				if (currWndTitle.toLowerCase().contains(expTitle.trim().toLowerCase())) {
					blnRes = true;
					LogManager.logInfo(WebActionEngine.class.getName(),
							"Switching to window containing title " + expTitle);
					break;
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to Switch to window containing title " + expTitle);
			e.printStackTrace();
		}
		if (blnRes == false) {
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Unable to Switch to window containing title " + expTitle);
		}
		return blnRes;
	}

	public boolean switchToWindowContainingNoTitle(boolean refresh) {
		boolean blnRes = false;
		// Work around in OMS - Monitor for consistency
		if (refresh) {
			driver.manage().window().maximize();
		}
		System.out.println(driver.getWindowHandles().size());
		Iterator<String> wndHandles = driver.getWindowHandles().iterator();
		int ii = 0;
		while (wndHandles.hasNext()) {
			String wndHandle = wndHandles.next();
			this.driver.switchTo().window(wndHandle);
			String currWndTitle = this.driver.getTitle().trim();
			ii++;
			LogManager.logInfo(WebActionEngine.class.getName(), "Window Title -" + ii + "->  " + currWndTitle);
			if (currWndTitle.toLowerCase().trim().isEmpty()) {
				blnRes = true;
				LogManager.logInfo(WebActionEngine.class.getName(), "Switching to window having no title");
				break;
			}
		}
		if (blnRes == false) {
			LogManager.logInfo(WebActionEngine.class.getName(), "Unable to Switch to window no title ");
		}
		return blnRes;
	}

	public String switchToDefaultContent() {
		this.driver.switchTo().defaultContent();
		return this.driver.getTitle();
	}

	public boolean typeUsingKeyBoard(String locatorName, String testdata) {
		boolean blnRes = false;
		WebElement ele = getElement(locatorName);
		ele.sendKeys(Keys.SHIFT, testdata);
		LogManager.logInfo(WebActionEngine.class.getName(),
				"Successfully entered  " + testdata + " in element " + locatorName);

		if (ele.getAttribute("value").trim().equalsIgnoreCase(testdata.trim())) {
			blnRes = true;
		} else {
			blnRes = false;
		}
		return blnRes;
	}

	public String getDefaultContentWindowHandle() {
		String oldTab = driver.getWindowHandle();
		this.driver.switchTo().window(oldTab);
		return "Done";
	}

	public boolean moveFocusToElement(String locator) {
		boolean blnRes = false;
		try {
			WebElement element = this.driver.findElement(byLocator(locator));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].focus();", element);
			Thread.sleep(500);
			blnRes = true;
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to move focus element");
			blnRes = false;
		}
		return blnRes;
	}

	public boolean saveDownloadFiles(String fileName) {
		boolean blnRes = false;
		try {
			Thread.sleep(gblConstants.wdWaitTimeout); // wait for file to
														// download completely
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			StringSelection dwnldPath = new StringSelection(gblConstants.downloadsPath + File.separator + fileName);
			LogManager.logInfo(WebActionEngine.class.getName(), "Downloaded file is " + fileName);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(dwnldPath, dwnldPath);

			// copy paste the path in download window
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_TAB);

		} catch (Exception e) {
			blnRes = false;
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to perform download");
		}
		return blnRes;
	}

	public boolean isAlertPresent() {
		boolean foundAlert = false;
		//Duration durationInSecs = Duration.ofSeconds(5);
		WebDriverWait wait = new WebDriverWait(this.driver, 5 /* timeout in seconds */);
		try {
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				this.driver.switchTo().alert().accept();
				/*
				 * Robot r = new Robot(); r.keyPress(KeyEvent.VK_ENTER);
				 * r.keyRelease(KeyEvent.VK_ENTER);
				 */
				foundAlert = true;
			}
		} catch (Exception eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	public String getAlerttext() {
		String AlertText = null;
		//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		WebDriverWait wait = new WebDriverWait(this.driver, 5 /* timeout in seconds */);
		try {
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				AlertText = this.driver.switchTo().alert().getText();
				this.driver.switchTo().alert().accept();
			}
		} catch (Exception eTO) {

		}
		return AlertText;
	}

	public void ScrollWeb() {
		try {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ScrollUp() {
		try {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0, -500)");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void BrowserZoomSize(float Val) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = 'Val'");
	}

	public boolean isAlertPresented() {
		boolean foundAlert = false;
		//Duration durationInSecs = Duration.ofSeconds(gblConstants.wdWaitTimeout);
		WebDriverWait wait = new WebDriverWait(this.driver, gblConstants.wdWaitTimeout /* timeout in seconds */);
		try {
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {

				/*
				 * Robot r = new Robot(); r.keyPress(KeyEvent.VK_ENTER);
				 * r.keyRelease(KeyEvent.VK_ENTER);
				 */
				foundAlert = true;

			}
		} catch (Exception eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	public void ClickMultipleButtons() {
		List<WebElement> allElements = driver
				.findElements(By.xpath("//*[@id='cpf_button_increase' and @type='button']"));
		for (int i = 0; i < allElements.size(); i++) {
			allElements.get(i).click();
		}
	}

	public void enterInstallMulVals(String locatorName, String testdata) {
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 0; i < allElements.size(); i++) {
				allElements.get(i).clear();
				allElements.get(i).sendKeys(testdata);
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
	}

	public void ClickMulButtons(String locatorName) {
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 0; i < allElements.size(); i++) {
				allElements.get(i).click();
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
	}

	public void UncheckMultipleCheckBoxes(String locatorName) {
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 1; i < allElements.size(); i++) {
				if (allElements.get(i).isSelected() == true) {
					allElements.get(i).click();
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
	}

	public String gettabledata(String locatorName) {
		String item = null;
		String subInv = null;
		// int qty=0;
		// String qtyVal=Integer.toString(qty);
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 0; i < allElements.size(); i++) {
				String CaptrQty = allElements.get(i).getText();
				if (Integer.parseInt(CaptrQty) != 0) {
					item = driver.findElement(
							// By.xpath("(//*[@data-ofsc-entity='inventory']//tr/td[1]/div)[" + (i + 1) +
							// "]"))
							By.xpath("(//*[@data-ofsc-entity='inventory']//tr/td[1]/div/span[1])[" + (i + 2) + "]"))
							.getText();
					subInv = driver.findElement(
							// By.xpath("(//*[@data-ofsc-entity='inventory']//tr/td[7]/div)[" + (i + 1) +
							// "]"))
							By.xpath("(//*[@data-ofsc-entity='inventory']//tr/td[1]/div/span[11])[" + (i + 2) + "]"))
							.getText();
					subInv = "#" + subInv;
					break;
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
		return item + subInv;
	}

	public String gettext(String locator, String elementName) throws Exception {
		String sVal = null;
		try {
			WebElement ele = getElement(locator);
			// scrollElementIntoView(locator);
			if (ele.isDisplayed() && ele.isEnabled()) {
				sVal = ele.getText();

				LogManager.logInfo(logClassName, this.msgClickSuccess + elementName);
			}
		} catch (Exception e) {
			LogManager.logException(e, logClassName, this.msgClickFailure + elementName);
			throw e;
		}
		return sVal;
	}

	public void clickExcessPart(String locatorName) {
		// int qty=0;
		// String qtyVal=Integer.toString(qty);
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 0; i < allElements.size(); i++) {
				String CaptrQty = allElements.get(i).getText();
				if (Integer.parseInt(CaptrQty) != 0) {
					driver.findElement(
							// By.xpath("(//*[@data-ofsc-entity='inventory']//tr/td[1]/div)[" + (i + 1) +
							// "]"))
							By.xpath("(//*[@data-ofsc-entity='inventory']//tr/td[1]/div/span[1])[" + (i + 2) + "]"))
							.click();
					break;
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to click element");
			e.printStackTrace();
		}

	}

	public void ClickOnPendingASL(String locatorName) {
		String sActivityStatus;
		String sActivityType;
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 2; i < allElements.size(); i++) {
				sActivityStatus = driver.findElement(By.xpath(
						"//*[@class='toa-grid-body toa-layout-fullfit toa-view-control-control ui-droppable']//tr[" + i
								+ "]/td[7]"))
						.getText();
				sActivityType = driver.findElement(By.xpath(
						"//*[@class='toa-grid-body toa-layout-fullfit toa-view-control-control ui-droppable']//tr[" + i
								+ "]/td[16]"))
						.getText();
				Thread.sleep(2000);
				if (sActivityStatus.equalsIgnoreCase("Pending") & sActivityType.equalsIgnoreCase("ASL Request")) {
					driver.findElement(By.xpath(
							"//*[@class='toa-grid-body toa-layout-fullfit toa-view-control-control ui-droppable']//tr["
									+ i + "]/td[2]"))
							.click();
					break;
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to do validations");
			e.printStackTrace();
		}

	}

	public void enterMulVals(String locatorName, String testdata) {
		try {
			List<WebElement> allElements = driver.findElements(byLocator(locatorName));
			for (int i = 0; i < allElements.size(); i++) {
				allElements.get(i).clear();
				allElements.get(i).sendKeys(testdata + generateRandomString(3, Mode.NUMERIC));
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
	}

	public static enum Mode {

		/** The alpha. */
		ALPHA,
		/** The alphanumeric. */
		ALPHANUMERIC,
		/** The numeric. */
		NUMERIC
	}

	public static String generateRandomString(int length, Mode mode) {
		StringBuffer buffer = new StringBuffer();
		String characters = "";
		Boolean isNumericOnly = false;
		String automationTextPrefix = "";
		switch (mode) {
		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case NUMERIC:
			isNumericOnly = true;
			characters = "0123456789";
			break;
		}
		int charactersLength = characters.length();
		if (length >= 3 && !isNumericOnly) {
			length = length - 2;
			automationTextPrefix = "AT";
		}
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		if (isNumericOnly) {
			return buffer.toString();
		} else {
			return automationTextPrefix + buffer.toString();
		}
	}

	public boolean selectTheDestinationValue(String elemnt, String selectValue) {
		boolean flag = false;
		try {
			Thread.sleep(2000);
			List<WebElement> allElements = driver.findElements(byLocator(elemnt));
			if (allElements.size() < 2)
				return flag;
			else if (selectValue == null || selectValue.isEmpty()) {
				// clickusingJavaScript(driver.findElements(byLocator(elemnt)).get(allElements.size()-1));
				driver.findElements(byLocator(elemnt)).get(allElements.size() - 1).click();
				flag = true;

			} else {
				for (int i = 0; i < allElements.size(); i++) {
					String actualValue = allElements.get(i).getText().trim();
					if (actualValue.equalsIgnoreCase(selectValue.trim())) {
						// clickusingJavaScript(driver.findElements(byLocator(elemnt)).get(i));
						driver.findElements(byLocator(elemnt)).get(i).click();
						;
						flag = true;
						break;
					}
				}

			}
			Thread.sleep(1500);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to get elements");
			e.printStackTrace();
		}
		return flag;
	}
	
	public String getFirstSelectedValue(String locatorId) {
		String text="";
		try {
			WebElement ele = getElement(locatorId);
			Select select = new Select(ele);			
			WebElement option = select.getFirstSelectedOption();
			text = option.getText();
		}catch(Exception e) {
			System.out.println("hi");
		}
		return text;
	}

	public boolean clickusingJavaScript(WebElement element) {
		boolean blnRes = false;
		try {
			Thread.sleep(1000);
			highlight(element);
			Thread.sleep(gblConstants.defSleepTime);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			LogManager.logInfo(logClassName, "Used java script executor to click element " + element);
			blnRes = true;
		} catch (Exception e) {
			blnRes = false;
			LogManager.logError(logClassName,
					"Unable to click the webelement " + element.toString() + " using java script executor");
		}
	//	testObj.reportStatus(blnRes, "Clicked on "+element, "Unable to click on "+element);
		return blnRes;
	}

	public boolean ibpClickOnToggles(String sStatus, String sToogleCount, String locator) {
		boolean tgl = false;
		try {
			if (sStatus == "OFF") {
				Thread.sleep(2000);
				List<WebElement> allElements = driver.findElements(byLocator(sToogleCount));
				for (int i = 0; i < allElements.size(); i++) {
					allElements.get(i).click();
					Thread.sleep(2000);
					clickusingJavaScript(locator);
					tgl = true;
				}
			}
			if (sStatus == "ON") {
				Thread.sleep(1000);
				List<WebElement> allElements = driver.findElements(byLocator(sToogleCount));
				for (int i = 0; i < allElements.size(); i++) {
					allElements.get(i).click();
					Thread.sleep(2000);
					// clickusingJavaScript(locator);
					tgl = true;
				}
			}
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to click element");
			e.printStackTrace();
		}
		return tgl;
	}

	public boolean ibpClickOnTogglesForUser(String sTooglexpath, String listxpath, String locator, String sExpVal) {
		boolean tgl = false;
		try {
			Thread.sleep(1000);
			WebElement ele = getElement(sTooglexpath);
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.click();
				LogManager.logInfo(logClassName, this.msgClickSuccess);
			}
			Thread.sleep(2000);
			click(listxpath, "clicked on list");
			selectTheDestinationValue(locator, sExpVal);
			tgl = true;

		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to click element");
			e.printStackTrace();
		}
		return tgl;
	}

	public WebElement findShadowRootReference(String innerElSelector, WebElement shadowParent) {
		String getInnerEl = null;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		// Wait for page to completely load
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return executor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
		//	Duration durationInSecs = Duration.ofSeconds(30);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {

		}
		Object shadowRoot = null;
		// Convert RemoteWebElement to WebElement to use it as a parent Shadow element
		try {
			if (shadowParent == null) {
				getInnerEl = "return document.querySelector(\"" + innerElSelector + "\").shadowRoot;";
				shadowRoot =  executor.executeScript(getInnerEl);
			} else {
				getInnerEl = "return arguments[0].querySelector(\"" + innerElSelector + "\").shadowRoot;";
				shadowRoot =  executor.executeScript(getInnerEl, shadowParent);
			}
			
			  Map<String, Object> map = (Map<String, Object>) shadowRoot; 
			  String id = (String) ((Map<String, Object>)shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf"); 
			  RemoteWebElement shadowRootElement = new RemoteWebElement();
			  shadowRootElement.setParent((RemoteWebDriver)driver);
			  shadowRootElement.setId(id); 
			  if (shadowRootElement instanceof RemoteWebElement) {				  
			  try {			  
				  @SuppressWarnings("rawtypes") 
				  Class[] parameterTypes = new Class[] {
				  SearchContext.class,String.class, String.class }; 
				  Method m = shadowRootElement.getClass().getDeclaredMethod("setFoundBy", parameterTypes);
				  m.setAccessible(true); 
				  Object[] parameters = new Object[] { driver, "cssSelector", innerElSelector }; 
				  m.invoke(shadowRootElement, parameters); 
			  }catch (Exception fail) { 
                  throw new RuntimeException(fail); 
			  } 
			 } 
			Thread.sleep(1000);  
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Found Shadow root reference for " + innerElSelector + " having parent as " + shadowParent);
			//testObj.reportStatus(true, "Found Shadow root reference for " + innerElSelector + " having parent as " + shadowParent, "");
		    return  shadowRootElement;
		} catch (Exception e) {
		//	testObj.reportStatus(true,"","Haven't Found Shadow root reference for " + innerElSelector + " having parent as " + shadowParent);
			LogManager.logException(e, WebActionEngine.class.getName(), "Unable to find Shadow root reference for "
					+ innerElSelector + " having parent as " + shadowParent);
			e.printStackTrace();
			driver.close();
			System.exit(1);
		}
		return null;
	}

	public void clickElementShadowDOM(WebDriver driver, WebElement shadowReference, String innerElSelector) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String getInnerEl = "return arguments[0].querySelector(\"" + innerElSelector + "\").click();";
		// Wait for page to completely load
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			//Duration durationInSecs = Duration.ofSeconds(50);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {

		}
		try {
			if (shadowReference != null)
				executor.executeScript(getInnerEl, shadowReference);
			else
				executor.executeScript(getInnerEl);
			LogManager.logInfo(WebActionEngine.class.getName(),
					"Clicked on Shadow DOM Element " + getInnerEl + " having parent as " + shadowReference);
			System.out.println("clicked " + getInnerEl);
			Thread.sleep(1000);
		} catch (Exception e) {
			LogManager.logException(e, WebActionEngine.class.getName(),
					"Unable to click on Shadow DOM Element " + getInnerEl + " having parent as " + shadowReference);
			e.printStackTrace();
		}

	}

}
