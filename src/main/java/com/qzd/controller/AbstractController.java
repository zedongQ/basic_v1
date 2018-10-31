package com.qzd.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;


/**
 * Controller公共组件
 * 
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpSession session;
	
	protected void redirectTo(HttpServletRequest request,HttpServletResponse response, String url) throws IOException{
		if (!StringUtils.isEmpty(url)) {
			response.sendRedirect(request.getContextPath()+"/"+url.replaceFirst("/", "")); 
		}
	}
	
	/** 
	 * 读取国际化文件里面的变量值 
	 *  
	 * @param msgCode 变量名称 
	 * @param args 参数 
	 * @return 
	 */  
	protected String message(String msgCode) {  
	  ReloadableResourceBundleMessageSource messageSource;  
	  messageSource = new ReloadableResourceBundleMessageSource();  
	  messageSource.setBasename("classpath:/messages");  
	  messageSource.setUseCodeAsDefaultMessage(true); 
	  return messageSource.getMessage(msgCode, null, new Locale("zh", "CN"));
	}
}
