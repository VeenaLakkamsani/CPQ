package ricoh.web.pages;

import org.openqa.selenium.Keys;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class callSupport {
	
    private OFSCWebActionEngine ofscEngine = null;
    //private LoginPage loginPage = null;
    OFSCTestEngine testObj = null;

    //Locators
    public final String lnkPhoneFix = "xpath=//a[text()='Phone Fix/ Cancel']";
    public final String chkPhoneFix = "xpath=//*[@data-label='A_PHONE_FIX' and @type='checkbox']";
    
    public final String lstPhoneFixOption="xpath=//*[@data-label='A_PHONE_FIX_OPTION']";//Phone Fix Complete,Phone Fix ARMS
    public final String lstCancellationReason="xpath=//*[@data-label='cancel_reason']";//Cleared By Phone
    public final String txtCancelNotes="xpath=//*[@data-label='closure_notes']";
    public final String btnPhoneFixSubmit="xpath=//*[contains(text(),'Submit')]";
    

    
 public callSupport(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
        }
    
    public boolean phoneFix() {
		boolean res= false ;
	        try {
	        	res=clickOnPhoneFix();
	        	this.testObj.reportStatus(res, "Successfully clicked on Phonefix", "Failed to clicked on Phonefix");
	        	res=selectPhoneFixOption("Phone Fix Complete");
	        	this.testObj.reportStatus(res, "Successfully selected  Phonefix reason", "Failed to select Phonefix reason");	        	
	        	res=selectCancelReason("Cleared By Phone");
	        	this.testObj.reportStatus(res, "Successfully selected  canel reason", "Failed to select cancel reason");
	        	res=enterCancelNotes("Completed");
	        	this.testObj.reportStatus(res, "Successfully entered  canel reason", "Failed to entered cancel reason");
	        	res=clickonCallSupportSubmit();
	        	this.testObj.reportStatus(res, "Successfully clicked on submit button", "Failed to clicked on submit");
	        	} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
		
			}
	return res ;
	}
    
    public boolean clickOnPhoneFix() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPhoneFix, "Clicked on PhoneFix link");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Phone fix/Cancel");
			}
		return res;	
	}
	
	public boolean selectPhoneFixOption(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstPhoneFixOption, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}	
	
	public boolean setPhoneFixCheckbox() {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.setCheckbox(chkPhoneFix, true, "slected phone fix checkbox");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to check phonefix checkbox");
		
			}
	return res ;
	}	
	public boolean selectCancelReason(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstCancellationReason, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}	
	public boolean enterCancelNotes(String we) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtCancelNotes, we);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter required value");
			}
		return res;	
	}
	
	
	public boolean clickonCallSupportSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnPhoneFixSubmit, "Click on call support submit button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on submit button");
			}
		return res;
	}
	
}