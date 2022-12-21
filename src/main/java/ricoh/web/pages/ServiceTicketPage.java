package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ServiceTicketPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
  
    //Locators
    
    public final String lnkServiceTicket="xpath=//a[contains(text(),'Service Ticket')]";
    public final String txtPONumber="xpath=//input[@id='po_number' and @class='cp_field_text_component form-item']";
    public final String chkViewTerms="xpath=//input[@id='cpf_viewTerms_Check' and @class='cp_field_checkbox_component form-item']";
    public final String txtSignField="xpath=//div[@id='signature']";
    public final String chkEmailServiceTicket="xpath=//*[@id='serviceEmail' and @type='checkbox']";
    public final String btnEstimateSubmit="xpath=(//input[@id='cpf__unnamed_62' and @class='cp_plugin_button button submit'])[1]";
    public final String btnServiceTicketCancel="xpath=//*[@id='cancelBtn' and @type='button']";
    public final String btnBackbutton="xpath=//*[@id='back-button']";
    public final String lnkTotalLabourBill="xpath=//a[@class='billing-amount-original-value-link total-labor-billamount']";
    public final String txtBillChangeVal="xpath=//*[@id='inner_overriden_val_2' and @type='text']";
    public final String lstBillingReason="xpath=//*[@id='cpf_billingReasonDDSection']";
    public final String btnCustomerReview="xpath=//*[@id='customerViewBtn' and @type='button']";
    public final String chkSignatureByEmail="xpath=//*[@id='docuSignEmail' and @type='checkbox']";
    public final String txtCCEmails="xpath=//*[@id='ccEmails' and @type='text']";

    public ServiceTicketPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }
    public boolean customerReview(String sAmount){
		boolean res=false;
		try{
			res=clickOnServiceTicket();
			this.testObj.reportStatus(res,"Successfully clicked On ServiceTicket","Failed to clicked on ServiceTicket");
			ofscEngine.switchToIFrame();
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkTotalLabourBill, "click on edit bill");
			this.testObj.reportStatus(res,"Successfully clicked On edit bill","Failed to clicked on edit bill");
			res=this.ofscEngine.type(txtBillChangeVal, sAmount);
			this.testObj.reportStatus(res, "Successfully enter the amount","Failed to enter amount");
			res=this.ofscEngine.selectByName(lstBillingReason, "Billable Meter Read", "select required field"); //Billable Meter Read
			this.testObj.reportStatus(res, "Successfully selected billing reason","Failed to select billing reason");
			if(this.ofscEngine.isElementPresent(btnCustomerReview, true)) {
				res=this.ofscEngine.click(btnCustomerReview, "click on customer review button");
				this.testObj.reportStatus(res,"Successfully clicked On customer review button","Failed to clicked on customer review button");
				ofscEngine.switchToDefaultFrame();
				ofscEngine.switchToIFrame();
				Thread.sleep(2000);
			}
			res=setViewTerms();
			this.testObj.reportStatus(res, "Successfully set ViewTerms","Failed to clicked on set ViewTerms");
			res=enterSignField();
			this.testObj.reportStatus(res,"Successfully enter Signin Time", "Failed to enter Signin Time");
			res=setEmailServiceTicket();
			this.testObj.reportStatus(res,"Successfully set Email service ticket", "Failed to clicked on email service ticket");
			res=res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Ticket Submit button");
			this.testObj.reportStatus(res, "Successfully clicked On EstimateSubmit","Failed to clicked on EstimateSubmit");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the serviceTicket process-OFSC ");
		}return res;
	}
    public boolean ServiceTicketErrorValidation(String PoNumber){
		boolean res=false;
		try{
			res=clickOnServiceTicket();
			this.testObj.reportStatus(res,"Successfully clicked On ServiceTicket","Failed to clicked on ServiceTicket");
			res=clickOnServiceTicketCancel();
			this.testObj.reportStatus(res, "Successfully clicked On ServiceTicket Cancel","Failed to clicked on ServiceTicket Cancel");
			res=clickOnServiceTicket();
			this.testObj.reportStatus(res,"Successfully clicked On ServiceTicket","Failed to clicked on ServiceTicket");
			res=setViewTerms();
			this.testObj.reportStatus(res, "Successfully set ViewTerms","Failed to clicked on set ViewTerms");
			res=enterSignField();
			this.testObj.reportStatus(res,"Successfully enter Signin Time", "Failed to enter Signin Time");
			res=setEmailServiceTicket();
			this.testObj.reportStatus(res,"Successfully set Email service ticket", "Failed to clicked on email service ticket");
			res=clickOnEstimateSubmit(PoNumber);
			this.testObj.reportStatus(res, "Successfully clicked On EstimateSubmit","Failed to clicked on EstimateSubmit");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the serviceTicket process-OFSC ");
		}return res;
	}
   	public boolean serviceTicket(String PoNumber,String CCEmail){
		boolean res=false;
		try{
			res=clickOnServiceTicket();
			this.testObj.reportStatus(res,"Successfully clicked On ServiceTicket","Failed to clicked on ServiceTicket");
			res=setViewTerms();
			this.testObj.reportStatus(res, "Successfully set ViewTerms","Failed to clicked on set ViewTerms");
			res=enterSignField();
			this.testObj.reportStatus(res,"Successfully enter Signin Time", "Failed to enter Signin Time");
			res=setEmailServiceTicket();
			this.testObj.reportStatus(res,"Successfully set Email service ticket", "Failed to clicked on email service ticket");
			res=setSignatureByEmail();
			this.testObj.reportStatus(res,"Successfully set SignatureByEmail", "Failed to clicked on SignatureByEmail");
			//res=enterCCEmail("praveen.padeti@ricoh-usa.com");
			//this.testObj.reportStatus(res,"Successfully set Email service ticket", "Failed to clicked on email service ticket");
			res=clickOnEstimateSubmit(PoNumber);
			this.testObj.reportStatus(res, "Successfully clicked On EstimateSubmit","Failed to clicked on EstimateSubmit");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the serviceTicket process-OFSC ");
		}return res;
	}

   	public boolean InCompleteAssistServiceTicket(){
		boolean res=false;
		try{
			res=clickOnServiceTicket();
			this.testObj.reportStatus(res,"Successfully clicked On ServiceTicket","Failed to clicked on ServiceTicket");
			res=clickOnServiceTicketSubmit();
			this.testObj.reportStatus(res, "Successfully clicked On ServiceTicket Submit","Failed to clicked on ServiceTicket Submit");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the serviceTicket process-OFSC ");
		}return res;
	}
	public boolean clickOnServiceTicket() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkServiceTicket, "Clicked on Service ticket");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service ticket");
			}
		return res;	
	} 
	public boolean clickOnBackButton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnBackbutton, "Clicked on Service ticket");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service ticket");
			}
		return res;	
	}
	public boolean setViewTerms() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.setCheckbox(chkViewTerms, true , "Set the checkbox for view terms");
			ofscEngine.ScrollWeb();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to set ViewTerms");
			}
		return res;	
	}
	
	public boolean setEmailServiceTicket() {
		boolean res=false;
		try{
			//ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.setCheckbox(chkEmailServiceTicket, true , "Set the checkbox for Email Service Ticket");
			ofscEngine.ScrollWeb();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to set Email service ticket");
			}
		return res;	
	}
	public boolean enterSignField() {
		boolean res= false ;
	        try {
	        	Thread.sleep(3000);
	        	//res= this.ofscEngine.mouseDraw(txtSignField, "Digital signature field");
	        	res= this.ofscEngine.mouseDrawSignature(txtSignField, "Digital signature field");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter SignField");
			}
	return res ;
	}
	public boolean setSignatureByEmail() {
		boolean res=false;
		try{
			//ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.setCheckbox(chkSignatureByEmail, true , "Set the checkbox for Signature By Email");
			//ofscEngine.ScrollWeb();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to check Signature By Email");
			}
		return res;	
	}
	public boolean clickOnEstimateSubmit(String PoNumber) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Ticket Submit button");
			Thread.sleep(1000);
			if (ofscEngine.isAlertPresent()){
				System.out.println("Aleart accepted");
				//res=enterPONumber(PoNumber);
				//res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Estimate");
			}
			if(this.ofscEngine.isElementPresent(btnEstimateSubmit, true)) {
				res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Ticket Submit button");
				Thread.sleep(2000);
			}
			if (ofscEngine.isAlertPresent()){
		
			}
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service Estimate Submit button");
			}
		return res;	
	}
	public boolean clickOnServiceTicketSubmit() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Ticket Submit button");	
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service Estimate Submit button");
			}
		return res;	
	}
	public boolean clickOnServiceTicketCancel() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnServiceTicketCancel, "Clicked on Service Ticket Cancel button");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service Ticket cancel button");
			}
		return res;	
	}
	public boolean enterPONumber(String PoNumber) {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtPONumber, PoNumber);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter PONumber");
			}
		return res;	
	}
	public boolean enterCCEmail(String CCEmail) {
		boolean res=false;
		try{
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtCCEmails, CCEmail);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter PONumber");
			}
		return res;	
	}
	
}