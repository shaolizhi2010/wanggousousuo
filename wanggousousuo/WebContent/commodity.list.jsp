<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.CommodityEntity"%>
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
							url
						</th>						<th>
							price
						</th>						<th>
							imgUrl
						</th>						<th>
							commentCount
						</th>						<th>
							commentUrl
						</th>						<th>
							source
						</th>						<th>
							keyword
						</th>
						 <th>修改</th>
						 <th>删除</th>
					</tr>
				</thead>
				<tbody>
			<%
				
				List<CommodityEntity> list = (List<CommodityEntity>)request.getAttribute("list");
				if(list==null){
					list = new ArrayList<CommodityEntity>();
				}
				
				for(CommodityEntity e : list){
					
			%>
				<tr>
											<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getName() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getUrl() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getPrice() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getImgUrl() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getCommentCount() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getCommentUrl() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getSource() %></a>
							
						</td>
						<td>
							<a href='commodity!view.action?id=<%=e.getId()%>'><%=e.getKeyword() %></a>
							
						</td>

					<td><a href='commodity!update.action?id=<%=e.getId()%>'>修改</a></td>
					<td><a href='commodity!delete.action?id=<%=e.getId()%>'>删除</a></td>
					
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
