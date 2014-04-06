package com.seeker.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.connect.Connecter;
import com.digger.vo.Product;
import com.env.StaticInfo;
import com.shop.ShopInfo;
import com.utils.C;
import com.utils.L;
import com.utils.ShopNames;

/* 循环为每个网站生成rule */
public class AllRulesGenerator {
	
	public void generateRules(String basePath){
		String[] keywords = {"新款","衬衫","乔布斯","诺基亚"
				,"安全座椅","华硕","寶寶"};//,"运动","鞋","食品"
		
		Map<String, ShopInfo> shops = StaticInfo.getShops();
		for(Entry<String, ShopInfo> shop : shops.entrySet()){
//			if(!shop.getValue().getShopName().equals(ShopNames.m18.toString())){	//只跑某个shop
//				continue ;
//			}
			
			//删除待分析shop的以前的rule文件
			File rules = new File(basePath + C.rulesDir);
			if(rules.listFiles()!=null){
				File[] ruleFiles = rules.listFiles();
				for(File ruleFile : ruleFiles){
					if(ruleFile.getName().endsWith(".rule") 
							&& ruleFile.getName().contains(shop.getValue().getShopName())){//如果找到商城的rule文件
						ruleFile.delete();
					}
				}
			}
			
			
			for(String keyword : keywords){
				new CommonRuleGenerator().generateRule(shop.getValue().getShopName(), keyword,keyword, basePath);
			}
		}
	}
	
	public static void main(String[] args) {
		new AllRulesGenerator().generateRules("C:/workspace/wanggousousuo/WebContent/");
	}
	
	
}
