package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.WebActionEngine.Mode;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ManagerPage {

	private OFSCWebActionEngine ofscEngine = null;
	
	OFSCTestEngine testObj = null;
    private String getValue=null;
    
    
    
    public final String lnkActivityInfo="xpath=//*[@class='activity-title']";
    public final String lnkEquipmentInfo="xpath=//*[contains(text(),'Equipment Info')]";
    public final String lnkAccountInfo="xpath=//*[contains(text(),'Account Information')]";
    public final String lnkLabourBillingInfo="xpath=//*[contains(text(),'Labor/Billing info')]";
    public final String lnkOtherInfo="xpath=//*[contains(text(),'Other Info')]";
    public final String lnkMeterInfo="xpath=//*[contains(text(),'Meter Info')]";
    public final String lnkStatusDetails="xpath=//*[contains(text(),'Status Details')]";
    public final String lnkEquipment="xpath=(//a[contains(text(),'Equipment')])[2]";
    public final String lnkProviderPreference="xpath=//a[contains(text(),'Provider Preferences')]";
    public final String lnkMessages="xpath=//a[contains(text(),'Messages')]";
    public final String lnkHistory="xpath=//a[contains(text(),'History')]";
    public final String lnkLinksInfo="xpath=//a[contains(text(),'Links')]";
    public final String btnTechInfoBack="xpath=//*[@aria-label='Dispatch Console' and @role='button']";
    
    
    public final String lstHamburgerMenu="xpath=//*[@aria-label='Hamburger menu' and @class='hamburger-button' and @role='button']";
    public final String lnkDispatchConsole="xpath=//a[contains(text(),'Dispatch Console')]";
    public final String lnkResourceMenu="xpath=//a[contains(text(),'Resources')]";
    public final String lnkRoutingMenu="xpath=//a[contains(text(),'Routing')]";
    public final String lnkConfigMenu="xpath=//a[contains(text(),'Configuration')]";
    public final String lnkManageMenu="xpath=//a[contains(text(),'Manage')]";
    public final String lnkMapMenu="xpath=//a[contains(text(),'Maps')]";
    public final String lnkCalenderMenu="xpath=//a[contains(text(),'Calendars')]";
    public final String lnkDashBoardsMenu="xpath=//a[contains(text(),'Dashboards')]";
    public final String lnkQuotaMenu="xpath=//a[contains(text(),'Quota')]";
    public final String lnkSettingsMenu="xpath=//a[contains(text(),'Settings')]";
    public final String lnkSignOutMenu="xpath=//a[contains(text(),'Sign out')]";
    
    
    
    
    public final String btnGlobalSearch="xpath=//*[@aria-label='GlobalSearch' and @role='button']";
    public final String txtSearchActivities="xpath=//*[contains(text(),'Search in activities or parts')]";
    public final String txtSerachVal="xpath=//*[@aria-label='Search in activities or parts' and @type='search']";
    public final String btnInfoSearch="xpath=//*[@class='activity-icon icon']";
    public final String btnSearchPref="xpath=//*[@class='search-bar-navigate']";
    public final String btnLnkSRMove="xpath=//*[contains(text(),'Move')]";
    public final String txtTechName="xpath=//td[@class='search_field_box']/descendant::input[@name='searchInput']";
    public final String chkClickAll="xpath=//td/input[@id='search_all']";
    public final String lnkConfirmTechName="xpath=(//div[@class='CGcontent']/descendant::div)[9]";
    public final String btnConfirmSRAssign="xpath=//input[@name='form_submit']";
    public final String txtTechInfoSearch="xpath=(//td[contains(@class,'toa-view-control-layout-table-cell toa-layout-reset')]/descendant::input[@name='searchInput'])[2]";
    public final String lnkTechNameToSelect="xpath=//div[@class='edt-label edt-empty']/child::span[@class='rtl-text rtl-prov-name']";
    public final String lnkTechResources="xpath=//a[@aria-label='Resources']";
    public final String chkSearchPrefChkbox="xpath=//*[contains(@class,'searchPreferenceCheckbox')][normalize-space(.)='%s']//*[contains(@class,'checkboxset')]//input[@type='checkbox']";
    public final String txtTechNameSearch="xpath=//*[@class='search-key jbf-form-item jbf-form-item--search' and @type='text']";
    public final String tblTechTableInfo="xpath=//*[@class='resource-row-body']";
    public final String lnkTechInfoEdit="xpath=//*[contains(text(),'Information')]";
    public final String lblTechExternalID="xpath=//*[@aria-label='External ID' and @type='text']";
    public final String txtTechMailLogin="xpath=//*[@aria-label='Login' and @type='text']";
    public final String txtTechPassword="xpath=//*[@aria-label='Password' and @type='password']";
    public final String txtTechConfirmPassword="xpath=//*[@aria-label='Confirm Password' and @type='password']";
    public final String txtTechConfirmMail="xpath=//*[@aria-label='Email Address' and @type='email']";
    public final String lstTechWorkingStatus="xpath=//*[@data-label='R_WORKING_STATUS']";
    public final String btnTechConfirmSubmit="xpath=//button[@class='button submit' and @type='submit']";
    public final String lnkResourceBack="xpath=//*[@aria-label='Resources' and @role='link']";
   
    
    public final String btnActions="xpath=//*[@aria-label='Click to view actions' and @type='button']";
    public final String btnAddActivity="xpath=//*[@aria-label='Add Activity' and @name='create_activity']";
    public final String lstActivityType="xpath=//*[@data-label='aworktype']";
    public final String btnDispatchBack="xpath=//*[@aria-label='Dispatch Console' and @role='button']";
    
    
    public final String txtSearchInput="xpath=//*[@name='searchInput' and @data-ctrl-id='63']";
    public final String btnTechImageIcon="xpath=//*[@icon-type='pt1']";
    public final String lnkTechInfo="xpath=//a[@class='info' and @title='Info']";
    public final String lnkTechLocationsInfo="xpath=//span[contains(text(),'Locations')]";
    public final String lnkTechCalenderInfo="xpath=//span[contains(text(),'Resource Calendar')]";
    public final String lnkTechWorkSkills="xpath=//span[contains(text(),'Work Skills')]";
    public final String lnkTechWorkZones="xpath=//span[contains(text(),'Work Zones')]";
    public final String lnkTechInventoryInfo="xpath=//span[contains(text(),'Resource Inventory')]";
    public final String lnkResourceInfo="//span[@aria-label='Resource Info']";//"xpath=//*[@class='back-button' and @role='link']";
    public final String btnResourceInfo="xpath=//*[@id='back-button' and @role='button']";
    public final String lnkDispatchBack="xpath=//*[@aria-label='Dispatch Console' and @role='link']";
    public final String btnTechAddActivity="xpath=//*[contains(text(),'Add Activity')]";
    public final String btnTechCancel="xpath=//*[@name='cancel' and @value='Close']";
    
  
    public final String lstTimeFormat="xpath=//*[@data-label='sutime_fid']";
    public final String lstDateFormat="xpath=//*[@data-label='sudate_fid']";
    public final String txtMobileActivityCount="xpath=//*[@data-label='mobile_activity_count' and @type='number']";
    public final String txtMobileInventoryCount="xpath=//*[@data-label='mobile_inventory_count' and @type='number']";
    public final String txtMobileResourceCount="xpath=//*[@data-label='mobile_provider_count' and @type='number']";
    public final String txtDesignTheme="xpath=//*[@data-label='design_theme']";
    public final String btnTechInfoSubmit="xpath=//button[@class='button submit' and @type='submit']";
    
    public final String lnkEditDashBoard="xpath=//*[@class='menu-button oj-button-icon oj-end' and @slot='endIcon']";
    public final String lnkAddDashBoard="xpath=//*[contains(text(),'Add Dashboard')]";
    public final String btnDashBoardSubmit="xpath=//button[@class='button submit']";
    public final String btnDashBoardCancel="xpath=//button[contains(text(),'Cancel')]";
    
    //public final String btnTechNameSearch="xpath=//span[contains(text(),'%s')]";
    public final String txtTechAddress="xpath=//*[@aria-label='Address ' and @type='text']";
    public final String txtTechCity="xpath=//*[@aria-label='City' and @type='text']";
    public final String txtTechState="xpath=//*[@aria-label='State' and @type='text']";
    public final String txtTechZipCode="xpath=//*[@aria-label='ZIP/Postal Code' and @type='text']";

    public final String btnPhysicalInventory="xpath=//*[contains(@class,'toaGantt-timeChart ui-droppable')]//*[contains(@class,'toaGantt-canvas')]//*[text()='   Physical Inventory']";
    public final String btnPullPhysicalInventory="xpath=//span[contains(text(),'Pull Physical Inventory')]";
    public final String lstSubInventory="xpath=//*[@id='cpf_sub_inventory_dropdown']";
    public final String lst1StVal="xpath=(//select[@id='cpf_sub_inventory_dropdown']//*)[1]";
    public final String btnSendRequest="xpath=//*[@id='cpf_send_pull_request' and @value='Send Request']";
    public final String btnCloseInventory="xpath=//*[@class='ui-button-icon ui-icon ui-icon-closethick']";
    
    public final String btnTechNameInfo="xpath=//span[text()='%s']";
    public final String btnBreakFix="xpath=//*[text()='   5, Break/Fix, Standard Service']";
    public final String btnBreakFixDetail="xpath=//*[text()='Details']";
    public final String lnkServiceImages="xpath=//*[text()='Service Images']";
    public final String btnBack="xpath=//*[@id='back-button' and @role='button']";
    
    
    public final String btnAddNewTech="xpath=(//span[@class='action-bar-item-icon icon-button-add'])[1]";
    public final String txtUserName="xpath=//*[@aria-label='Name/User Name' and @type='text']";
    //public final String txtLogin="xpath=//*[@data-label='ulogin']";
    public final String lstUserType="xpath=//*[@data-label='user_type_id']";
    public final String txtOrgUnit="xpath=//*[@data-label='organization_unit_id']";
    public final String txtVisibleResources="xpath=//*[@data-label='resources']";
    public final String chkOrgUnit="xpath=//span[@class='check']";
    public final String btnCheckSelect="xpath=//*[@data-action='select' and @type='button']";
    
    
    //public final String lnkTechNameInfo="xpath=(//div[@class='edt-children'])[1]//div[@class='edt-row toa-layout-hbox edt-selected']";
    public final String btnListView="xpath=//*[text()='List View']";
    public final String ASLTabel="xpath=//*[@class='toa-grid-body toa-layout-fullfit toa-view-control-control ui-droppable']//tr";
    public final String btnASLDetails="xpath=//*[text()='Details']";
    public final String btnReviewASLRequest="xpath=//a[@data-label='PI-ReviewASLRequestMobility' and @role='button']";
    public final String lstStatusMenu="xpath=//*[@class='statusMenu']";
    public final String btnASLRequestSubmit="xpath=//*[@id='submitASLRequest']";
    public final String btnBackbutton="xpath=//*[@id='back-button']";
    
    
    public ManagerPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }
    
    public boolean ASLManagersApprovals(String sTechName, String sStatusMenu) {
    	boolean res=false;
		try{
			Thread.sleep(5000);
			res=this.ofscEngine.clickUsingEnter(txtSearchInput, "cliked on text field");
			this.testObj.reportStatus(res, "Successfully cliked on SearchInput","Failed to click on SearchInput");
			Thread.sleep(5000);
			res=this.ofscEngine.type(txtSearchInput, sTechName);
			this.testObj.reportStatus(res, "Successfully entered sTechName","Failed to entered sTechName");
			Thread.sleep(5000);
			res=this.ofscEngine.click(String.format(btnTechNameInfo,sTechName), "cliked on Techname");
			this.testObj.reportStatus(res, "Successfully cliked on TechImageIcon","Failed to click on TechImageIcon");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnListView, "clicked on Listview button");
			this.testObj.reportStatus(res, "Successfully cliked on Listview","Failed to click on Listview");
			Thread.sleep(5000);
			this.ofscEngine.ClickOnPendingASL(ASLTabel);
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnASLDetails, "clicked on btnASLDetails");
			this.testObj.reportStatus(res, "Successfully cliked on btnASLDetails","Failed to click on btnASLDetails");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnReviewASLRequest, "clicked on btnReviewASLRequest");
			this.testObj.reportStatus(res, "Successfully cliked on btnReviewASLRequest","Failed to click on btnReviewASLRequest");
			Thread.sleep(5000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.selectByName(lstStatusMenu, sStatusMenu, "select status menu");
		    this.testObj.reportStatus(res,"Successfully selected status menu","Failed to selected status menu");
		    Thread.sleep(2000);
		    res=this.ofscEngine.click(btnASLRequestSubmit, "clicked on btnASLRequestSubmit");
			this.testObj.reportStatus(res, "Successfully cliked on btnASLRequestSubmit","Failed to click on btnASLRequestSubmit");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnBackbutton, "clicked on btnBackbutton");
			this.testObj.reportStatus(res, "Successfully cliked on btnBackbutton","Failed to click on btnBackbutton");
			Thread.sleep(5000);
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Select Inventory Adjustment");
		}
	return res;	
}
    public boolean AttachFilesValidation(String sTechName){
		boolean res=false;
		try{
		Thread.sleep(5000);
		res=this.ofscEngine.clickUsingEnter(txtSearchInput, "cliked on text field");
		this.testObj.reportStatus(res, "Successfully cliked on SearchInput","Failed to click on SearchInput");
		Thread.sleep(5000);
		res=this.ofscEngine.type(txtSearchInput, sTechName);
		this.testObj.reportStatus(res, "Successfully entered sTechName","Failed to entered sTechName");
		Thread.sleep(5000);
		res=this.ofscEngine.click(String.format(btnTechNameInfo,sTechName), "cliked on Techname");
		this.testObj.reportStatus(res, "Successfully cliked on Techname","Failed to click on Techname");
		Thread.sleep(2000);
		res=this.ofscEngine.click(btnBreakFix, "cliked on BreakFix");
		this.testObj.reportStatus(res, "Successfully cliked on BreakFix","Failed to click on BreakFix");
		Thread.sleep(2000);
		res=this.ofscEngine.click(btnBreakFixDetail, "cliked on BreakFixDetail");
		this.testObj.reportStatus(res, "Successfully cliked on BreakFixDetail","Failed to click on BreakFixDetail");
		Thread.sleep(5000);
		res=this.ofscEngine.click(lnkServiceImages, "cliked on ServiceImages");
		this.testObj.reportStatus(res, "Successfully cliked on ServiceImages and Images verified","Failed to click on ServiceImages and Images verified");
		Thread.sleep(5000);
		res=this.ofscEngine.click(btnBack, "cliked on back button");
		this.testObj.reportStatus(res, "Successfully cliked on ServiceImages","Failed to click on BreakFix");
		Thread.sleep(5000);
		res=this.ofscEngine.click(btnBack, "cliked on back button");
		this.testObj.reportStatus(res, "Successfully cliked on ServiceImages","Failed to click on BreakFix");
		Thread.sleep(5000);
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean PhysicalInventoryCreation(String sTechName){
		boolean res=false;
		try{
			res=this.ofscEngine.type(txtSearchInput, sTechName);
			this.testObj.reportStatus(res, "Successfully entered sTechName","Failed to entered sTechName");
			Thread.sleep(5000);
			res=this.ofscEngine.click(String.format(btnTechNameInfo,sTechName), "clicked on techname");
			this.testObj.reportStatus(res, "Successfully cliked on techname","Failed to click on techname");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnActions, "clicked on action button");
			this.testObj.reportStatus(res, "Successfully cliked on Actions","Failed to click on actions");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnAddActivity, "clicked on AddActivity button");
			this.testObj.reportStatus(res, "Successfully cliked on Actions","Failed to click on actions");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lstActivityType, "Activity type verified successfully");
			this.testObj.reportStatus(res, "Successfully ActivityType verified","Failed to verify ActivityType");
			Thread.sleep(2000);
			res=this.ofscEngine.selectByName(lstActivityType, "Physical Inventory", "select required field");
			this.testObj.reportStatus(res, "Successfully selected Service Reqst", "Failed to selected Service Reqst");
			Thread.sleep(2000);
			res=this.ofscEngine.type(txtTechAddress, "oakbrook");
			this.testObj.reportStatus(res, "Successfully entered sTechAdress","Failed to entered sTechAdress");
			Thread.sleep(2000);
			res=this.ofscEngine.type(txtTechCity, "Malvern");
			this.testObj.reportStatus(res, "Successfully entered TechCity","Failed to entered TechCity");
			Thread.sleep(2000);
			res=this.ofscEngine.type(txtTechState, "PA");
			this.testObj.reportStatus(res, "Successfully entered TechState","Failed to entered TechState");
			Thread.sleep(2000);
			res=this.ofscEngine.type(txtTechZipCode, "19355");
			this.testObj.reportStatus(res, "Successfully entered ZipCode","Failed to entered ZipCode");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnTechInfoSubmit, "clicked on submit");
			this.testObj.reportStatus(res, "Successfully cliked on submit","Failed to click on submit");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnPhysicalInventory, "clicked on physical Inventory");
			this.testObj.reportStatus(res, "Successfully cliked on physical Inventory","Failed to click on physical Inventory");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnPullPhysicalInventory, "clicked on pull physical inventory");
			this.testObj.reportStatus(res, "Successfully cliked on pull physical inventory","Failed to click on pull physical inventory");
			Thread.sleep(2000);
			ofscEngine.switchToLastFrame();
			res=this.ofscEngine.click(btnSendRequest, "clicked on sendrequest");
			this.testObj.reportStatus(res, "Successfully cliked on sendrequest","Failed to click on sendrequest");
			Thread.sleep(2000);
			ofscEngine.switchToDefaultFrame();
			res=this.ofscEngine.click(btnCloseInventory, "clicked on CloseInventory");
			this.testObj.reportStatus(res, "Successfully cliked on CloseInventory","Failed to click on CloseInventory");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkTechResources, "cliked on Resources");
		    this.testObj.reportStatus(res, "Successfully cliked on resources","Failed to click on resources");
		    Thread.sleep(5000);
		    res=this.ofscEngine.type(txtTechNameSearch, sTechName);
		    this.testObj.reportStatus(res, "Successfully entered on TechName","Failed to enter TechName");
		    Thread.sleep(5000);
		    res=this.ofscEngine.click(tblTechTableInfo, "cliked on table Resource");
		    this.testObj.reportStatus(res, "Successfully cliked on table","Failed to click on table");
		    Thread.sleep(5000);
		    res=this.ofscEngine.click(lnkTechInfoEdit, "cliked on Edit button of tech info");
		    this.testObj.reportStatus(res, "Successfully cliked on TechEdit","Failed to click on TechEdit");
		    Thread.sleep(5000);
		    String Val=this.ofscEngine.getAttributeValue(txtTechMailLogin,"value");
		    String[] TechName=Val.split("@");
		    String sFiedTechName=TechName[0]+"@falsericoh-usa.com";
		    Thread.sleep(2000);
		    res=this.ofscEngine.type(txtTechMailLogin, sFiedTechName);
		    this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
		    Thread.sleep(2000);
		    res=this.ofscEngine.type(txtTechPassword, "1234");
		    this.testObj.reportStatus(res, "Successfully entered on TechPassword","Failed to enter TechPassword");
		    Thread.sleep(2000);
		    res=this.ofscEngine.type(txtTechConfirmPassword, "1234");
		    this.testObj.reportStatus(res, "Successfully entered on ConfirmPassword","Failed to enter ConfirmPassword");
		    Thread.sleep(2000);
		    res=this.ofscEngine.selectByName(lstTechWorkingStatus, "Not working", "select Not working status");
		    this.testObj.reportStatus(res,"Successfully selected Not working","Failed to selected Not working");
		    Thread.sleep(2000);
		    res=this.ofscEngine.type(txtTechConfirmMail, sFiedTechName);
		    this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
		    Thread.sleep(5000);
		    res=this.ofscEngine.click(btnTechConfirmSubmit, "cliked on submit button");
		    this.testObj.reportStatus(res, "Successfully cliked on submit","Failed to click on submit");
		    Thread.sleep(5000);
		    res=this.ofscEngine.click(lnkResourceBack, "cliked on back button");
		    this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean AddDashBoardValidation(){
		boolean res=false;
		try{
			res=this.ofscEngine.click(lstHamburgerMenu, "cliked on HamburgerMenu");
			this.testObj.reportStatus(res, "Successfully cliked on HamburgerMenu","Failed to click on HamburgerMenu");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(lnkDashBoardsMenu, "DashBoardsMenu link verified successfully");
			this.testObj.reportStatus(res, "Successfully DashBoardsMenu verified","Failed to verify DashBoardsMenu");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkDashBoardsMenu, "cliked on DashBoardsMenu");
			this.testObj.reportStatus(res, "Successfully cliked on DashBoardsMenu","Failed to click on DashBoardsMenu");
			Thread.sleep(5000);	
			res=this.ofscEngine.click(lnkEditDashBoard, "cliked on EditDashBoard");
			this.testObj.reportStatus(res, "Successfully cliked on EditDashBoard","Failed to click on EditDashBoard");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkAddDashBoard, "cliked on AddDashBoard");
			this.testObj.reportStatus(res, "Successfully cliked on AddDashBoard","Failed to click on AddDashBoard");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(btnDashBoardSubmit, "DashBoardSubmit verified successfully");
			this.testObj.reportStatus(res, "Successfully DashBoardSubmit verified","Failed to verify DashBoardSubmit");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnDashBoardCancel, "cliked on DashBoardCancel");
			this.testObj.reportStatus(res, "Successfully cliked on DashBoardCancel","Failed to click on DashBoardCancel");
			Thread.sleep(2000);
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean SettingFiledsValidation(){
		boolean res=false;
		try{
			res=this.ofscEngine.click(lstHamburgerMenu, "cliked on HamburgerMenu");
			this.testObj.reportStatus(res, "Successfully cliked on HamburgerMenu","Failed to click on HamburgerMenu");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(lnkSettingsMenu, "Settings link verified successfully");
			this.testObj.reportStatus(res, "Successfully SettingsMenu verified","Failed to verify SettingsMenu");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkSettingsMenu, "cliked on Settings");
			this.testObj.reportStatus(res, "Successfully cliked on Settings","Failed to click on Settings");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lstTimeFormat, "TimeFormat verified successfully");
			this.testObj.reportStatus(res, "Successfully lstTimeFormat verified","Failed to verify TimeFormat");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(lstDateFormat, "DateFormat verified successfully");
			this.testObj.reportStatus(res, "Successfully DateFormat verified","Failed to verify DateFormat");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(txtMobileActivityCount, "MobileActivityCount verified successfully");
			this.testObj.reportStatus(res, "Successfully MobileActivityCount verified","Failed to verify MobileActivityCount");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(txtMobileInventoryCount, "MobileInventoryCount verified successfully");
			this.testObj.reportStatus(res, "Successfully MobileInventoryCount verified","Failed to verify MobileInventoryCount");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(txtMobileResourceCount, "MobileResourceCount verified successfully");
			this.testObj.reportStatus(res, "Successfully MobileResourceCount verified","Failed to verify MobileResourceCount");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(txtDesignTheme, "DesignTheme verified successfully");
			this.testObj.reportStatus(res, "Successfully DesignTheme verified","Failed to verify DesignTheme");
			Thread.sleep(2000);
			res=this.ofscEngine.eleDisplayed(btnTechInfoSubmit, "btnSubmit verified successfully");
			this.testObj.reportStatus(res, "Successfully btnSubmit verified","Failed to verify btnSubmit");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnDispatchBack, "clicked on back button");
			this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
			Thread.sleep(5000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
			}
			return res;
		
			}
    public boolean AdditionalInfoFeaturesValidation(String sTechName){
		boolean res=false;
		try{
			Thread.sleep(5000);
			res=this.ofscEngine.clickUsingEnter(txtSearchInput, "cliked on text field");
			this.testObj.reportStatus(res, "Successfully cliked on SearchInput","Failed to click on SearchInput");
			Thread.sleep(5000);
			res=this.ofscEngine.type(txtSearchInput, sTechName);
			this.testObj.reportStatus(res, "Successfully entered sTechName","Failed to entered sTechName");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnTechImageIcon, "clicked on TechImage button");
			this.testObj.reportStatus(res, "Successfully cliked on TechImageIcon","Failed to click on TechImageIcon");
			Thread.sleep(5000);
			res=this.ofscEngine.click(lnkTechInfo, "clicked on TechImage button");
			this.testObj.reportStatus(res, "Successfully cliked on TechInfo","Failed to click on TechInfo");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lnkTechLocationsInfo, "Locations info verified successfully");
			res=this.ofscEngine.click(lnkTechLocationsInfo, "clicked on Locations button");
			this.testObj.reportStatus(res, "Successfully TechLocationsInfo verified","Failed to verify TechLocationsInfo");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkResourceInfo, "clicked on ResouceInfo button");
			this.testObj.reportStatus(res, "Successfully cliked on ResouceInfo button","Failed to click on ResouceInfo button");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lnkTechCalenderInfo, "TechCalender info verified successfully");
			res=this.ofscEngine.click(lnkTechCalenderInfo, "clicked on CalenderInfo button");
			this.testObj.reportStatus(res, "Successfully TechCalender verified","Failed to verify TechCalender");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkResourceInfo, "clicked on ResouceInfo button");
			this.testObj.reportStatus(res, "Successfully cliked on ResouceInfo button","Failed to click on ResouceInfo button");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lnkTechInfoEdit, "TechInfo verified successfully");
			res=this.ofscEngine.click(lnkTechInfoEdit, "clicked on TechInfo button");
			this.testObj.reportStatus(res, "Successfully TechInfoEdit verified","Failed to verify TechInfoEdit");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnResourceInfo, "clicked on ResouceInfo button");
			this.testObj.reportStatus(res, "Successfully cliked on ResouceInfo button","Failed to click on ResouceInfo button");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lnkTechWorkSkills, "TechWorkSkills info verified successfully");
			res=this.ofscEngine.click(lnkTechWorkSkills, "clicked on TechWorkSkills button");
			this.testObj.reportStatus(res, "Successfully TechWorkSkills verified","Failed to verify TechWorkSkills");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkResourceInfo, "clicked on ResouceInfo button");
			this.testObj.reportStatus(res, "Successfully cliked on ResouceInfo button","Failed to click on ResouceInfo button");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lnkTechWorkZones, "TechWorkZones info verified successfully");
			res=this.ofscEngine.click(lnkTechWorkZones, "clicked on TechWorkZones button");
			this.testObj.reportStatus(res, "Successfully TechWorkZones verified","Failed to verify TechWorkZones");
			Thread.sleep(2000);
			res=this.ofscEngine.click(lnkResourceInfo, "clicked on ResouceInfo button");
			this.testObj.reportStatus(res, "Successfully cliked on ResouceInfo button","Failed to click on ResouceInfo button");
			Thread.sleep(5000);
			res=this.ofscEngine.eleDisplayed(lnkTechInventoryInfo, "TechInventoryInfo verified successfully");
			res=this.ofscEngine.click(lnkTechInventoryInfo, "clicked on TechInventoryInfo button");
			this.testObj.reportStatus(res, "Successfully TechInventory verified","Failed to verify TechInventory");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnResourceInfo, "clicked on ResouceInfo button");
			this.testObj.reportStatus(res, "Successfully cliked on ResouceInfo button","Failed to click on ResouceInfo button");
			Thread.sleep(5000);
			//res=this.ofscEngine.click(lnkDispatchBack, "clicked on dispatchback button");
			//this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
			//Thread.sleep(5000);
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    
    public boolean AddActivityResourceValidation(String sTechName){
		boolean res=false;
		try{
			Thread.sleep(5000);
			res=this.ofscEngine.clickUsingEnter(txtSearchInput, "cliked on text field");
			this.testObj.reportStatus(res, "Successfully cliked on SearchInput","Failed to click on SearchInput");
			Thread.sleep(5000);
			res=this.ofscEngine.type(txtSearchInput, sTechName);
			this.testObj.reportStatus(res, "Successfully entered sTechName","Failed to entered sTechName");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnTechImageIcon, "clicked on TechImage button");
			this.testObj.reportStatus(res, "Successfully cliked on TechImageIcon","Failed to click on TechImageIcon");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnTechAddActivity, "clicked on AddActivity button");
			this.testObj.reportStatus(res, "Successfully cliked on TechAddActivity","Failed to click on TechAddActivity");
			Thread.sleep(5000);
			res=this.ofscEngine.click(btnDispatchBack, "clicked on back button");
			this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
			Thread.sleep(5000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
			}
			return res;
		
			}
    public boolean AddActivityManagerValidation(){
		boolean res=false;
		try{
		res=this.ofscEngine.click(btnActions, "clicked on action button");
		this.testObj.reportStatus(res, "Successfully cliked on Actions","Failed to click on actions");
		Thread.sleep(5000);
		res=this.ofscEngine.click(btnAddActivity, "clicked on AddActivity button");
		this.testObj.reportStatus(res, "Successfully cliked on Actions","Failed to click on actions");
		Thread.sleep(5000);
		res=this.ofscEngine.eleDisplayed(lstActivityType, "Activity type verified successfully");
		this.testObj.reportStatus(res, "Successfully ActivityType verified","Failed to verify ActivityType");
		Thread.sleep(5000);
		res=this.ofscEngine.click(btnDispatchBack, "clicked on DispatchControl button");
		this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
		Thread.sleep(5000);
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean ViewActivityPageValidation(String SRNumber){
		boolean res=false;
		try{
		  res=this.ofscEngine.click(btnGlobalSearch, "clicked on global search button");
		  this.testObj.reportStatus(res, "Successfully cliked on global search","Failed to click on global search");
		  res=this.ofscEngine.click(txtSearchActivities, "cliked on text field");
		  this.testObj.reportStatus(res, "Successfully cliked on search activities","Failed to click on search activities");
		  res=this.ofscEngine.type(txtSerachVal, SRNumber);
		  this.testObj.reportStatus(res, "Successfully entered SR number","Failed to entered SR number");
		  res=this.ofscEngine.clickUsingEnter(txtSerachVal, "cliked on text field");
		  this.testObj.reportStatus(res, "Successfully cliked on search filed","Failed to click on search field");
		  Thread.sleep(5000);
		  res=this.ofscEngine.click(lnkActivityInfo, "cliked on SR text field");
		  this.testObj.reportStatus(res, "Successfully cliked on SR Activity","Failed to click on SR Activity");
		  Thread.sleep(5000);
		  res=this.ofscEngine.eleDisplayed(lnkEquipmentInfo, "EquipmentInfo link verified successfully");
		  this.testObj.reportStatus(res, "Successfully EquipmentInfo verified","Failed to verify EquipmentInfo");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkAccountInfo, "AccountInfo link verified successfully");
		  this.testObj.reportStatus(res, "Successfully AccountInfo verified","Failed to verify AccountInfo");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkLabourBillingInfo, "LabourBillingInfo link verified successfully");
		  this.testObj.reportStatus(res, "Successfully LabourBilling verified","Failed to verify LabourBilling");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkOtherInfo, "OtherInfo link verified successfully");
		  this.testObj.reportStatus(res, "Successfully OtherInfo verified","Failed to verify OtherInfo");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkMeterInfo, "MeterInfo link verified successfully");
		  this.testObj.reportStatus(res, "Successfully MeterInfo verified","Failed to verify MeterInfo");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkStatusDetails, "StatusDetails link verified successfully");
		  this.testObj.reportStatus(res, "Successfully StatusDetails verified","Failed to verify StatusDetails");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkEquipment, "Equipment link verified successfully");
		  this.testObj.reportStatus(res, "Successfully Equipment verified","Failed to verify Equipment");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkProviderPreference, "ProviderPreference link verified successfully");
		  this.testObj.reportStatus(res, "Successfully ProviderPreference verified","Failed to verify ProviderPreference");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkMessages, "Messages link verified successfully");
		  this.testObj.reportStatus(res, "Successfully Messages verified","Failed to verify Messages");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkHistory, "History link verified successfully");
		  this.testObj.reportStatus(res, "Successfully History verified","Failed to verify History");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkLinksInfo, "LinksInfo link verified successfully");
		  this.testObj.reportStatus(res, "Successfully LinksInfo verified","Failed to verify LinksInfo");
		  Thread.sleep(2000);
		  res=this.ofscEngine.click(btnTechInfoBack, "clicked on back button successfully");
		  this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
		  Thread.sleep(2000);
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean HamburgerValidation(){
		boolean res=false;
		try{
		  Thread.sleep(5000);
		  res=this.ofscEngine.click(lstHamburgerMenu, "cliked on HamburgerMenu");
		  this.testObj.reportStatus(res, "Successfully cliked on HamburgerMenu","Failed to click on HamburgerMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkDispatchConsole, "DispatchConsole link verified successfully");
		  this.testObj.reportStatus(res, "Successfully DispatchConsole verified","Failed to verify DispatchConsole");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkResourceMenu, "Resource link verified successfully");
		  this.testObj.reportStatus(res, "Successfully ResourceMenu verified","Failed to verify ResourceMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkRoutingMenu, "Routing link verified successfully");
		  this.testObj.reportStatus(res, "Successfully RoutingMenu verified","Failed to verify RoutingMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkConfigMenu, "Config link verified successfully");
		  this.testObj.reportStatus(res, "Successfully ResourceMenu verified","Failed to verify ResourceMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkManageMenu, "Manage link verified successfully");
		  this.testObj.reportStatus(res, "Successfully ManageMenu verified","Failed to verify ManageMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkMapMenu, "Map link verified successfully");
		  this.testObj.reportStatus(res, "Successfully MapMenu verified","Failed to verify MapMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkCalenderMenu, "Calender link verified successfully");
		  this.testObj.reportStatus(res, "Successfully CalenderMenu verified","Failed to verify CalenderMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkDashBoardsMenu, "DashBoards link verified successfully");
		  this.testObj.reportStatus(res, "Successfully DashBoardsMenu verified","Failed to verify DashBoardsMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkQuotaMenu, "Quota link verified successfully");
		  this.testObj.reportStatus(res, "Successfully QuotaMenu verified","Failed to verify QuotaMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkSettingsMenu, "Settings link verified successfully");
		  this.testObj.reportStatus(res, "Successfully SettingsMenu verified","Failed to verify SettingsMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.eleDisplayed(lnkSignOutMenu, "SignOut link verified successfully");
		  this.testObj.reportStatus(res, "Successfully SignOutMenu verified","Failed to verify SignOutMenu");
		  Thread.sleep(2000);
		  res=this.ofscEngine.click(lnkTechResources, "SearchInfo");
		  
		  res=this.ofscEngine.clickUsingEnter(lnkTechResources, "cliked on SR text field");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean AssignSRToTech(String SRNumber, String sTechName){
		boolean res=false;
		try{
			  res=this.ofscEngine.click(btnGlobalSearch, "SearchInfo");
			  this.testObj.reportStatus(res, "Successfully cliked on global search","Failed to click on global search");
			  res=this.ofscEngine.click(txtSearchActivities, "cliked on textfield");
			  this.testObj.reportStatus(res, "Successfully cliked on search activities","Failed to click on search activities");
			  res=this.ofscEngine.type(txtSerachVal, SRNumber);
			  this.testObj.reportStatus(res, "Successfully entered SR number","Failed to entered SR number");
			  res=this.ofscEngine.clickUsingEnter(txtSerachVal, "cliked on SR text field");
			  this.testObj.reportStatus(res, "Successfully cliked on search filed","Failed to click on search field");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnInfoSearch, "cliked on SR Activity");
			  this.testObj.reportStatus(res, "Successfully cliked on SR Activity","Failed to click on SR Activity");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnLnkSRMove, "cliked on move button");
			  this.testObj.reportStatus(res, "Successfully cliked on SR move button","Failed to click on SR move button");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(chkClickAll, "selected All checkbox button");
			  this.testObj.reportStatus(res,"Successfully selected All checkbox","Failed to selected all checkbox");
			  res=this.ofscEngine.type(txtTechName, sTechName);
			  this.testObj.reportStatus(res, "Successfully entered tech name","Failed to entered tech name");
			  Thread.sleep(10000);
			  res=this.ofscEngine.click(lnkConfirmTechName, "cliked on TechName");
			  this.testObj.reportStatus(res, "Successfully cliked on Techname","Failed to click on techname");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnConfirmSRAssign, "cliked on SR confirm OK button");
			  this.testObj.reportStatus(res, "Successfully cliked on ok button","Failed to click on ok button");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkTechResources, "cliked on Resources");
			  this.testObj.reportStatus(res, "Successfully cliked on resources","Failed to click on resources");
			  Thread.sleep(5000);
			  res=this.ofscEngine.type(txtTechNameSearch, sTechName);
			  this.testObj.reportStatus(res, "Successfully entered on TechName","Failed to enter TechName");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(tblTechTableInfo, "cliked on table Resource");
			  this.testObj.reportStatus(res, "Successfully cliked on table","Failed to click on table");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkTechInfoEdit, "cliked on Edit button of tech info");
			  this.testObj.reportStatus(res, "Successfully cliked on TechEdit","Failed to click on TechEdit");
			  Thread.sleep(5000);
			  String Val=this.ofscEngine.getAttributeValue(txtTechMailLogin,"value");
			  String[] TechName=Val.split("@");
			  String sFiedTechName=TechName[0]+"@falsericoh-usa.com";
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechMailLogin, sFiedTechName);
			  this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechPassword, "1234");
			  this.testObj.reportStatus(res, "Successfully entered on TechPassword","Failed to enter TechPassword");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechConfirmPassword, "1234");
			  this.testObj.reportStatus(res, "Successfully entered on ConfirmPassword","Failed to enter ConfirmPassword");
			  Thread.sleep(2000);
			  res=this.ofscEngine.selectByName(lstTechWorkingStatus, "Not working", "select Not working status");
			  this.testObj.reportStatus(res,"Successfully selected Not working","Failed to selected Not working");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechConfirmMail, sFiedTechName);
			  this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnTechConfirmSubmit, "cliked on submit button");
			  this.testObj.reportStatus(res, "Successfully cliked on submit","Failed to click on submit");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkResourceInfo, "cliked on back button");
			  this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    
    public boolean ModifyTechWorkingStatusForASAgeing(String sTechName){
		boolean res=false;
		try{ 	
			  res=this.ofscEngine.click(lnkTechResources, "cliked on Resources");
			  this.testObj.reportStatus(res, "Successfully cliked on resources","Failed to click on resources");
			  Thread.sleep(5000);
			  res=this.ofscEngine.type(txtTechNameSearch, sTechName);
			  this.testObj.reportStatus(res, "Successfully entered on TechName","Failed to enter TechName");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(tblTechTableInfo, "cliked on table Resource");
			  this.testObj.reportStatus(res, "Successfully cliked on table","Failed to click on table");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkTechInfoEdit, "cliked on Edit button of tech info");
			  this.testObj.reportStatus(res, "Successfully cliked on TechEdit","Failed to click on TechEdit");
			  Thread.sleep(5000);
			  String sExternalID=this.ofscEngine.getAttributeValue(lblTechExternalID,"value");
			  System.out.println(sTechName+" ExternalId is:"+sExternalID);
			  Thread.sleep(2000);
			  String Val=this.ofscEngine.getAttributeValue(txtTechMailLogin,"value");
			  String[] TechName=Val.split("@");
			  String sFiedTechName=TechName[0]+"@falsericoh-usa.com";
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechMailLogin, sFiedTechName);
			  this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechPassword, "1234");
			  this.testObj.reportStatus(res, "Successfully entered on TechPassword","Failed to enter TechPassword");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechConfirmPassword, "1234");
			  this.testObj.reportStatus(res, "Successfully entered on ConfirmPassword","Failed to enter ConfirmPassword");
			  Thread.sleep(2000);
			  String sWorkingStatus=this.ofscEngine.getAttributeValue(lstTechWorkingStatus,"value");
			  System.out.println("Tech working status is:"+sWorkingStatus);
			  Thread.sleep(1000);
			  if(sWorkingStatus.equalsIgnoreCase("not_working")) {
				  res=this.ofscEngine.selectByName(lstTechWorkingStatus, "Working", "select working status");
				  this.testObj.reportStatus(res,"Successfully selected working","Failed to selected working");
				  Thread.sleep(2000);
			  }
			  else {
				  res=this.ofscEngine.selectByName(lstTechWorkingStatus, "Not working", "select Not working status");
				  this.testObj.reportStatus(res,"Successfully selected Not working","Failed to selected Not working");
				  Thread.sleep(2000); 
			  }
			  res=this.ofscEngine.type(txtTechConfirmMail, sFiedTechName);
			  this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnTechConfirmSubmit, "cliked on submit button");
			  this.testObj.reportStatus(res, "Successfully cliked on submit","Failed to click on submit");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkResourceBack, "cliked on back button");
			  this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");	
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
    public boolean CreatingTechForASAgeing(){
		boolean res=false;
		try{ 
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkTechResources, "cliked on Resources");
			  this.testObj.reportStatus(res, "Successfully cliked on resources","Failed to click on resources");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnAddNewTech, "Clicked on creation of new tech");
			  this.testObj.reportStatus(res, "Successfully Clicked on creation of new tech","Failed to Clicked on creation of new tech");
			  Thread.sleep(5000);
			  String sTech=ofscEngine.generateRandomString(12,Mode.ALPHA);
			  String sFiedTechName=sTech+"@abxu.com";
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtUserName, sFiedTechName);
			  this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechMailLogin, sFiedTechName);
			  this.testObj.reportStatus(res, "Successfully entered on Modified techmailID","Failed to enter Modified techmailID");
			  Thread.sleep(2000);
			  res=this.ofscEngine.selectByName(lstUserType, "Mobile Worker (MPF)", "select Not working status");
			  this.testObj.reportStatus(res,"Successfully selected Not working","Failed to selected Not working");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechPassword, "1234");
			  this.testObj.reportStatus(res, "Successfully entered on TechPassword","Failed to enter TechPassword");
			  Thread.sleep(2000);
			  res=this.ofscEngine.type(txtTechConfirmPassword, "1234");
			  this.testObj.reportStatus(res, "Successfully entered on ConfirmPassword","Failed to enter ConfirmPassword");
			  Thread.sleep(2000);		  
			  res=this.ofscEngine.click(txtOrgUnit, "Clicked on Organization unit");
			  this.testObj.reportStatus(res, "Successfully clicked on Organization unit","Failed clicked on Organization unit");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(chkOrgUnit, "Clicked on Organization");
			  this.testObj.reportStatus(res, "Successfully clicked on Organization","Failed clicked on Organization");
			  Thread.sleep(2000);
			  res=this.ofscEngine.click(btnCheckSelect, "Clicked on select button");
			  this.testObj.reportStatus(res, "Successfully clicked on select button","Failed clicked on select button");
			  Thread.sleep(2000);
			  res=this.ofscEngine.click(txtVisibleResources, "Clicked on visible resource");
			  this.testObj.reportStatus(res, "Successfully clicked on visible resource","Failed clicked on visible resource");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(chkOrgUnit, "Clicked on Organization");
			  this.testObj.reportStatus(res, "Successfully clicked on Organization","Failed clicked on Organization");
			  Thread.sleep(2000);
			  res=this.ofscEngine.click(btnCheckSelect, "Clicked on select button");
			  this.testObj.reportStatus(res, "Successfully clicked on select button","Failed clicked on select button");
			  Thread.sleep(2000);
			  res=this.ofscEngine.click(btnTechConfirmSubmit, "cliked on submit button");
			  this.testObj.reportStatus(res, "Successfully cliked on submit","Failed to click on submit");
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(lnkResourceBack, "cliked on back button");
			  this.testObj.reportStatus(res, "Successfully cliked on back button","Failed to click on back button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the EnRoute process-OFSC ");
		}
		return res;
	
		}
}