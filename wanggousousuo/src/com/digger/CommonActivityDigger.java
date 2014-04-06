package com.digger;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.connect.Connecter;
import com.digger.vo.Product;
import com.env.StaticInfo;
import com.exception.BaseException;
import com.seeker.commodity.analyzer.CommentAnalyzer;
import com.seeker.commodity.analyzer.ImgAnalyzer;
import com.seeker.commodity.analyzer.TitleAnalyzer;
import com.shop.ShopInfo;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.ShopNames;
import com.utils.X;

public class CommonActivityDigger extends WebBaseDigger implements Digger{
	String basePath; //rule 文件夹位置
	String shopName;
	
	public CommonActivityDigger(String shopName,  String basePath) {
		this.shopName = shopName;
		this.basePath = basePath;
	}
	
	public List<String> dig(String keyword,File ruleFile, String encodeCharset){
		
		List<String> imgurlList = new ArrayList<String>();
		
		ShopInfo shopinfo = StaticInfo.getShopbyName(shopName);
		if(shopinfo == null){
			L.exception(this, "can not find shop --- " + shopName);
			return new ArrayList<String>();
		}
		String url = shopinfo.getActivityUrl();
		if(url==null || url.length()==0){
			return new ArrayList<String>();
		}
		
		try {	 
			
			String responseString = Connecter.connect(url, null);
			
			Document doc  = new org.jdom2.input.SAXBuilder().build(new StringReader(responseString));
			List<Element> imgList = X.selectNodes(doc, "//img");
			//List<Element> imgList = X.selectNodes(doc, "//a[count(.//img) >0 and count(.//img)<2 ]");
			//for(Element link : imgList){
//				if(link.getAttribute("href")!=null && link.getAttribute("href").getValue().startsWith("http://")){
//					imgurlList.add(link.getAttribute("href").getValue());
//					//System.out.println( link.getAttribute("href").getValue());
//				}
//			}
			
			for(Element imgElement : imgList){
				List<Attribute> imgAttrList =  imgElement.getAttributes();
				DataInputStream dis = null;
				BufferedImage buff = null;
				for(Attribute a : imgAttrList){//遍历所有attribute
					String attr = a.getValue();
					if(attr!=null && attr.length()>10 && attr.startsWith("http") ){ //&& attr.startsWith("http")
						try{
							URL imgurl = new URL( attr);
							dis = new DataInputStream(imgurl.openStream());
					        buff = ImageIO.read(dis); 
					        if(buff == null){
					        	continue;// io 异常 跳过
					        }
						
						}catch (Exception e) {
							continue;//url不合法 跳过
						}
						//判断是不是活动图片
						if( buff.getWidth() *buff.getHeight() > 100*200    ){
							//System.out.println(attr);
							//imgurlList.add(attr);
							if(imgElement.getParentElement()!=null ){
								String activityurl = "";
								// a/img
								if(imgElement.getParentElement().getAttribute("href")!=null){
									activityurl =(imgElement.getParentElement().getAttribute("href").getValue());
								}
								//   a/*/img
								else if(imgElement.getParentElement().getParentElement()!=null 
										&&imgElement.getParentElement().getParentElement().getAttribute("href")!=null){
									activityurl = (imgElement.getParentElement().getParentElement().getAttribute("href").getValue());
								}
								//  a/*/*/img
								else if(imgElement.getParentElement().getParentElement().getParentElement()!=null
										&&imgElement.getParentElement().getParentElement().getParentElement().getAttribute("href")!=null){
									activityurl = (imgElement.getParentElement().getParentElement().getParentElement().getAttribute("href").getValue());
								}	
								if(activityurl!=null && activityurl.length()>1){
									if(activityurl.startsWith("http")){
										imgurlList.add(activityurl);
									}
									else{
										imgurlList.add(shopinfo.getDomainName()+""+activityurl);
									}
								}
								
							}
							
						}
					  
					  
					}
				}//for
			}//for
	 
			return imgurlList;
		
		} catch (Exception e) {
			L.exception(this, url + " --- "+e.getMessage());
			e.printStackTrace();
		} 
		return new ArrayList<String>();
		
	}
 

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("begin");
		System.out.println(System.currentTimeMillis());
		
		try {
			L.level = LogLevel.debug;
			
			Map<String, ShopInfo> shops = StaticInfo.getShops();
			
//			List<String> list = new ArrayList<String>();
//			for(Entry<String, ShopInfo> shop : shops.entrySet()){
//				List<String> listOfOneshop = new CommonActivityDigger(shop.getValue().getShopName(), "").dig(null,null,null);
//				list.addAll(listOfOneshop);
//			}
//			
			List<String> list = new CommonActivityDigger("tmall", "").dig(null,null,null);
			
			//System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			//System.out.println("<Annotations start=\"0\" num=\""+list.size()+"\" total=\""+list.size()+"\">");
//			System.out.println("<Annotations>");
//			
//			for(String s : list){
//				System.out.println("<Annotation about=\"" + s + "\">");
//				System.out.println("<Label name=\"_cse_rpx9g2rlads\"/>");
//				System.out.println("<AdditionalData attribute=\"original_url\" value=\""+s+"\" />");
//				System.out.println("</Annotation>");
//				
//			}
//			System.out.println("</Annotations>");
			
			
			System.out.println(list.size());
			for(String s : list){
				System.out.println(s);
			}
			
			
			
			System.out.println(System.currentTimeMillis());
			System.out.println("end");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> digAll(String keyword) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customPick(Product product, Element xml) {
		// TODO Auto-generated method stub
		
	}

 
}
