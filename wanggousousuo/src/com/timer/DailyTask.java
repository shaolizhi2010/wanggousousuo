package com.timer;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.env.Env;
import com.utils.C;
import com.utils.L;
import com.utils.P;

public class DailyTask extends java.util.TimerTask {

	@Override
	public void run() {
		L.trace(this, " is running");
		
	}// end run

//	public List<String> getkeywordsFromDB() {
//		List<String> keywords = new ArrayList<String>();
//		KeywordService ks = new KeywordService();
//		List<Map> keywordmaplist = ks.getKeywordList();
//		for (Map m : keywordmaplist) {
//			keywords.add((String) m.get("keyword"));
//		}
//
//		return keywords;
//	}
//	
	

}
