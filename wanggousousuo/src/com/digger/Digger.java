package com.digger;

import java.util.List;

import com.entity.CommodityEntity;
import com.exception.BaseException;


public interface Digger {
	public List<CommodityEntity>  digAll(String keyword) throws BaseException ;
	public List<CommodityEntity>  digAll() throws BaseException ;
}
