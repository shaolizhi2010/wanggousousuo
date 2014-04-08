package com.digger.util;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Element;

import com.utils.L;

public class ImgUtil {
	
	public static final int advImgSize = 10000;
	
	public static boolean isSrc(String src) {
		if (StringUtils.isBlank(src) ) {
			return false;
		}
		return true;
	}

	public static int size(String src) {
		int imgSize = 0;

		DataInputStream dis = null;
		BufferedImage buff = null;
		
		//get img buffer
		try {
			URL imgurl = new URL(src);
			dis = new DataInputStream(imgurl.openStream());
			buff = ImageIO.read(dis);

		} catch (Exception e) {
			return -1;
		}
		
		//check buff
		if (buff == null) {
			return -1;
		}
		
		//get img size
		imgSize = buff.getWidth() * buff.getHeight();
		
		//close io
		if (dis != null) {// 关闭流
			try {
				dis.close();
			} catch (IOException e) {
				dis = null;
			}
		}
		return imgSize;
		
	}
	
	public static int size(Element imgElement,int imgSize){
		
		if(imgElement == null){
			return imgSize;
		}
		
		
		String imgWidth = imgElement.getAttributeValue("width");
		String imgHeight = imgElement.getAttributeValue("height");
		
		try {
	            if (StringUtils.isNotBlank(imgWidth)
	                    && StringUtils.isNotBlank(imgHeight)) {
	                imgSize = Integer.parseInt(imgWidth)
	                        * Integer.parseInt(imgHeight);
	            }
        } catch (Exception e) {
            L.exception("ImgUtil", "计算img size 出错 ");
            L.exception("ImgUtil", e.getMessage());
        	return imgSize;
        }
		
		return imgSize;
	}
	
	/**
	 * 有时 img 的地址 并不在src属性里
	 * 比如页面需延迟加载图片时
	 * 此方法遍历 img element 所有的 属性，并计算出img的地址
	 * @param imgElement
	 * @param minSize
	 * @param domainName
	 * @return
	 */
	public String getSrc(Element imgElement, String domainName) {
		
		if(domainName == null){
			domainName = "";
		}

		/* 准备遍历所有attribute 因为img 属性不一定是src 有些延迟加载会使用自定义属性 */
		List<Attribute> imgAttrList = imgElement.getAttributes();
		DataInputStream dis = null;
		BufferedImage buff = null;
		for (Attribute a : imgAttrList) {/* 遍历所有attribute*/
			String attrName = a.getName().trim();
			if(attrName.equalsIgnoreCase("class")
					||attrName.equalsIgnoreCase("id")
					||attrName.equalsIgnoreCase("style")
					||attrName.equalsIgnoreCase("width")
					||attrName.equalsIgnoreCase("height")){
				continue;
			}
			
			String attrValue = a.getValue();
			if (attrValue != null && attrValue.length() > 10 ) { // &&
				
				//某些图片地址会省略域名，次处补全
				if(!attrValue.startsWith("http") && StringUtils.isNotBlank(domainName)){
					attrValue = domainName +"/"+ attrValue;
					attrValue = attrValue.replace("///", "/");
					attrValue = attrValue.replace("//", "/");
					attrValue = "http://"+attrValue;
				}
				
				try {
					URL imgurl = new URL(attrValue);
					dis = new DataInputStream(imgurl.openStream());
					buff = ImageIO.read(dis);
					if (buff == null) {
						continue;// io 异常 跳过，说明不是合法的img地址
					}

				} catch (Exception e) {
					continue;// url不合法 跳过
				}

				if (dis != null) {// 关闭流
					try {
						dis.close();
					} catch (IOException e) {
						dis = null;
					}
				}
				
				//找到图片
				if (buff.getWidth() * buff.getHeight() > 1) {
					return attrValue;
				}
			}
		}// end for attr
		
		//循环结束仍没找到图片
		return "";

	}
}
