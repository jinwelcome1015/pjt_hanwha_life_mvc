package com.gooroomee.gooroomeeadapter.interceptor;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InterfaceClientHttpRequestInterceptorForLogging implements ClientHttpRequestInterceptor {
	
	public static void main(String[] args) {
		System.out.println(loggerForBase64DataLogging);
	}

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

		String requestBody = new String(body, StandardCharsets.UTF_8);

		Map<String, Object> requestBodyMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {
		});

		@SuppressWarnings("unchecked")
		Map<String, Object> requestBodyHeaderMap = (Map<String, Object>) requestBodyMap.get("header");
		
		String rcveSrvcId = null;
		if((requestBodyHeaderMap != null) && (requestBodyHeaderMap.get("rcveSrvcId") != null)) {
			rcveSrvcId = (String) requestBodyHeaderMap.get("rcveSrvcId");
		}
		
		String reqLog = new StringBuilder().append("[REQUEST]").append(" ").append("Uri : ").append(request.getURI()).append(", ").append("Method : ")
				.append(request.getMethod()).append(", ").append("Request Body : ").append(new String(body, StandardCharsets.UTF_8)).toString();
		
		if (IfConstant.IfSpec.IfMcCs001.getRcveSrvcId().equals(rcveSrvcId)) {
			loggerForBase64DataLogging.info(reqLog);
		} else {
			log.info(reqLog);
		}

	}

	private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {

		String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);

		Map<String, Object> responseBodyMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
		});

		@SuppressWarnings("unchecked")
		Map<String, Object> responseBodyHeaderMap = (Map<String, Object>) responseBodyMap.get("header");

		
		String rcveSrvcId = null;
		if((responseBodyHeaderMap != null) && (responseBodyHeaderMap.get("rcveSrvcId") != null)) {
			rcveSrvcId = (String) responseBodyHeaderMap.get("rcveSrvcId");
		}

		String resLog = new StringBuilder().append("[RESPONSE]").append(" ").append("Uri : ").append(uri).append(", ").append("Status code : ")
				.append(response.getStatusCode()).append(", ").append("Response Body : ").append(responseBody).toString();

		if (IfConstant.IfSpec.IfMcCs001.getRcveSrvcId().equals(rcveSrvcId)) {
			loggerForBase64DataLogging.info(resLog);
		} else {
			log.info(resLog);
		}
	}
}
