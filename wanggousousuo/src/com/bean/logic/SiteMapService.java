package com.bean.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.env.Env;
import com.utils.L;

public class SiteMapService {
	
	public void genSiteMap(){
		List<String> keywords = new KeywordService().getKeywordListFromCacheServer();
		L.log(this, "Site map contains keywords size : " + keywords.size());
		
		List<String> blogs = new BlogService().getBlogListFromServer();
		L.log(this, "Site map contains blogs size : " + blogs.size());
		
		//generate Sitemap.txt
		StringBuffer linksBuffer = new StringBuffer();
		
		linksBuffer.append("http://www.wanggousousuo.com/index.jsp" + System.getProperty("line.separator"));
		linksBuffer.append("http://www.wanggousousuo.com/map-1.htm" + System.getProperty("line.separator"));
		
		linksBuffer.append("http://www.wanggousousuo.com/" + System.getProperty("line.separator"));
		try {
			for(String keyword : keywords){
				if(StringUtils.isBlank(keyword) || keyword.contains("?") || keyword.contains("�")  || keyword.length()>5){
					continue;
				}
					String link = "http://www.wanggousousuo.com/" + keyword + ".htm" + System.getProperty("line.separator");//sitemap中的一条网址
					linksBuffer.append(link);
			}
			for(String blog : blogs){
				if(StringUtils.isBlank(blog)){
					continue;
				}
				blog = blog.replaceAll(".txt", "");
					String link = "http://www.wanggousousuo.com/blog-" + blog + ".htm" + System.getProperty("line.separator");//sitemap中的一条网址
					linksBuffer.append(link);
			}
			
			File siteMapFile = new File( Env.basePath +"Sitemap.txt");
			FileUtils.write(siteMapFile, linksBuffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
