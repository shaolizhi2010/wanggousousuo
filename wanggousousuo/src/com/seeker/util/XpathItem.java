package com.seeker.util;

/**
 * 计算 Xpath 使用 
 */
public class XpathItem {
	
	String key;
 
	//xpath的得分，分数高低xpath 最终有效
	//一般 一次匹配计一分
	Double score = 0.0;
	Integer count = 0;
	
	//xpath
	String xpath;
	
 
//	public Integer getCount() {
//		return count;
//	}
//	public void setCount(Integer count) {
//		this.count = count;
//	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getXpath() {
		return xpath;
	}
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	//set int, and convert int to float
	public void setScore(Integer score) {
		this.score =   score.doubleValue();
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
