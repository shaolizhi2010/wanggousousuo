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

import com.entity.ShopEntity;

public class ShopDao {
	
	private DB db = App.getInstance().getDBContext();
	private DBCollection collection = db.getCollection("shop");
	
	public void add(ShopEntity entity) {
		try {
			
			collection = db.getCollection("shop");
			DBObject dbo = U.toDBObject(entity);
			
			collection.insert(dbo);

		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
	
	public void update(ShopEntity entity){
		
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
    
	public void delete(ShopEntity entity) {
		try {
			String id = entity.getId();
			delete(id);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
    
    public List<ShopEntity> list(){
		return list(new ShopEntity());
	}
	
    //分页
    public List<ShopEntity> list(int start, int end){
		
    	if(start == 0){
    		return list();
    	}
    	return list();
	}
	
	 public List<ShopEntity> list(ShopEntity entity) {
			
			List<ShopEntity> entityList = new ArrayList<ShopEntity>();
			DBObject dbo = U.toDBObject(entity);;
	
			DBObject sortObj = new BasicDBObject();
			sortObj.put("_id", -1);
			Iterator<DBObject> list = collection.find(dbo).sort(sortObj).iterator();
			
			while(list.hasNext()){
				
				DBObject dbo1 = list.next();
				ShopEntity e = U.toEntity(dbo1, ShopEntity.class);
				entityList.add(e);
			}
			return entityList;
		 
	}
	
	public ShopEntity get(String id){
		try {
		 	
			DBObject o = new BasicDBObject();
			o.put("_id", new ObjectId(id));
			
			DBObject dbo1 = collection.findOne(o);
			
			ShopEntity e = U.toEntity(dbo1, ShopEntity.class);
			
			return e;
    	} catch (Exception e) {
			L.exception(this, e.getMessage());
			return new ShopEntity();
		}
	}
	
 
}
