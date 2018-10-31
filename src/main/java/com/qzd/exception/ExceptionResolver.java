/**
 * 
 */
package com.qzd.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzd.utils.RESULT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * @author cuiyuguo
 *	错误统一处理
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {
		// 判断是否ajax请求
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || 
			 (request.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 如果不是ajax，JSP格式返回
			// 这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
			logger.error(exception.getMessage(), exception); 
			// 对于非ajax请求，我们都统一跳转到error.jsp页面
			ModelAndView mview = new ModelAndView("pages/error/error");
        	mview.addObject("exception", exception);
        	
            return mview;
		} else {
			// 如果是ajax请求，JSON格式返回
			try {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				//加解密时出错了，此时返回加密的错误信息给调用者
				if (exception instanceof AESCryptException) {
					writer.write(RESULT.error(exception.getMessage()).aes());
				} else {
					writer.write(JSON.toJSONString(RESULT.error(exception.getMessage())));
				}
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
