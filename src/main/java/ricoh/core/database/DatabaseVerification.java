package ricoh.core.database;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import ricoh.config.gblConstants;

public class DatabaseVerification {
	
	String strDBDriverName = "" ;
	String strDBConnectString = "" ;
	String strDBServerName = "";
	String strDBName = "";
	String strDBUser = "";
	String strPassword = "";
	String strDBType="";
	Connection connection=null;
	
	public DatabaseVerification(String strDBType, String strDBServer,String strDBName, String strUserName, String strPassword){
		this.strDBType=strDBType;				
		switch (strDBType.toLowerCase()) {
			case "oracle"	: this.strDBDriverName = gblConstants.dbDriver_Oracle; break;
			case "sqlserver" 	: this.strDBDriverName = gblConstants.dbDriver_SQLServer; break;
		}
		this.strDBServerName = strDBServer.trim();
		this.strDBName = strDBName.trim();
		this.strDBUser = strUserName.trim();
		this.strPassword = strPassword.trim();
	}		

	public DatabaseVerification(String strDBType, String strDBServer, String strUserName, String strPassword){
		this.strDBType=strDBType;				
		switch (strDBType.toLowerCase()) {
			case "oracle" 		: this.strDBDriverName = gblConstants.dbDriver_Oracle; break;
			case "sqlserver" 	: this.strDBDriverName = gblConstants.dbDriver_SQLServer; break;
		}
		this.strDBServerName = strDBServer.trim();
		this.strDBName = strDBName.trim();
		this.strDBUser = strUserName.trim();
		this.strPassword = strPassword.trim();
	}	
	
	public Connection getConnection(String strDBType) {
		try {
			Class.forName(strDBDriverName);
			if(strDBType.equalsIgnoreCase("MySQL") || strDBType.equalsIgnoreCase("SQLSERVER")) {
				String connectionUrl = strDBServerName + "; databaseName=" + strDBName + ";" + "user=" + this.strDBUser + ";"
						+ "password= "+ this.strPassword;
				connection = DriverManager.getConnection(connectionUrl);
			}else if(strDBType.equalsIgnoreCase("Oracle")) {
				connection = DriverManager.getConnection(strDBServerName,strDBUser,strPassword);
			}else {
				System.out.println("Check for correct Database Type....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;		
	}
	
	
	public boolean closeConnection() {
		
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet getQueryResponse(String queryString) throws Exception {
		Statement statement = getConnection(strDBType).createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		return rs;
	}

	/*public ResultSet getQueryResponse(String strServerName, String strDB, String queryString) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//updateProperty();
		String connectionUrl = strServerName + "; databaseName=" + strDB + ";" + "user=" + this.strDBUser + ";"
				+ "password= "+ this.strPassword;
		Connection conn = DriverManager.getConnection(connectionUrl);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(queryString);
		return rs;
	}*/
	
	/**
	 * @author Satya, Gajula
	 * @param rs
	 * @return
	 */
	public List<Map<String, String>> getResultAsList(ResultSet rs) {
		List<Map<String, String>> resultSet = new ArrayList<Map<String, String>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String, String> rowData= new HashMap<String, String>();
				for (Integer i = 1; i <= rsmd.getColumnCount(); i++) {
					rowData.put(rsmd.getColumnLabel(i), rs.getString(i));
				}
				resultSet.add(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}


//	private String get12HrTime(String s) throws ParseException{
//		DateFormat f1 = new SimpleDateFormat("hh:mm");
//		Date d = f1.parse(s);
//		DateFormat f2 = new SimpleDateFormat("h:mma");
//		f2.format(d).toLowerCase();
//		return f2.format(d).toUpperCase();
//
//	}

	/**
	 * @author Satya, Gajulas
	 * @param DBName
	 * @param insertStatement
	 * @throws Exception
	 */
	public void insertInToDataBase(String insertStatement)
			throws Exception {
		Statement statement = getConnection(strDBType).createStatement();
		statement.executeUpdate(insertStatement);
	}
	
	/**
	 * @author Satya, Gajula
	 * @return Update Query returns the no of row affected
	 * @throws Exception
	 */
	public void insertInToDataBase(String strServerName, String strDB, String insertStatement)
			throws Exception {
		Statement statement = getConnection(strDBType).createStatement();
		statement.executeUpdate(insertStatement);
	}	

	/**
	 * @author Satya, Gajula
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public int executeUpdateQuery(String strUpdateStatement) throws Exception {
		int rowAffected;
		Statement statement = getConnection(strDBType).createStatement();
		rowAffected= statement.executeUpdate(strUpdateStatement);
		return rowAffected;
	}
	
	/**
	 * @author Satya, Gajula
	 * @return Update Query returns the no of row affected
	 * @throws Exception
	 */
	public int executeUpdateQuery(String strServerName, String strDB, String strUpdateStatement)
			throws Exception {
		int rowAffected;
		Statement statement = getConnection(strDBType).createStatement();
		rowAffected= statement.executeUpdate(strUpdateStatement);
		return rowAffected;
	}
	
	/**
	 * @author Jagadeesh, Boddeda
	 * @param Result set object and Column name of BLOB
	 * @return Returns the blob data as String
	 * @throws Exception
	 */
	public String getBlobData(ResultSet OrcResultSet, String columnName) {
		String blobData = null;
		try {
			 ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024*5]; //memory allocated
				while (OrcResultSet.next()) {
				      InputStream inpStream = OrcResultSet.getBinaryStream(columnName);
				      while (inpStream.read(buffer) > 0) {
				    	  outStream.write(buffer,0,buffer.length);
				      }
				      blobData = new String(outStream.toByteArray(),Charset.defaultCharset()).replace("<br />", "").replace("<br/>", "");
				} 
				 outStream.close();
				} catch (Exception e) {
					e.printStackTrace();
					return blobData;
				}
				return blobData;
	}
	
	/**
	 * @author Jagadeesh, Boddeda
	 * @param Result set object, Column name of Date field and required Date format
	 * @return Returns the String date as per the input format
	 * @throws Exception
	 */
	public String readDateColumn(ResultSet OrcResultSet, String ColumnName, String DateFormat) {
		String newDate = null;
		try {
	    	OrcResultSet.next();
			Date date = OrcResultSet.getDate(ColumnName);
			DateFormat dateFormat = new SimpleDateFormat(DateFormat);
			newDate = dateFormat.format(date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	protected void finalize() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("SQL Exception to close db connection " + e.getMessage());
			e.printStackTrace();
		}
	}
}