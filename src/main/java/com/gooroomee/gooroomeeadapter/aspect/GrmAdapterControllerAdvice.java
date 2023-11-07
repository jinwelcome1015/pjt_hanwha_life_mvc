package com.gooroomee.gooroomeeadapter.aspect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GrmAdapterControllerAdvice {
	
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseDto<String> globalExceptionHandler(Exception exception) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> exceptionMap = new HashMap<>();
		
		String message = exception.getMessage();
		String stackTraceJson;
		try {
			stackTraceJson = objectMapper.writeValueAsString(exception.getStackTrace());
			exceptionMap.put("message", message);
			exceptionMap.put("stackTraceJson", stackTraceJson);
		} catch (JsonProcessingException jsonProcessingException) {
			exceptionMap.put("message", message);
		}
		
		log.error("[EXCEPTION] : {}", exceptionMap);
		
		return new ResponseDto<String>(Result.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, message);

	}
}
