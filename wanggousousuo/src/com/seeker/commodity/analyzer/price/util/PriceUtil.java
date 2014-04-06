package com.seeker.commodity.analyzer.price.util;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.seeker.commodity.analyzer.vo.Price;


public class PriceUtil {
	
	/*正则 价格字符串特征，加分*/
	public static String addScoreRule = "^.*(¥|￥|&yen|价|元|好乐买|折后|促销价|折后价).*$";
	
	/*正则 非价格字符串特征，减分*/
	public static String  deductionScoreRule = "^.*(减\\d|省\\d|评|市场|折|成交|销量|售出|描述|态度|速度|专柜价).*$";
	
	/*字符串包含价格字符串，即里边有价格，但也可能有其他非价格的字符*/
	public static String  containPriceRule = ".*\\d{1,6}(\\.\\d{1,2})?.*"; 
	
	/*字符串是价格，即 12.34 的形式，不包含任何多余字符,100万以内*/
	public static String isPriceRule = "^\\d{1,6}(\\.\\d{1,2})?$";
	
	/*从小到大排序 eg: 1,2,3*/ 
	public static List<Price> reverse(List<Price> list){
		Collections.sort(list );
		
		Collections.reverse(list);
		
		return list;
	}
	
	/*从大到小排序 eg: 3,2,1*/
	public static List<Price> sort(List<Price> list){
		
		Collections.sort(list);
		
		return list;
	}
	
	public static Boolean matchAddScore(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		return str.matches(addScoreRule);
	} 
	public static Boolean matchDeductionScore(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		return str.matches(deductionScoreRule);
	}
	public static Boolean containPrice(String str){//¥|￥|&yen
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		return str.matches(containPriceRule);
	}
	
	public static Boolean isPrice(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		return str.matches(isPriceRule);
	}
	
	/* 从字符串中提取价格数字 */
	public static String getPrice(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		Pattern pattern = Pattern.compile("\\d{1,6}(\\.\\d{1,2})?");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			return matcher.group();
		}
		return "";
	}
	
}
