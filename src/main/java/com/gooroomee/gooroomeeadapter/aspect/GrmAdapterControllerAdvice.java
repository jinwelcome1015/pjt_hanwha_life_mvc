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
import com.gooroomee.gooroomeeadapter.exception.IfException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GrmAdapterControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseDto<String> globalExceptionHandler(Exception exception) {
		
		
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(exception instanceof IfException) {
			IfException ifException = (IfException) exception;
			httpStatus = ifException.getHttpStatus();
		}
		
		if(httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
			log.error("[EXCEPTION]", exception);
		} else {
			if(exception instanceof IfException) {
				log.info("[EXCEPTION] : {}", exception.getMessage());
			}else {
				log.info("[EXCEPTION]", exception);
			}
		}
		
		return new ResponseDto<String>(Result.FAIL, httpStatus, exception.getMessage());

	}
}
