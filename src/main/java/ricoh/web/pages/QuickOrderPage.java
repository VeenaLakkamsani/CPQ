package ricoh.web.pages;

import org.openqa.selenium.Keys;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.AbstractTestEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class QuickOrderPage{
	 
    private OFSCWebActionEngine ofscEngine = null;
    OFSCTestEngine testObj = null;
    //private LoginPage loginPage = null;

    //Locators
    
    public final String lblTaskNumber="xpath=//*[@data-label='appt_number']";
    public final String lnkActivities="xpath=//*[@id='back-button']";
    public final String lnkQuickOrder="xpath=//*[text()='Quick Order']";
    public final String txtSearchKeyword="xpath=//*[@id='searchKeyword1' and @type='search']";
    public final String btnMyInventory="xpath=//*[@id='searchButton' and @type='button']";
    //public final String txtEnterNeededQty="xpath=//*[@id='cpf_entered_qty' and @type='number']";
    public final String txtEnterNeededQty="xpath=//*[@id='cpf_entered_needed_qty' and @type='number']";
    public final String btnReviwOrder = "xpath=//*[@id='reviewOrder' and @type='button']";
    public final String lstTypeOFAddress ="xpath=//*[@id='cpf_OrderAddressType_inner']";
    public final String lstShipToAddress="xpath=//*[@id='cpf_ShipToLocation_inner']";
    public final String ddlShipToAddress="xpath=//*[@id='cpf_ShipToLocation_inner']/option";
    public final String chkOEM="xpath=//*[@id='cpf_A_ORDER_OEM_CODE_inner' and @type='checkbox']";
    public final String lstOpenShipMethod="xpath=//*[@id='cpf_A_ORDER_SHIP_METHOD_inner']";
    public final String ddlOpenShipMethod="xpath=//*[@id='cpf_A_ORDER_SHIP_METHOD_inner']/option";
    public final String txtTaskNumber="xpath=//*[@id='cpf_A_TASK_NUMBER_inner' and @type='text']";
    public final String btnPlaceOrder="xpath=//*[@id='placeOrderButton' and @type='button']";
    public final String lnkActions="xpath=//a[@title='Actions']";
  
 public QuickOrderPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
        }
    public boolean QuickOrderValidation(String sItemnumber, String sItemQty, String sTypeOfAddress, String sShipToAddress, String sOpenShipMethod, String sTaskNumber) {
    	boolean res= false ;
    	try {
    		/*String sTaskNumber=this.ofscEngine.getVisibleText(lblTaskNumber);
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(lnkActivities, "clicked on Activities");*/
    		Thread.sleep(4000);
    		if(this.ofscEngine.isElementPresent(lnkQuickOrder, true)) {
				res=this.ofscEngine.click(lnkQuickOrder, "Clicked on quick order");
				this.testObj.reportStatus(res, "Successfully clicked on quick order","Failed to clicked on quick order");
				Thread.sleep(2000);
			}else {
				res=this.ofscEngine.click(lnkActions, "Clicked on Actions");
				Thread.sleep(2000);
				res=this.ofscEngine.click(lnkQuickOrder, "clicked on quick order");
	    		this.testObj.reportStatus(res, "Successfully clicked on quick order","Failed to clicked on quick order");
			}
    		
    		Thread.sleep(4000);
    		this.ofscEngine.switchToIFrame();
    		res=this.ofscEngine.type(txtSearchKeyword, sItemnumber);
    		this.testObj.reportStatus(res,"Successfully entered Itemnumber","Failed to enter Itemnumber");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnMyInventory, "clicked on My inventory");
    		this.testObj.reportStatus(res, "Successfully clicked on MyInventory","Failed to clicked on MyInventory");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtEnterNeededQty, sItemQty);
    		this.testObj.reportStatus(res,"Successfully entered ItemQty","Failed to enter ItemQty");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnReviwOrder, "clicked on ReviewOrder");
    		this.testObj.reportStatus(res, "Successfully clicked on ReviwOrder","Failed to clicked on ReviwOrder");
    		Thread.sleep(4000);
    		this.ofscEngine.switchToIFrame();
    		res=this.ofscEngine.selectByName( lstTypeOFAddress, sTypeOfAddress, "Ship to location selected as address");
    		this.testObj.reportStatus(res, "Successfully selected TypeOfAddress","Failed to select TypeOfAddress");
    		Thread.sleep(4000);
    		//res=this.ofscEngine.selectByName( lstShipToAddress, "272790, 10002 AURORA AVE N 36", "Ship to location selected");
    		res=this.ofscEngine.click(lstShipToAddress, "clicked on ship address");
    		res=this.ofscEngine.selectTheDestinationValue(ddlShipToAddress,sShipToAddress);
    		this.testObj.reportStatus(res, "Successfully selected ShipToAddress","Failed to select ShipToAddress");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(chkOEM, "selected OEM checkbox");
    		this.testObj.reportStatus(res, "Successfully selected OEM checkbox","Failed to select OEM checkbox");
    		Thread.sleep(4000);
    		//res=this.ofscEngine.selectByName( lstOpenShipMethod, "272790, 10002 AURORA AVE N 36", "Ship to location selected");
    		res=this.ofscEngine.click(lstOpenShipMethod, "clicked on ship address");
    		res=this.ofscEngine.selectTheDestinationValue(ddlOpenShipMethod,sOpenShipMethod);
    		this.testObj.reportStatus(res, "Successfully selected OpenShipMethod","Failed to select OpenShipMethod");
    		Thread.sleep(4000);
    		res=this.ofscEngine.type(txtTaskNumber, sTaskNumber);
    		this.testObj.reportStatus(res,"Successfully entered TaskNumber","Failed to enter TaskNumber");
    		Thread.sleep(4000);
    		res=this.ofscEngine.click(btnPlaceOrder, "clicked Place order button");
    		this.testObj.reportStatus(res, "Successfully clicked on PlaceOrder","Failed to clicked on PlaceOrder");
    	} catch (Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to Log in to Application-OFSC ");
			
		}
return res ;
    	
    }
   
 
	
}