package com.connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import com.digger.CommonCommodityDigger;
import com.env.Env;
import com.exception.BaseException;
import com.utils.App;
import com.utils.C;
import com.utils.L;
import com.utils.U;

public class Connecter {
	
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
	
	protected static Map<String, String> getPageSource(String url) throws BaseException {
		
		L.trace("Connect getPageSource() ", "Url is " + url);
		
		HttpClient httpclient = App.getInstance().getHttpClient();
		HttpGet httpget = null;
		Map<String, String> map = new HashMap<String, String>();
		try{
			L.debug(Connecter.class.getName(), "connecting --- " + url);
			
	        httpget = new HttpGet(url);
	        httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        httpget.setHeader("Accept-Language","zh-CN"); 
	        httpget.setHeader("Accept-Charset","UTF-8,GBK;q=0.7"); 
	        
	        String baseUrl = getdomain(url);
	        httpget.setHeader("Referer",baseUrl); //模拟浏览器//TODO
	        
	        HttpResponse response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        InputStream entityContent = entity.getContent();
	        
	        /* 自动设置字符集 begin */
	        String charsetStr = "";	//default
	        Charset charset = ContentType.getOrDefault(entity).getCharset();
	        if(charset != null){	/* response 设置了charset */
	        	charsetStr = charset.toString();
	        }
	        else{	/* 如果http response 没有指定 charset，从返回html 头中查找 */ 
	        	BufferedReader in = new BufferedReader(new InputStreamReader(entityContent));
	        	String line = null;

	        	while((line = in.readLine()) != null) {	//逐行读取html内容
	        	    if(StringUtils.substringBetween(line, "charset=","\"") !=null){//找到 charset
	        	    		charsetStr = StringUtils.substringBetween(line, "charset=","\"");
	        	    		break;
	        	    }
	        	    if(line.indexOf("/head")>0){	//html head部分已经结束，不用再找了
	        	    	break;
	        	    }
	        	}
	        }
	        if(StringUtils.isBlank(charsetStr)){	//未找到字符集
	        	L.exception("Connecter", url + " --- " + "无法确定字符集，设置为默认字符集 utf-8");
	        	charsetStr = "UTF-8";	//default
	        }
	        map.put("charset", charsetStr);
	        map.put("pageSource", IOUtils.toString(entityContent,charsetStr));
	        //自动设置字符集 end	        
	        
	        return map;
	        
		}catch (Exception e) {
			L.exception("Connecter", e.getMessage());
			L.exception("Connecter getPageSource() ", " url is " + url);
		}
		finally{
			if(httpget !=null){
				httpget.releaseConnection();
			}
			//httpclient.getConnectionManager().shutdown();
		}
		return map;
	}
	
	public static String connect(String preUrl, String keyword, String requestEncodeCharset){
		String searchUrl = buildUrl(preUrl, keyword, requestEncodeCharset);
		return connect(searchUrl, requestEncodeCharset);
	}
	
	public static String connect(String url, String requestEncodeCharset){
		String xmlString = "";
		try{
			
			Map<String, String> returnMap  = getPageSource(url);
			String pageSource = returnMap.get("pageSource");
			String charset = returnMap.get("charset");
			
			//clean page source to xml format
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(pageSource);
			
			CleanerProperties props = hc.getProperties();
			props.setNamespacesAware(false);
			PrettyXmlSerializer serializer = new PrettyXmlSerializer(props);
			xmlString = serializer.getAsString(node,charset);
			xmlString = U.clean(xmlString);
//			System.out.println(xmlString);
			if(!Env.isProd){
				IOUtils.write(xmlString, new FileOutputStream(Env.basePath+"out.txt"),"UTF-8");
			}
			
			return xmlString;
		}catch (Exception e) {
			L.exception("Connecter", e.getMessage());
		}

		return xmlString;
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
	
	public static void connecct(String url) throws BaseException{
		HttpClient httpclient = App.getInstance().getHttpClient();
		HttpGet httpget = null;
		Map<String, String> map = new HashMap<String, String>();
		try{
			L.trace(Connecter.class.getName(), "connecting --- " + url);

	        httpget = new HttpGet(url);
	        httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        httpget.setHeader("Accept-Language","zh-CN"); 
	        httpget.setHeader("Accept-Charset","UTF-8,GBK;q=0.7"); 
	        String baseUrl = getdomain(url);
	        
	        httpget.setHeader("Referer",baseUrl); //模拟浏览器//TODO
	        
	        HttpResponse response = httpclient.execute(httpget);
		}catch (Exception e) {
			throw new BaseException("Connecter", e.getMessage());
		}
	}
}
