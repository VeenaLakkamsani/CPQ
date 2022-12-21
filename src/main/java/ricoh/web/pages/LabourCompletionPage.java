package ricoh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCTestEngine;
import ricoh.web.engine.OFSCWebActionEngine;

public class LabourCompletionPage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
    OFSCTestEngine testObj = null;
 
    //Locators
    
    public final String lnkLaborCmptn="xpath=//a[contains(text(),'Labor Completion')]";
    public final String lstServiceRqstStatus="xpath=//select[@id='cpf_AStatus_inner' and @class='cp_field_dropdown_component form-item']";//Complete,Incomplete Parts
    public final String lstConfirmDeInstall="xpath=//*[@id='deinstall_code_dropdown']";//Yes,No
    public final String radBeforeRepair="xpath=//label[.='Completely Down']/preceding-sibling::input[@name='before_repair_name']"; //RadioButtons list
    public final String redPartailAfterRepair="xpath=//label[.='Partially Functional']/preceding-sibling::input[@name='after_repair_name']";
    public final String radAfterRepair="xpath=//label[.='Fully Operational']/preceding-sibling::input[@name='after_repair_name']";
    public final String radCompletelyDown="xpath=//label[.='Completely Down']/preceding-sibling::input[@name='after_repair_name']";
    public final String lstResolutionCode="xpath=//*[@id='resolution_code_dropdown']";//AC -Problem with Line Voltage to equipment.,Computer/Network problem,Hard Drive Repair/Replace,Hub Repair/Replace,Memory Issue Repair/Replace,Server/Workstation Reconfiguration,Software Install/Configuration/Update,Router Issue Repair/Replace,Other Peripheral Repair/Replace,System Reconfiguration
    public final String lstSymptonCode="xpath=//select[@id='cpf_symptomFirstLevelGlobal_inner']";//Image Problem - Specific Area,Print/Copy/Scan Image - Whole area,Other Image Problem,Paper Transport Problem,Original Transport Problem,Master transport/Finishing problem
    public final String lstSymptonMinerCode="xpath=//select[@id='cpf_symptomSecondLevelGlobal_inner']";//Line/band - laser scan direction
    public final String lstColor="xpath=//select[@id='cpf_symptomColorsGlobal_inner']";//Magenta,Yellow,Cyan
    public final String lstSelectArea="xpath=//select[@id='cpf_symptomAreasGlobal_inner']";//Whole area
    public final String radCauseMajorCodeMF="xpath=//*[@id='cpf_cause_machine_inner_input' and @type='radio']";
    public final String radCauseMajorCodeNMF="xpath=//*[@id='cpf_cause_not_machine_inner_input' and @type='radio']";
    public final String lstCauseCode="xpath=//select[@id='cpf_causeFirstLevelGlobal_inner']";//Machine - Mechanical Malfunction
    public final String lstCauseMinerCode="xpath=//select[@id='cpf_causeSecondLevelGlobal_inner']";//Adhering
    public final String lstActionCode="xpath=//select[@id='cpf_actionFirstLevelGlobal_inner']";//Physical Treatment
    public final String lstActionMinerCode="xpath=//select[@id='cpf_actionSecondLevelGlobal_inner']";//Mechanical adjustment
    public final String lstLocationMajorCode="xpath=//select[@id='cpf_locationFirstLevelGlobal_inner']";//Paper Feed Station
    public final String lstLcationMinerCode="xpath=//select[@id='cpf_locationSecondLevelGlobal_inner']";//1st paper feed
    public final String lstTargetMajorCode="xpath=//select[@id='cpf_targetFirstLevelGlobal_inner']";//PCB
    public final String lstTargetMinerCode="xpath=//select[@id='cpf_targetSecondLevelGlobal_inner']";//I/F board
    public final String selectSkipReasonddown="xpath=//select[@id='cpf_MeterSkipReason_inner']";//SkipReason
    
    public final String lblPreviousMeterVal="xpath=//*[@id='cpf_CounterTotalMeter_previous_read']";
    public final String txtBlackWhite="xpath=//*[@id='cpf_CounterBlackWhite_inner' and @type='text']";
    public final String lblPreviousBWVal="xpath=//*[@id='cpf_CounterBlackWhite_previous_read']";
    public final String lblPreviousColorVal="xpath=//*[@id='cpf_CounterColor_previous_read']";
    public final String txtColor="xpath=//*[@id='cpf_CounterColor_inner' and @type='text']";
   // public final String txtTotal="xpath=//*[@id='cpf_CounterTotalMeter_inner' and @type='text']";
    public final String txtTotalMeter="xpath=//input[@id='cpf_CounterTotalMeter_inner' and @type='text']";
    public final String btnActivityDispatch="xpath=//a[@id='activity_dispatch_time_override']";
    public final String rbtnSkipMeterReads="xpath=//input[@id='cpf_MeterAction_inner_skip']";

    
    public final String txtActvieTimeset="xpath=//input[@id='activityDispatchTimeCalender']";
    public final String btnStartOverRide="xpath=//a[@id='start_time_override']";
    public final String txtTimeSet="xpath=//input[@id='startTimeCalender']";//Have to set the time
    public final String txtComments="xpath=//textarea[@id='comment_section']";
    public final String btnLaborSumit="xpath=//input[@id='labor_completion_submit']";
   
    public LabourCompletionPage(OFSCWebActionEngine actEngine, OFSCTestEngine ofscObj){  
    	
    	ofscEngine = actEngine;
    	this.testObj = ofscObj;
      //  waitForPage();
    }

	public boolean labourCompletion(){
		boolean res=false;
		try{
			res=clickOnLaborCmptn();
			this.testObj.reportStatus(res,"Successfully clicked On Labor Completion","Failed to clicked On LaborCmptn");
			res=selectServiceReqst("Complete");
			this.testObj.reportStatus(res,"Successfully selected Service Reqst", "Failed to selected Service Reqst");
			res=setBeforeRepair();
			this.testObj.reportStatus(res,"Successfully set Before Repair", "Failed to set Before Repair");
			res=setAfterRepair();
			this.testObj.reportStatus(res,"Successfully set After Repair", "Failed to set After Repair");
			res=selectSymptonCode("Image Problem");//("Image Problem - Specific Area");
			this.testObj.reportStatus(res, "Successfully selected Sympton Code","Failed to selected Sympton Code");
			res=selectSymptonMinerCode("Image missing/not printed");//("Line/band - laser scan direction");
			this.testObj.reportStatus(res, "Successfully selected Sympton miner Code","Failed to selected Sympton miner Code");
			//res=selectColor("Magenta");
			res=selectSelectArea("Whole area");
			this.testObj.reportStatus(res,"Successfully selected Select Area", "Failed select SelectArea");
			res=setMachineFailure("Machine - Mechanical Malfunction");
			this.testObj.reportStatus(res, "Successfully selected lst CauseCode","Failed to select lst CauseCode");
			//res=selectlstCauseCode("Machine - Mechanical Malfunction");
			res=selectCauseMinerCode("Adhering");
			this.testObj.reportStatus(res,"Successfully selected cause miner Code","Failed to selected cause miner Code");
			res=selectlstActionCode("Physical Treatment");
			this.testObj.reportStatus(res, "Successfully clicked On Labor Cmptn","Failed to clicked On LaborCmptn");
			res=selectlstActionMinerCode("Mechanical adjustment");
			this.testObj.reportStatus(res, "Successfully selected lst ActionMinerCod","Failed to select lst ActionMinerCod");
			res=selectlstLocationMajorCode("Paper Feed Station");
			this.testObj.reportStatus(res, "Successfully selected  lstLocationMajorCode","Failed to selected  lstLocationMajorCode");
			res=selectLcationMinerCode("1st paper feed");
			 this.testObj.reportStatus(res, "Successfully selected LcationMinerCode","Failed to selected LcationMinerCode");
			res=selectTargetMajorCode("PCB");
			this.testObj.reportStatus(res,"Successfully Selected Target major code ", "Failed to Select Target major code");
			res=selectTargetMinerCode("I/F board");
			this.testObj.reportStatus(res,"Successfully selected Target Miner Code","Failed to selected Target Miner Code");
			res=enterTotalMeter();
			this.testObj.reportStatus(res, "Successfully entered Total Meter value","Failed to entered Total Meter value");
			res=clickOnLaborSubmit();	
			this.testObj.reportStatus(res,"Successfully clicked On Labor Sumit", "Failed to clicked On Labor Sumit");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the labourCompletion process-OFSC ");
		}
		return res;
	}
	public boolean IncompleteAssistConnect(){
		boolean res=false;
		try{
			res=clickOnLaborCmptn();
			this.testObj.reportStatus(res,"Successfully clicked On Labor Completion","Failed to clicked On LaborCmptn");
			res=selectServiceReqst("Incomplete Assist Connect");
			this.testObj.reportStatus(res,"Successfully selected Service Reqst", "Failed to selected Service Reqst");
			res=setBeforeRepair();
			this.testObj.reportStatus(res,"Successfully set Before Repair", "Failed to set Before Repair");
			res=setPartialAfterRepair();
			this.testObj.reportStatus(res,"Successfully set After Repair", "Failed to set After Repair");
			res=enterTotalMeter();
			this.testObj.reportStatus(res, "Successfully entered Total Meter value","Failed to entered Total Meter value");
			res=clickOnLaborSubmit();	
			this.testObj.reportStatus(res,"Successfully clicked On Labor Sumit", "Failed to clicked On Labor Sumit");
			
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the labourCompletion process-OFSC ");
		}
		return res;
	}
	public boolean IncompleteAssist(){
		boolean res=false;
		try{
			res=clickOnLaborCmptn();
			this.testObj.reportStatus(res,"Successfully clicked On Labor Completion","Failed to clicked On LaborCmptn");
			res=selectServiceReqst("Incomplete Assist");
			this.testObj.reportStatus(res,"Successfully selected Service Reqst", "Failed to selected Service Reqst");
			res=setBeforeRepair();
			this.testObj.reportStatus(res,"Successfully set Before Repair", "Failed to set Before Repair");
			res=setPartialAfterRepair();
			this.testObj.reportStatus(res,"Successfully set After Repair", "Failed to set After Repair");
			res=enterTotalMeter();
			this.testObj.reportStatus(res, "Successfully entered Total Meter value","Failed to entered Total Meter value");
			res=clickOnLaborSubmit();	
			this.testObj.reportStatus(res,"Successfully clicked On Labor Sumit", "Failed to clicked On Labor Sumit");	
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the labourCompletion process-OFSC ");
		}
		return res;
	}
	public boolean InSufficientPartsLabourCmpln(){
		boolean res=false;
		try{
			res=clickOnLaborCmptn();
			this.testObj.reportStatus(res,"Successfully clicked On Labor Completion","Failed to clicked On LaborCmptn");
			res=setBeforeRepair();
			this.testObj.reportStatus(res,"Successfully set Before Repair", "Failed to set Before Repair");
			res=setPartialAfterRepair();
			this.testObj.reportStatus(res,"Successfully set After Repair", "Failed to set After Repair");
			res=enterTotalMeter();
			this.testObj.reportStatus(res, "Successfully entered Total Meter value","Failed to entered Total Meter value");
			res=clickOnLaborSubmit();	
			this.testObj.reportStatus(res,"Successfully clicked On Labor Sumit", "Failed to clicked On Labor Sumit");	
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the labourCompletion process-OFSC ");
		}
		return res;
	}
	
	public boolean InstallDeInstall(String black, String color){
		boolean res=false;
		try{
			res=clickOnLaborCmptn();
			this.testObj.reportStatus(res, "Successfully clicked On Labor Completion", "Failed to clicked On LaborCmptn");
			res=selectServiceReqst("Complete");
			this.testObj.reportStatus(res,"Successfully selected Service Reqst", "Failed to selected Service Reqst");
			res=selectConfirmDeInstall("Yes");
			this.testObj.reportStatus(res,"Successfully selected DeInstall", "Failed to selected DeInstall");
			res=setBeforeRepair();
			this.testObj.reportStatus(res,"Successfully set Before Repair", "Failed to set Before Repair");
			res=setPartialAfterRepair();
			this.testObj.reportStatus(res,"Successfully set After Repair", "Failed to set After Repair");
			res=enterBlackWhite(black);
			this.testObj.reportStatus(res,"Successfully entered value", "Failed to enter value");
			res=enterColor(color);
			this.testObj.reportStatus(res,"Successfully entered color value", "Failed to enter color value");
			res=enterTotalMeter();
			this.testObj.reportStatus(res, "Successfully entered Total Meter value","Failed to entered Total Meter value");
			//res=enterTotalMeter();
			res=clickOnLaborSubmit();	
			this.testObj.reportStatus(res,"Successfully clicked On Labor Sumit", "Failed to clicked On Labor Sumit");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the labourCompletion process-OFSC ");
		}
		return res;
	}
	
	public boolean labourCompletionForSkipMeter(){
		boolean res=false;
		try{
			res=clickOnLaborCmptn();
			this.testObj.reportStatus(res, "Successfully clicked On Labor Completion", "Failed to clicked On LaborCmptn");
			res=selectServiceReqst("Complete");
			this.testObj.reportStatus(res, "Successfully selected Service Reqst", "Failed to selected Service Reqst");
			res=setBeforeRepair();
			this.testObj.reportStatus(res, "Successfully set Before Repair", "Failed to set Before Repair");
			res=setAfterRepair();
			this.testObj.reportStatus(res, "Successfully set After Repair", "Failed to set After Repair");
			res=selectSymptonCode("Image Problem");//("Image Problem - Specific Area");
			this.testObj.reportStatus(res, "Successfully selected Sympton Code", "Failed to selected Sympton Code");
			res=selectSymptonMinerCode("Image missing/not printed");//("Line/band - laser scan direction");
			this.testObj.reportStatus(res, "Successfully selected Sympton miner Code", "Failed to selected Sympton miner Code");
			//res=selectColor("Magenta");
			res=selectSelectArea("Whole area");
			this.testObj.reportStatus(res, "Successfully selected Select Area", "Failed select SelectArea");
			res=setMachineFailure("Machine - Mechanical Malfunction");
			this.testObj.reportStatus(res, "Successfully selected lst CauseCode", "Failed to select lst CauseCode");
			//res=selectlstCauseCode("Machine - Mechanical Malfunction");
			res=selectCauseMinerCode("Adhering");
			this.testObj.reportStatus(res, "Successfully selected cause miner Code", "Failed to selected cause miner Code");
			res=selectlstActionCode("Physical Treatment");
			this.testObj.reportStatus(res, "Successfully clicked On Labor Cmptn", "Failed to clicked On LaborCmptn");
			res=selectlstActionMinerCode("Mechanical adjustment");
			this.testObj.reportStatus(res, "Successfully selected lst ActionMinerCod", "Failed to select lst ActionMinerCod");
			res=selectlstLocationMajorCode("Paper Feed Station");
			this.testObj.reportStatus(res, "Successfully selected  lstLocationMajorCode", "Failed to selected  lstLocationMajorCode");
			res=selectLcationMinerCode("1st paper feed");
			this.testObj.reportStatus(res, "Successfully selected LcationMinerCode", "Failed to selected LcationMinerCode");
			res=selectTargetMajorCode("PCB");
			this.testObj.reportStatus(res, "Successfully Selected Major code ", "Failed to select major code");
			res=selectTargetMinerCode("I/F board");
			this.testObj.reportStatus(res, "Successfully selected Target Miner Code", "Failed to selected Target Miner Code");
			res=clickOnSkipMeterReads();
			this.testObj.reportStatus(res, "Successfully Clicked on Skip meter Reads", "Failed to Skip meter Reads");
			res=selectSkipResaon("Machine Down");
			this.testObj.reportStatus(res, "Successfully selected Skip reason from dropdown", "Failed to selected Skip reason from dropdown");
			res=clickOnLaborSubmit();
			this.testObj.reportStatus(res, "Successfully clicked On Labor Sumit", "Failed to clicked On Labor Sumit");
		}catch(Exception e){
			LogManager.logError(LoginPage.class.getName(), "Failed to complete the labourCompletion process-OFSC ");
		}
		return res;
	}
	
	public boolean clickOnLaborCmptn() {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.click(lnkLaborCmptn, "Clicked on Labour completion");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Labour completion");
			}
		return res;	
	}
	
	public boolean selectServiceReqst(String value) {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(2000);
			res=this.ofscEngine.selectByName(lstServiceRqstStatus, value, "select required field");
			Thread.sleep(2000);
			if(ofscEngine.eleDisplayed(lstConfirmDeInstall, "Confirm DeInstall list")) {
				res=this.ofscEngine.selectByName(lstConfirmDeInstall, "No", "select required field");
			}
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Service Request");
			}
		return res;	
	}
	public boolean selectConfirmDeInstall(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstConfirmDeInstall, value, "select required field");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Select Service Request");
			}
		return res;	
	}
	public boolean setBeforeRepair() {
		boolean res=false;
		try{
			ofscEngine.switchToIFrame();
			Thread.sleep(4000);
			res=this.ofscEngine.setCheckbox(radBeforeRepair, true, "select before repair");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select before Repair");
			}
		return res;	
	}
	public boolean setPartialAfterRepair() {
		boolean res=false;
		try{
			
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(redPartailAfterRepair, true, "select after repair");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select After Repair");
			}
		return res;	
	}
	public boolean setAfterRepair() {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(radAfterRepair, true, "select after repair");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select After Repair");
			}
		return res;	
	}
	public boolean setCompleteDown() {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(radCompletelyDown, true, "select complete down");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select completly down");
			}
		return res;	
	}
	public boolean selectResolutionCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstResolutionCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}	
	public boolean selectSymptonCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstSymptonCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}	
	public boolean selectSymptonMinerCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstSymptonMinerCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectColor(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstColor, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectSelectArea(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstSelectArea, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean setMachineFailure(String value) {
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(radCauseMajorCodeMF, true, "select after repair");
			res=this.ofscEngine.selectByName(lstCauseCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select After Repair");
			}
		return res;	
	}
	public boolean setNotMachineFailure(String value) { //Environment,Supply,Operator Error,Network Error,Limitation/Specification,Cause Others
		boolean res=false;
		try{
			Thread.sleep(2000);
			res=this.ofscEngine.setCheckbox(radCauseMajorCodeNMF, true, "select after repair");
			res=this.ofscEngine.selectByName(lstCauseCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to Select After Repair");
			}
		return res;	
	}
	public boolean selectlstCauseCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstCauseCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectCauseMinerCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstCauseMinerCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectlstActionCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstActionCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectlstActionMinerCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstActionMinerCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectlstLocationMajorCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstLocationMajorCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectLcationMinerCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstLcationMinerCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	public boolean selectTargetMajorCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstTargetMajorCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}

	public boolean selectTargetMinerCode(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(lstTargetMinerCode, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	
	public boolean selectSkipResaon(String value) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.selectByName(selectSkipReasonddown, value, "select required value");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to select required value");
			}
		return res;	
	}
	
	
	public boolean clickOnSkipMeterReads() {
		boolean res=false;
		try{
			Thread.sleep(1000);
			res=this.ofscEngine.waitAndClick(rbtnSkipMeterReads, "Clicked on Skip meter radio button");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Skip meter radio button");
			}
		return res;	
	}
	public boolean enterBlackWhite(String we) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtBlackWhite, we);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter required value");
			}
		return res;	
	}
	public boolean enterColor(String we) {
		boolean res=false;
		try{
			Thread.sleep(4000);
			res=this.ofscEngine.type(txtColor, we);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter required value");
			}
		return res;	
	}
	
	public boolean enterTotalMeter() {
		boolean res=false;
		try{
			
			Thread.sleep(4000);
			if(this.ofscEngine.isElementPresent(lblPreviousBWVal, true)) {
				String sBWVal=this.ofscEngine.getVisibleText(lblPreviousBWVal);
				Thread.sleep(2000);
				res=this.ofscEngine.type(txtBlackWhite, sBWVal);
			}else {
				res=this.ofscEngine.type(txtBlackWhite, "2");					
        	}
			if(this.ofscEngine.isElementPresent(lblPreviousColorVal, true)) {
				String sColorVal=this.ofscEngine.getVisibleText(lblPreviousColorVal);
				Thread.sleep(2000);
				res=this.ofscEngine.type(txtColor, sColorVal);
			}else {
				res=this.ofscEngine.type(txtColor, "2");
			}
			if(this.ofscEngine.isElementPresent(lblPreviousMeterVal, true)) {
			String Val=this.ofscEngine.getVisibleText(lblPreviousMeterVal);
			Thread.sleep(2000);
			res=this.ofscEngine.type(txtTotalMeter, Val);
			}
			else {
				res=this.ofscEngine.type(txtTotalMeter, "4");
			}
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to enter required value");
			}
		return res;	
	}
	
	public boolean clickOnActiveDispatch() {
		boolean res=false;
		try{
			Thread.sleep(1000);
			res=this.ofscEngine.waitAndClick(btnActivityDispatch, "Clicked on Active dispatch");
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on Active dispatch");
			}
		return res;	
	}

	
	public String getActiveTime() {
		String value= null ;
	        try {
	        	Thread.sleep(4000);
	        	value=this.ofscEngine.getAttributeValue(txtActvieTimeset, "value");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to get the attribute value");
			}
	return value ;
	}
	
	
	public boolean clickOnStartOverRide() {
		boolean res=false;
		try{
			Thread.sleep(1000);
		//	res=this.ofscEngine.waitAndClick(btnStartOverRide, "Clicked on start over ride");
			WebElement element = this.ofscEngine.driver.findElement(By.xpath("//a[@id='start_time_override']"));
			JavascriptExecutor executor = (JavascriptExecutor) this.ofscEngine.driver;
			executor.executeScript("arguments[0].click();", element);
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on start over ride");
			}
		return res;	
	}

	
	public String getStartTime() {
		String value= null ;
	        try {
	        	Thread.sleep(4000);
	        	value=this.ofscEngine.getAttributeValue(txtTimeSet, "capture the start time");
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to get the attribute value");
			}
	return value ;
	}
	
	
	
	public boolean enterUpdatedTime() {
		boolean res= false ;
	        try {
	        Thread.sleep(1000);
	        	this.ofscEngine.click(txtTimeSet, "click on start time set");
	        	Thread.sleep(2000);
	        	this.ofscEngine.driver.findElement(By.id("startTimeCalender")).sendKeys(Keys.DOWN,Keys.RETURN);
	        	Thread.sleep(2000);
	        	/*this.ofscEngine.driver.findElement(By.id("startTimeCalender")).sendKeys(Keys.LEFT,Keys.RETURN);
	        	Thread.sleep(5000);
	        	this.ofscEngine.driver.findElement(By.id("startTimeCalender")).sendKeys(Keys.DOWN,Keys.RETURN);
	        	Thread.sleep(5000);*/
//	        	this.ofscEngine.driver.findElement(By.id("startTimeCalender")).sendKeys(Keys.TAB);
//	        	this.ofscEngine.driver.findElement(By.id("startTimeCalender")).sendKeys(Keys.DOWN,Keys.RETURN);
	        	res=true;
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter updated time");
			}
	return res ;
	}
	public boolean enterComments(String Comments) {
		boolean res= false ;
	        try {
	        	res= this.ofscEngine.type(txtComments, Comments);
			} catch (Exception e) {
				LogManager.logError(LoginPage.class.getName(), "Failed to enter Comments");
			}
	return res ;
	}
	
	public boolean clickOnLaborSubmit() {
		boolean res=false;
		try{
			Thread.sleep(3000);
			//res=this.ofscEngine.clickusingJavaScript(btnLaborSumit);
			ofscEngine.ScrollWeb();
			res=this.ofscEngine.click(btnLaborSumit, "btnLaborSumit");
			//this.ofscEngine.isAlertPresent();	
			  if (ofscEngine.isAlertPresent()){
				  Thread.sleep(3000);
				  res=clickOnStartOverRide(); 
				  res=enterUpdatedTime(); 
				  Thread.sleep(8000);
				  res=this.ofscEngine.click(btnLaborSumit, "btnLaborSumit");
				  Thread.sleep(2000);
				  }
			 
			/*else{
				res=clickOnStartOverRide();
				res=enterUpdatedTime();
				res=this.ofscEngine.click(btnLaborSumit, "btnLaborSumit");
			}*/
				this.ofscEngine.switchToDefaultFrame();
			}catch(Exception e){
				LogManager.logError(LoginPage.class.getName(), "Failed to click on LaborSumit");
			}
		return res;	
	}
	
}