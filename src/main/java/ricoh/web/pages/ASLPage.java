package ricoh.web.pages;

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

public class ASLPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    private AbstractTestEngine abs=null;
	private WebActionEngine ofscaction=null;
	OFSCTestEngine testObj = null;
    //Locators
	public final String lnkAddActivity="xpath=//a[contains(text(),'Add Activity')]";
	public final String lstActivityType="xpath=//select[@name='aworktype']"; //ASL Request
	public final String txtDurationinMinits="xpath=//*[@name='length']";
	public final String btnInventorySubmit="xpath=//*[contains(text(),'Submit')]";
	public final String btnSaveActivity="xpath=//*[@id='save_form']";
	//punchin,arrived
	public final String lnkParts="xpath=//a[contains(text(),'Parts')]";
	public final String lnkAslRequest="xpath=//a[contains(text(),'ASL request')]";
	public final String txtPartNumber="xpath=//input[@name='150']";
	public final String btnASL="xpath=(//*[contains(text(),'ASL')])[2]";
	public final String btnBackButton="xpath=//*[@id='back-button']";
	public final String lnkASLSR="xpath=//section[contains(@data-ofsc-activity-status,'pending')]//div[@class='grid-item grid-item--main']/descendant::span[text()='ASL Request']";
	public final String lnkEnRoute = "xpath=//*[@data-plugin='PI-Enroute']";
	public final String txtStartOdoMeter="xpath=//input[@id='odoMeter' and @name='odoMeter']";
	public final String chkGlobalNotes="xpath=//input[@id='viewGlobalNotesChkBox' and @name='viewGlobalNotesChkBox']";
	public final String chkServiceNotes="xpath=//input[@id='viewServiceNotesChkBox' and @name='viewServiceNotesChkBox']";
	public final String btnEnrouteSubmit="xpath=//*[@id='enRouteSubmitBtn']";
	
	
	
	
    public final String lnkArrived="xpath=//a[contains(text(),'Arrived')]";
    public final String txtOdoMeterEnd="xpath=//input[@name='430']";
    public final String btnArrivedSubmit="xpath=//input[@value='Submit']";
   
    public ASLPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }
        
	public boolean ASLRequestValidation(String Odometer, String time){
		boolean res=false;
		try{
			res=clickOnAddActivity();
			res=selectASLRequest("ASL Request");
			res=enterTimeDuration(time);
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on InventorySubmitBtn", "Failed to clicked on InventorySubmitBtn");
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	
        	res=clickOnASLSR();
        	
        	
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Arrived process-OFSC ");
		}return res;
	}
	public boolean clickOnAddActivity() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkAddActivity, "Clicked on AddActivity");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on AddActivity");
			}
		return res;	
	}
	public boolean clickOnASLSR() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkASLSR, "Clicked on ASL link");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ASL Link");
			}
		return res;	
	}
	public boolean clickOnEnRoute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkEnRoute, "Click on EnRoute");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
	public boolean enterOdoMeterStart(String Odometer) {
    	boolean res= false ;
            try {
            	Thread.sleep(4000);
            	ofscEngine.switchToIFrame();
            	res= this.ofscEngine.type(txtStartOdoMeter, Odometer);
            	if(this.ofscEngine.isElementPresent(chkServiceNotes, true)) {
            		clickOnchkServiceNotes();
            	}
            	if(this.ofscEngine.isElementPresent(chkGlobalNotes, true)) {
            		clickOnchkGlobalNotes();
            	}
            	/*if (this.ofscaction.eleDisplayed(chkServiceNotes,"Service Notes checkBox is displayed")){
    				LogManager.logInfo(LoginPage.class.getName(), "Service Notes checkBox is displayed");
    				clickOnchkServiceNotes();
    				}
    			
    			 * if (this.ofscaction.eleDisplayed(
    			 * chkGlobalNotes,"Global Notes checkBox is displayed")){
    			 * LogManager.logInfo(LoginPage.class.getName(),
    			 * "Global Notes checkBox is displayed"); clickOnchkGlobalNotes();
    			 * 
    			 * }
    			 */
            	}
            catch (Exception e) {
    			
       				LogManager.logError(LoginPage.class.getName(), "Failed to enter OdoMeter reading");
            	 }
            return res ;
       }
    public boolean clickOnchkGlobalNotes() {
		boolean res=false;
		try{
			Thread.sleep(2000);
				res=this.ofscEngine.setCheckbox(chkGlobalNotes,true ,"Check the Global notes checkbox");
				
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to check the global note checkbox");
			}
		return res;	
	}
	public boolean clickOnchkServiceNotes() {
		boolean res=false;
		try{
			Thread.sleep(2000);
				res=this.ofscEngine.setCheckbox(chkServiceNotes,true ,"Check the Global notes checkbox");
				
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to check the global note checkbox");
			}
		return res;	
	}
	 public boolean clickOnEnrouteSubmit() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				
				res=this.ofscEngine.click(btnEnrouteSubmit, "Click on EnrouteSubmit");
	        	ofscEngine.switchToDefaultFrame();
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on EnrouteSubmit");
				}
			return res;	
		} 
	public boolean clickOnInventorySubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnInventorySubmit, "Clicked on InventorySubmit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on InventorySubmit");
			}
		return res;	
	}
	public boolean selectASLRequest(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstActivityType, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}	
	public boolean enterTimeDuration(String time) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtDurationinMinits, time);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Duration time");
		
			}
	return res ;
	}	
	
	public boolean clickOnAddActivitySubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSaveActivity, "Clicked on Submit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Submit");
			}
		return res;	
	}
	public boolean clickOnParts() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkParts, "Clicked on Parts");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Parts");
			}
		return res;	
	}
	public boolean clickOnASLRequest() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkAslRequest, "Clicked on AslRequest");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on AslRequest");
			}
		return res;	
	}
	public boolean enterPartNumber(String Number) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtPartNumber, Number);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Part number");
		
			}
	return res ;
	}	
	public boolean clickOnASL() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASL, "Clicked on Asl");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Asl");
			}
		return res;	
	}
	public boolean clickOnBackButton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnBackButton, "Clicked on back button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on back button");
			}
		return res;	
	}
}