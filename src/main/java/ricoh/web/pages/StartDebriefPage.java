package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class StartDebriefPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
  //  private WebActionEngine webAction=null;

    //Locators
   
    public final String lnkStartDebrief="xpath=//a[contains(text(),'Start Debrief')]";
    public final String lstDossSelection="xpath=//select[@class='form-item' and @name='655']";
    public final String lstEqipSelection="xpath=//select[@class='form-item' and @name='656']";
    //public final String chkPmPerform="xpath=//*[@class='form-item' and @name='691' and @type='checkbox']";
    public final String txtFutureSR="xapth=//textarea[@class='form-item' and @name='692']";
    public final String chkPmPerform="xpath=//*[@data-label='A_LAST_PM_DATE_SELECTION' and @type='checkbox']";//19C Update
    public final String chkImpImages="xpath=//*[@data-label='A_IMAGES_ADD' and @type='checkbox']";
    public final String lstServiceType="xpath=//*[@data-label='A_SERVICE_TYPE_LIST']";
    //public final String chkConfirm="xpath=//*[@class='form-item' and @name='712' and @type='checkbox']";
    public final String chkConfirm="xpath=//*[@data-label='A_CONFIRM' and @type='checkbox']";//19C Update
    //public final String btnSaveForm="xpath=//*[@id='save_form' and @type='submit']";
    public final String btnSaveForm="xpath=//*[contains(text(),'Submit')]";//19C Update
   
    public StartDebriefPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }

  
	public boolean startDebrief(){
		boolean res=false;
		try{
			res=clickOnStartDebrief();
			this.testObj.reportStatus(res,"Successfully clicked on StarDebrief", "Failed to clicked on StarDebrief");
			//res=selectDoss(1);
			//res=selectEqipSelect( 1);
			res=setPMPerform();
			this.testObj.reportStatus(res, "Successfully checkbox selected","Failed to select checkbox");
			//res=enterFutureSRS( Srreqst);
			if(this.ofscEngine.isElementPresent(lstServiceType, true)) {
				res=this.ofscEngine.selectByName(lstServiceType, "Continuous Forms", "selected required field");
				this.testObj.reportStatus(res,"Successfully selected ServiceType", "Failed to select ServiceType");
        	}
			res= this.ofscEngine.setCheckbox(chkImpImages, true, "necessary Images have been attached");//as per new changes after 20B
			this.testObj.reportStatus(res,"Successfully selected necessary Images checkbox", "Failed to necessary Images checkbox");
			res=setConfirmCheckbox();
			this.testObj.reportStatus(res,"Successfully selected Confirm checkbox", "Failed to Confirm checkbox");
			res=clickOnSaveForm();
			this.testObj.reportStatus(res, "Successfully saved form","Failed to saved form");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the StartDebrief process-OFSC ");
		}return res;
	}
	
	
	public boolean clickOnStartDebrief() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkStartDebrief, "Clicked on StartDebrief");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on StartDebrief");
			}
		return res;	
	}
	public boolean selectDoss(int index) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			//res=this.ofscEngine.selectByName(lstDossSelection, value, "DOSS Enabled Selection");
			res=this.ofscEngine.selectByIndex(lstDossSelection, index, "Doss selection");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select Doss");
			}
		return res;	
	}
	
	public boolean selectEqipSelect(int index) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByIndex(lstEqipSelection, index, "Eqip select");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select eqip");
			}
		return res;	
	}	
	
	public boolean setPMPerform() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.ScrollWeb();
			res=this.ofscEngine.setCheckbox(chkPmPerform, true, "checked PM perform");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to check on PM Perform");
			}
		return res;	
	}	
	
	public boolean enterFutureSRS(String Odometer) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtFutureSR, Odometer);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter OdoMeter reading ");
		
			}
	return res ;
	}
		
	public boolean setConfirmCheckbox() {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.setCheckbox(chkConfirm, true, "checkbox");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to check confirm checkbox");
		
			}
	return res ;
	}	
	
	public boolean clickOnSaveForm() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnSaveForm, "Clicked on Save");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Save");
			}
		return res;	
	}
	
}