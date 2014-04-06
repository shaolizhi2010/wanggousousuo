package com.base.vo;

import com.google.gson.Gson;

public class BaseVO {

	@Override
	public String toString() {
		return this.toJson();
	};
 
	public String toJson(){
		return new Gson().toJson(this);
	}
}
