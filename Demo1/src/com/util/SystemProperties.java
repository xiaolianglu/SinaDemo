package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class SystemProperties {
	
	private static final Logger logger = Logger.getLogger(SystemProperties.class);
	
	private static final Properties properties=new Properties();
	static{
		try {
			properties.load(SystemProperties.class.getResourceAsStream("/sina_url.properties"));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	private SystemProperties(){
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}

}
