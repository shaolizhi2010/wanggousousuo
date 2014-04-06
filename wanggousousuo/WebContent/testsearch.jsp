
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
 <jsp:include page="part/head-meta.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<link rel="stylesheet" href="http://wgsousou.gotoip3.com/css/search.css" type="text/css"/>

<script src="js/jquery-1.9.1.png"></script>
<script src="js/taobaoref.png"></script>

<script>

var C_productsPerPage = 40;//const :product max count per page
var currentItemIndex = 0;
var needShowedItemCount = 0;
var needShowedPageCount = 1;

var allProduct = [];
var shop_condition='none';
var pageIndex ='1';
var price_between_switch = 'off';
var price_min = 0;	//price filter condition
var price_max = 0;	//price filter condition

$( document ).ready(function() {
	
	setPageIndex(1);
	//resetPageIndexCount();
	doSearch('<%=keyword%>');

				//onclick search
				$("#submit-search-btn").on("click", function() {

					doSearch($("#keyword").val());

				}); //end  on

				$("#keyword").keydown(
						function(event) {
							if (event.keyCode == 13) {
								window.location.href = 'search.jsp?keyword='
										+ $("#keyword").val(); // 跳转
							}

				});

				//onclick shop-condition
				$(".shop-condition").on("click", function() {
					
					setPageIndex(1);
					
					$(".shop-condition").attr("style", "color:#055D90");
					$(this).attr("style", "color:red");
					
					$("div#search_result").empty();
				//	alert($(this).children(":first").text());
					shop_condition = $.trim(  $(this).children(":first").text() ) ;
					
					//var filteredProducts = filterProductsByPageIndex();
					showProducts(allProduct);

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
					var sortedProducts = sortJSON(allProduct,'comment', '123'); // 123 or 321
					$("div#search_result").empty();
					setPageIndex(1);
					showProducts(sortedProducts);
				}); //end  on
				$("#sort-comment-321").on("click", function() {
					$(".product-sort").attr("style", "color:#055D90");
					$(this).attr("style", "color:red");
					var sortedProducts = sortJSON(allProduct,'comment', '321'); // 123 or 321
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
				
				
			}); //end document ready

	function doSearch(keywordvar) {
		//search all
		$('#keyword').val(keywordvar);
		searchAll(keywordvar);
	}

	function searchAll(keywordvar) {
		//show loading bar when begin search
		 $("#loading").show();
		 setPageIndex(1);
		 
		//re initialize
		$("#keyword-info span").text("相关搜索  " + $('#keyword').val());
		$("div#search_result").empty();	//clear page
		$('#search_result_summary span').text('0');
		allProduct = []; 
		//init end
		
		searchOne('<%=ShopNames.amazon%>', $('#keyword').val(),4);
		
		
		searchOne('<%=ShopNames.taobao%>', $('#keyword').val(),3);
		searchOne('<%=ShopNames.tmall%>', $('#keyword').val(),3);
		searchOne('<%=ShopNames.jingdong%>', $('#keyword').val(),2);
		
		
		searchOne('<%=ShopNames.dangdang%>', $('#keyword').val(),3);
		searchOne('<%=ShopNames.vipshop%>', $('#keyword').val(),1);
		searchOne('<%=ShopNames.fanke%>', $('#keyword').val(),1);
		searchOne('<%=ShopNames.yixun%>', $('#keyword').val(),1);
		searchOne('<%=ShopNames.paipai%>', $('#keyword').val(),1);
		searchOne('<%=ShopNames.newegg%>', $('#keyword').val(),1);
		searchOne('<%=ShopNames.okbuy%>', $('#keyword').val(),1);
		
	}

	function searchOne(shopnameVar, keywordVar, times) {
		var i = 0;
		while(i<times){
			i++;
			
			$.ajax({
				type:'get',
				 url:'searchBean',	
				 dataType:'jsonp',
				 jsonp:"callback",
				 data:{"shopname":shopnameVar, "keyword":keywordVar, "pagenum":i },
				 async: true,
				 success:function(data){
						//var subproducts = JSON.parse(data);
						showProducts(data);

						allProduct = $.merge(allProduct, data);
						//$("#msg").text("over");
						
						//hide loading bar when any search finished.
						 $("#loading").hide();
				 }

			 }) ;
			
		}
		
	} //end search one
	
	function initProduct(data){
		alert('initProduct');
	}

	function showProducts(products) { 
		
		
		
		//$("#page_numbers").children(".pageindex").gt(needShowedPageCount).hide();
		//$("#page_numbers").children(".pageindex:gt(0)").hide();
		
		//alert("products.length"+products.length);
		//alert('currentItemIndex'+currentItemIndex);
		//template div
		$template = $("div#search_item_template>div").clone();

		
		for ( var p in products) {
			
			
			//filter condition 某些item无需显示
			if(shop_condition != 'none'){	//shop condition filter
				if(shop_condition != products[p].source){//not match codition, not show
					continue;
				}
			}
			if(price_between_switch=='on'){	//price condition filter
				
				if(price_min != 0){
					if( products[p].price < price_min  ){
						continue;
					}
				}
				if(price_max != 0){
					if( products[p].price > price_max){
						continue;
					}
				}
			}
			
			currentItemIndex++;
			
			//翻页 某些item暂不显示
			var startItemIndex  = (pageIndex-1) *C_productsPerPage;
			var endItemIndex  = (pageIndex) *C_productsPerPage;
			
			if( currentItemIndex < startItemIndex || currentItemIndex >= endItemIndex){
				continue;	
			}
			
			
			$newItem = $template.clone();
			$newItem.find("div[class='item_pic'] a img ").attr('src',
					products[p].imgUrl);
			$newItem.find("div[class='item_pic'] a").attr('href',
					products[p].url);
			
			$newItem.find("div[class='item_title'] a").text(products[p].name);
			$newItem.find("div[class='item_title'] a").attr('href',
					products[p].url);

			if (products[p].price.length == 0) {
				$newItem.find("div[class='item_price'] span ").text("暂无");
			} else if (products[p].price.length > 15) {
				$newItem.find("div[class='item_price'] span").hide();
				$newItem.find("div[class='item_price'] img").attr('src',
						products[p].price);
			} else {
				$newItem.find("div[class='item_price'] img").hide();
				$newItem.find("div[class='item_price'] span").text(
						products[p].price);
			}
			
			if(products[p].comment == 0){
				$newItem.find("div[class='item_comment'] a span ").text("暂无");
			}
			else{
				$newItem.find("div[class='item_comment'] a span ").text(products[p].comment);
			}

			$newItem.find("div[class='item_comment'] a").attr('href',
					products[p].commentUrl);
			
			$newItem.find("div[class='item_source'] a").attr('href',
					products[p].url);
			$newItem.find("div[class='item_source'] a img").attr('src',
					'img/logos/' + products[p].source + '.png');
			
			//show on page
			$("#search_result").append($newItem);
			

		}//end for
		
		//alert("needShowedItemCount "+needShowedItemCount );
		//alert("needShowedPageCount "+needShowedPageCount );
		
	}
	
	function setPageIndex(index){
		pageIndex = index;
		currentItemIndex = 0;
		$(".pageindex").attr("style", "color:#055D90");
		$(".pageindex").eq(index-1).attr("style", "color:red");
		$("div#search_result").empty();
		
	}
	
	/*
	function resetPageIndexCount(){	//分页栏总页数 初始化为一个
		needShowedItemCount=0;
		needShowedPageCount=1;
		$('.pageindex').hide();
	}
	*/

	//--- tools --------------------------------------------------------------

	function sortJSON(data, key, way) {
		return data.sort(function(a, b) {
			var x = parseFloat(a[key]);
			var y = parseFloat(b[key]);
			
			if (way === '123') {
 				if(x=='x')return 0;//x unkown value
 				if(y=='x')return 1;//x unkown value
				if(x>y) return 1;
				if(x==y) return 0;
				if(x<y) return -1;
			}
			if (way === '321') {
				if(x=='x')return 1;//x unkown value
 				if(y=='x')return 0;//x unkown value
				if(x<y) return 1;
				if(x==y) return 0;
				if(x>y) return -1;
			}
		});
	}
	
	

</script>

</head>

<body>
<jsp:include page="part/hat.jsp" />
 <jsp:include page="part/header.jsp" />

	<div id="main">
 		<div id="left" class="searchresult-left">
	
			
	
 		</div>
		<div id="right" class="searchresult-right">
			<div id="keyword-info">
				<span>相关搜索</span>
			</div>
	  
			<div id="search_filter">
					<div id="shop_condition">
						条件 : <a href="#" class="shop-condition" id="none-condition"><span style="display: none">none</span>全部</a> 
						
						 <a	href="#" class="shop-condition" id="amazon-condition"><span style="display: none">amazon</span>亚马逊</a>
						 <a href="#" class="shop-condition" id="taobao-condition"><span style="display: none">taobao</span>淘宝网</a>
						 <a href="#" class="shop-condition" id="tmall-condition"><span style="display: none">tmall</span>天猫商城</a>
						 <a href="#" class="shop-condition" id="dangdang-condition"><span style="display: none">dangdang</span>当当网</a>
						 <a href="#" class="shop-condition" id="jingdong-condition"><span style="display: none">jingdong</span>京东商城</a> 
						  <a href="#" class="shop-condition" id="fanke-condition"><span style="display: none">fanke</span>凡客诚品</a>
						  <a href="#" class="shop-condition" id="vipshop-condition"><span style="display: none">vipshop</span>唯品会</a>
						  <a href="#" class="shop-condition" id="yixun-condition"><span style="display: none">yixun</span>易迅网</a>
						  <a href="#" class="shop-condition" id="paipai-condition"><span style="display: none">paipai</span>腾讯拍拍</a>
						  <a href="#" class="shop-condition" id="newegg-condition"><span style="display: none">newegg</span>新蛋中国</a>    
						  <a href="#" class="shop-condition" id="okbuy-condition"><span style="display: none">okbuy</span>好乐买</a>
					</div>
					<br/><br/>
					<div id = "sort">
						 排序 : <a href="#" class="product-sort" id="sort-price-123">价格↑</a><a href="#" class="product-sort" id="sort-price-321">价格↓</a>
						<a href="#" class="product-sort" id="sort-comment-123">销量↑</a><a href="#" class="product-sort" id="sort-comment-321">销量↓</a>
					</div>
					<div id="price-between">
						价格区间:<input id="price-min" type="text"/> - <input id="price-max"  type="text"/> 
						 <a href="#" id="price-betwwen-on"><span>使用</span></a> <a href="#" id="price-betwwen-off"><span>关闭</span></a>
					</div>
			</div>

			<div id="search_result"></div>

			<div id="search_item_template" style="display: none;">
				<div class="search_item">
					<div class="item_pic">
						<a target="_blank" href="#"><img alt="加载中" src=""/></a>
					</div>
					<div class="item_title">
						<a target="_blank" href="#"></a>
					</div>
					<div class="item_price">
						￥<span></span>
					</div>
					<div class="item_comment">
						<a target="_blank" href="#"><span></span><font size="1"> ( 销量/评论)</font></a>
						
					</div>
					<div class="item_source">
						<a target="_blank" href="#"><img alt="" src=""/></a>
					</div>
				</div>
			</div>
			
			<div class='line_holder'></div>
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

<!-- waiting div -->
<div id="loading">
  <p><img src="img/loading.gif" />搜索中...</p>
</div>

	<div class="floater-x"></div>
	
 <jsp:include page="part/footer3.jsp" /> 

</body>
</html>