package com.codegen.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.codegen.common.annotation.MaxLength;
import com.codegen.vo.ColumnVO;
import com.utils.L;

public class AbstractGenerator {
	public void saveFile(String content, String path) {
		try {
			IOUtils.write(content, new FileOutputStream(new File(path)));
		} catch (Exception e) {
			L.exception("CodeGenerator", e.getMessage());
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

	public void metaData(String entity, List<ColumnVO> columnList){
		
		Field[] fields = entity.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		
		for (Field field : fields) {
			String fieldName = field.getName();
			String type = field.getGenericType().toString(); // 获取属性的类型
			String maxlength = "";
			if(field.getAnnotation(MaxLength.class)!=null){
				maxlength = field.getAnnotation(MaxLength.class).toString();//获取maxlength注解	
			}
			
			ColumnVO vo = new ColumnVO();
			vo.setName(fieldName);
			vo.setType(type);
			vo.setMaxlength(maxlength);
		}
	}
}
