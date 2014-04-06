package com.utils;

import java.util.Date;


public class DefaultDateTimeUtils {
	
	public static String currentDateTimeStr(){
		return new Date(System.currentTimeMillis()).toLocaleString();
	} 
	
	public static String long2String(long timestamp){
		return new Date(timestamp).toLocaleString();
	}
}
