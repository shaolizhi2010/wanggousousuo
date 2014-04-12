package com.digger;

import org.jdom2.Element;

import com.entity.CommodityEntity;

public abstract class WebBaseDigger {
	
	public String requestCharset = "";
	public String responeCharset = "";

	protected String shopName = "";
	//protected String baseUrl = "";
	//protected String searchUrl = "";
	//public String keyword = "";
	public String unEncodeKeyword;

	protected String listKey = "";

	protected String nameKey = ""; // product name
	protected String urlKey = ""; // 商品链接
	protected String urlAttr = "href"; // 取商品链接所需属性，默认href

	protected String imgUrlKey = ".//img[0]"; // product
	protected String imgUrlAttr = "src"; // 取图片链接所需属性，默认src 可能是 lazy load
											// img
	protected String pricekey = ""; // price key
	protected String commentCountKey = ""; // comment
	protected String commentUrlkey = ""; // comment count
	protected String commentUrlAttr = "href"; // comment url

 
	
	public WebBaseDigger(){
	}
	
	public abstract void customPick(CommodityEntity product,Element xml);
	
	
}
