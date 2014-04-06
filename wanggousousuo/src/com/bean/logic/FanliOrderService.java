package com.bean.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import com.db.DB;
import com.env.StaticInfo;
import com.google.gson.Gson;

public class FanliOrderService {
	
	DB db = new DB("jdbc:mysql://wgsousou.gotoftp3.com:3306/wgsousou","wgsousou","cake4you@W");
	
	public boolean add(String userid, String orderid, String shopname,String comment){
		
		
		String sql = "insert into fl_order (userid, orderid , shopname, comment) values (?,?,?,?)";
		int num = db.update(sql, new Object[]{ userid, orderid, shopname, comment});
		if(num >0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Map> get(String userid){
		
		String sql = "select * from fl_order where userid = ?";
		List<Map> list = (ArrayList<Map>) db.query(sql, new MapListHandler(), new Object[]{userid} );
		
		for(Map m : list){
			String shopname = "";
			if(m.get("shopname")!=null){
				shopname = m.get("shopname").toString();
			}
			shopname = StaticInfo.getShopbyName(shopname).getCnName();
			m.put("shopname", shopname);
		}
		return list;
	}
	
}
