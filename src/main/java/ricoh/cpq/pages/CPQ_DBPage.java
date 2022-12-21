package ricoh.snow.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ricoh.web.engine.OFSCDBCon;
import ricoh.web.engine.OFSCWebActionEngine;

public class Snow_DBPage {

	private OFSCWebActionEngine snowEngine = null;
	private OFSCDBCon objDBCon = null;
	public Snow_DBPage(OFSCWebActionEngine actEngine){
		this.snowEngine = actEngine;
		objDBCon = new OFSCDBCon();	
	}
	boolean res;
	
	public String fnGetSNOWRequestID(String DBName, String sOrderNum) {
		String queryString = "select * from ikncu.RAC_CI2057_SO_HDR_STG where order_number = '"+sOrderNum+"'";
		objDBCon.establishDatabaseConnection(DBName);		
		List<Map<String, String>> list = objDBCon.fnGetDataFromDB(queryString);
		for(Map<String, String> mpData : list) {
			for(Map.Entry<String, String> entry : mpData.entrySet()) {
				if(entry.getKey().equals("REQUEST_ID")) return entry.getValue();
			}
		}
		return null;
	}
	
	public String fnGetSNOWProjectID(String DBName, String sOrderNum) {
		String queryString = "select * from ikncu.rac_CI1558_ps_order_header where Oracle_SO_number  ='"+sOrderNum+"'";
		objDBCon.establishDatabaseConnection(DBName);	
		String sProjectID = "";
		HashMap<String,String> dbOracleOrderDetails = new HashMap<String,String>();
		List<Map<String, String>> list = objDBCon.fnGetDataFromDB(queryString);
		for(Map<String, String> mpData : list) {
			for(Map.Entry<String, String> entry : mpData.entrySet()) {
				if(entry.getKey().equals("ORD_HEADER_STATUS")) dbOracleOrderDetails.put("ORD_HEADER_STATUS", entry.getValue());					
				if(entry.getKey().equals("PROJECT_ID")) dbOracleOrderDetails.put("PROJECT_ID",entry.getValue() );
				if(entry.getKey().equals("PROJECT_NAME")) dbOracleOrderDetails.put("PROJECT_NAME",entry.getValue() );	
				if(entry.getKey().equals("PROJECT_ID")) dbOracleOrderDetails.put("PROJECT_ID",entry.getValue() );
				if(entry.getKey().equals("PROJECT_ID")) dbOracleOrderDetails.put("PROJECT_ID",entry.getValue() );
				if(entry.getKey().equals("PROJECT_ID")) dbOracleOrderDetails.put("PROJECT_ID",entry.getValue() );
				if(entry.getKey().equals("PROJECT_ID")) dbOracleOrderDetails.put("PROJECT_ID",entry.getValue() );
				if(entry.getKey().equals("PROJECT_ID")) dbOracleOrderDetails.put("PROJECT_ID",entry.getValue() );
				
			}
		}
		return null;
	}
}
