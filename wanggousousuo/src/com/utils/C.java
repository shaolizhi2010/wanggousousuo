package com.utils;

public interface C {
	
	String userId = "userId";
	
	//------------------------------------------------------------------business value
	String seo_title = "网购搜索网,一键同时搜索 淘宝 天猫 京东 当当 亚马逊等数十家网购, 找网购，到网购搜索网";
	String seo_description = "网购搜索网 wanggousousuo.com，提供全网最简洁实用的网购搜索功能，一键同时搜索 淘宝 天猫 京东 当当 亚马逊等数十家网购, 找网购，到网购搜索网";
	String seo_keywords = "网购搜索网 找网购 网购搜索 哪能网购  哪能买到 哪能网购到";
	
	String default_search_value = "";
	String ceoContent = "<meta name='description' content=''/><meta name='keywords' content=''/>";
	String status_request = "已提交";
	String status_done = "已完成";
	String status_failed = "失败";
	//------------------------------------------------------------business var ---
	String keywordVar = "$keyword$";
	String md5_random_token = "this is a md5 random token";
	
	//------------------------------------------------------------ business control ---
	int max_products = 30;	//搜索时，每个商城的最大记录数
	int max_search_page= 3;
	//int prducCountPerPage = 6;	//每个shop 在页面显示的产品数量 
	boolean useCache = true;
	long cachePeriod = 24*60*60*1000;	//cache stale time 
	long cachePeriodForSearchEngine = 365*24*60*60*1000;	//365 days 
	long max_delay_time = 15*1000; //最大延迟，15秒，用于检测url是否可用
	int maxNewKeywordCount = 200;
	
	//------------------------------------------------------------------ keys -----
	
	String baseURL = "baseURL";
	String encoding = "encoding";
	String productListKey= "productListKey";
	
	String pNameKey="pNameKey";
	String pUrlKey="pUrlKey";
	String pImgKey="pImgKey";
	String pPricekey="pPricekey";
	String pPriceType = "pPriceType";
	String pcommentKey="pcommentKey";
	
	String rulesDir = "rules/";
	String blogDir = "blog/";
	String promotionDir = "promotion/";
	
	String autoSearch = "autoSearch";
	String loglevel = "loglevel";
	
	//---------------------------------------------------------------- system -----
	int BUFFER_SIZE = 1024;  
	String file_separator = "\\";
	
	//--------------------------------------------------------------- path ---
	String cachePath = "cache/";
	String userPath = "user/";
	String viewProductHistoryPath = "user/view-product-history/";
	
	//-----------------------------------------------------------------infomation
	String e_nodata = "e_nodata - 没有找到哦亲";
	//String msg_defaulSearch = "搜索\"新款\"试试，看能找到什么~";
}
