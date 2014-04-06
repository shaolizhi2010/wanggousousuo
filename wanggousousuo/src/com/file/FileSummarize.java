package com.file;

/**
 * 文章概述，文件名 时间 目录等等
 * 不包括文章内容
 * @author shaoliz
 *
 */
public class FileSummarize {
	String name;
	String path;
	String forder;//相对路径
	String createDate;
	String catalog;
	public String getForder() {
		return forder;
	}
	public void setForder(String forder) {
		this.forder = forder;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
}
