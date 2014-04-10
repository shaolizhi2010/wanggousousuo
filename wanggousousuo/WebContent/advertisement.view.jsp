<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.entity.AdvertisementEntity"%>

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
						AdvertisementEntity entity = (AdvertisementEntity)request.getAttribute("entity");;
					
				%>
				
						            <div class="row clearfix">
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
