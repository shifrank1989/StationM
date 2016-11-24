package com.sm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sm.view.XmlAnalysis;
import com.sm.view.zmlhModle;

public class DBGetWebData_Icr {
	
		static Connection ct;
		static PreparedStatement ps=null;
		//static PreparedStatement pss=null;
		static ResultSet rs=null;
		Vector Tm_hang;
		Vector Tm_rs;
		Vector TEMP;
		String CRsql;
		static String SynDataSQL;
		static String xml;
		static Log log = LogFactory.getLog("DBGetWebData_Icr");
		//������ʱ����������
		public DBGetWebData_Icr (String xmlString){
		   //������
			xml=xmlString;
			//�������ݽ���			
		    CRsql="create table ##Tm_Table"
				+ "(uid varchar(50),satname varchar(50),daima varchar(15),bsname varchar(50),code varchar(15),opertype varchar(15))";		   
			//DBConection dbc = new DBConection();   
			//ct = dbc.getConnection();
		    ct=DBManager.getConn();
			try {
					ps=ct.prepareStatement(CRsql);
					ps.executeUpdate();
					System.out.println("������ʱ��ɹ�");
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
			System.out.println("temp����"+TEMP.size());
			for(int i=3;i<TEMP.size();i++){
				String ISsql="insert into ##Tm_Table values (?,?, ?, ?, ?,?)";
				try {					
					ps=ct.prepareStatement(ISsql);
					System.out.println("�������ݣ�"+((Vector)TEMP.get(i)).get(0).toString());
					ps.setString(1, ((Vector)TEMP.get(i)).get(0).toString());
					ps.setString(2, ((Vector)TEMP.get(i)).get(1).toString());
					ps.setString(3, ((Vector)TEMP.get(i)).get(2).toString());
					ps.setString(4, ((Vector)TEMP.get(i)).get(3).toString());
					ps.setString(5, ((Vector)TEMP.get(i)).get(4).toString());
					ps.setString(6, ((Vector)TEMP.get(i)).get(5).toString());
					ps.executeUpdate();					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(ps!=null) ps.close();
						//if(ct!=null) ct.close();
						
					} catch (Exception e2) {
						e2.printStackTrace();
						// TODO: handle exception
					}			
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
					System.out.println("���ݿ���ȡ��(��������)  ��"+rs.getString(6));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					//if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					//if(ct!=null) ct.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}			
			}	
		}
	    /*//����ʱ�����ԭ��
	    public void UpdataDB(){
	    	String UpdataSQL="update ? SET ?=? FROM T_ZhanMingLueHao z,T_BaoSuo b WHERE  z.baosuoId=b.id";
	    	
	    	
	    }*/
		public void AddData(){
			String SQL = "select * from ##Tm_Table where opertype='1'";
			String AddSQL="insert into T_ZhanMingLueHao () values (?,?, ?, ?, ?,?)";
			try {
				//DBConection dbc = new DBConection();   
				//ct = dbc.getConnection();
				ct=DBManager.getConn();
				ps=ct.prepareStatement(SQL);
				rs=ps.executeQuery();
				while(rs.next()){
					System.out.println("���ݿ���ȡ����������(Ψһ��ʶ��)��"+rs.getString(1));
					System.out.println("���ݿ���ȡ����������(��վ����)  ��"+rs.getString(2));
					System.out.println("���ݿ���ȡ����������(��վ�籨��)��"+rs.getString(3));
					System.out.println("���ݿ���ȡ����������(�籨����)  ��"+rs.getString(4));
					System.out.println("���ݿ���ȡ����������(�����Ժ�)  ��"+rs.getString(5));
					System.out.println("���ݿ���ȡ����������(��������)  ��"+rs.getString(6));
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
		
		  //����ʱ�����ԭ��
	    public static boolean SyndataDB(){
	    	boolean s=true;
	    	//String InsertDataSQL="insert into T_ZhanMingLueHao (code,name,baosuoId,juweiId) values (?,?,?,?)";
	    	String seleSQL = "select * from ##Tm_Table" ;
	    	//String SynDataSQL = null;
			try {
				ps=ct.prepareStatement(seleSQL);
				rs=ps.executeQuery();
				while(rs.next()){
					
					String code=rs.getString(3);
					String name=rs.getString(2);
					int baosuoId=zmlhModle.getbaosuoId(zmlhModle.getbaosuo(rs.getString(5).trim()));
					String juweiId=zmlhModle.getjuweiID(rs.getString(3).trim().substring(rs.getString(3).trim().length()-1, rs.getString(3).trim().length()));
					System.out.println("������"+rs.getString(6).trim());
					if(rs.getString(6).equals("1")){
						SynDataSQL="insert into T_ZhanMingLueHao (code,name,baosuoId,juweiId) values (?,?,?,?)";
						ps=ct.prepareStatement(SynDataSQL);
						ps.setString(1, code);
						ps.setString(2, name);
						ps.setInt(3,baosuoId);
						ps.setString(4, juweiId);
						ps.executeUpdate();
					}else if(rs.getString(6).equals("2")){
						//�޸Ĵ�����
						//SynDataSQL="update T_ZhanMingLueHao set code=?,name=?,baosuoId=?,juweiId=? where code=?";
					}else if(rs.getString(6).equals("3")){
						DBhelper dBhelper=new DBhelper();
						if(dBhelper.CheckExist("select name from T_ZhanMingLueHao where code=? ", code)){
							System.out.println("ɾ����"+code);
							SynDataSQL="delete from T_ZhanMingLueHao where code =?";
							ps=ct.prepareStatement(SynDataSQL);
							ps.setString(1, code);						
							ps.executeUpdate();
						}else{
							s=false;
							JOptionPane.showMessageDialog(null, "������ɾ���"+code);
							log.error("ɾ��ʧ�ܣ�վ���Ժţ�"+code+" ������");
						}
						
						
					}
                  
				}
				//log.info("ͬ�����ݿ�ɹ�"+xml);
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
	    	return s;
	    }
	   
	}

