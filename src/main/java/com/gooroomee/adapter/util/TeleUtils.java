package com.gooroomee.adapter.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeleUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(TeleUtils.class);
	
	public static final String SEVER_TYPE_LOCAL = "local";
	public static final String SEVER_TYPE_DEV = "dev";
	public static final String SEVER_TYPE_QA = "qa";
	public static final String SEVER_TYPE_PROD = "prod";
	
	public static final String DEFAULT_IP_ADDRESS = "127.0.0.1";
	
	public static String getTlgrCretDttm() {
		String pattern = "yyyyMMddHHmmss"; 
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	
	public static String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
					if(!inetAddress.isLoopbackAddress() 
							&& !inetAddress.isLinkLocalAddress() 
							&& inetAddress.isSiteLocalAddress()
					) {
						return inetAddress.getHostAddress();
					}
					
				}
			}
		} catch (SocketException e) {
			logger.error(e.getMessage());
		}
		
		return DEFAULT_IP_ADDRESS;
	}

	/**
	 * 
	 * @return
	 */
	public static String getRandomNumber() {
		return getRandomNumber(4);
	}

	private static String getRandomNumber(int digits) {
		if(digits < 1) {
			throw new IllegalArgumentException("digits must be a positive number.");
		}
		
		int exclusiveUpperBound = (int) Math.pow(10, digits);
		SecureRandom secureRandom = new SecureRandom();
		int nextInt = secureRandom.nextInt(exclusiveUpperBound);
		
		String formattedRandomNumber = String.format("%04d", nextInt);
		
		return formattedRandomNumber;
	}
	
}
