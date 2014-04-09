package test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
        
    		HttpClient httpclient = new DefaultHttpClient();  
            // 利用HTTP GET向服务器发起请求  
            HttpGet get = new HttpGet("http://www.jd.com");
            try {
    			HttpResponse response = httpclient.execute(get);
    			String s = response.getFirstHeader("title").toString();
    			System.out.println(s);
    		} catch ( Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end...");

    }

}
