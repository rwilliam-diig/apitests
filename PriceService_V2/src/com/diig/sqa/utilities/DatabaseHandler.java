package com.diig.sqa.utilities;

import java.sql.DriverManager ;
import java.sql.Statement ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Connection ;
import java.util.ArrayList;
import java.util.Map;

import com.diig.sqa.setup.TestProperties;

public class DatabaseHandler {

	 Connection m_Connection = null;
     Statement m_Statement = null;
     ResultSet m_ResultSet = null;
     int i_ResultSet = 0;
     String m_Driver = "com.mysql.jdbc.Driver";
     //String m_Driver = "com.microsoft.sqlserver.jdbc.DriverJDBCVersion";
     String m_Url = "jdbc:mysql://localhost/prsdb";
     
     TestProperties properties = new TestProperties("resources/test.properties");
     
     static Object obj = DatabaseHandler.class.getName();

     public void DataBaseHandler(){
    	 try {
             Class.forName(m_Driver);
         } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
         }
     }
     
     /**
      * This method retrieves the database url based on the environment
      * specified in the test.properties file
      * 
      * @param strEnvironment
      * @return
      */
     private String getDbUrl(String strEnvironment){
    	 String strUrl="";
    	 
    	 switch(strEnvironment){
    	 	case "priceService":
    	 		strUrl = properties.ReadProperty(obj.toString(), "price_service_db");
    		break;
    	 	case "staging":
    	 		strUrl = properties.ReadProperty(obj.toString(), "sf_staging_db");
    	 	break;
    	 	case "uat":
    	 		strUrl = properties.ReadProperty(obj.toString(), "sf_uat_db");
    	 	break;

    	 }
    	 
    	 return strUrl;
     }
     
     
     public String getDbName(){
    	 return properties.ReadProperty(obj.toString(),"db_name");
     }
     /**
      * This method takes in a query string and executes the query, and 
      * returns the results as a string
      * @param strQuery
      * @return
      */
     public String runQuery(String strQuery){

         String strResults="";
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:mysql://"+getDbUrl("priceService"),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pass"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //Execute the query
             m_ResultSet = m_Statement.executeQuery(strQuery);
             
             //Loop through the results
             while (m_ResultSet.next()) {
            	 strResults = strResults+m_ResultSet.getString(1);
             	}
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return strResults;
     }
     
     /**
      * This method takes in a query string and executes the query
      * @param strQuery
      * @return
      */
     public int runUpdateQuery(String strQuery){

         int iResults=0;
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:mysql://"+getDbUrl("priceService"),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pass"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //Execute the query
             iResults = m_Statement.executeUpdate(strQuery);
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return iResults;
     }
     
     
     
     public int runCreateTable(String strDatabaseName){

         int iResults=0;
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:mysql://"+getDbUrl("priceService"),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pass"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             String strQuery = "CREATE DATABASE IF NOT EXISTS "+strDatabaseName+";";
             //Execute the query
             iResults = m_Statement.executeUpdate(strQuery);
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return iResults;
     }
     
     /**
      * This method drops the specified database if it exists
      * @param strQuery
      * @return
      */
     public int runDropTable(String strDatabaseName){

         int iResults=0;
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:mysql://"+getDbUrl("priceService"),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pass"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //String strQuery = "DROP DATABASE IF EXISTS "+strDatabaseName+" CASCADE;";
             String strQuery = "DROP DATABASE IF EXISTS "+strDatabaseName;
             //Execute the query
             iResults = m_Statement.executeUpdate(strQuery);
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return iResults;
     }
     
     /**
      * This method takes in a query string and executes the query, and 
      * returns the results as a string
      * @param strQuery
      * @return
      */
     public String runQuery1(String strQuery){

         String strResults="";
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:sqlserver://"+getDbUrl(properties.ReadProperty(obj.toString(), "environment")),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pword"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //Execute the query
             m_ResultSet = m_Statement.executeQuery(strQuery);
             
             //Loop through the results
             while (m_ResultSet.next()) {
            	 strResults = strResults+m_ResultSet.getString(1);
             	}
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return strResults;
     }
     
     
     /**
      * This method takes in a query string and executes the query, and 
      * returns the results as a string
      * @param strQuery
      * @return
      */
     public ArrayList<String> runQueryMultipleRows(String strQuery){

         String strResults="";
         
         ArrayList<String> resultList = new ArrayList<String>();
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:sqlserver://"+getDbUrl(properties.ReadProperty(obj.toString(), "environment")),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pword"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //Execute the query
             m_ResultSet = m_Statement.executeQuery(strQuery);
             
             //Loop through the results
             while (m_ResultSet.next()) {
                 resultList.add(m_ResultSet.getString(1));  
             	}
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return resultList;
     }
     
     
     
     
     /**
      * This method takes in a query string and executes the query, and 
      * returns the results as a string
      * @param strQuery
      * @return
      */
     public String runSingleSingleQuery(String strQuery){

         String strResults="";
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:sqlserver://"+getDbUrl(properties.ReadProperty(obj.toString(), "environment")),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pword"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //Execute the query
             m_ResultSet = m_Statement.executeQuery(strQuery);
             
             //Loop through the results
             while (m_ResultSet.next()) {
            	 strResults = strResults+m_ResultSet.getString(1);
             	}
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return strResults;
     }
     
     /**
      * This method takes in the query string and number of columns contained in the query string,
      * executes the query and returns the results in an array
      * @param strQuery
      * @param iNumberOfColumns
      * @return Array
      */
     public String [] runMultipleColumnsQuery(String strQuery,int iNumberOfColumns){

         String strResults="";
         try {
             //Create connection object
             m_Connection = DriverManager.getConnection("jdbc:sqlserver://"+getDbUrl(properties.ReadProperty(obj.toString(), "environment")),
            		 properties.ReadProperty(obj.toString(), "db_user"), 
            		 properties.ReadProperty(obj.toString(), "db_pword"));

             //Create Statement object
             m_Statement = m_Connection.createStatement();
             
             //Execute the query
             m_ResultSet = m_Statement.executeQuery(strQuery);
             
             //Loop through the results
             while (m_ResultSet.next()) {
            	 switch(iNumberOfColumns){
            	 	case 1:
            	 		strResults = strResults+m_ResultSet.getString(1)+",";
            		break;
            	 	case 2:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2);
            	 	break;
            	 	case 3:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3);
            	 	break;
            	 	case 4:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4);
            	 	break;
            	 	case 5:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5);
            	 	break;
            	 	case 6:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6);
            	 	break;
            	 	case 7:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7);
            	 	break;
            	 	case 8:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8);
            	 	break;
            	 	case 9:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9);
            	 	break;
            	 	case 10:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9)+","+m_ResultSet.getString(10);
            	 	break;
            	 	case 11:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9)+","+m_ResultSet.getString(10)+","+m_ResultSet.getString(11);
            	 	break;
            	 	case 12:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9)+","+m_ResultSet.getString(10)+","+m_ResultSet.getString(11)+","+m_ResultSet.getString(12);
            	 	break;
            	 	case 13:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9)+","+m_ResultSet.getString(10)+","+m_ResultSet.getString(11)+","+m_ResultSet.getString(12)+","+m_ResultSet.getString(13);
            	 	break;
            	 	case 14:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9)+","+m_ResultSet.getString(10)+","+m_ResultSet.getString(11)+","+m_ResultSet.getString(12)+","+m_ResultSet.getString(13)+","+m_ResultSet.getString(14);
            	 	break;
            	 	case 15:
            	 		strResults = strResults+m_ResultSet.getString(1)+","+m_ResultSet.getString(2)+","+m_ResultSet.getString(3)+","+m_ResultSet.getString(4)+","+m_ResultSet.getString(5)+","+m_ResultSet.getString(6)+","+m_ResultSet.getString(7)+","+m_ResultSet.getString(8)
            	 		+","+m_ResultSet.getString(9)+","+m_ResultSet.getString(10)+","+m_ResultSet.getString(11)+","+m_ResultSet.getString(12)+","+m_ResultSet.getString(13)+","+m_ResultSet.getString(14)+","+m_ResultSet.getString(15);
            	 	break;
            	 	}
             	}
         	} 
         catch (SQLException ex) {
             //ex.getMessage();
             System.out.println(ex.getMessage());
         	} 
         finally {
             try {
                 if (m_ResultSet != null) {
                     m_ResultSet.close();
                 }
                 if (m_Statement != null) {
                     m_Statement.close();
                 }
                 if (m_Connection != null) {
                     m_Connection.close();
                 }
             	} 
             catch (SQLException ex) {
                 ex.getMessage();
             	}
         	}
     return strResults.split(",");
     }
     
     public String constructCreateTableQuery(Map<Integer,String> mMap, int iRow,String strTablename){
    	 String strQuery="";

    	 String [] aData = mMap.get(iRow).split(":");
    	 
    	 for(int a=0;a<aData.length;a++){
    		 if(a==0){strQuery="Create table "+strTablename+" ("+aData[a];}
    		 if(isItEven(a)==true && a > 0){
    			 strQuery = strQuery +" "+aData[a];
    		 }
    		 if(isItEven(a)==false && a > 0){
    			 switch(aData[a].toLowerCase()){
	    			 case "string":
	    				 strQuery = strQuery + " varchar (255)";
	    				break;
	    			 case "number":
	    				 strQuery = strQuery + " int";
	    				break;
	    			 case "date":
	    				 strQuery = strQuery + " DATE";
	    				break;
	    			 }
    			 if(a == aData.length - 1){
    				 strQuery = strQuery+");";
    			 }
    			 else{
    				 strQuery = strQuery+",";
    			 	}
    		 	}
    	 }
    	 
    	 return strQuery;
     }
     
     public boolean isItEven(int iRowNum){
    	 if(iRowNum % 2 == 0){
    		 return true;
    	 }
    	 else{
    		 return false;
    	 }
     }
}
