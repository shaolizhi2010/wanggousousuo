
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.digger.vo.Product"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.utils.ShopNames"%>
<%@page import="com.utils.C"%>
<%@page import="java.util.*"%>
<%@page import="com.shop.ShopInfo"%>
<%@page import="com.env.StaticInfo"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.bean.logic.Cache"%>
 

<%
	String keyword = (String)request.getAttribute("keyword");
	if (keyword == null) {
		keyword = "";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
 <jsp:include page="part/head-meta-sub.jsp" />

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/search.css" type="text/css"/>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<jsp:include page="js/search.js.jsp" /> 
<script src="<%=request.getContextPath() %>/js/taobaoref.js"></script>

<script>

var C_productsPerPage = 40;//const :product max count per page

var allProduct = [];
var shop_condition='none';
var page_condition='1';

$( document ).ready(function() {
	
	//searchAll('<%=keyword%>' );
	
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
				
}); //end document ready

	 

</script>


</head>

<body>
<jsp:include page="part/hat.jsp" />
 <jsp:include page="part/header.jsp" />

	<div id="main">
 		<div id="left" class="searchresult-left">
 		</div>
		<div id="right" class="searchresult-right">
 
			<div id="search_result"></div>

			<div id="search_item_template" >
 
 
<%
Map<String, ShopInfo> shops = StaticInfo.getShops();
for(Entry<String, ShopInfo> shop : shops.entrySet()){
	String json = (String)request.getAttribute(shop.getValue().getShopName());
	if(StringUtils.isBlank(json)){
		continue;
	}
	
	List<Product> list = new Gson().fromJson(json, new TypeToken<List<Product>>(){}.getType());
	
	for(Product p : list){
		
		%>
			<div class="search_item" style="display: display;">
					<div class="item_pic">
						<a target="_blank" href="<%=p.getUrl()%>"><img alt="<%=p.getName() %>" src="<%=p.getImgUrl()%>"/></a>
					</div>
					<div class="item_title">
						<a target="_blank" href="<%=p.getUrl()%>"><%=p.getName()%></a>
					</div>
					<div class="item_price">
						￥<span><%=p.getPrice()%></span>
					</div>
					<div class="item_comment">
						<a target="_blank" href="<%=p.getCommentUrl()%>"><span><%=p.getCommentCount()%></span><font size="1"> ( 销量/评论)</font></a>
					</div>
					<div class="item_source">
						<a target="_blank" href='<%=p.getUrl()%>'> <%=p.getSource()%> </a>
					</div>
				</div>		
<%
	}
}
%>
 
 
			</div>
			
			<div class='line_holder'></div>

		</div>
		<!-- end right -->
	</div>
	<!-- end div main -->
 
	<div id="pages">
		<% 
			for(int p=1;p<=10;p++){
				out.print("<a href='"+request.getContextPath()+"/map-"+ p +".htm'> "+p+"  </a>");	
			}
		%>
	</div>


	
	<div class="floater-x"></div>
	<div class="floater-x"></div>
	
 <jsp:include page="part/footer3.jsp" />
	
</body>
</html>