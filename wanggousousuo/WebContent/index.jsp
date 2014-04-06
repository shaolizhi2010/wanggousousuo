<%@page import="com.utils.C"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = "";// "http://wgsousou.gotoip3.com/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta.jsp" />


<!-- script src="<%=path%>js/jquery-1.9.1.png"></script> -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=path%>css/common.css" type="text/css" />
<link rel="stylesheet" href="<%=path%>css/index.css" type="text/css" />


<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script>
$( document ).ready(function() {
 
	$( "#submit-search-btn" ).on("click", function() {
		//alert($("#keyword").val().length);
		if($("#keyword").val().length>0){
			window.location.href='search.jsp?keyword='+$("#keyword").val(); // 跳转
		}
		else{
			window.location.href='search.jsp?keyword=新款'; // 跳转
		}
		$("#keyword").focus();
	});//end on
	
	$( "#keyword" ).focus( function() {
		//alert($("#keyword").val().length);
		$( "#keyword" ).css("background-image","url()");
		$( "#keyword" ).css("opacity","1");
	});
	$( "#keyword" ).blur( function() {
		//alert($("#keyword").val().length);
		if($( "#keyword" ).val()==''){ //no input
			$( "#keyword" ).css("opacity","0.9");
			$( "#keyword" ).css("background-image","url(img/water-print2.png)");
		}
		
	});	
	
	$( "#keyword" ).keydown( function(event) {

		$( "#keyword" ).css("background-image","url()");
		$( "#keyword" ).css("opacity","1");
		
		if( event.keyCode == 13  ){
			window.location.href='search.jsp?keyword='+$("#keyword").val(); // 跳转
		}
		
	});
	 
});

</script>
</head>

<body>
	<jsp:include page="part/hat.jsp" />

	<h1 class="logo">
		<a href="search.jsp?keyword=新款"> <img width="266" height="70"
			title="<%=C.seo_title%>" alt="<%=C.seo_title%>"
			src="img/logo-wg.png" />
		</a>
	</h1>


	<div id="search-input-div">
		<input type="text" name="keyword" id="keyword" value=''
			onkeydown="searchKeyDown()" /> <input type="button"
			id="submit-search-btn" value="搜索" />

		<div id="hot-words">
			<span>热门搜索:</span> <a href="search.jsp?keyword=打折">打折</a> <a
				href="search.jsp?keyword=新款" style="color: red">新款</a> <a
				href="search.jsp?keyword=家居">家居</a> <a
				href="search.jsp?keyword=大闸蟹">大闸蟹</a> <a
				href="search.jsp?keyword=书包">书包</a> 
				<a href="search.jsp?keyword=换季">换季</a>
		</div>

	</div>

	<jsp:include page="part/footer2.jsp" />
	<jsp:include page="part/footer-link.jsp" />
</body>
</html>