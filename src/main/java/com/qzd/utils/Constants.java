package org.ieforex.utils;

import org.ieforex.utils.ApplicationResource;


/**
 * 常量
 * 
 * @author user
 */
public class Constants {
	public static String UPLOAD_BASE_DIR; //文件上传的根目录
	public static String IEFOREX_DIR_PREFIX_WEB;// 易汇文件目录前缀
	static {
		if (null == UPLOAD_BASE_DIR) {
			if (!"".equals(ApplicationResource.getEnvironment("upload_base_dir"))) {
				UPLOAD_BASE_DIR = ApplicationResource.getEnvironment("upload_base_dir");
			}else{
				UPLOAD_BASE_DIR = "/home/dev/oper/UploadFiles/";
			}
		}
		
		
		// 文件存放目录
		if (null == IEFOREX_DIR_PREFIX_WEB) {
			if (!"".equals(ApplicationResource.getEnvironment("ieforex_dir_prefix"))) {
				IEFOREX_DIR_PREFIX_WEB = ApplicationResource.getEnvironment("ieforex_dir_prefix");
			}else{
				IEFOREX_DIR_PREFIX_WEB = UPLOAD_BASE_DIR +"ieforex/";
			}
		}
	}
}