<%@page import="com.service.KeywordService"%>
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
 
			<%
				List<KeywordEntity> list = new KeywordService().list();;
				
				if(list==null){
					 list = new ArrayList<KeywordEntity>();
				}
				KeywordEntity entity = new KeywordEntity();
				if( request.getAttribute("entity")!=null ){
					 entity = (KeywordEntity)request.getAttribute("entity");
				}
				
				for(KeywordEntity e : list){
					
				%>
					 
					 
					 <div class="thumbnail col-md-2">
	       				<a href="commodity!search.action?keyword=<%= e.getKeyword()%>" target="_blank">  
							<%= e.getKeyword() %>
					 	</a> 
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
