
<%@page import="com.entity.SearchHistoryEntity"%>
<%@page import="com.service.SearchHistoryService"%>
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


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta-sub.jsp" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/search.css" type="text/css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<jsp:include page="js/search.js.jsp" />
<script src="<%=request.getContextPath()%>/js/taobaoref.js"></script>

<script>

var C_productsPerPage = 40;//const :product max count per page

var allProduct = [];
var shop_condition='none';
var page_condition='1';

$( document ).ready(function() {
	

				//onclick search
				$("#submit-search-btn").on(
						"click",
						function() {
							window.location.href = 'search.jsp?keyword='
									+ $("#keyword").val(); // 跳转
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
	
	String keyword = request.getParameter("keyword");
	if (keyword == null) {
		keyword = "";
	}
	keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
	
	String shopname = request.getParameter("shopname");
	if (shopname == null) {
		shopname = "";
	}
	shopname = new String(shopname.getBytes("ISO-8859-1"), "UTF-8");
	 
	  SearchHistoryEntity e = new  SearchHistoryService().get(shopname, keyword);
 		out.print(e.getContent());
	%>



	<div class="floater-x"></div>

	<jsp:include page="part/footer3.jsp" />

</body>
</html>