package com.sm.view;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

//import javax.mail.search.AndTerm;
import javax.swing.table.*;

import com.sm.db.DBConection;
import com.sm.tools.Layout;
import com.sm.tools.SQL;

public class zmlhModle extends AbstractTableModel{	
	static Connection ct=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;	
	static String juweiID;
	String luju;
	static String baosuo;	
	Vector rowData;
	Vector columnNames;
	
	public void init(String sql,int i){
		
		DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		if(sql.equals("")){
			//展示表中所有数据
			sql=SQL.All_SQL_select;				
			this.Set_columnNames();
			this.Set_rowData(sql);			
		}else if(i==1){
			//更新后展示更新数据
			this.Set_columnNames();
			columnNames.add("操作类型");			
			//行数据
			rowData=new Vector();			
			try {			
				ps=ct.prepareStatement(sql);				
				rs=ps.executeQuery();
				while(rs.next()){
					Vector hang=new Vector();				
					hang.add(rs.getString(3).trim());
					hang.add(rs.getString(2));
					String juwei=rs.getString(3).trim().substring(rs.getString(3).trim().length()-1, rs.getString(3).trim().length());
					hang.add(juwei);
					hang.add(this.getluju(juwei));
					hang.add("1");					
					hang.add(this.getbaosuo(rs.getString(5).trim()));
					hang.add("1");
					hang.add("1");
					hang.add(1);
					hang.add(1);
					hang.add(1);
					hang.add(1);
					hang.add("1");			
					//hang.add(rs.getBoolean(13));
					hang.add(1);
					
					String OperationType=null;
					//System.out.println("------操作类型------"+rs.getString(6).trim());
					if(rs.getString(6).trim().equals("1")){
						OperationType="新增";
					}else if(rs.getString(6).trim().equals("2")){
						OperationType="修改";
					}else if(rs.getString(6).trim().equals("3")){
						OperationType="停用/删除";
					}
					//System.out.println("------操作类型------"+OperationType);
					hang.add(OperationType);					
					rowData.add(hang);					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally{
				try {
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}						
			}
		}else if(i==2){	
			//展示查询结果
			this.Set_columnNames();
			this.Set_rowData(sql);			
		}
	}
	//修改数据库
    public boolean upDate(String sql,String []paras){
    	boolean b=true;
    	DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		try {
			ps=ct.prepareStatement(sql);
			
			for(int i=0;i<paras.length;i++){
				
			  ps.setString(i+1, paras[i]);
			}
			
			if(ps.executeUpdate()!=1){
			  b=false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}		
		}
		return b;
		
    }
	
	public zmlhModle() {
		// TODO Auto-generated constructor stub
		this.init("",0);
	}
	
	public zmlhModle(String sql,int i) {
		// TODO Auto-generated constructor stub
		this.init(sql,i);
	}
	//设置jtable列名
    public  void Set_columnNames(){
		columnNames=new Vector();
		columnNames.add("略号");
		columnNames.add("名称");
		columnNames.add("局尾");
		columnNames.add("铁路局");
		columnNames.add("公司");
		columnNames.add("电报所");
		columnNames.add("车站代码");
		columnNames.add("省份");
		columnNames.add("客运属性");
		columnNames.add("货运属性");
		columnNames.add("线运属性");
		columnNames.add("业务属性");
		columnNames.add("启用标识");
		columnNames.add("页码");
	}
	//加载jtable数据
    public  void Set_rowData(String sql){
		//行数据
		rowData=new Vector();
		
		try {			

			ps=ct.prepareStatement(sql);
			
			rs=ps.executeQuery();

			while(rs.next()){
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
				hang.add(rs.getInt(9));
				hang.add(rs.getInt(10));
				hang.add(rs.getInt(11));
				hang.add(rs.getInt(12));
				if(rs.getBoolean(13)){
					hang.add("已启用");
				}else{
					hang.add("未启用");
				}				
				//hang.add(rs.getBoolean(13));
				hang.add(rs.getInt(14));				
				rowData.add(hang);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}	}
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int row, int columnt) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(columnt);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}
	//获取报所名
	
	public static String getbaosuo(String smlh){
		ResultSet RSbaosuo = null;
			DBConection dbc = new DBConection();   
			ct = dbc.getConnection();
			try {
				//System.out.println("所名略号"+smlh);
				ps=ct.prepareStatement("select name from T_BaoSuo where code= ?");
				ps.setString(1, smlh);
				RSbaosuo=ps.executeQuery();
				while(RSbaosuo.next()){				
					baosuo=RSbaosuo.getString("name");
					//System.out.println(RSbaosuo.getString("name"));				
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					if(RSbaosuo!=null) RSbaosuo.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				} catch (Exception e2) {
					// TODO: handle exceptione
					e2.printStackTrace();
				}
			
			}
			return baosuo;
		}	
	//获取路局
	ResultSet RSluju;
	public String getluju(String sdaizi){
		DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		try {
			System.out.println("局尾daizi"+sdaizi);
			ps=ct.prepareStatement("select name from T_JuWei where daizi= ?");
			ps.setString(1, sdaizi);
			RSluju=ps.executeQuery();
			while(RSluju.next()){				
				luju=RSluju.getString("name");
				//System.out.println(RSluju.getString("name"));				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(RSluju!=null) RSluju.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception e2) {
				// TODO: handle exceptione
				e2.printStackTrace();
			}
		
		}
		return luju;
	}
	//获取报所ID
	
	public static int getbaosuoId(String selsname){
		ResultSet baosuoID;
		int baosuoid=0; 
		DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		//System.out.println("-------"+selsname+"-------选择的是");		
		try {			
			ps=ct.prepareStatement("select id from T_BaoSuo where name=?");
			ps.setString(1, selsname);
			baosuoID=ps.executeQuery();
			//System.out.println("=========");
			while(baosuoID.next()){	
				//System.out.println("-------=========");
				baosuoid=baosuoID.getInt("id");
				//System.out.println("-----------id     ："+baosuoID.getString("id"));				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return baosuoid;
	}
	//获取选中行的局尾ID
	
	public static String getjuweiID(String sdaizi){
		ResultSet RSjuwei = null;
		DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		try {
			//System.out.println("局尾daizi"+sdaizi);
			ps=ct.prepareStatement("select id from T_JuWei where daizi= ?");
			ps.setString(1, sdaizi);
			RSjuwei=ps.executeQuery();
			while(RSjuwei.next()){				
				juweiID=RSjuwei.getString("id");
				//System.out.println(model.getString("name"));				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(RSjuwei!=null) RSjuwei.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception e2) {
				// TODO: handle exceptione
				e2.printStackTrace();
			}
		
		}
		return juweiID;
	}
	//获取 combox list model
	ResultSet model;
	public Vector getmodel(int i,String juweiID){
		Vector list=new Vector();
		DBConection dbc = new DBConection();   
		ct = dbc.getConnection();
		try {
			if(i==0){
				ps=ct.prepareStatement("select name from T_BaoSuo where juweiId= ?");
				ps.setString(1, juweiID);
				model=ps.executeQuery();
				list.clear();
				while(model.next()){				
					list.addElement(model.getString("name"));
					//System.out.println(model.getString("name"));				
				}
			}else if(i==1) {
				ps=ct.prepareStatement("select distinct(company) from T_ZhanMingLueHao where juweiId= ?");
				ps.setString(1, juweiID);
				model=ps.executeQuery();
				list.clear();
				while(model.next()){				
					list.addElement(model.getString("company"));
					//System.out.println("公司     ："+model.getString("company"));				
				}
			}else if(i==2){
				ps=ct.prepareStatement("select daizi from T_JuWei");
				//ps.setString(1, null);
				model=ps.executeQuery();
				list.clear();
				while(model.next()){				
					list.addElement(model.getString("daizi"));
					//System.out.println("公司     ："+model.getString("company"));				
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(model!=null) model.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception e2) {
				// TODO: handle exceptione
				e2.printStackTrace();
			}
			
		}
		
		return list;
	}
	

}

