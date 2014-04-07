package com.bean.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utils.L;

public class ParameterMap {
	Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	public ParameterMap(Map<String, List<String>> map){
		this.map = map;
	}
	
	public String getParameter(String key){
		
		if(map.get(key)!=null &&
				map.get(key).size()>0){
			
			if(map.get(key).size()>1){
				L.exception(this, "getParameter result more than one :" + map.get(key).size());
			}
			
			return map.get(key).get(0);
		}
		return "";
	}
	
	public String[] getParameterValues(String key){
		if(map.get(key)==null){
			return new String[0];
		}
		
		return map.get(key).toArray(new String[map.get(key).size()]);
	}
}
