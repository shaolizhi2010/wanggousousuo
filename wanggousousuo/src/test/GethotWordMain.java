package test;

import java.io.FileReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.JDomSerializer;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Document;
import org.jdom2.Element;
 

import com.google.gson.Gson;
import com.utils.X;

public class GethotWordMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(new URL("http://www.taobao.com"),"GBK");//encoding used for parse returned content
			CleanerProperties props = hc.getProperties();
			PrettyXmlSerializer serializer = new PrettyXmlSerializer(props);
			String xmlString = serializer.getAsString(node,"GBK");
			Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(xmlString));
			
			List list = X.selectNodes(doc, "//a");
			
			//System.out.println(list.size());
	//		
			StringBuffer sb = new StringBuffer();
			for (Object o : list) {
				
				Element xml = (Element) o;
				String value = xml.getValue();
				value = value.replaceAll("\n", "");
				value = value.replaceAll("\r", "");
				value = new String(value.getBytes(),"UTF-8");
				if(StringUtils.isNotBlank(value)  && value.length()<4){
				//	//System.out.println( value );
					sb.append(value).append("---");
				}
			}
			
			//System.out.println(sb.toString());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//http://pcg8870100.blog.163.com/blog/static/374424582011310102758175/

	}

}
