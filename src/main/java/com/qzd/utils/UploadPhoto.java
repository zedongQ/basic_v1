package org.ieforex.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import org.ieforex.utils.Constants;

public class UploadPhoto {
	private static final long serialVersionUID = -6905547426347632253L;
	private static Logger log = Logger.getLogger(UploadPhoto.class);

	public static String upload(String moduleName, File file, String fileName) {
		String newFilename = null;
		long now = (new Date()).getTime();

		String path = Constants.IEFOREX_DIR_PREFIX_WEB;
		Calendar cal = Calendar.getInstance();

		String strAfterPath = moduleName + File.separatorChar + cal.get(Calendar.YEAR) + "" + File.separatorChar + (cal.get(Calendar.MONTH) + 1) + File.separatorChar;
		path += strAfterPath;
		log.info("=====文件的路径：" + path);
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			newFilename = now + fileName.substring(index);
		} else {
			newFilename = Long.toString(now);
		}
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newFilename));
			bos = new BufferedOutputStream(fos);
			byte[] buf = new byte[4096];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pathTransform(strAfterPath + newFilename);
	}

	public static String upload(String moduleName, InputStream ins, String fileName) {
		String newFilename = null;
		long now = (new Date()).getTime();

		String path = Constants.IEFOREX_DIR_PREFIX_WEB;
		Calendar cal = Calendar.getInstance();

		String strAfterPath = moduleName + File.separatorChar + cal.get(Calendar.YEAR) + "" + File.separatorChar + (cal.get(Calendar.MONTH) + 1) + File.separatorChar;
		path += strAfterPath;
		log.info("=====文件的路径：" + path);
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			newFilename = now + fileName.substring(index);
		} else {
			newFilename = Long.toString(now);
		}
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(ins);

			FileOutputStream fos = new FileOutputStream(new File(dir, newFilename));
			bos = new BufferedOutputStream(fos);
			byte[] buf = new byte[4096];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pathTransform(strAfterPath + newFilename);
	}
	
	public static String pathTransform(String path) {
		return path.replace("\\", "/");
	}
}
