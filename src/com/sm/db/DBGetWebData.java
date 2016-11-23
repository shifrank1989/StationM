package com.sm.db;

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
import com.sm.test.GetDBDataModelOne;
import com.sm.view.XmlAnalysis;
import com.sm.view.zmlhModle;

public class DBGetWebData {
	static Connection ct;
	static PreparedStatement ps=null;
	//static PreparedStatement ps1=null;
	//static PreparedStatement ps2=null;
	static ResultSet rs=null;
	Vector Tm_hang;
	Vector Tm_rs;
	Vector TEMP;
	String CRsql;

	//������ʱ����������
	public DBGetWebData(String xmlString){
	   //������
		
	  //ȫ�����ݽ���
	   CRsql="create table ##Tm_Table"
			+ "(uid varchar(50),satname varchar(50),daima varchar(15),bsname varchar(50),code varchar(15))";
		
	   
		//DBConection dbc = new DBConection();   
		//ct = dbc.getConnection();
	   ct=DBManager.getConn();
		try {
				ps=ct.prepareStatement(CRsql);
				ps.executeUpdate();
			} catch (SQLException e) {
		    // TODO Auto-generated catch block
			e.printStackTrace();
			}
		XmlAnalysis dm=new XmlAnalysis(xmlString);
		TEMP=new Vector();
		TEMP=dm.getXmlAnaRs();
	}	
	
	//��������
	public void InserDB(){
		
        
		for(int i=0;i<TEMP.size();i++){
			String ISsql="insert into ##Tm_Table values (?,?, ?, ?, ?)";
			try {
				
				ps=ct.prepareStatement(ISsql);
				System.out.println("�������ݣ�"+((Vector)TEMP.get(i)).get(0).toString());
				ps.setString(1, ((Vector)TEMP.get(i)).get(0).toString());
				ps.setString(2, ((Vector)TEMP.get(i)).get(1).toString());
				ps.setString(3, ((Vector)TEMP.get(i)).get(2).toString());
				ps.setString(4, ((Vector)TEMP.get(i)).get(3).toString());
				ps.setString(5, ((Vector)TEMP.get(i)).get(4).toString());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
    //��ѯ��ʱ������
	public void SeleTmDB(){
		String seleSQL = "select * from ##Tm_Table" ;
		try {
			ps=ct.prepareStatement(seleSQL);
			rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("���ݿ���ȡ��(Ψһ��ʶ��)��"+rs.getString(1));
				System.out.println("���ݿ���ȡ��(��վ����)  ��"+rs.getString(2));
				System.out.println("���ݿ���ȡ��(��վ�籨��)��"+rs.getString(3));
				System.out.println("���ݿ���ȡ��(�籨����)  ��"+rs.getString(4));
				System.out.println("���ݿ���ȡ��(�����Ժ�)  ��"+rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) DBManager.closeConn(ct);
				
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}			
		}	
	}
  
}
