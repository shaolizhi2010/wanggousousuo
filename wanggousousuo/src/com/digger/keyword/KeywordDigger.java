package com.digger.keyword;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Document;
import org.jdom2.Element;

import com.bean.logic.KeywordService;
import com.db.DB;
import com.utils.L;
import com.utils.X;

public class KeywordDigger {
	
	String jdbcURL = "jdbc:mysql://bijia365.gotoftp1.com:3306/bijia365";
	String userName = "bijia365";
	String password = "cake4you@W";
	
	public Map<String, List<String>> allListMap = new HashMap<String, List<String>>();

	public List<String> book = new ArrayList<String>();//图书、音像、电子书刊
	public List<String> elec = new ArrayList<String>();//家用电器
	public List<String> shuma = new ArrayList<String>();//手机、数码
	public List<String> computer = new ArrayList<String>();//电脑、办公
	public List<String> home = new ArrayList<String>();//家居家装 厨具
	public List<String> cloth = new ArrayList<String>();//服饰鞋帽
	public List<String> beauty = new ArrayList<String>();//个护化妆 美容
	public List<String> bag = new ArrayList<String>();//钟表 珠宝 首饰
	public List<String> luxury = new ArrayList<String>();//钟表 珠宝 首饰 奢侈品
	public List<String> sport = new ArrayList<String>();//运动健康 户外
	public List<String> car = new ArrayList<String>();//汽车用品
	public List<String> baby = new ArrayList<String>();//玩具乐器 母婴
	public List<String> eat = new ArrayList<String>();//玩具乐器 母婴
	
	public KeywordDigger(){
		init();
	}
	
	public void init(){
		book.add("http://book.jd.com/");
		book.add("http://mvd.jd.com/");
		book.add("http://e.jd.com/");
//		book.add("http://www.amazon.cn/%E5%9B%BE%E4%B9%A6/b/ref=sa_menu_top_books_l1?ie=UTF8&node=658390051");
//		book.add("http://www.amazon.cn/%E9%9F%B3%E5%83%8F/b/ref=sa_menu_top_music_l1?ie=UTF8&node=247167071");
//		book.add("http://www.amazon.cn/%E6%AD%A3%E7%89%88%E8%BD%AF%E4%BB%B6/b/ref=sa_menu_top_software_l1?ie=UTF8&node=863872051");
		
		elec.add("http://channel.jd.com/electronic.html");
		
		shuma.add("http://shouji.jd.com/");
		shuma.add("http://channel.jd.com/digital.html");
		
		computer.add("http://channel.jd.com/computer.html");
		
		home.add("http://channel.jd.com/home.html");
		home.add("http://channel.jd.com/kitchenware.html");
		
		cloth.add("http://channel.jd.com/clothing.html");
//		cloth.add("http://www.amazon.cn/%E6%9C%8D%E8%A3%85%E6%9C%8D%E9%A5%B0/b/ref=sa_menu_top_cloth_l1?ie=UTF8&node=2016156051");
//		cloth.add("http://www.amazon.cn/%E9%9E%8B-%E9%9D%B4%E5%AD%90/b/ref=sa_menu_top_shoes_l1?ie=UTF8&node=2029189051");
		
			
		beauty.add("http://channel.jd.com/beauty.html");
//		beauty.add("http://www.amazon.cn/%E7%BE%8E%E5%AE%B9%E5%8C%96%E5%A6%86/b/ref=sa_menu_top_beauty_l1?ie=UTF8&node=746776051");
		
		

		bag.add("http://channel.jd.com/bag.html");
		
		luxury.add("http://channel.jd.com/watch.html");
		luxury.add("http://channel.jd.com/jewellery.html");
		
		sport.add("http://channel.jd.com/sports.html");
//		sport.add("http://www.amazon.cn/%E8%BF%90%E5%8A%A8-%E6%88%B7%E5%A4%96-%E4%BC%91%E9%97%B2/b/ref=sa_menu_top_sports_and_outdoor_l1?ie=UTF8&node=836312051");
		
		
		car.add("http://channel.jd.com/auto.html");
		
		baby.add("http://channel.jd.com/baby.html");
		baby.add("http://channel.jd.com/toys.html");
		
		eat.add("http://channel.jd.com/food.html");
		
		allListMap.put("图书音像", book);
		allListMap.put("家用电器", elec);
		allListMap.put("数码茶品", shuma);
		allListMap.put("电脑办公", computer);
		allListMap.put("家居厨卫", home);
		allListMap.put("服装鞋帽", cloth);
		allListMap.put("美妆个护", beauty);
		allListMap.put("礼品箱包", bag);
		allListMap.put("钟表珠宝", luxury);
		allListMap.put("运动户外", sport);
		allListMap.put("汽车用品", car);
		allListMap.put("母婴玩具", baby);
		allListMap.put("食品保健", eat);
		
	}
	
	public void scan(){
		
		for(Map.Entry<String, List<String>> entry: allListMap.entrySet()){
			String category = entry.getKey();
			List<String> urlList = entry.getValue();
			for(String url : urlList){
				dig(url, category);
			}
		}
	}
	
	public void dig(String url, String category){
		HtmlCleaner hc = new HtmlCleaner();
		TagNode node;
		try {
			node = hc.clean(new URL(url),"GBK");
			CleanerProperties props = hc.getProperties();
			PrettyXmlSerializer serializer = new PrettyXmlSerializer(props);
			String xmlString = serializer.getAsString(node,"GBK");
			Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(xmlString));
			
			
			
			
			List list = X.selectNodes(doc, "//a");
			
			System.out.println(list.size());
			DB db = new DB(jdbcURL,userName,password);
			
			for (Object o : list) {
				
				Element xml = (Element) o;
				String value = xml.getValue();
	 
				String keywordUrl = xml.getAttributeValue("href");
				
				if(keywordUrl==null)continue;
				if(value==null)	continue;
				
				if( !keywordUrl.endsWith("html") && !keywordUrl.endsWith("htm")){
					continue;
				}
				

				value = value.replaceAll("\n", "");
				value = value.replaceAll("\r", "");
				
				
				//value = new String(value.getBytes(),"UTF-8");
				if(StringUtils.isNotBlank(value)  && value.length()<6){
					db.update(" insert into keyword (keyword,category ) values(?,?) ", value,category);
				}
				
			}
			
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}  
		
		
	}
	
	public void readKeywords(){
		
//		Map<String, List<String>> category = new HashMap<String, List<String>>();
//		
//		String sql = "select * from keyword ";
//		List<Map> mapList = (List) new DB().query(sql, new MapListHandler() );
//		System.out.println(mapList.size());
		
		List<Map> list = new KeywordService().getKeywordList();
		
		for(Map m : list){
			System.out.println(m.get("keyword"));
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("start main");
		System.out.println(new Date(System.currentTimeMillis()));
		KeywordDigger digger = new KeywordDigger();
		digger.scan();
		
//		digger.readKeywords();
		System.out.println("end");
		System.out.println(new Date(System.currentTimeMillis()));

	}

}
