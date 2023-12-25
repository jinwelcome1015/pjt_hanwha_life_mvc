package com.gooroomee.backbone.external.interceptor.logging.client.common;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 이 어플리케이션이 클라이언트가 되어 서버와 통신할때, 요청, 응답을 기록하기 위한 템플릿 클래스 
 * </pre>
 * @author 신용진
 */
@Slf4j
public abstract class ClientHttpRequestInterceptorForLogging implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		try {
			this.traceRequest(request, body);
		}catch (Exception e) {
			log.error("[LOGGING EXCEPTION]", e);
		}

		ClientHttpResponse response = execution.execute(request, body);

		URI uri = request.getURI();
		
		try {
			this.traceResponse(response, uri);
		}catch (Exception e) {
			log.error("[LOGGING EXCEPTION]", e);
		}
	
		return response;
	}
	
	/**
	 * 요청 내용을 기록한다.
	 * @param request HttpRequest 객체
	 * @param body HttpRequest 의 body 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	protected abstract void traceRequest(HttpRequest request, byte[] body) throws Exception;
	
	
	/**
	 * 응답 내용을 기록한다.
	 * @param response ClientHttpResponse 객체
	 * @param uri 요청 URI 문자열
	 * @throws IOException
	 */
	protected abstract void traceResponse(ClientHttpResponse response, URI uri) throws Exception;


}
