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

                				<h3> blog </h3> 
               <form role="form" action="blog!create.action" method="POST">
				
				<div class="form-group">
					 <textarea class="form-control" name="content" id="content" rows="8"/></textarea>
				</div>

				
				<button type="submit" class="btn btn-default">完成</button>
				
				
			</form>


            </div>

 
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
