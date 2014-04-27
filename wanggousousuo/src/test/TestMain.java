package test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import com.connect.Connecter;
import com.connect.SimpleConnecter;
import com.digger.CommodityDigerThread;
import com.digger.CommonCommodityDigger;
import com.entity.CommodityEntity;
import com.html.Html;
import com.service.KeywordService;
import com.utils.L;
import com.utils.ShopNames;
import com.utils.U;

public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
        
            try {
            	
            	new KeywordService().fresh();
            	
            	//SimpleConnecter.connect("http://localhost:8080/wanggousousuo/commodity!search.action?keyword=%E8%A1%A3%E6%9C%8D");
            	
            	//Connecter.connect("http://localhost:8080/wanggousousuo/commodity!search.action?keyword=%E8%A1%A3%E6%9C%8D","utf-8");
            	
//            	 Html html = Connecter.getHtmlInfo("http://www.wanggou.com/sports/brand_list.shtml?index=2&ptag=20219.15.2&pps=focus.8%231038%233375%2362983%23254697%23704955914%231506%231606331%231397355153%230&DAP=17834211002463992134:562954256978559405:2:254697");
//            	 //System.out.println(html.title());
//            	 //System.out.println(html.description());
            	
//            	List<CommodityEntity> list = new CommonCommodityDigger(ShopNames.taobao.toString(), "abc").digAll();
//            	U.printList(list);
            	
//            	String url = "http://search.jd.com/Search?keyword=%E6%96%B0%E6%AC%BE&enc=utf-8";
//            	long start = System.currentTimeMillis();
//
//            	start = System.currentTimeMillis();
//            	String content = SimpleConnecter.connect(url, "GBK");
//            	HtmlCleaner hc1 = new HtmlCleaner();
//            	TagNode node1 = hc1.clean(content);
//            	
//            	System.out.println("SimpleConnecter time is "+ (System.currentTimeMillis() - start));
//            	
//            	start = System.currentTimeMillis();
//            	Connecter.getPageSource(url);
//            	System.out.println("Connecter time is "+ (System.currentTimeMillis() - start));
//            	
//            	start = System.currentTimeMillis();
//            	URL urlobj = new URL(url);
//            	HtmlCleaner hc2 = new HtmlCleaner();
//        		TagNode node2 = hc2.clean(urlobj,"GBK");
//        		
//        		 
//        		System.out.println("HtmlCleaner time is "+ (System.currentTimeMillis() - start));
//        		String s = hc.getInnerHtml(node);
//        		System.out.println(s);
        		
        		
    		} catch ( Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("end...");

    }

}
