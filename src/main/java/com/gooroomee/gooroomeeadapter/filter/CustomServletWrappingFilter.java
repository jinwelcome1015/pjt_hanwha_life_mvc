package com.gooroomee.gooroomeeadapter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class CustomServletWrappingFilter implements Filter {
	
	@Autowired
	ObjectMapper objectMapper; 

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    	
    	ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper(httpServletResponse);

        chain.doFilter(cachingRequest, cachingResponse);
        
		/*
		String requestURI = cachingRequest.getRequestURI();
		int httpStatus = cachingResponse.getStatus();
		String reqContent = new String(cachingRequest.getContentAsByteArray());
		String resContent = new String(cachingResponse.getContentAsByteArray());
		*/
        
        cachingResponse.copyBodyToResponse();
        
		/*
		log.info("[{}] [Request Body] : {}", requestURI, reqContent);
		//        log.info("[{}] [Request Body] : {}", requestURI, objectMapper.readTree(reqContent));
		
		log.info("[{}] [RESPONSE STATUS] : {}, [RESPONSE BODY] : {}", requestURI, httpStatus, resContent);
		//        log.info("[{}] [RESPONSE STATUS] : {}, [RESPONSE BODY] : {}", requestURI, httpStatus, objectMapper.readTree(resContent));
		*/
    }
}
