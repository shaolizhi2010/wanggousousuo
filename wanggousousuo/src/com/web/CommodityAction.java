package com.web;

import com.web.base.BaseAction;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.utils.L;
import com.service.CommodityService;
import com.entity.CommodityEntity;

public class CommodityAction extends BaseAction {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	private String commentCount;

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	private String commentUrl;

	public String getCommentUrl() {
		return commentUrl;
	}

	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}

	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String create() {

		CommodityEntity entity = new CommodityEntity();

		entity.setId(id);
		entity.setName(name);
		entity.setUrl(url);
		entity.setPrice(price);
		entity.setImgUrl(imgUrl);
		entity.setCommentCount(commentCount);
		entity.setCommentUrl(commentUrl);
		entity.setSource(source);
		entity.setKeyword(keyword);

		CommodityService service = new CommodityService();
		service.add(entity);

		return "list";
	}

	public String list() {
		CommodityEntity entity = new CommodityEntity();

		entity.setId(id);
		entity.setName(name);
		entity.setUrl(url);
		entity.setPrice(price);
		entity.setImgUrl(imgUrl);
		entity.setCommentCount(commentCount);
		entity.setCommentUrl(commentUrl);
		entity.setSource(source);
		entity.setKeyword(keyword);

		CommodityService service = new CommodityService();
		try {
			List<CommodityEntity> list = service.list(entity);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "listPage";
	}

	public String search() {
		CommodityEntity entity = new CommodityEntity();

		entity.setId(id);
		entity.setName(name);
		entity.setUrl(url);
		entity.setPrice(price);
		entity.setImgUrl(imgUrl);
		entity.setCommentCount(commentCount);
		entity.setCommentUrl(commentUrl);
		

		try {
			if (keyword != null) {
				keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
			}

		} catch (UnsupportedEncodingException e) {
			L.exception(this, e.getMessage());
		}
		
		try {
			if (source != null) {
				source = new String(source.getBytes("ISO-8859-1"), "UTF-8");
			}

		} catch (UnsupportedEncodingException e) {
			L.exception(this, e.getMessage());
		}

		entity.setKeyword(keyword);
		entity.setSource(source);
		
		CommodityService service = new CommodityService();
		try {
			List<CommodityEntity> list = service.search(entity);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("entity", entity);
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "searchPage";
	}

	public String view() {

		CommodityService service = new CommodityService();
		CommodityEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("entity", entity);

		return "view";
	}

	public String delete() {
		
 		HttpServletRequest request = ServletActionContext.getRequest ();
 		String password = request.getParameter("p");
 	 	if(StringUtils.isBlank(password)){
 	 		return "list"; //无密码
 	 	}
 	 	if(!"cake4you".equals(password)){
 	 		return "list"; // 密码错
 	 	}
		
		CommodityService service = new CommodityService();
		try {
			service.delete(id + "");
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}

		return "list";
	}

}