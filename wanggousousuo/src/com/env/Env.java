package com.env;

import com.utils.U;

public class Env {
	public static String basePath = new U().getRulePath();//系统根目录
	public static Boolean isProd = false;//是否是生产环境
	public static Boolean isCenter = false;//是否是中心服务器
	
}
