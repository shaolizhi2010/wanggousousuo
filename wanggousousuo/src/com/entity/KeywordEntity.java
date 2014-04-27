package com.entity;

import org.apache.commons.lang3.StringUtils;

public class KeywordEntity {
	
	String keyword;
	String times;	//被搜索次数
	String lasttime;	//最后更新时间
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public void timesPlus(){
		if(StringUtils.isBlank(times)){
			times = "1";
			return;
		}
		try {
			int timesInt = Integer.parseInt(times);
			timesInt++;
			times = timesInt+"";
		} catch (Exception e) {
			return;
		}
		
	}
	
	
	
	String id;
	String orderNumber;
	String useful;// true false;
	String description;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getUseful() {
		return useful;
	}
	public void setUseful(String useful) {
		this.useful = useful;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
