package com.timer;

import java.util.ArrayList;
import java.util.List;

import com.digger.advertisement.JingDongAdvertisementDigger;
import com.entity.AdvertisementEntity;
import com.service.AdvertisementService;
import com.utils.L;

public class DailyTask extends java.util.TimerTask {

	@Override
	public void run() {
		L.trace(this, "start");
		
		L.trace(this, "start dig jing dong advertisement");
		String url = "http://www.jd.com/";	//京东广告页 moreSubject.aspx
		List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
		new JingDongAdvertisementDigger().dig(url, list);
		
		AdvertisementService advertisementService = new AdvertisementService();
		for(AdvertisementEntity ad : list){
			advertisementService.add(ad);
		}
		
		L.trace(this, "end");
		
	}// end run

 
	

}
