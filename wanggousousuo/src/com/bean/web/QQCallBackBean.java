package com.bean.web;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import sun.net.www.http.HttpClient;

import com.bean.logic.LinkService;
import com.bean.logic.QQCallBackService;
import com.db.DB;
import com.google.gson.Gson;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;

public class QQCallBackBean extends BaseBean {

	public void execute(HttpServletRequest request, HttpServletResponse response, ParameterMap parameterMap)
			throws Exception {

		try{
			
			String token = request.getParameter("access_token"); //action type
			
			if( StringUtils.isBlank(token)){
				//TODO
				return ;
			}
			
			QQCallBackService qq = new QQCallBackService();
			String openId = qq.getOpenId(token);
			
			Map<String, String> userinfo = qq.getUserQQInfo(token, "100484692", openId);
			String nickname = userinfo.get("nickname");
			String head30Url = userinfo.get("figureurl");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", openId);
			session.setAttribute("nickname", nickname);
			session.setAttribute("head30Url", head30Url);
			
			response.getOutputStream().write((openId+" --- ").getBytes("UTF-8"));
			response.getOutputStream().write((nickname+" --- ").getBytes("UTF-8"));
			response.getOutputStream().write((head30Url+" --- ").getBytes("UTF-8"));
			
		}catch (Exception e) {
			L.exception(this, e.getMessage());
			// ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
			//catch this and do nothing, this is because the client do not receive from server any more
		}
	
 
	}
}
