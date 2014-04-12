package com.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class U {
	
//	public static String getPinyin(String str){
//		
//	}
	
	public static String toString(Object o){
		if(o == null){
			return "";
		}
		else{
			return o.toString();
		}
	}
	
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
		
		//排除大小写干扰
		str = str.replaceAll("Head", "head");
		str = str.replaceAll("HEAD", "head");
		str = str.replaceAll("Description", "description");
		str = str.replaceAll("DESCRIPTION", "description");
		str = str.replaceAll("Keyword", "keyword");
		str = str.replaceAll("KEYWORD", "keyword");
		str = str.replaceAll("Content-Type", "content-type");
		
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
	public String getRulePath(){
		String path = this.getClass().getClassLoader().getResource("").getPath();
		path = StringUtils.substringBeforeLast(path, "target");
		return path;
	}
	
	public static DBObject toDBObject(Object o){
		Class clz = o.getClass();
		DBObject dbo = new BasicDBObject();
		
		Method[] methods = clz.getDeclaredMethods();
		for(Method m : methods){
			String methodName = m.getName();
			if(methodName.startsWith("get")){
				try {
					String fieldName = U.getFieldNameFromMethod(methodName,"get");
					String value = U.toString(m.invoke(o));
					if(value.length()>0){	//do not save empty value
						
						if(fieldName.equalsIgnoreCase("id")){
							dbo.put(fieldName, new ObjectId(value));
						}
						else{
							dbo.put(fieldName, value);
						}
						
						
					}
					
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					//do nothing
					e.printStackTrace();
				}
			}
		}
		return dbo;
	}
	
	public static <T>T toEntity(DBObject dbo, Class<T> clz){
		//this.name = U.toString(dbo.get("name"));
		T obj = null;
		try {
			obj = clz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
			Method[] methods = clz.getDeclaredMethods();
			for(Method m : methods){
				String methodName = m.getName();
				if(methodName.startsWith("set")){
					String fieldName = U.getFieldNameFromMethod(methodName,"set");
					String value = null;
					
					try {
						if(fieldName.equalsIgnoreCase("id")){
							value = U.toString(dbo.get("_id"));
							m.invoke(obj, value);
						}
						else{
							value = U.toString(dbo.get(fieldName));
							m.invoke(obj, value);
						}
						
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// do nothing
						e.printStackTrace();
					}
				}
			}

		return obj;
	}
	
	public static String getFieldNameFromMethod(String methodName, String sub){
		String fieldName = null;
		if(StringUtils.isNotBlank(methodName)){
			fieldName = StringUtils.substringAfter(methodName, sub);
			fieldName = StringUtils.uncapitalize(fieldName);
		}
		return fieldName;
	}
 
}
