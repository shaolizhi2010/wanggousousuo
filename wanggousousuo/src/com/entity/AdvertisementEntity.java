package com.entity;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.GsonBuilder;

public class AdvertisementEntity implements Comparable<AdvertisementEntity>{
	
	String id;
	String url;
	String imgUrl;
	String name;
	String description;
	String imgsize;
	
 
	public String getImgsize() {
		return imgsize;
	}
	public void setImgsize(String imgsize) {
		this.imgsize = imgsize;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
//		return "AdvertisementEntity [url=" + url + ", imgUrl=" + imgUrl
//				+ ", name=" + name + ", description=" + description + "]";
	
		return toJson();
	}
 
	public String toJson(){
		return new GsonBuilder().setPrettyPrinting()
				.disableHtmlEscaping()
				.create().toJson(this);
	}
	@Override
	public int compareTo(AdvertisementEntity that) {
		
		int thisImgSize = 0;
		if( !StringUtils.isBlank(this.imgsize)){
			thisImgSize = Integer.parseInt(this.imgsize);
		}
		
		int thatImgSize = 0;
		if( !StringUtils.isBlank(that.imgsize)){
			thatImgSize = Integer.parseInt(that.imgsize);
		}
		
		return thatImgSize - thisImgSize;
	}
 
	

	
}
