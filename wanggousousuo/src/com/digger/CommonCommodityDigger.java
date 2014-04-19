package com.digger;

import java.io.File;
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
import com.entity.ShopEntity;
import com.env.StaticInfo;
import com.html.Html;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.seeker.commodity.analyzer.CommentAnalyzer;
import com.seeker.commodity.analyzer.TitleAnalyzer;
import com.seeker.commodity.analyzer.price.util.PriceUtil;
import com.seeker.rule.Rule;
import com.service.CommodityService;
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
	CommodityService commodityService = new CommodityService();
	
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
		L.trace(this,"Start digall" ); 
		basePath = new U().getRulePath() ;//不加 src 
		//System.out.println("basePath = " + basePath);
		File rules = new File(basePath + C.rulesDir);
		if(rules.listFiles()==null){
			L.exception(this, "path have no rule file, path is --- " + basePath + C.rulesDir);
			return new ArrayList<CommodityEntity>() ;
		}

		
		File[] ruleFiles = rules.listFiles();
		for(File ruleFile : ruleFiles){//遍历rule file 知道找到结果
			if(ruleFile.getName().contains(shopName)){//如果找到商城的rule文件
				List<CommodityEntity> productList = new ArrayList<CommodityEntity>(); 
				dig(keyword, ruleFile, productList);
				
				if(productList !=null && productList.size()>0){
					L.trace(this, "match ruleFile --- " + ruleFile.getName());
					
					return productList;
				 
				}
			}
		}
		return new ArrayList<CommodityEntity>();
	}
	
	public void dig(String keyword,File ruleFile, List<CommodityEntity> listDeprecated){
		
		L.trace(this,"Start dig " ); 
		//listDeprecated = null;//@Deprecated
		
		ShopEntity shopinfo = StaticInfo.getShopbyName(shopName);
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
			
			String charsetForUrl = rule.getRequestEncodeCharset();
			String charsetForContent = rule.getContentEncodeCharset();
			String itemPath = rule.getItemPath();
			String titlePath = rule.getTitlePath();
			String imgPath = rule.getImgPath();
			String pricePath = rule.getPricePath();
			String commentPath = rule.getCommentPath();
			
			String url = URLUtils.buildUrl(preSearchUrl, keyword, charsetForUrl);
			//这个charset 是url 所用的charset 一般网站url和内容所使用的charset相同，暂时用一个进行解析
			Html html = Connecter.getHtml(url,charsetForContent);
			L.trace(this,"Finished get html" ); 
			
			Document doc  = html.getDoc();
			
			List itemList = X.selectNodes(doc, itemPath);
			
			////System.out.println("itemList size "+itemList.size());
			
			
			if(itemList == null || itemList.size()==0) {
			    return ;
			}
			for (Object o :   itemList) {
				Element e = (Element)o;
				CommodityEntity p = new CommodityEntity();
				
				////L.trace(this, X.toString(e));
				
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
					L.exception(this, e2.getMessage());
					L.exception(this, "格式化 price 出错 - " + price);
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
				
				String source = StaticInfo.getShopbyName(shopName).getShopNameCn();
				
				p.setName(name).setUrl(productHref)
					.setImgUrl(imgUrl).setPrice(price)
						.setCommentCount(comment).setCommentUrl(commentHref)
							.setSource(source).setKeyword(keyword);
				
				
				if(p.useful()){
					commodityService.add(p);
					listDeprecated.add(p);
					
				}
				
			} //end for 
			L.trace(this,"Finished iterate item list" ); 
		} catch (Exception e) {
			L.exception(this, "Digger Failed, url is " + preSearchUrl);
			e.printStackTrace();
		} 
		
	}
	
	protected String getPrice(Element e, String pricePath){
		
		////L.trace(this, "getPrice() element xml content: ");
		////L.trace(this, X.toString(e));
		
		String price = X.getValue(e, pricePath);
		price = PriceUtil.getPrice(price);
		return price;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//System.out.println("begin");
		
		try {
			L.level = LogLevel.trace;
	
			String keyword = "iphone";
			
			long start = System.currentTimeMillis();
			L.always("Main","Start main function" ); 
			List<CommodityEntity> plist = new CommonCommodityDigger(
					ShopNames.taobao.toString(),  keyword).digAll();
			System.out.println("Finesh read commodity list, time is " +(System.currentTimeMillis()-start));
			
//			U.printList(plist);
			
			System.out.println("end");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
