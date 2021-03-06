package com.sm.test;
/**
 * 对xml字符串进行解析，并将结果处理返回Tm_rs
 */

import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.chainsaw.Main;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class GetDBDataModelOne {
	Vector Tm_hang;
	static Vector Tm_rs=new Vector();
	
	static String message_TEXT=null;
	static int totalPages_TEXT;
	static String verno_TEXT=null;
	
	 public GetDBDataModelOne(String xmlString){
		 try {
	           Document doc = DocumentHelper.parseText(xmlString);
	           Element root=doc.getRootElement();
	           
	           Element message=root.element("message");
	           message_TEXT=message.getText();
	           
	           
	           Element totalPages=root.element("totalPages");
	           totalPages_TEXT=Integer.valueOf(totalPages.getText());
	           
	           
	           Element verno=root.element("verno");
	           verno_TEXT=verno.getText();
	           
	           
	           Iterator it = root.elementIterator();
	           while (it.hasNext()) 
	           {
	        	Tm_hang=new Vector();
	            Element element = (Element) it.next();
	            //Tm_hang.add(element.elementText("message"));
	            Tm_hang.add(element.elementText("唯一标识码"));
	            Tm_hang.add(element.elementText("车站名称"));
	            Tm_hang.add(element.elementText("车站电报码"));
	            Tm_hang.add(element.elementText("电报所名"));
	            Tm_hang.add(element.elementText("所名略号"));
 
	            Tm_rs.add(Tm_hang);

	           }
			  } catch (Exception e) {
			e.printStackTrace();
			}	
		  }
	 public Vector getRs(){
		 return Tm_rs;
	 }
	 public String getVerno() {
		System.out.println("verno:"+verno_TEXT);
		return verno_TEXT;
	}
	 public int getTotalPages() {
		System.out.println("totalPages:"+totalPages_TEXT);
		return totalPages_TEXT;
	}
	 public  String getMessage() {
		System.out.println("message:"+message_TEXT);
		return message_TEXT;
	} 
	 
}

