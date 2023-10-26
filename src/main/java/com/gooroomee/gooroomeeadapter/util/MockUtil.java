package com.gooroomee.gooroomeeadapter.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;

public class MockUtil {
	
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
			.registerModule(new SimpleModule())
			;
	
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
		Path path = Paths.get(resource.getURI());

		List<String> lines = Files.readAllLines(path);
		/*
		List<String> filteredLines = new ArrayList<>();
		for (String line : lines) {
			if (line.contains("trnnNo") || line.contains("tscsRqstVal") || line.contains("postfixSysCode")
					|| line.contains("subTrnmSysType")) {
				continue;
			} else if (line.contains("msgeStackTrace") && line.endsWith(",")) {
				line = line.replaceAll(",$", "");
			}
			filteredLines.add(line);
		}
		
		String delimiter = " ";
		String jsonData = String.join(delimiter, filteredLines);
		
		String patternFrom = ".*(data=)";
		String patternTo = ", encrypt=false]\\s*$";
		jsonData = jsonData.replaceAll(patternFrom, "");
		jsonData = jsonData.replaceAll(patternTo, "");
		*/
		
		String delimiter = " ";
		String jsonData = String.join(delimiter, lines);
		
//		ObjectMapper objectMapper = new ObjectMapper();
		
		
		
		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, Map.class);
		IfTelegram<Map> responseTelegram = null;
		responseTelegram = OBJECT_MAPPER.readValue(jsonData, javaType);
		Map<String, Object> payload = responseTelegram.getPayload();
		
		O o = OBJECT_MAPPER.convertValue(payload, outputClass);
		
		return o;
		
		
		/*
		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, outputClass);
		IfTelegram<O> responseTelegram = null;
		responseTelegram = objectMapper.readValue(jsonData, javaType);
		
		O payload = responseTelegram.getPayload();
		
		return payload;
		*/		
	}
}
