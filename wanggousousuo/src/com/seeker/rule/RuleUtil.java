package com.seeker.rule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.digger.WebBaseDigger;
import com.digger.vo.Product;
import com.utils.C;
import com.utils.DefaultStringUtils;
import com.utils.L;
import com.utils.X;

/**
 * 
 *
 */
public class RuleUtil {
	//all
	public static File[] getRuleFiles(String basePath){
		File rules = new File(basePath + C.rulesDir);
		if(rules.listFiles()==null){
			L.exception("RuleUtil getRuleFiles ", "path have no rule file, path is --- " + basePath + C.rulesDir);
		}
		File[] ruleFiles = rules.listFiles();
		return ruleFiles;
	}
	
	public static File[] getRuleFiles(String basePath, String shopName){
		File[] ruleFiles = getRuleFiles(basePath);
		File[] ruleFilesForshop = {};
		for(File ruleFile : ruleFiles){//遍历rule file 知道找到结果
			if(ruleFile.getName().contains(shopName)){//如果找到商城的rule文件
				ruleFilesForshop = ArrayUtils.add(ruleFilesForshop,ruleFile);
			}
		}
		return ruleFilesForshop;
	}
	
	public static Boolean checkRuleFileExist(Rule targetRule, String basePath, String shopName){
		if(targetRule == null){
			return false;
		}
		
		File[] files = getRuleFiles(basePath, shopName);
		for(File file : files){
			Rule rule = new Rule();
			try {
				rule.fromFile(file);
				if(targetRule.equals(rule)){
					return true;
				}
			} catch (IOException e) {
				L.exception("RuleUtil checkRuleFileExist ", e.getMessage());
				continue;
			}
		}
		return false;
	}
}
