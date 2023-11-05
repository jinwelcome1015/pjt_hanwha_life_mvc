package com.gooroomee.gooroomeeadapter.interceptor;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientHttpRequestInterceptorForLogging implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		this.traceRequest(request, body);

		ClientHttpResponse response = execution.execute(request, body);

		URI uri = request.getURI();
		this.traceResponse(response, uri);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) {
		String reqLog = new StringBuilder()
				.append("[REQUEST]")
				.append(" ")
				.append("Uri : ").append(request.getURI())
				.append(", ")
				.append("Method : ").append(request.getMethod())
				.append(", ")
				.append("Request Body : ").append(new String(body, StandardCharsets.UTF_8))
				.toString();
		log.info(reqLog);
	}

	private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {
		String resLog = new StringBuilder()
				.append("[RESPONSE]")
				.append(" ")
				.append("Uri : ").append(uri)
				.append(", ")
				.append("Status code : ").append(response.getStatusCode())
				.append(", ")
				.append("Response Body : ").append(StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8))
				.toString();
		log.info(resLog);
	}
}
