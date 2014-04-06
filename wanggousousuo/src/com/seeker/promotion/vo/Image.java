package com.seeker.promotion.vo;

import com.base.vo.ScoreVO;

public class Image extends ScoreVO {
	
	String src = "";
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	Integer size = 0;

}
