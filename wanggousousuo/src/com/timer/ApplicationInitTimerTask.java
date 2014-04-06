package com.timer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.JDomSerializer;
import org.htmlcleaner.TagNode;


import com.bean.logic.KeywordService;
import com.bean.logic.SiteMapService;
import com.connect.SimpleConnecter;
import com.digger.keyword.KeywordDigger;
import com.env.Env;
import com.utils.App;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.P;
import com.utils.ShopNames;

/**
 * 服务器初始化的一些配置，
 * 没有卸载 listner里，防止出现的异常导致服务器无法启动.
 * @author shaoliz
 *
 */
public class ApplicationInitTimerTask extends java.util.TimerTask{
	
	@Override
	public void run() {
		   L.always(this,"ApplicationInitTimerTask  is running");
		   
		   //判断是否是生产环境，生产环境和测试环境的日志级别等不同
	    	if(new File(Env.basePath+"/"+"prod.flag").exists()){
	    		Env.isProd = true;
	    		L.level = LogLevel.exception;
	    	}
	    	L.always(this, "app isProd --- " + Env.isProd);
	    	
	    	//set日志级别
	    	String loglevel = P.getProperty("loglevel");
	    	L.setLevel(loglevel);
	    	L.always(this, "log level is --- " + L.level );
	    	
	    	//判读是否中心服务器
	    	String isCenter = P.getProperty("iscenter");
	    	if( isCenter!= null && "1".equalsIgnoreCase(isCenter)){//是中心服务器
	    		Env.isCenter = true;
	    	}
	    	L.always(this, "app isCenter --- " + Env.isCenter);
		   
	}//end run
	
}
