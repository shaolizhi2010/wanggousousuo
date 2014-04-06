package com.dao;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.baseDao.BaseDao;
import com.entity.SearchHistoryEntity;
import com.utils.L;

public class SearchHistoryDao extends BaseDao{
	
	public SearchHistoryEntity save(SearchHistoryEntity entity) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.save(entity);
			tx.commit();
		
			session.close();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SearchHistoryEntity get(String shopname, String keyword) {
		L.log(this, "shopname = " + shopname + " keyword=" + keyword);
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from search_history where shopname=:shopname and keyword=:keyword");
		
		query.setString("shopname", shopname);
		query.setString("keyword", keyword);
		query.addEntity(SearchHistoryEntity.class);
		
		Object o = query.uniqueResult();
		
		session.close();
		
		if(o==null){
			return new SearchHistoryEntity();
		}
		return (SearchHistoryEntity)o;
	}
	
	public List keywords() {
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select distinct keyword from search_history");
		
		List l = query.list();
		session.close();
		
		return l;
	}
	
	public List<SearchHistoryEntity> list() {
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from search_history");
		query.addEntity(SearchHistoryEntity.class);
		List<SearchHistoryEntity> l = query.list();
		session.close();
		
		return l;
	}
	
	
	public SearchHistoryEntity delete(SearchHistoryEntity entity) {
		try {
			Session sess = sessionFactory.openSession();
			Transaction tx = sess.beginTransaction();
			sess.delete(entity);
			tx.commit();
			sess.close();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
