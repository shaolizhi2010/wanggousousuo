<%@page import="com.env.Env"%>
<%@page import="com.utils.C"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<script>
<%
	if(Env.isProd == false){
		session.setAttribute(C.userId, "123");	
	}

%>

$( document ).ready(function() {

	//$('#share-fee-div').hide();
<%
	if(session.getAttribute(C.userId)==null){//not login
%>
	$('#qqloginBtn').show();
	$('#qqstatus').hide();
<%
	}else{
%>
	$('#qqloginBtn').hide();
	$('#qqstatus').show();

<%
	}
%>	
	$( "#cm-msg-window" ).dialog({
		 autoOpen: false,
		 modal: true, 
		 buttons: {
			 确定: function() {
				 $( this ).dialog( "close" );
		 	}
		 }
	});
	$( "#share-fee-div" ).dialog({
		 autoOpen: false,
		 modal: true,
		 width:$( "#share-fee-div" ).width()+30
	});
	$( "#account-div" ).dialog({
		 autoOpen: false,
		 modal: true,
		 height: $( "#account-div" ).height()+30,
		 width: $( "#account-div" ).width()+50
	});
	
	
	$('#qqloginBtn img').on("click", function() {
		window.open ('https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=100484692&redirect_uri=http://www.wanggousousuo.com/qqcallback.jsp&state=test' );
		//https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=100484692&redirect_uri=http://bijia365.gotoip1.com/qqcallback.jsp&state=test

	}); //end  on
	
	$('#qqlogout').on("click", function() {
		$.ajax({
			type:'get',
			 url:'login.jsp',
			 data:{ "method":'logout'},
			 async: false,
			 success:function(data){
		 		return;
			 }
		 }) ;
		$('#qqloginBtn').show();
		$('#qqstatus').hide();
	}); //end  on

	
	$("#share-fee-click").on("click", function() {
		<%
		if(session.getAttribute(C.userId)==null){//not login
		%>
			showWindow('您还未登录，本站支持QQ一键登录，无需注册');
			return;
		<%}%>
	//	$('#body-cover').show();
	//	$('#share-fee-div').css("left",  $(window).width()-350   );  
	//	$('#share-fee-div').css("top",  50 ); 
	//	$('#share-fee-div').css("z-index", 50 ); 
		
		$('#share-fee-div').dialog("open");
		
		
	}); //end  on	
	
	$('#share-fee-div-cancel').on("click", function() {
		$( "#share-fee-div" ).dialog("close" );
		
	}); //end  on
	
	//申请返利 页面 点击提交按钮
	$('#share-fee-div-ok').on("click", function() {
		
		if($('#s-f-shopname').val()=='' || $('#s-f-shopname').val()=="请选择"){
			var temp = $(".cm-f-msg").text();
			setTimeout(function(){ $(".cm-f-msg").text(temp); },5000)
			
			$(".cm-f-msg").text("请选择网站名称");
			$('#s-f-shopname').effect( "highlight" ,5000);
			$(".cm-f-msg").effect( "highlight" ,5000);
			return;
		}
		
		if($('#s-f-orderid').val()==''){
			var temp = $(".cm-f-msg").text();
			setTimeout(function(){ $(".cm-f-msg").text(temp); },5000)

			$(".cm-f-msg").text("请输入订单号");
			$('#s-f-orderid').effect( "highlight" ,5000);
			$(".cm-f-msg").effect( "highlight" ,5000);
			return;
		}
		
		$.ajax({
			type:'get',
			 url:'fanli',
			 data:{ "actiontype":'addOrder',"s-f-shopname":$('#s-f-shopname').val(),
				 "s-f-orderid":$('#s-f-orderid').val(),"s-f-comment":$('#s-f-comment').val()},
			 async: false,
			 success:function(data){
				 if(data == '提交成功！'){
						$('#s-f-orderid').val('');
						//$('#s-f-comment').val('');
						showWindow('提交成功，您可以到 [我的账户]');
						//$( "#cm-msg-window p" ).text("提交成功");
						//$( "#cm-msg-window" ).dialog("open");
							//.delay(2500)
							//.fadeOut(function(){ $(this).dialog("close") });
				 }
				 else{
					 $( "#cm-msg-window p" ).text("提交失败: "+ data);
					 $( "#cm-msg-window" ).dialog("open");
				 }
		 		return;
			 }
		 }) ;
		
		//$('#share-fee-next').css("left", $(window).width()-350  );  
		//$('#share-fee-next').css("top", 50 ); 
	//	$('#share-fee-next').css("z-index", 50 ); 
		//$('#share-fee-next').show();
		//window.open ('http://s.share.baidu.com/mshare?click=1&url=http%3A%2F%2Flocalhost%2Fwanggousousuo%2Fsearch.jsp&uid=6686604&to=mshare&type=text&relateUid=&pic=&title=%25E7%25BD%2591%25E8%25B4%25AD%25E6%2590%259C%25E7%25B4%25A2%25E7%25BD%2591%25EF%25BC%258C%25E8%2583%25BD%25E5%2590%258C%25E6%2597%25B6%25E6%2590%259C%25E7%25B4%25A2%25E6%25B7%2598%25E5%25AE%259D%2520%25E5%25A4%25A9%25E7%258C%25AB%2520%25E4%25BA%25AC%25E4%25B8%259C%2520%25E5%25BD%2593%25E5%25BD%2593%2520%25E4%25BA%259A%25E9%25A9%25AC%25E9%2580%258A%25E7%25AD%2589%2520n%25E5%25AE%25B6%25E5%2595%2586%25E5%259F%258E&key=&sign=on&desc=&comment=&searchPic=0&l=17vc1fn3o17vc1fo3217vc1hk2h&linkid=hj2tfbegxas&sloc=844.231.1.9.1.39.15.128.28.2.649&apiType=0&buttonType=0&firstime=1366888266275' );		
	}); //end  on
	
	$('#share-fee-next-ok').on("click", function() {
		$(this).parent().parent().hide();
		$('#body-cover').hide();
		
	}); //end  on
	
	$('#share-fee-next-cancel').on("click", function() {
		$(this).parent().parent().slideUp();
		$('#body-cover').hide();
		
	}); //end  on
	
	$('#account-link').on("click", function() {
		<%
		if(session.getAttribute(C.userId)==null){//not login
		%>
			showWindow('请先登录，本站支持QQ一键登录，无需注册');
			return;
		<%}%>
			
		
		//alert('a');
		//$('#body-cover').show();
		//$('#account-div').css("left", ($(window).width()-$('#account-div').width())/2 );  
		//$('#account-div').css("top",  50 );
		
		$("#account-div").load("part/account-info.jsp", function(){
			$("#account-menu-request-money").trigger('click'); 
		});
		
		$("#account-div").dialog("open");
		
	}); //end  on
	
	

}); //end document ready

function showWindow(msg) {
	$( "#cm-msg-window p" ).text(msg);
	$( "#cm-msg-window" ).dialog("open");
}

</script>