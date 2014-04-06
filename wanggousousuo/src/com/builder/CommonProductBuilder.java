package com.builder;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bean.logic.Cache;
import com.digger.CommonCommodityDigger;
import com.digger.Digger;
import com.digger.JingdongDigger;
import com.digger.vo.Product;
import com.google.gson.Gson;
import com.utils.C;
import com.utils.L;
import com.utils.ShopNames;

/**
 * 把从商城抓来的数据进行加工处理
 */
public class CommonProductBuilder {
	
	public String  buildFromDigger(String shopStr, String keyword,int pageNum,String realpath){
			
		try {
			if(StringUtils.isBlank(shopStr)){
				L.exception(this, "digger name is null ");
				return "";
			}
			shopStr = StringUtils.remove(shopStr, "_");
			shopStr = StringUtils.remove(shopStr, " ");
			Digger digger = null;
			
	
			if(ShopNames.jingdong.toString().equalsIgnoreCase(shopStr)){
				digger = new JingdongDigger(shopStr,realpath);
				List<Product> products = digger.digAll(keyword);
				return new Gson().toJson(products);		
			}
			else{
				//TODO build url
				digger = new CommonCommodityDigger(shopStr,realpath);
				List<Product> products = digger.digAll(keyword);
				new UrlBuilder().buildUrlsForPage(shopStr, products);
				return new Gson().toJson(products);		
			}
			

//			else {	//common digger
//				L.exception(this, "can not find digger for --- " + shopStr);
//			}
//			List<Product> products = (ArrayList<Product>)digger.dig(pageNum); 
//			new UrlBuilder().buildUrlsForPage(shopStr, products);//build product comment img urls 
//			return new Gson().toJson(products);
			
			//return new Gson().toJson( digger.getCookedProductMap().get("productList") );//TODO
			
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		
		return "";
		
	}
	
	public String build(String shopname, String keyword, int pageNum, String realpath, boolean forSearchEngine){
		String resultJson = null;
		
//		if(C.useCache){
//			resultJson = new Cache().get(keyword, shopname, realpath, forSearchEngine);
//			 if( StringUtils.isNotBlank(resultJson) ){ //have cache){
//				return resultJson;
//			}
//		}
		//cache is not in use or no data in cache
		
		resultJson =  this.buildFromDigger(shopname, keyword,pageNum,realpath);
		if(C.useCache){
//			new Cache().save(keyword, shopname, resultJson, realpath);
			new Cache().save(keyword, shopname, resultJson);
		}
		return resultJson;
	}
}
