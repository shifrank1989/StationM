package com.sm.db;

import com.sm.tools.MyTools;
import com.sm.view.XmlAnalysis;

/**
 * @author shifrank1989
 * 数据版本号
 * webVerno为网络数据库版本号
 * lastVerno为本地数据库版本号
 */
public class DBVerno {
	public static String lastVerno="1.1.037b";
	// public static String webVerno="1.1.037e";
	static XmlAnalysis x=new XmlAnalysis(new MyTools().xmlString);
	public static String webVerno=x.getVerno().trim();
	public static boolean ListenVerno(){
		//如果有新版本返回true
		boolean t=false;
		if(lastVerno.equals(webVerno)){
			t=false;
		}else{
			t=true;
		}
		
		System.out.println(t);
		System.out.println("lastVerno "+lastVerno);
		System.out.println("webVerno "+webVerno);
		return t;
	}	
}
