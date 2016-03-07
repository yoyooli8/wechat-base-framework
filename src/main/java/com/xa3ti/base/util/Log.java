package com.xa3ti.base.util;

import org.apache.log4j.Logger;


public class Log {
	public static String appName = "com.sigma.wx";
	public static Logger log;
	static {
		log = Logger.getLogger(appName);
	}
	
	public static void d(Object message){
		log.debug(message);
	}
	
	public static void e(Object message){
		log.error(message);
	}
}
