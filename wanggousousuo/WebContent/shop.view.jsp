<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.entity.ShopEntity"%>

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
					if(request.getAttribute("entity") == null){
						
					}
					else{
						ShopEntity entity = (ShopEntity)request.getAttribute("entity");;
					
				%>
				
						            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							shopNameEn
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getShopNameEn() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							shopNameCn
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getShopNameCn() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							domainName
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getDomainName() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							shopAvailable
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getShopAvailable() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							searchUrl
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getSearchUrl() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							searchAvailable
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getSearchAvailable() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							charsetForUrl
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getCharsetForUrl() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							charsetForContent
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getCharsetForContent() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							index
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getIndex() %>
						</p>
					</div>
				</div>	            <div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							logoImg
						</p>
					</div>
					<div class="col-md-6 column">
						<p>
							<%=entity.getLogoImg() %>
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
