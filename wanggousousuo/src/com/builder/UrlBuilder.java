package com.builder;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.entity.CommodityEntity;
import com.utils.L;
import com.utils.ShopNames;

public class UrlBuilder {
	
	public void buildUrlsForPage(String shopStr, List<CommodityEntity> products){
		
		if(StringUtils.isBlank(shopStr)){
			L.exception(UrlBuilder.class, "shop name is null ");
			return ;
		}
		
		for( CommodityEntity p :  products ){
			String rawProductUrl = p.getUrl();
			String cookedProductUrl = p.getUrl();
			String rawCommentUrl = p.getCommentUrl();
			String cookedCommentUrl = p.getCommentUrl();
			
//			if(StringUtils.isBlank(rawProductUrl)){
//				L.exception(UrlBuilder.class, "url for build is null ");
//				cookedProductUrl = "#";
//			}
			
			
			if(ShopNames.amazon.toString().equalsIgnoreCase(shopStr)){	//amazon
				String pid = StringUtils.substringBetween(rawProductUrl, "/dp/", "/");	
				if(pid==null){
					L.exception(this, shopStr + " --- get product id is null");
					L.exception(this, "rawProductUrl --- " + rawProductUrl);
				}
				else{
					cookedProductUrl = "http://www.amazon.cn/gp/product/"+pid+"?ie=UTF8&camp=536&creativeASIN="+pid+"&linkCode=xm2&tag=bijia365-23" ;
				}
				
				//cookedCommentUrl = "http://www.amazon.cn/product-reviews/" + pid+"?ie=UTF8&camp=536&creativeASIN="+pid+"&linkCode=xm2&tag=bijia365-23" ;;
			}
//			if(ShopNames.jingdong.toString().equalsIgnoreCase(shopStr)){//jingdong
//				String pid = StringUtils.substringBetween(rawProductUrl, "product/", ".htm");	
//				cookedProductUrl = "http://item.jd.com/"+pid+".html";	////http://item.jd.com/1001577989.html
//				cookedCommentUrl = "http://item.jd.com/"+pid+".html#comment";
//			}
//			if(ShopNames.dangdang.toString().equalsIgnoreCase(shopStr)){
//				//<a target="_blank" href="http%3A%2F%2Fproduct.dangdang.com%2Fproduct.aspx%3Fproduct_id%3D60265977">当当热卖商品推荐</a>
//				String pid = StringUtils.substringBetween(rawProductUrl, "product_id=", "&");
//				if(pid==null){
//					L.exception(this, shopStr + " --- get product id is null");
//					L.exception(this, "rawProductUrl --- " + rawProductUrl);
//				}
//				else{
//					cookedProductUrl = "http://union.dangdang.com/transfer.php?sys_id=1&ad_type=10&from=P-315609&backurl=http://product.dangdang.com/product.aspx?product_id="+pid;
//				}
//				//cookedCommentUrl = "http://product.dangdang.com/product.aspx?product_id="+pid+"#review_point";
//			}
//			if(ShopNames.yihaodian.toString().equalsIgnoreCase(shopStr)){
//				cookedProductUrl = p.getUrl() + "&&tracker_u=102241362";
//			}

//			if(ShopNames.fanke.toString().equalsIgnoreCase(shopStr)){
//				cookedProductUrl = p.getUrl()+"?source=shaolizhi2010&sourcesuninfo=ad-0-4-0-0-3";
//				//cookedCommentUrl= "http://item.vancl.com/"+pid+".html?source=shaolizhi2010&sourcesuninfo=ad-0-4-0-0-3#feedback";
//			}
//			if(ShopNames.vipshop.toString().equalsIgnoreCase(shopStr)){
//				//http://m.vipshop.com/index.php?v=touch&m=product&brand_id=76388&product_id=9319148&act=intro
//				String brand_id = StringUtils.substringBetween(rawProductUrl, "brand_id=", "&");
//				String product_id = StringUtils.substringBetween(rawProductUrl, "product_id=", "&");
//				
//				//http://shop.vipshop.com/detail-76388-9319148.html
//				cookedProductUrl = "http://shop.vipshop.com/detail-"+brand_id+"-"+product_id+".html";
//				cookedCommentUrl= cookedProductUrl;//no comments in vipshop
//			}
//			if(ShopNames.yixun.toString().equalsIgnoreCase(shopStr)){
//				
//				cookedProductUrl = p.getUrl();
//				cookedCommentUrl= cookedProductUrl+"#introduction";
//			}
//			if(ShopNames.paipai.toString().equalsIgnoreCase(shopStr)){
//				String pid = StringUtils.substringBetween(p.getUrl(),"ic=","&");
//				cookedProductUrl = "http://auction1.paipai.com/search/0/"+pid+"-D0F6.html"; 
//				//http://auction1.paipai.com/search/0/67C6286D000000000401000012B2D771-CA37.html#nolink
//				cookedCommentUrl= cookedProductUrl+"#commodityRate";
//			}
//			if(ShopNames.newegg.toString().equalsIgnoreCase(shopStr)){
//				String pid = StringUtils.substringBetween(p.getUrl(),"Product/",".htm");
//				cookedProductUrl = "http://www.newegg.com.cn/Product/"+pid+".htm"; 
//				cookedCommentUrl= "http://www.newegg.com.cn/Product/"+pid+"/Reviews.htm";
//			}
//			if(ShopNames.okbuy.toString().equalsIgnoreCase(shopStr)){
//				//http://m.okbuy.com/product/detail/17042129.html?sid=518a5af2147c11368021746&ref_1=key&ref_2=search&ref_3=
//				String pid = StringUtils.substringBetween(p.getUrl(),"detail/",".html");
//				cookedProductUrl = "http://www.okbuy.com/a/shoe-"+pid+".html"; 
//				cookedCommentUrl= "http://www.okbuy.com/a/shoe-"+pid+".html#prodConBtm";
//			}
//			if(ShopNames.handuyishe.toString().equalsIgnoreCase(shopStr) //相对路径补全成绝对路径
//					|| ShopNames.outlets.toString().equalsIgnoreCase(shopStr)
//					|| ShopNames.okbuy.toString().equalsIgnoreCase(shopStr)){
//				if( p.getUrl().startsWith("/")){
//					cookedProductUrl = "http://"+StaticInfo.getShopbyName(shopStr).getDomainName()+  p.getUrl();
//				}
//				else{
//					cookedProductUrl = "http://"+StaticInfo.getShopbyName(shopStr).getDomainName()+"/"+  p.getUrl();
//				}
//				//cookedCommentUrl = "http://product.dangdang.com/product.aspx?product_id="+pid+"#review_point";
//			}
			p.setUrl(cookedProductUrl);
			p.setCommentUrl(cookedCommentUrl);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
