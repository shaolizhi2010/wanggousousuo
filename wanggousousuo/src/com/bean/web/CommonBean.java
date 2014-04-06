package com.bean.web;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.logic.Cache;
import com.bean.logic.KeywordService;
import com.env.Env;
import com.env.StaticInfo;
import com.file.FileUtil;
import com.google.gson.Gson;
import com.mutiServer.MutiServerUtil;
import com.utils.C;
import com.utils.L;
import com.utils.MD5;

public class CommonBean extends BaseBean {

	public void execute(HttpServletRequest request, HttpServletResponse response, ParameterMap parameterMap)
			throws Exception {
		
		String method = parameterMap.getParameter("method"); //
		String filename = parameterMap.getParameter("filename"); //
		String callback = parameterMap.getParameter("callback"); 
		
		String returnJson = "";
		
		//注册server，其他server调用本方法，注册
		if(method!=null && method.equalsIgnoreCase("registeServer")){
			
			String url = parameterMap.getParameter("url");
			String md5 = parameterMap.getParameter("md5");
			
			L.log(this,"Reviced regist request, server : "+ url);
			
			new MutiServerUtil().addServer(url, md5);
			
		}
		//注销server，其他server调用本方法，注销
		if(method!=null && method.equalsIgnoreCase("logoffServer")){
			String url = parameterMap.getParameter("url");
			String md5 = parameterMap.getParameter("md5");
			
			L.log(this,"Reviced logoff request, server : "+ parameterMap.getParameter("url"));
			
			new MutiServerUtil().logoffServer(url, md5);
		}
		
//		//取blog列表 json
//		if(method!=null && method.equalsIgnoreCase("getBlogsJson")){
//			returnJson = new FileUtil().getFilesJson(Env.basePath + C.blogDir +filename,);
//			response(request, response, returnJson);
//		}
//		
//		//jsonp
//		if(method!=null && method.equalsIgnoreCase("getBlogsJsonp")){
//			returnJson = new FileUtil().getFilesJson(Env.basePath + C.blogDir +filename);
//			//for jsonp
//			returnJson =  callback+ "(" + returnJson + ")";	//for jsonp
//			response(request, response, returnJson);
//			
//		}
		//get cache jsonp
		if(method!=null && method.equalsIgnoreCase("getcachesJson")){
			List<String> list = new KeywordService().getKeywordListFromCache(Env.basePath);
			returnJson = new Gson().toJson(list);
			//for jsonp
			//returnJson =  callback+ "(" + returnJson + ")";	//for jsonp
			response(request, response, returnJson);
			
		}
		else if(method!=null && method.equalsIgnoreCase("getcacheContent")){
			String keyword = parameterMap.getParameter("keyword");
			String shopname = parameterMap.getParameter("shopname");
			returnJson = new Cache().get(keyword, shopname, Env.basePath, true);
			response(request, response, returnJson);
		}

	}//end execute
	
 
}
