<%@page import="com.entity.ShopEntity"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="part/head.jsp" />
</head>


<body>

    <jsp:include page="part/hat.jsp" />
<%

ShopEntity entity = new ShopEntity();
if( request.getAttribute("entity")!=null ){
	 entity = (ShopEntity)request.getAttribute("entity");
}

%>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">

                				<h3> shop </h3> 
               <form role="form" action="shop!update.action" method="POST">
				
								<div class="form-group">
					 <label for="id">id</label>
					 <input class="form-control" name="id" id="id" type="text" value="<%=entity.getId()%>" />
				</div>
				<div class="form-group">
					 <label for="shopNameEn">shopNameEn</label>
					 <input class="form-control" name="shopNameEn" id="shopNameEn" type="text" value="<%=entity.getShopNameEn()%>" />
				</div>
				<div class="form-group">
					 <label for="shopNameCn">shopNameCn</label>
					 <input class="form-control" name="shopNameCn" id="shopNameCn" type="text" value="<%=entity.getShopNameCn()%>" />
				</div>
				<div class="form-group">
					 <label for="domainName">domainName</label>
					 <input class="form-control" name="domainName" id="domainName" type="text" value="<%=entity.getDomainName()%>" />
				</div>
				<div class="form-group">
					 <label for="searchUrl">searchUrl</label>
					 <input class="form-control" name="searchUrl" id="searchUrl" type="text" value="<%=entity.getSearchUrl()%>" />
				</div>
				<div class="form-group">
					 <label for="charsetForUrl">charsetForUrl</label>
					 <input class="form-control" name="charsetForUrl" id="charsetForUrl" type="text" value="<%=entity.getCharsetForUrl()%>" />
				</div>
				<div class="form-group">
					 <label for="charsetForContent">charsetForContent</label>
					 <input class="form-control" name="charsetForContent" id="charsetForContent" type="text" value="<%=entity.getCharsetForContent()%>" />
				</div>
 
 
				<div class="form-group">
					 <label for="index">index</label>
					 <input class="form-control" name="index" id="index" type="text" value="<%=entity.getIndex()%>" />
				</div>
				<div class="form-group">
					 <label for="logoImg">logoImg</label>
					 <input class="form-control" name="logoImg" id="logoImg" type="text" value="<%=entity.getLogoImg()%>" />
				</div>
				<div class="form-group">
					 <label for="orderNumber">orderNumber</label>
					 <input class="form-control" name="orderNumber" id="orderNumber" type="text" value="<%=entity.getOrderNumber()%>" />
				</div>

				
				<button type="submit" class="btn btn-default">完成</button>
			</form>


            </div>

 
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
