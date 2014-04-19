package com.web;

import com.web.base.BaseAction;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.utils.L;

import com.service.ShopService;
import com.entity.ShopEntity;

public class ShopAction extends BaseAction {

		private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
 	private String shopNameEn;
	
	public String getShopNameEn() {
		return shopNameEn;
	}
	
	public void setShopNameEn(String shopNameEn) {
		this.shopNameEn = shopNameEn;
	}
 	private String shopNameCn;
	
	public String getShopNameCn() {
		return shopNameCn;
	}
	
	public void setShopNameCn(String shopNameCn) {
		this.shopNameCn = shopNameCn;
	}
 	private String domainName;
	
	public String getDomainName() {
		return domainName;
	}
	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
 	private String shopAvailable;
	
	public String getShopAvailable() {
		return shopAvailable;
	}
	
	public void setShopAvailable(String shopAvailable) {
		this.shopAvailable = shopAvailable;
	}
 	private String searchUrl;
	
	public String getSearchUrl() {
		return searchUrl;
	}
	
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
 	private String searchAvailable;
	
	public String getSearchAvailable() {
		return searchAvailable;
	}
	
	public void setSearchAvailable(String searchAvailable) {
		this.searchAvailable = searchAvailable;
	}
 	private String charsetForUrl;
	
	public String getCharsetForUrl() {
		return charsetForUrl;
	}
	
	public void setCharsetForUrl(String charsetForUrl) {
		this.charsetForUrl = charsetForUrl;
	}
 	private String charsetForContent;
	
	public String getCharsetForContent() {
		return charsetForContent;
	}
	
	public void setCharsetForContent(String charsetForContent) {
		this.charsetForContent = charsetForContent;
	}
 	private String index;
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
 	private String logoImg;
	
	public String getLogoImg() {
		return logoImg;
	}
	
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
 	private String orderNumber;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
 

	public String create() {
		
		ShopEntity entity = new ShopEntity();

		entity.setId(id);
entity.setShopNameEn(shopNameEn);
entity.setShopNameCn(shopNameCn);
entity.setDomainName(domainName);
entity.setShopAvailable(shopAvailable);
entity.setSearchUrl(searchUrl);
entity.setSearchAvailable(searchAvailable);
entity.setCharsetForUrl(charsetForUrl);
entity.setCharsetForContent(charsetForContent);
entity.setIndex(index);
entity.setLogoImg(logoImg);
entity.setOrderNumber(orderNumber);

		 
		ShopService service = new ShopService();
		service.add(entity);
		
		return "list";
	}
	
	public String preUpdate(){
		ShopService service = new ShopService();
		ShopEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
		return "update";
	}
	
	public String update() {
		
		ShopEntity entity = new ShopEntity();

		entity.setId(id);
entity.setShopNameEn(shopNameEn);
entity.setShopNameCn(shopNameCn);
entity.setDomainName(domainName);
entity.setShopAvailable(shopAvailable);
entity.setSearchUrl(searchUrl);
entity.setSearchAvailable(searchAvailable);
entity.setCharsetForUrl(charsetForUrl);
entity.setCharsetForContent(charsetForContent);
entity.setIndex(index);
entity.setLogoImg(logoImg);
entity.setOrderNumber(orderNumber);


		ShopService service = new ShopService();
		service.update(entity);
		
		return "list";
	}	
	
	public String list() {
		ShopEntity entity = new ShopEntity();

		entity.setId(id);
entity.setShopNameEn(shopNameEn);
entity.setShopNameCn(shopNameCn);
entity.setDomainName(domainName);
entity.setShopAvailable(shopAvailable);
entity.setSearchUrl(searchUrl);
entity.setSearchAvailable(searchAvailable);
entity.setCharsetForUrl(charsetForUrl);
entity.setCharsetForContent(charsetForContent);
entity.setIndex(index);
entity.setLogoImg(logoImg);
entity.setOrderNumber(orderNumber);


		ShopService service = new ShopService();
		try {
			List<ShopEntity> list = service.list(entity);
			HttpServletRequest request = ServletActionContext.getRequest ();
			request.setAttribute("entity", entity);
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "listPage";
	}
	
	public String view(){

		ShopService service = new ShopService();
		ShopEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
	
		return "view";
	}
 	
 	public String delete(){
		ShopService service = new ShopService();
		try{
			service.delete(id+"");
		}catch(Exception e){
			L.exception(this, e.getMessage());
		}
		
		return "list";
	}

}