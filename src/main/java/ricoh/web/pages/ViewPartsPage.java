package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ViewPartsPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
    
    //Locators
    public final String btnViewTerms="xpath=//*[@data-plugin='PI_View_Parts']";
    public final String txtItemEnter="xpath=//*[@id='searchRequestField' and @type='text']";
    public final String btnSearch="xpath=//*[@id='searchByPartsButton' and @type='button']";
    public final String lblNoRecordFound="xpath=//*[@id='messagesDiv']";
    public final String lblSubInv="xpath=//*[contains(text(),'Sub-Inventories')]";
    public final String btnActivites="xpath=//*[@id='back-button']";
    public final String lstInventoryType="xpath=//*[@id='inventoryType']";//On Hand
    public final String radSearchByPart="xpath=//*[@id='searchByPart' and @type='radio']";
    public final String radDisplayAll="xpath=//*[@id='displayAll' and @type='radio']";
    public final String lstSubInventoryofViewParts="xpath=//select[@id='subInventory']";
    public final String ddlSubInventoryNames="xpath=//*[@id='subInventory']/option";
    public final String lnkParts="xpath=//a[contains(text(),'Parts')]";
    public final String tblRows="xpath=//*[@data-ofsc-entity='inventory']//tr/td[2]/div";
    public final String lnkActions="xpath=//a[@title='Actions']";
    public final String btnExpand="xpath=(//span[text()='Sub Inventory'])[1]";
    
    public ViewPartsPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
    	
      //  waitForPage();
    }

	public boolean ViewPartsErrorValidation(String Itemwith3char,String InValItem, String VaidItem, String NoOnHandQtyItem){
		boolean res=false;
		try{
			res=clickOnViewTerms();
			this.testObj.reportStatus(res, "Successfully clicked on ViewTerms link","Failed to clicked on ViewTerms link");
					
			res=enterItemVal(Itemwith3char);
			this.testObj.reportStatus(res,"Successfully entered 3 char Item value"+Itemwith3char,"Failed to enter 3 char item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on Search button","Failed to clicked on Search button");
			res=VerifyErrorMsg("Records not found. Change criteria and try again.");
			this.testObj.reportStatus(res,"Successfully verified error message","Failed to verify error message");
			res=enterItemVal(InValItem);
			this.testObj.reportStatus(res,"Successfully entered Item value"+InValItem,"Failed to enter Item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on search button and verified invalida item"+InValItem,"Failed to clicked on search button");
			res=VerifyErrorMsg("Records not found. Change criteria and try again.");
			this.testObj.reportStatus(res,"Successfully verified error message","Failed to verify error message");
			res=enterItemVal(VaidItem);
			this.testObj.reportStatus(res,"Successfully entered Item"+VaidItem,"Failed to enter Item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
			res=VerifySubInv();
			this.testObj.reportStatus(res,"Successfully verified subinventory and verified valid item"+VaidItem,"Failed to verify subinventory");
			res=enterItemVal(NoOnHandQtyItem);
			this.testObj.reportStatus(res,"Successfully entered Item value"+NoOnHandQtyItem,"Failed to enter Item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on search button","Failed to clicked on search button");
			res=VerifyErrorMsg("Records not found. Change criteria and try again.");
			this.testObj.reportStatus(res,"Successfully verified error message for no onhand qty item"+NoOnHandQtyItem,"Failed to verify error message");
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on Back button","Failed to clicked on back button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Arrived process-OFSC ");
		}return res;
	}
	public boolean ViewPartsErrorVal(String Itemwith3char,String InValItem, String VaidItem, String NoOnHandQtyItem){
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
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on Back button","Failed to clicked on back button");
			res=clickOnViewTerms();
			this.testObj.reportStatus(res, "Successfully clicked on ViewTerms link","Failed to clicked on ViewTerms link");
			Thread.sleep(4000);
        	ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(lstSubInventoryofViewParts, "clicked on ship address");
    		res=this.ofscEngine.selectTheDestinationValue(ddlSubInventoryNames,info[1]);
			res=selectInventoryType("On Hand");
			this.testObj.reportStatus(res, "Successfully selected on hand qty","Failed to selected on hand qty");
			res=enterItemVal(InValItem);
			this.testObj.reportStatus(res,"Successfully entered 3 char Item value","Failed to enter 3 char item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on Search button","Failed to clicked on Search button");
			res=VerifyErrorMsg("No results found for given search cretiera");
			this.testObj.reportStatus(res,"Successfully verified error message","Failed to verify error message");
			res=enterItemVal("");
			res=clickOnSearch();
			res=valErrorMessage("Search keyword should contain atleast 3 letters");
			res=enterItemVal(info[0]);//(VaidItem);
			this.testObj.reportStatus(res,"Successfully entered Item"+info[0],"Failed to enter Item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
			res=VerifySubInv();
			this.testObj.reportStatus(res,"Successfully verified subinventory and verified valid item"+info[0],"Failed to verify subinventory");
			res=enterItemVal(NoOnHandQtyItem);
			this.testObj.reportStatus(res,"Successfully entered Item value"+NoOnHandQtyItem,"Failed to enter Item value");
			res=clickOnSearch();
			this.testObj.reportStatus(res,"Successfully clicked on search button","Failed to clicked on search button");
			res=setDisplayAll();
			this.testObj.reportStatus(res,"Successfully selected display all radio button","Failed to select display all radio button");
			res=VerifySubInv();
			this.testObj.reportStatus(res,"Successfully verified subinventory ","Failed to verify subinventory");
			res=clickOnBackButton();
			this.testObj.reportStatus(res, "Successfully clicked on Back button","Failed to clicked on back button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Arrived process-OFSC ");
		}return res;
	}
	public boolean clickOnViewTerms() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			if(this.ofscEngine.isElementPresent(btnViewTerms, true)) {
				res=this.ofscEngine.click(btnViewTerms, "Clicked on ViewParts button");
				Thread.sleep(2000);
			}else {
				res=this.ofscEngine.click(lnkActions, "Clicked on Actions");
				Thread.sleep(2000);
				res=this.ofscEngine.click(btnViewTerms, "Clicked on ViewParts");
			}
			
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click ViewTerms");
			}
		return res;	
	}
	
	public boolean enterItemVal(String Item) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	//ofscEngine.switchToIFrame();
	        	//Thread.sleep(2000);
	        	res= this.ofscEngine.type(txtItemEnter, Item);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter OdoMeter reading ");
		
			}
	return res ;
	}	
	public boolean setDisplayAll() {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(radDisplayAll, true, "select display all radio button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select display all radio button");
			}
		return res;	
	}
	public boolean setSearchByPart() {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(radSearchByPart, true, "select search by part radio button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select search by part radio button");
			}
		return res;	
	}
	public boolean selectInventoryType(String Item) {
		boolean res= false ;
	        try {
	        	Thread.sleep(2000);
	        	res= this.ofscEngine.selectByName(lstInventoryType, Item, "Selected on hand qty");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter OdoMeter reading ");
		
			}
	return res ;
	}	
	public boolean clickOnSearch() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSearch, "Clicked on search");
			Thread.sleep(8000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on search");
			}
		return res;	
	}
	public boolean VerifyErrorMsg(String ErrorMsg) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			String Val=this.ofscEngine.getVisibleText(lblNoRecordFound);
			if(Val.equals(ErrorMsg))
			{
				System.out.println("Error message was verified");
				res=true;
			}
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify error msg");
			}
		return res;	
	}
	public boolean VerifySubInv() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.eleDisplayed(lblSubInv, "Sub-Inventory displayed");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to verify Sub-Inventory");
			}
		return res;	
	}
	public boolean clickOnBackButton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToDefaultFrame();
			res=this.ofscEngine.click(btnActivites, "Clicked on back button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on back button");
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
}