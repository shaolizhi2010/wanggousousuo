<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.AdvertisementEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	List<AdvertisementEntity> list = (List<AdvertisementEntity>)request.getAttribute("list");
	if(list == null){
		list = new ArrayList<AdvertisementEntity>();		
	}
	int index = 1; //瀑布流循环计数
	
	AdvertisementEntity e = new AdvertisementEntity();
	e.setImgUrl("http://img3.ddimg.cn/00083/sunliyuan/7851%E9%A6%96%E7%84%A6%E7%AA%84%E7%89%88.jpg");
	e.setDescription("清明踏春季-装备大搜罗_当当网");
	list.add(e);
	
	AdvertisementEntity e2 = new AdvertisementEntity();
	e2.setImgUrl("http://img10.360buyimg.com/da/g13/M0A/02/11/rBEhU1M-EuQIAAAAAADt_b5M8NoAALNPAMDdjUAAO4V188.jpg");
	e2.setDescription("清明踏春季-装备大搜罗_当当网");
	list.add(e2); 
	
	AdvertisementEntity e3 = new AdvertisementEntity();
	e3.setImgUrl("http://img11.360buyimg.com/da/g15/M03/13/0B/rBEhWlM-gxUIAAAAAADGEszfVuIAALPIAHLa-AAAMYq584.jpg");
	e3.setDescription("奶粉特惠是京东商城近期热门活动，欢迎大家前来参与奶粉特惠活动");
	list.add(e3);
	
	AdvertisementEntity e4 = new AdvertisementEntity();
	e4.setImgUrl("http://img32.ddimg.cn/37/15/21066742-1_l.jpg");
	e4.setDescription("奶粉特惠是京东商城近期热门活动，欢迎大家前来参与奶粉特惠活动");
	list.add(e4);
	
	AdvertisementEntity e5 = new AdvertisementEntity();
	e5.setImgUrl("http://img36.ddimg.cn/47/30/1097122106-1_l.jpg");
	e5.setDescription("【玩转外设之百人组团】是京东商城近期热门活动，欢迎大家前来参与【玩转外设之百人组团】活");
	list.add(e5);
	/**/
	AdvertisementEntity e6 = new AdvertisementEntity();
	e6.setImgUrl("http://img10.360buyimg.com/vclist/g12/M00/04/1D/rBEQYFM9R4gIAAAAAAAqC5NOkXYAADyfgOI1joAACoj403.jpg");
	e6.setDescription("【玩转外设之百人组团】是京东商城近期热门活动，欢迎大家前来参与【玩转外设之百人组团】活动");
	list.add(e6);
	AdvertisementEntity e7 = new AdvertisementEntity();
	e7.setImgUrl("http://img11.360buyimg.com/da/g14/M09/1E/15/rBEhV1NCTbQIAAAAAABmF9g37tcAALejgPGJmUAAGYv551.jpg");
	e7.setDescription("【玩转外设之百人组团】是京东商城近期热门活动，欢迎大家前来参与【玩转外设之百人组团】活动");
	list.add(e7);
	
%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="part/head-meta.jsp" />

<jsp:include page="part/head.jsp" />
 
<script>

$(function(){
	 var $container = $('.thumbnails');
	 $container.imagesLoaded( function(){
		 $container.masonry({
		 	itemSelector : '.col-md-4'
		 });
	 });
	});

</script>

</head>


<body>

<jsp:include page="part/hat.jsp" /> 
 
<div class="container">
	<div class="row clearfix">
				<div class="col-md-4" id="col-1">
					
					<%
						for(int i=0; i<list.size();i++){
							if(i%3 !=0){
								continue;	
							}
							AdvertisementEntity entity = (AdvertisementEntity)list.get(i);
							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=entity.getImgUrl() %>" />
									<div class="caption">
										<h5>
											<%=entity.getDescription() %>
										</h5>
										 
										<p>
											<a class="btn btn-primary" href="#">标记</a> <a class="btn" href="#">标记</a>
										</p>
									</div>
								</div>							
							
							<%
						}
					%>
					
				</div>
				
				<div class="col-md-4">
					<%
						for(int i=0; i<list.size();i++){
							if(i%3 !=1){
								continue;	
							}
							AdvertisementEntity entity = (AdvertisementEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=entity.getImgUrl() %>" />
									<div class="caption">
										<h5>
											<%=entity.getDescription() %> 
										</h5>
 
										<p>
											<a class="btn btn-primary" href="#">标记</a> <a class="btn" href="#">标记</a>
										</p>
									</div>
								</div>							
							
							<%
						}
					%>
				</div>
				<div class="col-md-4">
					<%
						for(int i=0; i<list.size();i++){
							if(i%3 !=2){ 
								continue;	
							}
							AdvertisementEntity entity = (AdvertisementEntity)list.get(i);

							%>
								<div class="thumbnail">
									<img alt="300x200" src="<%=entity.getImgUrl() %>" />
									<div class="caption">
										<h5>
											<%=entity.getDescription() %>
										</h5>
 
										<p> 
											<a class="btn btn-primary" href="#">标记</a> <a class="btn" href="#">标记</a>
										</p>
									</div>
								</div>							
							
							<%
						}
					%>					 
				</div>
				 
				
		</div>
	</div>
<jsp:include page="part/footer.jsp" /> 
</body>
</html>
