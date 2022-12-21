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

public class InventoryAdjustment {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    private AbstractTestEngine abs=null;
	private WebActionEngine ofscaction=null;
	OFSCTestEngine testObj = null;
    
    //Locators
	public final String btnPunchInConfirmMsg="xpath=//div[@id='notification-clear' and @class='button submit']";
	public final String lnkPunchIn="xpath=//a[contains(text(),'Punch In')]";
	public final String btnPunchinSubmit="xpath=//*[@id='punch-in-submit-btn']";
	public final String lnkArrived="xpath=//a[contains(text(),'Arrived')]";
	public final String btnArrivedSubmit="xpath=(//*[@class='button submit' and @type='submit'])[2]";
	public final String lnkAddActivity="xpath=//*[contains(text(),'Add Activity')]";
	public final String lstActivityType="xpath=//*[@data-label='aworktype']"; //Inventory Adjustment
	public final String lnkASLRequest="xpath=//*[@data-plugin='PI-AddASLParts']";
	public final String lnkASLSR="xpath=//section[contains(@data-ofsc-activity-status,'pending')]//div[@class='grid-item grid-item--main']/descendant::span[text()='ASL Request']";
	//public final String lnkASLSR="//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='ASL Request']";
	public final String txtTimeDuration="xpath=//*[@data-label='length[hours]']";
	public final String lstPositionRout="xpath=//*[@data-label='position_in_route']";//Not ordered
	public final String btnInventorySubmit="xpath=//*[contains(text(),'Submit')]";//Have to add submit in the code again
	public final String lnkParts="xpath=//*[@action_link_label='list_inventories']";
	public final String lnkInventoryAdjustment="xpath=//a[contains(text(),'Inventory Adjustment')]";
	public final String lnkInventoryAdjustmentrequest="xpath=//section[contains(@data-ofsc-activity-status,'pending')]//div[@class='grid-item grid-item--main']/descendant::span[text()='Inventory Adjustment']";
	public final String txtPartNumber="xpath=//*[@data-label='I_ITEM_NUMBER' and @type='text']";
	public final String txtQtyPiece="xpath=//*[@data-label='quantity']";
	public final String lstAdjustmentreason="xpath=//select[@data-label='I_ADJUSTMENT_COMMENTS']";//Add Quantity To Inventory,Remove Quantity From Inventory
	public final String txtInventoryComments="xpath=//*[@data-label='I_INVENTORY_ADJ_COMMENT']";
	public final String btnDetails="xpath=//*[@id='back-button' and @role='button']";
	public final String btnASLItem="xpath=(//*[contains(text(),'My Sub Inventories')and (@data-ofsc-role='section-title')]/ancestor::thead/following-sibling::tbody//tr[@*='SUBINVENTORY'])[position()=1]";
	public final String lblCaptureASLItem="xpath=//*[@data-label='I_ITEM_NUMBER']";
	public final String lnkEnRoute = "xpath=//*[@data-plugin='PI-Enroute']";
	public final String txtStartOdoMeter="xpath=//input[@id='odoMeter' and @name='odoMeter']";
	public final String chkGlobalNotes="xpath=//input[@id='viewGlobalNotesChkBox' and @name='viewGlobalNotesChkBox']";
	public final String chkServiceNotes="xpath=//input[@id='viewServiceNotesChkBox' and @name='viewServiceNotesChkBox']";
	public final String btnEnrouteSubmit="xpath=//*[@id='enRouteSubmitBtn']";
	public final String txtASLSearch="xpath=//*[@id='addKeyword' and @type='search']";
	public final String lblItemNumber="xpath=//*[@data-ofsc-entity='inventory']//td[1]/div";
    public final String lblSubInvName="xpath=//*[@data-ofsc-entity='inventory']//td[7]";
    public final String lstAslInventory="xpath=//*[@id='cpf_SubInventoryList_inner']";	
    public final String btnASLAddbtn="xpath=//*[@id='addButton' and @type='button']";
    public final String txtRequiredMinValue="xpath=(//div[@class='cp_field_label']/following-sibling::div/div/input)[1]";
    public final String txtRequiredMaxValue="xpath=(//div[@class='cp_field_label']/following-sibling::div/div/input)[2]";
    public final String btnASLSavebutton="xpath=//*[@id='saveAllButton' and @type='button']";
    public final String lblCustomText="xpath=//span[@class='custom-text']";
    public final String btnASLSubmit="xpath=//button[@type='submit']";
    public final String lnkSuspend="xpath=//*[@data-label='suspend_activity' and @role='button']";
    public final String chkDeleteOverRide="xpath=//*[@class='cp_field_checkbox_component' and @type='checkbox']";
    public final String btnPunchInMSG="xpath=(//*[@title='Close'])[2]";
    public final String tblRows="xpath=//*[@data-ofsc-entity='inventory']//tr/td[2]/div";
    public final String btnExpand="xpath=(//span[text()='Sub Inventory'])[1]";
    public final String lblApprovalRequired="xpath=//*[contains(text(),'Approval Required')]";
       
    
    public InventoryAdjustment(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }
    
    public boolean InventoryAdjustment(String PartNo, String AdjustReason, String Odometer) {
    	boolean res=false;
		try{
			Thread.sleep(4000);
			res= clickOnAddActivity();
			this.testObj.reportStatus(res, "Successfully clicked on AddActivity", "Failed to clicked on AddActivity");
			
			res=selectActivityType();
			this.testObj.reportStatus(res, "Successfully selected ActivityType as Inventory Adjustment", "Failed to select ActivityType as Inventory Adjustment");
			
			res=enterTimeDurationMins();
			this.testObj.reportStatus(res, "Successfully entered time duration in mins", "Failed to enter time duration in mins");
			
			//res=selectPositionInRoute();
			//this.testObj.reportStatus(res, "Successfully selected Position in route", "Failed to select Position in route");
			
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on InventorySubmitBtn", "Failed to clicked on InventorySubmitBtn");
			
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	
        	res=clickOnInvAdjustSR();
			this.testObj.reportStatus(res, "Successfully clicked on InventoryAdjustment", "Failed to clicked on InventoryAdjustment");
			
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");
			
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
			
			res=clickonpunchinAccept();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInaccept button", "Failed to clicked on PunchInaccept button");
			
			//res=clickOnInvAdjustSR();
			//this.testObj.reportStatus(res, "Successfully clicked on InventoryAdjustment", "Failed to clicked on InventoryAdjustment");
			
			res=clickOnArrived();
			this.testObj.reportStatus(res, "Successfully clicked on Arrived link","Failed to clicked on Arrived link");
			
			res=clickOnArrivedSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
			
			res=clickOnParts();
			this.testObj.reportStatus(res, "Successfully clicked on Parts Link", "Failed to clicked On Parts link");
			Thread.sleep(7000);
			res=this.ofscEngine.click(btnExpand, "Clicked on expand");
			Thread.sleep(4000);
			String capturedVal=this.ofscEngine.gettabledata(tblRows);
			String[] info=capturedVal.split("#");
			System.out.println(info[0]);
			System.out.println(info[1]);
			
			res=clickOnInventoryAdjustment();
			this.testObj.reportStatus(res, "Successfully clicked on InventoryAdjustment button", "Failed to clicked on InventoryAdjustment button");
			
			res=enterPartNumber(info[0]);  //(PartNo);
			this.testObj.reportStatus(res, "Successfully entered Part number value"+info[0], "Failed to enter Part number value");
			
			res=enterQuantity("1");
			this.testObj.reportStatus(res, "Successfully entered quantity and peices", "Failed to enter quantity and peices");
			
			res=selectAdjustmentReason(AdjustReason);
			this.testObj.reportStatus(res, "Successfully selected Adjustment reason as "+AdjustReason, "Failed to select Adjustment reason");
			
			res=enterComments("test");
			this.testObj.reportStatus(res, "Successfully clicked on ReceiveInventory link", "Failed to clicked on ReceiveInventory link");
			
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on submit buttont", "Failed to clicked on submit button");
			
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on submitAll button", "Failed to clicked on submitAll button");
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on submitAll button", "Failed to clicked on submitAll button");
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Inventory Adjustment");
			}
		return res;	
    }
    public boolean ASLtoOracleValidations(String Odometer, String sMinVal, String sMaxVal) {
    	boolean res=false;
		try{
			Thread.sleep(4000);
			
			res= clickOnAddActivity();
			this.testObj.reportStatus(res, "Successfully clicked on AddActivity", "Failed to clicked on AddActivity");
			
			res=selectASLRequest("ASL Request");
			this.testObj.reportStatus(res, "Successfully selected ActivityType as Inventory Adjustment", "Failed to select ActivityType as Inventory Adjustment");
			
			res=enterTimeDurationMins();
			this.testObj.reportStatus(res, "Successfully entered time duration in mins", "Failed to enter time duration in mins");
			
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on SubmitBtn", "Failed to clicked on SubmitBtn");
			
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	
        	res=clickOnASLSR();
			this.testObj.reportStatus(res, "Successfully clicked on InventoryAdjustment", "Failed to clicked on InventoryAdjustment");
			
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");
			
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
						
			res=clickonpunchinAccept();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInaccept button", "Failed to clicked on PunchInaccept button");
			
			res=clickOnArrived();
			this.testObj.reportStatus(res, "Successfully clicked on Arrived link","Failed to clicked on Arrived link");
			
			res=clickOnArrivedSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
			
			res=clickOnParts();
			this.testObj.reportStatus(res, "Successfully clicked on Parts Link", "Failed to clicked On Parts link");
			Thread.sleep(7000);
			res=this.ofscEngine.click(btnExpand, "Clicked on expand");
			Thread.sleep(4000);
			String capturedVal=this.ofscEngine.gettabledata(tblRows);
			String[] info=capturedVal.split("#");
			System.out.println(info[0]);
			System.out.println(info[1]);
						
			//String sItemNumber=this.ofscEngine.getVisibleText(lblItemNumber);
			//String sSubInventoryName=this.ofscEngine.getVisibleText(lblSubInvName);
			
			res=clickOnASLRequest();
			this.testObj.reportStatus(res, "Successfully clicked on ASLRequest", "Failed to clicked on ASLRequest");
			
			//res=selectTechDestinationDropdownInner(sSubInventoryName);
			res=selectTechDestinationDropdownInner(info[1]);
			this.testObj.reportStatus(res, "Successfully selected on Destination dropdown", "Failed to selected on Destination dropdown");
			res=enterASLpartNumber(info[0]);
			//res=enterASLpartNumber(sItemNumber);
			this.testObj.reportStatus(res, "Successfully entered on Part number", "Failed to entered on Part number");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLAddbtn,"clicked on add button");
			this.testObj.reportStatus(res, "Successfully clicked on add button", "Failed to click on add button");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMinValue, sMinVal);
			this.testObj.reportStatus(res, "Successfully entered on Min value", "Failed to entered on Min value");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMaxValue, sMaxVal);
			this.testObj.reportStatus(res, "Successfully entered on Max value", "Failed to entered on Max value");	
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLSavebutton, "clicked on save button");
			this.testObj.reportStatus(res, "Successfully clicked on save button", "Failed to click on save button");
			
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
			
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on submit button", "Failed to clicked on submit button");
			
			Thread.sleep(4000);
			res=VerifyLabelMsg("Request DOES NOT need manager review, please click Submit button below to close the activity and send to Oracle.");
			this.testObj.reportStatus(res, "Successfully verified the message", "Failed to verify the message");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLSubmit, "clicked on submit button");
			this.testObj.reportStatus(res, "Successfully clicked on submit button", "Failed to clicked on submit button");
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Inventory Adjustment");
			}
		return res;	
    }
    
    
    public boolean ASLtoManagerValidations(String Odometer, String sMinVal, String sMaxVal) {
    	boolean res=false;
		try{
			Thread.sleep(4000);
			
			res= clickOnAddActivity();
			this.testObj.reportStatus(res, "Successfully clicked on AddActivity", "Failed to clicked on AddActivity");
			
			res=selectASLRequest("ASL Request");
			this.testObj.reportStatus(res, "Successfully selected ActivityType as Inventory Adjustment", "Failed to select ActivityType as Inventory Adjustment");
			
			res=enterTimeDurationMins();
			this.testObj.reportStatus(res, "Successfully entered time duration in mins", "Failed to enter time duration in mins");
			
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on InventorySubmitBtn", "Failed to clicked on InventorySubmitBtn");
			
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	
        	res=clickOnASLSR();
			this.testObj.reportStatus(res, "Successfully clicked on ASLRequest", "Failed to clicked on ASLRequest");
			
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");
			
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
			
			res=clickonpunchinAccept();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInaccept button", "Failed to clicked on PunchInaccept button");
			
			res=clickOnArrived();
			this.testObj.reportStatus(res, "Successfully clicked on Arrived link","Failed to clicked on Arrived link");
			
			res=clickOnArrivedSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
			
			res=clickOnParts();
			this.testObj.reportStatus(res, "Successfully clicked on Parts Link", "Failed to clicked On Parts link");
			Thread.sleep(7000);
			res=this.ofscEngine.click(btnExpand, "Clicked on expand");
			Thread.sleep(4000);
			String capturedVal=this.ofscEngine.gettabledata(tblRows);
			String[] info=capturedVal.split("#");
			System.out.println(info[0]);
			System.out.println(info[1]);
			
			res=clickOnASLRequest();
			this.testObj.reportStatus(res, "Successfully clicked on ASLRequest", "Failed to clicked on ASLRequest");
			
			res=selectTechDestinationDropdownInner(info[1]);
			this.testObj.reportStatus(res, "Successfully selected on Destination dropdown", "Failed to selected on Destination dropdown");
			
			res=enterASLpartNumber("17R8233"); //(info[0]);//17R8233
			this.testObj.reportStatus(res, "Successfully entered on Part number", "Failed to entered on Part number");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLAddbtn,"clicked on add button");
			this.testObj.reportStatus(res, "Successfully clicked on add button", "Failed to click on add button");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMinValue, sMinVal);
			this.testObj.reportStatus(res, "Successfully entered on Min value", "Failed to entered on Min value");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMaxValue, sMaxVal);
			this.testObj.reportStatus(res, "Successfully entered on Max value", "Failed to entered on Max value");
			
			//Thread.sleep(4000);
			//res=VerifyLabelMsg("Approval Required");
			//this.testObj.reportStatus(res, "Successfully verified the message", "Failed to verify the message");
			
			//Thread.sleep(4000);
			//res=this.ofscEngine.click(chkDeleteOverRide, "Delete Over ride checkbox");
			//this.testObj.reportStatus(res, "Successfully selected delete override checkbox", "Failed to select delete override checkbox");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLSavebutton, "clicked on save button");
			this.testObj.reportStatus(res, "Successfully clicked on save button", "Failed to click on save button");
			
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
			
			//res=clickOnInventorySubmit();
			res=this.ofscEngine.click(lnkSuspend, "clicked on suspend button");
			this.testObj.reportStatus(res, "Successfully clicked on suspend button", "Failed to clicked on suspend button");
			
			Thread.sleep(4000);
			res=VerifyLabelMsg("Request DOES need manager review, please click Submit button below to close the activity and send for Manager Review.");
			this.testObj.reportStatus(res, "Successfully verified the message", "Failed to verify the message");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLSubmit, "clicked on submit button");
			this.testObj.reportStatus(res, "Successfully clicked on submit button", "Failed to clicked on submit button");
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Inventory Adjustment");
			}
		return res;	
    }
    public boolean ASLtoManagerMulPartsValidations(String Odometer, String sMinVal, String sMaxVal) {
    	boolean res=false;
		try{
			Thread.sleep(4000);
			
			res= clickOnAddActivity();
			this.testObj.reportStatus(res, "Successfully clicked on AddActivity", "Failed to clicked on AddActivity");
			
			res=selectASLRequest("ASL Request");
			this.testObj.reportStatus(res, "Successfully selected ActivityType as Inventory Adjustment", "Failed to select ActivityType as Inventory Adjustment");
			
			res=enterTimeDurationMins();
			this.testObj.reportStatus(res, "Successfully entered time duration in mins", "Failed to enter time duration in mins");
			
			res=clickOnInventorySubmit();
			this.testObj.reportStatus(res, "Successfully clicked on InventorySubmitBtn", "Failed to clicked on InventorySubmitBtn");
			
			res=clickOnEnRoute();
			this.testObj.reportStatus(res, "Successfully clicked on EnRoute", "Failed to clicked on EnRoute");
			
        	res=enterOdoMeterStart(Odometer);
        	this.testObj.reportStatus(res, "Successfully entered on Odometer", "Failed to Enetr on Odometer");
        	
        	res=clickOnEnrouteSubmit();
        	this.testObj.reportStatus(res, "Successfully clicked on EnrouteSubmitBtn", "Failed to clicked on EnrouteSubmitBtn");
        	
        	res=clickOnASLSR();
			this.testObj.reportStatus(res, "Successfully clicked on ASLRequest", "Failed to clicked on ASLRequest");
			
			res=clickOnPunchIn();
			this.testObj.reportStatus(res, "Successfully clicked on PunchIn Link", "Failed to clicked On PunchIn link");
			
			res=clickOnPunchInSubmit();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInSubmit button", "Failed to clicked on PunchInSubmit button");
			
			res=clickonpunchinAccept();
			this.testObj.reportStatus(res, "Successfully clicked on PunchInaccept button", "Failed to clicked on PunchInaccept button");
			
			res=clickOnArrived();
			this.testObj.reportStatus(res, "Successfully clicked on Arrived link","Failed to clicked on Arrived link");
			
			res=clickOnArrivedSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
			
			res=clickOnParts();
			this.testObj.reportStatus(res, "Successfully clicked on Parts Link", "Failed to clicked On Parts link");
			
			Thread.sleep(4000);
			String capturedVal=this.ofscEngine.gettabledata(tblRows);
			String[] info=capturedVal.split("#");
			System.out.println(info[0]);
			System.out.println(info[1]);
			
			res=clickOnASLRequest();
			this.testObj.reportStatus(res, "Successfully clicked on ASLRequest", "Failed to clicked on ASLRequest");
			
			res=selectTechDestinationDropdownInner(info[1]);
			this.testObj.reportStatus(res, "Successfully selected on Destination dropdown", "Failed to selected on Destination dropdown");
			
			res=enterASLpartNumber("17R8233"); //(info[0]);//17R8233
			this.testObj.reportStatus(res, "Successfully entered on Part number", "Failed to entered on Part number");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLAddbtn,"clicked on add button");
			this.testObj.reportStatus(res, "Successfully clicked on add button", "Failed to click on add button");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMinValue, sMinVal);
			this.testObj.reportStatus(res, "Successfully entered on Min value", "Failed to entered on Min value");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMaxValue, sMaxVal);
			this.testObj.reportStatus(res, "Successfully entered on Max value", "Failed to entered on Max value");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLAddbtn,"clicked on add button");
			this.testObj.reportStatus(res, "Successfully clicked on add button", "Failed to click on add button");
			
			res=enterASLpartNumber("17R8234"); //(info[0]);//17R8233
			this.testObj.reportStatus(res, "Successfully entered on Part number", "Failed to entered on Part number");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMinValue, sMinVal);
			this.testObj.reportStatus(res, "Successfully entered on Min value", "Failed to entered on Min value");
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtRequiredMaxValue, sMaxVal);
			this.testObj.reportStatus(res, "Successfully entered on Max value", "Failed to entered on Max value");
			
			
			//Thread.sleep(4000);
			//res=VerifyLabelMsg("Approval Required");
			//this.testObj.reportStatus(res, "Successfully verified the message", "Failed to verify the message");
			
			//Thread.sleep(4000);
			//res=this.ofscEngine.click(chkDeleteOverRide, "Delete Over ride checkbox");
			//this.testObj.reportStatus(res, "Successfully selected delete override checkbox", "Failed to select delete override checkbox");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLSavebutton, "clicked on save button");
			this.testObj.reportStatus(res, "Successfully clicked on save button", "Failed to click on save button");
			
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
			
			//res=clickOnInventorySubmit();
			res=this.ofscEngine.click(lnkSuspend, "clicked on suspend button");
			this.testObj.reportStatus(res, "Successfully clicked on suspend button", "Failed to clicked on suspend button");
			
			Thread.sleep(4000);
			res=VerifyLabelMsg("Request DOES need manager review, please click Submit button below to close the activity and send for Manager Review.");
			this.testObj.reportStatus(res, "Successfully verified the message", "Failed to verify the message");
			
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLSubmit, "clicked on submit button");
			this.testObj.reportStatus(res, "Successfully clicked on submit button", "Failed to clicked on submit button");
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Inventory Adjustment");
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
    public boolean clickOnInvAdjustSR() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkInventoryAdjustmentrequest, "Click on Inventory adjustment ");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Inventory adjustment .. ");
			}
		return res;	
	}
    public boolean VerifyLabelMsg(String ErrorMsg) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			String Val=this.ofscEngine.getVisibleText(lblCustomText);
			if(Val.equals(ErrorMsg))
			{
				System.out.println("Error message was verified");
				res=true;
			}
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify error msg");
			}
		return res;	
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
    public boolean clickOnTechDestinationDropdownInner() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lstAslInventory, "Clicked on All Group radio button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on All Group radio button");
			}
		return res;	
	}

public boolean selectTechDestinationDropdownInner(String value) {
		boolean res=false;
		try{
			
			Thread.sleep(4000);
			this.ofscEngine.switchToIFrame();
			res=this.ofscEngine.selectByName(lstAslInventory, value, "required value selected");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
    public boolean enterASLpartNumber(String partNo) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtASLSearch, partNo);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter ASL Item");
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
    public boolean clickOnASLItem(){
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnASLItem, "Click on ASLItem");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchIn");
			}
		return res;	
	}
    public boolean clickOnASLRequest() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkASLRequest, "Click on ASLRequest");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ASLRequest");
			}
		return res;	
	}
    
    public boolean clickOnPunchInSubmit() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnPunchinSubmit, "Click on PunchInSubmit");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchInSubmit");
			}
		return res;	
	}
    public boolean clickonpunchinAccept() {
		 boolean res=false;
		 try {
			 	ofscEngine.switchToIFrame();
				if(this.ofscEngine.isElementPresent(btnPunchInMSG, true)) {
		    		res=this.ofscEngine.click(btnPunchInMSG, "click on PunchIn button");
		    		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
		    	}
				if(this.ofscEngine.isElementPresent(btnPunchInConfirmMsg, true)) {
		    		res=this.ofscEngine.click(btnPunchInConfirmMsg, "click on PunchIn button");
		    		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
		    	}
		 }catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on punchin success button");
			}
		return res;	
	}
    public boolean clickOnArrived() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkArrived, "Clicked on Arrived");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Arrived");
			}
		return res;	
	}
    public boolean clickOnArrivedSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnArrivedSubmit, "Clicked on Submit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Submit");
			}
		return res;	
	}
    public boolean selectActivityType() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstActivityType, "Inventory Adjustment", "selected Activity Type as inventory adjustment");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Inventory Adjustment");
			}
		return res;	
	}
    public boolean ActivityTypeSelect(String Val) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstActivityType, Val, "selected Activity Type ");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Activity Type");
			}
		return res;	
	}
	public boolean clickOnInventoryAdjustment() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkInventoryAdjustment, "Clicked on InventoryAdjustment");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on InventoryAdjustment");
			}
		return res;	
	}
	public boolean clickOnBackButton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnDetails, "Clicked on InventoryAdjustment");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on InventoryAdjustment");
			}
		return res;	
	}
	public boolean clickOnParts() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkParts, "Clicked on InventorySubmit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on InventorySubmit");
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
	
	public boolean enterTimeDurationMins() {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtTimeDuration, "1");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Duration time");
		
			}
	return res ;
	}	
	
	public boolean selectAdjustmentReason(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstAdjustmentreason, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}	
	public boolean selectPositionInRoute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstPositionRout, "Ordered", "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectPositionInRouteVal(String Val) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstPositionRout, Val, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean enterPartNumber(String PartNum) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtPartNumber, PartNum);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Duration time");
		
			}
	return res ;
	}
	public boolean enterQuantity(String qty) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtQtyPiece, qty);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Duration time");
		
			}
	return res ;
	}
	public boolean enterComments(String Comment) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtInventoryComments, Comment);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Duration time");
		
			}
	return res ;
	}
}