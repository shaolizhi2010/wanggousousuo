package com.scaffold.jsp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.scaffold.base.AbstractGenerator;
import com.scaffold.common.ColumnVO;
import com.utils.U;

public class JspForCreateGenerator extends AbstractGenerator{

	public  void gen(String moduleName) {

		try {

			// gen jsp begin
			// jsp name
			String fileName = moduleName+".create";
			String actionPath = moduleName; 
			
			// jsp路径
			// String packagePath = "D:\work_space\secondjob\WebContent";
			// 数据库表中的所有列
			List<ColumnVO> columnList = new ArrayList<ColumnVO>();
			metaData(moduleName, columnList);
			U.printList(columnList);

			// *.jsp的内容

			// 取jsp 的 template
			String jspTemplateCode = getFileContent( srcPath
					+ "com/scaffold/jsp/jsp.create.template");
			
			//jsp 里边 的from
			String formTemplateCode = getFileContent( srcPath
					+ "com/scaffold/jsp/jsp.form.template");

			formTemplateCode = StringUtils.replace(formTemplateCode,
					"$actionPath$", actionPath);
			formTemplateCode = StringUtils.replace(formTemplateCode,
					"$title$", moduleName);
			
			String formContent = "";

			for (ColumnVO column : columnList) {
				if (column.getName().equalsIgnoreCase("id")) {
					continue; // id 写死 暂不动态处理
				}
				int columnDisplaySize = column.getColumnDisplaySize();
				
				String formPropertyCode = "";
				if(columnDisplaySize <500 
						&& column.getName().indexOf("description")<0){	//input text
					
					 formPropertyCode = getFileContent( srcPath
							+ "com/scaffold/jsp/jsp.form.property.template");
				}
				else{	//textarea
					 formPropertyCode = getFileContent( srcPath
							+ "com/scaffold/jsp/jsp.form.textarea.template");
				}
				formPropertyCode = StringUtils.replace(formPropertyCode,
						"$propertyName$", column.getName());
				formContent += formPropertyCode;

			}

			formTemplateCode = StringUtils.replace(formTemplateCode,
					"$formContent$", formContent);
			jspTemplateCode = StringUtils.replace(jspTemplateCode,
					"$jspContent$", formTemplateCode);
			
			String targetPath = webPath + fileName + ".jsp";
			
			if( checkBeforeSave(targetPath, jspTemplateCode) ){
				saveFile(jspTemplateCode,  targetPath);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	public static void main(String[] args) {

		try {
			String moduleName = "resume";
			
			new JspForCreateGenerator().gen(moduleName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
