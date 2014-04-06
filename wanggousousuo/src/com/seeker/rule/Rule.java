package com.seeker.rule;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 从商城页面中，提取商品信息所需信息，如
 * 商城的charset，
 * 各类节点的xpath （商品名称，价格，图片路径 等）
 *  
 **/
public class Rule {

	String requestEncodeCharset = "";
	
	/* 提取所有商品信息列表的 xpath，
	 * 如 //ul/li[@class="prod"]， 
	 * 通过这个xpath，执行 selectNotes 方法，即可取包含得所有的li元素的list 。
	 * 
	 * */
	String itemPath = "";	
	
	String titlePath = "";
	String imgPath = "";
	String pricePath = "";
	String commentPath = "";

	public String getRequestEncodeCharset() {
		return requestEncodeCharset;
	}

	public void setRequestEncodeCharset(String requestEncodeCharset) {
		this.requestEncodeCharset = requestEncodeCharset;
	}
	public String getItemPath() {
		return itemPath;
	}

	public void setItemPath(String itemPath) {
		if(itemPath==null){
			itemPath = "null";
		}
		this.itemPath = itemPath;
	}

	public String getTitlePath() {
		return titlePath;
	}

	public void setTitlePath(String titlePath) {
		if(titlePath==null){
			titlePath = "null";
		}
		this.titlePath = titlePath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		if(imgPath==null){
			imgPath = "null";
		}
		this.imgPath = imgPath;
	}

	public String getPricePath() {
		return pricePath;
	}

	public void setPricePath(String pricePath) {
		if(pricePath==null){
			pricePath = "null";
		}
		this.pricePath = pricePath;
	}

	public String getCommentPath() {
		return commentPath;
	}

	public void setCommentPath(String commentPath) {
		if(commentPath==null){
			commentPath = "null";
		}
		this.commentPath = commentPath;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		String newline = System.getProperty("line.separator");
		buffer.append(requestEncodeCharset).append(newline);
		buffer.append(itemPath).append(newline);
		buffer.append(titlePath).append(newline);
		buffer.append(imgPath).append(newline);
		buffer.append(pricePath).append(newline);
		buffer.append(commentPath).append(newline);
		return buffer.toString();
	}
	
	public String toJson(){
		return new Gson().toJson(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		if( !(obj instanceof Rule) ){
			return false;
		}
		
		Rule target = (Rule)obj;
		
		if(	target.getRequestEncodeCharset().trim().equalsIgnoreCase(this.getRequestEncodeCharset().trim())
				&& target.getItemPath().trim().equalsIgnoreCase(this.getItemPath().trim())
				&& target.getTitlePath().trim().equalsIgnoreCase(this.getTitlePath().trim())
				&& target.getImgPath().trim().equalsIgnoreCase(this.getImgPath().trim())
				&& target.getPricePath().trim().equalsIgnoreCase(this.getPricePath().trim())
				&& target.getCommentPath().trim().equalsIgnoreCase(this.getCommentPath().trim())
				){
			return true;
		}

		return false;

	}

	public void fromFile(File ruleFile) throws IOException {
		String ruleJson = FileUtils.readFileToString(ruleFile);
		this.fromJson(ruleJson);
	}
	
	public void fromJson(String ruleJson){
		Rule rule = new Gson().fromJson(ruleJson, new TypeToken<Rule>() {}.getType());
		this.fromObject(rule);
	}
	
	public void fromObject(Rule rule){
		this.requestEncodeCharset = rule.getRequestEncodeCharset();
		this.itemPath = rule.getItemPath();
		this.titlePath = rule.getTitlePath();
		this.imgPath = rule.getImgPath();
		this.pricePath = rule.pricePath;
		this.commentPath = rule.getCommentPath();
	}

}
