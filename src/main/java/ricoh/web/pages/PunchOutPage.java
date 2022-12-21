package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class PunchOutPage {

	private OFSCWebActionEngine ofscEngine = null;
    private WebActionEngine ofscaction=null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
    
  
    //Locators
    public final String lnkPunchOut="xpath=//a[contains(text(),'EOD/Punch out')]";
    public final String lnkRemove="xpath=//a[contains(text(),'Remove')]";
    public final String btnReimbursEx="xpath=//input[@id='add_expenses_reimbursable']";
    public final String lstReimburlst="xpath=//select[@id='cpf_ReimbursableExpensesType_inner']";//AIR TRAVEL EXPENSE,MILEAGE EXPENSE,MISCELLANEOUS EXPENSE
    public final String txtPunchOutSum="xpath=//input[@id='cpf_NewReimbursableExpensesAmount_inner']";
    public final String btnPunchOutSave="xpath=//input[@id='reImbursableSaveBtn']";
    public final String btnPunchSave="xpath=//input[@id='btn_save']";
    public final String btnDetails="xpath=//*[@id='back-button' and @role='button']";
    public final String btnPunchOut="xpath=//input[@id='punch-out-submit-btn']";
    public final String lnkLogout="xpath=//a[contains(text(),'Logout')]";
    public final String btnPunchOutMSG="xpath=(//button[@title='Close' and @type='button'])[2]";
    public final String btnPunchOutConfirmMsg="xpath=//div[@id='notification-clear' and @class='button submit']";
    
    public final String lnkMore="xpath=//a[contains(text(),'More')]";
    
    public final String lnkEndofDay="xpath=//*[contains(text(),'End of Day')]";
    public final String lnkCompletionInfo="xpath=//*[contains(text(),'Completion/ Suspend Information')]";
    public final String lblExpencesData="xpath=//*[@data-label='A_EXPENSES_OUTBOUND']";
    public final String btnBackButton="xpath=//*[@id='back-button']";

    public PunchOutPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }
    public boolean punchoutwithPendingActivity(){
		boolean res=false;
		try{
			res=clickOnPunchOut();
			this.testObj.reportStatus(res,"Successfully clicked On Punchout plugin","Failed to clicked on Punchout plugin");	
			res=VerifyPunchOutButton();
			this.testObj.reportStatus(res, "Successfully clicked On Punchoutbtn", "Failed to clicked on Punchoutbtn");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Punchout process-OFSC ");
		}return res;
	}
 	public boolean punchout(String PunchPrice){
		boolean res=false;
		try{
			res=clickOnPunchOut();
			this.testObj.reportStatus(res,"Successfully clicked On Punchout button","Failed to clicked on Punchout button");
			res=clickOnReimbursEx();
			this.testObj.reportStatus(res,"Successfully clicked On ReimbursEx", "Failed to clicked on ReimbursEx");
			res=selectReimburslist("AIR TRAVEL EXPENSE");
			this.testObj.reportStatus(res,"Successfully selected Reimburs list","Failed to clicked on selected Reimburs list");
			res=clickOnPunchOutSum(PunchPrice);
			this.testObj.reportStatus(res, "Successfully clicked On PunchOutSum", "Failed to clicked On PunchOutSum");
			res=clickOnPunchOutSave();
			this.testObj.reportStatus(res,"Successfully clicked On PunchOutSave", "Failed to clicked On PunchOutSave");
			res=clickOnPunchSave();
			this.testObj.reportStatus(res,"Successfully click On PunchSave", "Failed to click On PunchSave");
			res=clickOnPunchoutbtn();
			this.testObj.reportStatus(res, "Successfully clicked On Punchoutbtn", "Failed to clicked on Punchoutbtn");
			ofscEngine.switchToIFrame();
			if(this.ofscEngine.isElementPresent(btnPunchOutConfirmMsg, true)) {
        		res=this.ofscEngine.click(btnPunchOutConfirmMsg, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
			if(this.ofscEngine.isElementPresent(btnPunchOutMSG, true)) {
        		res=this.ofscEngine.click(btnPunchOutMSG, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Punchout process-OFSC ");
		}return res;
	}
 	public boolean DayMileagExpencePunchOut(String PunchPrice){
		boolean res=false;
		try{
			res=clickOnPunchOut();
			this.testObj.reportStatus(res,"Successfully clicked On EstimateSubmit","Failed to clicked on EstimateSubmit");
			res=clickOnReimbursEx();
			this.testObj.reportStatus(res,"Successfully clicked On ReimbursEx", "Failed to clicked on ReimbursEx");
			res=selectReimburslist("AIR TRAVEL EXPENSE");
			this.testObj.reportStatus(res,"Successfully selected Reimburs list","Failed to clicked on selected Reimburs list");
			res=clickOnPunchOutSum(PunchPrice);
			this.testObj.reportStatus(res, "Successfully clicked On PunchOutSum", "Failed to clicked On PunchOutSum");
			res=clickOnPunchOutSave();
			this.testObj.reportStatus(res,"Successfully clicked On PunchOutSave", "Failed to clicked On PunchOutSave");
			res=clickOnPunchSave();
			this.testObj.reportStatus(res,"Successfully click On PunchSave", "Failed to click On PunchSave");
			res=clickOnPunchoutbtn();
			this.testObj.reportStatus(res, "Successfully clicked On Punchoutbtn", "Failed to clicked on Punchoutbtn");
			ofscEngine.switchToIFrame();
			if(this.ofscEngine.isElementPresent(btnPunchOutConfirmMsg, true)) {
        		res=this.ofscEngine.click(btnPunchOutConfirmMsg, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
			if(this.ofscEngine.isElementPresent(btnPunchOutMSG, true)) {
        		res=this.ofscEngine.click(btnPunchOutMSG, "click on PunchIn button");
        		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
        	}
			ofscEngine.switchToDefaultFrame();
			res=clickOnEndOfDay();
			this.testObj.reportStatus(res, "Successfully clicked On EndOfDay", "Failed to clicked on EndOfDay");
			res=clickOnCompletionInfo();
			this.testObj.reportStatus(res, "Successfully clicked On CompleteInfo", "Failed to clicked on CompleteInfo");
			//res=clickOnExpencesData();
			//this.testObj.reportStatus(res, "Successfully Verifyied expences data", "Failed to verify expences data");
			res=clickOnDetails();
			this.testObj.reportStatus(res, "Successfully clicked on details", "Failed to click on details");
			res=clickOnDetails();
			this.testObj.reportStatus(res, "Successfully clicked on details", "Failed to click on details");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Punchout process-OFSC ");
		}return res;
	}
 	
 	public boolean clickOnPunchOut() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPunchOut, "Clicked on PunchOut");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchOut");
			}
		return res;	
	}
	public boolean clickOnEndOfDay() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkEndofDay, "Clicked on EndOfDay");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on EndOfDay");
			}
		return res;	
	}
	public boolean clickOnCompletionInfo() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkCompletionInfo, "Clicked on CompleteInfo");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on CompleteInfo");
			}
		return res;	
	}
	public boolean clickOnExpencesData() {
		boolean res=false;
		try{
			Thread.sleep(3000);
			//res=this.ofscEngine.click(lblExpencesData, "Clicked on ExpenceData");
			String Val=this.ofscEngine.getVisibleText(lblExpencesData);
			//System.out.println(Val);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ExpenceData");
			}
		return res;	
	}
	public boolean clickOnReimbursEx() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			//if(ofscEngine.eleDisplayed(lnkRemove, "Remove button")) {
				//res=this.ofscEngine.click(lnkRemove, "Clicked on Remove button");
			//}
			res=this.ofscEngine.click(btnReimbursEx, "Clicked on ReimbursEx");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ReimbursEx");
			}
		return res;	
	}
	public boolean selectReimburslist(String value) {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.selectByName(lstReimburlst, value, "Select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean clickOnPunchOutSum(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtPunchOutSum, value);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchOutSum");
			}
		return res;	
	}
	public boolean clickOnPunchOutSave() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnPunchOutSave, "Clicked on PunchOutSave");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchOutSave");
			}
		return res;	
	}
	public boolean clickOnPunchSave() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnPunchSave, "Clicked on PunchSave");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchSave");
			}
		return res;	
	}
	
	public boolean clickOnDetails() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnDetails, "Clicked on Details button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on details button");
			}
		return res;	
	}
	public boolean clickOnMore() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkMore, "Clicked on More link");			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on More");
			}
		return res;	
		
	}
	public boolean clickOnPunchoutbtn() throws Exception  {
		boolean res=false;
		try{
			 Thread.sleep(4000);
			 res=this.ofscEngine.click(btnPunchOut, "Clicked on Punchoutbutton");
			 ofscEngine.switchToDefaultFrame();
			}
		catch(Exception e){
				  clickOnMore();
				  Thread.sleep(4000);
				  res=this.ofscEngine.click(btnPunchOut, "Clicked on Punchout button");
				  ofscEngine.switchToDefaultFrame();
			}
		return res;
	}
	public boolean VerifyPunchOutButton() throws Exception  {
		boolean res=false;
		try{
			 Thread.sleep(4000);
			/* ofscEngine.switchToIFrame();
			 res=this.ofscEngine.eleEnabled(btnPunchOut, "Verify on Punchoutbutton");
			 if (res==false) {
				 res=true;
			 }
			 ofscEngine.switchToDefaultFrame();*/
			 res=this.ofscEngine.click(btnBackButton, "Clicked on Punchout button");
			}
		catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to click on Punchout button");
			}
		return res;
	}
	
	public boolean clickOnLogout() {
		boolean res=false;
		try{
	         if (this.ofscaction.eleDisplayed(lnkLogout,"Logout button is displayed"))
	         {
			 Thread.sleep(4000);
			 res=this.ofscEngine.click(lnkLogout, "Clicked on Logout button");
			 this.ofscEngine.closeDriver();
			 }
	         else
	         {
				  clickOnMore();
				  Thread.sleep(4000);
				  res=this.ofscEngine.click(lnkLogout, "Clicked on Logout Button");
				  this.ofscEngine.closeDriver();
				  
	         }
	         
			 }catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Logout button");
			}
		return res;
	}
}



