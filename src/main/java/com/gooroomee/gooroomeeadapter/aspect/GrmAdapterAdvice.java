package com.gooroomee.gooroomeeadapter.aspect;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.gooroomee.gooroomeeadapter.dto.client.Mvc019ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs019_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs019_O.DataBody;
import com.gooroomee.gooroomeeadapter.util.MockUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class GrmAdapterAdvice {

	@Autowired
	ModelMapper modelmapper;
	
	private static final String INTERFACE_OUTPUT_DTO_PREFIX = "com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs";
	private static final String INTERFACE_OUTPUT_DTO_SUFFIX = "_O";

	@Around("execution(* com.gooroomee.gooroomeeadapter.controller.*.*(..))")
	public Object responseWithMockData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		

		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();

		Object[] args = proceedingJoinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {

			if (args[i] instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) args[i];
				String requestURI = request.getRequestURI();
				
				if (requestURI.endsWith(MockUtil.URL_SUFFIX_FOR_MOCK)) {
					/*
					Type genericReturnType = method.getGenericReturnType();
					String typeName = genericReturnType.getTypeName();
					
					String dtoQualifiedName = typeName;
					
					dtoQualifiedName = dtoQualifiedName.replaceAll("(.*\\<)|(\\>.*)", "");
					
					Class<?> resDtoClass = Class.forName(dtoQualifiedName);
					
					String responseDtoClassSimpleName = resDtoClass.getSimpleName();
					String dtoNumber = responseDtoClassSimpleName.replaceAll("\\D", "");
					
					String interfaceOutputDtoQualifiedName = INTERFACE_OUTPUT_DTO_PREFIX + dtoNumber + INTERFACE_OUTPUT_DTO_SUFFIX;
					Class<?> interfaceOutputDtoClass = Class.forName(interfaceOutputDtoQualifiedName);
					
					Object interfaceOutputDto = MockUtil.getMockResponseData(method.getName(), interfaceOutputDtoClass, null);
					*/
					String subCasePath = null;
					IoMetaInfoDto ioMetaInfoDto = this.getIoMetaInfoDto(method, null, subCasePath);
					Object interfaceOutputDto = ioMetaInfoDto.getInterfaceOutputDto();
					Class<?> resDtoClass = ioMetaInfoDto.getResDtoClass();
					Object resDto = modelmapper.map(interfaceOutputDto, resDtoClass);

					ResponseDto<Object> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

					return responseDto;
				}else if(Pattern.matches(".*" + MockUtil.URL_SUFFIX_FOR_MOCK + "/\\d", requestURI)) {
					String subCasePath = requestURI.replaceAll(".*" + MockUtil.URL_SUFFIX_FOR_MOCK + "/", "");
//					IoMetaInfoDto ioMetaInfoDto = this.getIoMetaInfoDto(method, subCasePath);
//					Object interfaceOutputDto = ioMetaInfoDto.getInterfaceOutputDto();
//					Class<?> resDtoClass = ioMetaInfoDto.getResDtoClass();
					
					Type genericReturnType = method.getGenericReturnType();
					String typeName = genericReturnType.getTypeName();

					String dtoQualifiedName = typeName;

					dtoQualifiedName = dtoQualifiedName.replaceAll("(.*\\<)|(\\>.*)", "");

					Class<?> resDtoClass = Class.forName(dtoQualifiedName);
					
					if(resDtoClass == Mvc019ResDto.class) {
						IoMetaInfoDto ioMetaInfoDto = this.getIoMetaInfoDto(method, Mvc019ResDto.class, subCasePath);
						Object interfaceOutputDto = ioMetaInfoDto.getInterfaceOutputDto();
//						Class<?> resDtoClass = ioMetaInfoDto.getResDtoClass();
						Object resDto = modelmapper.map(interfaceOutputDto, resDtoClass);

						ResponseDto<Object> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

						return responseDto;
						
					}
				}
			}
		}

		Object proceed = proceedingJoinPoint.proceed();
		return proceed;

	}
	
	private IoMetaInfoDto getIoMetaInfoDto(Method method, Class<?> mockOutputDtoClass, String subCasePath) throws ClassNotFoundException, IOException {
		Type genericReturnType = method.getGenericReturnType();
		String typeName = genericReturnType.getTypeName();

		String dtoQualifiedName = typeName;

		dtoQualifiedName = dtoQualifiedName.replaceAll("(.*\\<)|(\\>.*)", "");

		Class<?> resDtoClass = Class.forName(dtoQualifiedName);
		
		if(mockOutputDtoClass == null) {
			String responseDtoClassSimpleName = resDtoClass.getSimpleName();
			String dtoNumber = responseDtoClassSimpleName.replaceAll("\\D", "");
			String interfaceOutputDtoQualifiedName = INTERFACE_OUTPUT_DTO_PREFIX + dtoNumber + INTERFACE_OUTPUT_DTO_SUFFIX;
			mockOutputDtoClass = Class.forName(interfaceOutputDtoQualifiedName);
		}

		Object interfaceOutputDto = MockUtil.getMockResponseData(method.getName(), mockOutputDtoClass, subCasePath);
		
		IoMetaInfoDto ioMetaInfoDto = new IoMetaInfoDto();
		ioMetaInfoDto.setInterfaceOutputDto(interfaceOutputDto);
		ioMetaInfoDto.setResDtoClass(resDtoClass);
		
		return ioMetaInfoDto;
	}
	
	@Getter
	@Setter
	@ToString
	private static class IoMetaInfoDto{
		private Object interfaceOutputDto;
		private Class<?> resDtoClass;
	}

}
