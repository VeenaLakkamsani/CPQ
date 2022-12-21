package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.WebActionEngine;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;


public class LogOutPage {

	private OFSCWebActionEngine ofscEngine = null;
    private WebActionEngine ofscaction=null;
    private String getValue=null;
  

    //Locators
   
    //public final String lnkLogout="xpath=//a[contains(text(),'Logout')]";
    public final String lnkLogout="xpath=//*[@data-label='logout']";//19C update
    public final String lnkMore="xpath=//a[contains(text(),'More')]";
    public final String clkMgnrlnk="xpath=//div[@class='user-avatar-wrapper']/following-sibling::div[@class='user-name']";
    public final String clkLogOutmgnr="xpath=//div[@class='toa-dropdownMenu-container toa-scroll-container-vertical']/descendant::li[@label='logout']/descendant::div[text()='Sign out']";
    public final String imgMangerClk="xpath=//*[@class='icon-text']";
    public final String btnSignOut="xpath=//*[@class='item-caption __logout']";
    public LogOutPage(OFSCWebActionEngine actEngine){
    	
    	ofscEngine = actEngine;
        }

    public boolean Logout(){
		boolean res=false;
		try{
			Thread.sleep(2000);
			if(this.ofscEngine.isElementPresent(lnkLogout, true)) {
				res=this.ofscEngine.click(lnkLogout, "Clicked on Logout button");
			}else {
			  Thread.sleep(1000);
			  res=this.ofscEngine.click(imgMangerClk, "cliked on Manager options to logout button");//it is not working as expected
			  Thread.sleep(2000);
			  res=this.ofscEngine.click(btnSignOut, "cliked on logout button");
			}
			
			//res=clickOnLogout();
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to do logout OFSC ");
		}return res;
	}
    
    public boolean ManagerLogout() {
		boolean res=false;
		try{
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(imgMangerClk, "cliked on Manager options to logout button");//it is not working as expected
			  Thread.sleep(5000);
			  res=this.ofscEngine.click(btnSignOut, "cliked on logout button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to logout");
			}
		return res;
		}
    public boolean mouseMoveOnMgnrToLogout() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.mouseHover(clkMgnrlnk, "Clicked on Manager link to logout");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Manager link to logout");
			}
		return res;
		}
    	
    public boolean clickOnMore() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkMore, "Clicked on More link");		
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on More");
			}
		return res;	
		}

	public boolean clickOnLogout() throws Exception {
		boolean res=false;
		try{
			 Thread.sleep(4000);
			 res=this.ofscEngine.click(lnkLogout, "Clicked on Logout button");
			 this.ofscEngine.closeDriver();
//			 	clickOnMore();
//			  Thread.sleep(4000);
//			  res=this.ofscEngine.click(lnkLogout, "Clicked on Logout Button");
//			  this.ofscEngine.closeDriver();
			}catch(Exception e){
				  
				  LogManager.logError(LoginPage.class.getName(), "Failed to click on Logout");
			}
		return res;
	}
}