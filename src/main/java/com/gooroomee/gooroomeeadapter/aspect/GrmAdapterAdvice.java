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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	ModelMapper modelmapper;
	
	
	@Around("execution(* com.gooroomee.gooroomeeadapter.controller.*.*(..))")
	public Object responseWithMockData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I
		final String interfaceOutputDtoPrefix = "com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs";
		final String interfaceOutputDtoSuffix = "_O";
		
		Class<? extends Object> targetClass = proceedingJoinPoint.getTarget().getClass();
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		
		Object[] args = proceedingJoinPoint.getArgs();
		Parameter[] parameters = method.getParameters();
		
		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			Object arg = args[i];
			if(parameter.isAnnotationPresent(RequestBody.class)) {
				
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> map = objectMapper.convertValue(arg, new TypeReference<Map<String, Object>>() {
				});
				String useMockResponseYn = (String) map.get("useMockResponseYn");
				if("Y".equalsIgnoreCase(useMockResponseYn)) {
					
					Type genericReturnType = method.getGenericReturnType();
					String typeName = genericReturnType.getTypeName();								// "com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto<com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto>"
					
					String dtoQualifiedName = typeName;
					
					String patternFrom = ".*\\<";
					String patternTo = "\\>.*";
					dtoQualifiedName = dtoQualifiedName.replaceAll(patternFrom, "");				// "com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto>"
					dtoQualifiedName = dtoQualifiedName.replaceAll(patternTo, "");					// "com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto"
					
					Class<?> resDtoClass = Class.forName(dtoQualifiedName);

					String responseDtoClassSimpleName = resDtoClass.getSimpleName();				// "Mvc003ResDto"
					String dtoNumber = responseDtoClassSimpleName.replaceAll("\\D", "");			// "003"
					
					String interfaceOutputDtoQualifiedName = interfaceOutputDtoPrefix + dtoNumber + interfaceOutputDtoSuffix;
					Class<?> interfaceOutputDtoClass = Class.forName(interfaceOutputDtoQualifiedName);
					
					Object interfaceOutputDto = MockUtil.getMockResponseData(method.getName(), interfaceOutputDtoClass);
					
					Object resDto = modelmapper.map(interfaceOutputDto, resDtoClass);
					
					ResponseDto<Object> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
					
					return responseDto;
				}
			}
		}
		
		Object proceed = proceedingJoinPoint.proceed();
	    return proceed;
	    
	}
	
}
