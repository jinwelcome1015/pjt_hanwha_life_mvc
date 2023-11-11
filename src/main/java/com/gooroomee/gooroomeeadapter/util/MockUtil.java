package com.gooroomee.gooroomeeadapter.util;

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
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;

public class MockUtil {

	public static final String URL_SUFFIX_FOR_MOCK = "/mock";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).registerModule(new SimpleModule());

	public static <O> O getMockRequestData(String thisMethodName, Class<O> requestDtoClass) throws IOException {
		String mockResponseDataFileName = "req.json";
		return getMockData(thisMethodName, requestDtoClass, mockResponseDataFileName);
	}

	public static <O> O getMockResponseData(String thisMethodName, Class<O> responseDtoClass) throws IOException {
		String mockResponseDataFileName = "res.json";
		return getMockData(thisMethodName, responseDtoClass, mockResponseDataFileName);
	}

	public static <O> O getMockData(String thisMethodName, Class<O> outputClass, String mockDataFileName)
			throws IOException {
		String mockDataRootPath = "/assets/mockData/";
		String mockDataDetailPath = mockDataRootPath + thisMethodName;

		File mockResponseDataFile = new File(mockDataDetailPath, mockDataFileName);

		ClassPathResource resource = new ClassPathResource(mockResponseDataFile.toString());

		/*
		Path path = Paths.get(resource.getURI());
		List<String> lineList = Files.readAllLines(path);
		*/
		Stream<String> lineStream = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"))
				.lines();
		List<String> lineList = lineStream.collect(Collectors.toList());

		String delimiter = " ";
		String jsonData = String.join(delimiter, lineList);

		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, Map.class);
		IfTelegram<Map> responseTelegram = null;
		responseTelegram = OBJECT_MAPPER.readValue(jsonData, javaType);
		Map<String, Object> payload = responseTelegram.getPayload();

		O o = OBJECT_MAPPER.convertValue(payload, outputClass);

		return o;

	}
}
