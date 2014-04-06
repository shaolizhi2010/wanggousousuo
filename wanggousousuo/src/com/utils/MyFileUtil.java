package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class MyFileUtil {

	 

	public static void mkdir(String path) {
		File dir = new File(path);
		if (!dir.exists()) { // 建文件夹
			dir.mkdirs();
		}
	}
	
	public static String getKuaidiComJson(){
		try {
			L.level = LogLevel.debug;
			return IOUtils.toString(new FileReader("/kuaidi.json")); 
		} catch (FileNotFoundException e) {
			L.debug(MyFileUtil.class, e.getMessage());
			L.debug(MyFileUtil.class, "can not get kuaidi json");
		} catch (IOException e) {
			L.debug(MyFileUtil.class, e.getMessage());
			L.debug(MyFileUtil.class, "can not get kuaidi json");
		}
		return "";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
