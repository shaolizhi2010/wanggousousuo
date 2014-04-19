package com.seeker.commodity.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.connect.Connecter;
import com.html.Html;
import com.seeker.gen.CommonRuleGenerator;
import com.seeker.util.AnalyzeResult;
import com.seeker.util.XpathItem;
import com.seeker.util.XpathMap;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.ShopNames;
import com.utils.U;
import com.utils.X;

public class ProductsAnalyzer {
	
	/**
	 * 使用 Xpath2.0 提取数据
	 * path 代表 所需使用的 xpath 路径表达式， 如 //div/a 等，
	 * pathList 中的path 不是网页最终使用的绝对路径，
	 * pathList 用于对网页进行初步分析
	 */
	public List<String> pathList = new ArrayList<String>();
	
	public ProductsAnalyzer(){
		
		pathList.add( "//*[ " +
				"count( .//*[string-length(normalize-space(.))>10]  ) >1  "
				+
				"and count( .//*[string-length(normalize-space(.))>10]  ) < 10 "
				+
				"and count( .//img ) > 0 " 
				+
				
				"and count( .//img ) < 20 " +	//去掉包括所有商品信息的大块父节点
				
				 "]" );
		
//		pathList.add("//*[ " +
//				"count( ./*[local-name()='li'])>10 " + //[local-name()= preceding-sibling::*/local-name() ] 
//				"and count( ./*[local-name()='li'])<20 " + //[local-name()= preceding-sibling::*/local-name() ]
//				"or count( ./*[local-name()='tr'])>10 " + //[local-name()= preceding-sibling::*/local-name() ]
//				"]/*");
		
//		pathList.add("html/body/div/div[contains(@class,'con')]/div[contains(@class,'col article')]/div[contains(@class,'book_shoplist')]/div[contains(@class,'con shoplist  book_shoplist')]/ul[contains(@class,'list_aa bigimg')]/li[contains(@class,'line')]");
//		pathList.add("");
	}
	
	public Boolean analyze(Document doc, XpathMap rootElementCountMap){
		
		//X.saveXml(doc);
		
		for(String path : pathList){
			/*
			 * 取出所有可能包含商品信息list的element
			 * 使用全路径形式 eg html/body/div/div....
			 * 不能用 //div/div 的形式，容易造成节点定位不准，如//div/div，可能对应很多个节点
			 * */
			List<Element> rootElementlist = X.getSubElementList(doc, path);	
			
			L.trace(this, "product element list size is " + rootElementlist.size());
			
			for(Element e: rootElementlist){
				//String xpath = X.getPath(e);//全路径
				String xpath =  X.getPath(e);
				rootElementCountMap.put(xpath, xpath, 1);
			}
			
		}
	 
		return true;
	}
	
	public static void main(String[] args) {
		L.level = LogLevel.trace;
		long start = System.currentTimeMillis();
		Html html = Connecter.getHtml("http://search.dangdang.com/?key=iphone", "GBK");
		Document doc = html.getDoc();
		
		new ProductsAnalyzer().analyze(doc, new XpathMap());
		
		L.trace("", "finished time is " + (System.currentTimeMillis()-start) );
		System.out.println("end");
	}
	
}
