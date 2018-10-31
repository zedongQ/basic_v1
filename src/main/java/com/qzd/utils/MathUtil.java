/**
 * 
 */
package com.qzd.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cuiyuguo
 *	处理一些基本数据转换等问题,其他方法你们项目开发时需要自己加
 */
public class MathUtil {
	public static int parseInt(String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static long parseLong(String value) {
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	numb  要处理的数据
	weishu 保留的位数
	type 四舍五入的类型 true:向上舍 false:向下舍
	*
	*/
	public static BigDecimal parseBigDecimal(BigDecimal numb,int weishu,boolean type){
		BigDecimal setScale = numb;
		if(numb!=null){
			if(type==true){
				setScale = numb.setScale(weishu,BigDecimal.ROUND_HALF_UP);
			}else if(type==false){
				setScale = numb.setScale(weishu,BigDecimal.ROUND_HALF_DOWN);
			}
		}
		return setScale;
	}
	
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        return dayWeek;
	}
	/*
	       是否是空，是空返回空，不是空去空格
	 */
	public static String isNull(Object obj) {
		String str="";
		if(obj!=null) {
			str = obj.toString().trim();
			return str;
		}
		return null;
	}

	public static String parseStr(Object obj){
		if(null != obj){
			return obj.toString();
		}else{
			return "";
		}
	}
}
