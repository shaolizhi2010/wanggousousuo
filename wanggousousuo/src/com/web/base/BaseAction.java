package com.web.base;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.utils.App;

public class BaseAction extends ActionSupport{
	protected static WebApplicationContext beanCtx = App.getInstance().getWebApplicationContext();
	
	public void test(){
		
	}
	
}
