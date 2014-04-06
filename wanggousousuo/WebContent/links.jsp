<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<style type="text/css">

.about-main{
	width: 40%; 
	height : 220px;  
	margin-top:50px; 
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
.apply_link{
	width: 40%;
	height: 240px; 
	margin-top:30px;
	margin-left:auto; margin-right:auto; 
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
	
	
	
	$("#sub").on("click", function() {
		
		if($('#name').val() == ''){
			alert('请填写网站名称');
			return;
		}
		
		if($('#url').val() == ''){
			alert('请填写网站地址');
			return;
		}
		
		$.post('linkBean', {
			actiontype:'addlink',
			name : $('#name').val(),
			url :$('#url').val()
		}, function(data) {
			$('#msg').text(data);
		});//end post callback function
		
	}); //end  on
	
}); //end document ready

</script>


</head>

<body>
<jsp:include page="part/hat.jsp" />
<jsp:include page="part/header.jsp" />

<div id="body-index">
	<div class="about-main">
	
			<div class="message">
				<span>申请友情链接先将本站加入您的首页链接, 然后提交链接申请，本站会在审核后把您的链接加入本站首页 </span>
				<span> --- </span> 
				<span>本站信息如下：</span>
				<span>网站名称：	网购搜索网</span>
				<span>网站地址：http://www.wanggousousuo.com</span>
				<span>网站介绍: 网购搜索网致力于实时网购搜索和网购导航，可以搜索全网最全最低价的商品，并收录当下最热门最全的网购网站</span>
			</div>
		
	</div>
	<!-- end div main -->
	 		<div class="apply_link">
	 			<h2>申请交换链接</h2>
				网站名称:<span style="color: red">*</span>&nbsp;<input type="text" size="50" maxlength="50" id="name" name="name" /> <br/><br/>
				网站地址:<span style="color: red">*</span>&nbsp;<input type="text "size="50" maxlength="50" id="url" name="url" />&nbsp;(www.abc.com)<br/><br/>
				<span>其他说明:</span>&nbsp;&nbsp;<textarea name="desc" maxlength="100" rows="3" cols="40"></textarea><br/>
				
				<input type="button" id="sub" name="sub"  style="margin-top:20px;margin-left:150px;width: 80px;height: 30px"    value="提交"/><span id="msg" style="color: red;margin-left: 20px;"></span><br/>
			</div>    
	
<jsp:include page="part/footer2.jsp" />
<jsp:include page="part/footer3.jsp" />

</body>
</html>