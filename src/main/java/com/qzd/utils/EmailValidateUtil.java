package org.ieforex.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.ieforex.entity.ECustomer;
import org.ieforex.entity.WMessageTemplet;
import org.ieforex.mail.MailUtil;
import org.ieforex.mail.MimeMessageDTO;
import org.ieforex.service.ECustomerService;
import org.ieforex.service.WMessageTempletService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class EmailValidateUtil {
	
	private static String userName = null;
	private static String emailPassWord = null;
	static {
		Properties pro = PropertiesUtil.loadProperties("mail-send.properties");
		userName = pro.getProperty("mail.userName");
		emailPassWord = pro.getProperty("mail.passWord");
	}
	
    /**
     * 发送验证信息方法
     * @param request 
     * @param messageTempletService
     * @param customerService
     * @param emailTempl  邮件模版
     * @param modelName 请求接口名称
     * @param uuid 用户id
     * @param email 邮件地址
     * @return 返回信息
     */
    public static RESULT sendEmail(HttpServletRequest request,WMessageTempletService messageTempletService,ECustomerService customerService,String emailTempl,String modelName,String uuid,String email){
    	//获取邮件模版
		WMessageTemplet messageTemplet = messageTempletService.queryMessageTemplet(emailTempl);
		if(messageTemplet==null){
			return RESULT.error(message("mail.template.error"));
		}
		//获取时间
		Long time=new Date().getTime();
		//获取路径 
		String projectUrl = request.getRequestURL().toString();
		projectUrl = projectUrl.substring(0, projectUrl.lastIndexOf("/"));
		String href="<a href='"+projectUrl+"/"+modelName+"?uuid="+uuid+"&t="+time+"&email="+email+"'>"+projectUrl+"/"+modelName+"?uuid="+uuid+"&t="+time+"&email="+email+"</a>";
		//邮件信息
		String title=messageTemplet.getTitle();
		String content=messageTemplet.getContent();
		content=content.replaceAll("\\{href\\}", href);
		//发送邮件
		MimeMessageDTO message = new MimeMessageDTO(title,new Date(),content+"");
		try {
			MailUtil.publicsendEmailEx(userName, emailPassWord, email, message, true, null);
		} catch (Exception e) {
			if(modelName.equals("activeUser")){
				return RESULT.ok(message("user.singup.emailsenderror"));
			}else{
				return RESULT.ok(message("user.sendemail.error"));
			}
		}
		ECustomer customer=new ECustomer();
		customer.setChangeInfoDate(time);
		customer.setCustomerId(MathUtil.parseLong(uuid));
		customerService.updatePwdOrEmail(customer);
		if(modelName.equals("activeUser")){
			return RESULT.ok(message("user.singup.success"));
		}else{
			return RESULT.ok(message("user.sendemail.success"));
		}
		
    }

    /**
     * 验证链接是否有效
     * @param time 时间
     * @param uuid 用户的id
     * @param email 邮箱地址
     * @param model 
     * @return 链接是否失效 true未失效  false失效
     */
    public static Boolean emailValidate(String uuid,Long time,String email,ECustomer customer)  {
    	//时间处理
		Date date = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, -2);
		Date nowDate = calendar.getTime();
		if(!uuid.equals(customer.getCustomerId().toString())||nowDate.getTime()>=time||!customer.getChangeInfoDate().equals(time)||!email.equals(customer.getLoginEmail())){
			return false;
		}
		return true;
    } 
	
    /**
     * 手机验证码验证
     */
    public static Boolean smsValidate(String cookie,String smsCode,String key){
		try {
			String decryptCookid= AesCryptUtil.decrypt(cookie, key, key).trim();
			if(!decryptCookid.equals(smsCode)){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
	/** 
	 * 读取国际化文件里面的变量值 
	 *  
	 * @param msgCode 变量名称 
	 * @param args 参数 
	 * @return 
	 */  
	protected static String message(String msgCode) {  
	  ReloadableResourceBundleMessageSource messageSource;  
	  messageSource = new ReloadableResourceBundleMessageSource();  
	  messageSource.setBasename("classpath:/messages");  
	  messageSource.setUseCodeAsDefaultMessage(true);  
	  return messageSource.getMessage(msgCode, null, new Locale("zh", "CN"));
	}
}