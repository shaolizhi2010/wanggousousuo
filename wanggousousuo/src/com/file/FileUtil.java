package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.activation.FileTypeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.env.Env;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.utils.L;

public class FileUtil {
	
	public static String File = "file";
	public static String All = "all";
	public static String Forder = "forder";
	
	//返回某文件夹中的文件和文件夹
	//type = file, 只返回文件
	//type = forder, 只返回forder
	//type = all , 返回 文件和文件夹
	public List<FileSummarize> getFilesAndDirs(String path, String type){
		List<FileSummarize> list = new LinkedList<FileSummarize>();
		File rootFolder = new File(path);
		if(rootFolder.listFiles() != null){	//遍历根目录
			File[] filesArray = rootFolder.listFiles();
			List<File> files = Arrays.asList(filesArray);
			//排序
			Collections.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					if (o1.isDirectory() && o2.isFile())
						return -1;
					if (o1.isFile() && o2.isDirectory())
						return 1;
					return (int)(o2.lastModified()-o1.lastModified());
				}
			});
			
			for(File file : files){	//遍历跟目录 得到目录列表
			
				String name = file.getName();
				FileSummarize fileSummarize = new FileSummarize();
				fileSummarize.setName(name);
				
				if(type.equalsIgnoreCase(FileUtil.All)){
					list.add(fileSummarize);	//文件夹和文件
				}
				else if(type.equalsIgnoreCase(FileUtil.Forder) && file.isDirectory()){
					list.add(fileSummarize);	//只文件夹
				}
				else if(type.equalsIgnoreCase(FileUtil.File) && !file.isDirectory()){
					list.add(fileSummarize);	//只文件
				}
			}
		}
		return list;
	}
	
	/*
	//返回某文件夹中的文件和文件夹
		//type = file, 只返回文件
		//type = forder, 只返回forder
		//type = all , 返回 文件和文件夹
		public List<FileSummarize> getFilesAndDirs(String path, String forder, String type,Boolean recursion){
			List<FileSummarize> list = new LinkedList<FileSummarize>();
			File rootFolder = new File(path);
			if(rootFolder.listFiles() != null){	//遍历根目录
				File[] files = rootFolder.listFiles();
				for(File file : files){	//遍历跟目录 得到目录列表
				
					String name = file.getName();
					FileSummarize fileSummarize = new FileSummarize();
					fileSummarize.setName(name);
					fileSummarize.setForder(forder);
					
					if(type.equalsIgnoreCase(FileUtil.All)){
						list.add(fileSummarize);	//文件夹和文件
					}
					else if(type.equalsIgnoreCase(FileUtil.Forder) && file.isDirectory()){
						list.add(fileSummarize);	//只文件夹
					}
					else if(type.equalsIgnoreCase(FileUtil.File) && !file.isDirectory()){
						list.add(fileSummarize);	//只文件
					}
					
					if(recursion && file.isDirectory()){	//递归所有子目录
						list.addAll( getFilesAndDirs(file.getAbsolutePath(),forder+file.getName()+"/",type,recursion));
					}
				}
			}
			return list;
		}
	
	*/
	
	public String getFilesJson(String path, String type){
		return new Gson().toJson(getFilesAndDirs(path,type));	//返回list 的 json
		
	}
	
	public String getFileContent(String path){
		String fileContent = "";
		try {
			fileContent = IOUtils.toString(new FileInputStream(path),"UTF-8");
		} catch (Exception e) {
			L.exception(this, e.getMessage());
			return "";
		} 
		return fileContent;
	}
	
	public void saveFile(String content, String path){
		try {
			IOUtils.write(content, new FileOutputStream(new File(path)));
		} catch ( Exception e) {
			L.exception(this, e.getMessage());
		}  
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Env.basePath = "C:/workspace/wanggousousuo/WebContent/";
		System.out.println(new FileUtil().getFilesJson(Env.basePath+"blog/",FileUtil.All));
	}

}
