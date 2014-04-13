package com.scaffold.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.file.FileUtil;
import com.scaffold.common.ColumnVO;
import com.utils.L;

public class AbstractGenerator {
	
	public String srcPath = getBasePath()+"src/";
	public String webPath = getBasePath()+"WebContent/";
	
	public String getBasePath(){
		String path = this.getClass().getClassLoader().getResource("").getPath();
		path = StringUtils.substringBeforeLast(path, "target");
		return path;
	}
	
	/*
	 * 可以覆盖 则返回true
	 */
	public boolean checkBeforeSave(String targetPath, String generatedCode){
		
		if(!new File(targetPath).exists()){
			return true;
		}
		String targetFileContent = FileUtil.getFileContent(targetPath);
		
		//如果目标文件已存在，并且比生成的文件大，说明之后添加过内容，那么不要覆盖
		//防止丢失代码
		if(targetFileContent!=null && targetFileContent.length() > generatedCode.length()){
			L.exception(this, "文件已存在 且添加过代码，覆盖终止");
			return false;
		}
		return true;
	}
	
	public void saveFile(String content, String path) {
		try {
			IOUtils.write(content, new FileOutputStream(new File(path)));
		} catch (Exception e) {
			L.exception("Scaffold", e.getMessage());
		}
	}

	public String getFileContent(String path) {
		String fileContent = "";
		try {
			fileContent = IOUtils.toString(new FileInputStream(path), "UTF-8");
		} catch (Exception e) {
			L.exception("CodeGenerator", e.getMessage());
			return "";
		}
		return fileContent;
	}
	
	

	public void metaData(String name, List<ColumnVO> columnList){
		
		if(!name.endsWith("Entity")){	//if module name
			name = StringUtils.capitalize(name)+"Entity";	//to Entity name
		}
		
		Class clazz = null;
		try {
			clazz = Class.forName("com.entity."+name);
		} catch (ClassNotFoundException e) {
			L.exception(this, e.getMessage());
			return;
		}
		
		Field[] fields = clazz.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		
		for (Field field : fields) {
			String fieldName = field.getName();
			String type = field.getGenericType().toString(); // 获取属性的类型
			
//			MaxLength maxlengthAnnotation = field.getDeclaredAnnotation(MaxLength.class);
//			String maxlength = "0";
//			if(maxlengthAnnotation != null){
//				maxlength = maxlengthAnnotation.value().toString();//获取maxlength注解
//			}
			
			ColumnVO vo = new ColumnVO();
			vo.setName(fieldName);
			vo.setType(type);
			//vo.setMaxlength(maxlength);
			//vo.setColumnDisplaySize(Integer.parseInt(maxlength));
			columnList.add(vo);
		}
	}
}
