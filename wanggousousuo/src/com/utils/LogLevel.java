package com.utils;

public enum LogLevel {
	trace,debug,log,exception,error,always,off;  
	
	private LogLevel level ;

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}
	
	//set log level by string
	public void setLevel(String level){
		for(LogLevel loglevel : LogLevel.values()){
			if(level.equalsIgnoreCase(loglevel.toString())){
				setLevel(loglevel);
				return;
			}
		}
	}
	
}
