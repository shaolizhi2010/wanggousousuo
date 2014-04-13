package com.bean.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.L;



public abstract class BaseBean extends  HttpServlet{
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		try {
			Map<String, List<String>> map = parseParameters(req);
			ParameterMap parameterMap = new ParameterMap(map);
			execute(req, resp, parameterMap);
			
		} catch (Exception e) {	//handle all sub servlet exception here
			L.exception(this, e.getMessage());
		}
		
	}

	
	//implement logic in this servlet
	public abstract void execute(HttpServletRequest req, HttpServletResponse resp, ParameterMap parameterMap) throws Exception ;
	
	public Map<String, List<String>> parseParameters(HttpServletRequest req){
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		if(req.getParameterMap()==null){
			return map;	//empty map
		}
		
		for(Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()){
			
			if(entry.getValue()==null) continue;
			
			String key = entry.getKey();
			String newValue = "";
			
			String[] values = entry.getValue();
			List<String> newValues = new ArrayList<String>();//转charset后的
			
			for(String value:values){
				if(value==null){
					newValue = "";
				}
				try {
					newValue = new String(value.getBytes("iso-8859-1"),"utf-8");
					
				} catch (UnsupportedEncodingException e) {
					continue;
				}
				newValues.add(newValue);
			}
			map.put(key, newValues);
		
		}
		
		return map;
	}
	
	
	
	public void response(HttpServletRequest request, HttpServletResponse response, String returnMsg){
		
		try{
			response.getOutputStream().write(returnMsg.getBytes("UTF-8")); 
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch ( Exception e) {
			L.exception(this, e.getMessage());
			// ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
			//catch this and do nothing, this is because the client do not receive from server any more
		}
		return;
	}

}


