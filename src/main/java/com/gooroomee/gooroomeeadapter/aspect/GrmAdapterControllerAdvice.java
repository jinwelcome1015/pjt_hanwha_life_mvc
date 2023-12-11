package com.gooroomee.gooroomeeadapter.aspect;

import java.util.LinkedHashMap;
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
		/*
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> exceptionMap = new LinkedHashMap<String, Object>();
		
		String message = exception.getMessage();
		
		StackTraceElement[] stackTrace = exception.getStackTrace();
		exceptionMap.put("message", message);
		exceptionMap.put("stackTrace", stackTrace);
		
		String exceptionInfoString;
		
		try {
			exceptionInfoString = objectMapper.writeValueAsString(exceptionMap);
		} catch (JsonProcessingException jsonProcessingException) {
			log.error("[JsonProcessingException] : {}", jsonProcessingException.getMessage());
			exceptionInfoString = message;
		}
		
		log.error("[EXCEPTION] : {}", exceptionInfoString);
		*/
		
		log.error("[EXCEPTION]", exception);
		
		return new ResponseDto<String>(Result.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

	}
}
