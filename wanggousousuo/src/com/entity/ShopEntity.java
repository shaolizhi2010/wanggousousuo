package com.entity;

public class ShopEntity {
	
	protected String shopNameEn = "";	/* 商城名，一般是英文简称，如	jingdong, amazon */
	protected String shopNameCn = "";	/* 网购商城的中文名，显示在页面上给用户看，如 淘宝网 京东商城 */
	protected String domainName = "";	/* 商城的域名，如 jd.com */
	protected String searchUrl = "";
	protected String charsetForUrl = "UTF-8";	 
	protected String charsetForContent = "GBK";	 
	
	protected String MaxResultNumber = "";	/* 对商城执行一次查询后，可以设置最多返回多少个结果 */
	protected String moneyStr="";
	
	public String getShopNameEn() {
		return shopNameEn;
	}
	public ShopEntity setShopNameEn(String shopNameEn) {
		this.shopNameEn = shopNameEn;
		return this;
	}
	public String getShopNameCn() {
		return shopNameCn;
	}
	public ShopEntity setShopNameCn(String shopNameCn) {
		this.shopNameCn = shopNameCn;
		return this;
	}
	public String getDomainName() {
		return domainName;
	}
	public ShopEntity setDomainName(String domainName) {
		this.domainName = domainName;
		return this;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public ShopEntity setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
		return this;
	}
	public String getCharsetForUrl() {
		return charsetForUrl;
	}
	public ShopEntity setCharsetForUrl(String charsetForUrl) {
		this.charsetForUrl = charsetForUrl;
		return this;
	}
	public String getCharsetForContent() {
		return charsetForContent;
	}
	public ShopEntity setCharsetForContent(String charsetForContent) {
		this.charsetForContent = charsetForContent;
		return this;
	}
	public String getMaxResultNumber() {
		return MaxResultNumber;
	}
	public ShopEntity setMaxResultNumber(String maxResultNumber) {
		MaxResultNumber = maxResultNumber;
		return this;
	}
	public String getMoneyStr() {
		return moneyStr;
	}
	public ShopEntity setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
		return this;
	}
	 
	
	
}
