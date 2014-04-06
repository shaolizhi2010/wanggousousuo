package com.seeker.promotion.util;

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
import com.utils.X;

public class ImageUtil {

	public String getSrc(Element imgElement, int minSize, String domainName) {
		
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
				if(!attrValue.startsWith("http") && StringUtils.isNotBlank(domainName)){
					attrValue = domainName +"/"+ attrValue;
					attrValue = attrValue.replace("///", "/");
					attrValue = attrValue.replace("//", "/");
					attrValue = "http://"+attrValue;
				}
				// attr.startsWith("http")
				try {
					URL imgurl = new URL(attrValue);
					dis = new DataInputStream(imgurl.openStream());
					buff = ImageIO.read(dis);
					if (buff == null) {
						continue;// io 异常 跳过
					}

				} catch (Exception e) {
					continue;// url不合法 跳过
				}

				if (buff.getWidth() * buff.getHeight() > minSize) {
					return attrValue;
				}
				if (dis != null) {// 关闭流
					try {
						dis.close();
					} catch (IOException e) {
						dis = null;
					}
				}
			}
		}// end for attr

		return "";

	}
}
