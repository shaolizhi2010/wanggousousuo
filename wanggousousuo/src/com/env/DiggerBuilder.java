package com.env;

 

import com.utils.L;

public class DiggerBuilder {
//
//	public static Map<String,Map<String,String>> buildDiggers() {
//
//		Map<String,Map<String,String>> manyDiggers = new HashMap<String,Map<String,String>>();
//		
//		SAXBuilder builder = new SAXBuilder();
//		Document document;
//		try {
//			document = builder
//					.build( new FileInputStream("C:\\workspace\\bijia\\digger.xml"));
//			Element root = document.getRootElement();
//
//			List<Element> diggers = root.getChildren("digger");
//			
//			
//			for (Element digger : diggers) {	//
//				
//				String diggerName = digger.getAttributeValue("name");
//			
//				List<Element> diggerInfos  = digger.getChildren();
//				java.util.Map<String, String> oneDigger = new HashMap<String, String>();
//				
//				for(Element diggerInfo : diggerInfos){	//��װһ��digger
//					String attribute = diggerInfo.getAttributeValue("attribute");
//					if(attribute!=null && attribute.length()>0){	
//						oneDigger.put(diggerInfo.getName(), diggerInfo.getValue().trim()+"###"+attribute); //��### ���зָ�
//					}
//					else{
//						oneDigger.put(diggerInfo.getName(), diggerInfo.getValue().trim()); //û��attribute��ֱ��ȡֵ
//					}
//					
//					
//				}
//				manyDiggers.put(diggerName, oneDigger);	//װ��ȥ
//			}
//			
//		} catch (FileNotFoundException e) {
//			L.debug("DiggerBuilder",e.getMessage());
//			e.printStackTrace();
//		} catch (JDOMException e) {
//			L.debug("DiggerBuilder", e.getMessage());
//			e.printStackTrace();
//		} catch (IOException e) {
//			L.debug("DiggerBuilder",e.getMessage());
//			e.printStackTrace();
//		}
//
//		return manyDiggers;
//
//	}
}
