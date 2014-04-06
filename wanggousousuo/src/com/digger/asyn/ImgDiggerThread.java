package com.digger.asyn;

import java.util.HashMap;
import java.util.Map;

import com.utils.App;
import com.utils.L;

/**
 * too slow, need 5 sec
 * @author shaoliz
 *
 */
@Deprecated
public class ImgDiggerThread implements Runnable {
	
	Map<String, String> productInfo = new HashMap<String, String>();
	String url;
	String imgKey;
	String imgAttr;
	
	public ImgDiggerThread(Map<String, String> productInfo, String imgKey, String imgAttr){
		this.productInfo = productInfo;
		this.url = productInfo.get("url");
		this.imgKey = imgKey;
		this.imgAttr = imgAttr;
	}
	
	@Override
	public void run() {
		
//		try{
//			
//			//String imgUrl = new ImgDigger().digImgUrl(url, imgKey, imgAttr);
//			L.debug(this, "--- webimg url --- " + imgUrl);
//			if(StringUtils.isNotBlank(imgUrl)){
//				productInfo.put("imgUrl",imgUrl);
//				productInfo.put("imgFromWeb","1"); //set the flag
//			}
//		}catch (Exception e) {
//			L.exception(this, e.getMessage());	//do not throw any exception
//		}
//		

		
	}

}
