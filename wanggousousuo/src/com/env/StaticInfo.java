package com.env;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.mutiServer.Server;
import com.shop.ShopInfo;
import com.utils.C;
import com.utils.ShopNames;

public class StaticInfo {
	
	/*
	 * 这个map中，存放的是网购商城的信息列表，
	 * 信息包括，域名，搜索页面url，商城中文名 等等，
	 * 每一个商城信息，作为map中的一个 elment。
	 * */
	private static Map<String, ShopInfo> shops = new HashMap<String, ShopInfo>();
	
	/*
	 * 这个map中，存放的是可用的 work server 的列表
	 * 每个work server， 都是一个digger server，也可能是一个cache server，
	 * 用户的请求，会分派到不同的server 执行，
	 * 以分摊服务器的压力，提高响应速度。
	 * work server 有三种状态，1可用 2暂不可用 3销毁，销毁的服务器的url会被从server map中删去 
	 * work server 的url 作为map 的key,server 的具体信息类，作为map 的value
	*/
	public static Map<String, Server> servers = new HashMap<String, Server>();
	
	
	public static void init(){
		if(shops.size()==0){
			//淘宝
			shops.put(ShopNames.taobao.toString(), new ShopInfo().
					setShopName(ShopNames.taobao.toString())
					.setDomainName("taobao.com")
					.setCnName("淘宝网")
					.setSearchUrl("http://s.taobao.com/search?q=")
					.setActivityUrl("http://www.taobao.com/index_global.php")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//天猫
			shops.put(ShopNames.tmall.toString(), new ShopInfo().
					setShopName(ShopNames.tmall.toString())
					.setDomainName("tmall.com")
					.setCnName("天猫商城")
					.setSearchUrl("http://list.tmall.com/search_product.htm?q=")
					.setActivityUrl("http://www.tmall.com/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//京东
			shops.put(ShopNames.jingdong.toString(), new ShopInfo().
					setShopName(ShopNames.jingdong.toString())
					.setDomainName("jd.com")
					.setCnName("京东商城")
					.setSearchUrl("http://search.jd.com/Search?keyword=")
					.setActivityUrl("http://www.jd.com/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//当当
			shops.put(ShopNames.dangdang.toString(), new ShopInfo().
					setShopName(ShopNames.dangdang.toString())
					.setDomainName("dangdang.com")
					.setCnName("当当网")
					.setSearchUrl("http://search.dangdang.com/?key=")
					.setActivityUrl("http://www.dangdang.com/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//亚马逊
			shops.put(ShopNames.amazon.toString(), new ShopInfo().
					setShopName(ShopNames.amazon.toString())
					.setDomainName("amazon.cn")
					.setCnName("亚马逊中国")
					.setSearchUrl("http://www.amazon.cn/s/ref=nb_sb_noss_1?__mk_zh_CN=亚马逊网站&url=search-alias%3Daps&field-keywords=")
					.setActivityUrl("http://www.amazon.cn/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//蔚蓝书店
			shops.put(ShopNames.wl.toString(), new ShopInfo().
					setShopName(ShopNames.wl.toString())
					.setDomainName("wl.cn")
					.setCnName("蔚蓝网")
					.setSearchUrl("http://search.wl.cn/search.aspx?index=1&q=")
					.setActivityUrl("http://www.wl.cn/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//qq易迅
			shops.put(ShopNames.yixun.toString(), new ShopInfo().
					setShopName(ShopNames.yixun.toString())
					.setDomainName("yixun.com")
					.setCnName("易迅网")
					.setSearchUrl("http://searchex.yixun.com/html?charset=utf-8&key=")
					.setActivityUrl("http://www.yixun.com/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//凡客
			shops.put(ShopNames.fanke.toString(), new ShopInfo().
					setShopName(ShopNames.fanke.toString())
					.setDomainName("vancl.com")
					.setCnName("凡客诚品")
					.setSearchUrl("http://s.vancl.com/search?k=")
					.setActivityUrl("http://www.vancl.com/")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//okbuy TODO 价格延迟加载
//			shops.put(ShopNames.okbuy.toString(), new ShopInfo().
//					setShopName(ShopNames.okbuy.toString())
//					.setDomainName("okbuy.com")
//					.setCnName("好乐买")
//					.setSearchUrl("http://www.okbuy.com/search?top_key=")
//					.setActivityUrl("http://www.okbuy.com")
//					.setMoneyStr("")
//					.setMaxResultNumber("40")
//					);
			//newegg
			shops.put(ShopNames.newegg.toString(), new ShopInfo().
					setShopName(ShopNames.newegg.toString())
					.setDomainName("newegg.com.cn")
					.setCnName("新蛋中国")
					.setSearchUrl("http://www.newegg.com.cn/Search.aspx?keyword=")
					.setActivityUrl("http://www.newegg.com.cn")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//handuyishe
			shops.put(ShopNames.handuyishe.toString(), new ShopInfo().
					setShopName(ShopNames.handuyishe.toString())
					.setDomainName("handuyishe.com")
					.setCnName("韩都衣舍")
					.setSearchUrl("http://www.handuyishe.com/search.php?keywords=")
					.setActivityUrl("http://www.handuyishe.com")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//coo8
			shops.put(ShopNames.coo8.toString(), new ShopInfo().
					setShopName(ShopNames.coo8.toString())
					.setDomainName("coo8.com")
					.setCnName("库巴网")
					.setSearchUrl("http://www.coo8.com/coo8/ec/atgsearch/coo8SearchResults.jsp?question=")
					.setActivityUrl("http://www.coo8.com")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//亲亲宝贝
			shops.put(ShopNames.qinqinbaby.toString(), new ShopInfo().
					setShopName(ShopNames.qinqinbaby.toString())
					.setDomainName("qinqinbaby.com")
					.setCnName("亲亲宝贝")
					.setSearchUrl("http://www.qinqinbaby.com/product/search.aspx?keyword=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//一号店
			shops.put(ShopNames.yihaodian.toString(), new ShopInfo().
					setShopName(ShopNames.yihaodian.toString())
					.setDomainName("yihaodian.com")
					.setCnName("一号店")
					.setSearchUrl("http://search.yihaodian.com/s2/c0-0/k")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//麦考林
//			shops.put(ShopNames.m18.toString(), new ShopInfo().
//					setShopName(ShopNames.m18.toString())
//					.setDomainName("m18.com")
//					.setCnName("麦考林")
//					.setSearchUrl("http://list.m18.com/gmkt.inc/M18/List/Search.aspx?keyword=")
//					.setMoneyStr("")
//					.setMaxResultNumber("40")
//					);
			//聚美优品
			shops.put(ShopNames.jumei.toString(), new ShopInfo().
					setShopName(ShopNames.jumei.toString())
					.setDomainName("jumei.com")
					.setCnName("聚美优品")
					.setSearchUrl("http://search.jumei.com/?filter=0-0-0-0-11-1&search=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//v+
			shops.put(ShopNames.vjia.toString(), new ShopInfo().
					setShopName(ShopNames.vjia.toString())
					.setDomainName("vjia.com")
					.setCnName("V+")
					.setSearchUrl("http://s.vjia.com/?k=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//乐蜂
			shops.put(ShopNames.lefeng.toString(), new ShopInfo().
					setShopName(ShopNames.lefeng.toString())
					.setDomainName("lefeng.com")
					.setCnName("乐蜂网")
					.setSearchUrl("http://search.lefeng.com/search/search?key=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);
			//国美
			shops.put(ShopNames.gome.toString(), new ShopInfo().
					setShopName(ShopNames.gome.toString())
					.setDomainName("gome.com.cn")
					.setCnName("国美在线")
					.setSearchUrl("http://www.gome.com.cn/ec/homeus/n/search/gomeSearchResults.jsp?question=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
//			//苏宁易购 TODO  价格是图片
//			shops.put(ShopNames.suning.toString(), new ShopInfo().
//					setShopName(ShopNames.suning.toString())
//					.setDomainName("suning.com")
//					.setCnName("苏宁易购")
//					.setSearchUrl("http://search.suning.com/emall/search.do?keyword=")
//					.setMoneyStr("")
//					.setMaxResultNumber("40")
//					);	
			//麦包包
			shops.put(ShopNames.mbaobao.toString(), new ShopInfo().
					setShopName(ShopNames.mbaobao.toString())
					.setDomainName("mbaobao.com")
					.setCnName("麦包包")
					.setSearchUrl("http://search.mbaobao.com/search/search?o=1&q=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//银泰网
			shops.put(ShopNames.yintai.toString(), new ShopInfo().
					setShopName(ShopNames.yintai.toString())
					.setDomainName("yintai.com")
					.setCnName("银泰网")
					.setSearchUrl("http://www.yintai.com/product/search.aspx?keyword=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//走秀网
			shops.put(ShopNames.xiu.toString(), new ShopInfo().
					setShopName(ShopNames.xiu.toString())
					.setDomainName("xiu.com")
					.setCnName("走秀网")
					.setSearchUrl("http://search.xiu.com/s?kw=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//校园时尚
			shops.put(ShopNames.xyss.toString(), new ShopInfo().
					setShopName(ShopNames.xyss.toString())
					.setDomainName("xyss.org")
					.setCnName("校园时尚")
					.setSearchUrl("http://www.xyss.org/?gallery--n,"+C.keywordVar+"-grid.html")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//梦芭莎
			shops.put(ShopNames.moonbasa.toString(), new ShopInfo().
					setShopName(ShopNames.moonbasa.toString())
					.setDomainName("moonbasa.com")
					.setCnName("梦芭莎")
					.setSearchUrl("http://s.moonbasa.com/?enc=utf-8&keyword=")
					.setMoneyStr("")
					.setMaxResultNumber("40")
					);	
			//拍鞋网
			shops.put(ShopNames.paixie.toString(), new ShopInfo().
					setShopName(ShopNames.paixie.toString())
					.setDomainName("paixie.net")
					.setCnName("拍鞋网")
					.setSearchUrl("http://www.paixie.net/shoes/list/______"+C.keywordVar+"_-1.html")
					.setMoneyStr("") 
					.setMaxResultNumber("40")
					);
			//优购网
			shops.put(ShopNames.yougou.toString(), new ShopInfo().
					setShopName(ShopNames.yougou.toString())
					.setDomainName("yougou.com")
					.setCnName("优购网")
					.setSearchUrl("http://www.yougou.com/k-"+C.keywordVar+".html")
					.setMoneyStr("") 
					.setMaxResultNumber("40")
					);		
			//奥特莱斯
			shops.put(ShopNames.outlets.toString(), new ShopInfo().
					setShopName(ShopNames.outlets.toString())
					.setDomainName("www.outlets365.com")
					.setCnName("奥特莱斯")
					.setSearchUrl("http://www.outlets365.com/Search-"+C.keywordVar+"-0-0-0-3-1.html")
					.setMoneyStr("") 
					.setMaxResultNumber("40")
					);	
		}
	}
	
	public static Map<String, ShopInfo> getShops(){
		if(shops.size()==0){
			init();
		}
		return shops;
	}
	
	public static ShopInfo getShopbyName(String shopname){
		ShopInfo shop = getShops().get(shopname);
		if(shop == null){
			shop = new ShopInfo();
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
