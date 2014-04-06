package com.bean.logic;

import com.db.DB;

public class GuardService {
	
	String jdbcURL = "jdbc:mysql://bijia365.gotoftp1.com:3306/bijia365";
	String userName = "bijia365";
	String password = "cake4you@W";
	
	public boolean add(String ip, String domain,String targetUrl){
		
		DB db = new DB(jdbcURL,userName,password);
		String sql = "insert into guest_record (ip,domain,target_url ) values (?,?,?)";
		int num = db.update(sql, new Object[]{ ip,domain,targetUrl});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
	}
	
 
	
}
