package com.sm.view;

import java.sql.ResultSet;

import com.sm.db.DBhelper;

public class userModle {

	public String checkuser(String username,String password){
		String admins="";
		DBhelper dbh = null;
		try {
			String sql="select admins from T_User where name=? and password=?";
			String[] paras={username,password};
			dbh=new DBhelper();
			ResultSet rs=dbh.query(sql, paras);
			if(rs.next()){
				admins=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			dbh.close();
		}
		return admins;
	}
}
