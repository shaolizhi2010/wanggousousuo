<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.entity.CommodityEntity"%>

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
						CommodityEntity entity = (CommodityEntity)request.getAttribute("entity");;
					
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
							url
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getUrl() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							price
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getPrice() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							imgUrl
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getImgUrl() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							commentCount
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getCommentCount() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							commentUrl
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getCommentUrl() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							source
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getSource() %>
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
