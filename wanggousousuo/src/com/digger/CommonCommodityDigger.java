package com.digger;

import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.connect.URLUtils;
import com.digger.util.DiggerUtil;
import com.entity.CommodityEntity;
import com.env.StaticInfo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.seeker.commodity.analyzer.CommentAnalyzer;
import com.seeker.commodity.analyzer.TitleAnalyzer;
import com.seeker.commodity.analyzer.price.util.PriceUtil;
import com.seeker.rule.Rule;
import com.shop.ShopInfo;
import com.utils.App;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.ShopNames;
import com.utils.U;
import com.utils.X;

public class CommonCommodityDigger extends WebBaseDigger implements Digger{
	String basePath; //rule 文件夹位置
	String shopName;
	String keyword;
	
	public CommonCommodityDigger(String shopName,  String keyword) {
		this.shopName = shopName;
		this.keyword = keyword;
	}
	
	public void customPick(CommodityEntity p, Element xml) { // 商铺特有的提取信息规则
		
	}
	
	@Deprecated
	public List<CommodityEntity> digAll(String keyword){
		return null;
	}
	
	public List<CommodityEntity> digAll(){
		
		basePath = new U().getRulePath(); ; 
		System.out.println("basePath = " + basePath);
		File rules = new File(basePath + C.rulesDir);
		if(rules.listFiles()==null){
			L.exception(this, "path have no rule file, path is --- " + basePath + C.rulesDir);
			return new ArrayList<CommodityEntity>() ;
		}
		DB db = App.getInstance().getDBContext();
		DBCollection commoditys = db.getCollection("commodity");
		
		File[] ruleFiles = rules.listFiles();
		for(File ruleFile : ruleFiles){//遍历rule file 知道找到结果
			if(ruleFile.getName().contains(shopName)){//如果找到商城的rule文件
				List<CommodityEntity> productList = new ArrayList<CommodityEntity>(); 
				dig(keyword, ruleFile, productList, commoditys);
				
				if(productList !=null && productList.size()>0){
					L.debug(this, "match ruleFile --- " + ruleFile.getName());
					
				 
				}
			}
		}
		return new ArrayList<CommodityEntity>();
	}
	
	public void dig(String keyword,File ruleFile, List<CommodityEntity> listDeprecated, DBCollection commoditys){
		
		
		listDeprecated = null;//@Deprecated
		
		ShopInfo shopinfo = StaticInfo.getShopbyName(shopName);
		if(shopinfo == null){
			L.exception(this, "can not find shop --- " + shopName);
			return ;
		}
		String preSearchUrl = shopinfo.getSearchUrl();
		if(preSearchUrl==null || preSearchUrl.length()==0){
			return  ;
		}
		String domainStr = shopinfo.getDomainName();
		
		try {
			Rule rule = new Rule();
			rule.fromFile(ruleFile);
			
			String charset = rule.getRequestEncodeCharset();
			String itemPath = rule.getItemPath();
			String titlePath = rule.getTitlePath();
			String imgPath = rule.getImgPath();
			String pricePath = rule.getPricePath();
			String commentPath = rule.getCommentPath();
			
			String url = URLUtils.buildUrl(preSearchUrl, keyword, charset);
			String responseString = Connecter.getPageSource(url);
			
			Document doc  = new org.jdom2.input.SAXBuilder().build(new StringReader(responseString));
			List itemList = X.selectNodes(doc, itemPath);
			//System.out.println("itemList size "+itemList.size());
			
			
			if(itemList == null || itemList.size()==0) {
			    return ;
			}
			
			for (Object o :   itemList) {
				Element e = (Element)o;
				CommodityEntity p = new CommodityEntity();
				
				L.trace(this, X.toString(e));
				
				String name = X.getValue(e, titlePath);
				name = StringUtils.normalizeSpace(name);
				name = TitleAnalyzer.parseTitle(name);
				//去掉关键字中间的空格 TODO
				
				String productHref = X.getAttrValue(e, titlePath, "href");
				productHref = StringUtils.normalizeSpace(productHref);
				productHref = DiggerUtil.getFullUrl(productHref, domainStr);
				
				String imgUrl = X.getAttrValue(e, imgPath);
				if(StringUtils.isBlank(imgUrl)){
					/*有些页面使用部分延迟加载，
					前边若干个图片使用src属性，
					后边的图片使用延迟加载属性，前边的图片就会读取不到
					*/
					String tempImagPath = imgPath;
					if(!tempImagPath.endsWith("src") ){
						String imgAttr = StringUtils.substringAfterLast(tempImagPath, "@");
						tempImagPath = tempImagPath.replace(imgAttr, "src");
						imgUrl = X.getAttrValue(e, tempImagPath);
					}
				}
				imgUrl = StringUtils.normalizeSpace(imgUrl);
				imgUrl = DiggerUtil.getFullUrl(imgUrl, domainStr);
				
				String price = getPrice(e, pricePath);
				price = StringUtils.normalizeSpace(price);
				try {
					if(!StringUtils.isBlank(price)){
						price = new DecimalFormat("######.00").format(new BigDecimal(price));
					}
				} catch (Exception e2) {
					L.debug(this, e2.getMessage());
					L.debug(this, "格式化 price 出错 - " + price);
				}
				
				String comment = "0";//默认0
				String commentHref = "#";
				if(commentPath!=null && !"null".equalsIgnoreCase(commentPath)){
					comment = X.getValue(e, commentPath);
					comment = CommentAnalyzer.getComment(comment);
					commentHref = X.getAttrValue(e, commentPath, "href");
				}
				comment = StringUtils.normalizeSpace(comment);
				commentHref = StringUtils.normalizeSpace(commentHref);
				commentHref = DiggerUtil.getFullUrl(commentHref, domainStr);
				
				String source = StaticInfo.getShopbyName(shopName).getCnName();
				
				p.setName(name).setUrl(productHref)
					.setImgUrl(imgUrl).setPrice(price)
						.setCommentCount(comment).setCommentUrl(commentHref)
							.setSource(source).setKeyword(keyword);
				if(p.useful()){
					//listDeprecated.add(p);
					DBObject commodityItem = U.toDBObject(p);
					
//					commodityItem.put("name", name);
//					commodityItem.put("productHref", productHref);
//					commodityItem.put("imgUrl", imgUrl);
//					commodityItem.put("comment", comment);
//					commodityItem.put("source", source);
//					commodityItem.put("commentHref", commentHref);
//					commodityItem.put("keyword", keyword);
					
					//TODO
					commoditys.insert(commodityItem);
					
				}
				
			} //end for 
		
		} catch (Exception e) {
			L.exception(this, "Digger Failed, url is " + preSearchUrl);
			e.printStackTrace();
		} 
		
	}
	
	protected String getPrice(Element e, String pricePath){
		
		L.trace(this, "getPrice() element xml content: ");
		L.trace(this, X.toString(e));
		
		String price = X.getValue(e, pricePath);
		price = PriceUtil.getPrice(price);
		return price;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("begin");
		
		try {
			L.level = LogLevel.trace;
	
			String keyword = "iphone";
			List<CommodityEntity> plist = new CommonCommodityDigger(
					ShopNames.yintai.toString(),  "D:/git/db/wanggousousuo/wanggousousuo/WebContent/").digAll(keyword);
			
			System.out.println("plist.size() " + plist.size());
			for(CommodityEntity p: plist){
				System.out.println(p.toString());
			}
			
			System.out.println("end");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
