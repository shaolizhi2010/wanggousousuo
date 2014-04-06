package com.bean.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.connect.Connecter;
import com.db.DB;
import com.env.Env;
import com.file.FileUtil;
import com.mutiServer.MutiServerUtil;
import com.utils.C;
import com.utils.L;
import com.utils.U;
import com.utils.X;

public class BlogService {

	public List<String> getBlogList(String realPath) {
		List<String> list = new ArrayList<String>();

		File blogs = new File(realPath + "blog/");
		if (blogs.listFiles() != null) {
			File[] blogFiles = blogs.listFiles();
			//转arraylist
			List<File> files = Arrays.asList(blogFiles);
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

			for (File blogFile : files) {
				if (blogFile.getName().endsWith(".txt")) {
					String blogFileName = blogFile.getName()
							.replace(".txt", "");
					list.add(blogFileName);
				}
			}
		}
		return list;
	}

	public List<String> getBlogListFromServer() {
		List<String> list = new ArrayList<String>();
		String blogJson = new MutiServerUtil().getFiles(
				"http://bijia365.net:8008/wanggousousuo/", "blog/",
				FileUtil.File);
		List<Map> bloglist = U.jsonToListMap(blogJson);
		for (Map<String, String> m : bloglist) {
			String name = m.get("name");
			list.add(name);
		}

		return list;
	}

	public void digBlog() {
		String responseString = Connecter.connect("http://www.paidai.com/",
				null);
		Document doc;
		try {
			doc = new org.jdom2.input.SAXBuilder().build(new StringReader(
					responseString));
			List itemList = X.selectNodes(doc, "//a");

			String jianxuLink = "";
			for (Object o : itemList) {
				Element e1 = (Element) o;
				if (e1.getText() != null && e1.getText().contains("派商简讯")) {

					jianxuLink = e1.getAttributeValue("href"); // e.getAttribute("href");
					String responseString2 = Connecter
							.connect(jianxuLink, null);
					Document doc2 = new org.jdom2.input.SAXBuilder()
							.build(new StringReader(responseString2));
					;
					List contentList = X.selectNodes(doc2,
							"//div[@id='topic_content']//p");
					boolean latOneisTitle = false;

					String path = Env.basePath + "blog/";
					File file = null;

					for (Object c : contentList) {

						try {
							Element ce = (Element) c;

							String text = StringUtils.normalizeSpace(ce
									.getValue());
							// System.out.println(text);
							if (text.contains("派商简讯") || text.contains("派代")) {
								continue;
							}
							if (text.matches("^\\d+.*$") && text.length() < 50) {

								latOneisTitle = !latOneisTitle;

								String title = text.replaceAll("^\\d+、", "");
								System.out.println("title --- " + title);
								file = new File(path + title + ".txt");
								file.createNewFile();
							} else {
								if (text.length() > 100) {
									if(!file.exists()){
										file.createNewFile();
									}
									latOneisTitle = !latOneisTitle;
									System.out.println("content --- " + text);
									IOUtils.write(text, new FileOutputStream(
											file, true), "UTF-8");
								}

							}
						} catch (Exception e) {
							L.exception(this, e.getMessage());
							e.printStackTrace();
						}
						// break;
					}// end for
				}// end if
			}// end for
		} catch (Exception e) {
			L.exception(this, e.getMessage());
			e.printStackTrace();
		}

	}
}
