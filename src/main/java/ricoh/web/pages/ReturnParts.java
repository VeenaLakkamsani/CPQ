package ricoh.web.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ReturnParts {
	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
  //  private WebActionEngine webAction=null;

   //Locator for ScanPage
    public final String lnkEnRoute = "xpath=//*[@data-plugin='PI-Enroute']";
    public final String lnkReturn="xpath=//section[contains(@data-ofsc-activity-status,'notscheduled')]//div[@class='grid-item grid-item--main']/descendant::span[text()='Returnable']";
    public final String btnReturnablePIEnRoute="xpath=//span[text()='Returnable']/following-sibling::input";
    public final String txtStatOdoVal="xpath=//*[@id='odoMeter' and @name='odoMeter']";
    public final String btnEnrouteSubmit="xpath=//*[@id='enRouteSubmitBtn' and @value='Submit']";
    public final String txtEndOdoVal="xpath=//*[@name='430' and @type='text']";
    public final String lnkReturnParts="xpath=//*[@data-plugin='PI-ReturnParts']";
    public final String lblShipTrackNum="xpath=//*[@id='trackingNumber']";
    public final String txtShippingLable="xpath=//*[@class='cp_field_text_component form-item' and @type='text']";
    public final String btnReturnableIncrease="xpath=//*[@id='cpf_button_increase']";
    public final String btnReturnSubmit="xpath=//*[@id='submitBtn']";
    public final String btnActivites="xpath=//*[contains(text(),'Activities')]";
    public final String btnSplitShipment="xpath=//*[@id='splitShipmentBtn' and @type='button']";
    public final String lstUnRecoverable="xpath=//*[@id='cpf_UnrecoverableReason_inner']";
    
    public ReturnParts(OFSCWebActionEngine actEngine){
    	
    	ofscEngine = actEngine;
  
      //  waitForPage();
    }
    
    public boolean selectUnRecoverableOption(String ErrorMsg) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnReturnSubmit, "Click on submit");
			if (ofscEngine.isAlertPresented()){
				String Capturedtext=ofscEngine.getAlerttext();
				System.out.println(Capturedtext);
				if(Capturedtext.equals(ErrorMsg)) {
					res=this.ofscEngine.selectByName(lstUnRecoverable, "broken", "Broken");
					res=this.ofscEngine.click(btnReturnSubmit, "Click on submit button");
				}
				res=true;
			}
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
    public boolean clickOnReturns() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkReturn, "Click on Returns");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on returns");
			}
		return res;	
	}
    public boolean clickOnSplitShipment() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnSplitShipment, "Click on split shipment");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on split shipment");
			}
		return res;	
	}
    public boolean switchToFrame() {	
		boolean res=false;
		try{
			res=ofscEngine.switchToIFrame();
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed switch to frame");
			}
		return res;	
	}
    public boolean clickOnReturnsPIEnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnReturnablePIEnRoute, "Click on ReturnsPI-EnRoute");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on ReturnsPI-EnRoute");
			}
		return res;	
	}
	public boolean clickOnReturnParts() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkReturnParts, "Clicked on ScanIn");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}
	public boolean clickOnReturnIncrease() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnReturnableIncrease, "Clicked on ScanIn");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}
	public boolean enterMulShippingLabel() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			String val=this.ofscEngine.getVisibleText(lblShipTrackNum).trim();
			String split=val.split(",")[0];
			Thread.sleep(2000);
			ofscEngine.enterMulVals(txtShippingLable, split+"5741785");
			res=true;
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter shipment label");
			}
		return res;	
	}
	public boolean enterShippingLabel() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			String val=this.ofscEngine.getVisibleText(lblShipTrackNum).trim();
			String split=val.split(",")[0];
			Thread.sleep(2000);
			this.ofscEngine.type(txtShippingLable, split+"5986741785");
			res=true;
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter shipment label");
			}
		return res;	
	}
	public boolean ShippingLabelErrorValidation(String Val) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			String val=this.ofscEngine.getVisibleText(lblShipTrackNum).trim();
			String split=val.split(",")[0];
			Thread.sleep(2000);
			//res=this.ofscEngine.type(txtShippingLable, split+"6385741582");
			res=this.ofscEngine.type(txtShippingLable, split+Val);
			res=true;
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}
	 public boolean clickOnIncReturnPartQty() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				ofscEngine.ClickMulButtons(btnReturnableIncrease);
				res=true;
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
				res=this.ofscEngine.click(btnReturnSubmit, "Click on submit");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on submit");
				}
			return res;	
		}
	 public boolean clickOnSplitShipubmit() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnReturnSubmit, "Click on increaseqty");
				ofscEngine.switchToDefaultFrame();
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on increase qty");
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
				}
				
			}
			else{
				res=false;
			}
			return res;
		}
	 public boolean clickOnErrorReturnSubmit() {
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
				res=this.ofscEngine.click(btnActivites, "Click on increaseqty");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on increase qty");
				}
			return res;	
		}
	
		
}