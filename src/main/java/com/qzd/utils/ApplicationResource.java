package com.qzd.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class ApplicationResource {

	private static final Logger logger = Logger.getLogger(ApplicationResource.class);

	private static Properties proEnvironment;
	
	
	public static String getEnvironment(String name) {
		if (proEnvironment == null)
			proEnvironment = new Properties();
			try {
				proEnvironment.load(ApplicationResource.class
						.getResourceAsStream("/environment.properties"));
			} catch (IOException e) {
				logger.error("load environment.properties error:",e);
				return null;
			}
			
		return StringUtils.trimToEmpty(proEnvironment.getProperty(name));
	}
}
