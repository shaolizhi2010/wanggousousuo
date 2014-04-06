package com.digger.util;


public class DiggerUtil {
	public static Boolean containsDomain(String str){
		String regexString = "^.*(\\.com|\\.cn|\\.net|\\.org).*$";
		return str.matches(regexString);
	}
	
	/* /product/3967.htm 的形式，没有商城的域名,则补全url	*/
	public static String getFullUrl(String url, String domain){
		
		if(!DiggerUtil.containsDomain(url) && url.length()>5){	/* /product/3967.htm 的形式，没有商城的域名*/
			
			url = url.replaceAll("http://", "");
			url = url.replaceAll("https://", "");
			url = domain + "/" + url;	/*补全url*/
			url = url.replaceAll("///", "/");
			url = url.replaceAll("//", "/");
			url = "http://www." + url;
			
		}
		return url;
	}
}
