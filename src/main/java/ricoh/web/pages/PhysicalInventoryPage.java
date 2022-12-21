package ricoh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCWebActionEngine;

public class PhysicalInventoryPage {
	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
  //  private WebActionEngine webAction=null;

   //Locator for physical Inventory
    public final String btnViewMore="xpath=//*[contains(text(),'View more')]";
    public final String lnkEnRoute = "xpath=//*[@data-plugin='PI-Enroute']";
    public final String lnkPhysicalInventory="xpath=//*[contains(text(),'Physical Inventory')]";
    public final String lnkPhysicalInventorySR="xpath=//section[contains(@data-ofsc-activity-status,'pending') or(contains(@data-ofsc-activity-status,'notscheduled'))]//div[@class='grid-item grid-item--main']/descendant::span[text()='Physical Inventory']";
    public final String btnPHIPIEnRoute="xpath=//span[text()='PHI']/following-sibling::input";////*[contains(text(),'PHI')]/following-sibling::input
    public final String txtStatOdoVal="xpath=//*[@id='odoMeter' and @name='odoMeter']";
    public final String btnEnrouteSubmit="xpath=//*[@id='enRouteSubmitBtn' and @value='Submit']";
    public final String txtEndOdoVal="xpath=//*[@name='430' and @type='text']";
    public final String lnkPhysicInv="xpath=//*[@data-plugin='PI-PhysicalInventory' and @component_gui='plugin' and @plugin-type='addon']";
    public final String lstPHISubInventory="xpath=//*[@id='cpf_sub_inventory_inner']";
    public final String lstSubVal1="xpath=//select[@id='cpf_sub_inventory_inner']//option[3]";
    public final String txtPartNumber="xpath=//*[@id='cpf_TechnicianGridSearch_inner' and @type='search']";
    public final String lblPartnumberCapture="xpath=//*[@id='cpf_TechnicianGrid_header']/following-sibling::tr";
    public final String btnIncreaseButton="xpath=//*[@value='+']";
    public final String btnAddbutton="xpath=//*[@id='cpf_TechnicianAddInventory' and @type='button']";
    public final String btnAddbtn="xpath=(//*[@type='button' and @value='Add'])[2]";
    public final String btnClose="xpath=//*[@id='addinventorycloseBtn' and @type='button']";
    public final String btnSubmitUsable="xpath=//*[@id='cpf_usable' and @type='button']";
    public final String btnSubmitDefective="xpath=//*[@id='cpf_defective' and @type='button']";
    public final String txtShippingLable="xpath=//*[@class='cp_field_text_component form-item' and @type='text']";
    public final String btnReturnableIncrease="xpath=//*[@id='cpf_button_increase']";
    public final String btnReturnSubmit="xpath=//*[@id='submitBtn']";
    public final String btnActivites="xpath=//*[contains(text(),'Activities')]";
    public final String btnDetails="xpath=//*[@id='back-button']";
    
    
  
    public PhysicalInventoryPage(OFSCWebActionEngine actEngine){
    	
    	ofscEngine = actEngine;
      //  waitForPage();
    }
    public boolean PhysiclInventory(String value) {
		boolean res=false;
		try{
			res=verifyPHIandclickOnEnRoute();
			res=clickOnPHIPIEnroute();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Service Request");
			}
		return res;	
	}
    public boolean select1stSubInventory() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			//res=this.ofscEngine.selectByName(lstPHISubInventory, value, "select required field");
			res=this.ofscEngine.selectByValue(lstPHISubInventory, "0");
			Thread.sleep(2000);
			}catch(Exception e){
				
				LogManager.logError(LoginPage.class.getName(), "Select Subinventory");
			}
		return res;	
	}
    public boolean select2ndSubInventory() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			//res=this.ofscEngine.selectByName(lstPHISubInventory, value, "select required field");
			res=this.ofscEngine.selectByValue(lstPHISubInventory, "1");
			Thread.sleep(2000);
			}catch(Exception e){
				
				LogManager.logError(LoginPage.class.getName(), "Select Service Request");
			}
		return res;	
	}
    public boolean clickOnIncreaseQty() {
    	boolean res=true;
		try{
			Thread.sleep(4000);
			if (this.ofscEngine.isElementPresent(btnIncreaseButton, true)) {
				this.ofscEngine.ClickMulButtons(btnIncreaseButton);
			}
			//this.ofscEngine.ClickMulButtons(btnIncreaseButton);
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
    public boolean enterPartNumber() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			String Val=this.ofscEngine.getVisibleText(lblPartnumberCapture);
			String[] splited = Val.split("\\s+");
			Thread.sleep(2000);
			res=this.ofscEngine.type(txtPartNumber, splited[0]);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter required value");
			}
		return res;	
	}
    public boolean clickOnAddbtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnAddbutton, "Click on Add button");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
    public boolean clickOn2ndAddbtn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnAddbtn, "Click on Add button");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
    public boolean clickOnClosebutton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnClose, "Click on Add button");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
    public boolean clickOnSubmitUsable() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSubmitUsable, "Click on Add button");
			if (ofscEngine.isAlertPresent()){
				System.out.println("alert accepted");
			}
//			if (ofscEngine.isAlertPresent()){
//				System.out.println("alert accepted");
//			}
			this.ofscEngine.switchToDefaultFrame();
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
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
    public boolean verifyPHIandclickOnEnRoute() {
		boolean res=false;
		boolean isELementPresent=false;
		try{
			Thread.sleep(1000);		
		if (this.ofscEngine.isElementPresent(lnkPhysicalInventory, true)) {
			this.ofscEngine.click(lnkEnRoute, "Click on PartsReceive Link");
			res=true;
		} else {
			do { 
				this.ofscEngine.click(btnViewMore, "Click on ViewMore Link");
				Thread.sleep(500);
				if (this.ofscEngine.isElementPresent(lnkPhysicalInventory, true)) {
					res=this.ofscEngine.click(lnkEnRoute, "Click on PartsReceive Link");
					isELementPresent=true;
					res=true;
					break;
				}
			} while (!isELementPresent);
		}
		 
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
    public boolean clickOnPHI() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPhysicalInventory, "Click on Physical Inventory");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Physical Inventory");
			}
		return res;	
	}
    public boolean clickOnPHISR() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPhysicalInventorySR, "Click on Physical Inventory");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Physical Inventory");
			}
		return res;	
	}
    public boolean clickOnPHIPIEnroute() {
		boolean res=false;
		try{
			Thread.sleep(3000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnPHIPIEnRoute, "Click on ReturnsPI-EnRoute");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ReturnsPI-EnRoute");
			}
		return res;	
	}
	public boolean clickOnPhysicalInv() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPhysicInv, "Clicked on PhysicalInventoryLink");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}
	public boolean clickOnSubmitDefective() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSubmitDefective, "Clicked on SubmitDefective");
			if (ofscEngine.isAlertPresent()){
				System.out.println("alert accepted");
			}
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}

	 public boolean clickOnIncReturnPartQty() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnReturnableIncrease, "Click on increaseqty");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on increase qty");
				}
			return res;	
		}	
	
	 public boolean clickOnIncreasesubmit() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnReturnSubmit, "Click on increaseqty");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on increase qty");
				}
			return res;	
		}
	 public boolean clickOnActivites() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnActivites, "Click on Activites");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on increase qty");
				}
			return res;	
		}
	
	 public boolean clickOnDetails() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnDetails, "Click on Backbutton");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on back button");
				}
			return res;	
		}
}