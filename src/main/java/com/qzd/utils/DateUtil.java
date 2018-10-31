package org.ieforex.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * yyyy-MM-dd 格式的日期对比
	 * bDate > aDate 返回 1
	 * bDate < aDate 返回 -1
	 * bDate = aDate 返回 0
	 * */
	public static int compareToDate(Date bDate,Date aDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String bDateString = sdf.format(bDate);
		String aDateString = sdf.format(aDate);
		ParsePosition pp = new ParsePosition(0);
		ParsePosition pp2 = new ParsePosition(0);
		bDate = sdf.parse(bDateString , pp);
		aDate = sdf.parse(aDateString , pp2);
		return bDate.compareTo(aDate);
	}
	
	     //判断选择的日期是否是本周  
	    public static boolean isThisWeek(long time)  
	    {  
	        Calendar calendar = Calendar.getInstance();  
	        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);  
	        calendar.setTime(new Date(time));  
	        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);  
	        if(paramWeek==currentWeek){  
	           return true;  
	        }  
	        return false;  
	    }  
	    //判断今天是否是周日
	    public static boolean isSunday(long time)  
	    {  
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(new Date(time)); 
	    	int day = calendar.get(Calendar.DAY_OF_WEEK);
	    	//星期天。星期天是1，类推
	    	if(day==1){
	    	   return true;
	    	}else{
	    	   return false;
	    	}
	    }  
	    //判断今天是否是周一
	    public static boolean isMonday(long time)  
	    {  
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(new Date(time)); 
	    	int day = calendar.get(Calendar.DAY_OF_WEEK);
	    	//星期天。星期天是1，类推
	    	if(day==2){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }  
	    //判断选择的日期是否是今天  
	    public static boolean isToday(long time)  
	    {  
	       return isThisTime(time,"yyyy-MM-dd");  
	    }  
	    //判断选择的日期是否是本月  
	    public static boolean isThisMonth(long time)  
	    {  
	         return isThisTime(time,"yyyy-MM");  
	    }  
	    private static boolean isThisTime(long time,String pattern) {  
	        Date date = new Date(time);  
	         SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
	         String param = sdf.format(date);//参数时间  
	         String now = sdf.format(new Date());//当前时间  
	         if(param.equals(now)){  
	           return true;  
	         }  
	         return false;  
	    }  
}
