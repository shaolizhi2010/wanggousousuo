package com.mutiServer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.connect.SimpleConnecter;
import com.env.StaticInfo;
import com.utils.C;
import com.utils.L;
import com.utils.MD5;

public class MutiServerUtil {
	/*
	 * 检查requeest server 的md5， 防止非放url 申请加入 
	 * md5 机密方法： 将url 和 系统自定的 token （C.md5_random_token）, 合并成一个字符串 
	 * 对这个字符串进行md5 加密 
	 * 如果request server的md5 符合要求，说明是合法server 
	*/
	public Boolean checkMD5(String url, String md5){
		return new MD5().check(url+C.md5_random_token, md5);
	}
	
	/*将一个新server 加入 app */
	public Boolean addServer(String url, String md5){
		
		//check md5
		if(!checkMD5(url, md5)){
			L.exception(this, "Add server failed, check md5 failed, server : " + url);
			return false;
		}
		
		//check delay time
		Long delayTime = checkUrlDelayTime(url);
		if( delayTime <0 ){//url 能否通过searchbean 取到结果，-1表示url不可用
			L.exception(this, "Add server failed, can not connect or get result from server : " + url);
			return false;
		}
		
		//init server
		Server server = StaticInfo.servers.get(url);
		if(server == null){ server = new Server(); }
		server.setUrl(url); server.setUseful(true);server.setLastUpdate(System.currentTimeMillis());
		server.setDelayTime(delayTime);
		
		//add server to app 
		StaticInfo.servers.put(url, server);
		L.log(this, "Add server succeed, Server : "+ url +" delay time is "+ delayTime);
		return true;
	}
	
	/* 取出可用server 列表 */
	public List<Server>  getUsefulUrlList(){
		List<Server> list = new LinkedList<Server>();
		for(Map.Entry<String,Server> entry : StaticInfo.servers.entrySet()){
			if(entry.getValue().getUseful()){	//如果可以，加入可用server列表
				list.add(entry.getValue());
			}
		}
		return list;
	}
	
	
	public Server getServerByUrl(String url){
		return StaticInfo.servers.get(url);
	}

	/*从可用server列表中 随机取一个 */
	public Server getServerByRandom(){
		
		List<Server> list = getUsefulUrlList();
		
		if(list == null || list.size()==0)	return new Server();//无server
		
		int i = new Random().nextInt( list.size());

		return list.get(i);
	}
	
	/* 取出 delaytime最小，即 最快的server */
	public Server getServerByDelayTime(){
		
		long minDelayTime = Long.MAX_VALUE;
		Server fastServer = new Server();
		
		for(Server server : getUsefulUrlList()){
			if(server.getDelayTime() <minDelayTime){ /* 如果这个server更快 */
				minDelayTime = server.getDelayTime(); /* 刷新最小时间 */
				fastServer = server; 
			}
		}
		
		return fastServer;
	}
	
	/* 遍历并检查server 列表，重新计算每个server的相应时间 并评估 是否可用 */
	public void checkAll(){
		for(Map.Entry<String,Server> entry : StaticInfo.servers.entrySet()){
			Server server = entry.getValue();
			long delayTime = checkUrlDelayTime(server.getUrl());
			if(delayTime < 0){//不可用
				server.setUseful(false);
				server.setLastUpdate(System.currentTimeMillis());
			}
			else{		//可用
				server.setUseful(true);
				server.setDelayTime(delayTime);
				server.setLastUpdate(System.currentTimeMillis());
			}
			L.log(this, "Check result : "+server.getUrl() + " - " + server.getDelayTime());
		}
		L.log(this, "checked server size : " + StaticInfo.servers.size());
	}
	
	/* 检查url 是否可以连接，并计算url返回结果的相应时间，一次时间判断server的快慢 */
	public Long checkUrlDelayTime(String url){
		String keywordEncode = "abc";
		try {
			keywordEncode = URLEncoder.encode("新款",
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			L.exception(this, e.getMessage());
		}
		//测试 url 是否能访问
		url = url+"searchBean?shopname=tmall&keyword="+keywordEncode;
		
		long start = System.currentTimeMillis();
		String responseString = SimpleConnecter.connect(url);
		long delayTime = System.currentTimeMillis()-start;
		
		//相应时间过长，即为不可用
		if(delayTime > C.max_delay_time){
			L.exception(this, "Check url available failed, respone time too long, is " + delayTime 
					+ " Server : "+ url);
			return -1L;
		}
		
		/* 不能成功连接 或返回结果长度不大于500，视为不可用 */
		if(responseString==null || responseString.length() <500 ){
			return -1L;
		}
		
		//可用 返回延迟时间
		return delayTime;
	}
	
	public Boolean logoffServer(String url, String md5){
		//check md5
		if(!checkMD5(url, md5)){
			L.exception(this, "Logoff server failed, check md5 failed, server : " + url + " - md5 - "+ md5);
			return false;
		}
		//check url exist
		if(StaticInfo.servers.get(url)==null){
			L.exception(this, "Logoff server failed, can not find server : " + url);
			return false;
		}
		//remove
		StaticInfo.servers.remove(url);
		L.log(this, "Log off server succeed, server : " + url);
		return true;
	}
	/* 从cache server上取 keyword 列表 */
	public String getKeywordsFromCacheServer(){
		return SimpleConnecter.connect("http://bijia365.net:8008/wanggousousuo/common?method=getCachesJson","utf-8");
	}
	
	public String getFiles(String url,String path, String type){
		try {
			url = url + "disk.jsp?method=getFiles&path="+URLEncoder.encode(path,"utf-8")+"&type="+type;
		} catch (UnsupportedEncodingException e) {
			L.exception(this, e.getMessage());
		}
		return SimpleConnecter.connect(url,"utf-8");
	}
	
	public String getFileContent(String url,String path){
		try {
			url = url + "disk.jsp?method=getFileContent&path="+URLEncoder.encode(path,"utf-8");
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return SimpleConnecter.connect(url,"utf-8");
	}
	
	/* 从cache server上取keyword缓存的内容 */
	public String getCacheContentFromCacheServer(String keyword, String shopname){
		String keywordEncode = keyword;
		try {
			keywordEncode = URLEncoder.encode(keyword,
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			L.exception(this, "Keyword encode exception " + e.getMessage());
		}
		return SimpleConnecter.connect("http://bijia365.net:8008/wanggousousuo/common?method=getcachecontent&keyword="+keywordEncode+"&shopname="+shopname,"utf-8");
	}
	

}
