package com.sm.db;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBBackUp {
	static Connection ct=null;
	static PreparedStatement ps=null;
	private static final Log log = LogFactory.getLog(DBManager.class);
	
	public  DBBackUp(){
		//DBConection dbc = new DBConection();   
		//ct = dbc.getConnection();
		ct=DBManager.getConn();
	}
	public static Connection getConnection() {  
        Connection conn = null;  
        try {  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            String url = "jdbc:sqlserver://localhost:1433;databaseName=master";  
            String username = "sa";  
            String password = "123456";   
            conn = DriverManager.getConnection(url, username, password);  
              
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
	/** 
     * 备份数据库 
     * @return backup 
     * @throws Exception 
     */  
    public String backup() {  
        /*ActionContext context = ActionContext.getContext();  
        HttpServletRequest request = (HttpServletRequest) context  
                .get(ServletActionContext.HTTP_REQUEST);  
        String webtruepath = request.getParameter("path"); */ 
        String name = "RMS_Station"; //数据库名  
        Date date=new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String classpath=System.getProperty("user.dir");
		System.out.println();
        try {  
            File file = new File(classpath);  
            String path = file.getPath() + "\\backup\\" +name+df.format(date)+ ".bak";// name文件名  
            String bakSQL = "backup database RMS_Station to disk=? with init";// SQL语句  
            PreparedStatement bak = ct.prepareStatement(bakSQL);  
            bak.setString(1, path);// path必须是绝对路径  
            bak.execute(); // 备份数据库 
            JOptionPane.showMessageDialog(null, "数据库备份成功!");
            log.info("数据库备份成功!"+path);
            bak.close();  
        } catch (Exception e) { 
        	JOptionPane.showMessageDialog(null, "数据库备份失败"+"\r\n"+e.getMessage());
        	log.error("数据库备份失败!"+e.getMessage());
            e.printStackTrace();  
        }  
        return "backup";  
    }  

    /** 
     * 数据库还原 
     * @return recovery 
     */  
    public String recovery() {  
    	 /*try {
    		   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		  } catch (Exception e1) {
    		   // TODO Auto-generated catch block
    		   e1.printStackTrace();
    		  }*/       
        //String webtruepath = request.getParameter("path");  
        //String name = "******";  
        String dbname = "RMS_Station"; 
        //FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File("D:\\")); 
        JFileChooser jfc=new JFileChooser(new File(System.getProperty("user.dir")+ "\\backup\\"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "数据库文件(*.bak)", "bak");
        jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "选择数据库备份文件");  
        //jfc.setCurrentDirectory(new File(System.getProperty("user.dir"))); 
        File file=jfc.getSelectedFile();  
        if(file.isDirectory()){  
            System.out.println("文件夹:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("文件:"+file.getAbsolutePath());  
        }  
        try {  
            // File file = new File(webtruepath);  
            //String path = file1.getPath() + "\\" + name + ".bak";// name文件名  
            String path=file.getAbsolutePath();
        	String recoverySql = "ALTER   DATABASE   RMS_Station   SET   ONLINE   WITH   ROLLBACK   IMMEDIATE";// 恢复所有连接  
              
            PreparedStatement ps =this.getConnection().prepareStatement(recoverySql);  
            CallableStatement cs = this.getConnection().prepareCall("{call killrestore(?,?)}");  
                cs.setString(1, dbname); // 数据库名  
                cs.setString(2, path); // 已备份数据库所在路径  
                cs.execute(); // 还原数据库  
                ps.execute(); // 恢复数据库连接    
                log.info("数据库恢复成功");
                JOptionPane.showMessageDialog(null, "数据库恢复成功");
        } catch (Exception e) { 
        	log.error("数据库恢复失败!"+e.getMessage());
        	JOptionPane.showMessageDialog(null, "数据库恢复失败!\r\n"+e.getMessage());
            e.printStackTrace();  
        }  
        return "recovery";  
    }  


}
