<%@page import="com.env.StaticInfo"%>
<%@page import="com.utils.ShopNames"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 <jsp:include page="hat-logic.jsp" />
 
<div id="hat" >
	 
	 	<div id="hat-x2">
		<!-- Baidu Button BEGIN -->
		<!-- Baidu Button BEGIN -->
			<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
			<span class="bds_more">分享到：</span>
			<a class="bds_qzone"></a>
			<a class="bds_tsina"></a>
			<a class="bds_tqq"></a>
			<a class="bds_renren"></a>
			<a class="bds_t163"></a>
			</div>
			<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6686604" ></script>
			<script type="text/javascript" id="bdshell_js"></script>
			<script type="text/javascript">
			document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
			</script>
			<!-- Baidu Button END -->
		<!-- Baidu Button END -->
	</div>	
	 
	<div class="fenge001" ><span  > | </span></div>
		
	<div id="view-history-div" class="hat-link-001 cm-can-hover">
		<a href="search.jsp" id="view-history-link" style="color:green;">我看过的宝贝</a></div>
	
	<div class="fenge001" ><span  > | </span></div>
	
	<!-- 
	<div class="hat-link-001 cm-can-hover">
		<a href="#" id="share-fee-click" style="color:green;">购物返利</a>
	</div>
	<div class="fenge001" ><span  > | </span></div>
	 -->
	
	<div class="hat-link-001 cm-can-hover">
		<a href="#" id="account-link" style="color:green;">我的账户</a>
	</div>
	<div class="fenge001" ><span  > | </span></div>
	
	<div id="qqloginBtn" class="hat-qq">
		<div> 欢迎来到网购搜索网！</div>   
 		<img alt="使用QQ帐号登录" src="img/qqlogin.png">
	</div>		
			
	<div id="qqstatus" class="hat-qq" style="display: none;">
		<div>欢迎来到网购搜索网！ </div> 
 		<!-- 
 			<img width="20" height="20"  alt="" src="<%=session.getAttribute("headUrl") %>">
 		 -->
 		<div><%=session.getAttribute("nickname")  %></div> 
 		<!-- 
	 		<div class="hat-link-001">
	 			<a href="#" id="account-link-bk"  account-link  style="color:green;" >[ 我的账户 ] </a>&nbsp; 
	 		</div>
 		 -->
 		<div class="hat-link-001">
 			<a href="#" id="qqlogout"    style="color:green;" > [ 退出 ] </a>
		</div>
	</div>
	
	
</div>

<div id="share-fee-div" class="cm-form" title="购物返利">
	
		<div class="cm-f-msg">	部分商家提够返利，购买后可在此申请，之后点击 [我的账户] 查看。
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>网站名称:</span>
			</div>
			<div class="cm-f-i-input">
				<select name="s-f-shopname" id="s-f-shopname">
					<option value="<%=ShopNames.taobao%>"><%=StaticInfo.getShopbyName(ShopNames.taobao.toString()).getCnName()%></option>
					<option value="<%=ShopNames.tmall%>"><%=StaticInfo.getShopbyName(ShopNames.tmall.toString()).getCnName()%></option>
					<option value="<%=ShopNames.amazon%>"><%=StaticInfo.getShopbyName(ShopNames.amazon.toString()).getCnName()%></option>
					<option value="<%=ShopNames.dangdang%>"><%=StaticInfo.getShopbyName(ShopNames.dangdang.toString()).getCnName()%></option>
					<option value="<%=ShopNames.yihaodian%>"><%=StaticInfo.getShopbyName(ShopNames.yihaodian.toString()).getCnName()%></option>
					<option value="<%=ShopNames.fanke%>"><%=StaticInfo.getShopbyName(ShopNames.fanke.toString()).getCnName()%></option>
				</select>
			</div>
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>订单号:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="s-f-orderid" id="s-f-orderid" title="请输入订单号." />
			</div>
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span>备注:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="s-f-comment" id="s-f-comment"/>
			</div>
		</div>
		
		<div class="cm-f-buttons">
			<input type="button" id="share-fee-div-ok" class="cm-f-button common-button-ok" value="提交"/>
			<input type="button" id="share-fee-div-cancel" class="cm-f-button common-button-cancel" value="取消"/>
		</div>
</div>



<div id="account-div" title="我的账户">

</div>

<div id="cm-msg-window" title="提示信息" >
	<p>提示信息.</p>
</div>

<div id="body-cover-bk"  >
</div>
 
