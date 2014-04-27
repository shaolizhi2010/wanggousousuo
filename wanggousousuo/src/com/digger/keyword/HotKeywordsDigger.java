package com.digger.keyword;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.entity.KeywordEntity;
import com.html.Html;
import com.service.KeywordService;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.U;
import com.utils.X;

public class HotKeywordsDigger {

	public static void main(String[] args) {
		
		L.level = LogLevel.trace;
		HotKeywordsDigger digger = new HotKeywordsDigger();
		digger.dig();
		L.always(null, "end");
	}
	
	public void dig(){
		
		
		List<String> pages = new ArrayList<String>();
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.L1xFvG&zb_type=day");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.NbsYXo&zb_type=day&offset=30");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.NbsYXo&zb_type=day&offset=60");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.NbsYXo&zb_type=day&offset=90");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.NbsYXo&zb_type=day&offset=120");
		
		
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.UDBl74&zb_type=week");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.SPKyM6&zb_type=week&offset=30");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.SPKyM6&zb_type=week&offset=60");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.SPKyM6&zb_type=week&offset=70");
		pages.add("http://top.etao.com/zongbang.php?spm=0.0.0.0.SPKyM6&zb_type=week&offset=120");
		
		for(String page : pages){
			Html html = Connecter.getHtml(page);
			Document doc = html.getDoc();
			List<Element> linkElementlist = X.getSubElementList(doc,"//a");
			
			KeywordService s = new KeywordService();
			for (Element link : linkElementlist) {
				
				String href = link.getAttributeValue("href");
				if(StringUtils.isNoneBlank(href) && StringUtils.startsWith(href, "http://ju.atpanel.com/?url=http://s.taobao.com/")){
					//keywords.add(link.getValue());
					KeywordEntity keywordEntity = new KeywordEntity();
					String keyword = link.getValue();
					keyword = keyword.trim();
					keywordEntity.setKeyword(keyword);
					s.add(keywordEntity);
				}
				
			}
		}
		

		
	}

}
