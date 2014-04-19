package com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.dao.CommodityDao;
import com.digger.CommodityDigerThread;
import com.entity.AdvertisementEntity;
import com.entity.CommodityEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.utils.L;
import com.utils.ShopNames;

public class CommodityService {
	
	private int minCommodityNumber = 16;
	private int maxCommodityNumber = 100;
	
	CommodityDao commodityDao = new CommodityDao();

	public void add(CommodityEntity entity) {
		
		//根据 url和price 判断 商品 是否已经存在 避免重复

		CommodityEntity query = new CommodityEntity();
		query.setUrl(entity.getUrl());
		query.setPrice(entity.getPrice());
		
		List<CommodityEntity> list = commodityDao.list(query);
		if(list !=null && list.size()>0){ //已经存在
			L.debug(this, "url and price is already exsit");
			return;
		}
		
		commodityDao.add(entity);
	}

	public void delete(String id)  {
		commodityDao.delete(id);
	}

	public void delete(CommodityEntity entity) {
		commodityDao.delete(entity);
	}

	public List<CommodityEntity> list() {
		return this.list(new CommodityEntity(), 0, maxCommodityNumber);
		
	}

	public List<CommodityEntity> list(int start, int limit) { 
		return this.list(new CommodityEntity(),start, limit);
	}
	
	public List<CommodityEntity> list(CommodityEntity entity) {
		return this.list(entity, 0, maxCommodityNumber);
	}
	

	public List<CommodityEntity> list(CommodityEntity entity,int start, int limit) {
		
		List<CommodityEntity> list = commodityDao.list(entity, start, limit);

		if(list.size()>minCommodityNumber){
			//inner comparor
			Comparator<CommodityEntity> comparor = new Comparator<CommodityEntity>() {
				
				@Override
				public int compare(CommodityEntity e1, CommodityEntity e2) {
					
					int commentCount1 = 0;
					int commentCount2 = 0;
					
					if(StringUtils.isNotBlank(e1.getCommentCount())){
						try {
							commentCount1 = Integer.parseInt(e1.getCommentCount());
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if(StringUtils.isNotBlank(e2.getCommentCount())){
						try {
							commentCount2 = Integer.parseInt(e2.getCommentCount());
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
					return commentCount2 - commentCount1  ;
					
				}
			};
			
			Collections.sort(list, comparor);
			
			return list.subList(0, minCommodityNumber);
		}
		return list;
	}
	
	public List<CommodityEntity> search(CommodityEntity entity) throws Exception {
		
		
		
		if(entity==null){
			return list();
		}
		
		String keyword = entity.getKeyword();
		List<CommodityEntity> list = this.list(entity);
		
		
		if(list!= null && list.size()>=minCommodityNumber){//找到结果
			
			//结果不够 新，暂时返回，同时异步更新
			if(System.currentTimeMillis() > 24*60*60*1000 - new ObjectId( list.get(0).getId()).getTimestamp()){
				dig(keyword);
			}
			
			return list;
		}
		
		//没找到结果,触发异步找结果
		dig(keyword);
		
		int i = 0;
		while(true){
			
			try {//不够 就等会再取
				Thread.sleep(500);	//每次隔0.5秒
			} catch (InterruptedException e) {
				return list;
			}
			
			list = this.list(entity);
			
			if(list.size()>=minCommodityNumber || i>99){//够数了就返回 
				return list;
			}
			
			i++;
			
		} 
		
	}
	
	public void dig(String keyword){
		for(ShopNames shopName : ShopNames.values()){
			//System.out.println(shopName.toString());
			//System.out.println(keyword);
			
			Runnable r = new CommodityDigerThread(shopName.toString(), keyword);
			Thread t = new Thread(r);
			t.start();
			
		}
	}

	public CommodityEntity get(String id) {
		return commodityDao.get(id);
	}
 
	
	
}
