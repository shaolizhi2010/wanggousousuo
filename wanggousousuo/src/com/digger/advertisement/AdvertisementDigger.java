package com.digger.advertisement;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.digger.util.CleanUtils;
import com.digger.util.ImgUtil;
import com.entity.AdvertisementEntity;
import com.html.Html;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.utils.L;
import com.utils.U;
import com.utils.X;
import com.vo.ImageVO;

/**
 * 抓取打折促销信息
 * 
 * @author Administrator
 *
 */
public class AdvertisementDigger {

	public static void main(String[] args) {
		
		L.always("", "start");
		String href = "http://sale.jd.com/act/fxHO2ryas1PI.html";

		//List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
		AdvertisementEntity entity =  dig(href);
		//Collections.sort(list);
		//U.printList(list);
		System.out.println(entity);
		L.always("", "end");
	}
	
	/**
	 * 
	 * input 网页url
	 * output 网页包含的广告信息，包括图片url，网页url
	 * 	但不包括网页的title 和 description
	 * 
	 * @param href
	 * @return
	 */
	public static AdvertisementEntity dig(String href){
		List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
		
		dig(href, list);
		
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		else{
			return new AdvertisementEntity();
		}
		
		
		
		
	}
	
	public static void dig(String href, List<AdvertisementEntity> list){
		
		Html html = Connecter.getHtml(href,"utf-8");
		Document doc = html.getDoc();
		
		String title = html.title();
		String description = html.description();
 
		List<Element> imgElementlist = X.getSubElementList(doc,"//img");

		
		for (Element img : imgElementlist) {
			
			AdvertisementEntity entity = new AdvertisementEntity();
			
		    //String href = link.getAttributeValue("href");
		    
		    
		    List<Attribute> imgAttrList =  img.getAttributes();
		    
		    for(Attribute a : imgAttrList){//遍历所有attribute
		    	String attr = a.getValue();
				if(attr!=null && attr.length()>10 && attr.startsWith("http") ){ //&& attr.startsWith("http")
					try{


						    if ( StringUtils.isBlank(attr)) {	//check src
						        continue;
						    }
						    
						    ImageVO imgvo = ImgUtil.get(attr);
						    int imgSize = imgvo.getSize();
						    
						    if(imgSize> ImgUtil.advImgSize 
						    		&& imgvo.getWidth()/imgvo.getHeight() < 2
						    		&& imgvo.getWidth()/imgvo.getHeight() > 1/2){
						        
						    	
						    	/* 
						         * 再次通过width 和 height 属性取 img size，如果有width 和 height，则以这个为准 
						         * 这样做是为了防止某些图片很大，但在网页上显示的很小，
						         * 这种情况应该按小图片处理
						         * */
						        imgSize = ImgUtil.get(img).getSizeInPage();
						    }
						    
						    //找到 打折促销信息
						    if (imgSize > ImgUtil.advImgSize
						    		) {
						   	 	entity.setImgUrl(attr);
							    entity.setUrl(href);
							    entity.setImgsize(imgSize+"");
							    entity.setName(title);
							    entity.setDescription(description);
							    list.add(entity);
						    }
						
					}catch (Exception e) {
						continue;//url不合法 跳过
					}
				}
		    }
		    Collections.sort(list);
		    
		  
		
		}
			 
		
	}

}
