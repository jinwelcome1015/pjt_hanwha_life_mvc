package com.gooroomee.gooroomeeadapter.interceptor.logging.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiServerLoggingInterceptor implements HandlerInterceptor {
	
	@Autowired
	GrmAdapterController grmAdapterController; 

	@Autowired
	ObjectMapper objectMapper;
	
	@Value(value = "${api.ocr.logging.enabled:false}")
	private boolean apiOcrLoggingEnabled;
	
	private static final Logger LOGGER_FOR_BASE64_DATA_LOGGING = LoggerFactory
			.getLogger(ApiServerLoggingInterceptor.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_BASE64); // "com.gooroomee.gooroomeeadapter.interceptor.logging.server.ApiServerLoggingInterceptor._BASE64"

	
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		try {
			
			String requestUri = request.getRequestURI();
			
			String controllerPath = request.getRequestURI().replaceAll(String.format("^%s", request.getContextPath()), "");
			String controllerName = grmAdapterController.findControllerName(controllerPath);
			
			if (exception != null) {
				request.setAttribute(GrmAdapterController.EXCEPTION_ATTRIBUTE_NAME, exception);
				request.getRequestDispatcher(GrmAdapterController.EXCEPTION_CONTROLLER_PATH).forward(request, response);
			}
			
			final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
			ObjectNode requestObjectNode = (ObjectNode) objectMapper.readTree(cachingRequest.getContentAsByteArray());
			
			
			
			if (grmAdapterController.isRequestThatHasBase64Data(requestUri)) {
				if(apiOcrLoggingEnabled) {
					String base64Data = requestObjectNode.get("data").asText();
					LOGGER_FOR_BASE64_DATA_LOGGING.info("[GRM-CLIENT] [BASE64] : {}", base64Data);
				}
				
				requestObjectNode.put("data", "");
			} 
			log.info("[GRM-CLIENT] [{}({})] [Request Body] : {}", controllerPath, controllerName, requestObjectNode);
			
			
			final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
			ObjectNode responseObjectNode = (ObjectNode) objectMapper.readTree(cachingResponse.getContentAsByteArray());
			
			if (grmAdapterController.isRequestThatHasBase64Data(requestUri)) {
//			LOGGER_FOR_BASE64_DATA_LOGGING.info("[GRM-CLIENT] [Response Body] : {}", responseObjectNode);
			} 
//		log.info("[GRM-CLIENT] [{}] [Response Body] : {}", request.getRequestURI(), responseObjectNode);
			log.info("[GRM-CLIENT] [{}({})] [Response Body] : {}", controllerPath, controllerName, responseObjectNode);
			
		}catch (Exception e) {
			log.error("[LOGGING EXCEPTION]", e);
		}

		HandlerInterceptor.super.afterCompletion(request, response, handler, exception);
	}

	
	
	
}
