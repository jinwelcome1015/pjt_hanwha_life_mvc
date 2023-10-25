package com.gooroomee.gooroomeeadapter.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeaderResponseMessage;
import com.gooroomee.gooroomeeadapter.exception.IfException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class IfUtil {
	
	/** 송신 시스템 코드 */
	private static final String TRNM_SYS_CODE = IfConstant.TRNM_SYS_CODE;

	/** 채널 타입 코드 */
	private static final String CHNL_TYPE_CODE = IfConstant.IfChnlTypeCode.SERVER.getValue();

	/** 소속 기관 코드 */
	private static final String BELN_ORGN_CODE = IfConstant.BELN_ORGN_CODE;
	
	/** RestTemplate */
	private RestTemplate restTemplate;

	/** 사원 번호 */
	private String enmb;

	/** 활성 프로필(PROD, QA, DEV, LOCAL) */
	private String activeProfile;

	/** 전송대상(MCI/ESB/FEB) URL 정보 */
	private String targetBaseUrl;
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


	public IfUtil(RestTemplate restTemplate, String enmb, String activeProfile, String targetBaseUrl) {
		super();
		this.restTemplate = restTemplate;
		this.enmb = enmb;
		this.activeProfile = activeProfile;
		this.targetBaseUrl = targetBaseUrl;
	}

	public IfTelegramHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode) {
		return createHeader(itfcId, rcveSrvcId, rcveSysCode, "N");
	}

	public IfTelegramHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode,
			String prsnInfoIncsYn) {
		IfTelegramHeader header = new IfTelegramHeader();
		header.setItfcId(itfcId);
		header.setRcveSrvcId(rcveSrvcId);
		header.setChnlTypeCode(CHNL_TYPE_CODE);
		header.setTrnmSysCode(TRNM_SYS_CODE);
		header.setRcveSysCode(rcveSysCode);
		header.setEmnb(this.getEnmb());
		header.setBelnOrgnCode(BELN_ORGN_CODE);
		header.setPrsnInfoIncsYn(prsnInfoIncsYn);
		header.setIpAddr(this.formatIpAddress(this.getLocalIpAddress()));
		header.setTlgrCretDttm(this.getTlgrCretDttm());
		header.setRndmNo(this.getRandomNumber());
		header.setServerType(this.getServerType());
		header.setRspnDvsnCode(IfConstant.IfRspnDvsnCode.SEND.getValue());
		return header;
	}

	private String getServerType() {
		String serverType = "";

		String profile = this.getActiveProfile();

		if ("local".equalsIgnoreCase(profile)) {
			serverType = IfConstant.IfServerType.LOCAL.getValue();
		} else if ("dev".equalsIgnoreCase(profile)) {
			serverType = IfConstant.IfServerType.DEV.getValue();
		} else if ("qa".equalsIgnoreCase(profile)) {
			serverType = IfConstant.IfServerType.QA.getValue();
		} else if ("prod".equalsIgnoreCase(profile)) {
			serverType = IfConstant.IfServerType.PROD.getValue();
		} else {
			serverType = IfConstant.IfServerType.ETC.getValue();
		}

		return serverType;
	}

	public String getTargetFullUrl(IfConstant.IfType ifType) {

		String targetFullUrl = "";

		String targetBaseUrl = this.getTargetBaseUrl();

		if (ifType == IfConstant.IfType.MCI) {
			targetFullUrl = targetBaseUrl + "/mci" + "/" + TRNM_SYS_CODE.toLowerCase();
		} else if (ifType == IfConstant.IfType.ESB) {
			targetFullUrl = targetBaseUrl + "/esb";
		} else if (ifType == IfConstant.IfType.FEB) {
			targetFullUrl = targetBaseUrl + "/feb";
		}

		return targetFullUrl;
	}

	public <I, O> IfTelegram<O> sendAndReceiveMessage(IfConstant.IfType ifType, IfTelegramHeader header,
			I inputDto, Class<O> outputDtoClass) throws JsonProcessingException, URISyntaxException {
		ObjectMapper objectMapper = OBJECT_MAPPER;

		IfTelegram<I> requestTelegram = new IfTelegram<I>();
		requestTelegram.setHeader(header);
		requestTelegram.setPayload(inputDto);

		IfTelegram<O> responseTelegram = null;

		String requestJson = objectMapper.writeValueAsString(requestTelegram);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON));

		String targetFullUrl = getTargetFullUrl(ifType);

		RequestEntity<String> requestEntity = new RequestEntity<>(requestJson, httpHeaders, HttpMethod.POST,
				new URI(targetFullUrl));

		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		String responseBody = responseEntity.getBody();

		if (responseBody != null) {
			JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class,
					outputDtoClass);
			responseTelegram = objectMapper.readValue(responseBody, javaType);
		}

		IfTelegramHeader responseTelegramHeader = responseTelegram.getHeader();

		String prcsRsltDvsnCode = responseTelegramHeader.getPrcsRsltDvsnCode();

		if (!IfConstant.IfPrcsRsltDvsnCode.NORMAL.getValue().equals(prcsRsltDvsnCode)) {
			int cnt = responseTelegramHeader.getMsgeListCont();
			List<IfTelegramHeaderResponseMessage> msgeList = responseTelegramHeader.getMsgeList();

			if (cnt > 0 && msgeList != null && msgeList.size() > 0) {
				throw new IfException(msgeList.get(0).getMsgeCntn());
			}
		}

		return responseTelegram;
	}
	
	public String formatIpAddress(String ipAddress) {
		String formattedIpAddrString = "";
		
		String[] ipAddressTokens = ipAddress.split("\\.");
		for (String ipAddressToken : ipAddressTokens) {
			Integer ipTokenDigit = Integer.valueOf(ipAddressToken);
			String formattedToken = String.format("%03d", ipTokenDigit);
			formattedIpAddrString += formattedToken;
		}
		
		return formattedIpAddrString;
	}

	public String getTlgrCretDttm() {
		String pattern = "yyyyMMddHHmmssSSS";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		return simpleDateFormat.format(date);
	}

	public String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						return inetAddress.getHostAddress();
					}

				}
			}
		} catch (SocketException e) {
			log.error(e.getMessage());
		}

		return IfConstant.DEFAULT_IP_ADDRESS;
	}

	public String getRandomNumber() {
		return getRandomNumber(4);
	}

	private String getRandomNumber(int digits) {
		if (digits < 1) {
			throw new IllegalArgumentException("digits must be a positive number.");
		}

		int exclusiveUpperBound = (int) Math.pow(10, digits);
		SecureRandom secureRandom = new SecureRandom();
		int nextInt = secureRandom.nextInt(exclusiveUpperBound);

		String formattedRandomNumber = String.format("%04d", nextInt);

		return formattedRandomNumber;
	}
	
}