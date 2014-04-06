package test;

import java.math.BigDecimal;

import org.apache.commons.dbutils.QueryRunner;

import com.bean.logic.AccountService;
import com.bean.logic.DrawMoneyService;
import com.bean.logic.FanliOrderService;
import com.bean.logic.GuardService;
import com.bean.logic.KeywordService;
import com.bean.logic.LinkService;

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		String jdbcURL = "jdbc:mysql://bijia365.gotoftp1.com:3306/bijia365";
//		String jdbcDriver = "com.mysql.jdbc.Driver";

		try {
			System.out.println("begin");
		//	DbUtils.loadDriver(jdbcDriver);
		//	Connection conn = DriverManager.getConnection(jdbcURL, "bijia365", "cake4you@W");
			
			//QueryRunner qRunner = new QueryRunner();   
//			Map map = (Map) qRunner.query(conn,   
//				     "select * from friend_link ", new MapHandler());   
//			System.out.println(map.size());
//			System.out.println(map.get("name"));
			
		//	qRunner.update(conn, "insert into friend_link (name,url,desc) values (?,?,?)",new Object[]{"aa" , "bb","cc"});
			
			//System.out.println(new DrawMoneyService().add("123","bcd",new BigDecimal(35),"test2"));
			
			System.out.println(new FanliOrderService().get("123"));
			
			System.out.println("end");
			//conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
