package com.sm.view;
/**
 * ��xml�ַ������н������������������Tm_rs
 */

import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.chainsaw.Main;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlAnalysis {
	Vector Tm_hang;
	static Vector Tm_rs=new Vector();
	static String message_TEXT=null;
	static int totalPages_TEXT;
	static String verno_TEXT=null;
	
	 public XmlAnalysis(String xmlString){
		 try {
	           Document doc = DocumentHelper.parseText(xmlString);
	           Element root=doc.getRootElement();
	           /*//��ȡҳ��
	           Element totalPages=root.element("totalPages");
	           totalPages_TEXT=Integer.valueOf(totalPages.getText());*/
	           //��ȡ�汾��
               Element verno=root.element("verno");
	           verno_TEXT=verno.getText();
	           
	           Iterator it = root.elementIterator();	           	                      
	           while (it.hasNext()) 
	           {
	        	Tm_hang=new Vector();
	            Element element = (Element) it.next();
	            Tm_hang.add(element.elementText("Ψһ��ʶ��"));
	            Tm_hang.add(element.elementText("��վ����"));
	            Tm_hang.add(element.elementText("��վ�籨��"));
	            Tm_hang.add(element.elementText("�籨����"));
	            Tm_hang.add(element.elementText("�����Ժ�"));
	            Tm_hang.add(element.elementText("��������"));
 
	            Tm_rs.add(Tm_hang);

	           }
			  } catch (Exception e) {
			e.printStackTrace();
			}	
		  }
	 public Vector getXmlAnaRs(){
		 return Tm_rs;
	 }
	 //��ȡ���°汾��
	 public static String getVerno() {
			//System.out.println("webDBverno:"+verno_TEXT);
		 Tm_rs.removeAllElements();
			return verno_TEXT;
			
		}
		 /*public int getTotalPages() {
			System.out.println("totalPages:"+totalPages_TEXT);
			return totalPages_TEXT;
		}*/

}
