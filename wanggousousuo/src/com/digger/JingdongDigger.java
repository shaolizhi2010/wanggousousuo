package com.digger;

import java.util.List;
import java.util.Map;

import org.jdom2.Element;

import com.connect.SimpleConnecter;
import com.entity.CommodityEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utils.L;
import com.utils.X;

public class JingdongDigger extends CommonCommodityDigger{

	public JingdongDigger(String shopname, String basePath) {
		super(shopname, basePath);
	}
	
	@Override
	protected String getPrice(Element e, String pricePath){
		String priceId = X.getAttrValue(e, ".//*[contains(@class,'J_')]", "class");
		String url = "http://p.3.cn/prices/mgets?skuids="+priceId;
		String priceJson = "";
		String price = "";
		try {
			priceJson = SimpleConnecter.connect(url);
			List<Map> list = new Gson().fromJson(priceJson, new TypeToken<List<Map>>(){}.getType() );

//			System.out.println("price url "+ url);
			
			if(list.get(0).get("p") !=null){
				price = list.get(0).get("p").toString();
			}
		} catch (Exception e2) {
			L.debug(this, "取京东 price 出错 priceJson - " +priceJson );
		}

		return price;
	}
 
	public static void main(String[] args) {
		
		String preUrl = "http://search.jd.com/Search?keyword=";
		String keyword = "T恤";
		
		List<CommodityEntity> plist = new JingdongDigger("jingdong", "").digAll(keyword);
		System.out.println(plist.size());
		for(CommodityEntity p: plist){
			
			System.out.println(p.toString());
		}
		
		System.out.println("end");
	}

	
		
}
