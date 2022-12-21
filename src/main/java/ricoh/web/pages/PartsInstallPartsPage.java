package ricoh.web.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class PartsInstallPartsPage {
	
		private OFSCWebActionEngine ofscEngine = null;
	    private String getValue=null;
	    OFSCTestEngine testObj = null;
	    
	    //Locators//Parts
	   
	    public final String lnkParts="xpath=//a[contains(text(),'Parts')]";
	    public final String lnkSearchParts="xpath=//a[contains(text(),'Search Parts')]";
	    public final String lnkSubInventory="xpath=//*[contains(text(),'Sub Inventory')]";
	    public final String txtItemSerachFiled="xpath=//*[@id='searchKeyword1' and @type='search']";
	    public final String btnSearchbutton="xpath=//*[@id='searchButton' and @type='button']";
	    public final String btnOtherInventory="xpath=//*[@id='searchOthers' and @type='button' and @value='Others Inventory']";
	    public final String txtIntallQty="xpath=//*[@id='cpf_entered_install_qty' and @type='number']";
	    public final String txtRequiredQty="xpath=//*[@id='cpf_entered_needed_qty' and @type='number']";
	    public final String lstSerchBy="xpath=//*[@id='searchBy' and @name='searchBy']";
	    public final String txtWareHouseQty="xpath=//*[@id='quantity1' and @type='number' and @placeholder='Quantity']";
	    public final String btnSearchWarehouseParts="xpath=//*[@id='searchParts' and @type='submit']";
	    
	    public final String lnkOrderParts="xpath=//a[@data-plugin='PI_Order_Parts']";
	    public final String lnkTransferParts="xpath=//a[contains(text(),'Transfer Parts')]";
	    public final String txtBoxSearchParts="xpath=//*[@id='searchKeyword' and @type='text']";//"xpath=//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='searchKeyword']";
	    public final String txtBoxSearchPartsTransfer="xpath=//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_transferPartsSearchString_inner']";
	    public final String btnSearchParts="xpath=//div[@class='cp_field_value_inner_wrapper']/following::input[@id='searchButton']";
	    public final String btnSearchPartsTransfer="xpath=//div[@class='cp_field_value_inner_wrapper']/following::input[@id='searchButton']";
	    public final String clickInstallParts="xpath=(//span[@id='installLink'])[1]";
	    public final String clickNeededParts="xpath=(//span[@id='neededLink'])[1]";
	    public final String click2ndInstallParts="xpath=(//span[@id='installLink'])[2]";

	    
	    public final String clickOn1stPlusInstallQnatyty="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[1]/.";
	    public final String btnOn1stPlusNeededQnatyty="xpath=(//*[@id='cpf_button_increase' and @value='+'])[2]";//(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[2]/.";
	    public final String btn1stNeededSave="xpath=(//div[@class='cp_wrapper']/descendant::input[@id='submitDeInstallQty'])[1]";
	    
	    
	    public final String clickOn2ndPlusInstallQnatyty="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[3]/.";
	    public final String clickOn1stPlusInstallQnatytyTransfer="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[1]/.";
	    public final String clickOn2ndPlusInstallQnatytyTransfer="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[2]/.";
	    public final String clickOn3rdPlusInstallQnatytyTransfer="xpath=(//div[@class='cp_field_value_inner_wrapper']/descendant::input[@id='cpf_button_increase'])[3]/.";
	    public final String clickOn1stsavebtn="xpath=(//div[@class='cp_wrapper']/descendant::input[@id='submitInstallQty'])[1]";
	    public final String clickOn1stsaveNeededbtn="xpath=(//div[@class='cp_wrapper']/descendant::input[@id='submitDeInstallQty'])[1]";

	    public final String clickOn2ndsavebtn="xpath=(//div[@class='cp_wrapper']/descendant::input[@id='submitInstallQty'])[2]";

	    public final String clickOnSubmitAll="xpath=//input[@id='submitAllParts']/..";
	    public final String clickOnSubmitTransfer="xpath=//div[@class='cp_wrapper buttons-panel submit-line-links']/descendant::input[@value='Submit']";
	    public final String clickOnDetails="xpath=//div[text()='Details']";
	    public final String lstDestinationInner="xpath=//select[@id='cpf_destResourceSelected_inner']";
	    
	    
	    public final String lnkSearchByOrg="xpath=//*[@id='searchByWarehouse' and @value='Search by Warehouse']";
	    public final String btnBackbutton="xpath=//*[@id='back-button']";
	    public final String btnInventories="xpath=//*[contains(text(),'Inventories')]";
	    
	   
	    public PartsInstallPartsPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
	    	
	    	ofscEngine = actEngine;
	    	this.testObj = ofscObj;
	    }
	    public boolean SearchFunctionalityValidation(String ItemWith3Char){
			boolean res=false;
			try{
				String targetwindowhandle=null;
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts and verified details", "Failed to clicked on parts and details verification");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterItemNumber(ItemWith3Char);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickOnSearchbutton();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				targetwindowhandle=this.ofscEngine.getwindowhandle();
				res=clickOnOtherInventory();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				//Team search
				res=selectWareHouse("Team");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				//Area search
				res=selectWareHouse("Area");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				//All Field Techs
				res=selectWareHouse("All Field Techs");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				//Warehouse
				res=selectWareHouse("Warehouse");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				//FSL
				res=selectWareHouse("FSL");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				//Shared Sub-inventory
				res=selectWareHouse("Shared Sub-inventory");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				
				res=this.ofscEngine.closeAllOtherWindows(targetwindowhandle);
				this.testObj.reportStatus(res, "Successfully closed Other Inventory window ", "Failed to closed other inventory window");
				res=clickOnInventories();
				this.testObj.reportStatus(res, "Successfully clicked on Inventories button", "Failed to clicked on Inventories button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
				res=clickOnBackButton();
				this.testObj.reportStatus(res, "Successfully clicked on Activities button", "Failed to clicked on Activities button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
	    
	    public boolean InstallandOrderPartsErrorVal(String WareHouse,String ItemWith3Char,String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts and verified details", "Failed to clicked on parts and details verification");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=valErrorMessage("Please enter a search term.");
				this.testObj.reportStatus(res, "Successfully verified Error message", "Failed to verify error message");
				res=enterPartNmber(WareHouse);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=VerifySubmitallButton();
				this.testObj.reportStatus(res, "Successfully searched with warehouse"+WareHouse, "Failed to search withwarehouse");
				res=enterPartNmber(ItemWith3Char);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully searched with 3 char item"+ItemWith3Char, "Failed to search with 3 char item");
				res=clickOnBackButton();
				this.testObj.reportStatus(res, "Successfully clicked on back button", "Failed to click on back button");
				res=clickOnOrderParts();
				this.testObj.reportStatus(res, "Successfully clicked on Order Parts", "Failed to clicked on order parts");
				res=clickOnSearchByOrg();
				this.testObj.reportStatus(res, "Successfully clicked on Search by wareHouse", "Failed to clicked on SearchByWareHouse");
				res=valErrorMessage("Warehouse request submit successful. Please revisit in sometime to view the Warehouse data");
				this.testObj.reportStatus(res, "Successfully verified Error message", "Failed to verify error message");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterPartNmber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=clickonInstall();
				this.testObj.reportStatus(res, "Successfully clicked on install ", "Failed to clicked on Install");
				res=clickonPlusInstalledQuantity();
				this.testObj.reportStatus(res, "Successfully clicked on Increase button", "Failed to clicked on increase button");
				res=clickonSaveButton();
				this.testObj.reportStatus(res, "Successfully clicked on Save button", "Failed to clicked on Save button");
				res=clickonSubmitallButton();
				this.testObj.reportStatus(res, "Successfully clicked on submit all button ", "Failed to clicked on submit all button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
	    public boolean InstallandOrderPartsErrorValidation(String WareHouse,String ItemWith3Char,String val){
			boolean res=false;
			try{
				String targetwindowhandle=null;
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts and verified details", "Failed to clicked on parts and details verification");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=clickOnSearchbutton();
				//this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=valErrorMessage("Please enter a search term.");
				this.testObj.reportStatus(res, "Successfully verified Error message", "Failed to verify error message");
				res=enterItemNumber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickOnSearchbutton();
				//this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=valErrorMessage("Please enter a search term more than 3 characters");
				this.testObj.reportStatus(res, "Successfully verified Error message", "Failed to verify error message");
				res=enterItemNumber(ItemWith3Char);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickOnSearchbutton();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				targetwindowhandle=this.ofscEngine.getwindowhandle();
				res=clickOnOtherInventory();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=selectWareHouse("Warehouse");
				this.testObj.reportStatus(res, "Successfully selected Warehouse ", "Failed to select warehouse");
				res=enterWareHouseQty("1");
				this.testObj.reportStatus(res, "Successfully entered warehouse value", "Failed to clicked on warehouse value");
				res=clickOnSearchWareHousebtn();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=this.ofscEngine.closeAllOtherWindows(targetwindowhandle);
				this.testObj.reportStatus(res, "Successfully closed Other Inventory window ", "Failed to closed other inventory window");
				res=clickOnInventories();
				this.testObj.reportStatus(res, "Successfully clicked on Inventories button", "Failed to clicked on Inventories button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
				//res=clickOnBackButton();
				//this.testObj.reportStatus(res, "Successfully clicked on Activities button", "Failed to clicked on Activities button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
	    
		public boolean partsInstall(String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts excess", "Failed to clicked on parts");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterPartNmber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=clickonInstall();
				this.testObj.reportStatus(res, "Successfully clicked on install ", "Failed to clicked on Install");
				res=clickonPlusInstalledQuantity();
				this.testObj.reportStatus(res, "Successfully clicked on Increase button", "Failed to clicked on increase button");
				res=clickonSaveButton();
				this.testObj.reportStatus(res, "Successfully clicked on Save button", "Failed to clicked on Save button");
				res=clickonSubmitallButton();
				this.testObj.reportStatus(res, "Successfully clicked on submit all button ", "Failed to clicked on submit all button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
		public boolean CarStackPartValidation(String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts excess", "Failed to clicked on parts");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterPartNmber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}		
		public boolean MulPartsWithQty(String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts excess", "Failed to clicked on parts");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterPartNmber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=clickonInstall();
				this.testObj.reportStatus(res, "Successfully clicked on install ", "Failed to clicked on Install");
				res=clickonPlusInstalledQuantity();
				this.testObj.reportStatus(res, "Successfully clicked on Increase button", "Failed to clicked on increase button");
				res=clickonSaveButton();
				this.testObj.reportStatus(res, "Successfully clicked on Save button", "Failed to clicked on Save button");
				res=clickon2ndInstall();
				this.testObj.reportStatus(res, "Successfully clicked on 2ndinstall ", "Failed to clicked on 2nd Install");
				res=clickon2ndPlusInstalledQuantity();
				this.testObj.reportStatus(res, "Successfully clicked on Increase button", "Failed to clicked on increase button");
				res=clickon2ndSaveButton();
				this.testObj.reportStatus(res, "Successfully clicked on Save button", "Failed to clicked on Save button");
				res=clickonSubmitallButton();
				this.testObj.reportStatus(res, "Successfully clicked on submit all button ", "Failed to clicked on submit all button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
		public boolean MulPartsWithQtyValidation(String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts excess", "Failed to clicked on parts");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterItemNumber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickOnSearchbutton();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				this.ofscEngine.enterInstallMulVals(txtIntallQty,"1");
				this.testObj.reportStatus(true, "Successfully entered installed increased qty ", "Failed to entered installed increased qty");
				res=clickonSubmitallButton();
				this.testObj.reportStatus(res, "Successfully clicked on submit all button ", "Failed to clicked on submit all button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
		public boolean InsufficientParts(String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts excess", "Failed to clicked on parts");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterPartNmber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickonSerachParts();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=clickonNeeded();
				this.testObj.reportStatus(res, "Successfully clicked on Needed button ", "Failed to clicked on Needed button");
				res=clickonPlusNeededQuantity();
				this.testObj.reportStatus(res, "Successfully clicked on Increase button", "Failed to clicked on increase button");
				res=clickonPlusNeededQuantity();
				this.testObj.reportStatus(res, "Successfully clicked on Increase button", "Failed to clicked on increase button");
				res=clickonSaveNeededButton();
				this.testObj.reportStatus(res, "Successfully clicked on Save button", "Failed to clicked on Save button");
				res=clickonSubmitallButton();
				this.testObj.reportStatus(res, "Successfully clicked on submit all button ", "Failed to clicked on submit all button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
			}
			return res;
		}
		public boolean InsufficientPartsValidation(String val){
			boolean res=false;
			try{
				res=clickOnParts();
				this.testObj.reportStatus(res, "Successfully clicked on Parts excess", "Failed to clicked on parts");
				res=clickOnsearchPartsmenu();
				this.testObj.reportStatus(res, "Successfully clicked on Search parts menu", "Failed to clicked on search parts");
				res=enterItemNumber(val);
				this.testObj.reportStatus(res, "Successfully entered on Parts number", "Failed to enter Part number");
				res=clickOnSearchbutton();
				this.testObj.reportStatus(res, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				this.ofscEngine.enterInstallMulVals(txtRequiredQty,"1");
				this.testObj.reportStatus(true, "Successfully clicked on search Parts ", "Failed to clicked on search parts");
				res=clickonSubmitallButton();
				this.testObj.reportStatus(res, "Successfully clicked on submit all button ", "Failed to clicked on submit all button");
				res=clickOnDetails();
				this.testObj.reportStatus(res, "Successfully clicked on details button", "Failed to clicked on detils button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to complete the  PartInstall process-OFSC ");
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
		public boolean clickOnInventories() {
			boolean res=false;
			try{
				ofscEngine.switchToDefaultFrame();
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnInventories, "Clicked on Inventories");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Inventories");
				}
			return res;	
		}
		public boolean clickOnBackButton() {
			boolean res=false;
			try{
				
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnBackbutton, "Clicked on back button");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on back button");
				}
			return res;	
		}
		public boolean clickOnSearchByOrg() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(lnkSearchByOrg, "Clicked on Search by warehouse");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on search by warehouse");
				}
			return res;	
		}
		public boolean clickOnsearchPartsmenu() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(lnkSearchParts, "Clicked on serached parts");
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on serached parts");
				}
			return res;	
		}
		public boolean clickOnOrderParts() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(lnkOrderParts, "Clicked on order parts");
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on order parts");
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
		
		public boolean selectDestinationDropdownInner(String value) {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.selectByName(lstDestinationInner, value, "required value selected");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
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
		public boolean enterPartNmber(String pNum) {
			boolean res= false ;
		        try {
		        	res= this.ofscEngine.type(txtBoxSearchParts, pNum);
				} catch (Exception e) {
					LogManager.logError(LoginPage.class.getName(), "Failed to enter Part number");
				}
		return res ;
		}
		public boolean enterWareHouseQty(String pNum) {
			boolean res= false ;
		        try {
		        	res= this.ofscEngine.type(txtWareHouseQty, pNum);
				} catch (Exception e) {
					LogManager.logError(LoginPage.class.getName(), "Failed to enter Part number");
				}
		return res ;
		}
		public boolean enterPartNumberTransfer(String pNum) {
			boolean res= false ;
		        try {
		        	res= this.ofscEngine.type(txtBoxSearchPartsTransfer, pNum);
				} catch (Exception e) {
					LogManager.logError(LoginPage.class.getName(), "Failed to enter Part number");
				}
		return res ;
		}
		public boolean enterItemNumber(String pNum) {
			boolean res= false ;
		        try {
		        	res= this.ofscEngine.type(txtItemSerachFiled, pNum);
				} catch (Exception e) {
					LogManager.logError(LoginPage.class.getName(), "Failed to enter Part number");
				}
		return res ;
		}
		
		public boolean clickonSerachParts() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnSearchParts, "Clicked on Labour completion");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Labour completion");
				}
			return res;	
		}
		
		public boolean clickonSerachPartsTransfer() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnSearchPartsTransfer, "Clicked on Labour completion");
				Thread.sleep(4000);
				ofscEngine.switchToDefaultFrame();
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Labour completion");
				}
			return res;	
		}
		
		
		public boolean clickonInstall() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickInstallParts, "Clicked on Install");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Install");
				}
			return res;	
		}
		
		public boolean clickonNeeded() {
			boolean res=false;
			try{
				//Thread.sleep(4000);
				//ofscEngine.switchToIFrame();
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickNeededParts, "Clicked on Needed");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Needed");
				}
			return res;	
		}
		
		public boolean clickon2ndInstall() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				Thread.sleep(4000);
				res=this.ofscEngine.click(click2ndInstallParts, "Clicked on Install");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Install");
				}
			return res;	
		}
		
		public boolean clickonPlusInstalledQuantity() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickOn1stPlusInstallQnatyty, "Clicked on Plus Installed Quantity");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Plus Installed Quantity");
				}
			return res;	
		}
		
		public boolean clickonPlusNeededQuantity() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnOn1stPlusNeededQnatyty, "Clicked on Plus Needed Quantity");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Plus Needed Quantity");
				}
			return res;	
		}
		public boolean clickonSaveNeededQuantity() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btn1stNeededSave, "Clicked on Save Needed button");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Save Needed button");
				}
			return res;	
		}
		
		public boolean clickon2ndPlusInstalledQuantity() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickOn2ndPlusInstallQnatyty, "Clicked on Plus Installed Quantity");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Plus Installed Quantity");
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
		
		public boolean clickonSaveNeededButton() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickOn1stsaveNeededbtn, "Clicked on Save Needed button");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Save Needed button");
				}
			return res;	
		}
		
		public boolean clickon2ndSaveButton() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickOn2ndsavebtn, "Clicked on Save button");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Save button");
				}
			return res;	
		}
		
		public boolean clickonSubmitallButton() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(clickOnSubmitAll, "Clicked on Submit all Button");
				Thread.sleep(4000);
				ofscEngine.switchToDefaultFrame();
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to Clicked on Submit all Button");
				}
			return res;	
		}
		public boolean VerifySubmitallButton() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.eleDisplayed(clickOnSubmitAll, "Verify Submit all Button");
				Thread.sleep(4000);
				ofscEngine.switchToDefaultFrame();
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to verify Submit all Button");
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
		public boolean clickOnSearchbutton() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnSearchbutton, "Clicked on search button");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on search button");
				}
			return res;	
		}
		public boolean clickOnOtherInventory() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnOtherInventory, "Clicked on Other Inventory");
				ofscEngine.switchToNewWindow();
				Thread.sleep(4000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Other inventory");
				}
			return res;	
		}
		public boolean clickOnSearchWareHousebtn() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnSearchWarehouseParts, "Clicked on Other search button");
				ofscEngine.switchToNewWindow();
				Thread.sleep(4000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on search button");
				}
			return res;	
		}
		public boolean selectWareHouse(String Val) {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.selectByName(lstSerchBy, Val, "Selected WareHouse");//Warehouse,WAREHOUSE
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed to click on Other inventory");
				}
			return res;	
		}
	}