<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.CatalogEntity"%>
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
							name
						</th>						<th>
							keyword
						</th>						<th>
							orderNumber
						</th>						<th>
							useful
						</th>						<th>
							description
						</th>
						 <th>修改</th>
						 <th>删除</th>
					</tr>
				</thead>
				<tbody>
			<%
				List<CatalogEntity> list = new ArrayList<CatalogEntity>();
				if(request.getAttribute("list")!=null){
					 list = (List<CatalogEntity>)request.getAttribute("list");
				}
				CatalogEntity entity = new CatalogEntity();
				if( request.getAttribute("entity")!=null ){
					 entity = (CatalogEntity)request.getAttribute("entity");
				}
				
				for(CatalogEntity e : list){
					
			%>
				<tr>
											<td>
							<a href='catalog!view.action?id=<%=e.getId()%>'><%=e.getName() %></a>
							
						</td>
						<td>
							<a href='catalog!view.action?id=<%=e.getId()%>'><%=e.getKeyword() %></a>
							
						</td>
						<td>
							<a href='catalog!view.action?id=<%=e.getId()%>'><%=e.getOrderNumber() %></a>
							
						</td>
						<td>
							<a href='catalog!view.action?id=<%=e.getId()%>'><%=e.getUseful() %></a>
							
						</td>
						<td>
							<a href='catalog!view.action?id=<%=e.getId()%>'><%=e.getDescription() %></a>
							
						</td>

					<td><a href='catalog!preUpdate.action?id=<%=e.getId()%>'>修改</a></td>
					<td><a href='catalog!delete.action?id=<%=e.getId()%>'>删除</a></td>
					
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
