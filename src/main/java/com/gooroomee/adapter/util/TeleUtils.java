package com.gooroomee.adapter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TeleUtils {
	
	public static final String SEVER_TYPE_LOCAL = "local";
	public static final String SEVER_TYPE_DEV = "dev";
	public static final String SEVER_TYPE_QA = "qa";
	public static final String SEVER_TYPE_PROD = "prod";
	
	public static String getTlgrCretDttm() {
		String pattern = "yyyyMMddHHmmss"; 
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
}
