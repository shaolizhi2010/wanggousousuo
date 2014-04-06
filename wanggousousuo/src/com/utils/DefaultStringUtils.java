package com.utils;


import org.apache.commons.lang3.StringUtils;



/**
 * 
 * @author shaoliz
 *
 */
public class DefaultStringUtils {
	
	public static String getNumberStrFromString(String str){
		if(StringUtils.isBlank(str)){
			return "";
		}
		StringBuffer numbers = new StringBuffer();
		for(char c : str.toCharArray())
		{
		  if(Character.isDigit(c)) numbers.append(c);
		  if(c == '.') numbers.append(c);
		}
		return numbers.toString();
	}


}
