package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCWebActionEngine;

public class ExcessPartsPage {
	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
  //  private WebActionEngine webAction=null;

   //Locator for ScanPage
    public final String lnkExcessParts="xpath=//a[text()='Excess Parts']";
    public final String getExcessID="xpath=(//div[@class='cl-fld-value']/child::span[@class='cl-value-holder cl-fullwidth'])[4]";

    public ExcessPartsPage(OFSCWebActionEngine actEngine){
    	
    	ofscEngine = actEngine;
      //  waitForPage();
    }

	public boolean clickOnExcessParts() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkExcessParts, "Clicked on ScanIn");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}
	
	public boolean getTextID() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			this.ofscEngine.getVisibleText(getExcessID);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to fetched ID Value");
			}
		return res;
	}
	
	
	
	
		
}