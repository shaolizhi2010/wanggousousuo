<%@page import="com.entity.AdvertisementEntity"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="part/head.jsp" />
</head>


<body>

    <jsp:include page="part/hat.jsp" />
<%

AdvertisementEntity entity = new AdvertisementEntity();
if( request.getAttribute("entity")!=null ){
	 entity = (AdvertisementEntity)request.getAttribute("entity");
}

%>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">

                				<h3> advertisement </h3> 
               <form role="form" action="advertisement!update.action" method="POST">
				
								<div class="form-group">
					 <label for="id">id</label>
					 <input class="form-control" name="id" id="id" type="text" value="<%=entity.getId()%>" />
				</div>
				<div class="form-group">
					 <label for="url">url</label>
					 <input class="form-control" name="url" id="url" type="text" value="<%=entity.getUrl()%>" />
				</div>
				<div class="form-group">
					 <label for="imgUrl">imgUrl</label>
					 <input class="form-control" name="imgUrl" id="imgUrl" type="text" value="<%=entity.getImgUrl()%>" />
				</div>
				<div class="form-group">
					 <label for="name">name</label>
					 <input class="form-control" name="name" id="name" type="text" value="<%=entity.getName()%>" />
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
