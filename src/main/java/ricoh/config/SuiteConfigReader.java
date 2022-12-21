package ricoh.config;

import java.io.File;
import java.util.List;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPath;

import ricoh.core.fileutils.JsonReader;


public class SuiteConfigReader {

	Object jssuiteConfig = null;
	String currTestApp = "";
	String currTestEnv = "";
	String currTestDevice = "";
	
	/*
	 * Purpose:To load and read SuiteConfig.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */
	public SuiteConfigReader() {
		// TODO Auto-generated constructor stub		
		String suiteConfig = JsonReader.readJSONFile(new File(System.getProperty("user.dir")+ File.separator + gblConstants.suiteConfigName));
		jssuiteConfig = Configuration.defaultConfiguration().jsonProvider().parse(suiteConfig);
	}

	public void setAppUnderTest(String appName) {
		currTestApp = appName.trim();
	}

	public void setEnvUnderTest(String envName) {
		currTestEnv = envName.trim();
	}

	public void setMobileUnderTest(String devName) {
		currTestDevice = devName.trim();
	}

	/*
	 * Purpose:To get page tile value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */
	public String getTestAppPageTitle(String appName) {
		String respValue = (appName.length() > 0) ? 
		evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].pageTitle") :
		evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].pageTitle");
		return respValue;
	}

	

	/*
	 * Purpose:To get web app url value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */
	public String getTestAppUrl(String appName, String envName) {
		
		String respValue = null;
		try {
			if (appName.length() > 0 && envName.length() > 0 ) {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ envName.trim() +"')].url");
				respValue = resp.get(0).trim();					
			}else {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ currTestEnv.trim() +"')].url");
				respValue = resp.get(0).trim();					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;
	}

	

	/*
	 * Purpose:To get web app version number from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */
	public String getTestAppVersion(String appName, String envName) {
		
		String respValue = null;
		
		try {
			if(appName.length() > 0 && envName.length() > 0) {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ envName.trim() +"')].version");
				respValue = resp.get(0).trim();					
			}else {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ currTestEnv.trim() +"')].version");
				respValue = resp.get(0).trim();					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;
	}

	/*
	 * Purpose:To get web app login user value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */	
	public String getTestAppLoginUser(String appName, String envName) {

		String respValue = null;
		try {
			if (appName.length() > 0 && envName.length() > 0) {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ envName.trim() +"')].username");
				respValue = resp.get(0).trim();					
			}else {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ currTestEnv.trim() +"')].username");
				respValue = resp.get(0).trim();					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;
	}

	/*
	 * Purpose:To get web app login password value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */		
	public String getTestAppLoginPwd(String appName, String envName) {

		String respValue = null;
		try {
			if (appName.length() > 0 && envName.length() > 0) {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ envName.trim() +"')].password");
				respValue = resp.get(0).trim();			
			}else {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ currTestEnv.trim() +"')].password");
				respValue = resp.get(0).trim();			
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;				
	}



	/*
	 * Purpose:To get web app login location from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */		

	public String getTestAppLoginLoc(String appName, String envName) {

		String respValue = null;

		try {
			if (appName.length() > 0 && envName.length() > 0) {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ envName.trim() +"')].location");
				respValue = resp.get(0).trim();			
			}else {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ currTestEnv.trim() +"')].location");
				respValue = resp.get(0).trim();			
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;		
	}

		

	/*
	 * Purpose:To get API server endpoint value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */	
	public String getTestAPIServerEndpoint(String appName, String envName) {

		String respValue = null;
		try {
			if (appName.length() > 0 && envName.length() > 0) {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ envName.trim() +"')].apiServerUrl");		
				respValue = resp.get(0).trim();			
			}else {
				List<List<String>> env = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].environments");
				List<String> resp = JsonPath.read(env.get(0).toString(), "$[?(@.name == '"+ currTestEnv.trim() +"')].apiServerUrl");		
				respValue = resp.get(0).trim();			
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}



	/*
	 * Purpose:To get mobile device name of given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */	
	public String getMobileDeviceName(String appName, String devName) {		

		String respValue = null;
		try {
			if (appName.length() > 0 && devName.length() > 0) {			
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['device.name']");
				respValue = resp.get(0).trim();					
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['device.name']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}	



	/*
	 * Purpose:To get mobile device os type of given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */	
	public String getMobileDevicePlatform(String appName, String devName) {		

		String respValue = null;
		try {

			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['platform.name']");
				respValue = resp.get(0).trim();	
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['platform.name']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}	

	

	/*
	 * Purpose:To get mobile device OS version of given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */		
	public String getMobileDevicePlatformVer(String appName, String devName) {		

		String respValue = null;
		try {
			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['platform.version']");
				respValue = resp.get(0).trim();
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['platform.version']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}
	

	/*
	 * Purpose:To get mobile device user id of given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */		

	public String getMobileDeviceUserId(String appName, String devName) {		

		String respValue = null;
		try {

			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['userid.number']");
				respValue = resp.get(0).trim();
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['userid.number']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}



	/*
	 * Purpose:To get mobile device cordinates to click confirm on a given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 	
	public String getMobileConfirmClickX(String appName, String devName) {		

		String respValue = null;

		try {

			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['confirm.x']");
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['confirm.x']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}
	

	 * Purpose:To get mobile device cordinates to click confirm on a given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:

	public String getMobileConfirmClickY(String appName, String devName) {		

		String respValue = null;
		try {

			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['confirm.y']");
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['confirm.y']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}	


	 * Purpose:To get mobile device cordinates to click gallery on a given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:

	public String getMobileGalleryClickX(String appName, String devName) {		

		String respValue = null;
		try {

			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['gallery.x']");
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['gallery.x']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}


	 * Purpose:To get mobile device cordinates to click confirm on a given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:

	public String getMobileGalleryClickY(String appName, String devName) {		

		String respValue = null;
		try {
			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['gallery.y']");
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['gallery.y']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}			

	
	 * Purpose:To get adroid mobile camera keyevent on a given device from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	public String getMobileCamerKeyevent(String appName, String devName) {		

		String respValue = null;
		try {
			if (appName.length() > 0 && devName.length() > 0) {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+appName.trim()+"')].devices");
				List<String> resp = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ devName.trim() +"')].['camera.keyevent']");
			}else {
				List<List<String>> dev = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '"+currTestApp.trim()+"')].devices");
				respValue = JsonPath.read(dev.get(0).toString(), "$[?(@.name == '"+ currTestDevice.trim() +"')].['camera.keyevent']");					
			}			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;						
	}*/

	

	public String getAppiumVer(String appName) {				
		String respValue = (appName.length() > 0 ) ?
				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].['appium.version']"):
				evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].['appium.version']");
		return respValue;						
	}



	public String getAppiumPortNo(String appName) {				

		String respValue = (appName.length() > 0 ) ?

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].['localhost.port']"):

					evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].['localhost.port']");

		return respValue;

	}

	

	public String getAndroidAppPackageName(String appName) {		

		String respValue = (appName.length() > 0 ) ?

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].['app.package']"):

					evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].['app.package']");

		return respValue;						

	}

	

	public String getAndroidAppPackageActivity(String appName) {		

		String respValue = (appName.length() > 0 ) ?

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].['app.activity']"):

					evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].['app.activity']");

		return respValue;						

	}



	public String getAndroidAutomationName(String appName) {		

		String respValue = (appName.length() > 0 ) ?

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].['automation.name']"):

					evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].['automation.name']");

		return respValue;						

	}

	
	/*
	 * Purpose:To get Test rails url value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */
	public boolean getTestRailsLogging(String appName) {
		String respValue = (appName.length() > 0) ? 
				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].testrails.logResult") :
				evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].testrails.logResult");
		return Boolean.valueOf(respValue);		
	}
	
	/*
	 * Purpose:To get Test rails url value from suite config.json
	 * author-date: Satya, Gajula - 7/31/18
	 * reviewer-date:
	 */
	public String getTestRailsUrl(String appName) {
		String respValue = (appName.length() > 0) ? 
		evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].testrails.url") :
		evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].testrails.url");
		return respValue;	
	}

	

	/*

	 * Purpose:To get Test rails login user value from suite config.json

	 * author-date: Satya, Gajula - 7/31/18

	 * reviewer-date:

	 */	

	public String getTestRailsUser(String appName) {

		String respValue = (appName.length() > 0) ? 

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].testrails.email") :

				evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].testrails.email");

		return respValue;

	}



	/*

	 * Purpose:To get Test rails login password value from suite config.json

	 * author-date: Satya, Gajula - 7/31/18

	 * reviewer-date:

	 */	

	public String getTestRailsPassword(String appName) {

		String respValue = (appName.length() > 0) ? 

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].testrails.password") :

				evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].testrails.password");

		return respValue;

	}

	

	/*

	 * Purpose:To get Test rails login project name from suite config.json

	 * author-date: Satya, Gajula - 7/31/18

	 * reviewer-date:

	 */		

	public String getTestRailsProjectName(String appName) {

		String respValue = (appName.length() > 0) ? 

				evaluateJSONPathToString("$.apps[?(@.name == '"+appName.trim()+"')].testrails.project") :

				evaluateJSONPathToString("$.apps[?(@.name == '"+currTestApp.trim()+"')].testrails.project");

		return respValue;

	}



	private String evaluateJSONPathToString(String jsPath) {
		String respValue = null;
		try {
			List<String> respColl = JsonPath.read(jssuiteConfig, jsPath); 
			return respColl.get(0);			
		}catch(InvalidJsonException jsExp) {
			jsExp.printStackTrace();
		}
		return respValue;		
	}

	/*
	 * Purpose: To get database type of given database name from suite config.json
	 * author-date: Satya, Gajula - 8/8/18
	 * reviewer-date: 
	 */
	public String getdatabaseType(String dbName) {
		String dbType="";
        try {
			if(dbName.length() > 0) {
				dbType = evaluateJSONPathToString("$.databases[?(@.dbname == '"+dbName+"')].dbtype");
				System.out.println("DB Type "+dbType);				
			}else {
				return null;
			}
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return dbType;
	}

	

	/*
	 * Purpose: To get database server name of given database name from suite config.json
	 * author-date: Satya, Gajula - 8/8/18
	 * reviewer-date: 
	 */
	public String getdatabaseServer(String dbName) {
		String qaDbServer ="";
		if(dbName.length() > 0) {
			if(currTestEnv.trim().equalsIgnoreCase("qa") || currTestEnv.trim().equalsIgnoreCase("qa2")) {
				qaDbServer = "qa";
			}
			String dbServer = evaluateJSONPathToString("$.databases[?(@.dbname =='"+dbName+"')]."+qaDbServer+"dbservername");
			System.out.println("DB Server "+dbServer);
			return dbServer;
		}else {
			return null;
		}
	}	

	

	/*
	 * Purpose: To get database login user of given database name from suite config.json
	 * author-date: Satya, Gajula - 8/8/18
	 * reviewer-date: 
	 */
	public String getdatabaseLoginUser(String dbName) {
		if(dbName.length() > 0) {
			String dbUser = evaluateJSONPathToString("$.databases[?(@.dbname == '"+dbName+"')].username");
			System.out.println("DB User "+dbUser);
			return dbUser;
		}else {
			return null;
		}
	}

	

	/*
	 * Purpose: To get database login user of given database name from suite config.json
	 * author-date: Satya, Gajula - 8/8/18
	 * reviewer-date: 
	 */
	public String getdatabaseLoginPassword(String dbName) {
		if(dbName.length() > 0) {
			String dbPwd = evaluateJSONPathToString("$.databases[?(@.dbname == '"+dbName+"')].password");
			System.out.println("DB Password "+dbPwd);
			return dbPwd;
		}else {
			return null;
		}
	}
	
	
	/*
     * Purpose: To get desktop application version from suite config.json
     * author-date:
     * reviewer-date: 
     */
    public String getDesktopAppVer(String appName) {
        
        String respValue = null;
        
        try {
            if (appName.length() > 0) {
                List<String> resp  = JsonPath.read(jssuiteConfig, "$.apps[?(@.name == '" + appName.trim() + "')].version");
                respValue = resp.get(0).trim();

            } 
        } catch (InvalidJsonException jsExp) {
            jsExp.printStackTrace();
        }
        
        return respValue;
    }
    
    /*
	 * Purpose: To get card no of given credit card from suite config.json
	 * author-date: 
	 * reviewer-date: 
	 */
	public String getCreditCardNo(String cardname) {
		if(cardname.length() > 0) {
			String cardNo = evaluateJSONPathToString("$.creditcarddetails[?(@.cardname == '"+cardname+"')].cardno");
			return cardNo;
		}else {
			return null;
		}
	}
	
	/*
	 * Purpose: To get card name of given credit card from suite config.json
	 * author-date: 
	 * reviewer-date: 
	 */
	public String getCreditCardName(String cardname) {
		if(cardname.length() > 0) {
			String cardName = evaluateJSONPathToString("$.creditcarddetails[?(@.cardname == '"+cardname+"')].cardname");
			return cardName;
		}else {
			return null;
		}
	}
	/*
	 * Purpose: To get expire month of given credit card from suite config.json
	 * author-date: 
	 * reviewer-date: 
	 */
	public String getCreditCardExpiryMonth(String cardname) {
		if(cardname.length() > 0) {
			String expiryMonth = evaluateJSONPathToString("$.creditcarddetails[?(@.cardname == '"+cardname+"')].expirymonth");
			return expiryMonth;
		}else {
			return null;
		}
	}
	/*
	 * Purpose: To get expire year of given credit card from suite config.json
	 * author-date: 
	 * reviewer-date: 
	 */
	public String getCreditCardExpiryYear(String cardname) {
		if(cardname.length() > 0) {
			String expiryYear = evaluateJSONPathToString("$.creditcarddetails[?(@.cardname == '"+cardname+"')].expiryyear");
			return expiryYear;
		}else {
			return null;
		}
	}
	/*
	 * Purpose: To get zip code of given credit card from suite config.json
	 * author-date: 
	 * reviewer-date: 
	 */
	public String getCreditCardZipCode(String cardname) {
		if(cardname.length() > 0) {
			String zipCode = evaluateJSONPathToString("$.creditcarddetails[?(@.cardname == '"+cardname+"')].zipcode");
			return zipCode;
		}else {
			return null;
		}
	}
}

