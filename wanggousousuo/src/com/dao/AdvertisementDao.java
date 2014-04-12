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
			
			DBCollection collection = db.getCollection("advertisement");
			DBObject dbo = new BasicDBObject();
			
if(U.toString(entity.getId()).length()>0){dbo.put("_id", new ObjectId(entity.getId()));}
			
			

			if(U.toString(entity.getUrl()).length()>0){
				dbo.put("url", entity.getUrl());
			}			
			if(U.toString(entity.getImgUrl()).length()>0){
				dbo.put("imgUrl", entity.getImgUrl());
			}			
			if(U.toString(entity.getName()).length()>0){
				dbo.put("name", entity.getName());
			}			
			if(U.toString(entity.getDescription()).length()>0){
				dbo.put("description", entity.getDescription());
			}
			
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
			DBObject dbo = new BasicDBObject();
if(U.toString(entity.getId()).length()>0){dbo.put("_id", new ObjectId(entity.getId()));}
			
			if(U.toString(entity.getUrl()).length()>0){
				dbo.put("url", entity.getUrl());
			}			
			if(U.toString(entity.getImgUrl()).length()>0){
				dbo.put("imgUrl", entity.getImgUrl());
			}			
			if(U.toString(entity.getName()).length()>0){
				dbo.put("name", entity.getName());
			}			
			if(U.toString(entity.getDescription()).length()>0){
				dbo.put("description", entity.getDescription());
			}
			
			DBObject sortObj = new BasicDBObject();
			sortObj.put("_id", -1);
			Iterator<DBObject> list = collection.find(dbo).sort(sortObj).iterator();
			
			while(list.hasNext()){
				
				AdvertisementEntity e = new AdvertisementEntity();
				DBObject dbo1 = list.next();
				
e.setId(dbo1.get("_id").toString());
				e.setUrl(  U.toString( dbo1.get("url") ) );
				e.setImgUrl(  U.toString( dbo1.get("imgUrl") ) );
				e.setName(  U.toString( dbo1.get("name") ) );
				e.setDescription(  U.toString( dbo1.get("description") ) );

				entityList.add(e);
			}
			return entityList;
		 
	}
	
	public AdvertisementEntity get(String id){
		try {
		 	AdvertisementEntity e = new AdvertisementEntity();
			DBObject o = new BasicDBObject();
			o.put("_id", new ObjectId(id));
			
			DBObject dbo1 = collection.findOne(o);
			
			e.setId(dbo1.get("_id").toString());
				e.setUrl(  U.toString( dbo1.get("url") ) );
				e.setImgUrl(  U.toString( dbo1.get("imgUrl") ) );
				e.setName(  U.toString( dbo1.get("name") ) );
				e.setDescription(  U.toString( dbo1.get("description") ) );

			
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
