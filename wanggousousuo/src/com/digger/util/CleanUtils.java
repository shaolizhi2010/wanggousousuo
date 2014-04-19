package com.digger.util;

import java.io.IOException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import com.utils.U;

public class CleanUtils {
	
	public static String clean(String content, String charset){
		HtmlCleaner hc = new HtmlCleaner();
		TagNode node = hc.clean(content);
		String xmlString = "";
		
		CleanerProperties props = hc.getProperties();
		props.setNamespacesAware(false);
		PrettyXmlSerializer serializer = new PrettyXmlSerializer(props);
		try {
			xmlString = serializer.getAsString(node, charset);
		} catch (Exception e) {
		}
		 //TODO
		xmlString = U.clean(xmlString);
		
		//System.out.println(xmlString);
		
		return xmlString;
	}
	
	public static String clean(String content){
		return clean(content, "UTF-8");
	}
	
	
}
