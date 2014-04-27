package com.digger;

import java.util.List;

import com.entity.CommodityEntity;
import com.utils.App;
import com.utils.L;

public class CommodityDigerThread implements Runnable {
	
	private String shopName;
	private String keyword;
	
	
	public CommodityDigerThread(String shopName, String keyword){
		this.shopName = shopName;
		this.keyword = keyword;
		App.getInstance().threadCountPlus();
	}
	
	@Override
	public void run() {
		//guid = UUID.randomUUID().toString();
		long starttime = System.currentTimeMillis();
		
		CommonCommodityDigger digger = new CommonCommodityDigger(shopName, keyword);
		
		List<CommodityEntity>  list = digger.digAll();
		//new CommonCommodityDigger(shopName, basePath).dig(keyword, ruleFile);
		L.trace(this, "Digger finished, shop name is " + shopName + "keyword is "+ keyword+" time is "+ (System.currentTimeMillis()-starttime) + " fetch count is " + list.size());
		App.getInstance().threadCountSubtrac();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
	

}
