package com.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dao.KeywordDao;
import com.entity.AdvertisementEntity;
import com.entity.KeywordEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.utils.L;

public class KeywordService {
	KeywordDao keywordDao = new KeywordDao();

	public void add(KeywordEntity entity) {
		
		if(StringUtils.isBlank(entity.getKeyword())){
			return;
		}
		
		KeywordEntity query = new KeywordEntity();
		query.setKeyword(entity.getKeyword());
		 
		List<KeywordEntity> list = keywordDao.list(query);
		if(list !=null && list.size()>0){ //已经存在
			L.debug(this, "keyword : " + entity.getKeyword() + " is already exsit");
			return;
		}
		
		keywordDao.add(entity);
	}
	public void update(KeywordEntity entity) {
		keywordDao.update(entity);
	}
	public void delete(String id)  {
		keywordDao.delete(id);
	}

	public void delete(KeywordEntity entity) {
		keywordDao.delete(entity);
	}

	public List<KeywordEntity> list() {
		return this.list(new KeywordEntity(),0,100);
	}

	public List<KeywordEntity> list(int start, int limit) {
		return this.list(new KeywordEntity(),start, limit);
	}

	public List<KeywordEntity> list(KeywordEntity entity) {
		return this.list(entity,0,100);
	}
	
	public List<KeywordEntity> list(KeywordEntity entity,int start, int limit) {
		return keywordDao.list(entity,0,100);
	}

	public KeywordEntity get(String id) {
		return keywordDao.get(id);
	}
 
	
	
}
