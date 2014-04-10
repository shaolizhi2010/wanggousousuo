package com.service;

import java.util.List;

import com.dao.AdvertisementDao;
import com.entity.AdvertisementEntity;

public class AdvertisementService {
	AdvertisementDao advertisementDao = new AdvertisementDao();

	public void add(AdvertisementEntity entity) {
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
