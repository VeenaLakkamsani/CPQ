package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ArrivePage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
    
    //Locators
    public final String lnkArrived="xpath=//a[contains(text(),'Arrived')]";
   //public final String txtOdoMeterEnd="xpath=//input[@name='430' and @type='text']";
   //public final String btnArrivedSubmit="xpath=//input[@value='Submit']";
    public final String txtOdoMeterEnd="xpath=//*[@data-label='A_ODOMETER_END']";
    public final String btnArrivedSubmit="xpath=(//*[@class='button submit' and @type='submit'])[2]";
   
    public ArrivePage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
    	
      //  waitForPage();
    }

	public boolean arrived(String endOdometer){
		boolean res=false;
		try{
			res=clickOnArrived();
			this.testObj.reportStatus(res, "Successfully clicked on Arrived link","Failed to clicked on Arrived link");
			res=enterEndOdoMeter(endOdometer);
			this.testObj.reportStatus(res,"Successfully entered end odometer value"+endOdometer,"Failed to enter end odometer value");
			res=clickOnArrivedSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Arrived submit button","Failed to clicked on Arrived submit button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Arrived process-OFSC ");
		}return res;
	}
	public boolean clickOnArrived() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkArrived, "Clicked on Arrived");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Arrived");
			}
		return res;	
	}
	
	public boolean enterEndOdoMeter(String Odometer) {
		boolean res= false ;
	        try {
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtOdoMeterEnd, Odometer);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter OdoMeter reading ");
		
			}
	return res ;
	}	
	
	public boolean clickOnArrivedSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnArrivedSubmit, "Clicked on Submit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Submit");
			}
		return res;	
	}
	
}