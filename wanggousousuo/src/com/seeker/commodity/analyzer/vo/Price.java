package com.seeker.commodity.analyzer.vo;

import com.base.vo.ScoreVO;


public class Price extends ScoreVO {
	
	Double price = 0.0;
	String xpath = "";
 
	public Price(){
	}
	
	public Price(String xpath, Double price){
		this.xpath = xpath;
		this.price = price;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getXpath() {
		return xpath;
	}
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	
}
