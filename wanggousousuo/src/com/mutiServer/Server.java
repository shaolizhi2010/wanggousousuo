package com.mutiServer;

public class Server {
	
	String name="";
	String url ="";
	Boolean useful=true;
	long lastUpdate=0;//上次轮询时间
	long delayTime=0;//延迟时间，数字越小，说明服务器越快
	
	String forShop="all";//专为某个shop服务的server,all表示全部，多个shop用|| 分隔
	Boolean isCacheServer = false;
	Boolean isCenterServer = false;
	Boolean isBlogServer = false;
	

	public long getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getUrl() {
		if(url==null){
			return "";
		}
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getUseful() {
		return useful;
	}
	public void setUseful(Boolean useful) {
		this.useful = useful;
	}
	
	
}
