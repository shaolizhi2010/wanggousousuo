package com.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dao.ShopDao;
import com.entity.ShopEntity;

public class ShopService {
	ShopDao shopDao = new ShopDao();

	public void add(ShopEntity entity) {
		shopDao.add(entity);
	}
	public void update(ShopEntity entity) {
		shopDao.update(entity);
	}
	public void delete(String id)  {
		shopDao.delete(id);
	}

	public void delete(ShopEntity entity) {
		shopDao.delete(entity);
	}

	public List<ShopEntity> list() {
		return this.list(new ShopEntity(),0,100);
	}

	public List<ShopEntity> list(int start, int limit) {
		return this.list(new ShopEntity(),start, limit);
	}

	public List<ShopEntity> list(ShopEntity entity) {
		return this.list(entity,0,100);
	}
	
	public List<ShopEntity> listAvailableShops(ShopEntity entity,int start, int limit) {
		
		if(entity == null){ 
			entity = new ShopEntity();
		}
		
		List<ShopEntity> list = this.list(entity,start,limit);
		
	    Iterator<ShopEntity> shopIterator = list.iterator();  
	    while(shopIterator.hasNext()){  
	    	ShopEntity shop = shopIterator.next();  
			if( "false".equals(shop.getShopAvailable()) ){
				shopIterator.remove();
			}
	    }  
		
		return list;
	}
	
	public List<ShopEntity> listSearchAvailableShops(ShopEntity entity,int start, int limit) {
		
		if(entity == null){ 
			entity = new ShopEntity();
		}
		
		List<ShopEntity> list = this.list(entity,start,limit);
		
		for(ShopEntity shop : list){
			if( StringUtils.isBlank(shop.getSearchUrl()) ||  "false".equals(shop.getSearchAvailable()) ){
				list.remove(shop);
			}
		}
		
		return list;
	}
	
	public List<ShopEntity> list(ShopEntity entity,int start, int limit) {
		List<ShopEntity> list = shopDao.list(entity,0,100);
		
		return list;
	}

	public ShopEntity get(String id) {
		return shopDao.get(id);
	}
 
	
	
}
