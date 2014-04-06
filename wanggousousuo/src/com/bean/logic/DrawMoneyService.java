package com.bean.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.db.DB;


//退款
public class DrawMoneyService {
	
	DB db = new DB();
	
	public boolean add(String userid, String bankid, String peopleName, BigDecimal money,String comment){
		
		String sql = "insert into drawmoney (userid, bankid, peoplename, money, comment) values (?,?,?,?,?)";
		int num = db.update(sql, new Object[]{ userid, bankid, peopleName, money, comment});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public List<Map> get(String userid){
		
		String sql = "select * from drawmoney where userid = ?";
		List<Map> list = (ArrayList<Map>) db.query(sql, new MapListHandler(), new Object[]{userid} );
		return list;
	}
	
	public List<Map> getByStatus(String userid, String status){
		
		String sql = "select * from drawmoney where userid = ? and status= ?";
		List<Map> list = (ArrayList<Map>) db.query(sql, new MapListHandler(), new Object[]{userid,status} );
		return list;
	}
	
}
