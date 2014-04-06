package com.timer;

import com.connect.SimpleConnecter;
import com.env.Env;
import com.utils.C;
import com.utils.L;
import com.utils.P;

public class RegistServerTask extends java.util.TimerTask{
	
	@Override
	public void run() {
		 //  L.log(this," is running");
		   
		   /* 注册url的相关操作，如果是center，不注册url
		    * center 是提供给用户提供网页级别访问的server， 非center只开放json访问， 
		    * 可以分担center的压力，但非center服务器不会直接提供给用户 
		    * 轮询，非center每间隔一段时间（如5分钟）会访问center并重新注册一次，每次注册会刷新center上的时间， 
		    * center也会定时检查，如果最近一次注册的时间过久，则会删除掉 
		    * 
		    * 
		    * 
	    	*/
	    	if( !Env.isCenter){//不是中心服务器
	        
	    		String url =  P.getProperty("localurl");
		    	L.log(this,"url in config file is  "+url);
		    	
		    	String md5 = new com.utils.MD5().toMD5(url+C.md5_random_token);
		    	
	    		String centerServer = "";
	        		centerServer = "http://www.wanggousousuo.com/";
	        		SimpleConnecter.connect(centerServer + "common?method=registeServer&url="+url+"&md5="+md5);
		        	L.log(this, "Request regist Server : "+url+" on "+ centerServer);
	        		
		        	centerServer = "http://192.168.1.100/wanggousousuo/";
	        		SimpleConnecter.connect(centerServer + "common?method=registeServer&url="+url+"&md5="+md5);
		        	L.log(this, "Request regist Server : "+url+" on "+ centerServer);
	        	
	    	}
	    	else{
	    		//L.log(this, "app is center --- true" );
	    	}
	    	
	    	
	}//end run
	
}
