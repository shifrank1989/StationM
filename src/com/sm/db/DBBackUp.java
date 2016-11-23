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
     * �������ݿ� 
     * @return backup 
     * @throws Exception 
     */  
    public String backup() {  
        /*ActionContext context = ActionContext.getContext();  
        HttpServletRequest request = (HttpServletRequest) context  
                .get(ServletActionContext.HTTP_REQUEST);  
        String webtruepath = request.getParameter("path"); */ 
        String name = "RMS_Station"; //���ݿ���  
        Date date=new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String classpath=System.getProperty("user.dir");
		System.out.println();
        try {  
            File file = new File(classpath);  
            String path = file.getPath() + "\\backup\\" +name+df.format(date)+ ".bak";// name�ļ���  
            String bakSQL = "backup database RMS_Station to disk=? with init";// SQL���  
            PreparedStatement bak = ct.prepareStatement(bakSQL);  
            bak.setString(1, path);// path�����Ǿ���·��  
            bak.execute(); // �������ݿ� 
            JOptionPane.showMessageDialog(null, "���ݿⱸ�ݳɹ�!");
            log.info("���ݿⱸ�ݳɹ�!"+path);
            bak.close();  
        } catch (Exception e) { 
        	JOptionPane.showMessageDialog(null, "���ݿⱸ��ʧ��"+"\r\n"+e.getMessage());
        	log.error("���ݿⱸ��ʧ��!"+e.getMessage());
            e.printStackTrace();  
        }  
        return "backup";  
    }  

    /** 
     * ���ݿ⻹ԭ 
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
                "���ݿ��ļ�(*.bak)", "bak");
        jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "ѡ�����ݿⱸ���ļ�");  
        //jfc.setCurrentDirectory(new File(System.getProperty("user.dir"))); 
        File file=jfc.getSelectedFile();  
        if(file.isDirectory()){  
            System.out.println("�ļ���:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("�ļ�:"+file.getAbsolutePath());  
        }  
        try {  
            // File file = new File(webtruepath);  
            //String path = file1.getPath() + "\\" + name + ".bak";// name�ļ���  
            String path=file.getAbsolutePath();
        	String recoverySql = "ALTER   DATABASE   RMS_Station   SET   ONLINE   WITH   ROLLBACK   IMMEDIATE";// �ָ���������  
              
            PreparedStatement ps =this.getConnection().prepareStatement(recoverySql);  
            CallableStatement cs = this.getConnection().prepareCall("{call killrestore(?,?)}");  
                cs.setString(1, dbname); // ���ݿ���  
                cs.setString(2, path); // �ѱ������ݿ�����·��  
                cs.execute(); // ��ԭ���ݿ�  
                ps.execute(); // �ָ����ݿ�����    
                log.info("���ݿ�ָ��ɹ�");
                JOptionPane.showMessageDialog(null, "���ݿ�ָ��ɹ�");
        } catch (Exception e) { 
        	log.error("���ݿ�ָ�ʧ��!"+e.getMessage());
        	JOptionPane.showMessageDialog(null, "���ݿ�ָ�ʧ��!\r\n"+e.getMessage());
            e.printStackTrace();  
        }  
        return "recovery";  
    }  


}
