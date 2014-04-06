<%@page import="java.util.List"%>
<%@page import="com.entity.SearchHistoryEntity"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@page import="com.service.SearchHistoryService"%>
<%

//	SearchHistoryEntity entity =new  SearchHistoryService().get("一号店", "书包");
	//out.print(entity.getKeyword());
	//out.print(entity.getContent());
	
	List<SearchHistoryEntity> list = new  SearchHistoryService().list();
	for(SearchHistoryEntity e : list){
		out.print(e.getShopname());
		out.print(e.getKeyword());
		//out.print(e.getContent());
	}
	
%>