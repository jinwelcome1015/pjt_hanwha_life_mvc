package com.gooroomee.gooroomeeadapter.interceptor;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ApiLoggingInterceptor implements HandlerInterceptor {
	
	private static final Logger loggerForBase64DataLogging = LoggerFactory.getLogger(ApiLoggingInterceptor.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_BASE64);		// "com.gooroomee.gooroomeeadapter.interceptor.ApiLoggingInterceptor._BASE64"
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		
		if(exception != null) {
			request.setAttribute(GrmAdapterController.EXCEPTION_ATTRIBUTE_NAME, exception);
			request.getRequestDispatcher(GrmAdapterController.EXCEPTION_CONTROLLER_PATH).forward(request, response);
		}
		
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
//		if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
//		    if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
        		if(request.getRequestURI().contains("entry") || request.getRequestURI().contains("idcdOcrRqst")) {
        			loggerForBase64DataLogging.info("[{}] [Request Body] : {}", request.getRequestURI(), objectMapper.readTree(cachingRequest.getContentAsByteArray()));
        		}else {
        			log.info("[{}] [Request Body] : {}", request.getRequestURI(), objectMapper.readTree(cachingRequest.getContentAsByteArray()));
        		}
		        
//		    }
//		}
		
		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
//        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
//            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
				if(request.getRequestURI().contains("entry") || request.getRequestURI().contains("idcdOcrRqst")) {
					loggerForBase64DataLogging.info("[{}] [Response Body] : {}", request.getRequestURI(), objectMapper.readTree(cachingResponse.getContentAsByteArray()));
				}else {
					log.info("[{}] [Response Body] : {}", request.getRequestURI(), objectMapper.readTree(cachingResponse.getContentAsByteArray()));
				}
//            }
//        }
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, exception);
	}
	
}
