package com.seeker.commodity.analyzer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.connect.Connecter;
import com.connect.URLUtils;
import com.html.Html;
import com.seeker.util.XpathMap;
import com.utils.L;

public class CharsetAnalyzer {
	
	String[] charsetArray = {"UTF-8","GBK","ISO-8859-1"};
	
	/**
	 * 判断规则 
	 * 1 正确的charset 会比错误的charset 返回更多的数据，
	 * 正确页面转成的string 后 会更大，
	 * 即 返回页面size最大的charset，是正确的 charset
	 * 
	 * 需注意的是
	 * 用汉字做keyword 测试charset，
	 * 这条规则 英文不适用
	 * 
	 * 2 正确的charset返回的结果，含有keyword关键字
	 *  而错误的charset，因为keyword被错误的解析成了乱码，
	 *  所以不含有关键字
	 *  这样就可以根据返回页面string中keyword的多少来判断
	 *  
	 *  暂用第二条规则判断
	 * @param url
	 * @param keyword
	 * @return
	 */
	public Map<String, String> analyzeCharset(String url, String keyword){
		
		XpathMap countMapForUrl = new XpathMap();
		XpathMap countMapForContent = new XpathMap();
		
		for(String charsetForUrl : charsetArray){
			
				String buildedUrl = URLUtils.buildUrl(url, keyword, charsetForUrl);
				Html html = Connecter.getHtml(buildedUrl);
				String responseString = html.getPageSource();
				int matchCount = StringUtils.countMatches(responseString, keyword);
				String charsetForContent = html.charser();
//				L.always(this, "use charsert --- "+ charset +" --- contains keyword count --- " + matchCount);
				countMapForUrl.put(charsetForUrl, charsetForUrl, matchCount);
				countMapForContent.put(charsetForContent, charsetForContent, matchCount);
//				//System.out.println(charset + " - " + responseString.length());
//				//System.out.println("responseString " + responseString);
				if(matchCount>40){//够数 跳出 节省时间
					break;
				}
			}

		String charsetForUrl = countMapForUrl.getResultWithMaxScore().getXpath();
		String charsetForContent = countMapForContent.getResultWithMaxScore().getXpath();
		
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("charsetForUrl", charsetForUrl);
		resultMap.put("charsetForContent", charsetForContent);
		return resultMap;
	}
}
