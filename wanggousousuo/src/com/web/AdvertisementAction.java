package com.web;

import com.web.base.BaseAction;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.utils.L;

import com.service.AdvertisementService;
import com.entity.AdvertisementEntity;

public class AdvertisementAction extends BaseAction {

		private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
 	private String url;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
 	private String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
 	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
 	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
 

	public String create() {
		
		AdvertisementEntity entity = new AdvertisementEntity();

		entity.setId(id);
entity.setUrl(url);
entity.setImgUrl(imgUrl);
entity.setName(name);
entity.setDescription(description);

		 
		AdvertisementService service = new AdvertisementService();
		service.add(entity);
		
		return "list";
	}
	
	public String list() {
		AdvertisementEntity entity = new AdvertisementEntity();

		entity.setId(id);
entity.setUrl(url);
entity.setImgUrl(imgUrl);
entity.setName(name);
entity.setDescription(description);


		AdvertisementService service = new AdvertisementService();
		try {
			List<AdvertisementEntity> list = service.list(entity);
			HttpServletRequest request = ServletActionContext.getRequest ();
			request.setAttribute("list", list);
		} catch (Exception e) {
			L.exception(this, e.getMessage());
		}
		return "listPage";
	}
	
	public String view(){

		AdvertisementService service = new AdvertisementService();
		AdvertisementEntity entity = service.get(id);
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("entity", entity);
	
		return "view";
	}
 	
 	public String delete(){
		AdvertisementService service = new AdvertisementService();
		try{
			service.delete(id+"");
		}catch(Exception e){
			L.exception(this, e.getMessage());
		}
		
		return "list";
	}

}