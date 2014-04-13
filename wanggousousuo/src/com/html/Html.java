package com.html;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.connect.Connecter;
import com.file.FileUtil;
import com.html.vo.HtmlVo;
import com.utils.L;
import com.utils.U;
import com.utils.X;

public class Html {
	
	private String cleanedHtml = null;
	private String rawHtml = null;
	private Document doc = null;
	
	//是否成功init过，即是否有clean过的 html
	//建议使用clean 过的 html 进行节点查找 提取等操作
	private boolean isRaw = true;
	
	private String charset = "";
	//html 的 head 部分
	//Map<String, String> heads = new HashMap<String,String>();
	
	public Html(){
		
	}
	
	//如帶參數，則默認直接執行init
	public Html(String rawHtml){
		setRawPageSource(rawHtml);
		init();
	}
	
	public void init(){
		try {
			cleanedHtml = clean();
			doc = new SAXBuilder().build(new StringReader(cleanedHtml));
			this.isRaw = false;//init 过了 israw 就是false
		} catch (Exception e) {
			L.exception("HtmlUtils", e.getMessage());
			return;
		}
	}
	
	//clean 过的 html
	public String getPageSource(){
			return cleanedHtml;
	}
	
	@Deprecated
	public String getRawPageSource(){
		return rawHtml;
	}
	
	public void setRawPageSource(String rawHtml){
		this.rawHtml = rawHtml;
	}
	
	public String head(String headname){
		
		String value = "";
		
		if("charset".equalsIgnoreCase(headname)){
			String key = "html/head/meta[contains(@http-equiv,'content-type')]/@content";
			value = X.getAttrValue(doc, key);
			value = StringUtils.substringAfterLast(value, "charset=");
			if(StringUtils.isNotBlank(value)) value = StringUtils.upperCase(value);
			return value;
		}
		
		Object o = X.selectSingleNode(doc, "html/head/"+headname);
		if(o!=null){	//<title> abc... </title>	
			Element e = (Element)o;
			value = e.getText();
		}
		else{	// <meta name="description" content="abc.."/>
			String key = "html/head/meta[contains(@name,'"+headname+"')]";
			value = X.getAttrValue(doc, key, "content");
		}
		
		if(StringUtils.isBlank(value)) return "";
		value = value.trim();
		return value;
	}
	
	public String title(){
		return head("title");
	}
	
	public String description(){
		return head("description");
	}
	
	public String charser(){
		return charset;
	}
	public void charset(String charset){
		this.charset = charset;
	}
	
	public String clean(){
		HtmlCleaner hc = new HtmlCleaner();
		TagNode node = hc.clean(this.rawHtml);
		
		CleanerProperties props = hc.getProperties();
		props.setNamespacesAware(false);
		PrettyXmlSerializer serializer = new PrettyXmlSerializer(props);
		String cleanedHtml = "";
		try {
			cleanedHtml = serializer.getAsString(node,"UTF-8");
			//cleanedHtml = cleanedHtml.replaceAll("H", "h");
			//cleanedHtml = cleanedHtml.replaceAll("D", "d");
			//cleanedHtml = StringUtils.lowerCase(cleanedHtml,Locale.CHINESE);
		} catch (IOException e) {
			L.exception(this, e.getMessage());
		}
		this.cleanedHtml = U.clean(cleanedHtml);
		return this.cleanedHtml;
	}
	
	public boolean isRaw() {
		return isRaw;
	}

	public void setRaw(boolean isRaw) {
		this.isRaw = isRaw;
	}

	@Override
	public String toString() {
		return this.cleanedHtml;
	}


//	public void info(String html, HtmlVo vo){
//		Document doc = null;
//		try {
//			doc = new org.jdom2.input.SAXBuilder().build(new StringReader(rawHtml));
//		} catch (Exception e) {
//			L.exception("HtmlUtils", e.getMessage());
//			return;
//		}
//		info(doc, vo);
//	}
//	public void info(Document doc, HtmlVo vo){
//		
//		String title = "";
//		Object o = X.selectSingleNode(doc, "html/head/title");
//		Element e = (Element)o;
//		title = X.getValue(e,null);
//		vo.setTitle(title);
//		//TODO other data
//	}
	
	public static void main(String[] args) {
		try {
			Html html = Connecter.getHtmlInfo("http://jr.jd.com/?erpad_source=erpad");//getPageSource("z.cn").get("pageSource");
			FileUtil.saveFile(html.getPageSource(), "d:/test.txt");
			
			//System.out.println(html);
			System.out.println(html.head("charset"));
			System.out.println(html.head("title"));
			System.out.println(html.head("description"));
			System.out.println(html.head("keywords"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
