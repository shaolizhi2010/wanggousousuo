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
import com.entity.KeywordEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import com.trace.KeywordTrace;
import com.utils.L;
import com.utils.ShopNames;

/*
 * list 方法会默认取最新的100条数据(dao 实现)，然后对这100条数据 按评论数排序(service实现)
 * 
 * 这样可以保证
 * 1 取出的数据较新，不会把很久以前的数据也取出来
 * 2 对数据按评论数排序
 * 3 避免频繁删除历史数据，以后统计分析需要大量数据.
 * 
 * */
public class CommodityService {
	
	private int minCommodityNumber = 16;
	private int maxCommodityNumber = 100;
	private int freshTimeStamp = 3*24*60*60*1000; //commodity 信息保质期 3天
	
	CommodityDao commodityDao = new CommodityDao();
	KeywordService keywordService = new KeywordService();

	public void add(CommodityEntity entity) {
		
		//根据 url和price 判断 商品 是否已经存在 避免重复

		CommodityEntity query = new CommodityEntity();
		query.setUrl(entity.getUrl());
		query.setPrice(entity.getPrice());
		
		List<CommodityEntity> list = commodityDao.list(query);
		if(list !=null && list.size()>0){ //已经存在
			update(entity);
			//L.debug(this, "url and price is already exsit");
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

		if(list.size()> limit ){
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
	
	public List<CommodityEntity> search(CommodityEntity entity)  {
		return search(entity,0,maxCommodityNumber);
	}
	
	//查找keyword 相关的commodity信息，如果数据库中没有足够结果，则从新dig
	public List<CommodityEntity> search(CommodityEntity entity,int start, int limit)  {
		
		if(entity==null){
			return list();
		}
		
		String keyword = entity.getKeyword();
		
		//keyword search time++
		new KeywordTrace().timePlus(keyword);
		
		
		//查找commodity list
		List<CommodityEntity> list = this.list(entity,start,limit);
		
		//找到足够结果,返回
		if(list!= null && list.size() >= minCommodityNumber){
			return list;
		}
		
		//没找到足够结果,触发dig
		//有足够结果则不需重新出发dig，每天后台会自动dig， 更新commodity信息
		refresh(keyword);
		
		int i = 0;
		while(true){
			
			try {//不够 就等会再取
				Thread.sleep(500);	//每次隔0.5秒
			} catch (InterruptedException e) {
				return list;
			}
			
			list = this.list(entity,start,limit);
			
			if(list.size()>=minCommodityNumber || i> 30){//够数了就返回 
				return list;
			}
			i++;
		} 
	}
	
	//重新抓取更新商品信息
	public void refresh(String keyword){
		
		KeywordService keywordService = new KeywordService();
		
		KeywordEntity keywordEntity = new KeywordEntity();
		keywordEntity.setKeyword(keyword);
		
		 //keyword 已存在
		List<KeywordEntity> keywordList = keywordService.list(keywordEntity);
		if(keywordList != null && keywordList.size()>0){
			
			//取最新时间
			keywordEntity = keywordList.get(0);
			long lasttimeLong = 0L;
			try {
				lasttimeLong = Long.parseLong(keywordEntity.getLasttime()) ;
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//判断是否够新
			//足够新,则不需更新keyword，也不需要dig
			if(System.currentTimeMillis() - lasttimeLong < freshTimeStamp){	
				return ;
			}
			//已过期，更新 到当前时间
			else{
				keywordEntity.setLasttime(System.currentTimeMillis()+"");	//设置最新更新时间
				keywordService.update(keywordEntity);
			}
		}
		//新的keyword, insert
		else{
			keywordEntity.setLasttime(System.currentTimeMillis()+"");	//设置最新更新时间
			keywordService.add(keywordEntity);
		}
		
		//重新 dig 
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
 
	public void update(CommodityEntity entity){
		commodityDao.update(entity);
	}
	
}
