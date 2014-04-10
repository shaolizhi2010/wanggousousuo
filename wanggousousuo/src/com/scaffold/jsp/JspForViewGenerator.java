package com.scaffold.jsp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.scaffold.base.AbstractGenerator;
import com.scaffold.common.ColumnVO;
import com.utils.U;

public class JspForViewGenerator extends AbstractGenerator{

	public  void gen(String moduleName) {

		try {

			//String moduleName = "project";

			// gen jsp begin
			// jsp name
			String fileName = moduleName+".view";
			String serviceName = StringUtils.capitalize(moduleName)+"Service";
			String entityName = StringUtils.capitalize(moduleName)+"Entity";
			
			// jsp路径
			// String packagePath = "D:\work_space\secondjob\WebContent";
			// 数据库表中的所有列
			List<ColumnVO> columnList = new ArrayList<ColumnVO>();
			metaData(moduleName, columnList);
			U.printList(columnList);

			// *.jsp的内容

			// 取jsp 的 template
			String jspTemplateCode = getFileContent(srcPath
					+ "com/scaffold/jsp/jsp.view.template");
			
			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$serviceName$", serviceName);
			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$entityName$", entityName);
			
			
			//jsp 里边 的row
			String rowContent = "";

			

			for (ColumnVO column : columnList) {
				if (column.getName().equalsIgnoreCase("id")) {
					continue; // id 写死 暂不动态处理
				}
	 
				String rowTemplate = getFileContent(srcPath
							+ "com/scaffold/jsp/jsp.view.row.template");
				rowTemplate = StringUtils.replace(rowTemplate,
						"$propertyName$", column.getName());
				
				rowTemplate = StringUtils.replace(rowTemplate,
						"$propertyNameInMethod$", StringUtils.capitalize(column.getName()) );
				
				rowContent += rowTemplate;

			}

			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$row$", rowContent);
			
			saveFile(jspTemplateCode, webPath + fileName + ".jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	public static void main(String[] args) {

		try {
			String moduleName = "resume";
			
			new JspForViewGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
