package com.entity;

public class BlogEntity {
	String id;
	String title;
	String content;
	public String getId() {
		return id;
	}
	public BlogEntity setId(String id) {
		this.id = id;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public BlogEntity setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getContent() {
		return content;
	}
	public BlogEntity setContent(String content) {
		this.content = content;
		return this;
	}
	
}
