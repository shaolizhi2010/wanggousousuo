package com.timer;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bean.logic.BlogService;
import com.bean.logic.KeywordService;
import com.bean.logic.SiteMapService;
import com.env.Env;
import com.utils.C;
import com.utils.L;
import com.utils.P;

public class DailyTask extends java.util.TimerTask {

	@Override
	public void run() {
		L.log(this, " is running");
		
		/* 如果是blog server，每天抓取最新blog */
		try{
			if("1".equals(P.getProperty("isblogserver"))){
				/* 从派代抓取最新派代商讯 */
				L.log(this, "开始抓取blog");
				new BlogService().digBlog();
			}
		}catch (Exception e) {
			L.exception(this, "抓取 blog 失败");
			L.exception(this, e.getMessage());
		}
		
		/* 如果是cache server，把cache 中的keyword 刷新一遍 */
		if("1".equals(P.getProperty("iscacheserver"))){
			KeywordService keywordService = new KeywordService();
			
			try{
				/* 检查cache文件夹是否已满
				 * 满了就把cache文件夹改名，并新建一个空cache文件夹 
				 * */  
				String cachePath =  Env.basePath + File.separator + C.cachePath;
				cachePath = cachePath.replaceAll("//", "/");
				File cachePathFile = new File(cachePath);
				
				/* 如果文件数超过1万，将文件夹改名并创建新文件夹 */
				if(cachePathFile!=null && cachePathFile.listFiles()!=null && cachePathFile.listFiles().length>10000){
					String targetPath = Env.basePath + File.separator + "cache-"+UUID.randomUUID();
					cachePathFile.renameTo(new File(targetPath));
				}
				new File(cachePath).mkdir();
			}catch (Exception e) {
				L.exception(this, e.getMessage());
			}
			
			try{
				/* 从其他商城抓取新的keyword，保证网站每天有新keyword */
				List<String> keywordsFromLink = keywordService.getKeywordListFromLink();
				keywordService.refreshKeywords(keywordsFromLink,false);
				L.always(this, "Refresh New Keywords : " + keywordsFromLink.size());
			}catch (Exception e) {
				L.exception(this, e.getMessage());
			}
			
			/* 从cache中取关键字,暂不需更新，因为每次取cache都是随机的shop的内容，每次内容
			 * 都不一样，因此不需再更新 
			 */
//			List<String> keywordsFromCache = keywordService
//					.getKeywordListFromCache(Env.basePath);
//			keywordService.refreshKeywords(keywordsFromCache,true);
//			L.always(this, "Refresh cache : " + keywordsFromCache.size());
			
			//cache过少时使用
//			List<String> keywordsFromDB= keywordService.getKeywordListFromDB();
//			keywordService.refreshKeywords(keywordsFromDB,false);
//			L.always(this, "Refresh cache : " + keywordsFromDB.size());
			
		}// end if cache server
		
		/* 如果是center服务器，定时刷新sitemap 便于搜索引擎收录。 */
		try{
			if ("1".equalsIgnoreCase(P.getProperty("iscenter"))) {// 判断是否是中心服务器

				// init sitemap.txt
				new SiteMapService().genSiteMap();
				L.log(this, "site map generated ");
			}
			

		}catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		
	}// end run

//	public List<String> getkeywordsFromDB() {
//		List<String> keywords = new ArrayList<String>();
//		KeywordService ks = new KeywordService();
//		List<Map> keywordmaplist = ks.getKeywordList();
//		for (Map m : keywordmaplist) {
//			keywords.add((String) m.get("keyword"));
//		}
//
//		return keywords;
//	}
//	
	

}
