<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="part/auth.jsp"  %>

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

                				<h3> shop </h3> 
               <form role="form" action="shop!create.action" method="POST">
				
								<div class="form-group">
					 <label for="shopNameEn">shopNameEn</label>
					 <input class="form-control" name="shopNameEn" id="shopNameEn" type="text" />
				</div>
				<div class="form-group">
					 <label for="shopNameCn">shopNameCn</label>
					 <input class="form-control" name="shopNameCn" id="shopNameCn" type="text" />
				</div>
				<div class="form-group">
					 <label for="domainName">domainName</label>
					 <input class="form-control" name="domainName" id="domainName" type="text" />
				</div>
				<div class="form-group">
					 <label for="shopAvailable">shopAvailable</label>
					 <input class="form-control" name="shopAvailable" id="shopAvailable" type="text" />
				</div>
				<div class="form-group">
					 <label for="searchUrl">searchUrl</label>
					 <input class="form-control" name="searchUrl" id="searchUrl" type="text" />
				</div>
				<div class="form-group">
					 <label for="searchAvailable">searchAvailable</label>
					 <input class="form-control" name="searchAvailable" id="searchAvailable" type="text" />
				</div>
				<div class="form-group">
					 <label for="charsetForUrl">charsetForUrl</label>
					 <input class="form-control" name="charsetForUrl" id="charsetForUrl" type="text" />
				</div>
				<div class="form-group">
					 <label for="charsetForContent">charsetForContent</label>
					 <input class="form-control" name="charsetForContent" id="charsetForContent" type="text" />
				</div>
				<div class="form-group">
					 <label for="index">index</label>
					 <input class="form-control" name="index" id="index" type="text" />
				</div>
				<div class="form-group">
					 <label for="logoImg">logoImg</label>
					 <input class="form-control" name="logoImg" id="logoImg" type="text" />
				</div>
				<div class="form-group">
					 <label for="orderNumber">orderNumber</label>
					 <input class="form-control" name="orderNumber" id="orderNumber" type="text" />
				</div>

				
				<button type="submit" class="btn btn-default">完成</button>
			</form>


            </div>

 
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
