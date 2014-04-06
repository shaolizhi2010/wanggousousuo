package com.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public String toMD5(String msg){
		if(msg==null){
			return "";
		}
		BigInteger b = null;
		try {
			b = new BigInteger(MessageDigest.getInstance("md5").digest(msg.getBytes()));
			return b.toString();
		} catch (NoSuchAlgorithmException e) {
			L.exception(this, e.getMessage());
			return "";
		}
		
	}
	
	public Boolean check(String msg, String md5){
		if(msg == null || md5 == null){
			return false;
		}
		
		if( toMD5(msg).equals(md5) ){
			return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
