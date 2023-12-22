package com.gooroomee.backbone.external.component.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gooroomee.backbone.external.component.GrmCounsellingOtpUriSupplier;
import com.gooroomee.backbone.external.util.NetworkUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 구루미 화상상담 시스템 입장 URI를 제공하는 getConnectableUri 메서드를 지닌 인터페이스인 GrmCounsellingOtpUriSupplier 의 구현 클래스
 * 연결 테스트를 수행하고, 연결이 성공하면 연결을 끊고, 그 연결이 성공한 URI를 반환한다. 
 * (
 * 	연결테스트 : 이중화 환경의 경우, 
 * 		첫번째: 로컬서버
 * 		두번째: 반대편서버
 * )
 * </pre>
 * @author 신용진
 */
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
	public String getConnectableUri() throws IOException {
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
				log.debug("[CONNECT SUCCESS] ([IP] : {}, [PORT] : {})", ip, port);
			} catch (IOException e) {
				log.debug("[CONNECT FAIL] ([IP] : {}, [PORT] : {}, [IOException] : {})", ip, port, e.getMessage());
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

	
	
	/**
	 * IpPortInfo 객체의 List를 로컬 머신의  IP를 지닌 IpPortInfo 객체가 List의 첫번째의 요소가 되도록 순서를 조정한다.
	 * @param ipPortInfos 로컬 머신의  IP를 지닌 IpPortInfo 객체를 List의 첫번째의 요소가 되도록 순서를 조정하기 이전의 IpPortInfo 객체의 List
	 * @return 로컬 머신의  IP를 지닌 IpPortInfo 객체를 List의 첫번째의 요소가 되도록 순서를 조정한 이후의 IpPortInfo 객체의 List
	 */
	private List<IpPortInfo> reorderLocalFirst(List<IpPortInfo> ipPortInfos) {
		String localIpAddress = NetworkUtil.getLocalIpAddress();

		for (int i = 0; i < ipPortInfos.size(); i++) {
			IpPortInfo ipPortInfo = ipPortInfos.get(i);
			if (localIpAddress.equals(ipPortInfo.getIp())) {
				ipPortInfos.remove(i);
				ipPortInfos.add(0, ipPortInfo);
			}
		}
		log.info("[localIpAddress] : {}", localIpAddress);
		log.info("[reorderLocalFirst] : {}", ipPortInfos);

		return ipPortInfos;
	}

	/**
	 * properties 파일에 등록된 구루미 화상상담 시스템 입장 URI 발행 서버의 URI 리스트를 반환한다.
	 * @return properties 파일에 등록된 구루미 화상상담 시스템 입장 URI 발행 서버의 URI 리스트
	 */
	private List<IpPortInfo> getIpPortInfosOfMvcEntryUriIssueService() {
		IpPortInfo ipPortInfo_1 = this.getIpPortInfo(uriOfMvcEntryUriIssueService_1);
		IpPortInfo ipPortInfo_2 = this.getIpPortInfo(uriOfMvcEntryUriIssueService_2);

		List<IpPortInfo> ipPortInfoList = new ArrayList<>();
		ipPortInfoList.add(ipPortInfo_1);
		ipPortInfoList.add(ipPortInfo_2);

		return ipPortInfoList;
	}

	/**
	 * 인자로 받은 URI 문자열에서 뽑아낸 IP, PORT 정보를 담고 있는 IpPortInfo 객체를 생성해 반환한다.
	 * @param uri URI 문자열
	 * @return IpPortInfo 객체 (인자로 받은 URI 문자열에서 뽑아낸 IP, PORT 정보를 담고 있는)
	 */
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

	
	/**
	 * URI 문자열과, 그 URI에서 뽑아낸 IP, PORT를 담기 위한 DTO클래스
	 * @author 신용진
	 */
	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	private static class IpPortInfo {
		
		/** URI */
		private String uri;
		
		/** IP */
		private String ip;
		
		/** PORT */
		private int port;
	}
}
