 
<%@page import="com.utils.C"%>
<%@page import="com.connect.SimpleConnecter"%>
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.bean.logic.QQCallBackService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<% 

String appId = "100484692";
String appSecret = "825fb7ca290bc355b081a2d1a2e00617";
String callbackUrl = "http://www.wanggousousuo.com/qqcallback.jsp";

QQCallBackService qq = new QQCallBackService();

try{
	out.println("begin");
	String code = request.getParameter("code");
	if(  !StringUtils.isBlank(code) ){	//返回了code
		session.setAttribute("qqcode", code);
		out.println(" code : " +code);
		String state = new Random().nextInt()+"" ;
		session.setAttribute("qqstate", state);
		
		//String tokenJson = SimpleConnecter.connect("https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=100484692&client_secret=825fb7ca290bc355b081a2d1a2e00617&code="+code+"&redirect_uri=http://www.wanggousousuo.com/qqcallback.jsp&state="+state);
		//out.println(" tokenJson : " +tokenJson);
		
		String token = qq.getToken(code,state);
		out.println(" token : " +token);
		
		/* //验证state
		if(token.length()>0){
			String returnState = request.getParameter("state");
			if(!state.equalsIgnoreCase(returnState)){
				out.println(" state : " +state);
				out.println(" returnState : " +returnState);
				return;	//cscf攻击
			}
		}
		*/
		
		String openId = qq.getOpenId(token);
		out.println(" openId : " +openId);
		
		String json = SimpleConnecter.connect("https://graph.qq.com/user/get_user_info?access_token="+token+"&oauth_consumer_key="+appId+"&openid="+openId,"utf-8");
		
		out.println(" json : " +json);
		
		Map<String, String> userinfo = qq.getUserQQInfo(token, appId, openId);
		String nickname = userinfo.get("nickname");
		String headUrl = userinfo.get("figureurl_qq_1");
		
		out.println(" nickname : " +nickname);
		out.println(" headUrl : " +headUrl);
		
		session.setAttribute(C.userId, openId);
		session.setAttribute("nickname", nickname);
		session.setAttribute("headUrl", headUrl);
		
		response.sendRedirect("index.jsp");
		
		return;
	}
	else{	//返回 token
		//String token = request.getParameter("code");
		//out.println(" code : " +code);
		out.println(" else "   );
	}
	out.println("end");
	
}catch (Exception e) {
	out.println(e.getMessage());
	//L.exception(this, e.getMessage());
	// ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
	//catch this and do nothing, this is because the client do not receive from server any more
}

%>

<div style="display: none;">
		<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc2dab893ac85ba9830b8dfd19e9ca3ad' type='text/javascript'%3E%3C/script%3E"));
</script>

</div>