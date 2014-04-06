package com.dao.baseDao;

import org.hibernate.SessionFactory;

import com.utils.App;

public class BaseDao {

	protected static SessionFactory sessionFactory = App.getInstance().getSessionFactory();
	
	
}
