package com.timer;

import java.util.ArrayList;
import java.util.List;

import com.digger.advertisement.JingDongAdvertisementDigger;
import com.digger.keyword.HotKeywordsDigger;
import com.entity.AdvertisementEntity;
import com.entity.CommodityEntity;
import com.entity.KeywordEntity;
import com.service.AdvertisementService;
import com.service.CommodityService;
import com.service.KeywordService;
import com.utils.L;

public class DailyTask extends java.util.TimerTask {

	@Override
	public void run() {
		L.trace(this, "start");
		
		
		//advertise
		String url = "http://www.jd.com/";	//京东广告页 moreSubject.aspx
		List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
		new JingDongAdvertisementDigger().dig(url, list);
		
		AdvertisementService advertisementService = new AdvertisementService();
		int newAdCount = 0;
		for(AdvertisementEntity ad : list){
			boolean result = advertisementService.add(ad);
			if(result){
				newAdCount++;
			}
		}
		L.trace(this, newAdCount + " new advertisement added");
		
		//dig new keyword
		new HotKeywordsDigger().dig();
		
		
		//fresh keyword cache
		new KeywordService().fresh();
		 
		

		
		
		
		
		L.trace(this, "end");
		
	}// end run

 
	

}
