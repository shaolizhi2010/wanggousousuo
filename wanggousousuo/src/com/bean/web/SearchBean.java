package com.bean.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.builder.CommonProductBuilder;
import com.seeker.gen.CommonRuleGenerator;
import com.utils.C;
import com.utils.L;
import com.utils.ShopNames;
import com.utils.U;

public class SearchBean extends BaseBean {

	public void execute(HttpServletRequest request, HttpServletResponse response, ParameterMap parameterMap)
			throws Exception {
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		String freshJson = "";
		
		String shopname = request.getParameter("shopname"); // eg. jingdong  
		String keyword = request.getParameter("keyword"); //
		
		String method = request.getParameter("method"); //
		
		if(method != null && method.equals("addHistory")){
			String data = request.getParameter("data");
			String userId = request.getParameter("userId");
			
			//data = new String(data.getBytes("iso-8859-1"),"utf-8");
			String viewProductHistoryPath = realpath + C.viewProductHistoryPath;
//			File viewProductHistoryFile = new File(viewProductHistoryPath + userId);
//			if(viewProductHistoryFile.exists()){
				IOUtils.write(data, new FileOutputStream(viewProductHistoryPath + userId + ".json"),"UTF-8");
//			}
//			else{
//				viewProductHistoryFile.createNewFile();
//			}
			
			
//			L.always(this, data);
//			L.always(this, userId);
			
		}
		
		if(method != null && method.equals("getHistory")){
			String viewProductHistoryPath = realpath + C.viewProductHistoryPath;
			String userId = request.getParameter("userId");
			if(!new File(viewProductHistoryPath+userId+".json").exists()){
				new File(viewProductHistoryPath+userId+".json").createNewFile();
				freshJson = "";
			}
			else{
				freshJson = IOUtils.toString(new FileInputStream(viewProductHistoryPath+userId+".json"),"UTF-8");
			}
			
			
		}
		
		
		
		if(keyword!=null){
			keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
			String pageNumStr = request.getParameter("pagenum"); //
			
			int pageNumInt = U.parseInt(pageNumStr);
			

			L.debug(this, "begin search from --- " + shopname +" --- keyword ---" + keyword );

			freshJson = new CommonProductBuilder().build(shopname, keyword,pageNumInt,
					realpath,false);
			
			if(freshJson.length()<20){//没找到商品，可能是因为缺少rule,生成keyword 和 shop 的rule
				L.log(this, "Try gen rule for shope - " + shopname + " - keyword - " + keyword);
				new CommonRuleGenerator().generateRule(shopname, keyword,keyword, realpath);
				//重新抓取页面 提取数据
				freshJson = new CommonProductBuilder().build(shopname, keyword,pageNumInt,
						realpath,false);
			}
			
			if (StringUtils.isEmpty(freshJson)) { //
				freshJson = C.e_nodata;
			}
		}
		
		String callback = request.getParameter("callback");  
		//System.out.println("callback --- "+callback);
		
		//for jsonp
		freshJson = callback+ "(" + freshJson + ")";	//for jsonp
		
		response(request, response, freshJson);
		
	}
}
