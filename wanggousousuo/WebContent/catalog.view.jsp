<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.entity.CatalogEntity"%>

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
						CatalogEntity entity = (CatalogEntity)request.getAttribute("entity");;
					
				%>
				
						            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							name
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getName() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							keyword
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getKeyword() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							orderNumber
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getOrderNumber() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							useful
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getUseful() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							description
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getDescription() %>
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
