<%@ page language="java" pageEncoding="UTF-8"%>

<%

String keyword_seo = (String)request.getAttribute("keyword");
if (keyword_seo == null) {
	keyword_seo = "";
}

%>

<title>网购新鲜事</title>
<meta name='description' content='网购搜索网，网购新鲜事'/>
<meta name='keywords' content='网购新鲜事 <%=keyword_seo%>'/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
