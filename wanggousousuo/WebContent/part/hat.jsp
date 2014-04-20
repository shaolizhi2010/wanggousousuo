<%@page import="com.connect.URLUtils"%>
<%@page import="com.builder.UrlBuilder"%>
<%@page import="com.entity.CommodityEntity"%>
<%@page import="com.entity.ShopEntity"%>
<%@page import="com.service.ShopService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.service.CatalogService"%>
<%@page import="com.entity.CatalogEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


		<%
		
			CommodityEntity entity = new CommodityEntity();
			if(request.getAttribute("entity")!=null){
				if(request.getAttribute("entity") instanceof CommodityEntity){
					entity = (CommodityEntity)request.getAttribute("entity");
				}
			}
			if(entity.getKeyword() == null  ){
				entity.setKeyword("");
			}
		
			List<CatalogEntity> catalogList = new CatalogService().list(0,10);
			if (catalogList == null) {
				catalogList = new ArrayList<CatalogEntity>();
			}

		%>
<script>

$( document ).ready(function() {
	
	//set keyword to input
	$("#keyword").val("<%=entity.getKeyword()%>");
	
	//
	$("#quick-search-btn").on("click", function() {
		$("#quick-search").toggle();
	}); //end  on
});
</script>

<div class="container c-head">
	<!-- style="border-style:solid; border-width:2px; border-color:green;" -->
	<div class="row clearfix ">
		<!-- navbar-fixed-top -->
		<div class="col-md-12 c-personal hide" > <!-- personal -->
			<!-- style="background-color: #ccc;" -->
			<nav class="navbar navbar-default " role="navigation">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="dazhe.jsp">收藏网购搜索网</a></li>
						</ul>

						<ul class="nav navbar-nav pull-right">

							<li><a href="dazhe.jsp">登录</a></li>
							<li><a href="search.jsp">注册</a></li>
							<li><a href="/fenxiang">晒网购</a></li>

						</ul>


					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>

		</div>
 
		<div class="col-md-12 c-imgbar"><!-- img -->
			<!-- style="background-color: #ccc;" -->
			<a target="_blank" href="http://baby.tmall.com/go/market/baby/exchange9.php?spm=a221w.7085061.0.0.Qo1Ev1">
				<img class="img-responsive" 
					src="http://gtms03.alicdn.com/tps/i3/T1DkhtFIJXXXc8BxsO-1190-80.jpg"
					> 
			</a>
				

		</div>
		
		
		
		<div class="col-md-12 c-search"><!-- search --> 
			<div class="row">
				 <img id="logo" class="img-responsive col-md-5 col-sm-6" src="img/logo-wg.png">
				
				<form class="navbar-form navbar-left col-md-7 col-sm-6" role="search" action="commodity!search.action">
			        <div class="form-group">
			          <input type="search" class="form-control" name="keyword" id="keyword" placeholder="商品名称">
			        </div>
		        	<button   type="submit" id="search-btn" class="btn btn-default">搜索</button>
		        	<button   type="button" id="quick-search-btn" class="btn btn-default">快捷搜索</button>
		      	</form>
			</div>
		</div>
		
		
		
		<div class="col-md-12 c-menu"><!-- menu -->
			<!-- style="background-color: #ccc;" -->
			<nav class="navbar navbar-default""
				role="navigation">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-4">
							<span class="sr-only"> </span> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-4">
						<ul class="nav navbar-nav">
							<li><a href="index.jsp" style="color: #fff;">首页</a></li>
							<li><a href="dazhe.jsp" style="color: #fff;">打折促销</a></li>
		
							
							<li class="divider" style="color: #fff;"></li>
							
							<%
							for (CatalogEntity e : catalogList) {
								%>
								
								<li><a href="commodity!search.action?keyword=<%=e.getKeyword() %>" style="color: #fff;"><%=e.getName() %></a></li>
								
								<%
							}
							%>
							
							<li><a href="/fenxiang" style="color: #fff;">晒网购</a></li>

						</ul>


					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>

		</div>

       <div class="row clearfix col-md-12" id="quick-search">
        	<div class="col-md-12" >
	        	<%
	        		ShopService shopService = new ShopService();
	        		List<ShopEntity> shopList = shopService.listAvailableShops(null,0,16);  
	        		int i = 0;
	        		for(ShopEntity shop : shopList){
	        			%>
	        				<div class="thumbnail col-md-2">
	        				<a href="<%=URLUtils.buildUrl( shop, entity.getKeyword(), shop.getCharsetForUrl() )%>" target="_blank">  
								<img style="width:68px;height:30px;" src="img/logos/<%=shop.getLogoImg() %>" class="img-response" />
						 	</a> 
						 	</div>
	        			<%
	        		}
	        		
	        	%>
        	</div>
        </div>

	</div>

</div>