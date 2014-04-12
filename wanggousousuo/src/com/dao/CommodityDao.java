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

import com.entity.CommodityEntity;

public class CommodityDao {
	
	private DB db = App.getInstance().getDBContext();
	private DBCollection collection = db.getCollection("commodity");
	
	public void add(CommodityEntity entity) {
		try {
			
			DBCollection collection = db.getCollection("commodity");
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
    
	public void delete(CommodityEntity entity) {
		try {
			String id = entity.getId();
			delete(id);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
	}
    
    public List<CommodityEntity> list(){
		return list(new CommodityEntity());
	}
	
    //分页
    public List<CommodityEntity> list(int start, int end){
		
    	if(start == 0){
    		return list();
    	}
    	return list();
	}
	
	 public List<CommodityEntity> list(CommodityEntity entity) {
			
			List<CommodityEntity> entityList = new ArrayList<CommodityEntity>();
			DBObject dbo = U.toDBObject(entity);;

	
			Iterator<DBObject> list = collection.find(dbo).iterator();
			
			
			while(list.hasNext()){
				
				DBObject dbo1 = list.next();
				CommodityEntity e = U.toEntity(dbo1, CommodityEntity.class);
				entityList.add(e);
			}
			return entityList;
		 
	}
	
	public CommodityEntity get(String id){
		try {
		 	
			DBObject o = new BasicDBObject();
			o.put("_id", new ObjectId(id));
			
			DBObject dbo1 = collection.findOne(o);
			
			CommodityEntity e = U.toEntity(dbo1, CommodityEntity.class);
			
			return e;
    	} catch (Exception e) {
			L.exception(this, e.getMessage());
			return new CommodityEntity();
		}
	}
	
	public void update(CommodityEntity entity){
		
		DBObject o = new BasicDBObject();
		
		//DBObject dbo = collection.update(q, o)
		
		//entity.setName(U.toString( dbo.get("name") ));
		
	}
 
}
