package ricoh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;


public class TransferPartsPage {
	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
    
    
    public final String txtItemSerachFiled="xpath=//*[@id='searchKeyword1' and @type='search']";
    
    
    
    public final String lnkParts="xpath=//a[contains(text(),'Parts')]";
    public final String lnkSearchParts="xpath=//a[contains(text(),'Search Parts')]";
    public final String lnkTransferParts="xpath=//a[contains(text(),'Transfer Parts')]";
    public final String txtBoxSearchParts="xpath=//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='searchKeyword']";
    public final String txtBoxSearchPartsTransfer="xpath=//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_transferPartsSearchString_inner']";
    public final String btnSearchParts="xpath=//div[@class='cp_field_value_inner_wrapper']/following::input[@id='searchButton']";
    public final String btnSearchPartsTransfer="xpath=//div[@class='cp_field_value_inner_wrapper']/following::input[@id='cpf_transferPartsSearch']";
    public final String clickInstallParts="xpath=(//span[@id='installLink'])[1]";
    public final String clickOn1stPlusInstallQnatyty="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[1]/.";
    public final String clickOn1stPlusInstallQnatytyTransfer="xpath=//*[@id='cpf_button_increase' and @value='+']";
    public final String clickOn2ndPlusInstallQnatytyTransfer="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[2]";
    public final String clickOn3rdPlusInstallQnatytyTransfer="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[3]";
    public final String clickOn1stsavebtn="xpath=(//div[@class='cp_wrapper']/descendant::input[@id='submitInstallQty'])[1]";
    public final String clickOnSubmitAll="xpath=//input[@id='submitAllParts']/..";
    public final String clickOnSubmitTransfer="xpath=//*[@id='submitTransferParts' and @value='Submit']";
    public final String clickOnDetails="xpath=//div[text()='Details']";
    public final String lstSourceInner="xpath=//select[@id='cpf_ResourceGroupSelector_inner']";
    public final String lstDestinationInner="xpath=//select[@id='cpf_destStockSelected_inner']";
    public final String rbtnSubinvtry="xpath=//input[@id='cpf_destResourceTypeSelected_inner_stock']";
    public final String ddnSubDestination="xpath=//*[@id='cpf_destStockSelected_inner']";
    public final String ddnTechDestination="xpath=//*[@id='cpf_destResourceSelected_inner']";
    public final String ddnTechDestinationOptions="xpath=//*[@id='cpf_destResourceSelected_inner']//option";
    public final String rbtnTechnician="xpath=//input[@id='cpf_destResourceTypeSelected_inner_tech']";
    public final String rbtnMyGroup="xpath=//input[@id='cpf_techinicianFilterVisibility_My']";
    public final String clkOnDest="xpath=(//select[@class='cp_field_dropdown_component form-item'])[2]";
    public final String rbtnAllGroup="xpath=//input[@id='cpf_techinicianFilterVisibility_All']";
    public final String btnInventories="xpath=//*[@id='back-button']";
    
    
    
    public final String lblItemNumber="xpath=//*[@data-ofsc-entity='inventory']//td[1]/div";
    public final String lblSubInvName="xpath=//*[@data-ofsc-entity='inventory']//td[7]";
    public final String lstValOfDestination="xpath=//select[@id='cpf_destResourceSelected_inner']//option[3]";
    public final String rbtnSearchForSubInfo="xpath=//*[@id='cpf_techinicianFilterVisibility_All' and @type='radio']";
    public final String txtSearchForInfo="xpath=//*[@id='cpf_transferPartsSearchString_inv' and @type='search']";
    public final String btnNewSubSearch="xpath=//*[@id='cpf_transferPartsSearch_inv' and @value='Search']";
    public final String tblRows="xpath=//*[@data-ofsc-entity='inventory']//tr/td[2]/div";
    public final String btnExpand="xpath=(//span[text()='Sub Inventory'])[1]";
    public final String clickActivities="xpath=//div[text()='Activities']/.";
    
    
 public TransferPartsPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
	 
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
    }
 
 public boolean subtoTechTransfer(String SubName, String InfoName) {
 boolean res=false;
	try{
		Thread.sleep(4000);
		res=this.ofscEngine.click(lnkParts, "Clicked on Parts");
		this.testObj.reportStatus(res,"Successfully clicked On Parts","Failed to clicked On Parts");
		Thread.sleep(7000);
		res=this.ofscEngine.click(btnExpand, "Clicked on expand");
		Thread.sleep(4000);
		String capturedVal=this.ofscEngine.gettabledata(tblRows);
		String[] info=capturedVal.split("#");
		System.out.println(info[0]);
		System.out.println(info[1]);
		//String sItemNumber=this.ofscEngine.getVisibleText(lblItemNumber);
		//String sSubInventoryName=this.ofscEngine.getVisibleText(lblSubInvName);
		res=clickOnTransferPartsmenu();
		this.testObj.reportStatus(res, "Successfully clicked on Transfer parts", "Failed to clicked on Transfer parts");
		res=clickSoureDropdownInner();
		res=selectSoureDropdownInner(info[1]);
		this.testObj.reportStatus(res, "Successfully selected on Source dropdown", "Failed to selected on Source dropdown");
		res=this.ofscEngine.click(rbtnSearchForSubInfo, "selected search radio button");
		this.testObj.reportStatus(res,"Successfully selected search radio button","Failed to select search radio button");
		res= this.ofscEngine.type(txtSearchForInfo, InfoName);
		this.testObj.reportStatus(res,"Successfully entered info","Failed to enter info");
		Thread.sleep(4000);
		res=this.ofscEngine.click(btnNewSubSearch, "Clicked on search button");
		this.testObj.reportStatus(res,"Successfully clicked On search","Failed to clicked On search");
		Thread.sleep(10000);
		res=clickOnTechDestinationDropdownInner();
		res=selectTechDestinationDropdownInner(SubName);
		this.testObj.reportStatus(res, "Successfully selected on Destination dropdown", "Failed to selected on Destination dropdown");
		res=enterItemNumberTransfer(info[0]);
		this.testObj.reportStatus(res, "Successfully entered on Part number", "Failed to entered on Part number");
		res=clickonSerachPartsTransfer();
		this.testObj.reportStatus(res, "Successfully clicked on Search Parts button", "Failed to clicked on Serach Parts button");
		res=clickOn1stPlusInstalledQuantityTransfer();
		this.testObj.reportStatus(res, "Successfully clicked on 1st Plus Installed Quantity", "Failed to clicked on Plus Installed Quantity");
		res=clickonSubmitButtonTransfer();
		this.testObj.reportStatus(res, "Successfully clicked on Submit button", "Failed to clicked on Submit button");
		//Click on Activities
		res=clickonActivities();
		this.testObj.reportStatus(res, "Successfully clicked on Activities", "Failed to clicked on Activities");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to click on Parts");
		}
	return res;	
}
 
 public boolean subtosubTransfer() {
	 boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkParts, "Clicked on Parts");
			this.testObj.reportStatus(res,"Successfully clicked On Labor Completion","Failed to clicked On parts");
			Thread.sleep(7000);
			res=this.ofscEngine.click(btnExpand, "Clicked on expand");
			Thread.sleep(4000);
			String capturedVal=this.ofscEngine.gettabledata(tblRows);
			String[] info=capturedVal.split("#");
			System.out.println(info[0]);
			System.out.println(info[1]);
			//String sItemNumber=this.ofscEngine.getVisibleText(lblItemNumber);
			//String sSubInventoryName=this.ofscEngine.getVisibleText(lblSubInvName);
			res=clickOnTransferPartsmenu();
			this.testObj.reportStatus(res, "Successfully clicked on Transfer parts", "Failed to clicked on Transfer parts");
			res=clickSoureDropdownInner();
			res=selectSoureDropdownInner(info[1]);//(sSubInventoryName);
			this.testObj.reportStatus(res, "Successfully selected on Source dropdown", "Failed to selected on Source dropdown");
			res=clickOnTechDestinationDropdownInner();
			res=selectTechDestinationDropdownInner("");
			this.testObj.reportStatus(res, "Successfully selected on Destination dropdown", "Failed to selected on Destination dropdown");
			res=enterItemNumberTransfer(info[0]);//(sItemNumber);
			this.testObj.reportStatus(res, "Successfully entered on Part number", "Failed to entered on Part number");
			res=clickonSerachPartsTransfer();
			this.testObj.reportStatus(res, "Successfully clicked on Search Parts button", "Failed to clicked on Serach Parts button");
			res=clickOn1stPlusInstalledQuantityTransfer();
			this.testObj.reportStatus(res, "Successfully clicked on 1st Plus Installed Quantity", "Failed to clicked on Plus Installed Quantity");
			res=clickonSubmitButtonTransfer();
			this.testObj.reportStatus(res, "Successfully clicked on Submit button", "Failed to clicked on Submit button");
			//Click on Activities
			res=clickonActivities();
			this.testObj.reportStatus(res, "Successfully clicked on Activities", "Failed to clicked on Activities");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Parts");
			}
		return res;	
	}
 public boolean clickOnParts() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkParts, "Clicked on Parts");
	
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Parts");
			}
		return res;	
	}
 public boolean clickOnBackButton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnInventories, "Clicked on back button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on back button");
			}
		return res;	
	}
 
 public boolean clickOnTransferPartsmenu() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkTransferParts, "Clicked on serached parts");
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on serached parts");
			}
		return res;	
	}
 
 public boolean clickSoureDropdownInner() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lstSourceInner, "Clicked on Source dropdown");
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on source drop down");
			}
		return res;	
	}
 
 public boolean selectSoureDropdownInner(String value) {
		boolean res=false;
		try{
			//Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstSourceInner, value, "required value selected");
			//res=this.ofscEngine.selectByIndex(lstSourceInner, 1, "required value selected");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	//Recipient type Sub inventory
 public boolean clickOnSubinventoryradioBtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(rbtnSubinvtry, "Clicked on Subinventory radio button");
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Subinventory radio button");
			}
		return res;	
	}
 
 public boolean clickOnTechnicianradioBtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(rbtnTechnician, "Clicked on Technician radio button");
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Technician radio button");
			}
		return res;	
	}
 
 public boolean clickOnMyGroupradioBtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(rbtnMyGroup, "Clicked on My Group radio button");
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on  My Group radio button");
			}
		return res;	
	}
 
 public boolean clickOndestinationBtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clkOnDest, "Clicked on Select destination");
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on destination");
			}
		return res;	
	}
 
 public boolean clickOnAllGroupradioBtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(rbtnAllGroup, "Clicked on All Group radio button");
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on All Group radio button");
			}
		return res;	
	}
 public boolean clickOnSubDestinationDropdownInner() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(ddnSubDestination, "Clicked on All Group radio button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on All Group radio button");
			}
		return res;	
	}
 
 public boolean selectSubDestinationDropdownInner(String value) {
		boolean res=false;
		try{
			//Thread.sleep(4000);
			res=this.ofscEngine.selectByName(ddnSubDestination, value, "required value selected");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
 public boolean clickOnTechDestinationDropdownInner() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(ddnTechDestination, "Clicked on All Group radio button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on All Group radio button");
			}
		return res;	
	}

public boolean selectTechDestinationDropdownInner(String value) {
		boolean res=false;
		try{
			Thread.sleep(2000);
			System.out.println("selectTechDestinationDropdownInner The value is v"+value);
			res=this.ofscEngine.selectTheDestinationValue(ddnTechDestinationOptions,value);
			
		}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
 
 public boolean enterPartNumberTransfer(String pNum) {
		boolean res= false ;
	        try {
	        	Thread.sleep(7000);
	        	res= this.ofscEngine.type(txtBoxSearchPartsTransfer, pNum);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Comments");
			}
	return res ;
	}
 public boolean enterItemNumberTransfer(String pNum) {
		boolean res= false ;
	        try {
	        	Thread.sleep(7000);
	        	res= this.ofscEngine.type(txtItemSerachFiled, pNum);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Comments");
			}
	return res ;
	}
 public boolean clickonSerachPartsTransfer() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSearchPartsTransfer, "Clicked on Labour completion");
			Thread.sleep(2000);
			this.ofscEngine.ClickMultipleButtons();
			Thread.sleep(4000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Labour completion");
			}
		return res;	
	}
 public boolean clickOn1stPlusInstalledQuantityTransfer() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickOn1stPlusInstallQnatytyTransfer, "Clicked on Plus Installed Quantity");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Plus Installed Quantity");
			}
		return res;	
	}
	public boolean clickOn2ndPlusInstalledQuantityTransfer() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickOn2ndPlusInstallQnatytyTransfer, "Clicked on Plus Installed Quantity");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Plus Installed Quantity");
			}
		return res;	
	}
	public boolean clickOn3rdPlusInstalledQuantityTransfer() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickOn3rdPlusInstallQnatytyTransfer, "Clicked on Plus Installed Quantity");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Plus Installed Quantity");
			}
		return res;	
	}
	
	public boolean clickonSaveButton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickOn1stsavebtn, "Clicked on Save button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Save button");
			}
		return res;	
	}
	
	public boolean clickonSubmitButtonTransfer() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickOnSubmitTransfer, "Clicked on Submit all Button");
			Thread.sleep(4000);
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Clicked on Submit Button");
			}
		return res;	
	}
	
	public boolean clickOnDetails() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(clickOnDetails, "Clicked on Details");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Details");
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

	

}
