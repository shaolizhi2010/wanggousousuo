
<%@page import="java.util.UUID"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.utils.C"%>
<%@page import="com.utils.ShopNames"%>

<%
	String keyword = request.getParameter("keyword");
	if (keyword == null) {
		keyword = "";
	}
	keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
%>
<%
String path = "";//"http://wgsousou.gotoip3.com/";
//String path = "http://wgsousou.gotoip3.com/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta.jsp" />

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="css/common.css" type="text/css"/>
<link rel="stylesheet" href="css/search.css" type="text/css"/>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<jsp:include page="js/search.js.jsp" />



<script>

$( document ).ready(function() {
	//init and search after page is loaded
	setPageIndex(1);
	$('#keyword').val('<%=keyword%>');
	var userId = getCookie('wgss_userid');
	if(userId == ''){
		userId = '<%=UUID.randomUUID()%>';	
		setCookie('wgss_userid', userId, '365');
	}

	<%
	if(keyword.length()>0){
		%>
		searchAll('<%=keyword%>' );

		$.ajax({
			type:'get',
			 url:'search',	
			 dataType:'jsonp',
			 jsonp:"callback",
			 data:{"method":'getHistory',"userId": userId},
			 async: true,
			 success:function(data){
				 if(data != "" && data != undefined){
					 viewedProducts = [];
					 viewedProducts = $.merge(viewedProducts, data);	 
				 }
			 }
		 }) ;
		<%
	}
	else{
		%>
		$('#keyword-info').hide();
		$('#search_filter').hide();
		$.ajax({
			type:'get',
			 url:'search',	
			 dataType:'jsonp',
			 jsonp:"callback",
			 data:{"method":'getHistory',"userId": userId},
			 async: true,
			 success:function(data){
				 if(data != "" && data != undefined){
						viewedProducts = [];
						viewedProducts = $.merge(viewedProducts, data);
						//viewedProducts = viewedProducts.reverse();
						showProducts(viewedProducts.reverse());
				 }

				$("#loading").hide();
			 }
		 }) ;

		
		<%
	}
		%>

		

	
	
	//$('#go-top').css('left', ($("#search_result").offset().left+$("#search_result").width())+'px');
	
	
	$(window).scroll(function(){
		
		if($(document).scrollTop()>100){
			$('#go-top').css('display','block');	
		}
		else {
			$('#go-top').css('display','none');	
		}
	});
	
	//onclick search
	$("#submit-search-btn").on("click", function() {
		window.location.href = 'search.jsp?keyword='+ $("#keyword").val(); // 跳转
	}); //end  on

	$("#keyword").keydown(
		function(event) {
			if (event.keyCode == 13) {
				window.location.href = 'search.jsp?keyword='+ $("#keyword").val(); // 跳转
			}
	});

	//onclick shop-condition
	$(".shop-condition a").on("click", function() {
		
		setPageIndex(1);
		
		$(".shop-condition a").css("color", "#055D90");
		$(this).css("color", "red");
		
		$("div#search_result").empty();
		
		shop_condition = $.trim(  $(this).text() ) ;
		
		showProducts(allProduct);
		
		if($(this).text()=='淘宝网'){
			//alert('tb');
			//document.write("<script src='js/taobaoref.js' id='taobaojs'><\/scrip>");
			//$('#taobaojs').attr('src','js/taobaoref.js');
			//$.getScript("js/taobaoref.js"); 
		 
		}
		if($(this).text()=='天猫商城'){
			//alert('tm');
			//$("script[src='js/taobaoref.js']").remove();
			//$('#taobaojs').attr('src','') ;
			//alert($('#taobaojs').attr('src'));
			//win.alimamatk_onload=[];
			//$('a').unbind();
		}

	}); //end  on
	
	//onclick page num
	$(".pageindex").on("click", function() {
		pageIndex =   $(this).text();	//set page num
		setPageIndex(pageIndex);
		showProducts(allProduct);

	}); //end  on
	
	//begin sort
	$("#sort-price-123").on("click", function() {
		$(".product-sort").attr("style", "color:#055D90");
		$(this).attr("style", "color:red");
		var sortedProducts = sortJSON(allProduct,'price', '123'); // 123 or 321
		$("div#search_result").empty();
		setPageIndex(1);
		showProducts(sortedProducts);
	}); //end  on
	$("#sort-price-321").on("click", function() {
		$(".product-sort").attr("style", "color:#055D90");
		$(this).attr("style", "color:red");
		var sortedProducts = sortJSON(allProduct,'price', '321'); // 123 or 321
		$("div#search_result").empty();
		setPageIndex(1);
		showProducts(sortedProducts);
	}); //end  on
	$("#sort-comment-123").on("click", function() {
		$(".product-sort").attr("style", "color:#055D90");
		$(this).attr("style", "color:red");
		var sortedProducts = sortJSON(allProduct,'commentCount', '123'); // 123 or 321
		$("div#search_result").empty();
		setPageIndex(1);
		showProducts(sortedProducts);
	}); //end  on
	$("#sort-comment-321").on("click", function() {
		$(".product-sort").attr("style", "color:#055D90");
		$(this).attr("style", "color:red");
		var sortedProducts = sortJSON(allProduct,'commentCount', '321'); // 123 or 321
		$("div#search_result").empty();
		setPageIndex(1);
		showProducts(sortedProducts);
	}); //end  on
	//end sort
	
	$("#price-betwwen-on").on("click", function() {
		
		setPageIndex(1);
		
		price_min = $("#price-min").val();	//between start
		price_max = $("#price-max").val();  //between end
		
		price_min = parseFloat(price_min);
		price_max = parseFloat(price_max);
		
		$("#price-betwwen-off").attr("style", "color:#055D90");
		$(this).attr("style", "color:red");
		price_between_switch = "on";
		$("div#search_result").empty();
		showProducts(allProduct);
	}); //end  on

	$("#price-betwwen-off").on("click", function() {
		setPageIndex(1);
		$("#price-betwwen-on").attr("style", "color:#055D90");	//change text color
		$(this).attr("style", "color:red");
		price_between_switch = "off";	//open 
		$("div#search_result").empty();	//show products
		showProducts(allProduct);
	}); //end  on
	
	$(".logic-view-product").on("click", function() {
		
		var viewedProductStr = $(this).parent().parent().children('input').val();
		var viewedProductObj = JSON.parse(viewedProductStr);
		
		
		for(var p in viewedProducts){
			if(viewedProducts[p].url == viewedProductObj.url){//如果已经添加过了，不在添加
				//alert(viewedProducts[p].url);
				//alert(viewedProductObj.url);
				return true;
			}
		}
		
		viewedProducts = $.merge(viewedProducts, [viewedProductObj]);
		
		$.ajax({
			type:'post',
			 url:'search',
			 dataType:'jsonp',
			 jsonp:"callback",
			 data:{"data":JSON.stringify(viewedProducts), "method":'addHistory',"userId": userId},
			 async: true,
			 success:function(data){
		 		//
		 		return;
			 }
		 }) ;
 		 
	}); //end  on	


	
	$('#item-hovor').on("click", function() {
		//window.location.href=  $('#item-hovor').attr("href")   ; // 跳转
	}); //end  on
	
}); //end document ready



</script>

</head>

<body>
<jsp:include page="part/hat.jsp" /> 
 <jsp:include page="part/header.jsp" />
	<div id="main">
 		
		<div  class="searchresult" >
			<div id="keyword-info">
				<span>相关搜索</span>
			</div>
	  
			<div id="search_filter">
					<div class="condition">
						<div class="condition-left-1"><span style="">商家</span> </div>
						<div class="condition-right-1" id="condition-shops">
							<ul>
								 <li class="shop-condition"><a href="#"><span>全部</span></a> </li>
							</ul>
							  
						 </div>
					</div>
					<div class="clear-fix-show"></div>
					 
					<div class="condition-left-2">排序</div>
					<div class="condition-right-2" >
						<a href="#" class="product-sort" id="sort-price-123">价格↑</a><a href="#" class="product-sort" id="sort-price-321">价格↓</a>
						<a href="#" class="product-sort" id="sort-comment-123">销量↑</a><a href="#" class="product-sort" id="sort-comment-321">销量↓</a>
					</div>
					 
					<div class="condition-left-2">价格区间</div>
					<div class="condition-right-2" >
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="price-min" type="text"/> - <input id="price-max"  type="text"/>
						<a href="#" id="price-betwwen-on"><span>使用</span></a> <a href="#" id="price-betwwen-off"><span>关闭</span></a>
					</div>
					<div class="clear-fix-show"></div>
			</div>

			<div id="search_result"></div>

			<div id="search_item_template" style="display: none;">
					
					<div class="search_item">
						
						<div class="item_pic">
							<a href="#" target="_blank" class="logic-view-product">
								<img src=""  alt="" />
							</a>
						</div>
						<div class="item_title">
							<a target="_blank" href="#" class="logic-view-product"></a>
						</div>
						
						<div class="item_source">
							<a target="_blank" href="#" class="logic-view-product"></a>
						</div>
						<div class="item_comment" >
							<a target="_blank" href="#" class="logic-view-product">评论(<span></span>)  |  </a>
						</div>
						<div class="item_price">￥<span></span></div>

						<input type="hidden"   value=""/>
					</div>
			</div>
			
			<div id="item-hovor">
				<div id="item-shoucang" style="z-index: 5500" >☆ 收藏 ☆</div>
				<a id="link-cache" target="_blank" href="#"></a>
				
			</div>
			
			<a id="go-top" href="#"></a>
				
			
			<div class="clear-fix-show"></div>
			<div id="pages">
				<div id="page_numbers">
					<a href="#" class="pageindex" ><span >1</span></a>
					<a href="#" class="pageindex" ><span >2</span></a>
					<a href="#" class="pageindex" ><span >3</span></a>
					<a href="#" class="pageindex" ><span >4</span></a>
					<a href="#" class="pageindex" ><span >5</span></a>
					<a href="#" class="pageindex" ><span >6</span></a>
				</div>
			</div>
		</div>
		<!-- end right -->
	</div>
	<!-- end div main -->


<div id="view-history">


</div>

<!-- waiting div -->
<div id="loading">
  <p><img src="img/loading.gif" /></p>
</div>

	<div class="floater-x"></div>
	
 <jsp:include page="part/footer3.jsp" /> 
</body>

</html>