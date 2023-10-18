package com.gooroomee.gooroomeeadapter.aspect;

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
public class GrmControllerAdvice {
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseDto<String> globalExceptionHandler(Exception e) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String stackTraceJson;
		try {
			stackTraceJson = objectMapper.writeValueAsString(e.getStackTrace());
		} catch (JsonProcessingException e1) {
			stackTraceJson = e.getMessage();
		}
		log.error(stackTraceJson);
		
		return new ResponseDto<String>(Result.FAIL, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

	}
}
