<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.service.CatalogService"%>
<%@page import="com.entity.CatalogEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

 <%
	String password = request.getParameter("p");
 	if(StringUtils.isBlank(password)){
 		return; //无密码
 	}
 	if(!"cake4you".equals(password)){
 		return; // 密码错
 	}
 %>
 