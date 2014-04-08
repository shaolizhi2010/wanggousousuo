package com.codegen.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.codegen.base.AbstractGenerator;
import com.codegen.common.Constant;
import com.codegen.vo.ColumnVO;
import com.utils.U;

public class DaoGenerator extends AbstractGenerator{

	public void gen(String moduleName) {
		
		try {
			// gen dao begin
			// file name  like ABCDao
			String fileName = StringUtils.capitalize(moduleName) + "Dao";
			String tableName = moduleName;
			String entityName = StringUtils.capitalize(moduleName)+"Entity";
			String daoName = StringUtils.capitalize(moduleName)+"Dao" ;
			String newImport = "";
			
			// dao 路径
			String packagePath = "com/dao/";
			
			// 数据库表中的所有列
			List<ColumnVO> columnList = new ArrayList<ColumnVO>();
			metaData(moduleName, columnList);
			U.printList(columnList);

			// *Dao.java的内容
			StringBuffer code = new StringBuffer();
			// 取dao 的 template
			String daoTemplate = getFileContent(Constant.srcPath+"com/codegen/dao/dao.template");
			
			//替换dao 名
			daoTemplate = StringUtils.replace(daoTemplate, "$daoName$", daoName);
			
			// 在 *Dao.java 文件中，替换 entity名
			daoTemplate = StringUtils.replace(daoTemplate, "$entityName$", entityName);
			
			//add import
			newImport += "import com.entity."+entityName+";";
			daoTemplate = StringUtils.replace(daoTemplate, "$import$",
					newImport);	
			daoTemplate = StringUtils.replace(daoTemplate, "$tableName$",
					tableName);	
			
			String generatedCodeForCondition = "";
			String generatedCodeForSetCondition = "";
			
			for (ColumnVO column : columnList) {
				if(column.getName().equalsIgnoreCase("id")){
					continue;	//id暂不处理
				}
				String templateForcondition = getFileContent(Constant.srcPath+"com/codegen/dao/dao.select.condition.template");
				templateForcondition = StringUtils.replace(templateForcondition, "$propertyName$", column.getName());
				templateForcondition = StringUtils.replace(templateForcondition, "$propertyNameInMethod$", StringUtils.capitalize(column.getName()));
				generatedCodeForCondition += templateForcondition;
				
				String templateForSetcondition  = getFileContent(Constant.srcPath+"com/codegen/dao/dao.select.setcondition.template");
				templateForSetcondition = StringUtils.replace(templateForSetcondition, "$propertyName$", column.getName());
				templateForSetcondition = StringUtils.replace(templateForSetcondition, "$propertyNameInMethod$", StringUtils.capitalize(column.getName()));
				generatedCodeForSetCondition += templateForSetcondition;
			
			}
			daoTemplate = StringUtils.replace(daoTemplate, "$condition$",
					generatedCodeForCondition);
			
			daoTemplate = StringUtils.replace(daoTemplate, "$setCondition$",
					generatedCodeForSetCondition);
			
			
			
			code.append(daoTemplate);
			saveFile(code.toString(), Constant.srcPath + packagePath + fileName
					+ ".java");
			System.out.println("Dao Generated");
		} catch (Exception e) { 
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			String moduleName = "drawmoney";
			
			new DaoGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
