package com.bean.logic;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.connect.SimpleConnecter;
import com.utils.U;

public class QQCallBackService {
	
	public String getToken(String code, String state){
		String token = SimpleConnecter.connect("https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=100484692&client_secret=825fb7ca290bc355b081a2d1a2e00617&code="+code+"&redirect_uri=http://www.wanggousousuo.com/qqcallback.jsp&state="+state);
		if(token!=null ){
			token = StringUtils.substringBetween(token, "access_token=","&");
		}
		else{ 
			return  "";
		}
		
		if(token == null){
			return "";
		}
		return token;
	}
	
	public String getOpenId(String token){
		String json = SimpleConnecter.connect("https://graph.qq.com/oauth2.0/me?access_token="+token);
		if(StringUtils.isBlank(json)){
			return "";
		}
		
		json = StringUtils.substringBetween(json, "callback( ", " );");
		
		return U.jsonValue(json, "openid");
	}
	
	public Map<String,String> getUserQQInfo(String token, String appId, String openId){
		String json = SimpleConnecter.connect("https://graph.qq.com/user/get_user_info?access_token="+token+"&oauth_consumer_key="+appId+"&openid="+openId,"utf-8");
		
		return U.jsonToMap(json);
	}
	
	public String getNickName(String token, String appId, String openId){
		return getUserQQInfo(token, appId, openId).get("nickname");
	}
	
	public String getHead30Url(String token, String appId, String openId){
		return getUserQQInfo(token, appId, openId).get("figureurl");
	}
	
	
}
