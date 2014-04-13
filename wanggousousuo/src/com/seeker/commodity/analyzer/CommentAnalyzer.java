package com.seeker.commodity.analyzer;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.seeker.util.XpathMap;
import com.utils.L;
import com.utils.Regex;
import com.utils.X;


public class CommentAnalyzer {
	
	public static String containCommentKeyword = ".*评.*";
	public static String containNumberCommentRule = ".*\\d.*";
	public static String containChineseCommentRule = ".*\\d万.*";
	
	public List<String> pathList = new ArrayList<String>();
	
	public CommentAnalyzer(){
		
		//评论信息 长度一半小于15个字符
		pathList.add( ".//a[string-length(normalize-space(.))<15]" );
		
//		keyList.add("");
//		keyList.add("");
	}
	
	/**
	 * 规则
	 * 1 含有一个评字
	 * 2 含有整形数字 (也可能是1.3万) 这种形式
	 * @param t
	 * @param keyword
	 * @param countmap
	 * @return
	 */
	public void analyze(Element t, XpathMap countmap){
		
		XPathFactory xpfac = XPathFactory.instance();
		
		for(String xpath : pathList){
			XPathExpression xp = xpfac.compile(xpath);
			
			List<Element> commentList =  (List<Element>)xp.evaluate(t);
			for(Element commentElement : commentList){
				
				String commentStr = StringUtils.normalizeSpace(commentElement.getValue());
				
				if( containComment(commentStr) ){  
//					//System.out.println("commentStr "+ commentStr);
					String path =  X.getSubPath(X.getPath(commentElement), X.getPath(t)) ;
					countmap.put(path, path,1);
					break;
					
				}
			}//end for
		}//end for xpath list
	}
	
	public Boolean containComment(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		if(!str.matches(containCommentKeyword)){
			return false;// 不含 '评' 字
		}
		if(str.matches(containNumberCommentRule)){
			return true;
		}
		else if(str.matches(containChineseCommentRule)){
			return true;
		}
		return false;
	}
	
	/*从字符串中提取价格数字*/
	public static String getComment(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher matcher = pattern.matcher(str);
		String commentCount = "0";
		if(matcher.find()){
			commentCount = matcher.group();
		}
		if(str.contains("万")){	/* 评论是带万的 如  1.3万 的形式*/
			try {
				commentCount = new Float(commentCount)* 10000 +"";
				commentCount = StringUtils.substringBefore(commentCount, ".");
			} catch (Exception e) {
				L.exception("CommentAnalyzer", e.getMessage());
			}

		}
		return commentCount;
	}
	
}
