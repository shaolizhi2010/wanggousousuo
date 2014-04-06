package com.digger.vo;

import org.apache.commons.lang3.StringUtils;

public class Product {
	String name;
	String url;
	String price;
	String imgUrl;
	String commentCount;
	String commentUrl;
	String source;
	
	public String getName() {
		return name;
	}
	public Product setName(String name) {
		this.name = name;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public Product setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getPrice() {
		return price;
	}
	public Product setPrice(String price) {
		this.price = price;
		return this;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public Product setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public Product setCommentCount(String commentCount) {
		this.commentCount = commentCount;
		return this;
	}
	public String getCommentUrl() {
		return commentUrl;
	}
	public Product setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
		return this;
	}
	public String getSource() {
		return source;
	}
	public Product setSource(String source) {
		this.source = source;
		return this;
	}
	
	public boolean useful(){
		
		//缺少 商品标题 链接 或价格任意一项，即为无用信息
		if(StringUtils.isBlank(name) || StringUtils.isBlank(url) || StringUtils.isBlank(price) || StringUtils.isBlank(imgUrl)){
			return false;	
		}
		return true;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("name : ").append(this.name).append(System.getProperty("line.separator"));
		buffer.append("url : ").append(this.url).append(System.getProperty("line.separator"));
		buffer.append("price : ").append(this.price).append(System.getProperty("line.separator"));
		buffer.append("imgUrl : ").append(this.imgUrl).append(System.getProperty("line.separator"));
		buffer.append("commentCount : ").append(this.commentCount).append(System.getProperty("line.separator"));
		buffer.append("commentUrl : ").append(this.commentUrl).append(System.getProperty("line.separator"));
		buffer.append("source : ").append(this.source).append(System.getProperty("line.separator"));
		
		return buffer.toString();
	}
	
}
