package com.sm.view;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.*;
import com.sm.tools.*;
import com.sm.webservice.DataService_GetDate;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sm.db.DBBackUp;
import com.sm.db.DBConection;
import com.sm.db.DBGetWebData;
import com.sm.db.DBGetWebData_Icr;
import com.sm.db.DBManager;
import com.sm.db.DBVerno;
import com.sm.db.DBhelper;

import java.util.*;

public class zmlh extends JFrame implements ActionListener, ListSelectionListener,ItemListener{
	Image ZTbg,TitleImage;
	static String lastVerno;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12,jl13,jl14,jl15,jl16,jl17;
	JLabel jp7_jl3,jp7_jl4,jp7_jl5,jp7_jl6,jp7_jl7,jp7_jl8,jp7_jl9,jp7_jl10,jp7_jl11,jp7_jl12,jp7_jl13,jp7_jl14,jp7_jl15,jp7_jl16;
	JScrollPane sp;
	JTable jt;
	JTableHeader tableH;
	JMenuBar jmbr;
	JMenu jm1,jm2,jm3;
	JMenuItem jmt1,jmt2,jmt3,jmt4,jmt5,jmt6,jmt7;
	JComboBox jcb1,jcb2,jcb3,jcb4,jcb5,jcb6,jcb7,jcb8,jcb9,jcb10,jcb11,jcb12;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10,jtf11,jtf12,jtf13,jtf14;
	JTextField jp7_jtf1,jp7_jtf2/*,jp7_jtf3,jp7_jtf4*/;
	JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
	ImagePanel ip1,ip2;
	ImageIcon newicon;
	CardLayout cl;
	zmlhModle zmlhModle;
	logModle logModle;
	String querystring,querysql;
	String scode,sname,sdaizi,sljname,scompany,sbsname,sdaima,sprovince,sstatus;
	String selcompany,selsname,selstatus,seljuwei;
	String selkeyun,selhuoyun,selxianyun,selyewu;
	int skeyun,shuoyun,sxianyun,syewu,spage;
	//Log log = LogFactory.getLog( this.getClass());
	private static final Log log = LogFactory.getLog(zmlh.class);
	ImageIcon newimage;
	static TrayIcon icon; // ����ͼ��  
	static SystemTray tray; // ������ϵͳ���̵�ʵ�� 
	 
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
	//��¼jp6�п�Ƭ���ֵ�ǰ��ʾ��Ƭ
	int cardVis=0;
	boolean queryb=true;
	
	
  public static void main(String args[]){		   
		zmlh zmlh=new zmlh();		
	}
  
   //��ʼ���Ҳ����봰��
   public void initInWind(){
	   //sp=new JScrollPane();
	   /*sp.getViewport().setBackground(Color.BLACK);
	   sp.getViewport().setOpaque(false); 
	   //sp.setOpaque(false);     //���м��viewport����Ϊ͸��
	   sp.setViewportView(jt); //װ�ر�� 
       */	  
	   systemTray();
	      //���ù�����
		  sp=new JScrollPane(jt);
		  tableH=jt.getTableHeader();
		  tableH.setBackground(Color.lightGray);
		  
		  //sp.getViewport().setBackground(Color.BLACK);
		  sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		  sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		  sp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	   //����ͼ��
		try {
			TitleImage=ImageIO.read(new File("image/TitleImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIconImage(TitleImage);
		
		this.Set_MenuBar();
        this.Set_BottomLable();
		this.Set_Button();
		this.Set_Lable();
        this.Set_TextField();
        this.Set_jp2();
		this.Set_jp7();
		this.Set_jp6();
        this.Set_jp4();
        //����ҳ������	  
		this.add(sp);
		this.add(jp4,"East");
		//this.add(ip1,"South");;
		this.add(jp5,"South");
		this.setSize(1000,800);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				
				zmlh.this.setVisible(false);
				System.out.println("yingcang ");
			}
			
			});
		this.setVisible(true);	
		
		
   }

   //����ϵͳ����   
   private void systemTray() {
   if (SystemTray.isSupported()) { // �ж�ϵͳ�Ƿ�֧�����̹���.
	   System.out.println("zhichi");
    // ���������һ������˵�
    PopupMenu popupMenu = new PopupMenu();
     
    //���������˵��е��˳���
    MenuItem itemExit = new MenuItem("�˳�ϵͳ");
    itemExit.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
     log.info("�˳�ϵͳ");
      System.exit(0);
     }
     });
    popupMenu.add(itemExit);
     
    //��������ͼ��
    ImageIcon icon = new ImageIcon("image/11.gif"); // ����ͼƬ����
    TrayIcon trayIcon = new TrayIcon(icon.getImage(), "�籨վ���ԺŹ���ϵͳ",
     popupMenu);
    trayIcon.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
     zmlh.this.setVisible(true);
    }
    });
    
    
    
     
    //������ͼ����ӵ�ϵͳ����
    //������Ե���ر�֮���ٷŵ��������棬�ڴ��Ǵ򿪳���ֱ����ʾ����ͼ����
    try {
    SystemTray.getSystemTray().add(trayIcon);
    } catch (AWTException e1) {
    e1.printStackTrace();
    }
   }
   }
	
   public zmlh(){
	    super("�籨վ���ԺŹ���ϵͳ");
	    
	    
	    /*try {
 		   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
 		  } catch (Exception e1) {
 		   // TODO Auto-generated catch block
 		   e1.printStackTrace();
 		  }*/
	    
        zmlhModle=new zmlhModle();
		
        //��ʼ��jtable
		jt=new JTable(zmlhModle);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(zmlhModle);
		jt.setRowSorter(sorter); 
		//jt.setBackground();

		this.initInWind();	
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {				
        		@Override
					public void valueChanged(ListSelectionEvent e) {
						// TODO Auto-generated method stub
						int row=jt.getSelectedRow();
						System.out.println("ѡ�е�"+row);
						if(row<0){
							row=0;
						}
						scode= (String)zmlhModle.getValueAt(row, 0);						
						sname= (String)zmlhModle.getValueAt(row, 1);
						sdaizi= (String)zmlhModle.getValueAt(row, 2);
						sljname= (String)zmlhModle.getValueAt(row, 3);						
						scompany= (String)zmlhModle.getValueAt(row, 4);
						sbsname= (String)zmlhModle.getValueAt(row, 5);
						sdaima= (String)zmlhModle.getValueAt(row, 6);
						sprovince= (String)zmlhModle.getValueAt(row, 7);
						skeyun= (Integer)zmlhModle.getValueAt(row, 8);
						shuoyun= (Integer)zmlhModle.getValueAt(row, 9);
						sxianyun= (Integer)zmlhModle.getValueAt(row, 10);
						syewu= (Integer)zmlhModle.getValueAt(row, 11);
						sstatus= (String)zmlhModle.getValueAt(row, 12);
						spage= (Integer)zmlhModle.getValueAt(row, 13);
						if(cardVis==0){
							jtf1.setText(scode);
							jtf2.setText(sname);
							jtf3.setText(sdaizi);
							jtf4.setText(sljname);
							this.SetText();						
						}else{							
							jp7_jtf1.setText(scode);
							jp7_jtf2.setText(sname);
							/*jp7_jtf3.setText(sdaizi);
							jp7_jtf4.setText(sljname);*/
							/*jcb11.removeAllItems();
							jcb12.removeAllItems();*/
							jcb11.setEditable(true);
							jcb11.setSelectedItem(sdaizi);
							jcb12.setEditable(true);
							jcb12.setSelectedItem(sljname);
							
							jcb1.removeAllItems();
							
							String juweiID=zmlhModle.getjuweiID(sdaizi);
							System.out.println("��βid"+juweiID);
							for(int i=0;i<zmlhModle.getmodel(1, juweiID).size();i++)
							{
								jcb1.addItem(zmlhModle.getmodel(1, juweiID).get(i));
								jcb1.setSelectedItem(scompany);
							}
							jcb2.removeAllItems();
							for(int i=0;i<zmlhModle.getmodel(0, juweiID).size();i++)
							{
								jcb2.addItem(zmlhModle.getmodel(0, juweiID).get(i));
								jcb2.setSelectedItem(sbsname);
							}
							this.SetText();							
						}						
						list1.clear();
						for(int i=0;i<14;i++){
							list1.add(String.valueOf(zmlhModle.getValueAt(row, i)));
						}
						System.out.println("ѡ�е�һ���ǣ�");
						for(int i=0;i<14;i++){
							System.out.print(list1.get(i));
						}						
					}
                //�����Ҳ������
				private void SetText() {
					// TODO Auto-generated method stub
					jtf5.setText(scompany);
					jtf6.setText(sbsname);
					jtf7.setText(sdaima);
					jtf8.setText(sprovince);
					jtf9.setText(""+skeyun);
					jtf10.setText(""+shuoyun);
					jtf11.setText(""+sxianyun);
					jtf12.setText(""+syewu);
					jtf13.setText(sstatus);
					jtf14.setText(""+spage);
				}				
				});
               
	}
   //���ò˵���
   public void Set_MenuBar() {
	 
	 		jmbr=new JMenuBar();
	 		jmbr.setBackground(Color.LIGHT_GRAY);
	 		//jmbr.setBorder(BorderFactory.createEtchedBorder());
	 		jm1=new JMenu("  ϵͳ����  ");
	 		jm1.setFont(MyTools.f1);
	 		jmt1=new JMenuItem("�л��û�",new ImageIcon("image/changeuser.png"));
	 		jmt1.addActionListener(this);
	 		//jmt1.setFont(MyTools.f2);
	 		jmt2=new JMenuItem("�˳�",new ImageIcon("image/exit.png"));
	 		jmt2.addActionListener(this);
	 		jmt6=new JMenuItem("�������ݿ�");
	 		jmt6.addActionListener(this);
	 		jmt7=new JMenuItem("��ԭ���ݿ�");
	 		jmt7.addActionListener(this);
	 		jm1.add(jmt1);
	 		jm1.add(jmt2);
	 		jm1.add(jmt6);
	 		jm1.add(jmt7);
	 		
	 		jm2=new JMenu(" ����   ");
	 		jm2.setFont(MyTools.f1);
	 		jmt3=new JMenuItem("����");
            jm2.add(jmt3);
            
            jm3=new JMenu(" ��־����   ");
            jm3.setFont(MyTools.f1);
	 		jmt4=new JMenuItem("������־");
	 		jmt4.addActionListener(this);
	 		jmt5=new JMenuItem("�澯��־");
            jm3.add(jmt4);
            jm3.add(jmt5);
            
            jmbr.add(jm1);
            jmbr.add(jm3);
	 		jmbr.add(jm2);
	 		this.setJMenuBar(jmbr);
}
   //���õײ��������汾�ż�ʱ��
   public void Set_BottomLable(){
	   //״̬����ʾʱ��
	   javax.swing.Timer T;
	   T=new Timer(1000, this);
	   T.start();
	   jp5=new JPanel(new BorderLayout());
	 
	   newicon=new ImageIcon("image/11.gif");
	  
	   jl1=new JLabel("      �������ݰ汾��"+DBVerno.lastVerno+"          ");
	   if(DBVerno.ListenVerno()){
		   
		   jl17=new JLabel("�����������İ汾��"+DBVerno.webVerno);
		   jl17.setIcon(newicon);
		   
	   }else{
		   jl17=new JLabel("                �����������İ汾��"+DBVerno.webVerno);
		  
	   }
	  
	   jl2=new JLabel("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"          ");
	   //jl2.setForeground(Color.white);
	   /*//���״̬����
	   try {
		ZTbg=ImageIO.read(new File("image/Gbg.jpg"));
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	   ip1=new ImagePanel(ZTbg);
	   ip1.setLayout(new BorderLayout());
	   ip1.add(jl2,"East");
	   ip1.add(jl1,"West");*/
	   jp5.add(jl1,"West");
	   jp5.add(jl17);
	   jp5.add(jl2,"East");
	   jp5.setBackground(Color.LIGHT_GRAY);
   }
   //����jp2����ȫ��������Ҳ������
   public void Set_jp2(){
	   jp2=new JPanel();
		jp2.setLayout(new GridLayout(28,1));
		jp2.add(jl16);
		jp2.add(jtf1);
		jp2.add(jl3);
		jp2.add(jtf2);
		jp2.add(jl4);
		jp2.add(jtf3);
		jp2.add(jl5);
		jp2.add(jtf4);
		jp2.add(jl6);
		jp2.add(jtf5);	
		jp2.add(jl7);
		jp2.add(jtf6);	
		jp2.add(jl8);
		jp2.add(jtf7);
		jp2.add(jl9);
		jp2.add(jtf8);
		jp2.add(jl10);
		jp2.add(jtf9);
		jp2.add(jl11);
		jp2.add(jtf10);
		jp2.add(jl12);
		jp2.add(jtf11);
		jp2.add(jl13);
		jp2.add(jtf12);
		jp2.add(jl14);
		jp2.add(jtf13);
		jp2.add(jl15);
		jp2.add(jtf14);
   }
   //����jp7������ѡ������Ҳ������
   public void Set_jp7(){
	    String[] s1={"-1","1","2"};
	    String[] s2={"δ����","����"};
		jp7_jtf1=new JTextField();
		jp7_jtf2=new JTextField();
		/*jp7_jtf3=new JTextField();
		jp7_jtf4=new JTextField();*/
		jcb11=new JComboBox() ;
		jcb11.addItemListener(this);
		jcb11.addActionListener(this);
		/*jcb11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcb2.removeAllItems();
				for(int i=0;i<zmlhModle.getmodel(0, jcb11.getSelectedItem().toString().trim()).size();i++)
				{
					jcb2.addItem(zmlhModle.getmodel(0, jcb11.getSelectedItem().toString().trim()).get(i));
					jcb2.setSelectedItem(sbsname);
				}
			}
		});*/
		jcb12=new JComboBox() ;
		jcb12.addItemListener(this);
		jcb1=new JComboBox() ;
		jcb1.addItemListener(this);
		jcb2=new JComboBox() ;
		jcb2.addItemListener(this);
		jcb3=new JComboBox() ;
		jcb3.addItemListener(this);
		jcb4=new JComboBox() ;
		jcb4.addItemListener(this);
		jcb5=new JComboBox(s1) ;
		jcb6=new JComboBox(s1) ;
		jcb7=new JComboBox(s1) ;
		jcb8=new JComboBox(s1) ;
		jcb9=new JComboBox(s2) ;
		jcb10=new JComboBox() ;
	    jp7=new JPanel();
     	jp7.setLayout(new GridLayout(28,1));		
		jp7.add(jp7_jl16);
		jp7.add(jp7_jtf1);
		jp7.add(jp7_jl3);
		jp7.add(jp7_jtf2);
		jp7.add(jp7_jl4);
		//jp7.add(jp7_jtf3);
		jp7.add(jcb11);
		jp7.add(jp7_jl5);
		//jp7.add(jp7_jtf4);
		jp7.add(jcb12);
		jp7.add(jp7_jl6);
		jp7.add(jcb1);
		jp7.add(jp7_jl7);
		jp7.add(jcb2);
		jp7.add(jp7_jl8);
		jp7.add(jcb3);
		jp7.add(jp7_jl9);
		jp7.add(jcb4);
		jp7.add(jp7_jl10);
		jp7.add(jcb5);
		jp7.add(jp7_jl11);
		jp7.add(jcb6);
		jp7.add(jp7_jl12);
		jp7.add(jcb7);
		jp7.add(jp7_jl13);
		jp7.add(jcb8);
		jp7.add(jp7_jl14);
		jp7.add(jcb9);
		jp7.add(jp7_jl15);
		jp7.add(jcb10);
   }
   //����jp6��������jp7��jp2
   public void Set_jp6(){
	 //jp6--�Ҳ������--��Ƭ����,jp2,jp7���ص�jp6��
	    cl=new CardLayout();
		jp6=new JPanel(cl);
		jp6.add(jp2,"0");
		jp6.add(jp7,"1");
   }
   //����jp4��������򼰰�ť����
   public void Set_jp4(){
	 //����򼰰�ť����
	 		jp4=new JPanel();
	 		jp4.setLayout(new BorderLayout());
	 		//jp4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	 	    jp4.add(jp6);
	 	    jp4.add(jp3,"South");
   }
   //���ð�ť
   public void Set_Button(){
	    jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jb2=new JButton("�޸�");
		jb2.addActionListener(this);
		jb3=new JButton("����");
		jb3.addActionListener(this);
		jb4=new JButton("����");
		jb4.addActionListener(this);
		jb5=new JButton("��ʾȫ������");
		jb5.addActionListener(this);
		jb6=new JButton("ͬ��������");
		jb6.setEnabled(false);
		jb6.addActionListener(this);
		jp3=new JPanel();
		jp3.setLayout(new GridLayout(3, 2));		
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);
		jp3.add(jb4);
		jp3.add(jb5);
		jp3.add(jb6);
   }
   //���ñ༭��
   public void Set_TextField(){
	    jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		jtf7=new JTextField();
		jtf8=new JTextField();
		jtf9=new JTextField();
		jtf10=new JTextField();
		jtf11=new JTextField();
		jtf12=new JTextField();
		jtf13=new JTextField();
		jtf14=new JTextField();
		
	/*	jp7_jtf1=new JTextField();
		jp7_jtf2=new JTextField();
		jp7_jtf3=new JTextField();
		jp7_jtf4=new JTextField();*/
   }
   //���ñ�ǩ
   public void  Set_Lable(){
	       //�Ҳ�������б�				
			jl3=new JLabel(" ����");		
			jl4=new JLabel(" ��β");
			jl5=new JLabel(" ��·��");
			jl6=new JLabel(" ��˾");
			jl7=new JLabel(" �籨��");
			jl8=new JLabel(" ��վ����");
			jl9=new JLabel(" ʡ��");
			jl10=new JLabel(" ��������");
			jl11=new JLabel(" ��������");
			jl12=new JLabel(" ��������");
			jl13=new JLabel(" ҵ������");
			jl14=new JLabel(" ���ñ�ʶ");
			jl15=new JLabel(" ҳ��");
			jl16=new JLabel(" �Ժ�");
			
			//
			jp7_jl3=new JLabel(" ����");		
			jp7_jl4=new JLabel(" ��β");
			jp7_jl5=new JLabel(" ��·��");
			jp7_jl6=new JLabel(" ��˾");
			jp7_jl7=new JLabel(" �籨��");
			jp7_jl8=new JLabel(" ��վ����");
			jp7_jl9=new JLabel(" ʡ��");
			jp7_jl10=new JLabel(" ��������");
			jp7_jl11=new JLabel(" ��������");
			jp7_jl12=new JLabel(" ��������");
			jp7_jl13=new JLabel(" ҵ������");
			jp7_jl14=new JLabel(" ���ñ�ʶ");
			jp7_jl15=new JLabel(" ҳ��");
			jp7_jl16=new JLabel(" �Ժ�");
         }
  //��ѯ����
   public String query(){
	   
	   if(this.jp7_jtf2.getText().toString().trim().length()>0){
           //����վ����ѯ
		   querystring=this.jp7_jtf2.getText().trim();
		   querysql=SQL.Find_SQL_name+querystring+"%'";
	   }else if(jcb2.getSelectedItem()!=null){
		   //���ձ������ֲ�ѯ		  
		   querystring=this.jcb2.getSelectedItem().toString();
		   querysql=SQL.Find_SQL_bsname+querystring+"%'";
	   }/*else if(jcb2.getSelectedItem().toString().trim().length()<=0&&jcb1.getSelectedItem().toString().trim().length()>0){
		   
		   querystring=this.jcb1.getSelectedItem().toString();
		   querysql=SQL.Find_SQL_company+querystring+"%'";
	   }*/else if(jcb1.getSelectedItem()==null&jcb2.getSelectedItem()==null&jcb11.getSelectedItem().toString().length()>0){
           //���վ�β��ѯ
		   querystring=this.jcb11.getSelectedItem().toString();
		   querysql=SQL.Find_SQL_juwei+querystring+"%'";
	   }
	   return querysql;
   }
   
   //������Ӧ
   public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub	
	   //Log log = LogFactory.getLog( this.getClass());
	   //��ȡʵʱʱ��
	  // this.jl2.setText("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString());
	   
		if(arg0.getSource()==jb1){
			
			cardVis=1;
			cl.show(jp6, "1");
			
            if(queryb){
            	System.out.println("222222222");
            	for(int i=0;i<zmlhModle.getmodel(2, null).size();i++)
    			{
    				jcb11.addItem(zmlhModle.getmodel(2, null).get(i));
    				
    			}
            	queryb=false;
			}else{
				//System.out.println(jcb11.getSelectedItem().toString().length());
				this.query();
				zmlhModle=new zmlhModle(querysql,2);
				log.info(Login.userName+"��ѯ��"+querystring);
	            jt.setModel(zmlhModle);
	            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(zmlhModle);
	    		jt.setRowSorter(sorter);
			}
			//String name=this.jp7_jtf2.getText().trim();
			//querysql=SQL.Find_SQL_name+name+"%'";
			/*for(int i=0;i<zmlhModle.getmodel(2, null).size();i++)
			{
				jcb11.addItem(zmlhModle.getmodel(2, null).get(i));
				
			}this.query();
			zmlhModle=new zmlhModle(querysql,2);
			log.info(Login.userName+"��ѯ��"+querystring);
            jt.setModel(zmlhModle);*/
             
		}else if(arg0.getSource()==jb3){
			cardVis=1;
			System.out.println("ϣ������");
			cl.show(jp6, "1");
			/*DataService_GetDate dsg=new DataService_GetDate();
			//�õ���������
			String xmlString=dsg.getIcrData(lastVerno);
			
			String xmlString=dsg.getAllData();
			System.out.println(xmlString);*/
			//jb6.addActionListener(this);
			jb6.setEnabled(true);
			jb3.setEnabled(false);
			
			//��������
			String xmlString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			        "<results>"+
					"<message>��վ������</message>"+
	                    "<verno>1.1.037b</verno>"+
	                     "<records>6</records>"+
			           "<Row>"+
	                     "<Ψһ��ʶ��>fdbdc3c1755f48a98cb5d4790838cefb</Ψһ��ʶ��>"+
			              "<��վ����>�ﶫ��    </��վ����>"+
			              "<��վ�籨��>TBZ   </��վ�籨��>"+
			              "<�籨����>����վ    </�籨����>"+
			              "<�����Ժ�>NNZ    </�����Ժ�>"+
			              "<��������>3</��������>"+
			           "</Row>"+
			           "<Row>"+
			           "<Ψһ��ʶ��>e9f3cfa814754c2c9500346a17e66088</Ψһ��ʶ��>"+
			           "<��վ����>�Ǻα�</��վ����>"+
			           "<��վ�籨��>HUZ</��վ�籨��>"+
			           "<�籨����>����վ</�籨����>"+
			           "<�����Ժ�>NNZ</�����Ժ�>"+
			           "<��������>3</��������>"+
			           "</Row>"+
			        "</results>";
			
			DBGetWebData_Icr dbg=new DBGetWebData_Icr(xmlString);
			dbg.InserDB();
			String sql="select * from ##Tm_Table";
			zmlhModle=new zmlhModle(sql,1);
			jt.setModel(zmlhModle);
			RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(zmlhModle);
			jt.setRowSorter(sorter);
			//dbg.SeleTmDB();
			//dbg.AddData();
           //
			int row=this.jt.getSelectedRow();
			try {
				/*if(row==-1){
					JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				}else{*/
				if(row!=-1){
				//�����Ϣ��ȫ�Զ���ȫ
				if(sdaizi==null){
					sdaizi=scode.substring(scode.length()-1,scode.length());
					//System.out.println("---daizi----"+sdaizi);
					//jp7_jtf3.setText(sdaizi);
					jcb11.setSelectedItem(sdaizi);
				}				
				String code=this.jp7_jtf1.getText().trim();
				zmlhModle temp=new zmlhModle();			
			}} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}		
		}
		else if(arg0.getSource()==jb2){
			cardVis=1;
			//selcompany=jcb1.getSelectedItem().toString();
			//selsname=jcb2.getSelectedItem().toString();
			System.out.println("ϣ���޸�");
			cl.show(jp6,"1");
			//System.out.println("��ʾ����");
			int row=this.jt.getSelectedRow();
			try {
				if(row==-1){
					JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				}else{
				//�����Ϣ��ȫ�Զ���ȫ
				if(sdaizi==null){
					sdaizi=scode.substring(scode.length()-1,scode.length());
					//jp7_jtf3.setText(sdaizi);
					jcb11.setSelectedItem(sdaizi);
				}				
				String code=this.jp7_jtf1.getText().trim();
				zmlhModle temp=new zmlhModle();
				
				/*String sql="update T_ZhanMingLueHao set  "
						+ "name=? ,daima=?, province=?, keyun=?, huoyun=?, xianyun=?, yewu=?, company=? , page=? "
						+ "where code='"+code+"'";*/
				String sql="update T_ZhanMingLueHao set  "
						+ " company=? ,baosuoId=?,keyun=?,huoyun=?,xianyun=?,yewu=? "
						+ "where code='"+code+"'";
				String baosuoID=String.valueOf(zmlhModle.getbaosuoId(selsname));
				//System.out.println("-------"+baosuoID+"-----");	
				String[] paras = {selcompany,baosuoID,selkeyun,selhuoyun,selxianyun,selyewu};
                
				//��ʾ
				if(!temp.upDate(sql, paras)){
					
					JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
					
				}else{
					log.info("�޸ĳɹ�  "+code);
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
					
				}
				zmlhModle=new zmlhModle();				
				jt.setModel(zmlhModle);
				RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(zmlhModle);
				jt.setRowSorter(sorter);
				
			}} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		}else if(arg0.getSource()==jb5){
			cardVis=0;
			//��ʾ��������
			System.out.println("��ʾ��������");
			cl.show(jp6, "0");
			zmlhModle=new zmlhModle();
			jt.setModel(zmlhModle);
			RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(zmlhModle);
			jt.setRowSorter(sorter);
			this.RemoveAllText();		
		}else if(arg0.getSource()==jb6){
			//ͬ���������ݵ�����
			
			
			if(DBGetWebData_Icr.SyndataDB()){
				log.info("ͬ���������ݳɹ�");
				JOptionPane.showMessageDialog(this, "ͬ�����!");
			}else{
				log.info("ͬ����������ʧ��");
			}
			
			jt.setModel(zmlhModle);
			RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(zmlhModle);
			jt.setRowSorter(sorter);
			jb6.setEnabled(false);
		}else if(arg0.getSource()==jb4){
			System.out.println("����");
			/*JFileChooser jfc=new JFileChooser(); 
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "���ݿ��ļ�(*.bak)", "bak");
	        jfc.setFileFilter(filter);
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "ѡ�����ݿⱸ���ļ�");  
	        File file=jfc.getSelectedFile();  
	        if(file.isDirectory()){  
	            System.out.println("�ļ���:"+file.getAbsolutePath());  
	        }else if(file.isFile()){  
	            System.out.println("�ļ�:"+file.getAbsolutePath());  
	        }  */
		}else if(arg0.getSource()==jmt2){
			//JOptionPane.showMessageDialog(this, "nihao!");
			log.info("�˳�ϵͳ");
			System.exit(0);
		}else if(arg0.getSource()==jmt4){
			String sql=SQL.Find_log_SQL;
			logModle=new logModle(sql);
			jt.setModel(logModle);
			RowSorter<TableModel> logsorter = new TableRowSorter<TableModel>(logModle);
			jt.setRowSorter(logsorter); 
			//jt.removeEditor();
			this.setVisible(true);
		}else if(arg0.getSource()==jmt1){
			this.dispose();
			log.info("�˳�ϵͳ");
			new Login();			
		}else if(arg0.getSource()==jcb11){
			//System.out.println(jcb11.getSelectedItem().toString().trim());
			String juweiID=zmlhModle.getjuweiID(jcb11.getSelectedItem().toString().trim());
			jcb2.removeAllItems();
			System.out.println("jingru"+zmlhModle.getmodel(0, juweiID).size());
			for(int i=0;i<zmlhModle.getmodel(0, juweiID).size();i++)
			{
				//System.out.println("jingru");
				jcb2.addItem(zmlhModle.getmodel(0, juweiID).get(i));
				//jcb2.setSelectedItem("");
			}
		}else if(arg0.getSource()==jmt6){
			//�������ݿ�
			DBBackUp backup=new DBBackUp();
			backup.backup();
		}else if(arg0.getSource()==jmt7){
			//��ԭ���ݿ�
			DBBackUp backup=new DBBackUp();
			if(backup.recovery()){
			//log.info("���ݿ�ָ��ɹ�!");
				/*Runtime.getRuntime().addShutdownHook(new Thread() {
		            public void run() {
		                try {
		                	String cmd=System.getProperty("user.dir")+"";
							Runtime.getRuntime().exec("java Login");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }    
		        });
		        System.exit(0);*/
			};
		}
	}
 
   //����Ҳ������
   public void RemoveAllText(){
	    jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
		jtf4.setText("");
		jtf5.setText("");
		jtf6.setText("");
		jtf7.setText("");
		jtf8.setText("");
		jtf9.setText("");
		jtf10.setText("");
		jtf11.setText("");
		jtf12.setText("");
		jtf13.setText("");
		jtf14.setText("");
   }
@Override
   public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getStateChange() == ItemEvent.SELECTED){  
        //String itemSize = (String) e.getItem();  
        try{ 
        	selcompany=jcb1.getSelectedItem().toString();
        	selsname=jcb2.getSelectedItem().toString();
        	selkeyun=jcb5.getSelectedItem().toString();
        	selhuoyun=jcb6.getSelectedItem().toString();
        	selxianyun=jcb7.getSelectedItem().toString();
        	selyewu=jcb8.getSelectedItem().toString();
        	selstatus=jcb9.getSelectedItem().toString();
        	seljuwei=jcb11.getSelectedItem().toString();
        	
            //System.out.println("---ItemEvent performed:" + jcb1.getSelectedItem().toString() + "---");  
            //this.changeFontSize(Integer.parseInt(itemSize));  //�ı�����  
        }catch(Exception ex){                
        }  
    }  	
}
@Override
   public void valueChanged(ListSelectionEvent e) {
	// TODO Auto-generated method stub
	
}
}
