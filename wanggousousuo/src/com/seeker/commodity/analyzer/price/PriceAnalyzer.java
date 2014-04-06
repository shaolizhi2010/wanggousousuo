package com.seeker.commodity.analyzer.price;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.seeker.commodity.analyzer.price.util.PriceUtil;
import com.seeker.commodity.analyzer.vo.Price;
import com.seeker.util.XpathMap;
import com.utils.L;
import com.utils.X;


public class PriceAnalyzer {
	
	
	
	public List<String> pathList = new ArrayList<String>();
	
	public PriceAnalyzer(){
		
		//不是 <del> 删除线 ,这种价格是折前价 无意义
//		pathList.add( ".//*[local-name(.) != 'del' and count(*) = 0]" );
		
		/*遍历除del外的所有元素*/
		pathList.add( ".//*[local-name(.) != 'del' and local-name(.) != 'a']" );
	}
	
	/**
	 * 通过打分机制，计算哪个节点是价格节点
	 * 
	 * 可能影响分数的因素
	 * 1. 节点包含字符串中，包含价格节点常带有的标识（如，‘￥’加分），或包含非价格节点 常带有的标识（如，‘评论数’ 减分）
	 *    这个因素是第一因素，所以调整分数不能太小，建议大于 5 分
	 *    
	 * 2. 节点所包含的数字（可能是价格）的大小,有小到大排序，价格越小得分越高，
	 * 	    这个因素是第二因素，建议，排序相差一名，则相差一分
	 * 
	 * 
	 * @param t
	 * @param keyword
	 * @param countmap
	 * @return
	 */
	public boolean analyze(Element t, XpathMap scoreMap){
		
		boolean targetFinded = false;
		
		for(String xpath : pathList){
			
			List<Price> plist = new LinkedList<Price>();
			/*所有可能包含price 的节点*/
			List<Element> priceList =  X.getSubElementList(t, xpath);
			
			/*
			 * 处理每个可能包含价格的节点，然后判断是否是有效价格
			 * */
			for(Element priceElement : priceList){
				
				String priceStr = StringUtils.normalizeSpace(priceElement.getValue());
				
				if( priceStr.length()<15 && PriceUtil.containPrice(priceStr) ){  
					
					/*从字符串中取price，可能一个字符串存在多个price，取出一个就行*/
					String[] prices = priceStr.split("[^\\d.]");/* TODO \\d$ ?*/
					if(prices==null || prices.length>5){/*去掉包含太多商品信息节点，那可能是所有商品的父节点*/
						continue;
					}
					
					for(String price : prices){
						try {
							if(PriceUtil.isPrice(price) ){
								//String tempPath = X.getPath(priceElement);
								String path =  X.getSubPath(X.getPath(priceElement), X.getPath(t)) ;
								
								/*判断是否有相同xpath的其他节点，如有则标记index，防止混淆*/
								path = X.getPathWithIndex(priceElement, t, path);
								
								Double priceDouble  = new Double(price);
								Price pe = new Price(path,priceDouble);
								
								/*检查priceStr,而不是price,因为price已经滤掉了标识字符*/
								if(PriceUtil.matchAddScore(priceStr)){	
									pe.addScore(7);
								}
								if(PriceUtil.matchDeductionScore(priceStr)){	
									pe.addScore(-7);
								}
								plist.add(pe);
								
								targetFinded = true;
								break;	/*取出一个price就跳出，避免重复*/
							}
						} catch (Exception e) {
							L.exception(this,e.getMessage());
							continue;
						}
					}

					
				}//end if				
				
			}//end for  price list
			
			/*按price 从小到大排序*/
			plist = PriceUtil.reverse(plist);
			int score = 10;
			for(Price pe: plist){
				scoreMap.put(pe.getXpath(), pe.getXpath(), pe.getScore() + score--);/*price越大 得分越低*/
			}
			
 
		}//end for xpath list
		return targetFinded;
	}
	

	
}
