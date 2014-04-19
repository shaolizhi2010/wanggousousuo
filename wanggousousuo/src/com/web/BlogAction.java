package com.web;

import com.web.base.BaseAction;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.utils.L;

import com.service.BlogService;
import com.entity.BlogEntity;

public class BlogAction extends BaseAction {

		private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
 	private String title;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
 	private String content;
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
 

	public String create() {
		
		BlogEntity entity = new BlogEntity();

		entity.setId(id);
entity.setTitle(title);
entity.setContent(content);

		 
		BlogService service = new BlogService();
		service.add(entity);
		
		return "list";
	}
	
	public String list() {
		BlogEntity entity = new BlogEntity();

		entity.setId(id);
entity.setTitle(title);
entity.setContent(content);


		BlogService service = new BlogService();
		try {
			List<BlogEntity> list = service.list(entity);
			HttpServletRequest request = ServletActionContext.getRequest ();
			request.setAttribute("entity", entity);
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "listPage";
	}
	
	public String view(){

		BlogService service = new BlogService();
		BlogEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
	
		return "view";
	}
 	
 	public String delete(){
		BlogService service = new BlogService();
		try{
			service.delete(id+"");
		}catch(Exception e){
			L.exception(this, e.getMessage());
		}
		
		return "list";
	}

}