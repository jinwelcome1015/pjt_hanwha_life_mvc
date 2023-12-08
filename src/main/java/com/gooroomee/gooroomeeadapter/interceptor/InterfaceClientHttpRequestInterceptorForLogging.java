package com.gooroomee.gooroomeeadapter.interceptor;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InterfaceClientHttpRequestInterceptorForLogging implements ClientHttpRequestInterceptor {
	
	@Value(value = "${api.ocr.logging.enabled:false}")
	private boolean apiOcrLoggingEnabled;

	private static final Logger loggerForBase64DataLogging = LoggerFactory
			.getLogger(InterfaceClientHttpRequestInterceptorForLogging.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_BASE64); // "com.gooroomee.gooroomeeadapter.interceptor.ClientHttpRequestInterceptorForLogging._BASE64"

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

		this.traceRequest(request, body);

		ClientHttpResponse response = execution.execute(request, body);

		URI uri = request.getURI();
		this.traceResponse(response, uri);
		return response;
	}
	


	private void traceRequest(HttpRequest request, byte[] body) throws JsonMappingException, JsonProcessingException {

		String requestBody = new String(body, StandardCharsets.UTF_8);	// {"header":{"trnnNo":null,"trnmSysCode":"MVC","ipAddr":"010230246137","tlgrCretDttm":"20231130093601853","rndmNo":"5329","hsno":0,"ctfnTokn":null,"ogtsTrnnNo":null,"prsnInfoIncsYn":"N","itfcId":"HLIMVC00016","rcveSrvcId":"hcsIdcdOcrRqst","rcveSysCode":"HCS","mciNodeNo":null,"mciSesnId":null,"serverType":"Q","rspnDvsnCode":"S","extlDvsnCode":null,"emnb":"1077123","belnOrgnCode":"00630","custId":null,"chnlTypeCode":"SVR","scrnId":null,"befoScrnId":null,"userTmunIdnfVal":null,"rqsrIp":null,"rqstDttm":null,"baseCrny":null,"baseCnty":null,"baseLang":null,"tlgrRspnDttm":null,"prcsRsltDvsnCode":null,"totalCount":0,"lastPageYn":null,"msgeListCont":0,"msgeList":null,"msgeStackTrace":null},"payload":{"dataHeader":{"SRVC_ID":"SVC028","SCRN_ID":"MVC_SCRN_TEST_01","X_OCR_SECRET":"ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=","CRTF_RTCD":null,"DLRE_MSG":null},"dataBody":{"images":[{"format":"png","data":"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...

		ObjectNode requestBodyObjectNode = (ObjectNode) objectMapper.readTree(requestBody);
		
		String rcveSrvcId = null;
		IfSpec ifSpec = null;
		
		try {
			rcveSrvcId = requestBodyObjectNode.get("header").get("rcveSrvcId").asText();
			ifSpec = this.findIfSpec(rcveSrvcId);
		}catch (NullPointerException e) {
			log.error("payload.header.rcveSrvcId 가 없습니다.");
		}
		
		if (IfConstant.IfSpec.IfMcCs001.getRcveSrvcId().equals(rcveSrvcId)) {
			
			ObjectNode firstImageObjectNode = (ObjectNode) requestBodyObjectNode.get("payload").get("dataBody").get("images").get(0);
			
			if(apiOcrLoggingEnabled) {
				String base64Data = firstImageObjectNode.get("data").asText();
				loggerForBase64DataLogging.info("[INTERFACE] [BASE64] : {}", base64Data);
			}
			
			firstImageObjectNode.put("data", "");
		}
		
		log.info("[INTERFACE] [REQUEST] : {}({}) - {}", ifSpec.getRcveSrvcKorNm(), ifSpec.getRcveSrvcId(), requestBodyObjectNode);

		
		/*
		Map<String, Object> requestBodyMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
		
		@SuppressWarnings("unchecked")
		Map<String, Object> requestBodyHeaderMap = (Map<String, Object>) requestBodyMap.get("header");
		
		String rcveSrvcId = null;
		if(requestBodyHeaderMap != null && requestBodyHeaderMap.get("rcveSrvcId") != null) {
			rcveSrvcId = (String) requestBodyHeaderMap.get("rcveSrvcId");
		}
		String reqLog = new StringBuilder().append("[REQUEST]").append(" ").append("Uri : ").append(request.getURI()).append(", ").append("Method : ")
				.append(request.getMethod()).append(", ").append("Request Body : ").append(new String(body, StandardCharsets.UTF_8)).toString();
		
		if (IfConstant.IfSpec.IfMcCs001.getRcveSrvcId().equals(rcveSrvcId)) {
			loggerForBase64DataLogging.info(reqLog);
		} else {
			log.info(reqLog);
		}
		 */
	}


	private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {

		String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
		
		ObjectNode responseBodyObjectNode = (ObjectNode) objectMapper.readTree(responseBody);
		String rcveSrvcId = null;
		IfSpec ifSpec = null;
		
		try {
			rcveSrvcId = responseBodyObjectNode.get("header").get("rcveSrvcId").asText();
			ifSpec = this.findIfSpec(rcveSrvcId);
		}catch (NullPointerException e) {
			log.error("payload.header.rcveSrvcId 가 없습니다.");
		}
		
		if (IfConstant.IfSpec.IfMcCs001.getRcveSrvcId().equals(rcveSrvcId)) {
			
			ObjectNode dataBodyObjectNode = (ObjectNode) responseBodyObjectNode.get("payload").get("dataBody");
			
			if(apiOcrLoggingEnabled) {
				String ocrData = dataBodyObjectNode.get("images").asText();
				loggerForBase64DataLogging.info("[INTERFACE] [OCR] : {}", ocrData);
			}
			
			dataBodyObjectNode.put("images", "");
		}
		
//		log.info("[INTERFACE] [RESPONSE] Status code : {}, Response Body : {}", response.getStatusCode(), responseBodyObjectNode);
		
		log.info("[INTERFACE] [RESPONSE] : {}({}) - [Status code : {}], {}", ifSpec.getRcveSrvcKorNm(), ifSpec.getRcveSrvcId(), response.getStatusCode(), responseBodyObjectNode);
		
		/*
		Map<String, Object> responseBodyMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
		});
		
		@SuppressWarnings("unchecked")
		Map<String, Object> responseBodyHeaderMap = (Map<String, Object>) responseBodyMap.get("header");
		
		
		String rcveSrvcId = null;
		if(responseBodyHeaderMap != null && responseBodyHeaderMap.get("rcveSrvcId") != null) {
			rcveSrvcId = (String) responseBodyHeaderMap.get("rcveSrvcId");
		}
		
		String resLog = new StringBuilder().append("[RESPONSE]").append(" ").append("Uri : ").append(uri).append(", ").append("Status code : ")
				.append(response.getStatusCode()).append(", ").append("Response Body : ").append(responseBody).toString();
		
		if (IfConstant.IfSpec.IfMcCs001.getRcveSrvcId().equals(rcveSrvcId)) {
			loggerForBase64DataLogging.info(resLog);
		} else {
			log.info(resLog);
		}
		*/
	}
	
	
	private IfSpec findIfSpec(String rcveSrvcId) {
		
		IfSpec foundIfSpec = null;
		
		IfSpec[] ifspecs = IfConstant.IfSpec.values();
		
		for (IfSpec ifSpec : ifspecs) {
			if(ifSpec.getRcveSrvcId().equals(rcveSrvcId)) {
				foundIfSpec = ifSpec;
				break;
			}
		}
		
		return foundIfSpec;
	}
	
}
