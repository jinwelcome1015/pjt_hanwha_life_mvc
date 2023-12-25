package com.gooroomee.backbone.external.interceptor.logging.client;

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
import com.gooroomee.backbone.external.constant.IfConstant;
import com.gooroomee.backbone.external.interceptor.logging.client.common.ClientHttpRequestInterceptorForLogging;

import lombok.extern.slf4j.Slf4j;


/**
 * <pre>
 * 이 어플리케이션이 이미지 시스템 서버에 이미지 등록 요청을 할때
 * 요청데이터와 응답데이터를 기록하기 위한 logging용 인터셉터 클래스 
 * </pre>
 * @author 신용진
 */
@Component
@Slf4j
public class ImageSystemLoggingInterceptor extends ClientHttpRequestInterceptorForLogging {
	
	/** Multi 데이터 logging을 위한 전용 Logger 객체 */
	private static final Logger loggerForMultipartFormDataLogging = LoggerFactory.getLogger(ImageSystemLoggingInterceptor.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_MULTIPART_FORM_DATA); // "com.gooroomee.backbone.external.interceptor.ImageSystemLoggingInterceptor._MULTIPART_FORM_DATA"

	/** ObjectMapper 객체 */
	private ObjectMapper objectMapper = new ObjectMapper();


	protected void traceRequest(HttpRequest request, byte[] body) throws JsonMappingException, JsonProcessingException {
		String requestBody = new String(body, StandardCharsets.UTF_8);
		log.info("[MULTIPART] [REQUEST] Request Uri : {}", request.getURI());
		loggerForMultipartFormDataLogging.info("[IMG-SYS] [REQUEST] Request Uri : {}, Request Body Bytes Lengths : {}", request.getURI(), requestBody.getBytes().length);
	}

	
	protected void traceResponse(ClientHttpResponse response, URI uri) throws IOException {
		String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
		ObjectNode responseBodyObjectNode = (ObjectNode) objectMapper.readTree(responseBody);
		log.info("[IMG-SYS] [RESPONSE] Status code : {}, Response Body : {}", response.getStatusCode(), responseBodyObjectNode);
	}
}
