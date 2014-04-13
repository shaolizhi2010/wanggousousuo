package com.digger;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.entity.CommodityEntity;

public class CommodityDigerThread implements Runnable {
	
	private String guid;
	private String shopName;
	private String keyword;
	
	
	public CommodityDigerThread(String shopName, String keyword){
		this.shopName = shopName;
		this.keyword = keyword;
	}
	
	@Override
	public void run() {
		guid = UUID.randomUUID().toString();
		long starttime = System.currentTimeMillis();
		//System.out.println("Thread-"+ guid+" is start, time is "+ System.currentTimeMillis());
		
		List<CommodityEntity>  list = new CommonCommodityDigger(shopName, keyword).digAll();
		//new CommonCommodityDigger(shopName, basePath).dig(keyword, ruleFile);
		//System.out.println("Thread-"+ guid+" is finished, shop name is " + shopName +" time is "+ (System.currentTimeMillis()-starttime) + " fetch count : " + list.size());
	}

}
