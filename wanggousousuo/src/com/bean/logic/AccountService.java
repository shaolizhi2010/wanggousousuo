package com.bean.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.db.DB;

public class AccountService {
	
	DB db = new DB();
	
	public boolean add(String userid, String bankid, String peopleName){
		
		String sql = "insert into account (userid, bankid, peopleName) values (?,?,?)";
		int num = db.update(sql, new Object[]{ userid, bankid, peopleName});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean update(String userid, String bankid,String peopleName){
		
		String sql = "update account set bankid= ?, peopleName = ? where userid = ?";
		int num = db.update(sql, new Object[]{ bankid, peopleName, userid});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public List<Map> get(String userid){
		
		String sql = "select * from account where userid = ?";
		List<Map> keywordList = (ArrayList<Map>) db.query(sql, new MapListHandler(), new Object[]{userid} );
		return keywordList;
	}
	
	
}
