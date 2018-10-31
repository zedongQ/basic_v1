/**
 * 
 */
package com.qzd.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author tfj
 * 2014-8-22
 */
public class RequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	public static final String LAST_PAGE = "com.alibaba.lastPage";
    public static final String REDIRECT_HOME = "/";
    
    
    /**
     * 获取当前Request对象.
     * @return 当前Request对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    public static HttpServletRequest currentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }
    
    /**
     * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
     * @return 当前session对象或{@code null}.
     */
    public static HttpSession currentSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        return attrs.getRequest().getSession(false);
    }

    
    /**
     * 解密请求的页面
     * @param targetPage
     * @return
     */
    public static Map<String, String> getAllParameters(HttpServletRequest request) {
    	Enumeration em = request.getParameterNames();
    	Map<String, String> params = new HashMap<String, String>();
    	 while (em.hasMoreElements()) {
    	    String name = (String) em.nextElement();
    	    String value = request.getParameter(name);
    	    params.put(name, value);
    	}
    	return params;
    }
    /**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
    
}
