<%@page import="com.bean.logic.KeywordService"%>
<%@page import="com.utils.U"%>
<%@page import="com.mutiServer.MutiServerUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta-blog.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<style type="text/css">

.about-main{
	width: 50%; 
	height:3000px;
	margin-top:100px;
	margin-left:auto; margin-right:auto;
	border: 3px dotted #E6E6E6;    
	line-height: 20px; 
	
}

.message {
	font-size:12px;
	font-family:宋体;
	padding: 20px;
	float: left;
}
.message span{
	display:block;
	font-size:12px;
	font-family:宋体;
	color:#878787;
	clear:both;
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
 
 <%
 //test main
 		//List<String> list = new MutiServerUtil().getUsefulUrlList();
 	//	for(String url : list){
 	//		out.print(url);	
 	//	}
 	
		//List<String> list = new KeywordService().getKeywordListFromCacheServer();
		
		out.print(request.getContextPath());
 %>

<jsp:include page="/part/footer3.jsp" />
</body>
</html>