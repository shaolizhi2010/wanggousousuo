package com.service;

import java.util.List;

import com.dao.SearchHistoryDao;
import com.entity.SearchHistoryEntity;
import com.env.StaticInfo;

public class SearchHistoryService {
	
	SearchHistoryDao searchHistoryDao = new SearchHistoryDao();
	
	public void save(String shopname,String keyword,String content) {
		
		//有查询结果  则保存
		if(content !=null && content.length()>100){
			shopname = StaticInfo.getShopbyName(shopname).getCnName();
			SearchHistoryEntity entity = new SearchHistoryEntity();
			entity.setShopname(shopname);
			entity.setKeyword(keyword);
			entity.setContent(content);
			
			searchHistoryDao.save(entity);
		}
	}
	
	public SearchHistoryEntity get(String shopname, String keyword){
		return searchHistoryDao.get(shopname, keyword);
	}
	
	public List keywords() {
		return searchHistoryDao.keywords();
	}
	
	public List<SearchHistoryEntity> list(){
		return searchHistoryDao.list();
	}
}
