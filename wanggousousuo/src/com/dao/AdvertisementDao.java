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

import com.entity.AdvertisementEntity;

public class AdvertisementDao {
	
	private DB db = App.getInstance().getDBContext();
	private DBCollection collection = db.getCollection("advertisement");
	
	public void add(AdvertisementEntity entity) {
		try {
			
			collection = db.getCollection("advertisement");
			DBObject dbo = U.toDBObject(entity);
			
			collection.insert(dbo);

		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
	
	public void update(AdvertisementEntity entity){
		
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
    
	public void delete(AdvertisementEntity entity) {
		try {
			String id = entity.getId();
			delete(id);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
    
    public List<AdvertisementEntity> list(){
		return list(new AdvertisementEntity(),0,200);
	}
	
	public List<AdvertisementEntity> list(AdvertisementEntity entity){
		return list(entity,0,200);
	}
	
	
	
    //分页
    public List<AdvertisementEntity> list(int start, int limit){
		
		return list(new AdvertisementEntity(),start,limit);
	}
	
	 public List<AdvertisementEntity> list(AdvertisementEntity entity,int start, int limit) {
			
			List<AdvertisementEntity> entityList = new ArrayList<AdvertisementEntity>();
			DBObject dbo = U.toDBObject(entity);;
	
			DBObject sortObj = new BasicDBObject();
			sortObj.put("orderNumber", 1);
			sortObj.put("_id", -1);
			Iterator<DBObject> list = collection.find(dbo).sort(sortObj).skip(start).limit(limit).iterator();
			
			while(list.hasNext()){
				
				DBObject dbo1 = list.next();
				AdvertisementEntity e = U.toEntity(dbo1, AdvertisementEntity.class);
				entityList.add(e);
			}
			return entityList;
		 
	}
	
	public AdvertisementEntity get(String id){
		try {
		 	
			DBObject o = new BasicDBObject();
			o.put("_id", new ObjectId(id));
			
			DBObject dbo1 = collection.findOne(o);
			
			AdvertisementEntity e = U.toEntity(dbo1, AdvertisementEntity.class);
			
			return e;
    	} catch (Exception e) {
			L.exception(this, e.getMessage());
			return new AdvertisementEntity();
		}
	}
	
 
}
