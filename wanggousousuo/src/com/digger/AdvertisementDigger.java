package com.digger;

import java.io.StringReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;

import com.digger.util.CleanUtils;
import com.digger.util.ImgUtil;
import com.entity.AdvertisementEntity;
import com.utils.L;
import com.utils.X;

public class AdvertisementDigger {

	public static void main(String[] args) {
		
	}
	
	public static void dig(String content, List<AdvertisementEntity> list){
		
		String xmlString = CleanUtils.clean(content);
		
		Document doc = null;
		try {
			doc = new org.jdom2.input.SAXBuilder().build(new StringReader(xmlString));
		} catch (Exception e) {
			L.exception("AdvertisementDigger", e.getMessage());
			L.exception("AdvertisementDigger", "提取广告内容失败，string转xml出错");
			return;
		}  
		List<Element> linkElementlist = X.getSubElementList(doc,"//a[count( ./img ) = 1]");

		
		for (Element link : linkElementlist) {
			
			AdvertisementEntity entity = new AdvertisementEntity();
			
		    String href = link.getAttributeValue("href");
		    String imgsrc = link.getChild("img").getAttributeValue("src");
		    String alt = link.getAttributeValue("alt");//图片说明
		    try {
				//href = URLDecoder.decode(href );
				//imgsrc = URLDecoder.decode(imgsrc );
			} catch ( Exception e) {
				e.printStackTrace();
			}
		    
		    
		    if ( StringUtils.isBlank(imgsrc)) {	//check src
		        continue;
		    }
		
		    int imgSize = ImgUtil.size(imgsrc);
		    
		    if(imgSize> ImgUtil.advImgSize ){
		        /* 
		         * 再次通过width 和 height 属性取 img size，如果有width 和 height，则以这个为准 
		         * 这样做是为了防止某些图片很大，但在网页上显示的很小，
		         * 这种情况应该按小图片处理
		         * */
		      
		        imgSize = ImgUtil.size(link.getChild("img"),imgSize);
		    }
		    
		    //找到 打折促销信息
		    if (imgSize > ImgUtil.advImgSize) {
		   	 	entity.setImgUrl(imgsrc);
			    entity.setUrl(href);
			    list.add(entity);
		    }
		
		}
			 
		
	}

}
