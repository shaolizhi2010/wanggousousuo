package com.service;

import java.util.List;

import com.dao.CatalogDao;
import com.entity.CatalogEntity;

public class CatalogService {
	CatalogDao catalogDao = new CatalogDao();

	public void add(CatalogEntity entity) {
		catalogDao.add(entity);
	}
	public void update(CatalogEntity entity) {
		catalogDao.update(entity);
	}
	public void delete(String id)  {
		catalogDao.delete(id);
	}

	public void delete(CatalogEntity entity) {
		catalogDao.delete(entity);
	}

	public List<CatalogEntity> list() {
		return catalogDao.list();
	}

	public List<CatalogEntity> list(int start, int end) {
		return catalogDao.list(start, end);
	}

	public List<CatalogEntity> list(CatalogEntity entity) throws Exception {
		return catalogDao.list(entity);
	}

	public CatalogEntity get(String id) {
		return catalogDao.get(id);
	}
 
	
	
}
