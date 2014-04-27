package com.digger.advertisement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;

import com.connect.Connecter;
import com.entity.AdvertisementEntity;
import com.html.Html;
import com.utils.L;
import com.utils.U;
import com.utils.X;

public class JingDongAdvertisementDigger {

	public static void main(String[] args) {
		
		Html html = Connecter.getHtml("http://www.jd.com");
		Document doc = html.getDoc();
		List<Element> linkElementlist = X.getSubElementList(doc,"//a");
		
		List<String> hrefList = new ArrayList<String>();
		
		
		for (Element link : linkElementlist) {
			
			String href = link.getAttributeValue("href");
			if(StringUtils.isNoneBlank(href) && StringUtils.startsWith(href, "http://sale.jd.com/act/")){
				hrefList.add(href);
			}
			
		}
		
		//U.printList(hrefList);
		
		for(String href : hrefList){
			AdvertisementEntity entity = AdvertisementDigger.dig(href);
			L.always(null, href);
			L.always(null, entity.getImgUrl());
			L.always(null, "------");
		}
		
	}
	
	/**
	 * input 京东广告页总页的url
	 * output 页里的所有广告
	 * 
	 * @param list
	 */
	public void dig(String url, List<AdvertisementEntity> list ){
		
		//String url = "http://www.jd.com/moreSubject.aspx";	//京东广告页
		
		Html html = Connecter.getHtml(url);
		Document doc = html.getDoc();
		List<Element> linkElementlist = X.getSubElementList(doc,"//a");
		
		List<String> hrefList = new ArrayList<String>();
		
		for (Element link : linkElementlist) {
			
			String href = link.getAttributeValue("href");
			if(StringUtils.isNoneBlank(href) && StringUtils.startsWith(href, "http://sale.jd.com/act/")){
				hrefList.add(href);
			}
		}
		
		for(String href : hrefList){
			AdvertisementEntity entity = AdvertisementDigger.dig(href);
			list.add(entity);
		}
		
	}

}
