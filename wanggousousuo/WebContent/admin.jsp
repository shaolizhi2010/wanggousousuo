<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.service.CatalogService"%>
<%@page import="com.entity.CatalogEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

 <%
 
	String password = request.getParameter("p");
 	if(StringUtils.isBlank(password)){
 		return; //无密码
 	}
 	if(!"cake4you".equals(password)){
 		return; // 密码错
 	}
 
 %>

<div class="container c-head">
	<!-- style="border-style:solid; border-width:2px; border-color:green;" -->
	<div class="row clearfix ">
		<!-- navbar-fixed-top -->
		<div class="col-md-12 c-personal"> <!-- personal -->
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

						<ul class="nav navbar-nav pull-right">

							<li><a href="dazhe.jsp">登录</a></li>
							<li><a href="search.jsp">注册</a></li>
							<li><a href="/fenxiang">晒网购</a></li>
							<li class="divider"></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">advertisement <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="advertisement.create.jsp">create</a></li>
									<li><a href="advertisement!list.action">list</a></li>
									<li class="divider"></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">shop <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="shop.create.jsp">create</a></li>
									<li><a href="shop!list.action">list</a></li>
									<li class="divider"></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">BLog <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="blog.create.jsp">create</a></li>
									<li><a href="blog!list.action">list</a></li>
									<li class="divider"></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">catalog <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="catalog.create.jsp">create</a></li>
									<li><a href="catalog!list.action">list</a></li>
									<li class="divider"></li>
								</ul></li>
						</ul>


					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>

		</div>
 
	</div>

</div>