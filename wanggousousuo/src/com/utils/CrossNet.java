package com.utils;

import java.util.ArrayList;
import java.util.List;

import com.connect.SimpleConnecter;

public class CrossNet {
	
	public List<String> urlList = new ArrayList<String>();
	
	

	public String getjson(String url){
		return SimpleConnecter.connect(getBaseUrl()+url,"utf-8");
	}
	
	public String getBaseUrl(){
		//TODO 动态获取
		return "http://bijia365.net:8008/wanggousousuo/";
//		return "http://localhost/wanggousousuo/";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = new CrossNet().getjson("common?method=getBlogsJson");
		System.out.println(s);
	}

}
