package com.bean.web;

import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.connect.SimpleConnecter;
import com.env.Env;
import com.env.StaticInfo;
import com.timer.ApplicationInitTimerTask;
import com.timer.CheckServerTask;
import com.timer.DailyTask;
import com.timer.RegistServerTask;
import com.utils.C;
import com.utils.L;
import com.utils.LogLevel;
import com.utils.P;

public class ApplicationInitListner implements ServletContextListener {

	Timer timer1 = new Timer();
	Timer timer2 = new Timer();
	Timer timer3 = new Timer();
	Timer timer4 = new Timer();

	public void contextDestroyed(ServletContextEvent arg0) {

		if (!"1".equalsIgnoreCase(P.getProperty("iscenter"))) {// 判断是否是中心服务器
			String url = P.getProperty("localurl");
			String md5 = new com.utils.MD5().toMD5(url + C.md5_random_token);
			if (Env.isProd) {
				SimpleConnecter
						.connect("http://www.wanggousousuo.com/common?method=logoffServer&url="
								+ url + "&md5=" + md5);
			} else {
//				SimpleConnecter
//						.connect("http://192.168.1.100/wanggousousuo/common?method=logoffServer&url="
//								+ url + "&md5=" + md5);
			}
			L.always(this, "app is logoff");
		}

		timer1.cancel();
		timer2.cancel();
		timer3.cancel();
		timer4.cancel();

		L.always(this, "app stop ................");
	}

	/**
	 * 系统加载的时候运行下面这个方法
	 */
	public void contextInitialized(ServletContextEvent sce) {

		L.level = LogLevel.debug;
		L.always(this, "app start up ................");

		String basePath = sce.getServletContext().getRealPath("/");
		Env.basePath = basePath;
		L.always(this, "app base path --- " + Env.basePath);

		// 服务器初始化，time tast
		ApplicationInitTimerTask appInitTask = new ApplicationInitTimerTask();
		timer1.schedule(appInitTask, 0);// 立刻开始
		
		/* center server 执行这个task， 检查work server 服务器，检查每台服务器的响应时间，是否可用等 */
		CheckServerTask checkServerTask = new CheckServerTask();
		timer2.schedule(checkServerTask, 10 * 1000, 5 * 60 * 1000); // 10秒之后开始，5分钟一次
		
		/* work server 执行此task, 注册服务器 */
		RegistServerTask registServerTask = new RegistServerTask();
		timer3.schedule(registServerTask, 5 * 1000, 60 * 60 * 1000); // 5秒之后开始，1小时一次

		 //每天执行一次 time task
		 DailyTask dailyTask = new DailyTask();
		 //Calendar calendar = Calendar.getInstance();
		 //calendar.set(Calendar.HOUR, 1);//每天凌晨一点执行
		 //calendar.set(Calendar.MINUTE, 1);//每天凌晨一点一分执行
		 timer4.schedule(dailyTask, 30*1000, 24*60*60*1000);	//24小时执行一次
	}

}
