/**
 * 
 */
package com.qzd.language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cuiyuguo
 *	网站语言设置
 */
public class LanguageCookieInterceptor implements HandlerInterceptor{  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
            throws Exception {  
        
        return true;  
    }  
  
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception { 
    	if (null != modelAndView && handler instanceof HandlerMethod) {
    		String viewName = modelAndView.getViewName();
    		viewName = "pages/" + viewName;
    		
    		modelAndView.setViewName(viewName);
		}
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
          
    }  
  
}