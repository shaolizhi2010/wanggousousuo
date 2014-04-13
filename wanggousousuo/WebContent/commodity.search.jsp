<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.CommodityEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="part/head.jsp" />

<%
CommodityEntity entity = new CommodityEntity();
if(entity!=null){
	entity = (CommodityEntity)request.getAttribute("entity");
}

List<CommodityEntity> list = (List<CommodityEntity>)request.getAttribute("list");
if(list==null){
	list = new ArrayList<CommodityEntity>();
}
%>

<script>

$( document ).ready(function() {
	$("#keyword").val("<%=entity.getKeyword()%>");
	
});
</script>
</head>



<body>

    <jsp:include page="part/hat.jsp" />

    <div class="container">
        <div class="row clearfix">
             

<div class="col-md-3" id="col-1">
					
					<%
						for(int i=0; i<list.size();i++){
							if(i%4 !=0){
								continue;	
							}
							CommodityEntity e = (CommodityEntity)list.get(i);
							%>
								<div class="thumbnail">
									<a><img alt="<%=e.getName() %>"  target="_blank" href="<%=e.getUrl()%>" src="<%=e.getImgUrl() %>" /></a>
									<div class="caption">
 
										<p><a class="c-title" target="_blank" href="<%=e.getUrl()%>"><%=e.getName() %></a></p>
										<p>
											<span class="c-price" >￥<%=e.getPrice() %></span>
											<a  class="pull-right c-source" target="_blank" href="<%=e.getUrl()%>"><%=e.getSource() %></a>
											<a  class="pull-right c-comment" target="_blank" href="<%=e.getUrl()%>">评论(<%=e.getCommentCount() %>)|</a> 
											  
										</p>
									</div>
								</div>							
							
							<%
						}
					%>
					
				</div>
				
				<div class="col-md-3">
					<%
						for(int i=0; i<list.size();i++){
							if(i%4 !=1){
								continue;	
							}
							CommodityEntity e = (CommodityEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=e.getImgUrl() %>" />
								<div class="caption">
 
										<p><%=e.getName() %></p>
										<p>
											<a class="btn" target="_blank" href="<%=e.getUrl()%>">详细信息</a> 
										</p>
									</div>
								</div>							
							
							<%
						}
					%>
				</div>
				<div class="col-md-3">
					<%
						for(int i=0; i<list.size();i++){
							if(i%4 !=2){ 
								continue;	
							}
							CommodityEntity e = (CommodityEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=e.getImgUrl() %>" />
									<div class="caption">
 
										<p><%=e.getName() %></p>
										<p>
											<a  target="_blank" href="<%=e.getUrl()%>">详细信息</a> 
										</p>
									</div>
								</div>							
							
							<%
						}
					%>					 
				</div>
				<div class="col-md-3">
					<%
						for(int i=0; i<list.size();i++){
							if(i%4 !=3){ 
								continue;	
							}
							CommodityEntity e = (CommodityEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=e.getImgUrl() %>" />
									<div class="caption">
 
										<p><%=e.getName() %></p>
										<p>
											<a  target="_blank" href="<%=e.getUrl()%>">详细信息</a> 
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
