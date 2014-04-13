package com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dao.CommodityDao;
import com.digger.CommodityDigerThread;
import com.entity.AdvertisementEntity;
import com.entity.CommodityEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.utils.L;
import com.utils.ShopNames;

public class CommodityService {
	CommodityDao commodityDao = new CommodityDao();

	public void add(CommodityEntity entity) {
		
		//根据 url 判断 商品 是否已经存在 避免重复
		String url = entity.getUrl();
		if(StringUtils.isBlank(url)) {L.exception(this, "url is blank");return;}

		CommodityEntity query = new CommodityEntity();
		query.setUrl(url);
		
		List<CommodityEntity> list = commodityDao.list(query);
		if(list !=null && list.size()>0){ //已经存在
			L.debug(this, "url : " + url + " is already exsit");
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
		return commodityDao.list();
		
	}

	public List<CommodityEntity> list(int start, int end) {
		return commodityDao.list(start, end);
	}

	public List<CommodityEntity> list(CommodityEntity entity) throws Exception {
		
		List<CommodityEntity> list = commodityDao.list(entity);
		
		
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
				
				return commentCount1 - commentCount2;
				
			}
		};
		
		Collections.sort(list, comparor);
		if(list.size()>10){
			return list.subList(0, 16);
		}
		return list;
	}
	
	public List<CommodityEntity> search(CommodityEntity entity) throws Exception {
		
		if(entity==null){
			return list();
		}
		
		List<CommodityEntity> list = this.list(entity);
		
		if(list!= null && list.size()>=16){
			return list;
		}
		long starttime = System.currentTimeMillis();
		//System.out.println("ending first list -- " + starttime);
		String keyword = entity.getKeyword();
		
		for(ShopNames shopName : ShopNames.values()){
			//System.out.println(shopName.toString());
			//System.out.println(keyword);
			
			Runnable r = new CommodityDigerThread(shopName.toString(), keyword);
			Thread t = new Thread(r);
			t.start();
			
		}
		//System.out.println("ending create all thread  -- " + (System.currentTimeMillis()- starttime));
		
		try {//不够 就等会再取
			Thread.sleep(500);	//每次隔0.5秒
		} catch (InterruptedException e) {
		}
		
		int i = 0;
//		while(true){
//			
//			//System.out.println("i = "+ i);
//			//System.out.println("list.size() = "+ list.size());
//			
//			//如有历史数据 则返回历史数据 TODO 会造成数据不是最新，待改进
//			//list = commodityDao.list(entity);
//			
//			if(list.size()>=16 || i>9){//够数了就返回 20,最多取10次
//				return list;
//			}
//			
//			try {//不够 就等会再取
//				Thread.sleep(500);	//每次隔0.5秒
//			} catch (InterruptedException e) {
//				return list;
//			}
//			
//			i++;
//			
//		} 
		
		return list;	//TODO
	
	}

	public CommodityEntity get(String id) {
		return commodityDao.get(id);
	}
 
	
	
}
