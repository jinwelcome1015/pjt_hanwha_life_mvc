package com.gooroomee.gooroomeeadapter.component.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gooroomee.gooroomeeadapter.component.GrmCounsellingOtpUriSupplier;
import com.gooroomee.gooroomeeadapter.util.NetworkUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GrmCounsellingOtpUriSupplierWithConnectTest implements GrmCounsellingOtpUriSupplier {

	/** MVC시스템입장URI를 발행해주는 서비스의 URI */
	@Value(value = "${mvc.entry-uri-issue-service.uri_1}")
	private String uriOfMvcEntryUriIssueService_1;

	/** MVC시스템입장URI를 발행해주는 서비스의 URI */
	@Value(value = "${mvc.entry-uri-issue-service.uri_2}")
	private String uriOfMvcEntryUriIssueService_2;

	@Override
	public String getFirstConnectableUri() throws IOException {
		List<IpPortInfo> ipPortInfosOfMvcEntryUriIssueService = this.getIpPortInfosOfMvcEntryUriIssueService();
		List<IpPortInfo> reorderedLocalFirstIpPortInfos = this.reorderLocalFirst(ipPortInfosOfMvcEntryUriIssueService);

		String firstConnectableUri = null;

		for (int i = 0; i < reorderedLocalFirstIpPortInfos.size(); i++) {
			IpPortInfo ipPortInfo = reorderedLocalFirstIpPortInfos.get(i);

			String ip = ipPortInfo.getIp();
			int port = ipPortInfo.getPort();

			boolean isConnectable = false;

			Socket socket = new Socket();
			try {
				socket.connect(new InetSocketAddress(ip, port));
			} catch (IOException e) {
				log.info("[IP] : {}, [PORT] : {}, [IOException] : {}", ip, port, e.getMessage());
			} finally {
				isConnectable = socket.isConnected();
				socket.close();
			}

			if (isConnectable) {
				firstConnectableUri = ipPortInfo.getUri();
				break;
			} else {
				continue;
			}
		}

		if (firstConnectableUri == null) {
			throw new RuntimeException(String.format("%s 중, 연결가능한 URI가 없습니다.", reorderedLocalFirstIpPortInfos));
		}

		return firstConnectableUri;
	}

	private List<IpPortInfo> reorderLocalFirst(List<IpPortInfo> ipPortInfos) {
		String localIpAddress = NetworkUtil.getLocalIpAddress();

		for (int i = 0; i < ipPortInfos.size(); i++) {
			IpPortInfo ipPortInfo = ipPortInfos.get(i);
			if (localIpAddress.equals(ipPortInfo.getIp())) {
				ipPortInfos.remove(i);
				ipPortInfos.add(0, ipPortInfo);
			}
		}

		log.info("[reorderLocalFirst] : {}", ipPortInfos);

		return ipPortInfos;
	}

	private List<IpPortInfo> getIpPortInfosOfMvcEntryUriIssueService() {
		IpPortInfo ipPortInfo_1 = this.getIpPortInfo(uriOfMvcEntryUriIssueService_1);
		IpPortInfo ipPortInfo_2 = this.getIpPortInfo(uriOfMvcEntryUriIssueService_2);

		List<IpPortInfo> ipPortInfoList = new ArrayList<>();
		ipPortInfoList.add(ipPortInfo_1);
		ipPortInfoList.add(ipPortInfo_2);

		return ipPortInfoList;
	}

	private IpPortInfo getIpPortInfo(String uri) {
		String tokenOfHttp = "http://";
		String tokenOfHttps = "https://";

		String pattern1 = String.format("^(%s|%s)", tokenOfHttp, tokenOfHttps);
		String pattern2 = "/.*";

		String temp = uri;
		temp = temp.replaceAll(pattern1, "");
		temp = temp.replaceAll(pattern2, "");

		String[] ipPort = temp.split(":");

		String ip;
		int port;

		if (ipPort.length == 1) {
			ip = ipPort[0];
			boolean isHttp = Pattern.matches(String.format("^%s", tokenOfHttp), uri);
			boolean isHttps = Pattern.matches(String.format("^%s", tokenOfHttps), uri);
			if (isHttp) {
				port = 80;
			} else if (isHttps) {
				port = 443;
			} else {
				throw new RuntimeException("URI 의 프로토콜이 HTTP도, HTTPS 도 아닙니다.");
			}
		} else if (ipPort.length == 2) {
			ip = ipPort[0];
			port = Integer.parseInt(ipPort[1]);
		} else {
			throw new RuntimeException(String.format("IP 와 PORT 를 추출하는 과정에서 예외가 발생했습니다. ([URI] : %s)", uri));
		}

		IpPortInfo ipPortInfo = new IpPortInfo(uri, ip, port);

		return ipPortInfo;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	private static class IpPortInfo {
		private String uri;
		private String ip;
		private int port;
	}
}
