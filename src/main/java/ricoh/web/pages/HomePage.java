package ricoh.web.pages;

import org.openqa.selenium.Keys;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.AbstractTestEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class HomePage{
	 
    private OFSCWebActionEngine ofscEngine = null;
    OFSCTestEngine testObj = null;
    //private LoginPage loginPage = null;
    public final String btnGlobalSearch="xpath=//*[@aria-label='GlobalSearch' and @role='button']";
    public final String txtSearchActivities="xpath=//*[contains(text(),'Search in activities or parts')]";
    public final String txtSerachVal="xpath=//*[@aria-label='Search in activities or parts' and @type='search']";
    public final String btnInfoSearch="xpath=//*[@class='activity-icon icon']";
    public final String btnSearchPref="xpath=//*[@class='search-bar-navigate']";
    //Locators
    public final String btnMenuOptions = "xpath=//div[@class='hamburger-button']";
    public final String lnkMyRoute ="xpath=//a[contains(text(),'My route')]";
    public final String lnkReturnable="xpath=(//*[contains(text(),'Returnable')])[1]";
    public final String lnkExcessList="xpath=(//*[contains(text(),'Excess')])[1]";
    public final String lnkOpenParts="xpath=(//*[contains(text(),'Open Parts Order')])[1]";
    public final String linkViewMore="xpath=//*[contains(text(),'View more')]";
    public final String lnkActivities="xpath=//*[contains(text(),'Activities')]";
    public final String imgGlobalSearch="xpath=//*[@aria-label='GlobalSearch']";
    public final String txtPartnumbersearch="xpath=//*[@aria-label='Search in activities or parts' and @type='search']";
    public final String lnkSearchPreference="xpath=//a[contains(text(),'Search Preferences')]";
    public final String chkSearchCheckboxes="xpath=//input[@type='checkbox']";
    public final String chkTaskNumber="xpath=//input[@type='checkbox' and @value='appt_number']";
    public final String chkEquipmentID="xpath=//input[@type='checkbox' and @value='248']";
    public final String chkSRnumber="xpath=//input[@type='checkbox' and @value='247']";
    public final String txtHideCharges="xpath=//*[@aria-label='Hide Charges Flag' and @type='text']";
    public final String btnHideChargeSubmit="xpath=//button[@class='button submit' and @type='submit']";
    public final String lnkLeadMyRoute="xpath=//a[@class='global-navigation-item global-navigation-item--myRoute' and @aria-label='My Route']";
    
    
   //As Per PhaseII update
    //public final String lnkPendingReqId="xpath=//div[@class='grid-item grid-item--main']/descendant::span[text()='Chargeable']";
    //public final String lnkPendingReqId="xpath=//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='Chargeable']";
   
    public final String lnkPendingReqId="xpath=//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='%s']";
    
    //GlobalNotes
    public final String lnkGobalNotes="xpath=//a[text()='***GLOBAL NOTES***']";
    //SetAppoinment
    public final String lnkSetAppoinment="xpath=//a[text()='Set Appointment']";
    public final String lstChangeTaskType="xpath=//*[@data-label='A_CHANGE_TASK_TYPE']";
    public final String ddlChangeTaskType="xpath=//*[@data-label='A_CHANGE_TASK_TYPE']/option";//1-Appointment Set - Technician, 2-Appointment Set - Customer
    //change time slot
    public final String lnkChangeTimeSlot="xpath=//a[text()='Change Time Slot']";
    public final String lstSelectTimeSlotType="xpath=//*[@data-label='A_SELECT_TIME_SLOT_TYPE']";
    public final String ddlSelectTimeSlotptions="xpath=//*[@data-label='A_SELECT_TIME_SLOT_TYPE']/option";
    //1-General
    public final String lstGeneraTimeSlot="xpath=//*[@data-label='A_GENERAL_TIME_SLOT']";
    public final String ddlGeneralTimeSlot="xpath=//*[@data-label='A_GENERAL_TIME_SLOT']/option";
    //2-Hourly Business Hours
    public final String lstHourlyTimeSlotBusinessHrs="xpath=//*[@data-label='A_TIME_SLOT']";
    public final String ddlHourlyTimeSlotBusinessHrs="xpath=//*[@data-label='A_TIME_SLOT']/option";
    //3- Hourly After Hours
    public final String lstHourlyTimeSlotAfterHrs="xpath=//*[@data-label='A_HOURLY_AFTER']";
    public final String ddlHourlyTimeSlotAfterHrs="xpath=//*[@data-label='A_HOURLY_AFTER']/option";
    
    public final String btnTimeSlotSubmit="xpath=//*[text()='Submit']";
    
    //PORequired
    public final String lnkPORequired="xpath=//a[text()='A PO Is Required!']";
    public final String txtEnterPONumber="xpath=//*[@aria-label='Enter Required PO']";
   //use this object to submit "btnHideChargeSubmit"
    
    //UpdateSiteReference
    public final String lnkUpdateSiteReference="xpath=//a[text()='Update Site Reference']";
    public final String txtUpdateSiteReference="xpath=//textarea[@aria-label='Update Site Reference']";
  //use this object to submit "btnHideChargeSubmit"
    
    //Update ETA
    public final String lnkChangeAppoinment="xpath=//a[text()='Customer Changed Appointment']";
    public final String radAppoinmentCustomer="xpath=//*[@name='S_CUSTOMER_CHANGED_APPOINTMENT' and @type='radio']";
    //use this object to submit "btnHideChargeSubmit"
    
    //Customer Provider ETA
    public final String lnkProvideCustomerETA="xpath=//a[text()='Provide Customer ETA']";
    public final String txtETANotes="xpath=//*[@data-label='SR_RECORD_NOTES']";
  //use this object to submit "btnHideChargeSubmit"
    public final String lnkBackButton="xpath=//div[text()='Activities']";
    
    public final String btnSubmitUpdate="xpath=//a[text()='Submit Update']";
    public final String btnAppoinmentChangedToday="xpath=//a[text()='Appointment changed for today']";
    public final String btnRescheduleDiffDay="xpath=//a[text()='Reschedule to a different day']";
    
    
    public final String lnkPoUpdate="xpath=//a[text()='A PO Is Required!']";
    
    
    public final String lnkAddActivity="xpath=//a[@data-label='create_activity']";
    public final String lstActivityType="xpath=//select[@data-label='aworktype']";
    public final String lstVerifyDispatchType="xpath=//select[@data-label='A_SELECT_SCHEDULE']";
    public final String lstTaskType="xpath=//select[@data-label='A_TASK_TYPE']";
    public final String lstProblemCode="xpath=//select[@data-label='A_PROBLEM_CODE']";
    public final String lstFieldOpenReason="xpath=//select[@data-label='A_FIELD_OPEN_REASON']";
    public final String lstFieldOpenOption="xpath=//select[@data-label='A_FIELD_OPEN_OPTION']";
    public final String txtEquipID="xpath=//*[@data-label='A_EQUIPMENT_NUMBER']";
    public final String lstSelectTimeSlot="xpath=//select[@data-label='A_SELECT_TIME_SLOT_TYPE']";
    public final String txtProblemSummary="xpath=//*[@data-label='A_SUMMARY']";
    public final String btnBreakFixSubmit="xpath=(//*[@class='button submit' and @type='submit'])[2]";
    public final String btnBackButton="xpath=//*[@id='back-button' and @role='button']"; 
 public HomePage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
        }
 public boolean BreakFixAddActivity() {
	 boolean res= false ;
	 try {
		res=clickOnActivities();
		res=this.ofscEngine.click(lnkAddActivity, "Clicked on AddActivity");
		this.testObj.reportStatus(res, "Successfully clicked on AddActivity", "Failed to clicked On AddActivity");
		Thread.sleep(2000);
		res=this.ofscEngine.selectByName(lstActivityType, "Break/Fix", "select ActivityType");
		this.testObj.reportStatus(res, "Successfully selected ActivityType","Failed to select ActivityType");
		res=this.ofscEngine.selectByName(lstVerifyDispatchType, "THIS IS THE CORRECT DATE FOR THIS ACTIVITY", "select DispatchType");
		this.testObj.reportStatus(res, "Successfully selected DispatchType","Failed to select DispatchType");
		res=this.ofscEngine.selectByName(lstTaskType, "Standard Service", "select TaskType");
		this.testObj.reportStatus(res, "Successfully selected TaskType","Failed to select TaskType");
		res=this.ofscEngine.selectByName(lstProblemCode, "HW01-Poor Copy/Print Quality", "select ProblemCode");
		this.testObj.reportStatus(res, "Successfully selected ProblemCode","Failed to select ProblemCode");
		res=this.ofscEngine.selectByName(lstFieldOpenReason, "Other", "select FieldOpenReason");
		this.testObj.reportStatus(res, "Successfully selected FieldOpenReason","Failed to select FieldOpenReason");
		res=this.ofscEngine.selectByName(lstFieldOpenOption, "Equipment ID", "select FieldOpenOption");
		this.testObj.reportStatus(res, "Successfully selected FieldOpenOption","Failed to select FieldOpenOption");
		Thread.sleep(4000);
		res=this.ofscEngine.type(txtEquipID, "14010151");
		this.testObj.reportStatus(res, "Successfully entered EquipID", "Failed to enter EquipID");	
		res=this.ofscEngine.selectByName(lstSelectTimeSlot, "1-General", "select SelectTimeSlot");
		this.testObj.reportStatus(res, "Successfully selected SelectTimeSlot","Failed to select SelectTimeSlot");
		res=this.ofscEngine.type(txtProblemSummary, "Test");
		this.testObj.reportStatus(res, "Successfully entered Problem summary", "Failed to enter Problem Summary");
		res=this.ofscEngine.click(btnBreakFixSubmit, "Clicked on BreakFixSubmit");
		this.testObj.reportStatus(res, "Successfully clicked on BreakFixSubmit", "Failed to clicked On BreakFixSubmit");
		Thread.sleep(4000);
		res=this.ofscEngine.click(btnBackButton, "Clicked on Backbutton");
		this.testObj.reportStatus(res, "Successfully clicked on Backbutton", "Failed to clicked On Backbutton");
	 } catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
			
		}
return res ;
 }
 
 
    public boolean GlobalSearchInHomePage() {
    	boolean res= false ;
    	try {
    		res=clickOnGlobalSearch();
    		res=clickOnSearchPreference();
    		this.ofscEngine.UncheckMultipleCheckBoxes(chkSearchCheckboxes);
    		res=clickOnSRnumbrCheckbox();
    	} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
			
		}
return res ;
    	
    }
    public boolean SetAppointment(String sChangeTaskType, String sTimeSlotType, String sExpectedTimeSlot) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res=this.ofscEngine.click(lnkSetAppoinment, "click on SetAppoinment");
        		this.testObj.reportStatus(res, "Successfully clicked on SetAppoinment", "Failed to clicked On SetAppoinment");
        		res=this.ofscEngine.click(lstChangeTaskType, "clicked on ChangeTaskType");
        		res=this.ofscEngine.selectTheDestinationValue(ddlChangeTaskType,sChangeTaskType);
        		this.testObj.reportStatus(res, "Successfully selected ChangeTaskType","Failed to select ChangeTaskType");
        		Thread.sleep(4000);
        		res=this.ofscEngine.click(lstSelectTimeSlotType, "clicked on ChangeTaskType");
        		res=this.ofscEngine.selectTheDestinationValue(ddlSelectTimeSlotptions,sTimeSlotType);
        		this.testObj.reportStatus(res, "Successfully selected TimeSlotType","Failed to select TimeSlotType");
        		Thread.sleep(4000);
        		switch(sTimeSlotType) {
        		case "1-General":
        			res=this.ofscEngine.click(lstGeneraTimeSlot, "clicked on TimeSlotType");
            		res=this.ofscEngine.selectTheDestinationValue(ddlGeneralTimeSlot,sExpectedTimeSlot);
            		this.testObj.reportStatus(res, "Successfully selected GeneraTimeSlot","Failed to select GeneraTimeSlot");
            		Thread.sleep(4000);
        			break;
        		case "2-Hourly Business Hours":
        			res=this.ofscEngine.click(lstHourlyTimeSlotBusinessHrs, "clicked on HourlyTimeSlotBusinessHrs");
            		res=this.ofscEngine.selectTheDestinationValue(ddlHourlyTimeSlotBusinessHrs,sExpectedTimeSlot);
            		this.testObj.reportStatus(res, "Successfully selected HourlyTimeSlotBusinessHrs","Failed to select HourlyTimeSlotBusinessHrs");
            		Thread.sleep(4000);
        			break;
        		case "3- Hourly After Hours":
        			res=this.ofscEngine.click(lstHourlyTimeSlotAfterHrs, "clicked on HourlyTimeSlotAfterHrs");
            		res=this.ofscEngine.selectTheDestinationValue(ddlHourlyTimeSlotAfterHrs,sExpectedTimeSlot);
            		this.testObj.reportStatus(res, "Successfully selected HourlyTimeSlotAfterHrs","Failed to select HourlyTimeSlotAfterHrs");
            		Thread.sleep(4000);
        			break;
        		}
        		res=this.ofscEngine.click(btnHideChargeSubmit, "click on submit button");
        		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked On submit");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(lnkBackButton, "click on back button");
        		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked On back button");
        		Thread.sleep(5000);
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Update site reference ");
		
			}
	return res ;
	}
    public boolean ProvideCustomerETA(String sETANotes) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res=this.ofscEngine.click(lnkProvideCustomerETA, "click on lnkProvideCustomerETA button");
        		this.testObj.reportStatus(res, "Successfully clicked on lnkProvideCustomerETA button", "Failed to clicked On lnkProvideCustomerETA button");
        		Thread.sleep(5000);
        		res=this.ofscEngine.type(txtETANotes, sETANotes);
        		this.testObj.reportStatus(res, "Successfully entered sETANotes", "Failed to enter sETANotes");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(btnHideChargeSubmit, "click on submit button");
        		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked On submit");
        		Thread.sleep(5000);     
        		res=this.ofscEngine.click(lnkBackButton, "click on back button");
        		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked On back button");
        		Thread.sleep(5000);
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Update site reference ");
		
			}
	return res ;
	}
    public boolean UpdatePoNumber(String sPONumber) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res=this.ofscEngine.click(lnkPORequired, "click on PO required button");
        		this.testObj.reportStatus(res, "Successfully clicked on PO required button", "Failed to clicked On PO required button");
        		Thread.sleep(5000);
        		res=this.ofscEngine.type(txtEnterPONumber, sPONumber);
        		this.testObj.reportStatus(res, "Successfully entered PONumber", "Failed to enter PONumber");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(btnHideChargeSubmit, "click on submit button");
        		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked On submit");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(lnkBackButton, "click on back button");
        		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked On back button");
        		Thread.sleep(5000);
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Update site reference ");
		
			}
	return res ;
	}
    public boolean UpdateSiteReference(String sSiteReference) {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res=this.ofscEngine.click(lnkUpdateSiteReference, "click on Update Site reference ");
        		this.testObj.reportStatus(res, "Successfully clicked on Update Site reference", "Failed to clicked On Update Site reference");
        		Thread.sleep(5000);
        		res=this.ofscEngine.type(txtUpdateSiteReference, sSiteReference);
        		this.testObj.reportStatus(res, "Successfully entered SiteReference", "Failed to enter SiteReference");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(btnHideChargeSubmit, "click on submit button");
        		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked On submit");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(lnkBackButton, "click on back button");
        		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked On back button");
        		Thread.sleep(5000);
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Update site reference ");
		
			}
	return res ;
	}
    public boolean UpdateCustomerETA() {
		boolean res= false ;
	        try {
	        	Thread.sleep(5000);
	        	res=this.ofscEngine.click(lnkChangeAppoinment, "click on ChangeAppoinment ");
        		this.testObj.reportStatus(res, "Successfully clicked on ChangeAppoinment", "Failed to clicked On ChangeAppoinment");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(radAppoinmentCustomer, "selected customer changed appoinment");
        		this.testObj.reportStatus(res, "Successfully entered SiteReference", "Failed to enter SiteReference");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(btnHideChargeSubmit, "click on submit button");
        		this.testObj.reportStatus(res, "Successfully clicked on submit", "Failed to clicked On submit");
        		Thread.sleep(5000);
        		res=this.ofscEngine.click(lnkBackButton, "click on back button");
        		this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to clicked On back button");
        		Thread.sleep(5000);
	        } catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Update site reference ");
		
			}
	return res ;
	}
    public boolean homePage(String sCustomerName) {
		boolean res= false ;
	        try {
	        	//res=clickOnMenuOptions();
	        	//res=clickOnMyRoute();
	        	//res=this.ofscEngine.RefreshPage();
	        	if(this.ofscEngine.isElementPresent(lnkLeadMyRoute, true)) {
	        		res=this.ofscEngine.click(lnkLeadMyRoute, "click on MyRoute button");
	        		this.testObj.reportStatus(res, "Successfully clicked on MyRoute", "Failed to clicked On MyRoute");	
	        	}
	        	res=clickOnActivities();//19C upgrade
	        	this.testObj.reportStatus(res, "Successfully clicked on Activites", "Failed to clicked on Activies");
	        	// As Per phase II
	        	res=clickOnPendingSR(sCustomerName);
	        	//res=clickOnPendingSR();
	        	this.testObj.reportStatus(res, "Successfully clicked on ServiceRqst", "Failed to clicked On Service Rqst");
	        	Thread.sleep(5000);
	        	/*if(this.ofscEngine.isElementPresent(txtHideCharges, true)) {
	        		res=this.ofscEngine.type(txtHideCharges, "N");
	        		this.testObj.reportStatus(res, "Successfully entered Hide charges", "Failed to enter Hide charges");
	        		Thread.sleep(2000);
	        		res=this.ofscEngine.click(btnHideChargeSubmit, "click on HideChargesubmit button");
	        		this.testObj.reportStatus(res, "Successfully clicked on HideChargesubmit", "Failed to clicked On HideChargesubmit");
	        		res=clickOnPendingSR(sCustomerName);
	        	}
	        	*/
	        	
	        	} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
		
			}
	return res ;
	}
    public boolean ExcesshomePage() {
		boolean res= false ;
	        try {
	        	//res=clickOnMenuOptions();
	        	//res=clickOnMyRoute();
	        	res=clickOnActivities();//19C upgrade
	        	this.testObj.reportStatus(res, "Successfully clicked on Activites", "Failed to clicked on Activies");
	        	res=ExcessDisplayed();
	        	this.testObj.reportStatus(res, "Successfully verified excess", "Failed to verify excess");
	        	} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
		
			}
	return res ;
	}
    public boolean PendingReturnsshomePage() {
		boolean res= false ;
	        try {
	        	//res=clickOnMenuOptions();
	        	//res=clickOnMyRoute();
	        	res=clickOnActivities();//19C upgrade
	        	this.testObj.reportStatus(res, "Successfully clicked on Activites", "Failed to clicked on Activies");
	        	res=ReturnableDisplayed();
	        	this.testObj.reportStatus(res, "Successfully verified Returnable", "Failed to verify Returnable");
	        	} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
		
			}
	return res ;
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
    public boolean clickOnGlobalSearch() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(imgGlobalSearch, "Click on Golbal search");
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Global search");
			}
		return res;	
	}
	public boolean clickOnSearchPreference() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			//if(this.ofscEngine.isElementPresent(txtPartnumbersearch, true)) {
				res=this.ofscEngine.eleDisplayed(txtPartnumbersearch, "Part number search field is verified successfully");
        		System.out.println("Part number search field is verified successfully");
        	//}
			res=this.ofscEngine.click(lnkSearchPreference, "Click on Search preference");
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on search preferences .. ");
			}
		return res;	
	}
	 public boolean clickOnSRnumbrCheckbox() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(chkSRnumber, "Click on SR number checkbox");
				
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on SR number checkbox");
				}
			return res;	
		}
	 public boolean clickOnTasknumbrCheckbox() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(chkTaskNumber, "Click on Tasknumber checkbox");
				
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on Tasknumber checkbox");
				}
			return res;	
		}
	 public boolean clickOnEqIDCheckbox() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(chkEquipmentID, "Click on EquipID Check box");
				
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on EquipID Check box");
				}
			return res;	
		}
	public boolean clickOnActivities() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkActivities, "Clicked on Activities");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Activities .. ");
			}
		return res;	
	}
	
	public boolean ReturnableDisplayed() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.eleDisplayed(lnkReturnable, "Pending returnable displayed");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify returnable element exist");
			}	
		return res;
	}
	public boolean ExcessDisplayed() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.eleDisplayed(lnkExcessList, "Excess list displayed");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify excess element exist");
			}	
		return res;
	}
	public boolean OpenPartOrdersDisplayed() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.eleDisplayed(lnkOpenParts, "Open part order displayed");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify open part order element exist");
			}	
		return res;
	}
	
	
}