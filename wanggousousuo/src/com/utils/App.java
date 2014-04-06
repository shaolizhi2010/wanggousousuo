package com.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//singleton share by whole server
public class App {
	
	private App(){
	}
	
	private static App app = new App();
	
	private HttpClient httpClient = null;
	private WebApplicationContext ctx = null;
	private SessionFactory sessionFactory = null;
	
	public static App getInstance(){
		return app;
	}
	
	public HttpClient getHttpClient(){
		if(httpClient == null){
			PoolingClientConnectionManager poolManager = new PoolingClientConnectionManager();
			poolManager.setMaxTotal(100);
			
			httpClient = new DefaultHttpClient(poolManager);
			httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,  
	                "Mozilla/4.0 (compatible; MSIE 7.0; Win32");  
			httpClient.getParams().setParameter(  
	                CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);  
	        
	        // 浏览器兼容性  
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,  
	                CookiePolicy.BROWSER_COMPATIBILITY);  
		}
		return httpClient;
	}
	
	public WebApplicationContext getWebApplicationContext(){
		if(ctx == null){
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(ServletActionContext
							.getServletContext());
		}
		return ctx;
	}
	
	public SessionFactory getSessionFactory(){
		
		if(sessionFactory == null){
			sessionFactory = getWebApplicationContext().getBean(SessionFactory.class);
		}
		return sessionFactory;
	}
}
