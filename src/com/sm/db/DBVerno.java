package com.sm.db;

public class DBVerno {
	public static String lastVerno="1.1.037b";
	public static String webVerno="1.1.037e";
	public static boolean ListenVerno(){
		//������°汾����true
		boolean t=false;
		if(lastVerno!=webVerno){
			t=true;
		}
		return t;
	}	
}
