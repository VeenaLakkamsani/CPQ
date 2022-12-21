package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class ScanInPage {
	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
  //  private WebActionEngine webAction=null;

   //Locator for ScanPage
    public final String lnkScanIn="xpath=//a[@data-plugin='PI-Scan_in']";
    public final String lblEqipID="xpath=//*[text()='Equipment ID']/../following-sibling::div//span[contains(@class,'cl-value')]";
    public final String txtEqipID="xpath=//input[@id='cpf_equipment_id_inner']";
    public final String btnReTag="xpath=//*[@id='reTagBtn' and @type='button']";
    public final String btnScansubmit="xpath=//input[@id='scanSubmitBtn']";
    public final String btnRetagSubmit="xpath=//*[@id='reTagSubmitBtn' and @type='button']";
    public final String lnkPendingReqId="xpath=(//div[@class='grid-item grid-item--main']/descendant::span[text()='Chargeable'])[1]";
    public final String btnScanCancel="xpath=//*[@id='cancelBtn' and @type='button']";
    public final String btnReScan="xpath=//*[@id='reScanBtn' and @type='button']";
    public final String btnNotDone="xpath=//*[@id='notDoneBtn' and @type='button']";
    public final String lstNotDoneReason="xpath=//*[@data-label='cancel_reason']";
    public final String btnNotDoneSubmit="xpath=//button[@class='button submit' and @type='submit']";
    public final String btnBack="xpath=//*[@id='back-button' and @role='button']";
    public final String btnCancel="xpath=//*[@id='cancelBtn' and @type='button']";
    public final String lnkAttachfiles="xpath=//*[contains(text(),'Attach Files')]";
    public final String radDocImageType1="xpath=//*[@name='A_IMAGE_TYPE_1' and @value='DOC']";
    public final String radPostImageType2="xpath=//*[@name='A_IMAGE_TYPE_2' and @value='POST']";
    public final String radPreImageType3="xpath=//*[@name='A_IMAGE_TYPE_3' and @value='PRE']";
    public final String radDocImageType4="xpath=//*[@name='A_IMAGE_TYPE_4' and @value='DOC']";
    public final String radPostImageType5="xpath=//*[@name='A_IMAGE_TYPE_5' and @value='POST']";
    public final String radPreImageType6="xpath=//*[@name='A_IMAGE_TYPE_6' and @value='PRE']";
    public final String btnLoadFile="xpath=//*[contains(text(),'Upload')]";
    public final String btnAddImage="xpath=//*[text()='Add Image']";
    public final String btnFileUploadSubmit="xpath=//button[@class='button submit']";
    
    
    public ScanInPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }

    public boolean AttachFile(String sFilePath){
		boolean res=false;
		try{
			res=this.ofscEngine.click(lnkAttachfiles, "Clicked on attached files link");
			this.testObj.reportStatus(res, "Successfully clicked on Attachfiles","Failed to clicked on Attachfiles");
			Thread.sleep(5000);
			res=this.ofscEngine.click(radDocImageType1, "DocImage Type radio button selected");
			this.testObj.reportStatus(res, "Successfully selected Doc radio button","Failed to select Doc radio button");
			Thread.sleep(2000);
			res=this.ofscEngine.UpLoadFile(btnAddImage,sFilePath);
			this.testObj.reportStatus(res, "Successfully File uploaded","Failed to upload file");
			Thread.sleep(2000);
			res=this.ofscEngine.click(radPostImageType2, "PostImage Type radio button selected");
			this.testObj.reportStatus(res, "Successfully selected Post radio button","Failed to select Post radio button");
			Thread.sleep(2000);
			res=this.ofscEngine.UpLoadFile(btnAddImage,sFilePath);
			this.testObj.reportStatus(res, "Successfully File uploaded","Failed to upload file");
			Thread.sleep(2000);
			res=this.ofscEngine.click(radPreImageType3, "PreImage Type radio button selected");
			this.testObj.reportStatus(res, "Successfully selected Pre radio button","Failed to select Pre radio button");
			Thread.sleep(2000);
			res=this.ofscEngine.UpLoadFile(btnAddImage,sFilePath);
			this.testObj.reportStatus(res, "Successfully File uploaded","Failed to upload file");
			Thread.sleep(2000);
			res=this.ofscEngine.click(radDocImageType4, "DocImage Type radio button selected");
			this.testObj.reportStatus(res, "Successfully selected Doc radio button","Failed to select Doc radio button");
			Thread.sleep(2000);
			res=this.ofscEngine.UpLoadFile(btnAddImage,sFilePath);
			this.testObj.reportStatus(res, "Successfully File uploaded","Failed to upload file");
			Thread.sleep(2000);
			res=this.ofscEngine.click(radPostImageType5, "PostImage Type radio button selected");
			this.testObj.reportStatus(res, "Successfully selected Post radio button","Failed to select Post radio button");
			Thread.sleep(2000);
			res=this.ofscEngine.UpLoadFile(btnAddImage,sFilePath);
			this.testObj.reportStatus(res, "Successfully File uploaded","Failed to upload file");
			Thread.sleep(2000);
			res=this.ofscEngine.click(radPreImageType6, "PreImage Type radio button selected");
			this.testObj.reportStatus(res, "Successfully selected Pre radio button","Failed to select Pre radio button");
			Thread.sleep(2000);
			res=this.ofscEngine.UpLoadFile(btnAddImage,sFilePath);
			this.testObj.reportStatus(res, "Successfully File uploaded","Failed to upload file");
			Thread.sleep(2000);
			res=this.ofscEngine.click(btnFileUploadSubmit, "Clicked on submit button");
			this.testObj.reportStatus(res, "Successfully clicked file submit button","Failed to clicked on file submit button");
			
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Scanin process-OFSC ");
		}return res;
	}
	public boolean scanIn(){
		boolean res=false;
		try{
			//getValue=getOnEqipID();
			res=clickOnScanIn();
			this.testObj.reportStatus(res, "Successfully clicked on ScanIn link","Failed to clicked on ScanIn link");
			res=clickOnRetag();
			this.testObj.reportStatus(res,"Successfully clicked on retag button ", "Failed to click on retag button");
			res=clickOnRetagScanSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Scan button", "Failed to clicked on Scan button");
			//res=enterEqipID(value);
			//res=clickOnScanSubmit(); 
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Scanin process-OFSC ");
		}return res;
	}
	public boolean scanInErrorValidation(){
		boolean res=false;
		try{
			//getValue=getOnEqipID();	
			res=clickOnScanIn();
			this.testObj.reportStatus(res, "Successfully clicked on ScanIn link","Failed to clicked on ScanIn link");
			res=clickOnCancel();
			this.testObj.reportStatus(res, "Successfully clicked on ScanIn link","Failed to clicked on ScanIn link");
			res=clickOnScanIn();
			this.testObj.reportStatus(res, "Successfully clicked on ScanIn link","Failed to clicked on ScanIn link");
			res=enterEqipID("42564yh");
			this.testObj.reportStatus(res, "Successfully Entered eqipID","Failed to enter equipID");
			res=clickOnScanSubmit(); 
			this.testObj.reportStatus(res, "Successfully clicked on submit button","Failed to click on submit button");
			res=clickOnReScanIn();
			this.testObj.reportStatus(res, "Successfully clicked on ReScan button","Failed to click on Rescan button");
			res=clickOnRetag();
			this.testObj.reportStatus(res,"Successfully clicked on retag button ", "Failed to click on retag button");
			/*res=clickOnNotDone();
			this.testObj.reportStatus(res,"Successfully clicked on NotDone button ", "Failed to click on NotDone button");
			res=selectNotDoneReason("Call on Wrong Machine");
			this.testObj.reportStatus(res,"Successfully selected Not done reason ", "Failed to select NotDone reason");
			res=clickOnNotDoneSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on NotDonesubmit button ", "Failed to click on NotDonesubmit button");
			res=clickOnRetag();
			this.testObj.reportStatus(res,"Successfully clicked on retag button ", "Failed to click on retag button");*/
			res=clickOnRetagScanSubmit();
			this.testObj.reportStatus(res,"Successfully clicked on Scan button", "Failed to clicked on Scan button");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the Scanin process-OFSC ");
		}return res;
	}
	public String getOnEqipID() {
		String value=null;
		try{
			Thread.sleep(4000);
			value=this.ofscEngine.getVisibleText(lblEqipID);
			
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to get Equip value");
			}
		return value;
	}
	public boolean clickOnScanIn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkScanIn, "Clicked on ScanIn");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ScanIn");
			}
		return res;	
	}
	public boolean clickOnBack() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnBack, "Clicked on back button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on back button");
			}
		return res;	
	}
	public boolean clickOnCancel() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnCancel, "Clicked on cancel button");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on cancel button");
			}
		return res;	
	}
	public boolean clickOnNotDoneSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnNotDoneSubmit, "Clicked on NotDoneSubmit");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on NotDoneSubmit");
			}
		return res;	
	}
	public boolean clickOnReScanIn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			ofscEngine.switchToIFrame();
			res=this.ofscEngine.click(btnReScan, "Clicked on ReScan");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on ReScan");
			}
		return res;	
	}
	public boolean clickOnNotDone() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnNotDone, "Clicked on NotDone");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on NotDone");
			}
		return res;	
	}
	public boolean selectNotDoneReason(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstNotDoneReason, value, "required value selected");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean enterEqipID(String EqipID) {
		boolean res= false ;
	        try {
	        	ofscEngine.switchToIFrame();
	        	Thread.sleep(4000);
	        	res= this.ofscEngine.type(txtEqipID, EqipID);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Equipment ID");
			}
	return res ;
	}	
	public boolean clickOnRetag() {
		boolean res=false;
		try {
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnReTag, "Clicked on retag button");
		}catch(Exception e) {
			LogManager.logError(LoginPage.class.getName(), "Failed to click on Retag button");
		}
	return res;
	}
	
	public boolean clickOnScanSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnScansubmit, "Clicked on ScanSubmit");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on scansubmit");
			}
		return res;	
	}
	public boolean clickOnRetagScanSubmit() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(btnRetagSubmit, "Clicked on RetagScanSubmit");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Retagscansubmit");
			}
		return res;	
	}
	public boolean clickOnPendingSerReq() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkPendingReqId, "Clicked on ScanSubmit");
			ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on scansubmit");
			}
		return res;	
	}
	
}