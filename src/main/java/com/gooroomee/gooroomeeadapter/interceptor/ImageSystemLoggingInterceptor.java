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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ImageSystemLoggingInterceptor implements ClientHttpRequestInterceptor {
	

	private static final Logger loggerForMultipartFormDataLogging = LoggerFactory.getLogger(ImageSystemLoggingInterceptor.class.getCanonicalName() + IfConstant.LOGGER_NAME_SUFFIX_FOR_MULTIPART_FORM_DATA); // "com.gooroomee.gooroomeeadapter.interceptor.ImageSystemLoggingInterceptor._MULTIPART_FORM_DATA"

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
		log.info("[MULTIPART] [REQUEST] Request Uri : {}", request.getURI());
		loggerForMultipartFormDataLogging.info("[IMG-SYS] [REQUEST] Request Uri : {}, Request Body Bytes Lengths : {}", request.getURI(), requestBody.getBytes().length);
	}

	private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {
		String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
		ObjectNode responseBodyObjectNode = (ObjectNode) objectMapper.readTree(responseBody);
		log.info("[IMG-SYS] [RESPONSE] Status code : {}, Response Body : {}", response.getStatusCode(), responseBodyObjectNode);
	}
}
