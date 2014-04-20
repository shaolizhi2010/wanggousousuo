package com.env;

import java.util.HashMap;
import java.util.Map;

import com.entity.ShopEntity;
import com.utils.C;
import com.utils.ShopNames;

public class StaticInfo {
	
	/*
	 * 这个map中，存放的是网购商城的信息列表，
	 * 信息包括，域名，搜索页面url，商城中文名 等等，
	 * 每一个商城信息，作为map中的一个 elment。
	 * */
	private static Map<String, ShopEntity> shops = new HashMap<String, ShopEntity>();
	
	public static void init(){
		if(shops.size()==0){
			//淘宝
			shops.put(ShopNames.taobao.toString(), new ShopEntity().
					setShopNameEn(ShopNames.taobao.toString())
					.setDomainName("taobao.com")
					.setShopNameCn("淘宝网")
					.setSearchUrl("http://s.taobao.com/search?q=")
					
					);
			//天猫
			shops.put(ShopNames.tmall.toString(), new ShopEntity().
					setShopNameEn(ShopNames.tmall.toString())
					.setDomainName("tmall.com")
					.setShopNameCn("天猫商城")
					.setSearchUrl("http://list.tmall.com/search_product.htm?q=")
					);
			//京东
			shops.put(ShopNames.jingdong.toString(), new ShopEntity().
					setShopNameEn(ShopNames.jingdong.toString())
					.setDomainName("jd.com")
					.setShopNameCn("京东商城")
					.setSearchUrl("http://search.jd.com/Search?keyword=")
					);
			//当当
			shops.put(ShopNames.dangdang.toString(), new ShopEntity().
					setShopNameEn(ShopNames.dangdang.toString())
					.setDomainName("dangdang.com")
					.setShopNameCn("当当网")
					.setSearchUrl("http://search.dangdang.com/?key=")
					);
			//亚马逊
			shops.put(ShopNames.amazon.toString(), new ShopEntity().
					setShopNameEn(ShopNames.amazon.toString())
					.setDomainName("amazon.cn")
					.setShopNameCn("亚马逊中国")
					.setSearchUrl("http://www.amazon.cn/s/ref=nb_sb_noss_1?__mk_zh_CN=亚马逊网站&url=search-alias%3Daps&field-keywords=")
					);
			
			
			//蔚蓝书店
//			shops.put(ShopNames.wl.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.wl.toString())
//					.setDomainName("wl.cn")
//					.setShopNameCn("蔚蓝网")
//					.setSearchUrl("http://search.wl.cn/search.aspx?index=1&q=")
//					
//					);
			//qq易迅
//			shops.put(ShopNames.yixun.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.yixun.toString())
//					.setDomainName("yixun.com")
//					.setShopNameCn("易迅网")
//					.setSearchUrl("http://searchex.yixun.com/html?charset=utf-8&key=")
//					
//					);	
			//凡客
			shops.put(ShopNames.fanke.toString(), new ShopEntity().
					setShopNameEn(ShopNames.fanke.toString())
					.setDomainName("vancl.com")
					.setShopNameCn("凡客诚品")
					.setSearchUrl("http://s.vancl.com/search?k=")
					
					);
			//okbuy TODO 价格延迟加载
//			shops.put(ShopNames.okbuy.toString(), new ShopInfo().
//					setShopNameEn(ShopNames.okbuy.toString())
//					.setDomainName("okbuy.com")
//					.setShopNameCn("好乐买")
//					.setSearchUrl("http://www.okbuy.com/search?top_key=")
//					.setActivityUrl("http://www.okbuy.com")
//					
//					
//					);
			//newegg
			shops.put(ShopNames.newegg.toString(), new ShopEntity().
					setShopNameEn(ShopNames.newegg.toString())
					.setDomainName("newegg.com.cn")
					.setShopNameCn("新蛋中国")
					.setSearchUrl("http://www.newegg.com.cn/Search.aspx?keyword=")
					
					);	
			//handuyishe
			shops.put(ShopNames.handuyishe.toString(), new ShopEntity().
					setShopNameEn(ShopNames.handuyishe.toString())
					.setDomainName("handuyishe.com")
					.setShopNameCn("韩都衣舍")
					.setSearchUrl("http://www.handuyishe.com/search.php?keywords=")
					);	
			//coo8
			shops.put(ShopNames.coo8.toString(), new ShopEntity().
					setShopNameEn(ShopNames.coo8.toString())
					.setDomainName("coo8.com")
					.setShopNameCn("库巴网")
					.setSearchUrl("http://www.coo8.com/coo8/ec/atgsearch/coo8SearchResults.jsp?question=")
					
					);	
			//亲亲宝贝
			shops.put(ShopNames.qinqinbaby.toString(), new ShopEntity().
					setShopNameEn(ShopNames.qinqinbaby.toString())
					.setDomainName("qinqinbaby.com")
					.setShopNameCn("亲亲宝贝")
					.setSearchUrl("http://www.qinqinbaby.com/product/search.aspx?keyword=")
					
					);
			//一号店
			shops.put(ShopNames.yihaodian.toString(), new ShopEntity().
					setShopNameEn(ShopNames.yihaodian.toString())
					.setDomainName("yihaodian.com")
					.setShopNameCn("一号店")
					.setSearchUrl("http://search.yihaodian.com/s2/c0-0/k")
					
					
					);
			//麦考林
//			shops.put(ShopNames.m18.toString(), new ShopInfo().
//					setShopNameEn(ShopNames.m18.toString())
//					.setDomainName("m18.com")
//					.setShopNameCn("麦考林")
//					.setSearchUrl("http://list.m18.com/gmkt.inc/M18/List/Search.aspx?keyword=")
//					
//					
//					);
			//聚美优品
			shops.put(ShopNames.jumei.toString(), new ShopEntity().
					setShopNameEn(ShopNames.jumei.toString())
					.setDomainName("jumei.com")
					.setShopNameCn("聚美优品")
					.setSearchUrl("http://search.jumei.com/?filter=0-0-0-0-11-1&search=")
					
					
					);
			//v+
			shops.put(ShopNames.vjia.toString(), new ShopEntity().
					setShopNameEn(ShopNames.vjia.toString())
					.setDomainName("vjia.com")
					.setShopNameCn("V+")
					.setSearchUrl("http://s.vjia.com/?k=")
					
					
					);	
			//乐蜂
			shops.put(ShopNames.lefeng.toString(), new ShopEntity().
					setShopNameEn(ShopNames.lefeng.toString())
					.setDomainName("lefeng.com")
					.setShopNameCn("乐蜂网")
					.setSearchUrl("http://search.lefeng.com/search/search?key=")
					
					
					);
			//国美
			shops.put(ShopNames.gome.toString(), new ShopEntity().
					setShopNameEn(ShopNames.gome.toString())
					.setDomainName("gome.com.cn")
					.setShopNameCn("国美在线")
					.setSearchUrl("http://www.gome.com.cn/ec/homeus/n/search/gomeSearchResults.jsp?question=")
					
					
					);	
//			//苏宁易购 TODO  价格是图片
//			shops.put(ShopNames.suning.toString(), new ShopInfo().
//					setShopNameEn(ShopNames.suning.toString())
//					.setDomainName("suning.com")
//					.setShopNameCn("苏宁易购")
//					.setSearchUrl("http://search.suning.com/emall/search.do?keyword=")
//					
//					
//					);	
			//麦包包
//			shops.put(ShopNames.mbaobao.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.mbaobao.toString())
//					.setDomainName("mbaobao.com")
//					.setShopNameCn("麦包包")
//					.setSearchUrl("http://search.mbaobao.com/search/search?o=1&q=")
//					
//					
//					);	
			//银泰网
			shops.put(ShopNames.yintai.toString(), new ShopEntity().
					setShopNameEn(ShopNames.yintai.toString())
					.setDomainName("yintai.com")
					.setShopNameCn("银泰网")
					.setSearchUrl("http://www.yintai.com/product/search.aspx?keyword=")
					
					
					);	
			//走秀网
//			shops.put(ShopNames.xiu.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.xiu.toString())
//					.setDomainName("xiu.com")
//					.setShopNameCn("走秀网")
//					.setSearchUrl("http://search.xiu.com/s?kw=")
//					
//					
//					);	
			//校园时尚
//			shops.put(ShopNames.xyss.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.xyss.toString())
//					.setDomainName("xyss.org")
//					.setShopNameCn("校园时尚")
//					.setSearchUrl("http://www.xyss.org/?gallery--n,"+C.keywordVar+"-grid.html")
//					
//					
//					);	
			//梦芭莎
//			shops.put(ShopNames.moonbasa.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.moonbasa.toString())
//					.setDomainName("moonbasa.com")
//					.setShopNameCn("梦芭莎")
//					.setSearchUrl("http://s.moonbasa.com/?enc=utf-8&keyword=")
//					
//					
//					);	
			//拍鞋网
//			shops.put(ShopNames.paixie.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.paixie.toString())
//					.setDomainName("paixie.net")
//					.setShopNameCn("拍鞋网")
//					.setSearchUrl("http://www.paixie.net/shoes/list/______"+C.keywordVar+"_-1.html")
//					 
//					
//					);
			//优购网
//			shops.put(ShopNames.yougou.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.yougou.toString())
//					.setDomainName("yougou.com")
//					.setShopNameCn("优购网")
//					.setSearchUrl("http://www.yougou.com/k-"+C.keywordVar+".html")
//					 
//					
//					);		
			//奥特莱斯
//			shops.put(ShopNames.outlets.toString(), new ShopEntity().
//					setShopNameEn(ShopNames.outlets.toString())
//					.setDomainName("www.outlets365.com")
//					.setShopNameCn("奥特莱斯")
//					.setSearchUrl("http://www.outlets365.com/Search-"+C.keywordVar+"-0-0-0-3-1.html")
//					 
//					
//					);	
			
		}
	}
	
	public static Map<String, ShopEntity> getShops(){
		if(shops.size()==0){
			init();
		}
		return shops;
	}
	
	public static ShopEntity getShopbyName(String shopname){
		ShopEntity shop = getShops().get(shopname);
		if(shop == null){
			shop = new ShopEntity();
		}
		return shop;
	}
	
//	public static String getShopNameCN(String shopName){
//		return getShopInfoBySimpleName(shopName).getCnName();
//	}
//	
//	public static String getShopSearchUrl(String shopName){
//		return getShopInfoBySimpleName(shopName).getSearchUrl();
//	}
//	
}
