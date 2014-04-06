package com.seeker.promotion.vo;

import java.util.LinkedList;
import java.util.List;

import com.base.vo.ScoreVO;
import com.utils.U;

public class Promotion extends ScoreVO {
	List<String> imgList = new LinkedList<String>();
	String href = "";
	String title = "";
	String content = "";
	Long timestamp = System.currentTimeMillis();
	String date = U.curDate();
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public void addImg(String src) {
		imgList.add(src);
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
