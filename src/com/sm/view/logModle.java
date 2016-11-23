package com.sm.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.sm.db.DBConection;
import com.sm.db.DBManager;
import com.sm.tools.SQL;

public class logModle extends AbstractTableModel{
	Connection ct;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	Vector rowData;
	Vector columnNames;
	public logModle(String sql) {
		// TODO Auto-generated constructor stub
		this.init(sql);
	}
	public void init(String sql){
		//DBConection dbc = new DBConection();   
		//ct = dbc.getConnection();
		ct=DBManager.getConn();
		this.Set_columnNames();
		this.Set_rowData(sql);
	}
	//设置jtable列名
    public  void Set_columnNames(){
		columnNames=new Vector();
		columnNames.add("用户");
		columnNames.add("时间");
		//columnNames.add("类型");
		columnNames.add("内容");		
	}
	//加载jtable数据
    public  void Set_rowData(String sql){
		//行数据
		rowData=new Vector();
		
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){

				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				//System.out.println(rs.getString(1));
				rowData.add(hang);
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
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

}
