package com.bean.web;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


import com.bean.logic.Cache;
import com.builder.CommonProductBuilder;
import com.env.StaticInfo;
import com.mutiServer.MutiServerUtil;
import com.shop.ShopInfo;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.ShopNames;
import com.utils.U;

public class StaticSearchBean extends BaseBean {

	public void execute(HttpServletRequest request, HttpServletResponse response, ParameterMap parameterMap)
			throws Exception {

		String keyword = (String)request.getAttribute("keyword"); //
 
		Map<String, ShopInfo> shops = StaticInfo.getShops();
		int returnSize = 0;
		
		for(Entry<String, ShopInfo> shop : shops.entrySet()){
			String shopname = shop.getValue().getShopName();
			//随机取店铺，显示在页面上
			if(new Random().nextInt(10)>2){
				String  resultJson = new MutiServerUtil().getCacheContentFromCacheServer(keyword, shopname); 
				if(StringUtils.isNotBlank(resultJson)){
					returnSize = returnSize + resultJson.length();
					if(returnSize > 2*1024){	/*够2k数据就返回*/
						
					}
					request.setAttribute(shopname, resultJson);
				}
				
				
			}
	
		}
		request.setAttribute("keyword", keyword);
		
		request.getRequestDispatcher("static-search.jsp").forward(request, response);
		
//		new CommonProductBuilder().build(ShopNames.taobao.name(), keyword,1,
//				realpath,false);
//		new CommonProductBuilder().build(ShopNames.amazon.toString(), keyword,1,
//				realpath,false);
//		new CommonProductBuilder().build(ShopNames.tmall.toString(), keyword,1,
//				realpath,false);
//		new CommonProductBuilder().build(ShopNames.dangdang.toString(), keyword,1,
//				realpath,false);
	}
}
