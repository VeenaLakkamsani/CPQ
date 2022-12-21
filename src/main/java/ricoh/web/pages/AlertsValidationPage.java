package ricoh.web.pages;

import org.openqa.selenium.Keys;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.AbstractTestEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class AlertsValidationPage{
	 
    private OFSCWebActionEngine ofscEngine = null;
    OFSCTestEngine testObj = null;
    //private LoginPage loginPage = null;

    //Locators for AlertCIT
    public final String lnkAlertCIT="xpath=//*[@action_link_label='mobile_activity_request#47#43' and @role='button']";
    public final String lstContactReason="xpath=//*[@data-label='S_CIT_CONCERN']";
    public final String txtComments="xpath=//*[@data-label='S_SALES_COMM']";
    public final String txtCustomerContactName="xpath=//*[@data-label='S_CONTACT' and @type='text']";
    public final String txtContactNumber="xpath=//*[@data-label='S_PHONE_NUMBER' and @type='tel']";
    public final String lstResponseRequired="xpath=//*[@data-label='S_RESPONSE_REQUIRED']";
    public final String lstResponse="xpath=//*[@data-label='S_SALES_REASON']";
    public final String btnSubmit="xpath=//button[@type='submit']";
    
    //Locators for AlertSales
    public final String lnkAlertSales="xpath=//a[text()='Alert Sales']";
    public final String lstSalesContReason="xpath=//*[@data-label='S_SALES_CONCERN']";
   
    //Locaters for ARMS
    public final String lnkARMS="xpath=//*[@data-plugin='PI-ARMS' and @role='button']";
    public final String txtARMSUserID="xpath=//*[@name='userid' and @id='userid']";
    public final String txtARMSPassWD="xpath=//*[@name='password' and @id='password']";
    public final String btnARMSLogin="xpath=//*[contains(text(),'Login')]";
    public final String txtDeviceID="xpath=//*[@id='deviceid']";
    public final String btnARMSLogOut="xpath=//span[contains(text(),'Logout')]";
    public final String lnkARMSLogOut="xpath=//a[contains(text(),'Logout')]";
    public final String lnkDetails="xpath=//*[@id='back-button']";
    
    
    //Locators for Reshedule
    public final String lnkReshedule="xpath=//a[text()='Reschedule']";
    public final String tblDayFromTale="xpath=//*[@class='calendar-grid']//tr[6]/td[2]";
    public final String lnkOrdered="xpath=//*[contains(text(),'Ordered')]";
    public final String lnkReSheduleSubmit="xpath=//a[contains(text(),'Submit')]";
    
    
  
     
  
 public AlertsValidationPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
        }
 
 public boolean ReSheduleValidation() {
		boolean res= false ;
 	try {
 		Thread.sleep(4000);
 		res=this.ofscEngine.click(lnkReshedule, "clicked on Reshedule");
 		this.testObj.reportStatus(res, "Successfully clicked on Reshedule", "Failed to clicked on Reshedule");
 		Thread.sleep(8000);
 		res=this.ofscEngine.click(tblDayFromTale, "clicked on date in table");
 		this.testObj.reportStatus(res, "Successfully clicked on Date", "Failed to clicked on Date");
    	Thread.sleep(8000);
 		res=this.ofscEngine.click(lnkOrdered, "clicked on ordered link");
 		this.testObj.reportStatus(res, "Successfully clicked on ordered link", "Failed to clicked on ordered link");
 		Thread.sleep(4000);
 		res=this.ofscEngine.click(lnkReSheduleSubmit, "clicked submit button");
 		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked on submit");
 		
 	} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
			
		}
return res ;
 	
 }
 	
	public boolean ARMSValidation(String sUserID, String sPassword) {
 		boolean res= false ;
    	try {
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkARMS, "clicked on ARMS");
    		this.testObj.reportStatus(res, "Successfully clicked on ARMS", "Failed to clicked on ARMS");
    		Thread.sleep(8000);
    		this.ofscEngine.switchToIFrame();
    		res=this.ofscEngine.type(txtARMSUserID, sUserID);
    		this.testObj.reportStatus(res, "Successfully entered user name", "Failed to enter user name");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtARMSPassWD, sPassword);
    		this.testObj.reportStatus(res, "Successfully entered Password", "Failed to enter Password");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnARMSLogin, "clicked on Login button");
    		this.testObj.reportStatus(res, "Successfully clicked on login", "Failed to clicked on login");
       		Thread.sleep(8000);
       		this.ofscEngine.switchToIFrame();
    		res=this.ofscEngine.isElementPresent(txtDeviceID, true);
    		this.testObj.reportStatus(res, "Successfully verified the element", "Failed to verify the element");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnARMSLogOut, "clicked on ARMSLogout button");
    		this.testObj.reportStatus(res, "Successfully clicked on logout", "Failed to clicked on logout");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkARMSLogOut, "clicked on ARMSLogout Link in POPUP");
    		this.testObj.reportStatus(res, "Successfully clicked on logout", "Failed to clicked on logout");
    		Thread.sleep(4000);
    		this.ofscEngine.switchToDefaultContent();
    		res=this.ofscEngine.click(lnkDetails, "clicked on back button");
    		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkDetails, "clicked on back button");
    		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
    		
    	} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to Process ARMS validation");
			
		}
return res ;
    	
    }
 
 	public boolean AlertSalesValidation(String CustomerName) {
 		boolean res= false ;
    	try {
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkAlertSales, "clicked on AlertSales");
    		this.testObj.reportStatus(res, "Successfully clicked on AlertSales", "Failed to clicked on AlertSales");
    		Thread.sleep(4000);
    		res=this.ofscEngine.selectByName(lstSalesContReason, "Equipment performance concern", "Contact reason selected as Equipment performance concern");
    		this.testObj.reportStatus(res, "Successfully selected required value", "Failed to select required value");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtComments, "Test");
    		this.testObj.reportStatus(res, "Successfully entered comments", "Failed to enter comments");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtCustomerContactName, CustomerName);
    		this.testObj.reportStatus(res, "Successfully entered customer name", "Failed to enter customer name");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtContactNumber, "9849098490");
    		this.testObj.reportStatus(res, "Successfully entered contact number", "Failed to enter contact number");
    		Thread.sleep(4000);
    		res=this.ofscEngine.selectByName(lstResponseRequired, "NO", "Response required selected as Yes");
    		this.testObj.reportStatus(res, "Successfully clicked on Reshedule", "Failed to clicked on Reshedule");
    		Thread.sleep(4000);
    		res=this.ofscEngine.selectByName(lstResponse, "No Response required", "Response required selected as Yes");
    		this.testObj.reportStatus(res, "Successfully selected required value", "Failed to select required value");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnSubmit, "clicked on submit button");
    		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked on submit");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkDetails, "clicked on back button");
    		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
    	} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to process alert sales validation");
			
		}
return res ;
    	
    }
    	
 
    public boolean AlertCITValidation(String CustomerName) {
    	boolean res= false ;
    	try {
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkAlertCIT, "clicked on AlertCIT");
    		this.testObj.reportStatus(res, "Successfully clicked on AlertCIT", "Failed to clicked on AlertCIT");
    		Thread.sleep(4000);
    		res=this.ofscEngine.selectByName(lstContactReason, "Access to Location Denied", "Contact reason selected as Access to Location Denied");
    		this.testObj.reportStatus(res, "Successfully clicked on Reshedule", "Failed to clicked on Reshedule");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtComments, "Test");
    		this.testObj.reportStatus(res, "Successfully entered comments", "Failed to enter comments");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtCustomerContactName, CustomerName);
    		this.testObj.reportStatus(res, "Successfully entered customer name", "Failed to enter customer name");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtContactNumber, "9849098490");
    		this.testObj.reportStatus(res, "Successfully entered contact number", "Failed to enter contact number");
    		Thread.sleep(4000);
    		res=this.ofscEngine.selectByName(lstResponseRequired, "NO", "Response required selected as Yes");
    		this.testObj.reportStatus(res, "Successfully selected required value", "Failed to select required value");
    		Thread.sleep(4000);
    		res=this.ofscEngine.selectByName(lstResponse, "No Response required", "Response required selected as Yes");
    		this.testObj.reportStatus(res, "Successfully selected required value", "Failed to select required value");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnSubmit, "clicked on submit button");
    		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked on submit");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkDetails, "clicked on back button");
    		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked on back button");
    	} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to process Alert CIT validation");
			
		}
return res ;
    	
    }
   
 
	
}