package com.bean.logic;

import com.db.DB;

public class LinkService {
	
	String jdbcURL = "jdbc:mysql://bijia365.gotoftp1.com:3306/bijia365";
	String userName = "bijia365";
	String password = "cake4you@W";
	
	public boolean add(String name, String url){
		

		DB db = new DB(jdbcURL,userName,password);
		String sql = "insert into friend_link (name,url ) values (?,?)";
		int num = db.update(sql, new Object[]{ name,url});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean add(String name, String url, String desc,String logourl, String comment){
		
		DB db = new DB(jdbcURL,userName,password);
		String sql = "insert into friend_link ('name','url','desc','logourl','comment') values (?,?,?,?,?)";
		int num = db.update(sql, new Object[]{ name,url,desc,logourl,comment});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
	}
	
}
