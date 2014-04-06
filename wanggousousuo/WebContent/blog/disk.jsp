 
<%@page import="com.env.Env"%>
<%@page import="com.file.FileUtil"%>
<%@page import="com.utils.C"%>
 
<%@ page language="java" pageEncoding="UTF-8"%>

<% 
 
//从work server 的硬盘上去文件夹名或文件名
try{
	out.println("begin");
	
	String method = request.getParameter("method");
	out.println(method);
	
	if(method==null){
		return;
	}
	if(method.equalsIgnoreCase("getFiles")){//get forders in dir
		String path = request.getParameter("path");
		path = new String(path.getBytes("ISO-8859-1"), "UTF-8");
		String type = request.getParameter("type");
		String returnjson = new FileUtil().getFilesJson(Env.basePath+path, type);
		//out.print(returnjson);
		out.clear();
		response.getOutputStream().write(returnjson.getBytes("UTF-8"));
		response.getOutputStream().close();
		return;
	}
	if(method.equalsIgnoreCase("getFileContent")){//get forders in dir
		String path = request.getParameter("path");
		path = new String(path.getBytes("ISO-8859-1"), "UTF-8");
		String returnstr = new FileUtil().getFileContent(Env.basePath+path);
		out.clear();
		response.getOutputStream().write(returnstr.getBytes("UTF-8"));
		response.getOutputStream().close();
		return;
	}
	
	if(method.equalsIgnoreCase("logout")){
		//session.setAttribute(C.userId, null);
		session.removeAttribute(C.userId);
		out.println("logout");
	}
	/*
	//response.getOutputStream().write("logout".getBytes("UTF-8"));
	out.println("end");
	*/
}catch (Exception e) {
	out.println(e.getMessage());
}

%>

 