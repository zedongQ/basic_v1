package com.qzd.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class LoginHttpClientUtil {

		@Test  
	    public void jUnitTest() {  
			 String username = "17621201836";//用户名
			 String password = "111111";//密码
			 String url = "http://localhost:8070/ieforex-web-ch/externalLogin/validation";//请求地址
			 String loginType = "1";//登录类型
			 String postForm = LoginHttpClientUtil.postForm(username,password,loginType,url);  
			 System.out.println("--------------------------------------");  
             System.out.println("Response content: " + postForm);  
             System.out.println("--------------------------------------");  
			
	    }  
		@Test  
	    public void jUnitTest2() {  //发送验证码
			
			 String post = LoginHttpClientUtil.post("17621201835","http://localhost:8070/ieforex-web-ch/externalLogin/smsCode");  
			 System.out.println("--------------------------------------");  
             System.out.println("Response content: " + post);  
             System.out.println("--------------------------------------");  
			
	    }  
	  
	    /** 
	     * post方式提交表单（模拟用户登录请求） 
	     */  
	    public static String postForm(String parm1,String parm2,String loginType,String Url) {  
	        // 创建默认的httpClient实例.    
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        // 创建httppost    
	        HttpPost httppost = new HttpPost(Url);  
	        // 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	        formparams.add(new BasicNameValuePair("loginType", loginType)); 
	        if("1".equals(loginType)) {
		        formparams.add(new BasicNameValuePair("username", parm1));  
		        formparams.add(new BasicNameValuePair("password", parm2));  
	        }else if("0".equals(loginType)) {
		        formparams.add(new BasicNameValuePair("loginPhone", parm1));  
		        formparams.add(new BasicNameValuePair("smsCode", parm2));  
	        }
	        UrlEncodedFormEntity uefEntity;  
	        try {  
	            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
	            httppost.setEntity(uefEntity);  
	            System.out.println("executing request " + httppost.getURI());  
	            CloseableHttpResponse response = httpclient.execute(httppost);  
	            try {  
	                HttpEntity entity = response.getEntity();  
	                if (entity != null) {  
	                    return EntityUtils.toString(entity, "UTF-8");
	                }  
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return null;
	    }  
	  
	    /** 
	     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
	     */  
	    public static String post(String loginPhone,String Url) {  
	        // 创建默认的httpClient实例.    
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        // 创建httppost    
	        HttpPost httppost = new HttpPost(Url);  
	        // 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	        formparams.add(new BasicNameValuePair("loginPhone", loginPhone));  
	        UrlEncodedFormEntity uefEntity;  
	        try {  
	            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
	            httppost.setEntity(uefEntity);  
	            System.out.println("executing request " + httppost.getURI());  
	            CloseableHttpResponse response = httpclient.execute(httppost);  
	            try {  
	                HttpEntity entity = response.getEntity();  
	                if (entity != null) {  
	                    return EntityUtils.toString(entity, "UTF-8");
	                }  
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return null;
	    }  
	  
	    /** 
	     * 发送 get请求 
	     */  
	    public void get() {  
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        try {  
	            // 创建httpget.    
	            HttpGet httpget = new HttpGet("http://www.baidu.com/");  
	            System.out.println("executing request " + httpget.getURI());  
	            // 执行get请求.    
	            CloseableHttpResponse response = httpclient.execute(httpget);  
	            try {  
	                // 获取响应实体    
	                HttpEntity entity = response.getEntity();  
	                System.out.println("--------------------------------------");  
	                // 打印响应状态    
	                System.out.println(response.getStatusLine());  
	                if (entity != null) {  
	                    // 打印响应内容长度    
	                    System.out.println("Response content length: " + entity.getContentLength());  
	                    // 打印响应内容    
	                    System.out.println("Response content: " + EntityUtils.toString(entity));  
	                }  
	                System.out.println("------------------------------------");  
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	  
	}

