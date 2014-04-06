<%@page import="com.bean.logic.DrawMoneyService"%>
<%@page import="com.utils.C"%>
<%@page import="com.bean.logic.FanliOrderService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.bean.logic.AccountService"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="account-info-logic.jsp" />
 
<%
	String userid = "";
	if(session.getAttribute(C.userId)!=null){
		userid = session.getAttribute(C.userId).toString();
	}
	
	/*
	//账户余额
	String accountMoney = "0";
	String bankid = "";
	String peoplename = "";
	List<Map> records = new AccountService().get(userid);
	
	if(records!=null && records.size()>0 
			&& records.get(0)!=null ){
		if(records.get(0).get("money")!=null){
			accountMoney = records.get(0).get("money").toString();
		}
		if(records.get(0).get("bankid")!=null){
			bankid = records.get(0).get("bankid").toString();
		}
		if(records.get(0).get("peoplename")!=null){
			peoplename  = records.get(0).get("peoplename").toString();
		}
	}*/
	
%>

<!------------------------------------------------------------- 左侧菜单 -->
<div id="account-menu"  >
	<div id="account-menu-request-money" class="account-menu-item cm-item cm-can-hover cm-can-select cm-word-big cm-item-select">
		<a href="#">余额 / 提款</a>
	</div>
	<div id="account-menu-order" class="account-menu-item cm-item cm-can-hover cm-can-select cm-word-big">
		<a href="#">我的返利</a>
	</div>
	<div id="account-menu-request-money-record" class="account-menu-item cm-item cm-can-hover cm-can-select cm-word-big">
		<a href="#">我的提款</a>
	</div>
	<div id="account-menu-setting" class="account-menu-item cm-item cm-can-hover cm-can-select cm-word-big">
		<a href="#">账户设置</a>
	</div>
</div>


<!------------------------------------------------------------- 余额 / 提款  -->
<div id="account-request-money"  class="aciton-right-content">
	
	<div id="account-request-money-sub" class="cm-form">
		<div class="cm-f-msg">	你的账户现有 <span id="account-money-span" style="color:red;">0</span> 元 （十元起付）。
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>支付宝:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="account-bank-id" id="account-bank-id" value=""/>
			</div>
		</div>

		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>收款人姓名:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="account-people-name" id="account-people-name" value=""/>
			</div>
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>金额:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="account-money-input" id="account-money-input" value=""/>
			</div>
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span>备注:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="account-comment" id="account-comment"/>
			</div>
		</div>
		
		<div class="cm-f-buttons">
			<input type="button" id="account-drawmoney-ok" class="cm-f-button common-button-ok" value="申请提款"/>
			<input type="button" id="account-drawmoney-cancel" class="cm-f-button common-button-cancel" value="关闭页面"/>
		</div>
	</div>
</div>

<!------------------------------------------------------------- 我的返利 -->
<div id="account-orders"  class="aciton-right-content">

	<table id="order-table" class="cm-table">
		<tr> <th>网站名称</th><th>订单号</th><th>状态 / 返利</th><th>申请时间</th><th>备注</th> </tr>
	</table>
	
</div>

<!------------------------------------------------------------- 我的提款  -->
<div id="account-request-money-records"  class="aciton-right-content">
	<table id="money-records-table" class="cm-table">
		<tr> <th>支付宝</th><th>提款金额</th><th>状态</th><th>申请时间</th><th>备注</th> </tr>
	</table>
</div>

<!------------------------------------------------------------- 账户设置  -->
<div id="account-setting"  class="aciton-right-content">
	
	<div id="account-setting-sub" class="cm-form">
		<div class="cm-f-msg">	请设置您的账户。
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>支付宝:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="account-setting-bank-id" id="account-setting-bank-id"/>
			</div>
		</div>
		
		<div class="cm-f-item">
			<div class="cm-f-i-label">
				<span style="color: red;">*</span><span>收款人姓名:</span>
			</div>
			<div class="cm-f-i-input">
				<input type="text" name="account-setting-people-name" id="account-setting-people-name"/>
			</div>
		</div>
		
		<div class="cm-f-buttons">
			<input type="button" id="account-setting-ok" class="cm-f-button common-button-ok" value="更新"/>
			<input type="button" id="account-setting-cancel" class="cm-f-button common-button-cancel" value="关闭页面"/>
		</div>
	</div>
</div>
