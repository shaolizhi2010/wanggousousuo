package com.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.utils.App;
import com.utils.L;
import com.utils.U;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import com.entity.KeywordEntity;

public class KeywordDao {
	
	private DB db = App.getInstance().getDBContext();
	private DBCollection collection = db.getCollection("keyword");
	
	public void add(KeywordEntity entity) {
		try {
			
			collection = db.getCollection("keyword");
			DBObject dbo = U.toDBObject(entity);
			
			
			collection.insert(dbo);

		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
	
	public void update(KeywordEntity entity){
		
		DBObject dbo = U.toDBObject(entity);
		
		DBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(entity.getId()));
		
		collection.update(q, dbo);
		
		//entity.setName(U.toString( dbo.get("name") ));
		
	}
	
    public void delete(String id) {  
    	try {
			DBObject o = new BasicDBObject();
			o.put("_id", new ObjectId(id));
			
			collection.remove(o);
    	} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
    }  
    
	public void delete(KeywordEntity entity) {
		try {
			String id = entity.getId();
			delete(id);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
    
    public List<KeywordEntity> list(){
		return list(new KeywordEntity(),0,200);
	}
	
	public List<KeywordEntity> list(KeywordEntity entity){
		return list(entity,0,200);
	}
	
	
	
    //分页
    public List<KeywordEntity> list(int start, int limit){
		
		return list(new KeywordEntity(),start,limit);
	}
	
	 public List<KeywordEntity> list(KeywordEntity entity,int start, int limit) {
			
			List<KeywordEntity> entityList = new ArrayList<KeywordEntity>();
			DBObject dbo = U.toDBObject(entity);
	
			DBObject sortObj = new BasicDBObject();
			sortObj.put("orderNumber", 1);
			sortObj.put("_id", -1);
			Iterator<DBObject> list = collection.find(dbo).sort(sortObj).skip(start).limit(limit).iterator();
			
			while(list.hasNext()){
				
				DBObject dbo1 = list.next();
				KeywordEntity e = U.toEntity(dbo1, KeywordEntity.class);
				entityList.add(e);
			}
			return entityList;
		 
	}
	
	public KeywordEntity get(String id){
		try {
		 	
			DBObject o = new BasicDBObject();
			o.put("_id", new ObjectId(id));
			
			DBObject dbo1 = collection.findOne(o);
			
			KeywordEntity e = U.toEntity(dbo1, KeywordEntity.class);
			
			return e;
    	} catch (Exception e) {
			L.exception(this, e.getMessage());
			return new KeywordEntity();
		}
	}
	
 
}
