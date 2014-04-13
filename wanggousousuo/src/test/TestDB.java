package test;

import java.util.Iterator;
import java.util.List;

import com.entity.CommodityEntity;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.service.CommodityService;
import com.utils.App;
import com.utils.U;
 

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		try {
			//System.out.println("begin");
			
			DB db = App.getInstance().getDBContext();
			DBCollection searchHistory = db.getCollection("commodity");
			
			CommodityEntity entity = new CommodityEntity();
			entity.setKeyword("旧款");
			
			List<CommodityEntity> list =  new CommodityService().list(entity);
			for(CommodityEntity item : list){
				////System.out.println(dbo.get("shopname"));
				////System.out.println(dbo.get("keyword"));
						//System.out.println(item.getKeyword());
						//System.out.println(item.getName());
						//System.out.println(item.getUrl());
						//System.out.println(item.getImgUrl());
						//System.out.println(item.getCommentCount());
						//System.out.println(item.getSource());
						//System.out.println(item.getCommentUrl());

			}
			
			//System.out.println("end");
			//conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
