package com.seeker.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.connect.URLUtils;
import com.env.StaticInfo;
import com.seeker.commodity.analyzer.CharsetAnalyzer;
import com.seeker.commodity.analyzer.CommentAnalyzer;
import com.seeker.commodity.analyzer.ImgAnalyzer;
import com.seeker.commodity.analyzer.ProductsAnalyzer;
import com.seeker.commodity.analyzer.TitleAnalyzer;
import com.seeker.commodity.analyzer.price.PriceAnalyzer;
import com.seeker.rule.Rule;
import com.seeker.rule.RuleUtil;
import com.seeker.util.XpathItem;
import com.seeker.util.XpathMap;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.ShopNames;
import com.utils.U;
import com.utils.X;

public class CommonRuleGenerator {
 
	public void generateRule(String shopName,String keyword,String keywordPinyin,String basePath) {
		try {
			//L.level = LogLevel.debug;
			L.trace("CommonRuleGenerator", "generateRule begin, shopname - " + shopName + " - keyword - " + keyword);
			String preSearchUrl = StaticInfo.getShopbyName(shopName).getSearchUrl();
			
			Map<String, String> chasetMap = new CharsetAnalyzer().analyzeCharset(preSearchUrl, keyword);
			String requestEncodeCharset = chasetMap.get("charsetForUrl");
			String contentEncodeCharset = chasetMap.get("charsetForContent");
			L.trace("CommonRuleGenerator", "requestEncodeCharset --- " + requestEncodeCharset);
			
			
			String url = URLUtils.buildUrl(preSearchUrl, keyword, requestEncodeCharset);
			String responseString = Connecter.getPageSource(url,contentEncodeCharset);
			
			//L.debug("CommonRuleGenerator","CommonRuleGenerator", "responseString size --- " + responseString.length());
			
			Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(responseString));
			
			XpathMap rootElementCountMap = new XpathMap();	/*所有可能包含商品信息的element的list*/

			//XpathMap listItemMap = new XpathMap();
			
			/*提取所有可能的包含商品信息的xpath*/
			new ProductsAnalyzer().analyze(doc, rootElementCountMap);
			for(Map.Entry<String, XpathItem> e :rootElementCountMap.xpathMap.entrySet()){/*遍历所有可能的root element*/
				String rootPath = e.getValue().getXpath();
				//String rootPathShort = e.getv
				
				List<Element> elist = X.getSubElementList(doc, rootPath);
				
				XpathMap tilteCountMap = new XpathMap();
				XpathMap imgCountMap = new XpathMap();
				XpathMap priceCountMap = new XpathMap();
				XpathMap commentCountMap = new XpathMap();
				int matchedResultCount = 0 ;
				
				for (Element t : elist) {
					////L.trace(this, X.getPath(t));
					
					if(X.toString(t).length()<200){		/*节点长度太小，不会是商品信息element*/
						continue;
					}

					boolean titleFinded = new TitleAnalyzer().analyze(t, keyword, tilteCountMap);
					//L.trace(this, "titleFinded ");
					boolean imgFinded = new ImgAnalyzer().analyze(t, imgCountMap);
					//L.trace(this, "imgFinded ");
					boolean priceFinded = new PriceAnalyzer().analyze(t, priceCountMap);
					//L.trace(this, "priceFinded ");
					
					////L.trace(this, priceCountMap.toString());
					
			        new CommentAnalyzer().analyze(t, commentCountMap);
			        
			        if(titleFinded && imgFinded ){//&& priceFinded
			        	matchedResultCount++;
			        	//String path = "//"+X.getPath(t,1);
			        	//L.debug("CommonRuleGenerator","products path "+ path);
			        	//listItemMap.put(path, path,1);
			        }
					
					//L.debug("CommonRuleGenerator","-------------------------");
					//L.debug("CommonRuleGenerator","CommonRuleGenerator", StringUtils.normalizeSpace(t.getValue()));
//				  L.debug("CommonRuleGenerator",pathText.getText());
				} //end for 
				
				//String itemPath = (String)listItemMap.getResultWithMaxScore().getXpath();
				
				if(matchedResultCount>5){	/* 找到匹配的xpath */
				//	//L.trace(this, X.getPath(t));
					String titlePath = (String)tilteCountMap.getResultWithMaxScore().getXpath();
					String imgPath =  (String)imgCountMap.getResultWithMaxScore().getXpath();
					String pricePath =  (String)priceCountMap.getResultWithMaxScore().getXpath();
					String commentPath = (String)commentCountMap.getResultWithMaxScore().getXpath();
					
					Rule newRule = new Rule();
					newRule.setRequestEncodeCharset(requestEncodeCharset);
					newRule.setContentEncodeCharset(contentEncodeCharset);
					newRule.setItemPath(rootPath);
					newRule.setTitlePath(titlePath);
					newRule.setImgPath(imgPath);
					newRule.setPricePath(pricePath);
					newRule.setCommentPath(commentPath);
					
					L.trace(this, newRule.toString());
					
					/*检查rule是否存在 避免重复*/
					if(RuleUtil.checkRuleFileExist(newRule, basePath, shopName)){
						L.trace("CommonRulGenerator", "rule already exist");
						return;	/*存在，不再继续尝试新的rule，否则可能产生不准的rule文件*/
					}
					
					/*	save rule file	*/
					File dir = new File(basePath + C.rulesDir);
					if( !dir.exists() || !dir.isDirectory()){/*不存在或不是目录*/
						dir.mkdir();
					}
					
					String path = basePath + C.rulesDir + shopName+"-"+keywordPinyin+".rule" ;
					L.log("CommonRuleGenerator","New Rule generated, save rule to path "+ path);
					PrintWriter out = new PrintWriter(new FileWriter(new File(path)));  
					//System.out.println("path = "+ path);
					
//					out.print(newRule.toString());
					out.print(newRule.toJson());
		            out.flush();
		            out.close();
		            
		            /*成功产生一个rule 则 return	*/
		            return;
				}
//				else{
//					
//				}
			}
			L.debug("CommonRuleGenerator", "尝试产生rule 失败, shopname - " +shopName + "-keyword-"+keyword );
		} catch (Exception e) {
			L.exception("CommonRuleGenerator",e.getMessage());
		}  
	}
	
	public boolean checkRuleExist(){
		
		return false;
	}
	
	public static void main(String[] args) {
		String keyword = "iphone";
//		L.level = LogLevel.debug;
		L.level = LogLevel.trace;
		long start = System.currentTimeMillis();
		
		String basePath = new U().getRulePath()+"src/"; 
		new CommonRuleGenerator().generateRule(ShopNames.dangdang.toString(), keyword,keyword, basePath);
		L.trace("", "finished time is " + (System.currentTimeMillis()-start) );
		System.out.println("end");
	}

	
}
