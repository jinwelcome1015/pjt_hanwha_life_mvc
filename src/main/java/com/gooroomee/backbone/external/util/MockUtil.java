package com.gooroomee.backbone.external.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegram;

/**
 * 모조 요청 데이터, 응답 데이터 사용을 위한 utility 클래스 
 * @author 신용진
 */
public class MockUtil {

	/** 모조  데이터 사용 URI 의 suffix */
	public static final String REQUEST_URI_SUFFIX_FOR_MOCK = "/mock";

	/** ObjectMapper 객체 */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).registerModule(new SimpleModule());

	
	/**
	 * 모조 요청 데이터를 반환한다.
	 * @param <T> 요청 데이터 DTO 타입
	 * @param thisMethodName 모조 요청 데이터를 사용할 Controller 메소드 이름
	 * @param requestDtoClass 요청 데이터 DTO 클래스 객체
	 * @param subCasePath 경우에 따라 모조 응답 데이터를 다르게 만들기 위한 sub case 
	 * @return 모조 요청 데이터
	 * @throws IOException
	 */
	public static <T> T getMockRequestData(String thisMethodName, Class<T> requestDtoClass, String subCasePath) throws IOException {
		String mockResponseDataFileName = "req.json";
		return getMockData(thisMethodName, requestDtoClass, mockResponseDataFileName, subCasePath);
	}

	
	/**
	 * 모조 응답 데이터를 반환한다.
	 * @param <T> 응답 데이터 DTO 타입
	 * @param thisMethodName 모조 응답 데이터를 사용할 Controller 메소드 이름
	 * @param responseDtoClass 응답 데이터 DTO 클래스 객체
	 * @param subCasePath 경우에 따라 모조 응답 데이터를 다르게 만들기 위한 sub case 
	 * @return 모조 응답 데이터
	 * @throws IOException
	 */
	public static <T> T getMockResponseData(String thisMethodName, Class<T> responseDtoClass, String subCasePath) throws IOException {
		String mockResponseDataFileName = "res.json";
		return getMockData(thisMethodName, responseDtoClass, mockResponseDataFileName, subCasePath);
	}

	
	
	/**
	 * 모조 데이터(요청, 응답)를 반환한다.
	 * @param <T> 데이터(요청, 응답) DTO 타입
	 * @param thisMethodName 모조 데이터를 사용할 Controller 메소드 이름
	 * @param outputClass 데이터(요청, 응답) DTO 클래스 객체
	 * @param mockDataFileName 모조 데이터를 담고 있는 파일의 이름
	 * @param subCasePath 경우에 따라 모조 데이터를 다르게 만들기 위한 sub case 
	 * @return 모조 데이터
	 * @throws IOException
	 */
	public static <T> T getMockData(String thisMethodName, Class<T> outputClass, String mockDataFileName, String subCasePath) throws IOException {
		String mockDataRootPath = "/assets/mockData/";
		String mockDataDetailPath = mockDataRootPath + thisMethodName;
		if(subCasePath != null) {
			mockDataDetailPath += "/" + subCasePath;
		}

		File mockResponseDataFile = new File(mockDataDetailPath, mockDataFileName);

		ClassPathResource resource = new ClassPathResource(mockResponseDataFile.toString());

		/*
		Path path = Paths.get(resource.getURI());
		List<String> lineList = Files.readAllLines(path);
		*/
		
		Stream<String> lineStream = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8")).lines();
		List<String> lineList = lineStream.collect(Collectors.toList());

		String delimiter = " ";
		String jsonData = String.join(delimiter, lineList);

		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, Map.class);
		IfTelegram<Map> responseTelegram = null;
		responseTelegram = OBJECT_MAPPER.readValue(jsonData, javaType);
		Map<String, Object> payload = responseTelegram.getPayload();

		T t = OBJECT_MAPPER.convertValue(payload, outputClass);

		return t;

	}
}
