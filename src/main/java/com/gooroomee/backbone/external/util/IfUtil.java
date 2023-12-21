package com.gooroomee.backbone.external.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.backbone.external.constant.IfConstant;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegram;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegramHeader;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegramHeaderResponseMessage;
import com.gooroomee.backbone.external.exception.IfException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * 인터페이스 utility 클래스
 * @author 신용진
 */
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

	/** ObjectMapper 객체 */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	/**
	 * IfUtil 생성자
	 * @param restTemplate RestTemplate 객체
	 * @param enmb 사원번호
	 * @param activeProfile 활성 프로필(PROD, QA, DEV, LOCAL)
	 * @param targetBaseUrl 전송대상(MCI/ESB/FEB) URL 정보
	 */
	public IfUtil(RestTemplate restTemplate, String enmb, String activeProfile, String targetBaseUrl) {
		super();
		this.restTemplate = restTemplate;
		this.enmb = enmb;
		this.activeProfile = activeProfile;
		this.targetBaseUrl = targetBaseUrl;
	}

	
	/**
	 * IfTelegramHeader(인터페이스 헤더) 객체를 생성해서 반환한다.
	 * @param itfcId 인터페이스ID
	 * @param rcveSrvcId 수신서비스ID
	 * @param rcveSysCode 수신시스템코드
	 * @return IfTelegramHeader(인터페이스 헤더) 객체
	 */
	public IfTelegramHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode) {
		return createHeader(itfcId, rcveSrvcId, rcveSysCode, "N");
	}

	/**
	 * IfTelegramHeader(인터페이스 헤더) 객체를 생성해서 반환한다.
	 * @param itfcId 인터페이스ID
	 * @param rcveSrvcId 수신서비스ID
	 * @param rcveSysCode 수신시스템코드
	 * @param prsnInfoIncsYn 수신시스템코드(Y/N)
	 * @return IfTelegramHeader(인터페이스 헤더) 객체
	 */
	public IfTelegramHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode, String prsnInfoIncsYn) {
		IfTelegramHeader header = new IfTelegramHeader();
		header.setItfcId(itfcId);
		header.setRcveSrvcId(rcveSrvcId);
		header.setChnlTypeCode(CHNL_TYPE_CODE);
		header.setTrnmSysCode(TRNM_SYS_CODE);
		header.setRcveSysCode(rcveSysCode);
		header.setEmnb(this.getEnmb());
		header.setBelnOrgnCode(BELN_ORGN_CODE);
		header.setPrsnInfoIncsYn(prsnInfoIncsYn);
		header.setIpAddr(NetworkUtil.formatIpAddress(NetworkUtil.getLocalIpAddress()));
		header.setTlgrCretDttm(CommonUtil.getNowYyyyMMddHHmmssSSS());
		header.setRndmNo(CommonUtil.getRandomNumber());
		header.setServerType(this.getServerType(this.getActiveProfile()));
		header.setRspnDvsnCode(IfConstant.IfRspnDvsnCode.SEND.getValue());
		return header;
	}

	
	/**
	 * 활성 프로필을 통해 인터페이스 헤더에 셋팅할 서버타입을 얻어낸다.
	 * @param profile 활성 프로필
	 * @return 인터페이스 헤더에 셋팅할 서버타입
	 */
	private String getServerType(String profile) {
		String serverType = "";

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

	
	
	/**
	 * 인터페이스 타입별로 인터페이스 타겟 엔드포인트 URI 를 반환한다.
	 * @param ifType 인터페이스 타입
	 * @return 인터페이스 타겟 엔드포인트 URI
	 */
	private String getTargetFullUri(IfConstant.IfType ifType) {

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

	
	
	/**
	 * 인터페이스를 통해 요청전문, 응답전문을 주고 받는다.
	 * @param <I> 요청전문타입
	 * @param <O> 응답전문타입
	 * @param ifType 인터페이스타입
	 * @param header 인터페이스 요청전문  header
	 * @param inputDto 인터페이스 요청전문 payload
	 * @param outputDtoClass 인터페이스 응답전문 클래스객체
	 * @return 인터페이스 응답전문
	 * @throws JsonProcessingException
	 * @throws URISyntaxException
	 */
	public <I, O> IfTelegram<O> exchangeTelegram(IfConstant.IfType ifType, IfTelegramHeader header, I inputDto, Class<O> outputDtoClass)
			throws JsonProcessingException, URISyntaxException {

		IfTelegram<I> requestTelegram = new IfTelegram<I>();
		requestTelegram.setHeader(header);
		requestTelegram.setPayload(inputDto);

		IfTelegram<O> responseTelegram = null;

		String requestJson = OBJECT_MAPPER.writeValueAsString(requestTelegram);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		httpHeaders.set("Content-type", "application/json;charset=UTF-8");

//		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON));
//		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		String targetFullUrl = this.getTargetFullUri(ifType);

		RequestEntity<String> requestEntity = new RequestEntity<>(requestJson, httpHeaders, HttpMethod.POST, new URI(targetFullUrl));

		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		String responseBody = responseEntity.getBody();

		if (responseBody != null) {
			JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, outputDtoClass);
			responseTelegram = OBJECT_MAPPER.readValue(responseBody, javaType);
		}

		IfTelegramHeader responseTelegramHeader = responseTelegram.getHeader();

		String prcsRsltDvsnCode = responseTelegramHeader.getPrcsRsltDvsnCode();

		if (!IfConstant.IfPrcsRsltDvsnCode.NORMAL.getValue().equals(prcsRsltDvsnCode)) {
//			int cnt = responseTelegramHeader.getMsgeListCont();
			List<IfTelegramHeaderResponseMessage> msgeList = responseTelegramHeader.getMsgeList();
			String msgeStackTrace = StringUtils.defaultString(responseTelegramHeader.getMsgeStackTrace());
			
//			if (cnt > 0 && msgeList != null && msgeList.size() > 0) {
			if ((msgeList != null && msgeList.size() > 0) || (!"".equals(msgeStackTrace))) {
				IfTelegramHeaderResponseMessage firstIfTelegramHeaderResponseMessage = msgeList.get(0);
				String msgeCode = firstIfTelegramHeaderResponseMessage.getMsgeCode();
				String msgeCntn = firstIfTelegramHeaderResponseMessage.getMsgeCntn();
				
				/*
				Map<String, String> mapForError = new LinkedHashMap<>();
				mapForError.put("msgeCode", msgeCode);
				mapForError.put("msgeCntn", msgeCntn);
				mapForError.put("msgeStackTrace", msgeStackTrace);
				
				String errorJson = OBJECT_MAPPER.writeValueAsString(mapForError);
				throw new IfException(HttpStatus.OK, errorJson);
				*/
				
				String errorMsge = "";
				if("".equals(msgeStackTrace)) {
					errorMsge = msgeCntn;
				}else {
					errorMsge = msgeStackTrace;
				}
				
				throw new IfException(HttpStatus.OK, errorMsge);
			}
		}

		return responseTelegram;
	}

}
