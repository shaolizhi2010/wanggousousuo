package com.codegen.service;

import org.apache.commons.lang3.StringUtils;

import com.codegen.base.AbstractGenerator;
import com.codegen.common.Constant;

public class ServiceGenerator extends AbstractGenerator{

	public void gen(String moduleName) {

		try {


			// gen service begin
			String serviceName = StringUtils.capitalize(moduleName) + "Service";
			
			String fileName = StringUtils.capitalize(moduleName) + "Service";
			//related entity name
			String entityName = StringUtils.capitalize(moduleName) + "Entity";
			//dao name
			String daoName = StringUtils.capitalize(moduleName) + "Dao";
			
			//new improt
			String newImport = "";
			// service路径
			String packagePath = "com/service/";

			// *Service.java的内容
			
			// 取Service 的 template
			String generatedCode = getFileContent(Constant.srcPath+"com/codegen/service/service.template");
			
			// 在 *Service.java 文件中，替换service名
			generatedCode = StringUtils.replace(generatedCode, "$serviceName$", serviceName);
			
			//替换 dao名
			generatedCode = StringUtils.replace(generatedCode, "$daoName$", daoName);
			newImport += "import com.dao."+daoName+";";
			
			//替换 entity 名
			generatedCode = StringUtils.replace(generatedCode, "$entityName$",
					entityName);
	
			generatedCode = StringUtils.replace(generatedCode, "$moduleName$",
					moduleName);
			
			//newImport += "import com.entity."+entityName+";";
			
//			//add import
//			generatedCode = StringUtils.replace(generatedCode, "$import$",
//					newImport);		
			
			
			saveFile(generatedCode, Constant.srcPath + packagePath + fileName
					+ ".java");
			System.out.println("service Generated");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {

		try {
			String moduleName = "drawmoney";
			
			new ServiceGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
