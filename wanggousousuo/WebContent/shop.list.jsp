<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.ShopEntity"%>
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
							shopNameEn
						</th>						<th>
							shopNameCn
						</th>						<th>
							domainName
						</th>						<th>
							shopAvailable
						</th>						<th>
							searchUrl
						</th>						<th>
							searchAvailable
						</th>						<th>
							charsetForUrl
						</th>						<th>
							charsetForContent
						</th>						<th>
							index
						</th>						<th>
							logoImg
						</th>						<th>
							orderNumber
						</th>
						 <th>修改</th>
						 <th>删除</th>
					</tr>
				</thead>
				<tbody>
			<%
				List<ShopEntity> list = new ArrayList<ShopEntity>();
				if(request.getAttribute("list")!=null){
					 list = (List<ShopEntity>)request.getAttribute("list");
				}
				ShopEntity entity = new ShopEntity();
				if( request.getAttribute("entity")!=null ){
					 entity = (ShopEntity)request.getAttribute("entity");
				}
				
				for(ShopEntity e : list){
					
			%>
				<tr>
											<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getShopNameEn() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getShopNameCn() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getDomainName() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getShopAvailable() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getSearchUrl() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getSearchAvailable() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getCharsetForUrl() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getCharsetForContent() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getIndex() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getLogoImg() %></a>
							
						</td>
						<td>
							<a href='shop!view.action?id=<%=e.getId()%>'><%=e.getOrderNumber() %></a>
							
						</td>

					<td><a href='shop!update.action?id=<%=e.getId()%>'>修改</a></td>
					<td><a href='shop!delete.action?id=<%=e.getId()%>'>删除</a></td>
					
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
