package ricoh.web.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import ricoh.core.accelerators.AbstractTestEngine;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class EnroutePage {
	
	 private OFSCWebActionEngine ofscEngine = null;
	 OFSCTestEngine testObj = null;
	 //private AbstractTestEngine abs=null;
	 //private WebActionEngine ofscaction=null;
	public final String txtOpenPartsOrder="xpath=(//*[contains(text(),'Open Parts Order')])[1]"; 
	public final String txtReturnable="xpath=(//*[contains(text(),'Returnable')])[1]";
	public final String lnkViewMore="xpath=//*[contains(text(),'View more')]";
	public final String lnkEnRoute = "xpath=//*[@data-plugin='PI-Enroute']";
    public final String lnkSRReqst= "xpath=(//div[@class='grid-item grid-item--main'])[1]";
    public final String lnkPIEnroute="xpath=//span[text()='Chargeable']/following-sibling::input";
    public final String lnkUnEnRoute="xpath=//*[@data-plugin='PI-UnRoute']";
    public final String lnkDIPIEnroute="xpath=//span[text()='Contract']/following-sibling::input";
    public final String txtStartOdoMeter="xpath=//input[@id='odoMeter' and @name='odoMeter']";
    public final String chkGlobalNotes="xpath=//input[@id='viewGlobalNotesChkBox' and @name='viewGlobalNotesChkBox']";
    public final String chkServiceNotes="xpath=//input[@id='viewServiceNotesChkBox' and @name='viewServiceNotesChkBox']";
    public final String btnEnrouteSubmit="xpath=//*[@id='enRouteSubmitBtn']";
    public final String lnkServiceRqst="xpath=//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='Chargeable']";
    public final String lnkDIServiceRqst="xpath=//div[contains(@id,'notscheduled-grid')]//span[text()='Contract']/..";
    //public final String lnkPendingReqId="xpath=//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='Chargeable']";
    public final String lnkPendingReqId="xpath=//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='%s']";
    public final String lnkLunchBreak="xpath=//div[@class='grid-item grid-item--main']/descendant::span[text()='Lunch break (Noon)']";
    public final String lnkCancelActivity="xpath=//a[@action_link_label='cancel_activity']";
    public final String txtCancelNotes="xpath=//*[@data-label='closure_notes']";
    public final String btnCancelSubmit="xpath=//*[contains(text(),'Submit')]";
    public final String btnExpand="xpath=(//span[text()='Excess Parts'])[1]";
    public final String tblRows="xpath=//*[@data-ofsc-entity='inventory']//tr/td[2]/div";
    
    public final String btnSplitShipment="xpath=//*[@id='splitShipmentBtn' and @class='cp_plugin_button button']";
    public final String clickActivities="xpath=//div[text()='Activities']/.";
    public final String clickLogout="xpath=//a[text()='Logout']";
    public final String lnkArrived="xpath=//a[contains(text(),'Arrived')]";
    public final String txtOdoMeterEnd="xpath=//*[@data-label='A_ODOMETER_END' and @type='text']";
    public final String btnArrivedSubmit="xpath=//*[contains(text(),'Submit')]";
    public final String lnkParts="xpath=//*[@data-label='list_inventories']";
    public final String tblExcessItem="xpath=//tr[@data-ofsc-inventory-type-label='EXCESS' and @role='button']";
    public final String lblExcessItem="xpath=//*[@data-label='I_EXCESS_ID']";
    public final String lblTrackNumber="xpath=//*[@id='cpf_trackingNumber']";
    public final String btnInvnetories="xpath=//*[@id='back-button']";
    public final String btnOdoCancel="xpath=//*[@id='enRouteCancelBtn' and @type='button']";
    
    public final String lnkServiceRqstUNroute="xpath=//*[@value='PI-UnEnroute' or type='button']";
    //public final String lnkExcessSR="xpath=//div[contains(@id,'notscheduled-grid')]//span[text()='Excess']/..";
    //public final String lnkExcessSR="xpath=//*[contains(@data-ofsc-entity-id,'grid-notscheduled')]//span[text()='Excess']/..";
    public final String lnkExcessSR="xpath=//div[@class='grid-item grid-item--main']/descendant::span[text()='Excess']";
    public final String lnkExcessPIEnroute="xpath=//span[text()='EXC']/following-sibling::input";
    public final String txtExcessID="xpath=//*[@class='cl-row block'][*/span[text()='ID']]/*[@class='cl-fld-value']";
    public final String lnkExcessParts="xpath=//*[@data-label='PI-Excess-parts']";
    public final String txtTypeExcessID="xpath=//*[@id='cpf_textExcessId_inner']";
    public final String btnExcessValidate="xpath=//*[@id='cpf_buttonExcessId']";
    public final String txtARSShippingLabel="xpath=//*[normalize-space(text())='ARS Shipping label']/ancestor::*[@class='cp_field_label']/following-sibling::*[@class='cp_field_value']//input";
    public final String btnIncreaseQty="xpath=//*[@id='cpf_button_increase' and @type='button']";
    public final String btnOnSubmit="xpath=//*[@value='Submit']";
    public final String txtETAStatus="xpath=//*[@class='cl-row block'][*/span[text()='ETA Status']]/*[@class='cl-fld-value']";
    
    public final String lnkExcessEnRoute="xpath=//div[@class='main-field']/descendant::span[text()='EXC']/following::input[1]";
    public final String lnkExcessServiceRqst="xpath=//div[contains(@class,'grid-row pointer ')]//span[text()='Excess']/..";

  //div[contains(@class,'grid-row pointer ')]//span[text()='Excess']/..
    
    
    	
  public EnroutePage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
        }
  
  
    public boolean CancelActivity() {
    	boolean res=false;
    	try{
			res=clickOnCancelActivity();
			this.testObj.reportStatus(res, "Successfully clicked on Cancel Activity", "Failed to clicked on Cancel Activity");
        	res=enterCancelNotes();
        	this.testObj.reportStatus(res, "Successfully entered Cancel notes", "Failed to Enetr Cancel notes");
        	res=clickOnCancelSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on SubmitBtn", "Failed to clicked on SubmitBtn");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
    	
    }
    public boolean enRoute(String Odometer, String sCustomerName){
		boolean res=false;
		try{
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			//res=clickOnPIEnroute();
			//this.testObj.reportStatus(res, "Successfully clicked on PIEnRoute", "Failed to clicked on PIEnRoute");
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	//res=verifyAvailabilityOfErrorMessage();
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	//res=clickOnServiceRqst();
        	res=clickOnPendingSR(sCustomerName);
        	this.testObj.reportStatus(res, "Successfully clicked on ServiceRqst", "Failed to clicked On Service Rqst");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	}
    
    public boolean ExcessenRoute(String Odometer){
		boolean res=false;
		try{
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			//res=clickOnExcessPIEnroute();
			//this.testObj.reportStatus(res, "Successfully clicked on Excess PIEnRoute", "Failed to clicked on excess PIEnRoute");
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	//res=verifyAvailabilityOfErrorMessage();
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	//res=clickOnServiceRqst();
        	res=clickOnExcessServiceRqstEnroute();
        	this.testObj.reportStatus(res, "Successfully clicked on excess ServiceRqst", "Failed to clicked On excess Service Rqst");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	}
    public boolean EnRouteVal(String Odometer){
		boolean res=false;
		try{
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			//res=clickOnPIEnroute();
			//this.testObj.reportStatus(res, "Successfully clicked on PIEnRoute", "Failed to clicked on PIEnRoute");
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	//res=verifyAvailabilityOfErrorMessage();
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	//res=clickOnServiceRqst();
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	}
    public boolean EnRouteErrorMsgVal(String Odometer,String sCustomerName){
		boolean res=false;
		try{
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			//res=clickOnPIEnroute();
			//this.testObj.reportStatus(res, "Successfully clicked on PIEnRoute", "Failed to clicked on PIEnRoute");
			res=clickOnOdoCancel();
        	this.testObj.reportStatus(res, "Successfully clicked on cancel button", "Failed to click on cancel button");
        	res=clickOnPendingSR(sCustomerName);
        	this.testObj.reportStatus(res, "Successfully clicked on Service Request", "Failed to click on Service Request");
        	res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
        	res=clickOnEnrouteErrorSubmit();
        	//this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	res=valErrorMessage("The odometer value should not be empty");
        	this.testObj.reportStatus(res, "Successfully Validated Error message", "Failed to validate error message");
        	/*res=enterOdoMeterStart(Odometer+"gggggggggg");
        	//this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	res=clickOnEnrouteErrorSubmit();
        	res=valErrorMessage("The odometer value should be numeric");
        	this.testObj.reportStatus(res, "Successfully Validated Error message", "Failed to validate error message");*/
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	res=clickOnPendingSR(sCustomerName);
        	this.testObj.reportStatus(res, "Successfully clicked on ServiceRqst", "Failed to clicked On Service Rqst");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	}
    public boolean UnEnRouteVal(){
		boolean res=false;
		try{
			res=clickOnServiceRqstUnroute();
			this.testObj.reportStatus(res, "Successfully clicked on UnRouteServiceRqst", "Failed to clicked On Service Rqst");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
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
	public boolean clickOnLunchBreak() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkLunchBreak, "Click on Lunchbreak");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
	public boolean clickOnCancelActivity() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkCancelActivity, "Click on Cancel Activity");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Cancel Activity .. ");
			}
		return res;	
	}
	public boolean enterCancelNotes() {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtCancelNotes, "test");
	        	res=true;
	        	}
	        catch (Exception e) {
				
	   				LogManager.logError(LoginPage.class.getName(), "Failed to enter OdoMeter reading");
	        	 }
	        return res ;
	   }
	public boolean clickOnCancelSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnCancelSubmit, "Click on Cancel submit");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Cancel submit button .. ");
			}
		return res;	
	}
	public boolean clickOnCancel() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnOdoCancel, "Clicked on Cancel");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Cancel .. ");
			}
		return res;	
	}
	public boolean clickOnOdoCancel() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnOdoCancel, "Clicked on Cancel");
			Thread.sleep(2000);
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Cancel .. ");
			}
		return res;	
	}
	 public boolean verifyReturnableandclickOnEnRoute() {
			boolean res=false;
			boolean isELementPresent=false;
			try{
				Thread.sleep(1000);		
			if (this.ofscEngine.isElementPresent(txtReturnable, true)) {
				this.ofscEngine.click(lnkEnRoute, "Click on PartsReceive Link");
				res=true;
			} else {
				do { 
					this.ofscEngine.click(lnkViewMore, "Click on ViewMore Link");
					Thread.sleep(500);
					if (this.ofscEngine.isElementPresent(txtReturnable, true)) {
						res=this.ofscEngine.click(lnkEnRoute, "Click on EnRoute Link");
						isELementPresent=true;
						res=true;
						break;
					}
				} while (!isELementPresent);
			}
			 
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
				}
			return res;	
		}
	public boolean txtReturnable() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			if(res=ofscEngine.eleDisplayed(txtReturnable, "Returnable visible successfully")) {
				System.out.println("Successfully verified Returnable");
			}else{
				do {
					ofscEngine.ScrollWeb();
					Thread.sleep(2000);
					clickOnViewMore();
					Thread.sleep(2000);
				}while(res=this.ofscEngine.eleDisplayed(txtReturnable, "Returnable visible successfully"));
			}	
			//res=this.ofscEngine.eleDisplayed(txtReturnable, "Returnable visible successfully");	
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify that Open order ");
			}
		return res;	
	}
	
	public boolean txtOpenPartOrder() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.eleDisplayed(txtOpenPartsOrder, "Open Parts Order");	
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify that Open order ");
			}
		return res;	
	}
	public boolean clickOnServiceReqst() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkSRReqst, "Click on ServiceRequest");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequest .. ");
			}
		return res;	
	}	
	public boolean clickOnDIPIEnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.waitAndClick(lnkDIPIEnroute, "Click on ServiceRequestPIEnroute");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequestPIEnroute .. ");
			}
		return res;	
	}	
	public boolean clickOnPIEnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.waitAndClick(lnkPIEnroute, "Click on ServiceRequestPIEnroute");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequestPIEnroute .. ");
			}
		return res;	
	}		
	public boolean clickOnExcessPIEnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.waitAndClick(lnkExcessPIEnroute, "Click on ServiceRequest Excess PIEnroute");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequest Excess PIEnroute .. ");
			}
		return res;	
	}	
	public boolean clickOnViewMore() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			//ofscEngine.switchToIFrame();
			res=this.ofscEngine.waitAndClick(lnkViewMore, "Clicked on ViewMore");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequest Excess PIEnroute .. ");
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
	
	public boolean clickonLogout() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickLogout, "Click on Logout");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Logout .. ");
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
	
	public boolean verifyAvailabilityOfErrorMessage(){
		boolean res=false;
		if (ofscEngine.isAlertPresented()){
			res=true;
		}
		else{
			res=false;
		}
		return res;
	}
	public boolean valErrorMessage(String ErrorMsg){
		boolean res=false;
		if (ofscEngine.isAlertPresented()){
			
			String Capturedtext=ofscEngine.getAlerttext();
			System.out.println(Capturedtext);
			if(Capturedtext.trim().contains(ErrorMsg)) {
				res=true;
				this.testObj.reportStatus(res, "Successfully verified Error message"+Capturedtext, "Failed to verify error message");
			}
			
		}
		else{
			res=false;
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
	public boolean clickOnEnrouteErrorSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			this.ofscEngine.textFieldClear(txtStartOdoMeter);
			res=this.ofscEngine.click(btnEnrouteSubmit, "Click on EnrouteSubmit");		
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on EnrouteSubmit");
			}
		return res;	
	} 
	
	public boolean  EnrouteValidation() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			this.ofscEngine.click(btnEnrouteSubmit, "Click on Enroute Submit");
			Thread.sleep(2000);
			 res = clickonActivities();
			}catch(Exception e){
				res = false;
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Activities");
			}
		return res;
	}
	
	public boolean verifyAlert(){
		boolean res = false;
		try {
			ofscEngine.isAlertPresent();
			ofscEngine.switchToDefaultFrame();
	        clickonActivities();
			
		} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Exeception to verify Alert");
		}
        return res;
	}
	
	public boolean clickOnServiceRqst() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(lnkServiceRqst, "Click on ServiceRqst");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ServiceRqst");
			}
		return res;	
	}
	public boolean clickOnPendingSR(String sCustomerName) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			//res=this.ofscEngine.waitAndClick(lnkPendingReqId, "Clicked on ServiceRqst");
			res=this.ofscEngine.waitAndClick(String.format(lnkPendingReqId,sCustomerName), "Clicked on ServiceRqst");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ServiceRqst");
			}
		return res;	
	}
	public boolean clickOnDIServiceRqst() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(lnkDIServiceRqst, "Click on Delivery Install ServiceRqst");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Delivery install ServiceRqst");
			}
		return res;	
	}
	
	public boolean clickOnServiceRqstUnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			//ofscEngine.switchToIFrame();
			//res=this.ofscEngine.waitAndClick(lnkPIEnroute, "Click on ServiceRequestPIEnroute");
			res=this.ofscEngine.click(lnkUnEnRoute, "click on unEnoute button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequestPIEnroute .. ");
			}
		return res;
	}
		
	public boolean clickOnExcessServiceRqstEnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(lnkExcessSR, "Click on ServiceRequest Excess SR");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ServiceRequest Excess PIEnroute");
			}
		return res;
	}

	public boolean captureExcessID(String EndOdoVal){
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkArrived, "Clicked on Arrived");
			this.testObj.reportStatus(res, "Successfully clicked on Arrived", "Failed to clicked on Arrived");
			Thread.sleep(4000);
        	res= this.ofscEngine.type(txtOdoMeterEnd, EndOdoVal);
        	Thread.sleep(4000);
			res=this.ofscEngine.click(btnArrivedSubmit, "Clicked on Submit");
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkParts,"Clicked on Parts");
			Thread.sleep(4000);
			res=this.ofscEngine.click(tblExcessItem, "tblExcessPart");
			Thread.sleep(2000);
			String ExcessId=this.ofscEngine.getVisibleText(lblExcessItem);
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnInvnetories, "Clicked on back button ");
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnInvnetories, "Clicked on back button ");
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkExcessParts, "Clicked on excess parts ");
			ofscEngine.switchToIFrame();
			res= this.ofscEngine.type(txtTypeExcessID, ExcessId );
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(btnExcessValidate, "Click on Excess Validate button");
			Thread.sleep(4000);
			String TrackNumber=this.ofscEngine.getVisibleText(lblTrackNumber);
			Thread.sleep(2000);
			this.ofscEngine.enterMulVals(txtARSShippingLabel, TrackNumber+"8965874");
			Thread.sleep(4000);
			this.ofscEngine.ClickMultipleButtons();
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(btnOnSubmit, "Clicked on submit button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to validate Excess PIEnroute");
		}
		return res;	
	}
	public boolean ExcessErrorValidation(String EndOdoVal){
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkArrived, "Clicked on Arrived");
			this.testObj.reportStatus(res, "Successfully clicked on Arrived", "Failed to clicked on Arrived");
			Thread.sleep(4000);
        	res= this.ofscEngine.type(txtOdoMeterEnd, EndOdoVal);
        	this.testObj.reportStatus(res, "Successfully entered odo meter value", "Failed to enter odo meter value");
        	Thread.sleep(4000);
			res=this.ofscEngine.click(btnArrivedSubmit, "Clicked on Submit");
			this.testObj.reportStatus(res, "Successfully clicked on Arrivedsubmit", "Failed to clicked on Arrivedsubmit");
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkParts,"Clicked on Parts");
			this.testObj.reportStatus(res, "Successfully clicked on parts", "Failed to clicked on parts");
			Thread.sleep(4000);
			//res=this.ofscEngine.click(btnExpand, "Clicked on expand");
			Thread.sleep(4000);
			this.ofscEngine.clickExcessPart(tblRows);	
			//res=this.ofscEngine.click(tblExcessItem, "tblExcessPart");
			this.testObj.reportStatus(res, "Successfully clicked on Excess table", "Failed to clicked on Excess table");
			Thread.sleep(2000);	
			String ExcessId=this.ofscEngine.getVisibleText(lblExcessItem);
			this.testObj.reportStatus(res, "Successfully caputred excess item"+ExcessId, "Failed to captured excess item");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnInvnetories, "Clicked on back button");
			this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnInvnetories, "Clicked on back button ");
			this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkExcessParts, "Clicked on excess parts ");
			this.testObj.reportStatus(res, "Successfully clicked on excess parts", "Failed to clicked on excessparts");
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.waitAndClick(btnExcessValidate, "Clicked on Validate button");
			//this.testObj.reportStatus(res, "Successfully clicked on Submit without  enter excessId", "Failed to clicked on Submit without enter excessId");
			res=valErrorMessage("Some fields are filled incorrectly.");
			this.testObj.reportStatus(res, "Successfully verified error message", "Failed to verify error message");
			res= this.ofscEngine.type(txtTypeExcessID, ExcessId+23 );
			this.testObj.reportStatus(res, "Successfully entered excessId", "Failed to entered excessId");
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(btnExcessValidate, "Click on Excess Validate button");
			res=valErrorMessage("Incorrect Excess ID");
			this.testObj.reportStatus(res, "Successfully verified error message", "Failed to verify error message");
			Thread.sleep(4000);
			res= this.ofscEngine.type(txtTypeExcessID, ExcessId );
			this.testObj.reportStatus(res, "Successfully entered excessId", "Failed to entered excessId");
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(btnExcessValidate, "Click on Excess Validate button");
			String TrackNumber=this.ofscEngine.getVisibleText(lblTrackNumber);
			Thread.sleep(2000);
			this.ofscEngine.enterMulVals(txtARSShippingLabel, "8965874");
			res=this.ofscEngine.waitAndClick(btnOnSubmit, "Clicked on submit button");
			res=valErrorMessage("Please enter the quantity greater than zero or remove ARS Shipping label.");
			this.ofscEngine.enterMulVals(txtARSShippingLabel,TrackNumber);
			res=this.ofscEngine.waitAndClick(btnOnSubmit, "Clicked on submit button");
			res=valErrorMessage("Please enter the quantity greater than zero or remove ARS Shipping label.");
			this.ofscEngine.ClickMulButtons(btnSplitShipment);
			this.ofscEngine.enterMulVals(txtARSShippingLabel, TrackNumber+"8965874");
			Thread.sleep(4000);
			this.ofscEngine.ClickMultipleButtons();
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(btnOnSubmit, "Clicked on submit button");
			Thread.sleep(4000);
			res=this.ofscEngine.waitAndClick(btnInvnetories, "Clicked on Back button");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to validate Excess PIEnroute");
		}
		return res;	
	}

}
