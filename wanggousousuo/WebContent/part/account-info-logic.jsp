<%@page import="com.utils.C"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<script>

var accountMoneyNeedRefresh = true;
var fanliNeedRefresh = true;
var drawMoneyNeedRefresh = true;
var accountSettingNeedRefresh = true;

$( document ).ready(function() {
	
	$('#account-head-close').on("click", function() {
		$(this).parent().parent().hide();
		$('#body-cover').hide();

	}); //end  on
	
	$('.cm-can-select').on("click", function() {
		$('.cm-can-select').removeClass("cm-item-select");
		$(this).addClass('cm-item-select');

	}); //end  on
	
	$('#account-menu-money').on("click", function() {
		
		$('#account-navigation').text('网购搜索网 > 账户余额');
		$('.aciton-right-content').hide();
		$('#account-money').show();
	}); //end  on
	
	//-------------------------------------------------------我的返利 菜单被点击
	$('#account-menu-order').on("click", function() {
		
		if(fanliNeedRefresh){
			$('#loading').show();
			
			$.ajax({
				type:'get',
				 url:'fanli',
				 data:{ "actiontype":'getFanliOrderJson'},
				 async: true,
				 success:function(dataJsonStr){
					 var data = JSON.parse(dataJsonStr);
					 $("#order-table").find("tr:not(:first)").remove();
					 for ( var d in data) {
						 $('#order-table').append("<tr><td>"+data[d].shopname+"</td><td>"+data[d].orderid+"</td><td>"+ data[d].status + " / "+ data[d].ordermoney  + "</td><td>"+ data[d].date +"</td><td>"+ data[d].comment +"</td></tr>");
					 }
					 $('#loading').hide();
					 fanliNeedRefresh = false;//已是最新数据，下次打开不需要再刷新
				 },
				 error:function(){
					 showWindow('打开 我的返利 菜单时出现错误');
				 }
			 }) ; //end ajax
		}// end if

		$('#account-navigation').text('网购搜索网 > 我的返利');
		$('.aciton-right-content').hide();
		$('#account-orders').show();

	}); //end  on
	
	//-------------------------------------------------------余额/退款 菜单被点击
	$('#account-menu-request-money').on("click", function() {
		if(accountMoneyNeedRefresh){
			
			$('#loading').show();
			$.ajax({
				type:'get',
				 url:'fanli',
				 data:{ "actiontype":'getAccount'},
				 async: true,
				 success:function(dataJsonStr){
					 var data = JSON.parse(dataJsonStr);
					 
						 $('#account-money-span').text(data[0].money );
						 $('#account-bank-id').val(data[0].bankid  );
						 $('#account-bank-id').val(data[0].bankid  );
						 $('#account-people-name').val(data[0].peoplename  );
						 
					 
					 
					 
					 $('#loading').hide();
					 accountMoneyNeedRefresh = false;//已是最新数据，下次打开不需要再刷新
				 },
				 error:function(){
					 showWindow('打开  菜单时出现错误');
				 }
			 }) ; //end ajax
		}// end if
		
		$('#account-navigation').text('网购搜索网 > 余额 / 提款');
		$('.aciton-right-content').hide();
		$('#account-request-money').show();
	}); //end  on
	
	//-------------------------------------------------------我的退款 菜单被点击
	$('#account-menu-request-money-record').on("click", function() {
		
		if(drawMoneyNeedRefresh){
			$('#loading').show();
			
			$.ajax({
				type:'get',
				 url:'fanli',
				 data:{ "actiontype":'getDrawMoneyJson'},
				 async: true,
				 success:function(dataJsonStr){
					 var data = JSON.parse(dataJsonStr);
					 $("#money-records-table").find("tr:not(:first)").remove();
					 for ( var d in data) {
						 $('#money-records-table').append("<tr><td>"+data[d].bankid+"</td><td>"+data[d].money+"</td><td>"+data[d].status+"</td><td>"+ data[d].date +"</td><td>"+ data[d].comment +"</td></tr>");
					 }
					 $('#loading').hide();
					 drawMoneyNeedRefresh = false;//已是最新数据，下次打开不需要再刷新
				 },
				 error:function(){
					 showWindow('打开  菜单时出现错误');
				 }
			 }) ; //end ajax
		}// end if
		
		
		$('#account-navigation').text('网购搜索网 > 我的提款');
		$('.aciton-right-content').hide();
		$('#account-request-money-records').show();
	}); //end  on
	
	//-------------------------------------------------------提交退款页面，点击提交按钮
	$('#account-drawmoney-ok').on("click", function() {

		$.ajax({
			type:'get',
			 url:'fanli',
			 data:{ "actiontype":'addDrawMoney',"account-bank-id":$('#account-bank-id').val(),
				 "account-money-input":$('#account-money-input').val(), "account-comment":$('#account-comment').val()},
			 async: false,
			 success:function(data){
				 if(data == '提交成功！'){
						$('#account-bank-id').val('');
						$('#account-money-input').val('');
						$('#account-comment').val('');
						drawMoneyNeedRefresh = true;
						accountMoneyNeedRefresh = true;
						showWindow('提交成功');
				 }
				 else{
					showWindow(data);
				 }
				//$(this).parent().parent().hide();//隐藏返利window
				//$('#body-cover').hide();
				 //showWindow(data);
		 		return;
			 }
		 }) ;
		
		
	}); //end  on
	
	$('#account-drawmoney-cancel').on("click", function() {
		$("#account-div").dialog("close");
	}); //end  on
	//-------------------------------------------------------设置账户 菜单 单击
	$('#account-menu-setting').on("click", function() {
		
		if(accountSettingNeedRefresh){
			$('#loading').show();
			
			$.ajax({
				type:'get',
				 url:'fanli',
				 data:{ "actiontype":'getAccount'},
				 async: true,
				 success:function(dataJsonStr){
					 var data = JSON.parse(dataJsonStr);
					 $('#account-setting-bank-id').val(data[0].bankid);//data.bankid
					 $('#account-setting-people-name').val(data[0].peoplename);
					 $('#loading').hide();
					 accountSettingNeedRefresh = false;//已是最新数据，下次打开不需要再刷新
				 },
				 error:function(){
					 showWindow('打开  菜单时出现错误');
				 }
			 }) ; //end ajax
		}// end if
		
		
		//$('#account-navigation').text('网购搜索网 > 账户设置');
		$('.aciton-right-content').hide();
		$('#account-setting').show();
	}); //end  on
	
	////-------------------------------------------------------设置账户 页面，点击提交按钮
	$('#account-setting-ok').on("click", function() {

		$.ajax({
			type:'get',
			 url:'fanli',
			 data:{ "actiontype":'updateAccount',"account-setting-bank-id":$('#account-setting-bank-id').val(),
				 "account-setting-people-name":$('#account-setting-people-name').val()},
			 async: false,
			 success:function(data){
				 if(data == '提交成功！'){
						accountSettingNeedRefresh = true;
						accountMoneyNeedRefresh = true;	//刷新 账户/退款 页
						showWindow('提交成功');
				 }
				 else{
					showWindow(data);
				 }
				//$(this).parent().parent().hide();//隐藏返利window
				//$('#body-cover').hide();
				 //showWindow(data);
		 		return;
			 }
		 }) ;
		
		
	}); //end  on
	
	$('#account-setting-cancel').on("click", function() {
		$("#account-div").dialog("close");
	}); //end  on
	
}); //end document ready



</script>