<%@page import="com.service.AdvertisementService"%>
<%@page import="com.entity.AdvertisementEntity"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.service.CommodityService"%>
<%@page import="com.entity.CommodityEntity"%>
<%@page import="com.service.CatalogService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.CatalogEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.utils.C"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/head.jsp" />
<script>
	$(document).ready(function() {

		
		$('#c-ad img').each(
			function() {
				var w = $("#c-ad").width();
				$(this).height(w / 4);
				$(this).width(w /3);
			}
		);

		//$('.carousel-inner-img').width(w);

		$('.c-square').each(function() {
			var w = $(this).width();
			$(this).height(w);
			$(this).width(w);
		});
		/*
		$('.c-half-square').each(function() {
			var w = $(this).width();
			$(this).height(w*3/5);
			$(this).width(w);
		});		
		 */

	});
</script>
</head>

<body>
	<jsp:include page="part/hat.jsp" />

	<div class="container">

		<div class="row clearfix">


			<div class="col-md-12" id="c-ad">

				<h4>最新打折促销信息</h4>
				<%
					AdvertisementService adService = new AdvertisementService();
					List<AdvertisementEntity> adlist   = adService.list(0, 3);
					for(AdvertisementEntity ad : adlist){
	        			%>
        				<div class="thumbnail col-md-4">
        				<a href="<%= ad.getUrl()%>" target="_blank">  
							<img  src="<%= ad.getImgUrl() %>" class="img-response" />
					 	</a> 
					 	</div>
        			<%
					}
				%>

			</div>

		</div>


		<br />



		<%
			List<CatalogEntity> catalogList = new CatalogService().list();
			if (catalogList == null) {
				catalogList = new ArrayList<CatalogEntity>();
			}

			for (CatalogEntity e : catalogList) {
		%>

		<div class="row clearfix">
			<div class="col-md-12">
				<h4><%=e.getName()%></h4>
			</div>
		</div>
		<div class="row clearfix">

			<%
				CommodityEntity query = new CommodityEntity();
					query.setKeyword(e.getKeyword());
					List<CommodityEntity> commodityList = new CommodityService()
							.list(query, 0,6); 
					if (commodityList == null) {
						commodityList = new ArrayList<CommodityEntity>();
					}

					for (CommodityEntity commodity : commodityList) {
			%>

			<div class="col-md-2" style="padding: 5px;">

				<div class="thumbnail">
					<a target="_blank" href="<%=commodity.getUrl()%>"><img
						class="c-square" alt="<%=commodity.getName()%>"
						src="<%=commodity.getImgUrl()%>" /></a>
					<div class="caption">

						<p>
							<a class="pull-left c-title"
								style="color: #666; font-size: 12px; margin-bottom: 15px;"
								target="_blank" href="<%=commodity.getUrl()%>"><%=StringUtils.substring(commodity.getName(), 0, 15)
							+ ".."%></a>
						</p>
						<p>
							<span class="c-price">￥<%=commodity.getPrice()%></span> <a
								class="pull-right c-source" target="_blank"
								href="<%=commodity.getUrl()%>"><%=commodity.getSource()%></a> <a
								class="pull-right c-comment" target="_blank"
								href="<%=commodity.getUrl()%>">评论(<%=commodity.getCommentCount()%>)|
							</a>

						</p>
					</div>
				</div>


			</div>
			<%
				} //end for commodity list
			%>
		</div>

		<%
			} // end for catalog list
		%>
	</div>




	<jsp:include page="part/footer.jsp" />
</body>
</html>