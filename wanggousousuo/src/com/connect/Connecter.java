package com.connect;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;

import com.html.Html;
import com.utils.App;
import com.utils.L;

public class Connecter {
	
	@Deprecated
	public static Html getRawHtmlInfo(String url) {
		
		//L.trace("Connect getPageSource() ", "Url is " + url);
		
		url = URLUtils.standard(url);
		
		HttpClient httpclient = App.getInstance().getHttpClient();
		HttpGet httpget = null;
		
		String charsetStr = "";	//default
		String pageSource = "";
		Html html = null;
		
		try{
			L.trace(Connecter.class.getName(), "connecting --- " + url);
			
			String baseUrl = URLUtils.getdomain(url);
	        httpget = new HttpGet(url);
	        httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        httpget.setHeader("Accept-Language","zh-CN"); 
	        httpget.setHeader("Accept-Charset","UTF-8,GBK;q=0.7"); 
	        httpget.setHeader("Referer",baseUrl); //模拟浏览器//TODO
	        
	        long starttime = System.currentTimeMillis();
	        //L.trace(null,"Connecter start download page, time is " + starttime  );
	        HttpResponse response = httpclient.execute(httpget);
	        //L.trace(null,"Connecter finshed download page, time is " + (System.currentTimeMillis()-starttime) + "+url is " + url );
	        
	        HttpEntity entity = response.getEntity();
	        //EntityUtils.toString(entity);
	        InputStream entityContent = entity.getContent();
	        
	        /* 自动设置字符集 begin */
	        //L.trace(null,"Connecter start analys charset, time is " + starttime  );
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
	        	    if(StringUtils.lowerCase(line).contains("gbk")){
	        	    	charsetStr = "gbk";
	        	    	break;
	        	    }
	        	    if(StringUtils.lowerCase(line).contains("utf-8")){
	        	    	charsetStr = "utf-8";
	        	    	break;
	        	    }
	        	    if(StringUtils.lowerCase(line).contains("gb2312")){
	        	    	charsetStr = "gb2312";
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
	        //L.trace(null,"Connecter finshed analyze charset, time is " + (System.currentTimeMillis()-starttime) + "+url is " + url );
	        //自动设置字符集 end	        
	        
	        starttime = System.currentTimeMillis();
	        pageSource = IOUtils.toString(entityContent,charsetStr);
	        L.trace(null,"Connecter finshed convert entityContent to string , time is " + (System.currentTimeMillis()-starttime)) ;
	        
	        //raw html 没进行clean等处理
	        
	        html = new Html();
	        
	        
	        html.setRawPageSource(pageSource);
	        
	        html.charset(charsetStr);
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
		return html;
	}
	
	public static Html getHtmlInfo(String url){
		Html html = getRawHtmlInfo(url);
		html.init();
		return html;
		
	}
	
	public static String getPageSource(String url){
		
		Html html = getHtmlInfo(url);
		if(html == null){
			return "";
		}
		return html.getPageSource();
	}
	
	@Deprecated
	public static String getRawPageSource(String url){
		Html html = getRawHtmlInfo(url);
		if(html == null){
			return "";
		}
		return html.getRawPageSource();
	}
	 
}
