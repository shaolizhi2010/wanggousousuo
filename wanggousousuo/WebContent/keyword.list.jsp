<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.KeywordEntity"%>
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
							keyword
						</th>						<th>
							times
						</th>						<th>
							lasttime
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
				List<KeywordEntity> list = new ArrayList<KeywordEntity>();
				if(request.getAttribute("list")!=null){
					 list = (List<KeywordEntity>)request.getAttribute("list");
				}
				KeywordEntity entity = new KeywordEntity();
				if( request.getAttribute("entity")!=null ){
					 entity = (KeywordEntity)request.getAttribute("entity");
				}
				
				for(KeywordEntity e : list){
					
			%>
				<tr>
											<td>
							<a href='keyword!view.action?id=<%=e.getId()%>'><%=e.getKeyword() %></a>
							
						</td>
						<td>
							<a href='keyword!view.action?id=<%=e.getId()%>'><%=e.getTimes() %></a>
							
						</td>
						<td>
							<a href='keyword!view.action?id=<%=e.getId()%>'><%=e.getLasttime() %></a>
							
						</td>
						<td>
							<a href='keyword!view.action?id=<%=e.getId()%>'><%=e.getOrderNumber() %></a>
							
						</td>
						<td>
							<a href='keyword!view.action?id=<%=e.getId()%>'><%=e.getUseful() %></a>
							
						</td>
						<td>
							<a href='keyword!view.action?id=<%=e.getId()%>'><%=e.getDescription() %></a>
							
						</td>

					<td><a href='keyword!preUpdate.action?id=<%=e.getId()%>'>修改</a></td>
					<td><a href='keyword!delete.action?id=<%=e.getId()%>'>删除</a></td>
					
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
