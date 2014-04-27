package com.trace;

import java.util.List;

import com.entity.KeywordEntity;
import com.service.KeywordService;

/**
 * 追踪keyword 的一些信息，如 最后更新时间，被搜索次数等。
 * @author Administrator
 *
 */
public class KeywordTrace {
	
	KeywordService ks = new KeywordService();

	public static void main(String[] args) {
		
	}
	
	/**
	 * 被搜索次数加1
	 */
	public void timePlus(String keyword){
		
		//set query obj
		KeywordEntity query = new KeywordEntity();
		query.setKeyword(keyword);
		
		//if exit, tims ++
		List<KeywordEntity> list = ks.list(query);
		if(list !=null && list.size()>0){
			KeywordEntity ke = list.get(0);
			ke.timesPlus();
			ks.update(ke);
		}
		//if new keyword, set time to 1, insert
		else{
			KeywordEntity ke = new KeywordEntity();
			ke.setKeyword(keyword);
			ke.setTimes("1");
			ks.add(ke);
		}
		
		
		
		
	}

}
