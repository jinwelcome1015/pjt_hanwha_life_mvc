package com.gooroomee.gooroomeeadapter.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
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

		final String interfaceOutputDtoPrefix = "com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs";
		final String interfaceOutputDtoSuffix = "_O";

		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();

		Object[] args = proceedingJoinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {

			if (args[i] instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) args[i];
				String requestURI = request.getRequestURI();

				if (requestURI.endsWith(MockUtil.URL_SUFFIX_FOR_MOCK)) {
					Type genericReturnType = method.getGenericReturnType();
					String typeName = genericReturnType.getTypeName();

					String dtoQualifiedName = typeName;

					dtoQualifiedName = dtoQualifiedName.replaceAll("(.*\\<)|(\\>.*)", "");

					Class<?> resDtoClass = Class.forName(dtoQualifiedName);

					String responseDtoClassSimpleName = resDtoClass.getSimpleName();
					String dtoNumber = responseDtoClassSimpleName.replaceAll("\\D", "");

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
