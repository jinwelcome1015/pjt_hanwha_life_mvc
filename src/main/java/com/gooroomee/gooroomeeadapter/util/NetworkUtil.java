package com.gooroomee.gooroomeeadapter.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.gooroomee.gooroomeeadapter.constant.IfConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetworkUtil {
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
