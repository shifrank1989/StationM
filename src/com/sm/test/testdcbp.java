package com.sm.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sm.db.DBManager;

public abstract class testdcbp {
	static PreparedStatement ps=null;
	static ResultSet rs=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long begin=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            Connection conn=DBManager.getConn();
           // System.out.print(i+"   ");
            try {
				ps=conn.prepareStatement("select name from T_ZhanMingLueHao where code='XXT'");
				rs=ps.executeQuery();
				while(rs.next()){
					String s=rs.getString(1);
					System.out.println(s);
				}
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DBManager.closeConn(conn);
        }
        long end=System.currentTimeMillis();
        System.out.println("ÓÃÊ±£º"+(end-begin));
	}

}
