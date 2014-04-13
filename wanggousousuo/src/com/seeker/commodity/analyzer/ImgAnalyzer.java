package com.seeker.commodity.analyzer;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.seeker.util.XpathMap;
import com.utils.L;
import com.utils.X;


public class ImgAnalyzer {
	
	public List<String> pathList = new ArrayList<String>();
	
	public ImgAnalyzer(){
		
		pathList.add( ".//img" );
		
//		keyList.add("");
//		keyList.add("");
	}
	
	/**
	 * 规则
	 * img url 属性规则
	 * 1. 长度大于10
	 * 2. 以 http 开头
	 * 
	 * 判断是否是合适img 的规则
	 * 1 宽 和 高 都大于100， 产品图片一般不会太小
	 * 
	 * @param t
	 * @param keyword
	 * @param countmap
	 * @return
	 */
	public boolean analyze(Object t, XpathMap countmap){
		
		boolean targetFinded = false;
		String rootPath = X.getPath((Element)t);
		
		
		for(String xpath : pathList){
			
			List<Element> imgList =  X.getSubElementList(t, xpath);
			for(Element imgElement : imgList){
				
				/*准备遍历所有attribute 因为img 属性不一定是src 有些延迟加载会使用自定义属性*/
				List<Attribute> imgAttrList =  imgElement.getAttributes();
				DataInputStream dis = null;
				BufferedImage buff = null;
				String path =  X.getSubPath(X.getPath(imgElement), rootPath) ;				
				for(Attribute a : imgAttrList){//遍历所有attribute
					//TODO 判断是否是图片地址
					//有的img 会含有包含js的属性
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
				        
				       // //System.out.println(buff.getWidth() + " - " + buff.getHeight());
				        if(buff.getWidth()>100 && buff.getHeight()>100){
							//图片合法
				        	String attrName = a.getName();//图片的合法属性
				        	//图片xpath 加上属性后缀，因为最好要找到是这个属性的xpath
				        	path = path+"/@"+attrName;
				        	//TODO 的path可能重复，需要加索引
//				        	//System.out.println("img path "+path);
//				        	//System.out.println("img src "+ attr);
				        	if(path.startsWith("./ul/li")){
				        		////L.trace(this, rootPath);
				        		////L.trace(this, X.getPath(imgElement));
				        	}
				        	countmap.put(path, path,1);
				        	targetFinded = true;
				        	
				        }
				        if(dis!=null){//关闭流
				        	 try {
								dis.close();
							} catch (IOException e) {
								dis = null;
							} 
				        }
					}
				}// end for attr
				if(targetFinded){
					break; //找到一张图片就跳出循环，防止根一级节点会多次扫描子节点中多张图片，造成计算路径不准
				}
				
			}//end for
		}
		return targetFinded;
	}
}
