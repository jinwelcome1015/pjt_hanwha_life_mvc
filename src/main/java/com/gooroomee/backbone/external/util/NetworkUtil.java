package com.gooroomee.backbone.external.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.gooroomee.backbone.external.constant.IfConstant;

import lombok.extern.slf4j.Slf4j;


/**
 * 네트워크 utility 클래스
 * @author 신용진
 */
@Slf4j
public class NetworkUtil {
	
	/**
	 * 로컬머신의 IP를 반환한다.
	 * @return 로컬머신의 IP
	 */
	public static String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
						return inetAddress.getHostAddress();
					}

				}
			}
		} catch (SocketException e) {
			log.error(e.getMessage());
		}

		return IfConstant.DEFAULT_IP_ADDRESS;
	}
	
	/**
	 * <pre>
	 * IP의 형식을 다음과 같이 변경한다. 
	 * [example] : 192.168.16.1 → 192168016001
	 * </pre>
	 * @param ipAddress 형식 변경 전 IP ([example] : 192.168.16.1)
	 * @return 형식 변경 후 IP ([example] : 192168016001)
	 */
	public static String formatIpAddress(String ipAddress) {
		String formattedIpAddrString = "";

		String[] ipAddressTokens = ipAddress.split("\\.");
		for (String ipAddressToken : ipAddressTokens) {
			Integer ipTokenDigit = Integer.valueOf(ipAddressToken);
			String formattedToken = String.format("%03d", ipTokenDigit);
			formattedIpAddrString += formattedToken;
		}

		return formattedIpAddrString;
	}
}
