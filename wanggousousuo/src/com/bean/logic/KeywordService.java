package com.bean.logic;

import java.io.File;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.connect.SimpleConnecter;
import com.db.DB;
import com.env.Env;
import com.env.StaticInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mutiServer.MutiServerUtil;
import com.shop.ShopInfo;
import com.utils.C;
import com.utils.L;
import com.utils.P;
import com.utils.X;

public class KeywordService {

	String jdbcURL = "jdbc:mysql://bijia365.gotoftp1.com:3306/bijia365";
	String userName = "bijia365";
	String password = "cake4you@W";

	DB db = new DB(jdbcURL, userName, password);

	public boolean add(String keyword, String category) {

		db.update(" insert into keyword values(?,?,?) ", keyword, category,
				null);
		return true;
	}

	public List<Map> getKeywordList() {

		String sql = "select keyword from keyword order by keyword desc";
		List keywordList = (ArrayList) db.query(sql, new MapListHandler());
		return keywordList;
	}

	public List<Map> getKeywordList(int start, int end) {// TODO

		String sql = "select keyword from keyword limit ";
		List keywordList = (ArrayList) db.query(sql, new MapListHandler());
		return keywordList;
	}

	public List<String> getKeywordListFromDB() {

		L.debug(this, "Getting keywords from DB");
		List<String> list = new ArrayList<String>();

		List<Map> listFromDB = getKeywordList();
		for (Map m : listFromDB) {
			Object o = m.get("keyword");
			if (o != null) {
				list.add(o.toString());
			}
		}

		return list;
	}

	// 从本地cache中读取keyword list，cahce server 使用此方法
	public List<String> getKeywordListFromCache(String realPath) {

		L.debug(this, "Getting keywords from cache");
		List<String> list = new ArrayList<String>();

//		File caches = new File(realPath + C.cachePath);
//		if (caches.listFiles() != null) {
//			File[] cacheFiles = caches.listFiles();
//			for (File cacheFile : cacheFiles) {
//				if (cacheFile.getName().endsWith(".txt")
//						&& !cacheFile.getName().contains("_")) { // 滤掉
//																	// taobao_abc.txt,取
//																	// abc.txt
//					String keyword = StringUtils.substringBeforeLast(
//							cacheFile.getName(), ".");// 去掉扩展名
//					if (!KeywordService.checkKeyword(keyword)) {
//						continue;
//					}
//					keyword = KeywordService.clearKeyword(keyword);
//					list.add(keyword);
//				}
//			}
//		}

		// 每个文件夹最多23307个文件 所以有很多备用cache cache1 cache2
		File cachesParentDir = new File(realPath);

		for (File file1 : cachesParentDir.listFiles()) {
			if (file1.isDirectory() && file1.getName().startsWith("cache")) {

				for (File cacheFile : file1.listFiles()) {
					if (cacheFile.getName().endsWith(".txt")
							&& !cacheFile.getName().contains("_")) { // 滤掉
																		// taobao_abc.txt,取
																		// abc.txt
						String keyword = StringUtils.substringBeforeLast(
								cacheFile.getName(), ".");// 去掉扩展名
						if (!KeywordService.checkKeyword(keyword)) {
							continue;
						}
						keyword = KeywordService.clearKeyword(keyword);
						list.add(keyword);
					}
				}
			}
		}

		L.debug(this, "Keyword size form cache : " + list.size());
		return list;
	}

	// 访问商城 从网页上抓取keyword
	public List<String> getKeywordListFromLink() {

		L.debug(this, "Getting keywords from link");
		List<String> newKeywordlist = new ArrayList<String>();
		List<String> oldKeywordlist = getKeywordListFromCache(Env.basePath);
		String targetUrl = "";

		for (Map.Entry<String, ShopInfo> shop : StaticInfo.getShops()
				.entrySet()) {
			if (new Random().nextInt(10) > 5) {// 随机，防止每次都抓取同一个商城
				continue;
			}
			targetUrl = shop.getValue().getActivityUrl();
			if (StringUtils.isBlank(targetUrl)) {
				continue;
			}
			String responseString = Connecter.connect(targetUrl, null);

			Document doc;
			try {
				doc = new org.jdom2.input.SAXBuilder().build(new StringReader(
						responseString));
				List itemList = X.selectNodes(doc, "//a");

				for (Object o : itemList) {
					Element e = (Element) o;

					String text = e.getText();// 取链接文字，提取keyword
					if (StringUtils.isNotBlank(text) && text.length() > 10
							& text.length() < 100) {// 过滤掉过长的字符
						text = KeywordService.clearKeyword(text);// 去掉无用字符
						String[] keywords = KeywordService.strToKeywords(text);// 分词
						for (String keyword : keywords) {// 判断每个keyword
							if (keyword.length() > 5)
								keyword = keyword.substring(0, 4);// 最长取四位
							if (!oldKeywordlist.contains(keyword)
									&& !newKeywordlist.contains(keyword)) {// 新关键字
								L.debug(this, "Add new keyword : " + keyword
										+ " from " + targetUrl);
								newKeywordlist.add(keyword);
								if (newKeywordlist.size() > C.maxNewKeywordCount) {// 到规定个数就停止
									return newKeywordlist; // 返回keyword list
								}
							}
						}

					}
				}// end for
			} catch (Exception e) {
				L.exception(this, e.getMessage());
			}

		}
		L.log(this, "Keyword size form link : " + newKeywordlist.size());
		return newKeywordlist;
	}

	// 从cache server 取keyword list
	public List<String> getKeywordListFromCacheServer() {
		List<String> keywordlist = new ArrayList<String>();
		String keywordsJson = new MutiServerUtil().getKeywordsFromCacheServer();
		if (StringUtils.isBlank(keywordsJson)) {
			return keywordlist;
		}
		List<String> list = new Gson().fromJson(keywordsJson,
				new TypeToken<List<String>>() {
				}.getType());
		if (list == null) {
			return keywordlist;
		}
		for (String keyword : list) {
			keywordlist.add(keyword);
		}
		return keywordlist;
	}

	/* 检错并重新生成cache，便于后续的 用cache 生成sitemap的工作，
	 * 便于搜索引擎收录 
	*/
	public void refreshKeywords(List<String> keywordsFromCache,
			boolean openRandom) {

		for (String keyword : keywordsFromCache) {

			if (openRandom && new Random().nextInt(10) > 3) {
				continue;// 随机刷keyword
			}
			if (!KeywordService.checkKeyword(keyword)) {
				continue;
			}

			for (String shopname : StaticInfo.getShops().keySet()) {

				try {
					String keywordEncode = URLEncoder.encode(keyword, "utf-8");
					String url = P.getProperty("cacheurl")
							+ "searchBean?shopname=" + shopname + "&keyword="
							+ keywordEncode;

					SimpleConnecter.connect(url);
					L.debug(this, "Refreshed keyword : " + url);

				} catch (Exception e) {
					L.exception("Example", e.getMessage());
					continue;
				}
			}
		}// end for
	}

	public static Boolean checkKeyword(String keyword) {

		if (StringUtils.isBlank(keyword)) {
			return false;
		}
		if (keyword.length() > 5) {// 长度1 - 5
			return false;
		}
		if (keyword.matches(".*[\\?�è].*")) {// 判断是否乱码
			return false;
		}

		return true;
	}

	public static String clearKeyword(String keyword) {

		if (StringUtils.isBlank(keyword)) {
			return "";
		}
		keyword = keyword.replaceAll("[\\[\\]【】\\s]", "");
		return keyword;
	}

	// 分词
	public static String[] strToKeywords(String str) {

		String[] keywords = {};
		if (StringUtils.isBlank(str)) {
			return keywords;
		}
		keywords = str.split("[:\\-\\s/]");// : - 空格
		return keywords;
	}

}
