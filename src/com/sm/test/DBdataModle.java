package com.sm.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.print.attribute.Size2DSyntax;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sm.db.DBConection;

public class DBdataModle {
	static Connection ct=null;
	static PreparedStatement ps=null;
	static PreparedStatement ps1=null;
	static PreparedStatement ps2=null;
	static ResultSet rs=null;
	Vector Tm_hang;
	Vector Tm_rs;

	
	//创建临时表并传入数据
	public DBdataModle(String xmlString){
		//创建表
		String tmsql="create table ##Tm_Table"
				+ "(uid varchar(50),satname varchar(50),daima varchar(15),bsname varchar(50),code varchar(15))";
		DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		try {
				ps=ct.prepareStatement(tmsql);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		GetDBDataModelOne dm=new GetDBDataModelOne(xmlString);
		Vector t=new Vector();
		t=dm.getRs();
		
		//插入数据
		int p;
		p=t.size();
		System.out.println("大小："+p);
		//System.out.println("试验"+((Vector)t.get(0)).get(0).toString());
		//System.out.println("插入数据："+((Vector)t.firstElement()).firstElement().toString());
		for(int i=6;i<t.size();i++){
			String sql="insert into ##Tm_Table values (?, ?, ?, ?, ?)";
			try {
				
				ps=ct.prepareStatement(sql);
				//System.out.println("插入数据："+((Vector)t.get(1)).get(0).toString());
				ps.setString(1, ((Vector)t.get(i)).get(0).toString());
				ps.setString(2, ((Vector)t.get(i)).get(1).toString());
				ps.setString(3, ((Vector)t.get(i)).get(2).toString());
				ps.setString(4, ((Vector)t.get(i)).get(3).toString());
				ps.setString(5, ((Vector)t.get(i)).get(4).toString());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//
		String sele = "select * from ##Tm_Table" ;
		try {
			ps=ct.prepareStatement(sele);
			rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("数据库中取出(唯一标识码)："+rs.getString(1));
				System.out.println("数据库中取出(车站名称)："+rs.getString(2));
				System.out.println("数据库中取出(车站电报码)："+rs.getString(3));
				System.out.println("数据库中取出(电报所名)："+rs.getString(4));
				System.out.println("数据库中取出(所名略号)："+rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}			
		}	
		//
		/*String UpdataSQL="update ? SET ?=? FROM T_ZhanMingLueHao z,##Tm_Table t WHERE  z.baosuoId=b.id";
		try {
			ps=ct.prepareStatement(UpdataSQL);
			ps.setString(1, "z");
			ps.setString(2, "");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		}
	
	
	
}
