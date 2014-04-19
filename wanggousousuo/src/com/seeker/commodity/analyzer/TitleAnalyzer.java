package com.seeker.commodity.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.seeker.util.XpathMap;
import com.utils.X;

public class TitleAnalyzer {
	
	public List<String> pathList = new ArrayList<String>();
	
	public TitleAnalyzer(){
		
		pathList.add( ".//a[string-length(normalize-space(.))>10 and count( .//img ) < 1 ]  " );
//		pathList.add( "html/body/div/div/div/div/div/p[contains(@class,'productTitle')]/a" );
		
	//	pathList.add(".//a[matches(normalize-space(.),'.*')]");
//		pathList.add("");
		
	}
	
	/**
	 * 
	 * @param t
	 * @param keyword
	 * @param countmap
	 * @return
	 */
	public boolean analyze(Element t, String keyword, XpathMap scoreMap){
		
		boolean targetFinded = false;
		 
		
		for(String xpath : pathList){
			 
			
			List<Element> titleList = X.getSubElementList(t, xpath);
			for(Element titleElement : titleList){
//				if(titleElement.getText()==null || titleElement.getText().length()>5){
//					continue;/*TODO 去掉干扰*/
//				}
				String title = StringUtils.normalizeSpace(titleElement.getValue());
				
				/*去掉所有空格，防止干扰 （这样keyword中不能含有空格了）*/
				title = title.replaceAll(" ", "");
				
//				//System.out.println("pre title "+ title);
				if( StringUtils.containsIgnoreCase(title, keyword) ){
					String path =  X.getSubPath(X.getPath(titleElement), X.getPath(t)) ;
//					//System.out.println("title "+title);
					scoreMap.put(path, path,10);/*包含关键字 10分*/
					targetFinded = true;
					break;
				}
				else if(  title.length()>10 ){
					String path =  X.getSubPath(X.getPath(titleElement), X.getPath(t)) ;
//					//System.out.println("title "+title);
					scoreMap.put(path, path,1);	//不包含关键字 10分
					targetFinded = true;
				}
			}
		}
		return targetFinded;
	}
	
	
	
	public static String parseTitle(String title){
		if(title == null){
			return "";
		}
		//页面瀑布流现实，不在限制标题长度
//		if(title.length()>40){
//			title = title.substring(0, 40);
//			title = title+"..";
//		}
		
		return title;
		
	}
	
}
