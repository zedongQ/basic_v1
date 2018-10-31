package org.ieforex.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Idcard {

	public static Boolean certification(String name,String iDNo) {
		
		 	String host = "http://aliyunverifyidcard.haoservice.com";
		    String path = "/idcard/VerifyIdcardv2";
		    String method = "GET";
		    String appcode = "6c7c4b79774f4ff49911732892779f0e";
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("cardNo", iDNo);
		    querys.put("realName", name);
		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
		    	//System.out.println(response.toString());
		    	//获取response的body
		    	String returnString = EntityUtils.toString(response.getEntity());
		    	System.out.println(returnString);
		    	//returnString = "{'error_code': 0,'reason': 'Success','result': {'realname': '张三','idcard': '330329199001020022','isok': false,'IdCardInfor': {'area': '山西省太原市清徐县','sex': '男','birthday': '1985-4-10'}}}";
		    	JSONObject json = new JSONObject(returnString); 
		    	JSONObject result = json.getJSONObject("result");
		    	//String reason = (String)json.get("reason");
		    	Boolean isok = (Boolean)result.get("isok");
		    	//if("Success".equals(reason.trim())||"成功".equals(reason.trim())){
		    		return isok;
		    	//}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    return false;
	}

}
