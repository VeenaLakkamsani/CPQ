package ricoh.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ExpencesPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
    //Locators
    public final String lnkExpences="xpath=//a[@data-plugin='PI-Expenses_Activities']";
    public final String btnReimbursExpence="xpath=//input[@id='cpf_addReimbursableExpenses']";
    public final String lstReimbursInner="xpath=//select[@id='cpf_ReimbursableExpensesType_inner']";//AIR TRAVEL EXPENSE,MILEAGE EXPENSE,MISCELLANEOUS EXPENSE,PARKING EXPENSE,TAXI EXPENSE
    public final String txtReimbursAmount="xpath=//input[@id='cpf_NewReimbursableExpensesAmount_inner']";
    public final String btnImbursSave="xpath=//input[@id='re_imbursable_save_btn']/..";
    public final String btnAddActivies="xpath=//input[@id='cpf_addActivities']/..";
    public final String lstActivityType="xpath=//select[@id='cpf_ActivitiesType_inner']";
    public final String btnActivityIncrease="xpath=//input[@id='cpf_NewActivitiesQuantity_button_increase']";
    public final String btnActiveSave="xpath=//input[@id='activities_save_btn']/..";
    public final String btnChrgActivity="xpath=//input[@id='cpf_addChargeableActivities']/..";
    public final String lstChargActivityType="xpath=//select[@id='cpf_ChargeableActivitiesType_inner']";
    public final String txtChargAmount="xpath=//input[@id='cpf_NewChargeableActivitiesAmount_inner']";
    public final String btnChargActIncrease="xpath=//input[@id='cpf_NewChargeableActivitiesQuantity_button_increase']/..";
    public final String btnChargActivSave="xpath=//input[@id='chargeable_activity_save_btn']/..";
    public final String btnExpenceSave="xpath=//input[@id='cpf__expenses_or_activities_submit_btn']";
    //Added here xpaths
    public final String clickActivities="xpath=//div[text()='Activities']/.";
    public final String clickDetails="xpath=//div[text()='Details']/.";
    public final String btnExpencesCancel="xpath=//*[@id='cpf__expenses_or_activities_cancel_btn' and @type='button']";
    public final String txtActivityQty="xpath=//*[@id='cpf_NewActivitiesQuantity_inner' and @type='text']";
    public final String txtChargableActQty="xpath=//*[@id='cpf_NewChargeableActivitiesQuantity_inner' and @type='text']";
    //Added CBM code
    public final String btnCBMAddbutton="xpath=//*[@id='cpf_addCBMActivities' and @type='button']";
    public final String lstCBMType="xpath=//select[@id='cpf_CBMActivitiesType_inner']";
    public final String txtCBMDuration="xpath=//*[@id='cpf_NewCBMActivitiesQuantity_inner' and @type='text']";
    public final String btnCBMSave="xpath=//*[@id='cbmactivities_save_btn' and @type='button']";
        

    public ExpencesPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }

	public boolean expences(String Amount, String Price ){
		boolean res=false;
		try{		
			res=clickOnExpence();
			this.testObj.reportStatus(res, "Successfully Clicked on expenses","Failed to Clicked on expenses");
			res=clickOnReimbursExpence();// this field is in frame  //iframe[@class='content-iframe']
			this.testObj.reportStatus(res,"Successfully Clicked on Reimburs Expence","Failed to Clicked on Reimburs Expence");
			res=selectReimbursInner("AIR TRAVEL EXPENSE"); //AIR TRAVEL EXPENSE,MILEAGE EXPENSE,MISCELLANEOUS EXPENSE,PARKING EXPENSE,TAXI EXPENSE
			this.testObj.reportStatus(res, "Successfully selected on ReimbursInner","Failed to selected on ReimbursInner");
			res=enterReimbursAmount(Amount);
			this.testObj.reportStatus(res, "Successfully entered ReimbursAmount","Failed to entered ReimbursAmount");
			res=clickOnImbursSave();
			this.testObj.reportStatus(res,"Successfully clicked on ImbursSave", "Failed to clicked on ImbursSave");
			res=clickOnAddActivies();
			this.testObj.reportStatus(res,"Successfully clicked on AddActivies", "Failed to clicked on AddActivies");
			res=selectActivityType("HD SURRENDER-SURRENDER HARD DRIVE TO CUSTOMER");//HD SURRENDER-SURRENDER HARD DRIVE TO CUSTOMER,METEROK-METERS CONFIGURATION WAS OK
			this.testObj.reportStatus(res, "Successfully selected Activity Type","Failed to selected Activity Type");
			//res=clickOnActivityIncrease();
			res=clickOnActivitySave();
			this.testObj.reportStatus(res,"Successfully clicked On Saved", "Failed to clicked On Saved");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnCBMAddbutton, "Clicked on Add CBM button");
			this.testObj.reportStatus(res,"Successfully clicked On CBM button", "Failed to clicked On CBM button");
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstCBMType, "IMAGE QUALITY - PAPER TRANSFER", "required value selected");
			this.testObj.reportStatus(res, "Successfully selected on CBM Type","Failed to selected on CBM Type");
			Thread.sleep(4000);
			res= this.ofscEngine.type(txtCBMDuration, Amount);
			this.testObj.reportStatus(res, "Successfully entered CBM duration","Failed to entered CBM duration");
			res=this.ofscEngine.click(btnCBMSave, "Clicked on Add CBM button");
			this.testObj.reportStatus(res,"Successfully clicked on CBMSave", "Failed to clicked on CBMSave");
			res=clickOnChargActivity();
			this.testObj.reportStatus(res,"Successfully clicked On ChargActivity","Failed to clicked On ChargActivity");
			res=selectChargActvType("TAS TRAINING ON FIERY CONTROLLER");//TAS TRAINING ON FIERY CONTROLLER,TAS SOLUTION TRAINING
			this.testObj.reportStatus(res,"Successfully selected Charge Actv Type","Failed to selected Charge Actv Type");
			res=enterChargeAmount(Price);
			this.testObj.reportStatus(res, "Successfully entered Charge Amount","Failed to entered Charge Amount");
			res=clickOnChargeActIncrease();
			this.testObj.reportStatus(res,"Successfully clicked On Charge Act Increase","Failed to clicked On Charge Act Increase");
			res=clickOnActiveSave();
			this.testObj.reportStatus(res,"Successfully Clicked on Active Save", "Failed to Clicked on Active Save");
			res=clickOnExpenceSave();
			this.testObj.reportStatus(res,"Successfully Clicked on ExpenceSave", "Failed to Clicked on ExpenceSave");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Expences process-OFSC ");
		}
		return res;
	}
	public boolean expencesErrorValidation(String Amount, String Price ){
		boolean res=false;
		try{		
			res=clickOnExpence();
			this.testObj.reportStatus(res, "Successfully Clicked on expenses","Failed to Clicked on expenses");
			res=clickOnExpenceCancel();
			this.testObj.reportStatus(res, "Successfully Clicked on expensesCancel","Failed to Clicked on ExpensesCancel");
			res=clickOnExpence();
			this.testObj.reportStatus(res, "Successfully Clicked on expenses","Failed to Clicked on expenses");
			res=clickOnReimbursExpence();// this field is in frame  //iframe[@class='content-iframe']
			this.testObj.reportStatus(res,"Successfully Clicked on Reimburs Expence","Failed to Clicked on Reimburs Expence");
			res=clickOnImbursSave();
			//this.testObj.reportStatus(res,"Successfully clicked on ImbursSave", "Failed to clicked on ImbursSave");
			res=valErrorMessage("Please Fill Out All The Fields");
			this.testObj.reportStatus(res,"Successfully Verified error message", "Failed to verify error message");
			res=selectReimbursInner("AIR TRAVEL EXPENSE"); //AIR TRAVEL EXPENSE,MILEAGE EXPENSE,MISCELLANEOUS EXPENSE,PARKING EXPENSE,TAXI EXPENSE
			this.testObj.reportStatus(res, "Successfully selected on ReimbursInner","Failed to selected on ReimbursInner");
			res=enterReimbursAmount(Amount);
			this.testObj.reportStatus(res, "Successfully entered ReimbursAmount","Failed to entered ReimbursAmount");			res=clickOnImbursSave();
			//this.testObj.reportStatus(res,"Successfully clicked on ImbursSave", "Failed to clicked on ImbursSave");
			res=valErrorMessage("Amount Should Be Greater Than Zero(0)");
			this.testObj.reportStatus(res,"Successfully Verified error message", "Failed to verify error message");	
			res=enterReimbursAmount("1");
			this.testObj.reportStatus(res, "Successfully entered ReimbursAmount","Failed to entered ReimbursAmount");
			res=clickOnImbursSave();
			this.testObj.reportStatus(res,"Successfully clicked on ImbursSave", "Failed to clicked on ImbursSave");
			
			
			
			res=clickOnAddActivies();
			this.testObj.reportStatus(res,"Successfully clicked on AddActivies", "Failed to clicked on AddActivies");
			res=enterActivityQty("0");
			this.testObj.reportStatus(res,"Successfully entered Activies qty", "Failed to Enter activity qty");
			res=clickOnActivitySave();
			//this.testObj.reportStatus(res,"Successfully clicked On Saved", "Failed to clicked On Saved");
			res=valErrorMessage("Please Fill Out All The Fields");
			this.testObj.reportStatus(res,"Successfully Verified error message", "Failed to verify error message");
			res=selectActivityType("HD SURRENDER-SURRENDER HARD DRIVE TO CUSTOMER");//HD SURRENDER-SURRENDER HARD DRIVE TO CUSTOMER,METEROK-METERS CONFIGURATION WAS OK
			this.testObj.reportStatus(res, "Successfully selected Activity Type","Failed to selected Activity Type");
			//res=clickOnActivityIncrease();
			res=clickOnActivitySave();
			//this.testObj.reportStatus(res,"Successfully clicked On Saved", "Failed to clicked On Saved");
			res=valErrorMessage("Quntity Should Be Greater Than Zero(0)");
			this.testObj.reportStatus(res,"Successfully Verified error message", "Failed to verify error message");
			res=enterActivityQty("1");
			this.testObj.reportStatus(res,"Successfully entered Activies qty", "Failed to Enter activity qty");
			res=clickOnActivitySave();
			this.testObj.reportStatus(res,"Successfully clicked On Saved", "Failed to clicked On Saved");
			
			
			
			
			res=clickOnChargActivity();
			this.testObj.reportStatus(res,"Successfully clicked On ChargActivity","Failed to clicked On ChargActivity");
			res=selectChargActvType("TAS TRAINING ON FIERY CONTROLLER");//TAS TRAINING ON FIERY CONTROLLER,TAS SOLUTION TRAINING
			this.testObj.reportStatus(res,"Successfully selected Charge Actv Type","Failed to selected Charge Actv Type");
			res=enterChargeAmount(Price);
			this.testObj.reportStatus(res, "Successfully entered Charge Amount","Failed to entered Charge Amount");
			res=clickOnActiveSave();
			//this.testObj.reportStatus(res,"Successfully Clicked on Active Save", "Failed to Clicked on Active Save");
			res=valErrorMessage("Amount Should Be Greater Than Zero(0)");
			this.testObj.reportStatus(res,"Successfully Verified error message", "Failed to verify error message");
			res=enterChargeAmount("1");
			this.testObj.reportStatus(res, "Successfully entered Charge Amount","Failed to entered Charge Amount");
			res=enterChargableActivityQty("0");
			this.testObj.reportStatus(res, "Successfully entered ChargebleActivity qty","Failed to entered ChargebleActivity qty");
			res=clickOnActiveSave();
			//this.testObj.reportStatus(res,"Successfully Clicked on Active Save", "Failed to Clicked on Active Save");
			res=valErrorMessage("Quntity Should Be Greater Than Zero(0)");
			this.testObj.reportStatus(res,"Successfully Verified error message", "Failed to verify error message");
			res=enterChargableActivityQty("1");
			this.testObj.reportStatus(res, "Successfully entered ChargebleActivity qty","Failed to entered ChargebleActivity qty");
			res=clickOnActiveSave();
			this.testObj.reportStatus(res,"Successfully Clicked on Active Save", "Failed to Clicked on Active Save");
			res=clickOnExpenceSave();
			this.testObj.reportStatus(res,"Successfully Clicked on ExpenceSave", "Failed to Clicked on ExpenceSave");
			
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Expences process-OFSC ");
		}
		return res;
	}
	public boolean clickOnExpence() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkExpences, "Clicked on Expence");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Expence");
			}
		return res;	
	}
	public boolean clickOnExpenceCancel() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnExpencesCancel, "Clicked on ExpenceCancel");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ExpenceCancel");
			}
		return res;	
	}
	public boolean enterActivityQty(String Amount) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtActivityQty, Amount);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Amount");
		
			}
	return res ;
	}
	public boolean enterChargableActivityQty(String Amount) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtChargableActQty, Amount);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter qty");
		
			}
	return res ;
	}
	public boolean valErrorMessage(String ErrorMsg){
		boolean res=false;
		if (ofscEngine.isAlertPresented()){
			String Capturedtext=ofscEngine.getAlerttext();
			System.out.println(Capturedtext);
			if(Capturedtext.equals(ErrorMsg)) {
				res=true;
				this.testObj.reportStatus(res, "Successfully verified Error message"+Capturedtext, "Failed to verify error message");
			}
			
		}
		else{
			res=false;
		}
		return res;
	}
	public boolean clickOnReimbursExpence() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnReimbursExpence, "Clicked on Reimburs");		
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Remiburs");
			}
		return res;	
	}
	
	public boolean selectReimbursInner(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstReimbursInner, value, "required value selected");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	
	public boolean enterReimbursAmount(String Amount) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtReimbursAmount, Amount);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Amount");
		
			}
	return res ;
	}
	
	public boolean clickOnImbursSave() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnImbursSave, "Clicked on Save");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Save");
			}
		return res;	
	}
	
	//Code Added here:
	public boolean clickonActivities() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickActivities, "Click on ServiceRequest");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Activities .. ");
			}
		return res;
	}
	
	public boolean clickonDetails() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickDetails, "Click on ServiceRequest");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Details .. ");
			}
		return res;
	}
	
	public boolean  expensesValidation() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnImbursSave, "Click on Save button");
			Thread.sleep(2000);
			res=clickonActivities();
			
			}catch(Exception e){
				res = false;
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Activities");
				
			}
		return res;
	}
	
	public boolean acceptAlert(){
		boolean res = false;
		try {
			ofscEngine.isAlertPresent();
			ofscEngine.switchToDefaultFrame();
			clickonDetails();
	        clickonActivities();
			
		} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Exeception to verify Alert");
		}
        return res;
	}
	
	//Code Added till here
	public boolean clickOnAddActivies() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnAddActivies, "Clicked on Add Activies");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Add Activies");
			}
		return res;	
	}
	
	
	public boolean selectActivityType(String value) {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.selectByName(lstActivityType, value, "clicked on activity type");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select activity");
			}
		return res;	
	}
	public boolean clickOnActivityIncrease() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnActivityIncrease, "Clicked on ActivityIncrease");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ActivityIncrease");
			}
		return res;	
	}
	public boolean clickOnActivitySave() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnActiveSave, "Clicked on ActiveSave");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ActiveSave");
			}
		return res;	
	}
	public boolean clickOnChargActivity() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnChrgActivity, "Clicked on ChargActive");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ChargActive");
			}
		return res;	
	}
	
	public boolean selectChargActvType(String value) {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.selectByName(lstChargActivityType, value, "Required field got selected");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required field");
			}
		return res;	
	}
	public boolean enterChargeAmount(String Amount) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtChargAmount, Amount);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Amount");
			}
	return res ;
	}
	public boolean clickOnChargeActIncrease() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnChargActIncrease, "Clicked on ChargActIncrease");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ChargActIncrease");
			}
		return res;	
	}
	public boolean clickOnActiveSave() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnChargActivSave, "Clicked on ChargActivSave");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ChargActivSave");
			}
		return res;	
	}
	public boolean clickOnExpenceSave() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.clickusingJavaScript(btnExpenceSave);
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ExpenceSave");
			}
		return res;	
	}
}