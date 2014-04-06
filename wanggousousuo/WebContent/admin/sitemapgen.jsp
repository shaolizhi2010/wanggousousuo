 
<%@page import="com.bean.logic.SiteMapService"%>
<%@page import="com.env.Env"%>
<%@page import="com.file.FileUtil"%>
<%@page import="com.utils.C"%>
 
<%@ page language="java" pageEncoding="UTF-8"%>

<% 
 
//从work server 的硬盘上去文件夹名或文件名
try{
	out.println("begin");
	
	new SiteMapService().genSiteMap();
	out.println("end");
	
}catch (Exception e) {
	out.println(e.getMessage());
}

%>

 