package com.sm.tools;
/**
 * ��̬���ر���ͼƬ
 * @author shifrank1989
 *
 */
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

public class ImagePanel extends JPanel {

   Image im;
   //ָ��panel��С
	/*public ImagePanel(Image im) {
		this.im=im;
		//����Ӧ��С
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//Dimension size = new Dimension(w, h);
		this.setSize(w,h);
	
	}
	public void paintComponents(Graphics g){
		
		//super.paintComponent(g);
		g.drawImage(im, 0, 0, this.getWidth(),this.getHeight(),this);
	}*/
   public ImagePanel(Image im)
   {
   this.im = im;
        //����Ӧ��С
 		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
 		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
        Dimension size = new Dimension(w, h);
        setSize(size);
 
   }

   protected void paintComponent(Graphics g)
   {
   g.drawImage(im, 0, 0,this.getWidth(),this.getHeight(),this);
   }
   
}
