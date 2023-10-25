package com.gooroomee.gooroomeeadapter.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.util.MockUtil;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class GrmAdapterAdvice {
	/*
	@Around("execution(* com.gooroomee.gooroomeeadapter.service.*.*(..))")
	public Object responseWithMockData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		Class<? extends Object> targetClass = proceedingJoinPoint.getTarget().getClass();
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		
		Object[] args = proceedingJoinPoint.getArgs();
		Parameter[] parameters = method.getParameters();
		
		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			Object arg = args[i];
			if(parameter.isAnnotationPresent(RequestBody.class)) {
				System.out.println("------");
				System.out.println(parameter.getType());
				System.out.println(arg);
				
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> map = objectMapper.convertValue(arg, new TypeReference<Map<String, Object>>() {
				});
				String useMockResponseYn = (String) map.get("useMockResponseYn");
				System.out.println("useMockResponseYn : " + useMockResponseYn);
				if("Y".equalsIgnoreCase(useMockResponseYn)) {
	//					Method thisMethod = new Object() {
	//					}.getClass().getEnclosingMethod();
	//					String thisMethodName = method.getName();
					
					Type genericReturnType = method.getGenericReturnType();
					genericReturnType.getClass().getTypeParameters();
					
					// XXX 로직 수정
					IfMcCs003_O mockResponseData = MockUtil.getMockResponseData(method.getName(), IfMcCs003_O.class);
	
	//					Mvc003ResDto resDto = modelMapper.map(mockResponseData, Mvc003ResDto.class);
					ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
					return responseDto;
				}
			}
		}
		
		
		
		Object proceed = proceedingJoinPoint.proceed();
	    return proceed;
	    
	}
	*/
}
