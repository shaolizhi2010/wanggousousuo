package com.bean.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.env.Env;
import com.timer.ApplicationInitTimerTask;
import com.timer.DailyTask;
import com.utils.L;
import com.utils.LogLevel;

public class ApplicationInitListner implements ServletContextListener {

	Timer timer1 = new Timer();
	Timer timer2 = new Timer();
	Timer timer3 = new Timer();
	Timer timer4 = new Timer();

	public void contextDestroyed(ServletContextEvent arg0) {
 

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
		
		 //每天执行一次 time task
		 DailyTask dailyTask = new DailyTask();
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.HOUR, 1);//每天凌晨一点执行
		 calendar.set(Calendar.MINUTE, 59);//每天凌晨一点一分执行
		// timer4.schedule(dailyTask, calendar.getTime(),24*60*60*1000);	//24小时执行一次
		 timer4.schedule(dailyTask, 3*1000, 24*60*60*1000);	//24小时执行一次
	}

}
