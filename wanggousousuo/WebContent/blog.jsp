<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.utils.U"%>
<%@page import="com.file.FileUtil"%>
<%@page import="com.mutiServer.MutiServerUtil"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.File"%>
<%@page import="com.env.Env"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta-blog.jsp" />

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css" type="text/css"/>
<style type="text/css">

pre {
white-space: pre-wrap;
word-wrap: break-word;
}

a.blog-link{
display:block;
margin-left: 10px;

}

.blog-left-folders{
	width: 20%; 
	min-height:1000px;
	margin-top:100px;
	margin-left:50px;
	padding-top:20px;
	border: 1px dotted  #E6E6E6; 
	float: left;
}

.about-main{
	width: 66%; 
	min-height:1000px;
	margin-top:100px;
	border: 3px dotted #E6E6E6;    
	line-height: 20px; 
	float: left;
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
	float: left;
}
  #pages{
  	width: 100%;
  	height:30px;
  	margin-top: 100px;
  	margin-left:auto; margin-right:auto;
  	
  	text-align: center;
  }
  #pages a{
  	font-size: 16px;
  	margin-left: 10px; 
  	text-decoration: none;
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
		
		if (!StringUtils.isBlank(filename)) {	//显示某篇博客的内容
			filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			//String filenameshow = filename.replace(".txt", "");
			String content = new MutiServerUtil().getFileContent("http://bijia365.net:8008/wanggousousuo/", "blog/" + filename+".txt");
		
			%>
			<div class="message">
				<pre>
<h3><%=filename %></h3>
<br/>
    <%=content %>
				</pre>
		 
			</div>
			<%
		}//end if
		
		
		%>
		
		<div class="floater"></div>
		
		<div class="blog-catalog">
		<%
		String pageIndexStr = "1";
		int pageIndex = 1; 
		if(request.getParameter("pageIndex") != null){
			pageIndexStr = request.getParameter("pageIndex");	
		}
		try{
			pageIndex = Integer.parseInt(pageIndexStr);	
		}catch(NumberFormatException e){
			pageIndex = 1; 
		}
		 
		int startIndex = (pageIndex-1)*30;
		int endIndex = pageIndex*30;
		String blogJson = new MutiServerUtil().getFiles("http://bijia365.net:8008/wanggousousuo/", "blog/", FileUtil.File);
		//System.out.println(blogJson);
		List<Map> bloglist = U.jsonToListMap(blogJson);
			int i = 0;
			for(Map m : bloglist){
				String filenameshow = m.get("name").toString().replace(".txt", "");
				String filenameUrl = filenameshow.replaceAll("%", "%25");
				i++;
				if(i>startIndex && i< endIndex){
				%>
				
				<a class="blog-link" href="<%=request.getContextPath()%>/<%=pageIndexStr%>/blog-<%=filenameUrl%>.htm"><%=filenameshow %></a><br/>
					
		<%
		}	//end if
			} //end for
		%> 
		

		</div>
</div>
<div style="float:left;" >本页文章，署名为网购搜索网的为原创， 其他为转载，如果侵犯了您的著作权，请与本站联系，本站会及时将其删除。</div>
	<!-- end div main -->
	<div class="floater-x"></div>
		<div id="pages">
		<% 
			for(int p=1;p<=bloglist.size()/30+1;p++){
				out.print("<a href='blogindex-"+ p +".htm'> "+p+"  </a>");	
			}
		%>
	</div>
<jsp:include page="/part/footer3.jsp" />
</body>
</html>