package com.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyHtmlSerializer;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import com.html.Html;
import com.utils.App;
import com.utils.L;
import com.utils.U;

public class Connecter {
	
	/**
	 * 速度较慢 不建议使用,最好指明charset
	 */
	@Deprecated
	public static Html getHtml(String url) {
		long start1 = System.currentTimeMillis();
		url = URLUtils.standard(url);
		
		HttpClient httpclient = App.getInstance().getHttpClient();
		HttpGet httpget = null;
		
		String charsetStr = "";	//default
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
	        L.trace(null,"Connecter finshed download page, time is " + (System.currentTimeMillis()-starttime) + " url is " + url );
	        
	        HttpEntity entity = response.getEntity();
	        //EntityUtils.toString(entity);
	        InputStream entityContent = entity.getContent();
	        
	        starttime = System.currentTimeMillis();
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
	        	    if(StringUtils.substringBetween(line, "harset=\"","\"") !=null){//找到 charset，c可能大些 略去
	        	    		charsetStr = StringUtils.substringBetween(line, "harset=\"","\"");
	        	    		if(U.validateCharset(charsetStr)) break;
	        	    		
	        	    }
	        	    if(StringUtils.substringBetween(line, "harset=","\"") !=null){//找到 charset，c可能大些 略去
        	    		charsetStr = StringUtils.substringBetween(line, "harset=","\"");
        	    		if(U.validateCharset(charsetStr)) break;
	        	    }
	        	    if(StringUtils.lowerCase(line).contains("gbk")){
	        	    	charsetStr = "gbk";
	        	    	break;
	        	    }
	        	    if(StringUtils.lowerCase(line).contains("utf-8")){
	        	    	charsetStr = "utf-8";
	        	    	break;
	        	    }
	        	    if(StringUtils.lowerCase(line).contains("iso-8859-1")){
	        	    	charsetStr = "iso-8859-1";
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
	        L.trace(null,"Connecter finshed analyze charset, time is " + (System.currentTimeMillis()-starttime)  );
	        //自动设置字符集 end	        
	        
	        starttime = System.currentTimeMillis();
	        HtmlCleaner hc = new HtmlCleaner();
	        
	        TagNode node = hc.clean(entityContent, charsetStr);
	        String  pageSource = getPageSourceFromNode(node);
	        //pageSource = hc.getInnerHtml(note);
	        
	        html = new Html(pageSource);
	        html.charset(charsetStr);
	        L.trace(null,"Connecter finshed clean html, time is " + (System.currentTimeMillis()-starttime)  );
	        
	        L.trace("Connecter","Finesh read url and convert to html, time is " +(System.currentTimeMillis()-start1));
	        
		}catch (Exception e) {
			L.exception("Connecter", e.getMessage());
			L.exception("Connecter getHtml(url) failed", " url is " + url);
		}
		finally{
			if(httpget !=null){
				httpget.releaseConnection();
			}
			//httpclient.getConnectionManager().shutdown();
		}
		return html;
	}
	
	public static Html getHtml(String url,String charset){
		long start = System.currentTimeMillis();
		HtmlCleaner hc = new HtmlCleaner();
		TagNode node = null;
		String htmlSource = null;
		Html html = null;
		try {
			long startread = System.currentTimeMillis();
			node = hc.clean(new URL(url),charset);
			L.trace("Connecter", "Finished read data from url, time is " +(System.currentTimeMillis()-start));
			htmlSource = getPageSourceFromNode(node);
			//htmlSource = hc.getInnerHtml(note);
		} catch (Exception e) {
			L.exception("Connect getHtmlInfo ", e.getMessage());
			return new Html();
		}
		
		html = new Html(htmlSource);
		L.trace("Connecter","Finesh read url and convert to html, time is " +(System.currentTimeMillis()-start));
		return html;
		
	}
	
	public static String getPageSource(String url,String charset){
		
		Html html = getHtml(url,charset);
		if(html == null){
			return "";
		}
		return html.getPageSource();
	}
	
	public static String getPageSourceFromNode(TagNode node){
		//long start = System.currentTimeMillis();
		HtmlCleaner hc = new HtmlCleaner();
		CleanerProperties props = hc.getProperties();
		props.setNamespacesAware(false);
		PrettyXmlSerializer serializer = new PrettyXmlSerializer(props);
		String pageSource = serializer.getAsString(node,"UTF-8");
		pageSource = U.clean(pageSource);
		//L.trace("Connecter getPageSourceFromNode ", " finished, time is " + (System.currentTimeMillis() -start));
		return pageSource;
	}
	 
}
