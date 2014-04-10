package com.scaffold.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.scaffold.base.AbstractGenerator;
import com.scaffold.common.ColumnVO;
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
			
			// dao 路径
			String packagePath = "com/dao/";
			
			// 数据库表中的所有列
			List<ColumnVO> columnList = new ArrayList<ColumnVO>();
			metaData(entityName, columnList);
			U.printList(columnList);

			// *Dao.java的内容
			StringBuffer code = new StringBuffer();
			// 取dao 的 template
			String daoTemplate = getFileContent(srcPath+"com/scaffold/dao/dao.template");
			
			//替换dao 名
			daoTemplate = StringUtils.replace(daoTemplate, "$daoName$", daoName);
			
			// 在 *Dao.java 文件中，替换 entity名
			daoTemplate = StringUtils.replace(daoTemplate, "$entityName$", entityName);
			daoTemplate = StringUtils.replace(daoTemplate, "$moduleName$", moduleName);
			
			String generatedCodeForSetDbo = "";
			String generatedCodeForSetentity = "";
			
			for (ColumnVO column : columnList) {
				if(column.getName().equalsIgnoreCase("id")){
					
					generatedCodeForSetentity += "e.setId(dbo1.get(\"_id\").toString());";
					generatedCodeForSetentity += System.getProperty("line.separator");
					//e.set$propertyNameInMethod$(  U.toString( dbo1.get("$propertyName$") ) );
					
					generatedCodeForSetDbo += "if(U.toString(entity.getId()).length()>0){";
					generatedCodeForSetDbo += "dbo.put(\"_id\", new ObjectId(entity.getId()));";
					generatedCodeForSetDbo += "}";
					generatedCodeForSetDbo += System.getProperty("line.separator");
				}
				else{
					String templateForcSetdbo = getFileContent(srcPath+"com/scaffold/dao/dao.setdbo.template");
					templateForcSetdbo = StringUtils.replace(templateForcSetdbo, "$propertyName$", column.getName());
					templateForcSetdbo = StringUtils.replace(templateForcSetdbo, "$propertyNameInMethod$", StringUtils.capitalize(column.getName()));
					generatedCodeForSetDbo += templateForcSetdbo;
			
					String templateForcSetentity = getFileContent(srcPath+"com/scaffold/dao/dao.setentity.template");
					templateForcSetentity = StringUtils.replace(templateForcSetentity, "$propertyName$", column.getName());
					templateForcSetentity = StringUtils.replace(templateForcSetentity, "$propertyNameInMethod$", StringUtils.capitalize(column.getName()));
					generatedCodeForSetentity += templateForcSetentity;	
				}

			
			}
			daoTemplate = StringUtils.replace(daoTemplate, "$setdbo$",
					generatedCodeForSetDbo);
			
			daoTemplate = StringUtils.replace(daoTemplate, "$setentity$",
					generatedCodeForSetentity);
			
			
			
			code.append(daoTemplate);
			saveFile(code.toString(), srcPath + packagePath + fileName
					+ ".java");

		} catch (Exception e) { 
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			String moduleName = "resume";
			//System.out.println( new DaoGenerator().getBasePath() ); 
			new DaoGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
