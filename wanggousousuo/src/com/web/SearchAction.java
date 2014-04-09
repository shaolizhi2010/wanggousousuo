package com.web;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.builder.CommonProductBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.seeker.gen.CommonRuleGenerator;
import com.utils.App;
import com.utils.C;
import com.utils.L;
import com.utils.U;
import com.web.base.BaseAction;

public class SearchAction extends BaseAction {
	private static final long serialVersionUID = -7919288320923232999L;

	private String shopname; // eg. jingdong
	private String keyword; //
	private String pageNum;
	private String callback;
	
	private String result;
	
	
	
	public String execute() {

		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/");
		String freshJson = "";

		ActionContext ctx = ActionContext.getContext();
		
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);

		CommonProductBuilder commonProductBuilder = (CommonProductBuilder)beanCtx.getBean(CommonProductBuilder.class);
		
		if (keyword != null) {

			try {
				keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				L.exception(this, e.getMessage());
			}

			int pageNumInt = U.parseInt(pageNum);

			L.debug(this, "begin search from --- " + shopname
					+ " --- keyword ---" + keyword);

			freshJson = commonProductBuilder.build(shopname, keyword,
					pageNumInt, realpath, false);

			if (freshJson.length() < 20) {// 没找到商品，可能是因为缺少rule,生成keyword 和 shop
											// 的rule
				L.log(this, "Try gen rule for shope - " + shopname
						+ " - keyword - " + keyword);
				new CommonRuleGenerator().generateRule(shopname, keyword,
						keyword, realpath);
				// 重新抓取页面 提取数据
				freshJson = commonProductBuilder.build(shopname, keyword,
						pageNumInt, realpath, false);
			}

			if (StringUtils.isEmpty(freshJson)) { //
				freshJson = C.e_nodata;
			}
		}
		
		

		// for jsonp
		freshJson = callback + "(" + freshJson + ")"; // for jsonp
		try{
			response.setContentType("text/json");
			response.getOutputStream().write(freshJson.getBytes("UTF-8")); 
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch ( Exception e) {
			L.exception(this, e.getMessage());
			// ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
			//catch this and do nothing, this is because the client do not receive from server any more
		}
		return null;
	}
	
	public String testMethod() {
		 
		System.out.println("in test method");
		return "success";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

 

}