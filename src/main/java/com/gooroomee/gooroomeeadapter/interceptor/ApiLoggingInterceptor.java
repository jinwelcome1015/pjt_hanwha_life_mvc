package com.gooroomee.gooroomeeadapter.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiLoggingInterceptor implements HandlerInterceptor {
	
	@Autowired
	ObjectMapper objectMapper;

	private static final Logger loggerForBase64DataLogging = LoggerFactory
			.getLogger(ApiLoggingInterceptor.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_BASE64); // "com.gooroomee.gooroomeeadapter.interceptor.ApiLoggingInterceptor._BASE64"

	private static final String TOKEN_OF_URL_WITH_BASE64_REQUEST_PARAM_1 = "entry";
	
	private static final String TOKEN_OF_URL_WITH_BASE64_REQUEST_PARAM_2 = "idcdOcrRqst";
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		
		if (exception != null) {
			request.setAttribute(GrmAdapterController.EXCEPTION_ATTRIBUTE_NAME, exception);
			request.getRequestDispatcher(GrmAdapterController.EXCEPTION_CONTROLLER_PATH).forward(request, response);
		}
		
		final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
		JsonNode requestJsonNode = objectMapper.readTree(cachingRequest.getContentAsByteArray());
		
		if (request.getRequestURI().contains(TOKEN_OF_URL_WITH_BASE64_REQUEST_PARAM_1) || request.getRequestURI().contains(TOKEN_OF_URL_WITH_BASE64_REQUEST_PARAM_2)) {
			loggerForBase64DataLogging.info("[{}] [Request Body] : {}", request.getRequestURI(), requestJsonNode);
		} else {
			log.info("[{}] [Request Body] : {}", request.getRequestURI(), requestJsonNode);
		}
		

		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
		JsonNode responseJsonNode = objectMapper.readTree(cachingResponse.getContentAsByteArray());

		if (request.getRequestURI().contains(TOKEN_OF_URL_WITH_BASE64_REQUEST_PARAM_1) || request.getRequestURI().contains(TOKEN_OF_URL_WITH_BASE64_REQUEST_PARAM_2)) {
			loggerForBase64DataLogging.info("[{}] [Response Body] : {}", request.getRequestURI(), responseJsonNode);
		} else {
			log.info("[{}] [Response Body] : {}", request.getRequestURI(), responseJsonNode);
		}

		HandlerInterceptor.super.afterCompletion(request, response, handler, exception);
	}

}
