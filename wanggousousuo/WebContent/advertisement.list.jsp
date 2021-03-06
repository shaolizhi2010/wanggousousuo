<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.AdvertisementEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

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
				<table class="table">
				<thead>
					<tr>
						 						<th>
							url
						</th>						<th>
							imgUrl
						</th>						<th>
							name
						</th>						<th>
							description
						</th>
						 <th>修改</th>
						 <th>删除</th>
					</tr>
				</thead>
				<tbody>
			<%
				List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
				if(request.getAttribute("list")!=null){
					 list = (List<AdvertisementEntity>)request.getAttribute("list");
				}
				AdvertisementEntity entity = new AdvertisementEntity();
				if( request.getAttribute("entity")!=null ){
					 entity = (AdvertisementEntity)request.getAttribute("entity");
				}
				
				for(AdvertisementEntity e : list){
					
			%>
				<tr>
											<td>
							<a href='advertisement!view.action?id=<%=e.getId()%>'><%=e.getUrl() %></a>
							
						</td>
						<td>
							<a href='advertisement!view.action?id=<%=e.getId()%>'><%=e.getImgUrl() %></a>
							
						</td>
						<td>
							<a href='advertisement!view.action?id=<%=e.getId()%>'><%=e.getName() %></a>
							
						</td>
						<td>
							<a href='advertisement!view.action?id=<%=e.getId()%>'><%=e.getDescription() %></a>
							
						</td>

					<td><a href='advertisement!preUpdate.action?id=<%=e.getId()%>'>修改</a></td>
					<td><a href='advertisement!delete.action?id=<%=e.getId()%>'>删除</a></td>
					
				</tr>
			<%		
					
				}
				
			%>

				</tbody>

	</table>
            </div>

 
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
