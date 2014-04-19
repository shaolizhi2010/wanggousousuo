package com.service;

import java.util.List;

import com.dao.BlogDao;
import com.entity.BlogEntity;

public class BlogService {
	BlogDao blogDao = new BlogDao();

	public void add(BlogEntity entity) {
		blogDao.add(entity);
	}

	public void delete(String id)  {
		blogDao.delete(id);
	}

	public void delete(BlogEntity entity) {
		blogDao.delete(entity);
	}

	public List<BlogEntity> list() {
		return blogDao.list();
	}

	public List<BlogEntity> list(int start, int end) {
		return blogDao.list(start, end);
	}

	public List<BlogEntity> list(BlogEntity entity) throws Exception {
		return blogDao.list(entity);
	}

	public BlogEntity get(String id) {
		return blogDao.get(id);
	}
 
	
	
}
