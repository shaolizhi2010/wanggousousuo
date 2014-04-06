
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
<jsp:include page="/part/head-meta.jsp" />

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/search.css" type="text/css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>



<script>
	$(document).ready(
			function() {
				

				$(window).scroll(function() {

					if ($(document).scrollTop() > 100) {
						$('#go-top').css('display', 'block');
					} else {
						$('#go-top').css('display', 'none');
					}
				});

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
	<jsp:include page="/part/hat.jsp" />
	<jsp:include page="/part/header.jsp" />
	<div id="main"></div>
	<!-- end div main -->

	<div id="view-history"></div>


	<div class="floater-x"></div>

	<jsp:include page="/part/footer3.jsp" />
</body>

</html>