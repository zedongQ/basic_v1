/**
 * 
 */
package org.ieforex.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;



public class EntityUtil {
	public static Map<String, String> reflect(Object e) {  
		try{
        Field[] fields = e.getClass().getDeclaredFields();  
        Map<String, String> map=new HashMap<String, String>();
	        for(int i=0; i<fields.length; i++){  
	            Field f = fields[i];  
	            f.setAccessible(true);
//	            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(e));  
	        } 
	        return map;
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return null;
    }
//	public static void main(String[] args) {
//		Dealer dealer=new Dealer();
//		dealer.setAddress("123123");
//		Map<String, String> dealers= reflect(dealer);
//	}
}