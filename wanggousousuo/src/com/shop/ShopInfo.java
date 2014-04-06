package com.shop;

public class ShopInfo {
	
	protected String shopName = "";	/* 商城名，一般是英文简称，如	jingdong, amazon */
	protected String domainName = "";	/* 商城的域名，如 jd.com */
	protected String searchUrl = "";
	protected String activityUrl = "";	/* 抓取商城促销活动信息使用，活动信息页面 一般是主页 */
	protected String cnName = "";	/* 网购商城的中文名，显示在页面上给用户看，如 淘宝网 京东商城 */
	protected String MaxResultNumber = "";	/* 对商城执行一次查询后，可以设置最多返回多少个结果 */
	protected String moneyStr="";
 
	public String getMoneyStr() {
		return moneyStr;
	}
	public String getActivityUrl() {
		return activityUrl;
	}
	public ShopInfo setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
		return this;
	}
	public ShopInfo setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
		return this;
	}
	public String getShopName() {
		return shopName;
	}
	public ShopInfo setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}
	public String getDomainName() {
		return domainName;
	}
	public ShopInfo setDomainName(String domainName) {
		this.domainName = domainName;
		return this;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public ShopInfo setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
		return this;
	}
	public String getCnName() {
		return cnName;
	}
	public ShopInfo setCnName(String cnName) {
		this.cnName = cnName;
		return this;
	}
	public String getMaxResultNumber() {
		return MaxResultNumber;
	}
	public ShopInfo setMaxResultNumber(String maxResultNumber) {
		MaxResultNumber = maxResultNumber;
		return this;
	}

	
}
