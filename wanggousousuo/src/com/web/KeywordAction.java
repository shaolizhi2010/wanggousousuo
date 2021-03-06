package com.web;

import com.web.base.BaseAction;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.utils.L;

import com.service.KeywordService;
import com.entity.KeywordEntity;

public class KeywordAction extends BaseAction {

		private String keyword;
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
 	private String times;
	
	public String getTimes() {
		return times;
	}
	
	public void setTimes(String times) {
		this.times = times;
	}
 	private String lasttime;
	
	public String getLasttime() {
		return lasttime;
	}
	
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
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
		
		KeywordEntity entity = new KeywordEntity();

		entity.setKeyword(keyword);
entity.setTimes(times);
entity.setLasttime(lasttime);
entity.setId(id);
entity.setOrderNumber(orderNumber);
entity.setUseful(useful);
entity.setDescription(description);

		 
		KeywordService service = new KeywordService();
		service.add(entity);
		
		return "list";
	}
	
	public String preUpdate(){
		KeywordService service = new KeywordService();
		KeywordEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
		return "update";
	}
	
	public String update() {
		
		KeywordEntity entity = new KeywordEntity();

		entity.setKeyword(keyword);
entity.setTimes(times);
entity.setLasttime(lasttime);
entity.setId(id);
entity.setOrderNumber(orderNumber);
entity.setUseful(useful);
entity.setDescription(description);


		KeywordService service = new KeywordService();
		service.update(entity);
		
		return "list";
	}	
	
	public String list() {
		KeywordEntity entity = new KeywordEntity();

		entity.setKeyword(keyword);
entity.setTimes(times);
entity.setLasttime(lasttime);
entity.setId(id);
entity.setOrderNumber(orderNumber);
entity.setUseful(useful);
entity.setDescription(description);


		KeywordService service = new KeywordService();
		try {
			List<KeywordEntity> list = service.list(entity);
			HttpServletRequest request = ServletActionContext.getRequest ();
			request.setAttribute("entity", entity);
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "listPage";
	}
	
	public String view(){

		KeywordService service = new KeywordService();
		KeywordEntity entity = service.get(id);
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
 	
		KeywordService service = new KeywordService();
		try{
			service.delete(id+"");
		}catch(Exception e){
			L.exception(this, e.getMessage());
		}
		
		return "list";
	}

}