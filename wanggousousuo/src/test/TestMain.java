package test;

import com.digger.CommodityDigerThread;
import com.utils.ShopNames;

public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
        
            try {
            	 Thread t1 = new Thread(
            			 new CommodityDigerThread(
            					 ShopNames.lefeng
            					 .toString(), "经典"));
            	 t1.start();
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
