package com.seeker.commodity.analyzer.vo;
 

public class ScoreVO extends BaseVO implements Comparable{
	public Double score = 0.0;
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public void setScore(Integer score) {
		this.score = score.doubleValue();
	}
	
	public void addScore(Double score){
		this.score = this.score + score;
	}

	public void addScore(Integer score){
		this.score = this.score + score;
	}
	
	@Override
	public int compareTo(Object o2) {
		
		ScoreVO p2 = (ScoreVO)o2;
		
		
		if(this.score > p2.score){
			return 1;
		}
		
		else if(this.score == p2.score) {
			return 0;
		}
		else{
			return -1;
		}
		
	}
	
}
