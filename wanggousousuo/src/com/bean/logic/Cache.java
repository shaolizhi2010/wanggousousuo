package com.bean.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.env.Env;
import com.service.SearchHistoryService;
import com.utils.C;
import com.utils.L;
import com.utils.ShopNames;

public class Cache {

	/**
	 * save json to db
	 * @param keyword
	 * @param shopname
	 * @param json
	 */
	public void save(String keyword, String shopname, String json) {
		new SearchHistoryService().save(shopname, keyword, json);
	}
	
	/**
	 * save json to local 
	 * 
	 * @param keyword
	 * @param shopname
	 * @param json
	 * @param realpath
	 */
	public void save(String keyword, String shopname, String json,
			String realpath) {

		// remove blank at beginning and end
		keyword = StringUtils.strip(keyword, " ");
		shopname = StringUtils.strip(shopname, " ");

		// path
		String pathAndName = buildPathAndName(keyword, shopname, realpath+File.separator
				+ C.cachePath);

		try {
			// 以[keyword].txt 为文件名保存，标识这个keyword被搜索过，目前为空，未来可添加一些信息到这个文件
			File keywordIndexFile = new File(realpath + File.separator
					+ C.cachePath + keyword + ".txt");// keyword
														// file,
														// empty，

			// save [keyword].txt
			IOUtils.write("", new FileOutputStream(keywordIndexFile));
			// save to file
			L.debug(this, "begin save to file : " + pathAndName);
			if(StringUtils.isNotBlank(json) && json.length()>50){//非空
				IOUtils.write(json, new FileOutputStream(pathAndName), "UTF-8");
				L.debug(this, "finished save to file: " + new File(pathAndName));
			}
			else{
				L.debug(this, "json is empty - " + shopname +" - "+ keyword);
			}
			
			

		} catch (FileNotFoundException e) {
			L.exception(this, e.getMessage());
			L.exception(this, "save json failed file not found : path  --- "
					+ pathAndName);
		} catch (IOException e) {
			L.exception(this, e.getMessage());
			L.exception(this, "save json failed :io exception : path  --- "
					+ pathAndName);
		}
		
	}

	/**
	 * get cache json
	 * 
	 * @param keyword
	 * @param shopname
	 * @param realpath
	 * @return
	 */
	public String get(String keyword, String shopname, String realpath,
			boolean forSearchEngine) {

		// remove blank at beginning and end
		keyword = StringUtils.strip(keyword, " ");
		shopname = StringUtils.strip(shopname, " ");
		// path

		File cachesParentDir = new File(realpath);
		for (File file1 : cachesParentDir.listFiles()) {	//遍历根目录
			if (file1.isDirectory() && file1.getName().startsWith("cache")) {

					String pathAndName = buildPathAndName(keyword, shopname,
							file1.getAbsolutePath());
					File localjson = new File(pathAndName);
					if (localjson.exists()) { // local json is found

						// if local json is stale ,
						if ((!forSearchEngine && System.currentTimeMillis()
								- localjson.lastModified() > C.cachePeriod)
								|| (forSearchEngine && System
										.currentTimeMillis()
										- localjson.lastModified() > C.cachePeriodForSearchEngine)

						) {
							return "";
						}

						try {
							// return json string
							L.debug(this, "geting json from --- " + pathAndName);
							return IOUtils.toString(new FileInputStream(
									pathAndName), "UTF-8");

						} catch (Exception e) {
							L.exception(this, e.getMessage());
							L.exception(this, "get json failed : path  --- "
									+ pathAndName);
						}
					}// end if
			}// end if

		}// end for

		// local json not found
		return null;
	}

	public String buildPathAndName(String keyword, String shopname,
			String cachepath) {
		return cachepath + File.separator +keyword + "_" + shopname + ".json";
	}

}
