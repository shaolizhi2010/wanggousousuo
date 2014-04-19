package com.entity;

public class BaseEntity {
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
