package com.sm.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Position.Bias;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

import com.sm.tools.MyTools;

public class Login extends JDialog implements  ActionListener{
	JPanel jp1,jp2; 
	JLabel jl1,jl2;
	JTextField jname;
	JPasswordField jpassword;
	JButton jb1,jb2;
	Log log = LogFactory.getLog( this.getClass());
	public static String userName;
	public static void main (String [] args){
		Login lg=new Login();		
	}
	public Login(){
		this.setLayout(null);
		
		LoginImage li=new LoginImage();
		li.setBounds(0,0,360,120);
		this.add(li);
		
		this.setTitle("电报站名略号管理系统");
		Container ct=this.getContentPane();
		jl1=new JLabel("请输入用户名:");
		//jl1.setFont(MyTools.f1);
		jl1.setBounds(45,157,100,15);
		ct.add(jl1);
		jl2=new JLabel("请输入密码:");
		//jl2.setFont(MyTools.f1);
		jl2.setBounds(45,210,100,15);
		ct.add(jl2);
		jname=new JTextField();
		jname.setBounds(137, 147, 166, 25);
		ct.add(jname);
		jpassword=new JPasswordField();
		jpassword.setBounds(137,200,166,25);
		ct.add(jpassword);
		jb1=new JButton("登陆");
		jb1.addActionListener(this);
		jb1.setBounds(65, 249, 70, 30);
		ct.add(jb1);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jb2.setBounds(198, 249, 70, 30);
		ct.add(jb2);
		
		
		this.setSize(360, 360);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-200);		
		this.setVisible(true);
		
       }
	class LoginImage extends JPanel{
		Image im;
		public LoginImage(){
			try {
				im = ImageIO.read(new File("image//loginbg.jpg"));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		public void paintComponent(Graphics g){
			g.drawImage(im, 0, 0, 360,120,this);
		}
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			userName=jname.getText().trim();
			String password=new String(jpassword.getPassword());
			userModle um=new userModle();
			String rs=um.checkuser(userName, password);
			if(rs.equals("")){
				JOptionPane.showMessageDialog(this, "请输入正确的用户名或密码！");
			}else{
				new zmlh();
				MDC.put("userName",userName);
				log.info("成功登陆"); 
				this.dispose();
			}
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
	

}
