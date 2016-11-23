/**
 * 本地数据库连接
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
		    System.out.println("数据库连接成功");   
		    } 
		catch (ClassNotFoundException e) 
		{   
			e.printStackTrace();    
			System.out.println("没有找到jdbc驱动");
	     } 
		catch (SQLException e) 
		{    
			e.printStackTrace();   
		}   return con;  
		} 

}
