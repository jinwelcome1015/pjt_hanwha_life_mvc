package com.gooroomee.backbone.external.interceptor.logging.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gooroomee.backbone.external.constant.IfConstant;
import com.gooroomee.backbone.external.controller.GrmExternalBackboneController;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 이 어플리케이션이 서버가 되어 클라이언트에 요청에 대한 응답을 할때, 
 * 요청데이터와 응답데이터를 기록하기 위한 logging용 인터셉터 클래스 
 * </pre>
 * @author 신용진
 */
@Component
@Slf4j
public class ApiServerLoggingInterceptor implements HandlerInterceptor {
	
	/** GrmExternalBackboneController 객체 */
	@Autowired
	GrmExternalBackboneController grmExternalBackboneController; 

	/** ObjectMapper 객체 */
	@Autowired
	ObjectMapper objectMapper;
	
	/** OCR서비스에 전달할 Base64데이터를 기록할지 여부 */
	@Value(value = "${api.ocr.logging.enabled:false}")
	private boolean apiOcrLoggingEnabled;
	
	/** Base64 데이터 logging을 위한 전용 Logger 객체 */
	private static final Logger LOGGER_FOR_BASE64_DATA_LOGGING = LoggerFactory
			.getLogger(ApiServerLoggingInterceptor.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_BASE64); // "com.gooroomee.backbone.external.interceptor.logging.server.ApiServerLoggingInterceptor._BASE64"
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		if (exception != null) {
			request.setAttribute(GrmExternalBackboneController.EXCEPTION_ATTRIBUTE_NAME, exception);
			request.getRequestDispatcher(GrmExternalBackboneController.EXCEPTION_CONTROLLER_PATH).forward(request, response);
		}
		
		try {
			
			String requestUri = request.getRequestURI();
			String controllerPath = request.getRequestURI().replaceAll(String.format("^%s", request.getContextPath()), "");
			String controllerName = grmExternalBackboneController.findControllerName(controllerPath);
			
			this.logRequest(request, requestUri, controllerPath, controllerName);
			
			this.logResponse(response, requestUri, controllerPath, controllerName);
			
		}catch (Exception e) {
			log.error("[LOGGING EXCEPTION]", e);
		}

		HandlerInterceptor.super.afterCompletion(request, response, handler, exception);
	}
	
	
	/**
	 * 이 어플리케이션에 대한 요청 데이터를 기록한다.
	 * @param request HttpServletRequest 객체
	 * @param requestUri 요청 URI 문자열
	 * @param controllerPath 요청 URI 에서 컨텍스트패스를 제거한 순수 컨트롤러 매핑 문자열  (@RequestMapping 의 path 멤버 값)
	 * @param controllerName 컨트롤러 이름 (@RequestMapping 의 name 멤버 값)
	 * @throws IOException 
	 */
	private void logRequest(HttpServletRequest request, String requestUri, String controllerPath, String controllerName) throws IOException {
		if(request instanceof StandardMultipartHttpServletRequest) {
			log.info("[GRM-CLIENT-WITH-MULTIPART] [{}({})] [Request Body ContentLength] : {}", controllerPath, controllerName, request.getContentLength());
		}else {
			final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
			ObjectNode requestObjectNode = (ObjectNode) objectMapper.readTree(cachingRequest.getContentAsByteArray());
			
			if (grmExternalBackboneController.isRequestThatHasBase64Data(requestUri)) {
				if(apiOcrLoggingEnabled) {
					String base64Data = requestObjectNode.get("data").asText();
					LOGGER_FOR_BASE64_DATA_LOGGING.info("[GRM-CLIENT] [BASE64] : {}", base64Data);
				}
				requestObjectNode.put("data", "");
			} 
			
			String requestBodyString = null;
			
			if(requestObjectNode.get("lognPswd") == null) {
				requestBodyString = requestObjectNode.toString();
			} else {
				log.info("lognPswd 가 로그에 찍히지 않도록 \"\"로 대체합니다.");
				requestObjectNode.put("lognPswd", "");
				requestBodyString = requestObjectNode.toString();
			}
//			log.info("[GRM-CLIENT] [{}({})] [Request Body] : {}", controllerPath, controllerName, requestObjectNode);
			log.info("[GRM-CLIENT] [{}({})] [Request Body] : {}", controllerPath, controllerName, requestBodyString);
		}
	}

	
	/**
	 * 이 어플리케이션에 대한 응답 데이터를 기록한다.
	 * @param response HttpServletResponse 객체
	 * @param requestUri 요청 URI 문자열
	 * @param controllerPath 요청 URI 에서 컨텍스트패스를 제거한 순수 컨트롤러 매핑 문자열  (@RequestMapping 의 path 멤버 값)
	 * @param controllerName 컨트롤러 이름 (@RequestMapping 의 name 멤버 값)
	 * @throws IOException
	 */
	private void logResponse(HttpServletResponse response, String requestUri, String controllerPath, String controllerName) throws IOException {

		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
		ObjectNode responseObjectNode = (ObjectNode) objectMapper.readTree(cachingResponse.getContentAsByteArray());

		if (grmExternalBackboneController.isRequestThatHasBase64Data(requestUri)) {
//		LOGGER_FOR_BASE64_DATA_LOGGING.info("[GRM-CLIENT] [Response Body] : {}", responseObjectNode);
		}
		log.info("[GRM-CLIENT] [{}({})] [Response Body] : {}", controllerPath, controllerName, responseObjectNode);

	}
	
	
}
