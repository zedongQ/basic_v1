package org.ieforex.utils;

public class charEcordingFilterUtil {
	
	public static String specialCharFilter(String str){
		if(str!=null){
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("<", "&lt;");
		}
		return str;
	}
}
