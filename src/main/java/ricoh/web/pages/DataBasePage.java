package ricoh.web.pages;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ricoh.config.gblConstants;
import ricoh.core.logs.LogManager;
import ricoh.web.engine.OFSCWebActionEngine;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import ricoh.config.gblConstants;
import ricoh.web.engine.OFSCDBCon;

public class DataBasePage {

	private OFSCWebActionEngine ofscEngine = null;
    private String getValue=null;
	OFSCDBCon dbcon = new OFSCDBCon();

    //Locators
    public final String lnkArrived="xpath=//a[contains(text(),'Arrived')]";
   
    public DataBasePage(OFSCWebActionEngine actEngine){
    	
    	ofscEngine = actEngine;
      //  waitForPage();
    }
    
    
    
    
  //DataBase P1-2 T.C
	public boolean punchInDbPunchTypeValidation(String Eid) {
		boolean res = false;
		dbcon.establishDatabaseConnection(gblConstants.DBUAT);
		try {
			Thread.sleep(5000);
			//System.out.println("select * FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by a.creation_date desc");
			ResultSet rs=dbcon.dbConnection.getQueryResponse("select SOURCE_ID FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by a.creation_date desc");
			//ResultSet rs = dbcon.dbConnection.getQueryResponse("select * from (select employee_id,time_entry_date, to_char(punch_time,'DD-MON-YYYY HH:MI:SS'), punch_type from apps.rac_fs_eta_time_tracking where employee_id = '"+Eid+"' order by punch_time desc) where rownum <2");
			//ResultSet rs = dbcon.dbConnection.getQueryResponse("select * FROM apps.rac_fs_eta_time_tracking a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by time_entry_date desc");
			List<Map<String, String>> lrs = dbcon.dbConnection.getResultAsList(rs);
             int x=lrs.size();
             System.out.println(x);       	 
             String y=lrs.get(0).get("RECORD_TYPE");
             System.out.println(y);
             if(y=="In Punch") {
            	 System.out.println("Punchin completed successfully");
             }
             /*int z=Integer.parseInt(y);
             if(z==1|z==2) {
                 System.out.println("passed");
                 res = true;
                 }else {
                     System.out.println("failed");
                 }*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogManager.logError(LoginPage.class.getName(), "Punch type is invalid");
			e.printStackTrace();
		}finally {
			dbcon.dbConnection.closeConnection();
		}

		return res;
	}
	
	// DataBase P1-2 T.C
	public boolean punchInTimeValidation(String Eid) {
		boolean res = false;
		//dbcon.establishDatabaseConnection(gblConstants.DBUAT);
		try {
			//ResultSet rs=dbcon.dbConnection.getQueryResponse("select * FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by a.creation_date desc");
			//ResultSet rs=dbcon.dbConnection.getQueryResponse("select * FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id order by a.creation_date desc");
			ResultSet rs=dbcon.dbConnection.getQueryResponse("select SOURCE_ID FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by a.creation_date desc");
			List<Map<String, String>> lrs = dbcon.dbConnection.getResultAsList(rs);		
			String date1 = lrs.get(0).get("TIME_ENTRY");
			System.out.println("Punch In Date" + date1);
			res=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogManager.logError(LoginPage.class.getName(), "Punch time not matched");
			e.printStackTrace();
		}finally {
			dbcon.dbConnection.closeConnection();
		}

		return res;
	}
	
	//DataBase P1-2T.C
		public boolean punchOutDbPunchTypeValidation(String Eid) {

			boolean res = false;
			//dbcon.establishDatabaseConnection(gblConstants.DBUAT);
			try {
				Thread.sleep(50000);
				//ResultSet rs = dbcon.dbConnection.getQueryResponse("select * from (select employee_id,time_entry_date, to_char(punch_time,'DD-MON-YYYY HH:MI:SS'), punch_type from apps.rac_fs_eta_time_tracking where employee_id = '"+Eid+"' order by punch_time desc) where rownum <2");
				//ResultSet rs = dbcon.dbConnection.getQueryResponse("select * from (select employee_id,time_entry_date, to_char(punch_time,'DD-MON-YYYY'), punch_type from apps.rac_fs_eta_time_tracking where employee_id = '"+Eid+"' order by punch_time desc) where rownum <2");
				//ResultSet rs = dbcon.dbConnection.getQueryResponse("select * FROM apps.rac_fs_eta_time_tracking a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by time_entry_date desc");
				ResultSet rs=dbcon.dbConnection.getQueryResponse("select * FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by a.creation_date desc");
				 List<Map<String, String>> lrs = dbcon.dbConnection.getResultAsList(rs);
	             int x=lrs.size();
	             System.out.println(x);       	 
	             String y=lrs.get(0).get("RECORD_TYPE");
	             System.out.println(y);
	             if(y=="Out Punch") {
	            	 System.out.println("Punchin completed successfully");
	             }
	             /*int z=Integer.parseInt(y);
	             if(z==2|z==1) {
	                 System.out.println("passed");
	                 res = true;
	                 }else {
	                     System.out.println("failed");
	                 }*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LogManager.logError(LoginPage.class.getName(), "Failed to click on PunchInSubmit");
				e.printStackTrace();
			}finally {
				dbcon.dbConnection.closeConnection();
			}
			return res;
		}

		
	// DataBase P1-2 T.C
	public boolean punchOutTimeValidation(String Eid) {
		boolean res = false;
		//dbcon.establishDatabaseConnection(gblConstants.DBUAT);
		try {
			//ResultSet rs = dbcon.dbConnection.getQueryResponse("select * FROM apps.rac_fs_eta_time_tracking a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by time_entry_date desc");
			ResultSet rs=dbcon.dbConnection.getQueryResponse("select * FROM ikncu.RAC_OFSC_ADP_TIME_PUNCH a, apps.jtf_rs_resource_extns b WHERE employee_id = source_number AND resource_number IN ('"+Eid+"') AND b.source_number = a.employee_id AND rownum<2 order by a.creation_date desc");
			List<Map<String, String>> lrs = dbcon.dbConnection.getResultAsList(rs);
			
		
			String date2 = lrs.get(0).get("TIME_ENTRY");
			System.out.println("Punch Out Date" + date2);
			res=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogManager.logError(LoginPage.class.getName(), "Punch time not matched");
			e.printStackTrace();
		}finally {
			dbcon.dbConnection.closeConnection();
		}
		return res;
	}
}