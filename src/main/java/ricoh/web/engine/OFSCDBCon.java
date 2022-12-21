package ricoh.web.engine;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import ricoh.config.gblConstants;
import ricoh.core.database.DatabaseVerification;

public class OFSCDBCon {
	
	
	public DatabaseVerification dbConnection = null ;

    public ResultSet resultSet = null;

   /*public OFSCDBCon (){
	   
	   try {

		   DBConnection = new DatabaseVerification(

                        gblConstants.suiteConfig.getdatabaseType(gblConstants.DBTEST),

                        gblConstants.suiteConfig.getdatabaseServer(gblConstants.DBTEST),

                        gblConstants.DBTEST,

                        gblConstants.suiteConfig.getdatabaseLoginUser(gblConstants.DBTEST),

                        gblConstants.suiteConfig.getdatabaseLoginPassword(gblConstants.DBTEST)

           );    
 

    } catch (Exception e ){

           System.out.println("Exception" + e.getMessage());

    }

   
   }*/
    
    
	public void establishDatabaseConnection(String dbname){

        switch(dbname){

            case "TEST2":

                dbConnection = new DatabaseVerification(

                        gblConstants.suiteConfig.getdatabaseType(gblConstants.DBTEST),

                        gblConstants.suiteConfig.getdatabaseServer(gblConstants.DBTEST),

                        gblConstants.suiteConfig.getdatabaseLoginUser(gblConstants.DBTEST),

                        gblConstants.suiteConfig.getdatabaseLoginPassword(gblConstants.DBTEST));

                break;

            case "UATE":

                dbConnection = new DatabaseVerification(

                        gblConstants.suiteConfig.getdatabaseType(gblConstants.DBUAT),

                        gblConstants.suiteConfig.getdatabaseServer(gblConstants.DBUAT),

                        gblConstants.suiteConfig.getdatabaseLoginUser(gblConstants.DBUAT),

                        gblConstants.suiteConfig.getdatabaseLoginPassword(gblConstants.DBUAT));
                System.out.println(dbConnection);
                
                break;
           
            case "SAND2":

                dbConnection = new DatabaseVerification(

                        gblConstants.suiteConfig.getdatabaseType(gblConstants.DBSAND2),

                        gblConstants.suiteConfig.getdatabaseServer(gblConstants.DBSAND2),

                        gblConstants.suiteConfig.getdatabaseLoginUser(gblConstants.DBSAND2),

                        gblConstants.suiteConfig.getdatabaseLoginPassword(gblConstants.DBSAND2));
                System.out.println(dbConnection);
                
                break;
                
         }
        
	}
	
	public List<Map<String, String>> fnGetDataFromDB(String queryString) {
		 List<Map<String, String>> list= null;
			try {
				resultSet = dbConnection.getQueryResponse(queryString);
				System.out.println(resultSet.toString());
		        list = dbConnection.getResultAsList(resultSet);
		        System.out.println(list);		      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;	
	}

}
