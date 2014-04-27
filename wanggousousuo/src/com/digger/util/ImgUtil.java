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
import com.vo.ImageVO;

public class ImgUtil {
	
	public static final int advImgSize = 10000;
	
	public static boolean isSrc(String src) {
		if (StringUtils.isBlank(src) ) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * input 图片地址
	 * output 如符合规则，返回图片 长乘以宽，否则返回-1
	 * @param src
	 * @return
	 */
	public static ImageVO get(String src) {
		int imgSize = 0;

		DataInputStream dis = null;
		BufferedImage buff = null;
		
		//get img buffer
		try {
			URL imgurl = new URL(src);
			dis = new DataInputStream(imgurl.openStream());
			buff = ImageIO.read(dis);
			
			//check buff
			if (buff == null) {
				return new ImageVO();
			}
			
			//get img size
			ImageVO vo = new ImageVO();
			
			vo.setWidth(buff.getWidth() );
			vo.setHeight(buff.getHeight());
			vo.setSize(buff.getWidth()*buff.getHeight());
			return vo;

		} catch (Exception e) {
			return new ImageVO();
		} finally{
			//close io
			if (dis != null) {// 关闭流
				try {
					dis.close();
				} catch (IOException e) {
					dis = null;
				}
			}
		}
		
	}
	
	/**
	 * input img element
	 * output img 在网页中如果有长宽限制，返回限制的长和 宽
	 * 
	 * @param imgElement
	 * @return
	 */
	public static ImageVO get(Element imgElement){
		
		if(imgElement == null){
			return new ImageVO();
		}
		
		
		String imgWidth = imgElement.getAttributeValue("width");
		
		String imgHeight = imgElement.getAttributeValue("height");
		
		try {
	            if (StringUtils.isNotBlank(imgWidth)
	                    && StringUtils.isNotBlank(imgHeight)) {
	            	
	            	ImageVO vo = new ImageVO();
	            	
	            	imgWidth = imgWidth.replaceAll("px", "");
	            	imgHeight = imgHeight.replaceAll("px", "");
	            	
	            	int imgWidthInt = Integer.parseInt(imgWidth);
	            	int imgHeightInt = Integer.parseInt(imgHeight);
	            	
	            	
	                vo.setWidthInPage(imgWidthInt);
	                vo.setHeightInPage(imgHeightInt);
	                vo.setSizeInPage(imgHeightInt*imgHeightInt);
	                return vo;
	            }
        } catch (Exception e) {
            L.exception("ImgUtil", "计算img size 出错 ");
            L.exception("ImgUtil", e.getMessage());
        	return new ImageVO();
        }
		
		return new ImageVO();
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
