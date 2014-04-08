package com.codegen.jsp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.codegen.base.AbstractGenerator;
import com.codegen.common.Constant;
import com.codegen.vo.ColumnVO;
import com.utils.U;

public class JspForListGenerator extends AbstractGenerator{

	public  void gen(String moduleName) {

		try {

			// gen jsp begin
			// jsp name
			String fileName = moduleName+".list";
			String entityName = StringUtils.capitalize(moduleName)+"Entity";
			
			// jsp路径
			// String packagePath = "D:\work_space\secondjob\WebContent";
			// 数据库表中的所有列
			List<ColumnVO> columnList = new ArrayList<ColumnVO>();
			metaData(moduleName, columnList);
			U.printList(columnList);

			// *.jsp的内容

			// 取jsp 的 template
			String jspTemplateCode = getFileContent(Constant.srcPath
					+ "com/codegen/jsp/jsp.list.template");
			
			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$entityName$", entityName);
			
			String theadContent = "";
			String rowContent = "";

			for (ColumnVO column : columnList) {
				if (column.getName().equalsIgnoreCase("id")) {
					continue; // id 写死 暂不动态处理
				}
				
				//jsp table里边 的thead
				String theadTemplateCode = getFileContent(Constant.srcPath
						+ "com/codegen/jsp/jsp.list.thead.template");
				//jsp table里边 的row
				String rowTemplateCode = getFileContent(Constant.srcPath
						+ "com/codegen/jsp/jsp.list.row.template");
				
				
				theadTemplateCode = StringUtils.replace(theadTemplateCode,
						"$propertyName$", column.getName());
				theadContent += theadTemplateCode;
				
				rowTemplateCode = StringUtils.replace(rowTemplateCode,
						"$propertyNameInMethod$", StringUtils.capitalize(column.getName()) );	
				rowContent += rowTemplateCode;
			}

			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$thead$", theadContent);
			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$row$", rowContent);
			
			saveFile(jspTemplateCode, Constant.webPath + fileName + ".jsp");
			System.out.println("jsp for list Generated");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	public static void main(String[] args) {

		try {
			String moduleName = "resume";
			
			new JspForListGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
