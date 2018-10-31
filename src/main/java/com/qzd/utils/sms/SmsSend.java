package org.ieforex.utils.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.bcics.sso.common.encrypt.MD5;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.ieforex.utils.PropertiesUtil;

public class SmsSend {
	
	private static HttpClient httpclient;
	private static String userName = null;
	private static String smsPassWord = null;
	private static String smsContent=null;
	static {
		Properties pro = PropertiesUtil.loadProperties("sms-send.properties");
		userName = pro.getProperty("sms.userName");
		smsPassWord = pro.getProperty("sms.passWord");
		smsContent=pro.getProperty("sms.text");
	}
	
//	public static void main(String[] args) {
//		try {
//			String sendCode = smsSend("13472770114","【荔枝返现网】验证码123343,15分钟有效");
//			System.out.println(sendCode);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	public static String smsSend(String phone,String code) throws Exception{
		httpclient = new SSLClient();
		String url = "https://dx.ipyy.net/sms.aspx";
		String accountName=userName;							//改为实际账号名
		String password=MD5.toMD5(smsPassWord);					//改为实际发送密码
		String text = smsContent.replaceFirst("@", code);
		
		String returnstatus="";							//成功返回Success 失败返回：Fail
		
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action","send"));
		nvps.add(new BasicNameValuePair("userid", ""));
		nvps.add(new BasicNameValuePair("account", accountName)); 	
		nvps.add(new BasicNameValuePair("password", password));		
		nvps.add(new BasicNameValuePair("mobile", phone));
		nvps.add(new BasicNameValuePair("content", text));
		nvps.add(new BasicNameValuePair("sendTime", ""));
		nvps.add(new BasicNameValuePair("extno", ""));

		post.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));

		HttpResponse response = httpclient.execute(post);

		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			// 将字符转化为XML
			String returnString=EntityUtils.toString(entity, "UTF-8");
			Document doc = DocumentHelper.parseText(returnString);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 获取根节点下的子节点的值
			returnstatus = rootElt.elementText("returnstatus").trim();
			String message = rootElt.elementText("message").trim();
			String remainpoint = rootElt.elementText("remainpoint").trim();
			String taskID = rootElt.elementText("taskID").trim();
			String successCounts = rootElt.elementText("successCounts").trim();

			System.out.println(returnString);
			System.out.println("返回状态为：" + returnstatus);
			System.out.println("返回信息提示：" + message);
			System.out.println("返回余额：" + remainpoint);
			System.out.println("返回任务批次：" + taskID);
			System.out.println("返回成功条数：" + successCounts);
			EntityUtils.consume(entity);
			return returnstatus;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return returnstatus;
	}

}
