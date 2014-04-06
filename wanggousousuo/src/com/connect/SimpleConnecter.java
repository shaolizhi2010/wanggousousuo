package com.connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.utils.L;

public class SimpleConnecter {
	public static String connect(String url){
		
	       String result = "";  
	        try {  
	            URL httpurl = new URL(url);  
	            HttpURLConnection httpConn = (HttpURLConnection) httpurl  
	                    .openConnection();  
	            httpConn.setDoInput(true);  
	            BufferedReader in = new BufferedReader(new InputStreamReader(  
	                    httpConn.getInputStream()));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	            in.close();  
	        } catch (Exception e) {  
	             L.exception("SimpleConnecter", e.getMessage());
	        }  
	        return result;  
	}
	
	public static String connect(String url,String charset){
		
	       String result = "";  
	        try {  
	            URL httpurl = new URL(url);  
	            HttpURLConnection httpConn = (HttpURLConnection) httpurl  
	                    .openConnection();  
	            httpConn.setDoInput(true);  
	            BufferedReader in = new BufferedReader(new InputStreamReader(  
	                    httpConn.getInputStream(),charset));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	            in.close();  
	        } catch (Exception e) {  
	             L.exception("SimpleConnecter", e.getMessage());
	        }  
	        return result;  
	}
}
