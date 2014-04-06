 
<%@page import="com.utils.C"%>
 
<%@ page language="java" pageEncoding="UTF-8"%>

<% 
 

try{
	out.println("begin");
	
	String method = request.getParameter("method");
	out.println(method);
	
	if(method==null){
		
		return;
	}
	
	if(method.equalsIgnoreCase("logout")){
		//session.setAttribute(C.userId, null);
		session.removeAttribute(C.userId);
		out.println("logout");
		
	}
	/*
	response.getOutputStream().write("logout".getBytes("UTF-8"));
	out.println("end");
	*/
}catch (Exception e) {
	out.println(e.getMessage());
}

%>

 