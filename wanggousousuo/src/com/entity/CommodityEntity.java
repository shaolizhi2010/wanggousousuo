package com.entity;

import org.apache.commons.lang3.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.utils.U;

public class CommodityEntity {
	
	String id;
	String name;
	String url;
	String price;
	String imgUrl;
	String commentCount;
	String commentUrl;
	String source;
	String keyword;
	
	public String getId() {
		return id;
	}
	public CommodityEntity setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public CommodityEntity setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}
	public String getName() {
		return name;
	}
	public CommodityEntity setName(String name) {
		this.name = name;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public CommodityEntity setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getPrice() {
		return price;
	}
	public CommodityEntity setPrice(String price) {
		this.price = price;
		return this;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public CommodityEntity setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public CommodityEntity setCommentCount(String commentCount) {
		this.commentCount = commentCount;
		return this;
	}
	public String getCommentUrl() {
		return commentUrl;
	}
	public CommodityEntity setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
		return this;
	}
	public String getSource() {
		return source;
	}
	public CommodityEntity setSource(String source) {
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

	
}
