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

	public void add(AdvertisementEntity entity) {
		
		//根据 img url 判断 ad 是否已经存在 避免重复
		String imgUrl = entity.getImgUrl();
		if(StringUtils.isBlank(imgUrl)) {L.exception(this, "img url is blank");return;}
		DBObject query = new BasicDBObject();
		query.put("imgUrl", imgUrl);
		List<AdvertisementEntity> list = advertisementDao.list(entity);
		if(list !=null && list.size()>0){ //已经存在
			L.log(this, "img url : " + imgUrl + " is already exsit");
			return;
		}
		
		String url = entity.getUrl();
		Html html = Connecter.getHtmlInfo(url);
		
		String title = html.title();
		String description = html.description();
		entity.setName(title);
		entity.setDescription(description);
		
		advertisementDao.add(entity);
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
