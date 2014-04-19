<%@page import="com.service.AdvertisementService"%>
<%@page import="com.dao.AdvertisementDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.AdvertisementEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	List<AdvertisementEntity> list = new AdvertisementService().list();
	if(list == null){
		list = new ArrayList<AdvertisementEntity>();		
	}
	
%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="part/head-meta.jsp" />

<jsp:include page="part/head.jsp" />
 
<script>

$(function(){
	 var $container = $('.thumbnails');
	 $container.imagesLoaded( function(){
		 $container.masonry({
		 	itemSelector : '.col-md-4'
		 });
	 });
	});

</script>

</head>


<body>

<jsp:include page="part/hat.jsp" /> 
 
<div class="container">
	<div class="row clearfix">
				<div class="col-md-4" id="col-1">
					
					<%
						for(int i=0; i<list.size();i++){
							if(i%3 !=0){
								continue;	
							}
							AdvertisementEntity entity = (AdvertisementEntity)list.get(i);
							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=entity.getImgUrl() %>" />
									<div class="caption">
										<h3>
											<%=entity.getName() %>
										</h3>
										 <p><%=entity.getDescription() %></p>
										<p>
											<a class="btn btn-success" target="_blank" href="<%=entity.getUrl()%>">详细信息</a> 
										</p>
									</div>
								</div>							
							
							<%
						}
					%>
					
				</div>
				
				<div class="col-md-4">
					<%
						for(int i=0; i<list.size();i++){
							if(i%3 !=1){
								continue;	
							}
							AdvertisementEntity entity = (AdvertisementEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=entity.getImgUrl() %>" />
									<div class="caption">
										<h3>
											<%=entity.getName() %>
										</h3>
										 <p><%=entity.getDescription() %></p>
										<p>
											<a class="btn btn-success" target="_blank" href="<%=entity.getUrl()%>">详细信息</a> 
										</p>
									</div>
								</div>							
							
							<%
						}
					%>
				</div>
				<div class="col-md-4">
					<%
						for(int i=0; i<list.size();i++){
							if(i%3 !=2){ 
								continue;	
							}
							AdvertisementEntity entity = (AdvertisementEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=entity.getImgUrl() %>" />
									<div class="caption">
										<h3>
											<%=entity.getName() %>
										</h3>
										 <p><%=entity.getDescription() %></p>
										<p>
											<a class="btn btn-success" target="_blank" href="<%=entity.getUrl()%>">详细信息</a> 
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
