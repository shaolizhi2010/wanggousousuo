<%@page import="org.apache.commons.lang3.StringUtils"%>
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
				
				String start = request.getParameter("start");
				if(StringUtils.isBlank(start)){
					start = "0";
				}
				int startInt = Integer.parseInt(start);
				
				int countPerPage = 120; 
				
				KeywordService service = new KeywordService();
				List<KeywordEntity> list = service.list(startInt,countPerPage);;
				
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
	       				<a href="commodity!search.action?keyword=<%= e.getKeyword()%>" target="_blank" >  
							<span style="text-align: center;" ><%= e.getKeyword() %></span>
					 	</a> 
				 	</div> 
				<%		
						
					}
					
				%>


            </div>

 
        </div>
        
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="pagination">
 
 			<%
			long count = service.count();
 			
 			
 			for(long i=0; i*countPerPage < count;i++){
				%>
 				
 				<li>
					<a href="keyword.list.hot.jsp?start=<%=i*countPerPage%>"><%=i+1 %></a>
				</li>
				
				<%
 			}
 			
 			%>
 
 
			</ul>
		</div>
	</div>
        
    </div>
    
    <jsp:include page="part/footer.jsp" />
</body>
</html>
