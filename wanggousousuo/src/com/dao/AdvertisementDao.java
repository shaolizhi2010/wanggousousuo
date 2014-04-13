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
		return list(new AdvertisementEntity());
	}
	
    //分页
    public List<AdvertisementEntity> list(int start, int end){
		
    	if(start == 0){
    		return list();
    	}
    	return list();
	}
	
	 public List<AdvertisementEntity> list(AdvertisementEntity entity) {
			
			List<AdvertisementEntity> entityList = new ArrayList<AdvertisementEntity>();
			DBObject dbo = U.toDBObject(entity);;
	
			DBObject sortObj = new BasicDBObject();
			sortObj.put("_id", -1);
			Iterator<DBObject> list = collection.find(dbo).sort(sortObj).iterator();
			
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
	
	public void update(AdvertisementEntity entity){
		
		DBObject o = new BasicDBObject();
		
		//DBObject dbo = collection.update(q, o)
		
		//entity.setName(U.toString( dbo.get("name") ));
		
	}
 
}
