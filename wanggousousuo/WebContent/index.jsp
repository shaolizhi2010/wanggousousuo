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

		var w = $('#myCarousel').width();
		$('#myCarousel img').each(function() {
			var w = $("#myCarousel").width();
			$(this).height(w * 3 / 4);
			$(this).width(w);
		}

		);

		//$('.carousel-inner-img').width(w);

		$('#myCarousel').carousel({
			interval : 15000
		});

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
			<div class="col-md-7">

				<table class="table">
					<tr>
						<td><a href="http://z.cn/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/amazon.png" class="img-response" /></a></td>
						<td><a href="http://www.dangdang.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/dangdang.png" class="img-response" /></a></td>
						<td><a href="http://www.elong.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/elong.gif" class="img-response" /></a></td>
						<td><a href="http://www.vancl.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/fanke.png" class="img-response" /></a></td>
					</tr>
					<tr>
						<td><a href="http://www.vmall.com/‎" target="_blank"><img style="width:68px;height:30px;" src="img/logos/huawei.gif" class="img-response" /></a></td>
						<td><a href="http://www.jd.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/jingdong.png" class="img-response" /></a></td>
						<td><a href="http://www.lefeng.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/lefeng.jpg" class="img-response" /></a></td>
						<td><a href="http://www.m18.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/m18.png" class="img-response" /></a></td>
					</tr>
					<tr>
						<td><a href="http://moonbasa.com/" target="_blank"><img style="width:68px;height:30px;" src="img/logos/moonbasa.gif" class="img-response" /></a></td>
						<td><a href="http://www.newegg.cn/‎" target="_blank"><img style="width:68px;height:30px;" src="img/logos/newegg.png" class="img-response" /></a></td>
						<td><a href="http://okbuy.com" target="_blank"><img style="width:68px;height:30px;" src="img/logos/okbuy.png" class="img-response" /></a></td>
						<td><a href="http://www.paipai.com" target="_blank"><img style="width:68px;height:30px;" src="img/logos/paipai.png" class="img-response" /></a></td>
					</tr>
					<tr>
						<td><a href="http://taobao.com" target="_blank"><img style="width:68px;height:30px;" src="img/logos/taobao.png" class="img-response" /></a></td>
						<td><a href="http://www.xtep.com.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/tebu.gif" class="img-response" /></a></td>
						<td><a href="http://www.tmall.com" target="_blank"><img style="width:68px;height:30px;" src="img/logos/tmall.png" class="img-response" /></a></td>
						<td><a href="https://www.vjia.com/‎" target="_blank"><img style="width:68px;height:30px;" src="img/logos/vijia.gif" class="img-response" /></a></td>
					</tr>
					<tr>
						<td><a href="http://www.vip.com/‎" target="_blank"><img style="width:68px;height:30px;" src="img/logos/vipshop.png" class="img-response" /></a></td>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/womai.gif" class="img-response" /></a></td>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/xiecheng.gif" class="img-response" /></a></td>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/yihaodian.gif" class="img-response" /></a></td>
					</tr>
					<tr>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/yixun.png" class="img-response" /></a></td>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/amazon.png" class="img-response" /></a></td>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/dangdang.png" class="img-response" /></a></td>
						<td><a href="http://z.cn" target="_blank"><img style="width:68px;height:30px;" src="img/logos/dangdang.png" class="img-response" /></a></td>
					</tr>
				</table>


			</div>

			<!-- 
			
			<div class="col-md-3">
			
				 <table class="table">
					<tr><td><img src="img/logos/58tuan.png" class="img-response" /></td><td><img src="img/logos/amazon.png" class="img-response" /></td>  </tr>
					<tr><td><img src="img/logos/58tuan.png" class="img-response" /></td><td><img src="img/logos/amazon.png" class="img-response" /></td>  </tr>
					<tr><td><img src="img/logos/58tuan.png" class="img-response" /></td><td><img src="img/logos/amazon.png" class="img-response" /></td>  </tr>
					<tr><td><img src="img/logos/58tuan.png" class="img-response" /></td><td><img src="img/logos/amazon.png" class="img-response" /></td>  </tr>
					<tr><td><img src="img/logos/58tuan.png" class="img-response" /></td><td><img src="img/logos/amazon.png" class="img-response" /></td>  </tr>
				</table>	

			</div> 
			 -->

			<div class="col-md-5">

				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
						<li data-target="#myCarousel" data-slide-to="3"></li>
						<li data-target="#myCarousel" data-slide-to="4"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner">
					
						<%
						
						List<AdvertisementEntity> advertisementList = new AdvertisementService().list();
						if(advertisementList == null){
							advertisementList = new ArrayList<AdvertisementEntity>();		
						}
						int i=0;
						for(AdvertisementEntity ad : advertisementList){
							if(i>4)break;
							
							%>
							<div class="item <%if(i==0){ %> active <% }%> ">
							<a href="<%=ad.getUrl() %>" target="_blank">
							<img
								src="<%=ad.getImgUrl() %>"
								alt="<%=ad.getName()+ " --- "+ad.getDescription()%>">

							</a>
							</div>
							
						<%
						i++;
						}
						%>
					
						 
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" style="color: black;"></span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" style="color: black;"></span>
					</a>
				</div>


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