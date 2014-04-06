package com.utils;

public class L {

	public static LogLevel level = LogLevel.log;
	
	public static void trace(Object c, String msg) {

		if (LogLevel.trace.compareTo(level) >= 0) {
			sysout(c, msg, null, LogLevel.trace);
		}
	}
	
	public static void debug(Object c, String msg) {

		if (LogLevel.debug.compareTo(level) >= 0) {
			sysout(c, msg, null, LogLevel.debug);
		}
	}
	
	public static void log(Object c, String msg) {

		if (LogLevel.log.compareTo(level) >= 0) {
			sysout(c, msg, null, LogLevel.log);
		}
	}

	public static void exception(Object c, String msg) {

		if (LogLevel.exception.compareTo(level) >= 0) {
			sysout(c, msg, null, LogLevel.exception);
		}

	}
	public static void error(Object c, String msg) {

		if (LogLevel.error.compareTo(level) >= 0) {
			sysout(c, msg, null, LogLevel.error);
		}

	}
	
	public static void always(Object c, String msg) {

		if (LogLevel.always.compareTo(level) >= 0) {
			sysout(c, msg, null, LogLevel.always);
		}
	}

	public static void sysout(Object c, String msg, String value,
			LogLevel loglevel) {

		String classType = "";
		if (c == null) {
			classType = "unkown";
		} else if (c instanceof String) {
			classType = (String) c;
		} else {
			classType = String.valueOf( c.getClass().getSimpleName() ) ;
		}

		 if(msg == null )msg = " ";
		 if(value == null) value = " ";
		
		System.out.println(U.curTime() + " - " + loglevel + " - " + classType
				+ " - " + msg );
	}
	
	//set log level by string
	public static void setLevel(String level){
		for(LogLevel loglevel : LogLevel.values()){
			if(level.equalsIgnoreCase(loglevel.toString())){
				L.level = loglevel;
				return;
			}
		}
	}

}
