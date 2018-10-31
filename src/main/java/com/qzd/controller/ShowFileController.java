package org.ieforex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ieforex.utils.Constants;
import org.ieforex.utils.DownloadPhoto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShowFileController extends AbstractController {
	
	@RequestMapping(value="showFile", method=RequestMethod.GET)
	public void customerDetailPage(HttpServletResponse response,Model model,HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//这里需要获取图片的路径和名称
		String url = request.getParameter("url");
		if(null == url || "".equals(url)) {
			return ;
		}
		String fileName = request.getParameter("fileName");
		DownloadPhoto.download(request, response, Constants.IEFOREX_DIR_PREFIX_WEB, url, fileName);
	}
	
}
