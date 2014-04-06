<%@page import="com.service.SearchHistoryService"%>
<%@page import="com.entity.SearchHistoryEntity"%>
<%@page import="com.file.FileUtil"%>
<%@page import="com.mutiServer.MutiServerUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.bean.logic.KeywordService"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<jsp:include page="part/head-meta.jsp" />

<link rel="stylesheet" href="css/common.css" type="text/css"/>
<style type="text/css">
    #main{
		width: 1050px; 
		height: 600px;
		margin-top:50px;
		margin-left:auto; margin-right:auto;
		border-left:1px dotted #B3B3B3;
		border-right:1px dotted #B3B3B3;
		
    }
    
     #main a{
        /*width:104px;*/
        height:20px;
        display:block;
        color:#5E5E5E;
       
        font-size: 12px;
        font-family:Arial;
        text-decoration:none;
        line-height:30px;
        text-align:center;
        white-space:nowrap;
        /*border-right:1px dotted #B3B3B3;*/
        float: left;
     }
     
      #main a:hover{
      	color: red ;
      }
      
      #pages{
      	width: 1050px;
      	height:30px;
      	margin-top: 100px;
      	margin-left:auto; margin-right:auto;
      	
      	text-align: center;
      }
      #pages a{
      	font-size: 16px;
      	margin-left: 10px; 
      	text-decoration: none;
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

	<div id="main">
		
		<% 
			List<SearchHistoryEntity> list = new  SearchHistoryService().list();
			for(SearchHistoryEntity e : list){
		%>
			<div style="width: 300px;float: left; "> <a href="static-search2.jsp?shopname=<%=e.getShopname() %>&keyword=<%=e.getKeyword() %>">在  <%=e.getShopname() %> 搜索 <%=e.getKeyword() %> </a> </div>
		<%
			}
		%>
		
	</div>
 
	<div class="floater-x"></div>
	
<jsp:include page="part/footer3.jsp" />

</body>
</html>