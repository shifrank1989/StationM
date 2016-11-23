package com.sm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.spec.PSource;

public class DBhelper {
	Connection ct;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
    public DBhelper(){
	   //DBConection dbc = new DBConection();   
	  // ct = dbc.getConnection();
    	 ct=DBManager.getConn();
    }
    public ResultSet query(String sql,String[] paras){
    	try {
			ps=ct.prepareStatement(sql);
			
			for(int i=0;i<paras.length;i++){
				
			  ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return rs;
    }
    public ResultSet query(String sql){
    	try {
			ps=ct.prepareStatement(sql);
			
			/*for(int i=0;i<paras.length;i++){
				
			  ps.setString(i+1, paras[i]);
			}*/
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return rs;
    }
    public  boolean CheckExist(String sql,String b ) throws SQLException{
    	boolean c=true;
    	
    	try {
			rs = this.query(sql, new String[] { b });
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(rs.next()){
			//´æÔÚ
    	    c=true;
    	}else{
    		//²»´æÔÚ
    		c=false;
    	}
    	return c;
    }
    public void  close(){
    	try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) DBManager.closeConn(ct);;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
