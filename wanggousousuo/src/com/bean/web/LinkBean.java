package com.bean.web;

import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import sun.net.www.http.HttpClient;

import com.bean.logic.LinkService;
import com.db.DB;
import com.google.gson.Gson;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;

public class LinkBean extends BaseBean {

	public void execute(HttpServletRequest request, HttpServletResponse response, ParameterMap parameterMap)
			throws Exception {


		String actionType = request.getParameter("actiontype"); //action type
		String returnMsg = "";
		
		if( StringUtils.isBlank(actionType)){
			return ;
		}
		
		if(actionType.equals("addlink")){
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String desc = request.getParameter("desc");
			
			L.debug(this, "add link --- " + name + " --- " + url + " --- " + desc );
			

			LinkService linkService = new LinkService();
			boolean result =linkService.add(name, url);
			
			if(result){
				returnMsg = "提交成功！";
			}
			else{
				returnMsg = "提交失败！请稍后再试";
			}
			
			
		}
		
		 
		// return json first
		response.getOutputStream().write(returnMsg.getBytes("UTF-8")); // return
																		// to
		try{
			response.getOutputStream().flush();
		}catch (java.net.SocketException e) {
			L.exception(this, e.getMessage());
			// ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
			//catch this and do nothing, this is because the client do not receive from server any more
		}
	
		response.getOutputStream().close();
 
	}
}
