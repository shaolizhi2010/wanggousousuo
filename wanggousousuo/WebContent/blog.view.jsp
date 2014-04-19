<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.entity.BlogEntity"%>

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
					if(request.getAttribute("entity") == null){
						
					}
					else{
						BlogEntity entity = (BlogEntity)request.getAttribute("entity");;
					
				%>
				
						            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							title
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getTitle() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							content
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getContent() %>
						</p>
					</div>
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
