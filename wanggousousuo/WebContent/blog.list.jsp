<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.bson.types.ObjectId"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.BlogEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="part/auth.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="part/head.jsp" />
</head>


<body>

    <jsp:include page="part/hat.jsp" />

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
				
			<%
				List<BlogEntity> list = new ArrayList<BlogEntity>();
				if(request.getAttribute("list")!=null){
					 list = (List<BlogEntity>)request.getAttribute("list");
				}
				BlogEntity entity = new BlogEntity();
				if( request.getAttribute("entity")!=null ){
					 entity = (BlogEntity)request.getAttribute("entity");
				}
				
				for(BlogEntity e : list){
					
			%>
 
				<div class="row" style="margin-bottom: 20px;5">
	 				<div class="col-md-6 col-md-push-1 highlight text-left">
					 
						<div class="jumbotron" > 
							 <div style="margin-bottom: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;<%=e.getContent() %></div> 
							 <small > <%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format( new ObjectId(e.getId()).getDate() )   %></small>
						</div> 
					</div > 
				</div>
			 
			<%
				}
			%>
            </div>
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
