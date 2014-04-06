package com.bean.web;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import sun.net.www.http.HttpClient;

import com.bean.logic.AccountService;
import com.bean.logic.DrawMoneyService;
import com.bean.logic.FanliOrderService;
import com.bean.logic.LinkService;
import com.db.DB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.U;

public class MoneyBean extends BaseBean {

	public void execute(HttpServletRequest request, HttpServletResponse response, ParameterMap parameterMap)
			throws Exception {


		String actionType = parameterMap.getParameter("actiontype"); //action type
		String returnMsg = "";
		String errorSysCode = "";
		Gson gson = new GsonBuilder().setDateFormat("MM-dd").create();
		boolean result = false;
		
		// check begin
		if(request.getSession()==null 
				|| request.getSession().getAttribute(C.userId)==null
				|| StringUtils.isBlank( (String)request.getSession().getAttribute(C.userId))){
			response(request, response, "您还未登录，请先登录");
			return;
		}
		String userid = (String)request.getSession().getAttribute(C.userId);
		
		if( StringUtils.isBlank(actionType)){
			response(request, response, "系统错误，操作为空");
			return;
		}
		//check end
		
		if(actionType.equals("updateAccount")){
			AccountService accountService = new AccountService();
			String bankid = parameterMap.getParameter("account-setting-bank-id");
			if(StringUtils.isBlank(bankid)){
				bankid = "";
			}
			String peopleName = parameterMap.getParameter("account-setting-people-name");
			if(StringUtils.isBlank(peopleName)){
				peopleName = "";
			}
			if(accountService.get(userid).size()==0){	//new account
				result = accountService.add(userid, bankid,peopleName);
			}
			else{	//update acount
				result = accountService.update(userid, bankid, peopleName);
			}
			if(result){
				returnMsg = "提交成功！";
			}
			else{
				returnMsg = "提交失败！请稍后再试。 错误码:" + errorSysCode ;
			}
		}
		if(actionType.equals("getFanliOrderJson")){
			List<Map> orderList = new FanliOrderService().get(userid);
			response(request, response, gson.toJson(orderList));
			return;
		}

		if(actionType.equals("getDrawMoneyJson")){
			List<Map> drawmoneylist = new DrawMoneyService().get(userid);
			
			response(request, response, gson.toJson(drawmoneylist));
			return;
		}
		
		if(actionType.equals("getAccount")){
			List<Map> accountData = new AccountService().get(userid);
			response(request, response, gson.toJson(accountData));
			return;
		}
		if(actionType.equals("getAccount")){	//get acount
			List<Map> accountData = new AccountService().get(userid);
			response(request, response, gson.toJson(accountData));
			return;
		}
	
		if(actionType.equals("addOrder")){
			String shopname = parameterMap.getParameter("s-f-shopname");
			if(StringUtils.isBlank(shopname)){
				response(request, response, "需要填写 网站名称");
				return;
			}
			if(shopname.length()>50){
				response(request, response, "网站名称 长度需50字以内 ");
				return;
			}
			
			String orderid = parameterMap.getParameter("s-f-orderid");
			if(StringUtils.isBlank(orderid)){
				response(request, response, "需要填写 订单号");
				return;
			}
			if(orderid.length()>50){
				response(request, response, "订单号 长度需50字以内 ");
				return;
			}
			
			String comment = parameterMap.getParameter("s-f-comment");
//			if(comment!=null){
//				comment = new String(comment.getBytes("iso-8859-1"),"utf-8");
//			}
			if(comment.length()>200){
				response(request, response, "备注 长度需200以内 ");
				return;
			}
			
			L.log(this, "add order --- " + shopname + " --- " + orderid   + " --- "  + comment );

			FanliOrderService service = new FanliOrderService();
			
			
			try {
				result = service.add(userid,orderid,shopname,comment);
			} catch (Exception e) {
				L.exception(this, e.getMessage());
				errorSysCode = e.getMessage();
			}
			
			
			if(result){
				returnMsg = "提交成功！";
			}
			else{
				returnMsg = "提交失败！请稍后再试。 错误码:" + errorSysCode ;
			}
			
		}
		
		//添加提款记录
		if(actionType.equals("addDrawMoney")){
			String bankid = parameterMap.getParameter("account-bank-id");
			if(StringUtils.isBlank(bankid)){
				response(request, response, "需要填写 收款帐号");
				return;
			}
			if(bankid.length()>200){
				response(request, response, "收款帐号 长度需200字以内 ");
				return;
			}
			
			String peopleName = parameterMap.getParameter("account-people-name");
			if(StringUtils.isBlank(peopleName)){
				response(request, response, "需要填写 收款人姓名");
				return;
			}
			if(peopleName.length()>100){
				response(request, response, "收款人姓名 长度需100字以内 ");
				return;
			}
			
			String comment = parameterMap.getParameter("account-comment");
			
			if(comment ==null){
				comment = "";
			}
			
			if(comment!=null && comment.length()>200){
				response(request, response, "备注 长度需200以内 ");
				return;
			}
			
			
			String money = parameterMap.getParameter("account-money-input");
			if(StringUtils.isBlank(money)){
				response(request, response, "需要填写 金额");
				return;
			}
			
			money = money.trim();
			
			if(!U.isMoney(money)){
				response(request, response, "请填写有效金额，只能由数字和小数点组成");
				return;
			}
			
			BigDecimal moneyDecimal = new BigDecimal(money);
			if(moneyDecimal.compareTo( new BigDecimal(10) ) < 0){
				response(request, response, "金额过小，每次提现最少十元");
				return;
			}
			if(moneyDecimal.compareTo( new BigDecimal(10000) ) > 0){
				response(request, response, "金额过大，每次提现最多一万元");
				return;
			}
			
			//账户余额
			String accountMoney = "0";
			List<Map> records = new AccountService().get(userid);
			
			if(records!=null && records.size()>0 
					&& records.get(0)!=null 
					&& records.get(0).get("money")!=null){
				accountMoney = records.get(0).get("money").toString();
			}
			if(moneyDecimal.compareTo( new BigDecimal(accountMoney) ) > 0){
				response(request, response, "余额不足");
				return;
			}
			
			DrawMoneyService service = new DrawMoneyService();
			//重复提款
			if(service.getByStatus(userid, C.status_request).size()>0){
				response(request, response, "您有未处理完的提款，处理完成后才能再次提款");
				return;
			}
			
			L.log(this, "add drawmoney --- " + bankid + " --- " + accountMoney + " --- " + comment );

			
			
			try {
				result = service.add(userid,bankid, peopleName, moneyDecimal,comment);
			} catch (Exception e) {
				L.exception(this, e.getMessage());
				errorSysCode = e.getMessage();
			}
			
			if(result){
				returnMsg = "提交成功！";
			}
			else{
				returnMsg = "提交失败！请稍后再试。 错误码:" + errorSysCode ;
			}
		}
		
		response(request, response, returnMsg);
	}
	
}
