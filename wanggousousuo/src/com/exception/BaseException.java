package com.exception;

import com.utils.L;

public class BaseException extends Exception {
	
	public BaseException(Object source, String msg){
		super(msg);
		L.debug(source, msg);
	}
	
	public BaseException(Object source, String msg, Exception e){
		super(msg,e);
		L.debug(source, msg);
	}
	
}
