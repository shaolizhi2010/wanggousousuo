package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.utils.L;

public class DB {
	
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String jdbcURL = "jdbc:mysql://wgsousou.gotoftp3.com:3306/wgsousou";
	String userName = "wgsousou";
	String password = "cake4you@W";
	
	public DB(){
		
	}
	
	public DB(String jdbcURL, String userName, String  password){
		this.jdbcURL = jdbcURL;
		this.userName = userName;
		this.password = password;
	}
	
	Connection conn = null;
	
	public Connection getConnection(){
		
		try {
			if( conn == null){
				DbUtils.loadDriver(jdbcDriver);
				conn = DriverManager.getConnection(jdbcURL, userName, password);
				conn.setAutoCommit(true);
			}
			
		} catch (SQLException e) {
			L.exception(this, "get connection failed");
			L.exception(this, e.getMessage());
		}
		return conn;
	}
	
 
    public int update(String sql, Object... param) {  
        QueryRunner queryRunner = new QueryRunner();
        int num = 0;
        try {  
        	num = queryRunner.update(getConnection(),sql, param);  //update number
 
        } catch (SQLException e) {  
            L.exception(this, e.getMessage());
        }  
  
        return num;  
    }  
    
    //
    public List<Map> query(String sql,  ResultSetHandler hander, Object... params) {  
        QueryRunner qu = new QueryRunner();  
        List result = null;  
        try {
            result = (ArrayList) qu.query(getConnection(),sql, hander, params);  
        } catch (SQLException e) {
            e.printStackTrace();  
        }  
        return result;  
    }  
	
	
	
}
