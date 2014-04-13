package test;

import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.JDomSerializer;
import org.htmlcleaner.TagNode;

public class HttpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(new URL("http://www.amazon.cn/mn/search/ajax?rh=i%3Aaps%2Ck%3Aakg+k420&__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&fromHash=%2Fref%3Dnb_sb_noss%3F__mk_zh_CN%3D%25E4%25BA%259A%25E9%25A9%25AC%25E9%2580%258A%25E7%25BD%2591%25E7%25AB%2599%26url%3Dsearch-alias%253Daps%26field-keywords%3Dakg%2B420%26rh%3Di%253Aaps%252Ck%253Aakg%2B420&section=ATF&fromApp=gp%2Fsearch&fromPage=results&version=2&ajp=iss"),"UTF-8");//encoding used for parse returned content
			CleanerProperties props = hc.getProperties();
			JDomSerializer jdomSerializer = new JDomSerializer(props, true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("end...");
	}

}
