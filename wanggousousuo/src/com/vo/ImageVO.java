package com.vo;

public class ImageVO {
	int width;
	int height;
	int size;
	int widthInPage;
	int heightInPage; //如网页中用width 和heigt 限制大小，
	int sizeInPage;
	String sre;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidthInPage() {
		return widthInPage;
	}
	public void setWidthInPage(int widthInPage) {
		this.widthInPage = widthInPage;
	}
	public int getHeightInPage() {
		return heightInPage;
	}
	public void setHeightInPage(int heightInPage) {
		this.heightInPage = heightInPage;
	}
	public String getSre() {
		return sre;
	}
	public void setSre(String sre) {
		this.sre = sre;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSizeInPage() {
		return sizeInPage;
	}
	public void setSizeInPage(int sizeInPage) {
		this.sizeInPage = sizeInPage;
	}
	
	
}
