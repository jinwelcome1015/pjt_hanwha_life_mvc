package com.gooroomee.backbone.external.aspect;

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

import com.gooroomee.backbone.external.dto.client.Mvc019ResDto;
import com.gooroomee.backbone.external.dto.client.common.ResponseDto;
import com.gooroomee.backbone.external.dto.client.common.ResponseDto.Result;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs019_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs019_O.DataBody;
import com.gooroomee.backbone.external.util.MockUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * AOP Advisor 클래스
 * @author 신용진
 */
@Aspect
@Component
@Slf4j
public class GrmExternalBackboneAdvisor {
	
	/** ModelMapper 객체 */
	@Autowired
	ModelMapper modelmapper;
	
	/** 인터페이스 응답 DTO 클래스의 PREFIX */
	private static final String INTERFACE_OUTPUT_DTO_PREFIX = "com.gooroomee.backbone.external.dto.intrf.IfMcCs";
	
	/** 인터페이스 응답 DTO 클래스의 SUFFIX */
	private static final String INTERFACE_OUTPUT_DTO_SUFFIX = "_O";

	
	/**
	 * 요청 URI 가 MockUtil.URL_SUFFIX_FOR_MOCK 의 값으로 끝날 경우, 모조 데이터로 응답하게 하기 위한 Advisor
	 * @param proceedingJoinPoint Advisor의 타겟 메서드
	 * @return 모조 또는 실제 응답 데이터
	 * @throws Throwable
	 */
	@Around("execution(* com.gooroomee.backbone.external.controller.*.*(..))")
	public Object responseWithMockOrRealData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();

		Object[] args = proceedingJoinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {

			if (args[i] instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) args[i];
				String requestURI = request.getRequestURI();
				
				if (requestURI.endsWith(MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK)) {
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
				}else if(Pattern.matches(".*" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK + "/\\d", requestURI)) {
					String subCasePath = requestURI.replaceAll(".*" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK + "/", "");
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
	
	/**
	 * 컨트롤러 메서드 이름과 sub case 이름을 인자로 받아, 모조 응답데이터를 생성하기 위한 IoMetaInfoDto 객체를 반환한다. 
	 * @param method 모조 응답 데이터로 응답할 Controller의 Method 객체
	 * @param mockOutputDtoClass 모조 응답 데이터룰 담기 위한 DTO클래스
	 * @param subCasePath 경우에 따라 모조 응답 데이터를 다르게 만들기 위한 sub case 
	 * @return IoMetaInfoDto 객체
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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
	
	
	/**
	 * <pre>
	 * IoMetaInfoDto
	 * 인터페이스 응답 객체와 API 클라이언트 응답 클래스객체를 담는다.
	 * </pre>
	 * @author 신용진
	 */
	@Getter
	@Setter
	@ToString
	private static class IoMetaInfoDto{
		/** 인터페이스 응답 객체 */
		private Object interfaceOutputDto;
		
		/** API 클라이언트 응답 클래스객체 */
		private Class<?> resDtoClass;
	}

}
