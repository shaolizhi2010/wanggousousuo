<%@page import="java.util.List"%>
<%@page import="com.file.FileSummarize"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.utils.CrossNet"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.File"%>
<%@page import="com.env.Env"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta-blog.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<style type="text/css">

pre {
white-space: pre-wrap;
word-wrap: break-word;
}

a.blog-link{
display:block;
margin-left: 10px;

}

.about-main{
	width: 70%; 
	height:3000px;
	margin-top:100px;
	margin-left:auto; margin-right:auto;
	border: 3px dotted #E6E6E6;    
	line-height: 20px; 
	
}

.message {
	font-size:14px;
	font-family:宋体;
	padding: 20px;
	float: left;
}

.message span{
	display:block;
	font-size:14px;
	font-family:宋体;
	clear:both;
}
 
 .blog-catalog{
	width: 100%; 
	float: left;
}

</style>
<script src="js/jquery-1.9.1.js"></script>
<script>

$( document ).ready(function() {
	
	
	$("#submit-search-btn").on("click", function() {
		if($("#keyword").val().length>0){
			window.location.href='search.jsp?keyword='+$("#keyword").val(); // 跳转
		}
		else{
			window.location.href='search.jsp?keyword=新款'; // 跳转
		}
		$("#keyword").focus();

	}); //end  on

	$("#keyword").keydown(
			function(event) {
				if (event.keyCode == 13) {
					window.location.href = 'search.jsp?keyword='
							+ $("#keyword").val(); // 跳转
				}

	});
	
}); //end document ready


</script>
</head>

<body>
<jsp:include page="part/hat.jsp" />
<jsp:include page="part/header.jsp" />

<div class="about-main">
		<% 
		
		String filename = request.getParameter("filename");
		if (filename != null) {	//显示某篇博客的内容
			filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			//String filenameshow = filename.replace(".txt", "");
			String content = IOUtils.toString(new FileInputStream(Env.basePath + "blog/" + filename+".txt"),"UTF-8");
		
			%>
			<div class="message">
				<pre>
<h3><%=filename %></h3>
<br/>
    <%=content %>
				</pre>
		 
			</div>
			<%
		}
		
		
		%>
		
		<div class="floater"></div>
		
		<div class="blog-catalog">
		<%
		
		String blogjson = new CrossNet().getjson("common?method=getBlogsJson");
		List<FileSummarize> list = new Gson().fromJson(blogjson, new TypeToken<List<FileSummarize>>(){}.getType());
		
		
			for(FileSummarize blogFile : list){
				String filenameshow = blogFile.getName();
				//filenameshow = new String(filenameshow.getBytes("gb2312"), "UTF-8");
				%>
				
				<a class="blog-link" href="blog-<%=filenameshow %>.htm"><%=filenameshow %></a><br/>
					
		<%}%> 
		</div>
</div>
<div >本页文章，署名为网购搜索网的为原创， 其他多为转载，如果侵犯了您的著作权，请与本站联系，本站会及时将其删除。</div>
	<!-- end div main -->
	
<jsp:include page="/part/footer3.jsp" />
</body>
</html>