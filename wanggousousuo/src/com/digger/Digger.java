package com.digger;

import java.util.List;

import com.digger.vo.Product;
import com.exception.BaseException;


public interface Digger {
	public List<Product>  digAll(String keyword) throws BaseException ;
}
