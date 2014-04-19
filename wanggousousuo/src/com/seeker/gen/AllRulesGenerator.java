package com.seeker.gen;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.entity.ShopEntity;
import com.env.StaticInfo;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.U;

/* 循环为每个网站生成rule */
public class AllRulesGenerator {
	
	public void generateRules(String basePath){
		String[] keywords = {"新款","衬衫","乔布斯","诺基亚"
				,"安全座椅","华硕","寶寶"};//,"运动","鞋","食品"
		
		Map<String, ShopEntity> shops = StaticInfo.getShops();
		for(Entry<String, ShopEntity> shop : shops.entrySet()){
//			if(!shop.getValue().getShopName().equals(ShopNames.m18.toString())){	//只跑某个shop
//				continue ;
//			}
			
			//删除待分析shop的以前的rule文件
			File rules = new File(basePath + C.rulesDir);
			if(rules.listFiles()!=null){
				File[] ruleFiles = rules.listFiles();
				for(File ruleFile : ruleFiles){
					if(ruleFile.getName().endsWith(".rule") 
							&& ruleFile.getName().contains(shop.getValue().getShopNameEn())){//如果找到商城的rule文件
						ruleFile.delete();
					}
				}
			}
			
			
			for(String keyword : keywords){
				new CommonRuleGenerator().generateRule(shop.getValue().getShopNameEn(), keyword,keyword, basePath);
			}
		}
	}
	
	public static void main(String[] args) {
		L.level = LogLevel.log;
		long start = System.currentTimeMillis();
		String basePath = new U().getRulePath()+"src/"; 
		new AllRulesGenerator().generateRules(basePath);
		L.trace("", "finished time is " + (System.currentTimeMillis()-start) );
		System.out.println("end..");
	}
	
	
}
