package org.ieforex.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownloadPhoto {
	private static final long serialVersionUID = -6905547426347632324L;
	private static Logger log = Logger.getLogger(DownloadPhoto.class);

	/**
	 * 将服务器端生成的文件提供给客户端下载
	 * 
	 * @param request
	 * @param response
	 * @param tempFile
	 * @param fileName
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String basePath, String partPath, String fileName) {
		String fileNameStr = basePath + partPath;

		DownloadPhoto.initialRequestOrResponse(request, response, fileName);

		OutputStream output = null;
		FileInputStream fis = null;
		try {
			output = response.getOutputStream();
			File file = new File(fileNameStr);
			fis = new FileInputStream(file);

			Long fSize = file.length();
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-Length", String.valueOf(fSize));

			long pos = 0;
			if (null != request.getHeader("Range")) {
				// 断点续传
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				try {
					pos = Long.parseLong(request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
				} catch (NumberFormatException e) {
					log.error(request.getHeader("Range") + " is not Number!");
					pos = 0;
				}
			}
			fis.skip(pos);// 跳过 多少字节
			String contentRange = new StringBuffer("bytes ").append(
					new Long(pos).toString()).append("-").append(
					new Long(fSize - 1).toString()).append("/").append(
					new Long(fSize).toString()).toString();
			response.setHeader("Content-Range", contentRange); // 设置文件下载的 内容范围
			log.debug("Content-Range：" + contentRange);

			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) != -1) {
				output.write(b, 0, i);
			}
			output.flush();
			output.close();
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				output = null;
			}
		}
	}
	
	/**
	 * @Title: initialRequestOrResponse
	 * @Description: 文件下载的时候 设置 响应头信息
	 * @param request
	 * @param response
	 * @param fileName
	 *            void
	 * @throws
	 */
	public static void initialRequestOrResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
		if(null != null && !"".equals(fileName)) {
			fileName = DownloadPhoto.setFileName(request, fileName);
		} else {
			fileName = "";
		}
		
		response.reset();
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	}
	
	public static String setFileName(HttpServletRequest request, String fileName) {
		String filenamedisplay = fileName;
		String agent = request.getHeader("User-Agent");
		try {
			if (null!=agent&& 
							(
							-1!=agent.toUpperCase().indexOf("MSIE")// ie10,ie9及以下
							||	-1!= agent.indexOf("Edge")   //edge浏览器
							||  -1!=agent.indexOf("Trident/7.0") //ie11浏览器
							) 
				) //IE 系浏览器 
				{
				String fn = URLEncoder.encode(filenamedisplay, "UTF-8");
				if (fn.length() > 150) {
					filenamedisplay = new String(filenamedisplay.getBytes("GBK"), "ISO-8859-1");
				}
				filenamedisplay = fn;
				filenamedisplay = filenamedisplay.replaceAll("\\+", " ");
			}else { //其他浏览器
				filenamedisplay = new String(filenamedisplay.getBytes("GBK"), "ISO-8859-1");  
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filenamedisplay;
	}
}
