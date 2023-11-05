package com.gooroomee.gooroomeeadapter.interceptor;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiLoggingInterceptor implements HandlerInterceptor {
	
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
		        log.info("[{}] [Request Body] : {}", request.getRequestURI(), objectMapper.readTree(cachingRequest.getContentAsByteArray()));
//		    }
//		}
		
		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
//        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
//            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
                log.info("[{}] [Response Body] : {}", request.getRequestURI(), objectMapper.readTree(cachingResponse.getContentAsByteArray()));
//            }
//        }
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, exception);
	}
	
}
