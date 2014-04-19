<%@page import="java.util.ArrayList"%>
<%@page import="com.service.CatalogService"%>
<%@page import="com.entity.CatalogEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


		<%
			List<CatalogEntity> catalogList = new CatalogService().list(0,10);
			if (catalogList == null) {
				catalogList = new ArrayList<CatalogEntity>();
			}


		%>


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
			<img class="img-responsive" 
				src="http://img13.360buyimg.com/da/g12/M00/06/1C/rBEQYVNGUhMIAAAAAACMv0eEd4QAAEK9gPp6l8AAIzX591.jpg"
				> 
		</div>
		
		
		
		<div class="col-md-12 c-search"><!-- search --> 
			<div class="row">
				 <img id="logo" class="img-responsive col-md-5 col-sm-6" src="img/logo-wg.png">
				
				<form class="navbar-form navbar-left col-md-7 col-sm-6" role="search" action="commodity!search.action">
			        <div class="form-group">
			          <input type="search" class="form-control" name="keyword" id="keyword" placeholder="商品名称">
			        </div>
		        	<button   type="submit" id="search-btn" class="btn btn-default">搜索</button>
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



	</div>

</div>