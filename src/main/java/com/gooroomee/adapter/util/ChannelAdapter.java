package com.gooroomee.adapter.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.adapter.constant.TeleConstant;
import com.gooroomee.adapter.dto.intrf.common.HlicpMessageHeader;
import com.gooroomee.adapter.dto.intrf.common.HlicpResponseMessage;
import com.gooroomee.adapter.dto.intrf.common.SimpleMessageEnvelop;
import com.gooroomee.adapter.exception.TeleException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelAdapter {

	/**
	 * 송신 시스템 코드
	 */
	private static final String TRNM_SYS_CODE = TeleConstant.TRNM_SYS_CODE;

	/**
	 * 채널 타입 코드
	 */
	private static final String CHNL_TYPE_CODE = TeleConstant.IfChnlTypeCode.SERVER.getValue();

	/**
	 * 소속 기관 코드
	 */
	private static final String BELN_ORGN_CODE = TeleConstant.BELN_ORGN_CODE;

	/**
	 * 사원 번호
	 */
	private String enmb;

	/**
	 * 활성 프로필(PROD, QA, DEV, LOCAL)
	 */
	private String activeProfile;

	/**
	 * 전송대상(MCI/ESB/FEB) URL 정보
	 */
	private String targetBaseUrl;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ChannelAdapter(String enmb, String activeProfile, String targetBaseUrl) {
		super();
		this.enmb = enmb;
		this.activeProfile = activeProfile;
		this.targetBaseUrl = targetBaseUrl;
	}

	public HlicpMessageHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode) {
		return createHeader(itfcId, rcveSrvcId, rcveSysCode, "N");
	}

	public HlicpMessageHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode,
			String prsnInfoIncsYn) {
		HlicpMessageHeader header = new HlicpMessageHeader();
		header.setItfcId(itfcId);
		header.setRcveSrvcId(rcveSrvcId);
		header.setChnlTypeCode(CHNL_TYPE_CODE);
		header.setTrnmSysCode(TRNM_SYS_CODE);
		header.setRcveSysCode(rcveSysCode);
		header.setEmnb(this.getEnmb());
		header.setBelnOrgnCode(BELN_ORGN_CODE);
		header.setPrsnInfoIncsYn(prsnInfoIncsYn);
		header.setIpAddr(this.getLocalIpAddress());
		header.setTlgrCretDttm(this.getTlgrCretDttm());
		header.setRndmNo(this.getRandomNumber());
		header.setServerType(this.getServerType());
		header.setRspnDvsnCode(TeleConstant.IfRspnDvsnCode.SEND.getValue());
		return header;
	}

	private String getServerType() {
		String serverType = "";

		String profile = this.getActiveProfile();

		if ("local".equalsIgnoreCase(profile)) {
			serverType = TeleConstant.IfServerType.LOCAL.getValue();
		} else if ("dev".equalsIgnoreCase(profile)) {
			serverType = TeleConstant.IfServerType.DEV.getValue();
		} else if ("qa".equalsIgnoreCase(profile)) {
			serverType = TeleConstant.IfServerType.QA.getValue();
		} else if ("prod".equalsIgnoreCase(profile)) {
			serverType = TeleConstant.IfServerType.PROD.getValue();
		} else {
			serverType = TeleConstant.IfServerType.ETC.getValue();
		}

		return serverType;
	}

	public String getTargetFullUrl(TeleConstant.IfType ifType) {

		String targetFullUrl = "";

		String targetBaseUrl = this.getTargetBaseUrl();

		if (ifType == TeleConstant.IfType.MCI) {
			targetFullUrl = targetBaseUrl + "/mci" + "/" + TRNM_SYS_CODE.toLowerCase();
		} else if (ifType == TeleConstant.IfType.ESB) {
			targetFullUrl = targetBaseUrl + "/esb";
		} else if (ifType == TeleConstant.IfType.FEB) {
			targetFullUrl = targetBaseUrl + "/feb";
		}

		return targetFullUrl;
	}

	public <I, O> SimpleMessageEnvelop<O> sendAndReceiveMessage(TeleConstant.IfType ifType, HlicpMessageHeader header,
			I inputDto, Class<O> outputDtoClass) throws JsonProcessingException, URISyntaxException {
		ObjectMapper objectMapper = new ObjectMapper();

		SimpleMessageEnvelop<I> requestEnvelop = new SimpleMessageEnvelop<I>();
		requestEnvelop.setHeader(header);
		requestEnvelop.setPayload(inputDto);

		SimpleMessageEnvelop<O> responseEnvelop = null;

		String requestJson = objectMapper.writeValueAsString(requestEnvelop);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON));

		String targetFullUrl = getTargetFullUrl(ifType);

		RequestEntity<String> requestEntity = new RequestEntity<>(requestJson, httpHeaders, HttpMethod.POST,
				new URI(targetFullUrl));

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		String responseBody = responseEntity.getBody();

		if (responseBody != null) {
			JavaType javaType = TypeFactory.defaultInstance().constructParametricType(SimpleMessageEnvelop.class,
					outputDtoClass);
			responseEnvelop = objectMapper.readValue(responseBody, javaType);
		}

		HlicpMessageHeader responseEnvelopHeader = responseEnvelop.getHeader();

		String prcsRsltDvsnCode = responseEnvelopHeader.getPrcsRsltDvsnCode();

		if (!TeleConstant.IfPrcsRsltDvsnCode.NORMAL.getValue().equals(prcsRsltDvsnCode)) {
			int cnt = responseEnvelopHeader.getMsgeListCont();
			List<HlicpResponseMessage> msgeList = responseEnvelopHeader.getMsgeList();

			if (cnt > 0 && msgeList != null && msgeList.size() > 0) {
				throw new TeleException(msgeList.get(0).getMsgeCntn());
			}
		}

		return responseEnvelop;
	}

	public String getTlgrCretDttm() {
		String pattern = "yyyyMMddHHmmss";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
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
			logger.error(e.getMessage());
		}

		return TeleConstant.DEFAULT_IP_ADDRESS;
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
	
	public static void main(String[] args) {
		
		System.out.println("1");
		ChannelAdapter channelAdapter = new ChannelAdapter("", "", "");
		System.out.println("2");
		
		
	}
}
