package com.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.connect.Connecter;
import com.dao.AdvertisementDao;
import com.entity.AdvertisementEntity;
import com.html.Html;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.utils.L;

public class AdvertisementService {
	AdvertisementDao advertisementDao = new AdvertisementDao();

	public boolean add(AdvertisementEntity entity) {
		
		//根据 img url 判断 ad 是否已经存在 避免重复
		String imgUrl = entity.getImgUrl();
		if(StringUtils.isBlank(imgUrl)) {L.exception(this, "img url is blank");return false;}
		
		//DBObject query = new BasicDBObject();
		//query.put("imgUrl", imgUrl);
		AdvertisementEntity query = new AdvertisementEntity();
		query.setImgUrl(imgUrl);
		
		
		List<AdvertisementEntity> list = advertisementDao.list(query);
		if(list !=null && list.size()>0){ //已经存在
			L.debug(this, "img url : " + imgUrl + " is already exsit");
			return false;
		}
		
		String url = entity.getUrl();
		Html html = Connecter.getHtml(url);
		
		String title = html.title();
		String description = html.description();
		entity.setName(title);
		entity.setDescription(description);
		
		advertisementDao.add(entity);
		L.trace(this, "advertisement : "+ entity.getImgUrl() + "is adding to db");
		return true;
	}
	
	public void update(AdvertisementEntity entity) {
		advertisementDao.update(entity);
	}
	public void delete(String id)  {
		advertisementDao.delete(id);
	}

	public void delete(AdvertisementEntity entity) {
		advertisementDao.delete(entity);
	}

	public List<AdvertisementEntity> list() {
		return advertisementDao.list();
	}

	public List<AdvertisementEntity> list(int start, int end) {
		return advertisementDao.list(start, end);
	}

	public List<AdvertisementEntity> list(AdvertisementEntity entity) throws Exception {
		return advertisementDao.list(entity);
	}

	public AdvertisementEntity get(String id) {
		return advertisementDao.get(id);
	}
 
	
	
}
