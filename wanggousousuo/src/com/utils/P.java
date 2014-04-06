package com.utils;

import java.util.Properties;
 

public class P {
	
	static Properties properties = new Properties();
	
	static{
		try {
			properties.load( P.class.getResourceAsStream("/config.properties") );
		} catch (Exception e) {
			L.exception(P.class, "load property file error" + e.getMessage());
		}
	}
	
	public static Properties getProperties(){
		
		try {
			properties.load( P.class.getResourceAsStream("/config.properties") );
		} catch (Exception e) {
			L.exception(P.class, "load property file error" + e.getMessage());
		}
		return properties;
	}
	
	public static String getProperty(String key){
		return properties.get(key)+"";
	}
	
//	public static String autoSearch(){
//		return getProperties().getProperty(C.autoSearch);
//	}
	
//	public static String loglevel(){
//		return getProperties().getProperty(C.loglevel);
//	}
}
