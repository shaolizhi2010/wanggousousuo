<%@ page language="java" pageEncoding="UTF-8"%>

<%

String keyword_seo = (String)request.getAttribute("keyword");
if (keyword_seo == null) {
	keyword_seo = "";
}

%>

<title>找网购 <%=keyword_seo %>-网购搜索网</title>
<meta name='description' content='网购搜索网  网购 <%=keyword_seo %> 一键搜索 淘宝天猫 亚马逊 京东商城 当当网等数十家网购'/>
<meta name='keywords' content='找网购- <%=keyword_seo %>'/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
