package com.timer;

import com.env.Env;
import com.mutiServer.MutiServerUtil;
import com.utils.L;

public class CheckServerTask extends java.util.TimerTask {

	@Override
	public void run() {
		L.log(this, " is running");

		// center server 启动此定时器
		// 每一段时间检查一次所有的work server，刷新server的响应时间和是否可用。，
		if (Env.isCenter) {// 是中心服务器
			new MutiServerUtil().checkAll();
		}

	}// end run

}
