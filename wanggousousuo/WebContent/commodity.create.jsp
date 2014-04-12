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

                				<h3> commodity </h3> 
               <form role="form" action="commodity!create.action" method="POST">
				
								<div class="form-group">
					 <label for="name">name</label>
					 <input class="form-control" name="name" id="name" type="text" />
				</div>
				<div class="form-group">
					 <label for="url">url</label>
					 <input class="form-control" name="url" id="url" type="text" />
				</div>
				<div class="form-group">
					 <label for="price">price</label>
					 <input class="form-control" name="price" id="price" type="text" />
				</div>
				<div class="form-group">
					 <label for="imgUrl">imgUrl</label>
					 <input class="form-control" name="imgUrl" id="imgUrl" type="text" />
				</div>
				<div class="form-group">
					 <label for="commentCount">commentCount</label>
					 <input class="form-control" name="commentCount" id="commentCount" type="text" />
				</div>
				<div class="form-group">
					 <label for="commentUrl">commentUrl</label>
					 <input class="form-control" name="commentUrl" id="commentUrl" type="text" />
				</div>
				<div class="form-group">
					 <label for="source">source</label>
					 <input class="form-control" name="source" id="source" type="text" />
				</div>
				<div class="form-group">
					 <label for="keyword">keyword</label>
					 <input class="form-control" name="keyword" id="keyword" type="text" />
				</div>

				
				<button type="submit" class="btn btn-default">完成</button>
			</form>


            </div>

 
        </div>
    </div>
    <jsp:include page="part/footer.jsp" />
</body>
</html>
