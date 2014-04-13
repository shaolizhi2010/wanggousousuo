package com.scaffold.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.scaffold.base.AbstractGenerator;
import com.scaffold.common.ColumnVO;
import com.utils.U;

public class ActionGenerator extends AbstractGenerator{

	public void gen(String moduleName) {

		try { 

			// gen action begin
			// file name of action like ABCAction
			String fileName = StringUtils.capitalize(moduleName) + "Action";
			//related entity name
			String entityName = StringUtils.capitalize(moduleName) + "Entity";
			//dao name
			String daoName = StringUtils.capitalize(moduleName) + "Dao";
			
			//service name
			String serviceName = StringUtils.capitalize(moduleName) + "Service";
			
			//new improt
			//String newImport = "";
			// action路径
			String packagePath = "com/web/";
			// 数据库表中的所有列
			List<ColumnVO> columnList = new ArrayList<ColumnVO>();
			metaData(entityName, columnList);
			U.printList(columnList);

			// *Action.java的内容
			
			// 取action 的 template
			String generatedCode = getFileContent(srcPath+"com/scaffold/action/action.template");
			
			// 在 *Action.java 文件中，替换action名
			generatedCode = StringUtils.replace(generatedCode, "$actionName$", StringUtils.capitalize(moduleName));
			
			//替换 dao名
			generatedCode = StringUtils.replace(generatedCode, "$daoName$", daoName);
			generatedCode = StringUtils.replace(generatedCode, "$serviceName$", serviceName);
			
			//newImport += "import com.dao."+daoName+";";
			//newImport += "import com.service."+serviceName+";";
			
			String generatedPropertyCode = "";
			String entityInitCode = "";
			
			for (ColumnVO column : columnList) {
				String templateForProperty = getFileContent( srcPath+"com/scaffold/action/action.property.template");
				if(column.getName().equalsIgnoreCase("id")){
					templateForProperty = StringUtils.replace(templateForProperty, "$propertyName$", column.getName());
					templateForProperty = StringUtils.replace(templateForProperty, "$propertyNameInMethod$", StringUtils.capitalize(column.getName()));
					templateForProperty = StringUtils.replace(templateForProperty, "$propertyType$", "String");//
					
				}
				else{
					templateForProperty = StringUtils.replace(templateForProperty, "$propertyName$", column.getName());
					templateForProperty = StringUtils.replace(templateForProperty, "$propertyNameInMethod$", StringUtils.capitalize(column.getName()));
					templateForProperty = StringUtils.replace(templateForProperty, "$propertyType$", "String");//暂时都用string 进行map
					
				}

				generatedPropertyCode += templateForProperty;
				
				entityInitCode += "entity.set"+StringUtils.capitalize(column.getName())+"("+column.getName()+");"+"\r\n";
				
			}

			generatedCode = StringUtils.replace(generatedCode, "$propertyAndMethod$",
					 generatedPropertyCode);
			
			//set entity
			
			generatedCode = StringUtils.replace(generatedCode, "$entityName$",
					entityName);
			//newImport += "import com.entity."+entityName+";";
			
			generatedCode = StringUtils.replace(generatedCode, "$entityInit$",
					entityInitCode);	
			
			
			
//			//add import
//			generatedCode = StringUtils.replace(generatedCode, "$import$",
//					newImport);		
			
			String targetPath = srcPath + packagePath + fileName
					+ ".java";
			
			if( checkBeforeSave(targetPath, generatedCode) ){
				saveFile(generatedCode, targetPath);
			}
			
			
			//auto change struts.xml
			String strutsConfigFile = getFileContent( srcPath+"struts.xml");
			
			String strutsConfigTemplate = getFileContent( srcPath+"com/scaffold/action/action.configxml.template");
			strutsConfigTemplate = StringUtils.replace(strutsConfigTemplate, "$path$",
					moduleName);	
			strutsConfigTemplate = StringUtils.replace(strutsConfigTemplate, "$actionName$",
					StringUtils.capitalize(moduleName));
			strutsConfigTemplate = StringUtils.replace(strutsConfigTemplate, "$moduleName$",
					moduleName);
			//防止重复
			if(!strutsConfigFile
					.replaceAll(" ", "")
					.replaceAll("\n", "")
					.replaceAll("\r", "").contains(strutsConfigTemplate
							.replaceAll(" ", "")
							.replaceAll("\n", "")
							.replaceAll("\r", ""))){
				strutsConfigFile = StringUtils.replace(strutsConfigFile, "</package>",
						strutsConfigTemplate +"</package>");
				
				saveFile(strutsConfigFile,  srcPath+"struts.xml");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {

		try {
			String moduleName = "resume";
			
			new ActionGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
