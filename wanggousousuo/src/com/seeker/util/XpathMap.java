package com.seeker.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

/**
 * 一组xpath，将备选的xpath 放入一个map，
 * 通过计算，取出最终有效的xpath
 * 
 * 
 * @author shaoliz
 *
 */
public class XpathMap {
	
	public Map<String, XpathItem> xpathMap = new HashMap<String, XpathItem>();
	
	public XpathItem get(String key){
		if(xpathMap.get(key)==null){
			return new XpathItem();
		}
		return xpathMap.get(key);
	}
	
	private XpathItem put(String key, String xpath){
		if(xpathMap.containsKey(key)){//已有，计数+1
			XpathItem item = xpathMap.get(key);
			//item.setScore(item.getScore()+1);
			return item;
		}
		else{ //新的
			XpathItem item = new XpathItem();
			item.setKey(key);
			//item.setScore(1);
			item.setXpath(xpath);
			xpathMap.put(key, item);
			return item;
		}
		
	}
	
	/*加入xpath的同时，设置xpath 计数*/
	public void put(String key, String xpath, Integer addscore){
		put(key, xpath, addscore.doubleValue());
	}
	
	/*加入xpath的同时，设置xpath 计数*/
	public void put(String key, String xpath, Double addscore){
		XpathItem item = put(key, xpath) ;
		
		item.setScore(item.getScore()+addscore);
		//xpathMap.put(key, item);
	}
	
//	//取出计数（匹配次数）最多的xpath
//	public XpathItem getResultWithMaxCount(){
//		
//		int count = 0;
//		XpathItem resultContent =  new XpathItem();
//		for(Entry<String, XpathItem> entry : xpathMap.entrySet()){
//			//System.out.println(entry.getKey() + " - " + entry.getValue());
//			if(entry.getValue().getCount() > count){//返回count最大的一个
//				count = entry.getValue().getCount();
//				resultContent = entry.getValue();
//			}
//		}
//		return resultContent;
//	}
	
	//取出计数（匹配次数）最多的xpath
	public XpathItem getResultWithMaxScore(){
		
		Double score = 0.0;
		XpathItem resultContent =  new XpathItem();
		for(Entry<String, XpathItem> entry : xpathMap.entrySet()){
			//System.out.println(entry.getKey() + " - " + entry.getValue());
			if(entry.getValue().getScore() > score){//返回score最大的一个
				score = entry.getValue().getScore();
				resultContent = entry.getValue();
			}
		}
		return resultContent;
	}
	
//	public XpathItem getMinResult1(){
//		
//		int count = Integer.MAX_VALUE;
//		XpathItem resultContent =  new XpathItem();
//		for(Entry<String, XpathItem> entry : xpathMap.entrySet()){
//			//System.out.println(entry.getKey() + " - " + entry.getValue());
//			if(entry.getValue().getScore() < count){//返回count最大的一个
//				count = entry.getValue().getScore();
//				resultContent = entry.getValue();
//			}
//		}
//		return resultContent;
//	}
	
	public int size(){
		return xpathMap.size();
	}
	
	public String toString(){
		String s = "";
		for(Entry<String, XpathItem> e: xpathMap.entrySet()){
			s = s + (e.getValue().getScore() +" - "+e.getValue().getXpath()) +System.getProperty("line.separator");
		}
		return s;
//		return new Gson().toJson(this);
	}
	
}
