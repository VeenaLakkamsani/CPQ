package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ServiceEstimatePage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;

    //Locators
   
    public final String lnkServiceEstimate="xpath=//a[@data-plugin='PI-Service_Estimate']";
    public final String txtPONumber="xpath=//input[@id='po_number' and @class='cp_field_text_component form-item']";
    public final String lnkTechBillAmount="xpath=//*[@class='billing-amount-original-value-link total-labor-billamount']";
    public final String chkViewTerms="xpath=//input[@id='cpf_viewTerms_Check' and @class='cp_field_checkbox_component form-item']";
    public final String txtSignField="xpath=//*[@id='signature']";
    public final String txtSignName="xpath=//*[@id='signeeName' and @type='text']";
    public final String txtContactEmail="xpath=//*[@id='signeeEmail']";
    public final String btnEstimateSubmit="xpath=//input[@id='cpf__unnamed_62' and @class='cp_plugin_button button submit']";
    public final String btnEstimateCancel="xpath=//*[@id='cancelBtn' and @value='Cancel']";
    
    
   
    public ServiceEstimatePage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }

    
	public boolean serviceEstimate(String PoNumber, String EmailID){
		boolean res=false;
		try{
			res=clickOnServiceEstimate();
			this.testObj.reportStatus(res,"Successfully Clicked on ServiceEstimate","Failed to Clicked on ServiceEstimate");
			res=enterPONumber(PoNumber);
			this.testObj.reportStatus(res, "Successfully Clicked on ServiceEstimate","Failed to Clicked on ServiceEstimate");
			res=setViewTerms();
			this.testObj.reportStatus(res,"Successfully set on set View Terms", "Failed to set on set View Terms");
			res=enterSignField();
			this.testObj.reportStatus(res,"Successfully entered sign fields", "Failed to entered sign fields");
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.type(txtSignName, "Girish");
			this.testObj.reportStatus(res,"Successfully entered EmailID", "Failed to entered EmailID");
			res=enterContactEmail(EmailID);
			this.testObj.reportStatus(res,"Successfully entered EmailID", "Failed to entered EmailID");
			res=clickOnEstimateSubmit();
			this.testObj.reportStatus(res,"Successfully clicked On EstimateSubmit","Failed to clicked On EstimateSubmit");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the serviceEstimate process-OFSC ");
		}return res;
	}
	public boolean serviceEstimateErrorVal(String PoNumber, String EmailID){
		boolean res=false;
		try{
			res=clickOnServiceEstimate();
			this.testObj.reportStatus(res,"Successfully Clicked on ServiceEstimate","Failed to Clicked on ServiceEstimate");
			res=clickOnEstimateCancel();
			this.testObj.reportStatus(res,"Successfully Clicked on ServiceEstimateCancel","Failed to Clicked on ServiceEstimateCancel");
			res=clickOnServiceEstimate();
			this.testObj.reportStatus(res,"Successfully Clicked on ServiceEstimate","Failed to Clicked on ServiceEstimate");
			res=clickOnErrorEstimateSubmit();
			//this.testObj.reportStatus(res,"Successfully clicked On EstimateSubmit","Failed to clicked On EstimateSubmit");
			res=valErrorMessage("Please Enter the PO# Number.");
			this.testObj.reportStatus(res,"Successfully verified error message","Failed to verify error message");
			res=enterPONumber(PoNumber);
			this.testObj.reportStatus(res, "Successfully Clicked on ServiceEstimate","Failed to Clicked on ServiceEstimate");
			res=clickOnTechBillEdit();
			this.testObj.reportStatus(res, "Successfully Clicked on TechBillEdit","Failed to Clicked on TechBillEdit");
			res=setViewTerms();
			this.testObj.reportStatus(res,"Successfully set on set View Terms", "Failed to set on set View Terms");
			res=enterSignField();
			this.testObj.reportStatus(res,"Successfully entered sign fields", "Failed to entered sign fields");
			res=enterContactEmail(EmailID);
			this.testObj.reportStatus(res,"Successfully entered EmailID", "Failed to entered EmailID");
			res=clickOnEstimateSubmit();
			this.testObj.reportStatus(res,"Successfully clicked On EstimateSubmit","Failed to clicked On EstimateSubmit");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the serviceEstimate process-OFSC ");
		}return res;
	}
	
	public boolean clickOnServiceEstimate() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkServiceEstimate, "Clicked on ServiceEstimate");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ServiceEstimate");
			}
		return res;	
	}
	public boolean clickOnEstimateCancel() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnEstimateCancel, "Clicked on ServiceEstimateCancel");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ServiceEstimateCancel");
			}
		return res;	
	}
	public boolean clickOnTechBillEdit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkTechBillAmount, "Clicked on Tech bill edit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on tech bill edit");
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
	public boolean enterContactEmail(String EmailID) {
		boolean res=false;
		try{
			
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtContactEmail, EmailID);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter EmailID");
			}
		return res;	
	}
	
	public boolean setViewTerms() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.setCheckbox(chkViewTerms, true , "Set the checkbox for view terms");
			ofscEngine.ScrollWeb();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to set ViewTerms");
			}
		return res;	
	}
	public boolean enterSignField() {
		boolean res= false ;
	        try {
	        	Thread.sleep(3000);
	        	//res= this.ofscEngine.mouseDraw(txtSignField, "Digital signature field");
	        	res= this.ofscEngine.mouseDrawSignature(txtSignField, "Digital signature field");
	        	Thread.sleep(8000);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter SignField");
			}
	return res ;
	}
	
	public boolean clickOnErrorEstimateSubmit() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Estimate");
			//ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service Estimate");
			}
		return res;	
	}
	public boolean clickOnEstimateSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnEstimateSubmit, "Clicked on Service Estimate");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Service Estimate");
			}
		return res;	
	}
}