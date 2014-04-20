package com.entity;

public class ShopEntity {

	String id ;
	String shopNameEn ; /* 商城名，一般是英文简称，如 jingdong, amazon */
	String shopNameCn ; /* 网购商城的中文名，显示在页面上给用户看，如 淘宝网 京东商城 */
	String domainName ; /* 商城的域名，如 jd.com */
	String shopAvailable ;
	String searchUrl ;
	String searchAvailable ;
	String charsetForUrl ;
	String charsetForContent ;
	
	

	//String MaxResultNumber = ""; /* 对商城执行一次查询后，可以设置最多返回多少个结果 */
	//String moneyStr = "";
	
	
	

	String index ; // 一般式域名首字母，商城拼音名首字母等，为了快速查找，比如 amazon亚马逊，index 是 a
						// 和y(拼音首字母)
	String logoImg ;
	String orderNumber;

	public String getIndex() {
		return index;
	}

	public ShopEntity setIndex(String index) {
		this.index = index;
		return this;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public ShopEntity setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}
	
	public String getShopAvailable() {
		return shopAvailable;
	}

	public ShopEntity setShopAvailable(String shopAvailable) {
		this.shopAvailable = shopAvailable;
		return this;
	}

	public String getSearchAvailable() {
		return searchAvailable;
	}

	public ShopEntity setSearchAvailable(String searchAvailable) {
		this.searchAvailable = searchAvailable;
		return this;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public ShopEntity setLogoImg(String logoImg) {
		this.logoImg = logoImg;
		return this;
	}

	public String getId() {
		return id;
	}

	public ShopEntity setId(String id) {
		this.id = id;
		return this;
	}

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

	 

}
