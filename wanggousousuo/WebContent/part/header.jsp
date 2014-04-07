<%@ page language="java" pageEncoding="UTF-8"%>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12">
			
			<div class="col-md-4" >
				<a  href="<%=request.getContextPath()%>/index.jsp">
					<img class="img-responsive"  alt="logo" src="<%=request.getContextPath()%>/img/logo-wg.png"/>
				</a>
			</div>
			<div class="col-md-4" >
				<input class="form-control" style="height:50px;" type="text" placeholder="Default input"  id="keyword" value="">
			</div>
			<div class="col-md-4" >
				<button class="btn btn-default" style="height:50px;" type="button">搜索</button>
			</div>
			
		</div>
	</div>
</div>

<!-- end div header -->
