<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<style type="text/css">

.about-main{
	width: 40%; 
	height : 150px;  
	margin-top:100px;
	margin-left:auto; margin-right:auto;
	border: 3px dotted #E6E6E6;    
	line-height: 20px; 
	
}

.message {
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

<div class="about-main">

	<div class="message">
		<span>网购搜索网(www.wanggousousuo.com) 由网友自发建立和维护，致力于实时网购搜索，帮您更快更方便的网购，
		本站不作竞价排名，尽量保证搜索结果的客观准确。</span>
		<br/>
		<span>如有任何问题请联系  qq 53733522，期待您的来信！</span>
	</div>
		 
</div>
	
	<!-- end div main -->
	
<jsp:include page="part/footer2.jsp" />
<jsp:include page="part/footer3.jsp" />
</body>
</html>