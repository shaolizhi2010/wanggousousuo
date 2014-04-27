<%@page import="com.entity.KeywordEntity"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="part/head.jsp" />
</head>


<body>

    <jsp:include page="part/hat.jsp" />
<%

KeywordEntity entity = new KeywordEntity();
if( request.getAttribute("entity")!=null ){
	 entity = (KeywordEntity)request.getAttribute("entity");
}

%>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">

                				<h3> keyword </h3> 
               <form role="form" action="keyword!update.action" method="POST">
				
								<div class="form-group">
					 <label for="keyword">keyword</label>
					 <input class="form-control" name="keyword" id="keyword" type="text" value="<%=entity.getKeyword()%>" />
				</div>
				<div class="form-group">
					 <label for="times">times</label>
					 <input class="form-control" name="times" id="times" type="text" value="<%=entity.getTimes()%>" />
				</div>
				<div class="form-group">
					 <label for="lasttime">lasttime</label>
					 <input class="form-control" name="lasttime" id="lasttime" type="text" value="<%=entity.getLasttime()%>" />
				</div>
				<div class="form-group">
					 <label for="id">id</label>
					 <input class="form-control" name="id" id="id" type="text" value="<%=entity.getId()%>" />
				</div>
				<div class="form-group">
					 <label for="orderNumber">orderNumber</label>
					 <input class="form-control" name="orderNumber" id="orderNumber" type="text" value="<%=entity.getOrderNumber()%>" />
				</div>
				<div class="form-group">
					 <label for="useful">useful</label>
					 <input class="form-control" name="useful" id="useful" type="text" value="<%=entity.getUseful()%>" />
				</div>
				<div class="form-group">
					 <label for="description">description</label>
					 <textarea class="form-control" name="description" id="description" rows="3" ><%=entity.getDescription()%></textarea>
				</div>

				
				<button type="submit" class="btn btn-default">完成</button>
			</form>


            </div>

 
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
