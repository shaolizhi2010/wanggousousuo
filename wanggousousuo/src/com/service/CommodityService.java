package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.CommodityDao;
import com.digger.CommodityDigerThread;
import com.entity.CommodityEntity;
import com.utils.ShopNames;

public class CommodityService {
	CommodityDao commodityDao = new CommodityDao();

	public void add(CommodityEntity entity) {
		commodityDao.add(entity);
	}

	public void delete(String id)  {
		commodityDao.delete(id);
	}

	public void delete(CommodityEntity entity) {
		commodityDao.delete(entity);
	}

	public List<CommodityEntity> list() {
		return commodityDao.list();
		
	}

	public List<CommodityEntity> list(int start, int end) {
		return commodityDao.list(start, end);
	}

	public List<CommodityEntity> list(CommodityEntity entity) throws Exception {
		
		if(entity==null){
			return list();
		}
		
		String keyword = entity.getKeyword();
		
		for(ShopNames shopName : ShopNames.values()){
			System.out.println(shopName.toString());
			System.out.println(keyword);
			
			Runnable r = new CommodityDigerThread(shopName.toString(), keyword);
			Thread t = new Thread(r);
			t.start();
			
		}
		
		List<CommodityEntity> list = new ArrayList<CommodityEntity>();
		
		int i = 0;
		while(true){
			
			System.out.println("i = "+ i);
			System.out.println("list.size() = "+ list.size());
			
			//如有历史数据 则返回历史数据 TODO 会造成数据不是最新，待改进
			list = commodityDao.list(entity);
			
			if(list.size()>20 || i>9){//够数了就返回 20,最多取10次
				return list;
			}
			
			try {//不够 就等会再取
				Thread.sleep(500);	//每次隔0.5秒
			} catch (InterruptedException e) {
				return list;
			}
			
			i++;
			
		}
		
	
	}

	public CommodityEntity get(String id) {
		return commodityDao.get(id);
	}
 
	
	
}
