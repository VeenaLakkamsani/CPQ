package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class PunchInPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
  //  private WebActionEngine webAction=null;

    //Locators
    public final String lnkPunchIn="xpath=//a[contains(text(),'Punch In')]";
    public final String radOnONCall="xpath=//input[@name='p_paycode'][@value='CALL_IN_IO']";
    public final String radRegularShift="xpath=//td//input[@name='p_paycode'][@value='CLOCK']";
    public final String radRegShift="xpath=//input[@id='p_paycode_0' and @type='radio']";
    //public final String radShift="xpath=//li[@class='radio-button-row']";
    public final String btnPunchinSubmit="xpath=//*[@id='punch-in-submit-btn']";
    public final String clickActivities="xpath=//div[text()='Activities']/.";
    public final String lnkMore="xpath=//a[text()='More']";
    public final String lnkShiftType="xpath=//a[text()='Shift Type']";
    public final String btnShiftTypeSubmit="xpath=//button[@class='button submit' and @type='submit']";
    public final String lnkDetails="xpath=//*[@id='back-button']";
    public final String btnPunchInMSG="xpath=(//*[@title='Close'])[2]";
    public final String btnPunchInConfirmMsg="xpath=//div[@id='notification-clear' and @class='button submit']";
  
    public final String lnkRequestHistory="xpath=//a[text()='Request History']";
    public final String lstSelectRange="xpath=//select[@data-label='A_SR_HISTORY_DURATION']";
    
   
    public PunchInPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }
    public boolean ShiftTypeValidation(){
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkShiftType, "Click on ShiftType");
			this.testObj.reportStatus(res, "Successfully clicked on ShiftType Link", "Failed to clicked On ShiftType link");	
			Thread.sleep(8000);
			res=res=this.ofscEngine.click(btnShiftTypeSubmit, "Click on ShiftType submit");
			this.testObj.reportStatus(res, "Successfully clicked on ShiftType Submit button", "Failed to clicked on ShiftType Submit button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the ShiftType process-OFSC ");
		}return res;
	}
    public boolean RequestHistoryValidation(){
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkRequestHistory, "Click on Request history");
			this.testObj.reportStatus(res, "Successfully clicked on Request history Link", "Failed to clicked On Request history");
			Thread.sleep(8000);
			res=this.ofscEngine.selectByName(lstSelectRange, "06 Months", "Required months history got selected");
			Thread.sleep(4000);
			res=res=this.ofscEngine.click(btnShiftTypeSubmit, "Click on ShiftType submit");
			this.testObj.reportStatus(res, "Successfully clicked on ShiftType Submit button", "Failed to clicked on ShiftType Submit button");
			Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkDetails, "clicked on back button");
    		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the ShiftType process-OFSC ");
		}return res;
	}
	public boolean punchin(){
		boolean res=false;
		try{
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");		
			res=clickOnRegularShift();
			this.testObj.reportStatus(res, "Successfully selected regular shift", "Failed to select regular shift");
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
			ofscEngine.switchToIFrame();
			if(this.ofscEngine.isElementPresent(btnPunchInConfirmMsg, true)) {
        		res=this.ofscEngine.click(btnPunchInConfirmMsg, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
			if(this.ofscEngine.isElementPresent(btnPunchInMSG, true)) {
        		res=this.ofscEngine.click(btnPunchInMSG, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the PunchIn process-OFSC ");
		}return res;
	}
	
	public boolean punchinForMM2M(String sPunchInType){
		boolean res=false;
		try{
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");
			String Str = new String(sPunchInType);
			Thread.sleep(2000);
			switch(Str.toUpperCase()) {
			case "REGULARSHIFT":
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(radRegularShift, "select regularshift");
			ofscEngine.switchToDefaultFrame();
			this.testObj.reportStatus(res, "Successfully selected regularshift radio button", "Failed to select regularshift Radio button");
			break;
			case "ONCALL":
			res=clickOnONCall();
			this.testObj.reportStatus(res, "Successfully selected ONCall radio button", "Failed to selected OnCall Radio button");
			break;
			}
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
			if(this.ofscEngine.isElementPresent(btnPunchInConfirmMsg, true)) {
        		res=this.ofscEngine.click(btnPunchInConfirmMsg, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
			if(this.ofscEngine.isElementPresent(btnPunchInMSG, true)) {
        		res=this.ofscEngine.click(btnPunchInMSG, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the PunchIn process-OFSC ");
		}return res;
	}
	public boolean CallSupportPunchIn(){
		boolean res=false;
		try{
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");
			res=clickOnONCall();
			this.testObj.reportStatus(res, "Successfully clicked on ONCall radio button", "Failed to clicked On OnCall Radio button");
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
			ofscEngine.switchToIFrame();
			if(this.ofscEngine.isElementPresent(btnPunchInConfirmMsg, true)) {
        		res=this.ofscEngine.click(btnPunchInConfirmMsg, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
			if(this.ofscEngine.isElementPresent(btnPunchInMSG, true)) {
        		res=this.ofscEngine.click(btnPunchInMSG, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the PunchIn process-OFSC ");
		}return res;
	}
	
	
	public boolean clickOnPunchIn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPunchIn, "Click on PunchIn");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchIn");
			}
		return res;	
	}
	public boolean clickonpunchinAccept() {
		 boolean res=false;
		 try {
			 	ofscEngine.switchToIFrame();
				if(this.ofscEngine.isElementPresent(btnPunchInConfirmMsg, true)) {
		    		res=this.ofscEngine.click(btnPunchInConfirmMsg, "click on PunchIn button");
		    		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
		    	}
				if(this.ofscEngine.isElementPresent(btnPunchInMSG, true)) {
		    		res=this.ofscEngine.click(btnPunchInMSG, "click on PunchIn button");
		    		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
		    	}
		 }catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on punchin success button");
			}
		return res;	
	}
	public boolean clickOnONCall() {
		boolean res=false;
		try{
			Thread.sleep(2000);
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(radOnONCall, "select on ON-Call");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select on ON-Call");
			}
		return res;	
	}
	public boolean clickOnRegularShift() {
		boolean res=false;
		try{
			Thread.sleep(2000);
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(radRegShift, "selected Regular shift");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select on ON-Call");
			}
		return res;	
	}
	public boolean clickOnPunchInSubmit() {
		boolean res=false;
		try{
			//Thread.sleep(4000);
			ofscEngine.switchToIFrame();	
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnPunchinSubmit, "Click on PunchInSubmit");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchInSubmit");
			}
		return res;	
	}
	public boolean clickonActivities() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickActivities, "Click on Activities");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Activities......");
			}
		return res;
	}
	
}