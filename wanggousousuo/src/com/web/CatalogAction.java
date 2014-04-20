package com.web;

import com.web.base.BaseAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.utils.L;
import com.service.CatalogService;
import com.entity.CatalogEntity;

public class CatalogAction extends BaseAction {

		private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
 	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
 	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
 	private String orderNumber;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
 	private String useful;
	
	public String getUseful() {
		return useful;
	}
	
	public void setUseful(String useful) {
		this.useful = useful;
	}
 	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
 

	public String create() {
		
		CatalogEntity entity = new CatalogEntity();

		entity.setName(name);
entity.setKeyword(keyword);
entity.setId(id);
entity.setOrderNumber(orderNumber);
entity.setUseful(useful);
entity.setDescription(description);

		 
		CatalogService service = new CatalogService();
		service.add(entity);
		
		return "list";
	}
	
	public String preUpdate(){
		CatalogService service = new CatalogService();
		CatalogEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
		return "update";
	}
	
	public String update() {
		
		CatalogEntity entity = new CatalogEntity();

		entity.setName(name);
entity.setKeyword(keyword);
entity.setId(id);
entity.setOrderNumber(orderNumber);
entity.setUseful(useful);
entity.setDescription(description);


		CatalogService service = new CatalogService();
		service.update(entity);
		
		return "list";
	}	
	
	public String list() {
		CatalogEntity entity = new CatalogEntity();

		entity.setName(name);
entity.setKeyword(keyword);
entity.setId(id);
entity.setOrderNumber(orderNumber);
entity.setUseful(useful);
entity.setDescription(description);


		CatalogService service = new CatalogService();
		try {
			List<CatalogEntity> list = service.list(entity);
			HttpServletRequest request = ServletActionContext.getRequest ();
			request.setAttribute("entity", entity);
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "listPage";
	}
	
	public String view(){

		CatalogService service = new CatalogService();
		CatalogEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
	
		return "view";
	}
 	
 	public String delete(){
 		HttpServletRequest request = ServletActionContext.getRequest ();
 		String password = request.getParameter("p");
 	 	if(StringUtils.isBlank(password)){
 	 		return "list"; //无密码
 	 	}
 	 	if(!"cake4you".equals(password)){
 	 		return "list"; // 密码错
 	 	}
		CatalogService service = new CatalogService();
		try{
			service.delete(id+"");
		}catch(Exception e){
			L.exception(this, e.getMessage());
		}
		
		return "list";
	}

}