package test;

import java.util.Iterator;

import com.entity.CommodityEntity;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.utils.App;
import com.utils.U;
 

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		try {
			System.out.println("begin");
			
			DB db = App.getInstance().getDBContext();
			DBCollection searchHistory = db.getCollection("commodity");
			
			DBCursor cursor = searchHistory.find();
			Iterator<DBObject> iter =  cursor.iterator();
			while(iter.hasNext()){
				DBObject dbo =  iter.next();
				//System.out.println(dbo.get("shopname"));
				//System.out.println(dbo.get("keyword"));
				CommodityEntity item = U.toEntity(dbo, CommodityEntity.class);
						System.out.println(item.getKeyword());
						System.out.println(item.getName());
						System.out.println(item.getUrl());
						System.out.println(item.getImgUrl());
						System.out.println(item.getCommentCount());
						System.out.println(item.getSource());
						System.out.println(item.getCommentUrl());

			}
			
			System.out.println("end");
			//conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
