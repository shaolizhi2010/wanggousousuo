<%@ page language="java" pageEncoding="UTF-8"%>

<div id="header">
	<div id="header-y1">
			<div id="header-logo">
				<a href="<%=request.getContextPath()%>/index.jsp">
					<img alt="" src="<%=request.getContextPath()%>/img/logo-wg.png"/>
				</a>
			</div>
		 	<div id="header-search">
				<input type="text" id="keyword" value="">
				<input type="button" id="submit-search-btn" value="搜索">
				<div id="hot-words2">
					<span>热门搜索:</span>
					<a href="search.jsp?keyword=短袖T恤">短袖T恤</a>
					<a href="search.jsp?keyword=新款" style="color: red">新款</a>
					<a href="search.jsp?keyword=三件套">三件套</a>
					<a href="search.jsp?keyword=电风扇">电风扇</a>
					<a href="search.jsp?keyword=凉席">凉席</a>
					<a href="search.jsp?keyword=运动">运动</a>
				</div>
			</div>
	</div>

</div>
<!-- end div header -->
