/**
 * �������ݿ�����
 */
package com.sm.db;
import java.sql.*;;

public class DBConection {
	public static Connection getConnection(){
		Connection con=null;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url  = "jdbc:sqlserver://localhost:1433;DatabaseName=RMS_Station";   
		String name = "sa";   String password = "123456";   
		try {    
			Class.forName(driver);    
		    con = DriverManager.getConnection(url,name,password);    
		    System.out.println("���ݿ����ӳɹ�");   
		    } 
		catch (ClassNotFoundException e) 
		{   
			e.printStackTrace();    
			System.out.println("û���ҵ�jdbc����");
	     } 
		catch (SQLException e) 
		{    
			e.printStackTrace();   
		}   return con;  
		} 

}
