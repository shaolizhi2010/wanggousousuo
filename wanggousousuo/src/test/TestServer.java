package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.bean.logic.KeywordService;
import com.connect.Connecter;
import com.env.Env;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Map> keywords = new KeywordService().getKeywordList();
		StringBuffer sb = new StringBuffer();
		
		for(Map m : keywords){
			if( StringUtils.isBlank((String)m.get("keyword"))  ){
				continue;
			}
			String keyword = (String)m.get("keyword");
			
			try {
//				keyword = URLEncoder.encode(keyword, "utf-8");

				String url = "http://www.wanggousousuo.com/search.jsp?keyword="+keyword;
				sb.append("<a target='_blank' href='" + url + "'> "+ keyword +" </a>");
				
			
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}//end for
		
		System.out.println(sb);

	}

}
