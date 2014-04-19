package com.service;

import java.util.List;

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
		return shopDao.list();
	}

	public List<ShopEntity> list(int start, int end) {
		return shopDao.list(start, end);
	}

	public List<ShopEntity> list(ShopEntity entity) throws Exception {
		return shopDao.list(entity);
	}

	public ShopEntity get(String id) {
		return shopDao.get(id);
	}
 
	
	
}
