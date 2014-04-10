package com.connect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import com.utils.C;
import com.utils.L;

public class URLUtils {
	
	/**
	 * 将不规范的url 转为 规范的, http开头的
	 * @param url
	 * @return
	 */
	public static String standard(String url){
		if(url == null){
			return "";
		}
		if(!url.startsWith("http")){
			url = "http://"+url;
		}
		return url;
	}
	
	public static String getShopNameFromUrl(String url){
		String domain = getdomain(url);
		String[] subs = domain.split("\\.");
		for(int i= subs.length;i>=1;i--){
			if(!"com".equalsIgnoreCase(subs[i-1]) 
					&& !"cn".equalsIgnoreCase(subs[i-1]) 
					&& !"net".equalsIgnoreCase(subs[i-1]) 
					&& !"org".equalsIgnoreCase(subs[i-1]) 
					&& !"info".equalsIgnoreCase(subs[i-1])
					&& !"edu".equalsIgnoreCase(subs[i-1])
					&& !"hk".equalsIgnoreCase(subs[i-1])){
				
				//如果不是顶级域名后缀，则是shop的name
				return subs[i-1];
			}
			
		}
		return "";
	}
	
	public static String getdomain(String url){

		if(!url.startsWith("http")  ){
			if(url.startsWith("www")){
				return "http://"+url;
			}
			else{
				return "http://www."+url;	
			}
			
		}
		return StringUtils.substringBetween(url, "http://", "/");
	}
	
public static String buildUrl(String url, String keyword,String encodeCharset) {
		
		//init keyword
		String encodedKeyword = keyword;
		
		if(!StringUtils.isBlank(encodeCharset)){ //need encoding
			try {
				encodedKeyword = URLEncoder.encode(keyword, encodeCharset);
			} catch (UnsupportedEncodingException e) {
				L.exception("Connecter", "method buildUrl, keyword --- " + keyword
						+ " --- can not encoding to " + encodeCharset);
			}
		}
		
		//init url
		String searchUrl = url;
		
		/* keyword 在url中间 */
		if(searchUrl.contains(C.keywordVar)){
			searchUrl = searchUrl.replaceAll(C.keywordVar, encodedKeyword);
		}
		else{	/* keyword 在url末尾 */
			if(!StringUtils.isBlank(encodedKeyword)){
				searchUrl = searchUrl + encodedKeyword;
			}
		}
		return searchUrl;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
