package ricoh.web.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.AbstractTestEngine;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;
import ricoh.web.pages.HomePage;


public class LoginPage {

    private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    private WebActionEngine ofscaction=null;
    private AbstractTestEngine abs=null;
	OFSCTestEngine testObj = null;

    //Locators
    public final String userName = "xpath=//*[@id='username' and @type='text']";
	//public final String userName="xpath=//input[@id='username|input']";
	//public final String passWord="xpath=//input[@id='password|input']";
    public final String passWord = "xpath=//*[@id='password' and @type='password']";
    //public final String signIn = "name=user_submitted_login_form";
    public final String signIn="xpath=//*[contains(text(),'Sign in')]";
	//public final String signIn="xpath=//span[@id='sign-in|text']";
    public final String loginErr = "xpath=//div[@class='errorrow']/span";
    public final String loginErrChckBox = "xpath=//span[@class='checkbox-mark']";//"xpath=//div[@class='form-row checkbox-with-label']/descendant::*[3]";
    
    public final String userNameMgnr = "xpath=//input[@id='username']";
    public final String passWordMgnr = "xpath=//input[@id='password']";
    public final String signInMgnr = "xpath=//button[@name='user_submitted_login_form']";

    
    public LoginPage(OFSCWebActionEngine actEngine){  
    	
    	ofscEngine = actEngine;
    	
        }

   	public boolean login(String UserName, String Password) {
		boolean res= false ;
	        try {
	        	//res= this.ofscEngine.click(UserName, "click on username");
	        	res=enterUserName(UserName);
	        	res=enterPassWord(Password);
	        	res=clickOnSignIn();
	        	Thread.sleep(3000);	
	        	if (this.ofscaction.isElementPresent(signIn,true)){
				LogManager.logError(LoginPage.class.getName(), "Error message is displayed");
	        	res=enterPassWord(Password);
        		res=clickonLoginErrchckBox();
	        	res=clickOnSignIn();
        	}
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
			}
	return res ;
	}
   	
   	public boolean loginMgnr(String UserName, String Password) {
		boolean res= false ;
	        try {
	        	res=enterUserName(UserName);
	        	res=enterPassWord(Password);
	        	res=clickOnSignIn();
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
			}
	return res ;
	}

	
	public boolean enterUserName(String UserName) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.type(userName, UserName);  		
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter user name ");
			}
	return res ;
	}
	
	public boolean enterUserNameMgnr(String UserName) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.type(userNameMgnr, UserName);  		
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter user name ");
			}
	return res ;
	}
	
	
	
	public boolean enterPassWord(String Password) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.type(passWord, Password);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter password ");
		
			}
	return res ;
	}
	
	public boolean enterPassWordMgnr(String Password) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.type(passWordMgnr, Password);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter password ");
		
			}
	return res ;
	}
	
	public boolean clickOnSignIn() {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.click(signIn, "Sign In Button");
	        	/*if(this.ofscaction.isElementEnabled(loginErrChckBox,"Login error checkbox is displayed",res)){
	        	res=this.ofscEngine.click(loginErrChckBox,"Clicked on Error checkbox in login page");
	        	res= this.ofscEngine.click(signIn, "Sign In Button");
	        	}*/
			} catch (Exception e) {
				
				LogManager.logError(LoginPage.class.getName(), "Failed click on sign in");
			}
	return res ;
	}
	
	public boolean clickOnSignInMgnr() {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.click(signInMgnr, "Sign In Button");
			} catch (Exception e) {
				
				LogManager.logError(LoginPage.class.getName(), "Failed click on sign in");
			}
	return res ;
	}

	
	public boolean clickonLoginErrchckBox() {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res= this.ofscEngine.click(loginErrChckBox, " Sign In Button");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed click on sign in .. ");	
			}
	return res ;
	}
}