package com.bean.web;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.URLEncoder;
import org.apache.commons.lang3.StringUtils;


import com.utils.L;

public class CommonFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		// .htm is for 伪静态
		String url = ((HttpServletRequest)req).getRequestURL().toString();
		
//		if( url.endsWith("jsp") ||url.endsWith("html")||url.endsWith("htm")){
//			new Guard().record(request, url);
//		}
		
		/* url以  '/' 结束，访问的是网站根目录? TODO */
		String pagename = StringUtils.substringAfterLast(url, "/");
		if(pagename.equalsIgnoreCase("") )
		{
			req.getRequestDispatcher("/search.jsp?keyword=iphone").forward(req, resp);
			return;
		}
		
		/* 以.htm 结束的， 可能是伪静态 */
		if(pagename.endsWith(".htm")){
			
			/* 以index.htm 结束，跳到搜索 新款 页面 */
			if( pagename.equalsIgnoreCase("index.htm") ){
				String keyword= "新款";
				req.setAttribute("keyword", keyword);
				keyword= new URLEncoder().encode(keyword);
				req.getRequestDispatcher("/search.jsp?keyword="+keyword).forward(req, resp);
				return;
			}
			/* 以map- 开始，是 网站地图的页面, 如 map-6.htm  */
			else if(pagename.startsWith("map-")){	//网站地图，关键字伪静态
				String pageIndex = StringUtils.substringBetween(pagename,"map-", ".htm");
				/* 转化成正常的网址 */
				req.getRequestDispatcher("/map.jsp?pageIndex="+pageIndex).forward(req, resp);
				return;
			}
			/* 以blogindex- 开始，是 网站地图的页面, 如 blogindex-6.htm  */
			else if(pagename.startsWith("blogindex-")){	/* 网站地图，关键字伪静态 */
				String pageIndex = StringUtils.substringBetween(pagename,"blogindex-", ".htm");
				req.getRequestDispatcher("/blog.jsp?pageIndex="+pageIndex).forward(req, resp);
				return;
		
			}			
			/*
			 * */
			else if(pagename.endsWith(".htm")){
				/* 查看blog内容的伪静态 */
				if(pagename.startsWith("blog-")){//blog 伪静态
					String blogname = StringUtils.substringBetween(pagename, "blog-", ".htm");
					req.getRequestDispatcher("/blog.jsp?filename="+blogname).forward(req, resp);
					return;
				}
				else{
					/*
					 * keyword.html 形式，网站地图页面里，关键字的链接，
					 * 跳转到查看staticsearch.jsp页面
					 */ 
					String keyword = StringUtils.substringBeforeLast(pagename, ".htm");
					
					keyword = new URLDecoder().decode(keyword, "utf-8");// utf-8
					//keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
					L.debug(this, keyword);
					
					req.setAttribute("keyword", keyword);
		 
					req.getRequestDispatcher("/staticSearchBean").forward(req, resp);
					return;
				}
				
			}
			
		}
		
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
