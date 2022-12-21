package ricoh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ReceivePartsPage {
	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
    public String Val;
  //  private WebActionEngine webAction=null;

   //Locator for ScanPage
    public final String lnkEnRoute = "xpath=//*[@data-plugin='PI-Enroute']";
    //public final String lnkReceiveParts="xpath=(//*[contains(text(),'Parts Receive')])[1]";
    public final String lnkReceiveParts="xpath=//section[@data-ofsc-entity-id='grid-notscheduled']//div[@class='grid-row pointer']/div[contains(@class,'grid-item')]/div/span[contains(text(),'Parts Receive')]";
    public final String btnPartsReceivePIEnRoute="xpath=(//span[text()=' Parts Receive']/following-sibling::input)[1]";
    public final String lblReceiveDoc="xpath=//span[text()='Receive Doc #']/following-sibling::span";
    public final String lnkTransActiviy="xpath=//*[@action_link_label='PI-Transfer_Activity']";
    public final String lstSubInvnetory="xpath=//*[@id='search__sub_inventory' and @name='search__sub_inventory']"; //MERRILLCMN, 300 11TH AVE EAST, PEARSONMN, 2125 4TH STREET NW
    public final String ddlSubInvnetory="xpath=//*[@id='search__sub_inventory' and @name='search__sub_inventory']/option";
    public final String txtSearchString="xpath=//*[@id='search__string' and @name='search__string']";
    public final String btnSearch="xpath=//*[@id='search__btn' and @type='button']";
    public final String btnMove="xpath=//*[@value='Move' and @type='button']";
    public final String btnAppoinmentMoved="xpath=//*[@value='Appointment has been moved' and @type='button']";
    public final String btnActivities="xpath=//*[@id='back-button' and @role='button']";
    public final String btnException="xpath=//*[@id='cpf_exception' and @type='button']";
    public final String btnDamageBtnInc="xpath=//*[@id='cpf_damaged_button_increase' and @type='button']";
    public final String btnUsedBtnInc="xpath=//*[@id='cpf_used_button_increase' and @type='button']";
    public final String btnExpenceSubmit="xpath=//*[@id='submitExcep' and @type='button']";
    public final String btnPunchInMSG="xpath=(//*[@title='Close'])[2]";
    
    public final String txtStatOdoVal="xpath=//*[@id='odoMeter' and @name='odoMeter']";
    public final String btnEnrouteSubmit="xpath=//*[@id='enRouteSubmitBtn' and @value='Submit']";
    public final String txtEndOdoVal="xpath=//*[@name='430' and @type='text']";
    public final String lnkReceiveInventory="xpath=//*[contains(text(),'Receive Inventory')]";
    public final String lblShipTrackNum="xpath=//*[@id='trackingNumber']";
    public final String txtShippingLable="xpath=//*[@class='cp_field_text_component form-item' and @type='text']";
    public final String btnReceivePartsIncrease="xpath=//*[@id='cpf_quantity_button_increase' and @value='+']";
    public final String btnPartsIncreaseConfirm="xpath=//*[@id='cpf_confirm_line_Btn' and @type='button']";
    public final String btnSubmitALLLineItems="xpath=//*[@id='submitAllLinesBtn' and @type='button']";
    public final String btnViewMore="xpath=//*[contains(text(),'View more')]";
    public final String lblLineItems="xpath=//*[contains(text(),'Line Items: ')]";
    public final String lblExpectedVal="xpath=//*[contains(text(),'Expected:')]";
    public final String txtExpectedVal="xpath=//*[@id='cpf_entered_qty' and @type='text']";
    public final String btnExpected="xpath=//*[@id='cpf_exception' and @type='button']";
    public final String btnConfirmLine="//*[@id='cpf_confirm_line_Btn' and @type='button']";
    public final String txtDamageQty="xpath=//*[@id='cpf_damaged_entered_qty' and @type='text']";
    public final String txtUsedQty="xpath=//*[@id='cpf_used_entered_qty' and @type='text']";
    
    
    public ReceivePartsPage(OFSCWebActionEngine actEngine){
    	
    	ofscEngine = actEngine;
      //  waitForPage();
    }
    public boolean clickOnActivites() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToDefaultFrame();
			res=this.ofscEngine.click(btnActivities, "Click on back button");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on backbutton .. ");
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
				LogManager.logError(LoginPage.class.getName(), "Failed click on Move .. ");
			}
		return res;	
	}
    public boolean clickOnExceptions() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnException, "Click on Exceptions");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Exceptions .. ");
			}
		return res;	
	}
    public boolean clickOnDamageBtnIncrease() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnDamageBtnInc, "Click on Damageincbutton");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on Damageincbutton .. ");
			}
		return res;	
	}
    public boolean clickOnUsedBtnIncrease() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnUsedBtnInc, "Click on Used increase button");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on increase button .. ");
			}
		return res;	
	}
    public boolean clickOnAppoinmentMove() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnAppoinmentMoved, "Click on AppoinmentMove");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on AppoinmentMove .. ");
			}
		return res;	
	}
    public boolean clickOnMove() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnMove, "Click on Move");
			Thread.sleep(2000);
			if(ofscEngine.isAlertPresent()) {
				System.out.println("Alert accepted");
				Thread.sleep(4000);
				if(ofscEngine.eleDisplayed(btnAppoinmentMoved, "Appoinment has been moved"))
				{
					res=true;
				}
			}
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on EnRoute .. ");
			}
		return res;	
	}
    public boolean clickOnPartsReceive() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkReceiveParts, "Click on PartsReceive");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on PartsReceive");
			}
		return res;	
	}
    public boolean clickOnPartsReceivePIEnroute() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnPartsReceivePIEnRoute, "Click on PartsReceivePI-EnRoute");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on PartsReceivePI-EnRoute");
			}
		return res;	
	}
	public boolean clickOnReceiveInventory() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkReceiveInventory, "Clicked on ReceiveInventory");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ReceiveInventory");
			}
		return res;	
	}
	
	 public boolean clickOnIncReceivePartQty() {
			boolean res=false;
			try{
				Thread.sleep(2000);
				ofscEngine.switchToIFrame();
				res=this.ofscEngine.click(btnReceivePartsIncrease, "Click on increaseqty");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on increase qty");
				}
			return res;	
		}	
	
	 public boolean clickOnPartsIncConfirm() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnPartsIncreaseConfirm, "Click on confirm button");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on confirm button");
				}
			return res;	
		}
	 public boolean clickOnSubmitAllbtn() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(btnSubmitALLLineItems, "Click on SubmitAll button");
				if(ofscEngine.isAlertPresent()) {
					System.out.println("Alert accepted");
					Thread.sleep(4000);
				}
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on SubmitAll button");
				}
			return res;	
		}
	 
	 public boolean clickonpunchinAccept() {
		 boolean res=false;
		 try {
			 	ofscEngine.switchToIFrame();
				if(this.ofscEngine.isElementPresent(btnPunchInMSG, true)) {
		    		res=this.ofscEngine.click(btnPunchInMSG, "click on PunchIn button");
		    		this.testObj.reportStatus(res, "Successfully clicked on PunchIn", "Failed to clicked On PunchIn");	
		    	}
		 }catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on punchin success button");
			}
		return res;	
	}
	 
	 public boolean clickOnTransActivity() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				res=this.ofscEngine.click(lnkTransActiviy, "Click on TransferActivity");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on TransferActivity");
				}
			return res;	
		}
	 public boolean clickOnErrorSubmitAllbtn() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				res=this.ofscEngine.click(btnSubmitALLLineItems, "Click on SubmitAll button");
				if(ofscEngine.isAlertPresent()) {
					System.out.println("Alert accepted");
					Thread.sleep(4000);
				}
				ofscEngine.switchToDefaultFrame();
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on SubmitAll button");
				}
			return res;	
		}
	 public boolean verifyandclickOnPartsReceivePIEnRoute() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				res=this.ofscEngine.click(btnPartsReceivePIEnRoute, "Clcked on PartsReceivePIEnroute");
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on PartsReceivePIEnroute .. ");
				}
			return res;	
		}
	 public boolean verifyPartReceiveandclickOnEnRoute() {
			boolean res=false;
			boolean isELementPresent=false;
			try{
				Thread.sleep(1000);		
			if (this.ofscEngine.isElementPresent(lnkReceiveParts, true)) {
				this.ofscEngine.click(lnkEnRoute, "Click on PartsReceive Link");
			} else {
				do { 
					this.ofscEngine.click(btnViewMore, "Click on ViewMore Link");
					Thread.sleep(500);
					if (this.ofscEngine.isElementPresent(lnkReceiveParts, true)) {
						res=this.ofscEngine.click(lnkEnRoute, "Click on PartsReceive Link");
						isELementPresent=true;
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
	 public  boolean verifyReceiveDocClickTransActivity() {
			boolean isELementPresent=false;
			boolean res=false;
			try{
				Thread.sleep(1000);		
			if (this.ofscEngine.isElementPresent(lblReceiveDoc, true)) {
				 Val=this.ofscEngine.getVisibleText(lblReceiveDoc);
				res=this.ofscEngine.click(lnkTransActiviy, "Click on TransActivity Link");
			} else {
				do { 
					this.ofscEngine.click(btnViewMore, "Click on ViewMore Link");
					Thread.sleep(500);
					if (this.ofscEngine.isElementPresent(lblReceiveDoc, true)) {
						 Val=this.ofscEngine.getVisibleText(lblReceiveDoc);
						res=this.ofscEngine.click(lnkTransActiviy, "Click on TransActivity Link");
						isELementPresent=true;
						break;
					}
				} while (!isELementPresent);
			}
			 
				Thread.sleep(2000);
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Failed click on TransActivity .. ");
				}
			return res;
		}
	 public boolean selectSubInventory() {
			boolean res=false;
			try{
				Thread.sleep(4000);
				ofscEngine.switchToIFrame();
				Thread.sleep(2000);
				res=this.ofscEngine.click(lstSubInvnetory, "Click on TransActivity Link");
				res=this.ofscEngine.selectTheDestinationValue(ddlSubInvnetory,"");
				//res=this.ofscEngine.selectByString(lstSubInvnetory, Val );				
				//res=this.ofscEngine.selectByIndex(lstSubInvnetory, 3, "SubInventory");
				}catch(Exception e){
					LogManager.logError(LoginPage.class.getName(), "Select Service Request");
				}
			return res;	
		} 
	 public boolean enterReceiveDoc(String Val) {
			boolean res= false ;
		        try {
		        	Thread.sleep(4000);
		        	res= this.ofscEngine.type(txtSearchString, Val);
				} catch (Exception e) {
					LogManager.logError(LoginPage.class.getName(), "Failed to enter Comments");
				}
		return res ;
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
public boolean clickOnSearchbutton() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSearch, "Click on search button");
			Thread.sleep(2000);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed click on search button");
			}
		return res;	
	}
public boolean clickOnExceptionSubmit() {
	boolean res=false;
	try{
		Thread.sleep(4000);
		res=this.ofscEngine.click(btnExpenceSubmit, "Click on Exception Submit");
		//ofscEngine.switchToDefaultFrame();
		Thread.sleep(2000);
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed click on Exception button");
		}
	return res;	
}


public boolean ReceivePartsValidation() {
	boolean res=false;
	try {
		Thread.sleep(2000);
		ofscEngine.switchToIFrame();
		String Val=this.ofscEngine.getVisibleText(lblLineItems);
		String[] splited = Val.split(": ");
		Thread.sleep(2000);
		 int Count=Integer.parseInt(splited[1]);
				 for(int j=0; j<Count; j++) {
					 Thread.sleep(2000);
					 List<WebElement> allElements = ofscEngine.driver.findElements(By.xpath("//*[contains(text(),'Expected:')]"));
					 String ExpVal= allElements.get(j).getText();
					 String[] splitedExpVal = ExpVal.split(": ");
					 
					 Thread.sleep(1000);
					 List<WebElement> txtExpectedVa = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_entered_qty' and @type='text']"));
					 txtExpectedVa.get(j).clear();
					 txtExpectedVa.get(j).sendKeys(splitedExpVal[1]);
					 int t=Integer.parseInt(splitedExpVal[1]);
					 Thread.sleep(1000);
					 List<WebElement> btnExp = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_exception' and @type='button']"));
					 btnExp.get(j).click();
					 Thread.sleep(1000);
					 List<WebElement> btnSubExp = ofscEngine.driver.findElements(By.xpath("//*[@id='submitExcep' and @type='button']"));
					 btnSubExp.get(j).click();
					 Thread.sleep(1000);
					 List<WebElement> btnConfirmLine = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_confirm_line_Btn' and @type='button']"));
					 btnConfirmLine.get(j).click();
				 }
			res=true;
	}catch(Exception e){
		LogManager.logError(LoginPage.class.getName(), "Failed click on Exception button");}
	return res;
}
public boolean ReceivePartsErrorValidations() {
	boolean res=false;
	try {
		Thread.sleep(2000);
		ofscEngine.switchToIFrame();
		String Val=this.ofscEngine.getVisibleText(lblLineItems);
		String[] splited = Val.split(": ");
		Thread.sleep(2000);
		 int Count=Integer.parseInt(splited[1]);
				 for(int j=0; j<Count; j++) {
					 Thread.sleep(2000);
					 List<WebElement> allElements = ofscEngine.driver.findElements(By.xpath("//*[contains(text(),'Expected:')]"));
					 String ExpVal= allElements.get(j).getText();
					 String[] splitedExpVal = ExpVal.split(": ");
					 
					 Thread.sleep(1000);
					 List<WebElement> txtExpectedVa = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_entered_qty' and @type='text']"));
					 txtExpectedVa.get(j).clear();
					 txtExpectedVa.get(j).sendKeys(splitedExpVal[1]);
					 int t=Integer.parseInt(splitedExpVal[1]);
					 Thread.sleep(1000);
					 List<WebElement> btnExp = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_exception' and @type='button']"));
					 btnExp.get(j).click();
					 Thread.sleep(1000);
					 if(j==0 | j==2) {
						 List<WebElement> txtDamageQty = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_damaged_entered_qty' and @type='text']"));
						 txtDamageQty.get(j).clear();
						 txtDamageQty.get(j).sendKeys("1");
					 }
					 Thread.sleep(1000);
					 if(j==1 | j==2) {
						 List<WebElement> txtUsedQty = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_used_entered_qty' and @type='text']"));
						 txtUsedQty.get(j).clear();
						 txtUsedQty.get(j).sendKeys("1");
					 }
					 List<WebElement> btnSubExp = ofscEngine.driver.findElements(By.xpath("//*[@id='submitExcep' and @type='button']"));
					 btnSubExp.get(j).click();
					 Thread.sleep(1000);
					 List<WebElement> btnConfirmLine = ofscEngine.driver.findElements(By.xpath("//*[@id='cpf_confirm_line_Btn' and @type='button']"));
					 btnConfirmLine.get(j).click();
				 }
			res=true;
	}catch(Exception e){
		LogManager.logError(LoginPage.class.getName(), "Failed click on Exception button");}
	return res;
}

}