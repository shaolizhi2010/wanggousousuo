package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Regex {
	
	public static String dazheRegex = "[折|减|送|促|低|省|活动|场|惊|喜|限时|庆|原价|市场价]";
	public static String inValidUrl1 = "^(//|javascript)+.*$";
	public static String inValidUrl2 = "^.*(png|jpg|gif)+$";
	
	public static Boolean containNumber(String str){
		 
		return str.matches(".*\\d.*");
	}
	
}
