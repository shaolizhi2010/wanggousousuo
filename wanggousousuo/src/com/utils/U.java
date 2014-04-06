package com.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class U {
	
//	public static String getPinyin(String str){
//		
//	}
	
	public static boolean isMoney(String money){
		if(StringUtils.isBlank(money)){
			return false;
		}
		if(money.matches("^[0-9]+(\\.[0-9]{1,2})?$")){
			return true;
		}
		return false;
	}
	
	public static String curTime(){
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	return  sdf.format(cal.getTime()) ;
	}
	
	public static String curDate(){
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return  sdf.format(cal.getTime()) ;
	}
	
	//str -> int
	public static int parseInt(String str){
		if(StringUtils.isBlank(str)){
			return 0;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			L.exception("U --- ", e.getMessage());
		}
		return 0;
	}
	
	public static Map jsonToMap(String json){
		Map jsonMap = new Gson().fromJson(json, new TypeToken<Map>(){}.getType() );
		if(jsonMap!=null){
			return jsonMap;
		}
		return new HashMap<String, String>();
	}
	
	public static List<Map> jsonToListMap(String json){
		List<Map> jsonListMap = new Gson().fromJson(json, new TypeToken<List<Map>>(){}.getType() );
		if(jsonListMap!=null){
			return jsonListMap;
		}
		return new ArrayList<Map>();
	}
	
	public static String jsonValue(String json, String key){
		
		Object obj =  jsonToMap(json).get(key);
		if(obj != null){
			return (String)obj;
		}
		return "";
	}
	
	public static String clean(String str){
		str = str.replaceAll("lang=\"zh-cn\"", "");
		return str;
	}
	
	public static void printList(List list){
		for(Object o : list){
			System.out.println(o.toString());
		}
	}
	public static void printArray(Object[] arr){
		for(Object o : arr){
			System.out.println(o.toString());
		}
	}
	public static void printMap(Map map){
		L.trace("U.printMap : ", "print map begin");
		for(Object o:  map.entrySet()){
			Map.Entry entry = (Map.Entry) o;
			System.out.println(entry.getKey() +" - "+entry.getValue());
		}
		L.trace("U.printMap : ", "print map end");
	}
}
