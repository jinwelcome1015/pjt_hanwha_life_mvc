package com.gooroomee.gooroomeeadapter.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc002ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc002ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc005ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc005ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc006ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc006ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataBody;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataHeader;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.service.GrmAdapterService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GrmAdapterController {

	@Autowired
	private GrmAdapterService gooroomeeAdapterService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String API_URL_TOKEN = "/intrf/";

	/**
	 * 진위확인결과조회
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@PostMapping(path = { "/intrf/trflCnfm" }, name = "진위확인결과조회")
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto)
			throws URISyntaxException, IOException {
		
		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs002_O mockResponseData = getMockResponseData(thisMethodName, IfMcCs002_O.class);
			
			Mvc002ResDto resDto = modelMapper.map(mockResponseData, Mvc002ResDto.class);
			ResponseDto<Mvc002ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}

		IfMcCs002_I cs002_I = modelMapper.map(reqDto, IfMcCs002_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs002_O cs002_O = gooroomeeAdapterService.ifmccs002(emnb, cs002_I);

		Mvc002ResDto resDto = modelMapper.map(cs002_O, Mvc002ResDto.class);

		ResponseDto<Mvc002ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 신분증스캔후처리
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@PostMapping(path = { "/intrf/itfcIdcdScan" }, name = "신분증스캔후처리")
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto)
			throws URISyntaxException, IOException {
		
		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs003_O mockResponseData = getMockResponseData(thisMethodName, IfMcCs003_O.class);
			
			Mvc003ResDto resDto = modelMapper.map(mockResponseData, Mvc003ResDto.class);
			ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}

		IfMcCs003_I cs003_I = modelMapper.map(reqDto, IfMcCs003_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs003_O cs003_O = gooroomeeAdapterService.ifmccs003(emnb, cs003_I);

		Mvc003ResDto resDto = modelMapper.map(cs003_O, Mvc003ResDto.class);

		ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	

	/**
	 * SSO대체로그인인증
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@PostMapping(path = { "/intrf/itfcUserCtfn" }, name = "SSO대체로그인인증")
	public @ResponseBody ResponseDto<Mvc005ResDto> itfcUserCtfn(@RequestBody Mvc005ReqDto reqDto)
			throws URISyntaxException, IOException {
		
		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs005_O mockResponseData = getMockResponseData(thisMethodName, IfMcCs005_O.class);
			
			Mvc005ResDto resDto = modelMapper.map(mockResponseData, Mvc005ResDto.class);
			ResponseDto<Mvc005ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}
		
		

		IfMcCs005_I cs005_I = modelMapper.map(reqDto, IfMcCs005_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs005_O cs005_O = gooroomeeAdapterService.ifmccs005(emnb, cs005_I);

		Mvc005ResDto resDto = modelMapper.map(cs005_O, Mvc005ResDto.class);

		ResponseDto<Mvc005ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 사원목록조회
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@PostMapping(path = { "/intrf/empeInqy" }, name = "사원목록조회")
	public @ResponseBody ResponseDto<Mvc006ResDto> empeInqy(@RequestBody Mvc006ReqDto reqDto)
			throws URISyntaxException, IOException {
		
		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs006_O mockResponseData = getMockResponseData(thisMethodName, IfMcCs006_O.class);
			
			Mvc006ResDto resDto = modelMapper.map(mockResponseData, Mvc006ResDto.class);
			ResponseDto<Mvc006ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}
		

		IfMcCs006_I cs006_I = modelMapper.map(reqDto, IfMcCs006_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs006_O cs006_O = gooroomeeAdapterService.ifmccs006(emnb, cs006_I);

		Mvc006ResDto resDto = modelMapper.map(cs006_O, Mvc006ResDto.class);

		ResponseDto<Mvc006ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	
	
	/**
	 * API 테스트 화면
	 * @param model
	 * @return
	 */
	@GetMapping(path = { "/test/api" })
	public String getApiTestView(Model model) {
		
		model.addAttribute("apiInfoList", this.getApiInfoList());

		return "/test/apiTest";
	}
	
	
	private List<Map<String, String>> getApiInfoList(){
		List<Map<String, String>> apiInfoList = new ArrayList<>();
		
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			PostMapping postMappingAnnotation = method.getAnnotation(PostMapping.class);
			if(postMappingAnnotation != null) {
				String name = postMappingAnnotation.name();
				String[] paths = postMappingAnnotation.path();
				List<String> pathList = Arrays.asList(paths);
				for (String path : pathList) {
					if(path.startsWith(API_URL_TOKEN)) {
						Map<String, String> map = new HashMap<>();
						map.put("path", path);
						map.put("name", name);
						apiInfoList.add(map);
					}
				}
			}
		}
		return apiInfoList;
	}
	
	@GetMapping(path = { "/test/apis" })
	public @ResponseBody String getApis() {
		return "";
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
		DataHeader dataHeader = new IfMcCs001_I.DataHeader();
		DataBody dataBody = new IfMcCs001_I.DataBody();
		IfMcCs001_I ifMcCs001_I = new IfMcCs001_I();
		ifMcCs001_I.setDataBody(dataBody);
		ifMcCs001_I.setDataHeader(dataHeader);
		
		String writeValueAsString = objectMapper.writeValueAsString(ifMcCs001_I);
		System.out.println(writeValueAsString);
	}
	
	
	
	private <O> O getMockResponseData(String thisMethodName, Class<O> outputClass) throws IOException {
		String mockDataRootPath = "/assets/mockData/";
		String mockDataDetailPath = mockDataRootPath + thisMethodName;
		String mockResponseDataFileName = "res.log";
		
		File mockResponseDataFile = new File(mockDataDetailPath, mockResponseDataFileName);
		
		ClassPathResource resource = new ClassPathResource(mockResponseDataFile.toString());
		Path path = Paths.get(resource.getURI());
		
		List<String> lines = Files.readAllLines(path);
		
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
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, outputClass);
		IfTelegram<O> responseTelegram = null;
		responseTelegram = objectMapper.readValue(jsonData, javaType);
		
		
		O payload = responseTelegram.getPayload();
		
		return payload;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@PostMapping(path = "/test/doTest02")
	public @ResponseBody String doTest02() throws IOException {
		
		UriComponents complexUrl = UriComponentsBuilder
		        .fromUriString("https://jsonplaceholder.typicode.com/posts")
		        .encode()
		        .build(); 
	
		ResponseEntity<List<Map<String, String>>> exchange = restTemplate.exchange(complexUrl.toUri(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Map<String, String>>>() {
				}
		);
		
		String writeValueAsString = new ObjectMapper().writeValueAsString(exchange);
		return writeValueAsString;
	}
	*/
	
	/*
	public static void main(String[] args) throws IOException {
		ClassPathResource resource = new ClassPathResource("mockData/res.log");
		Path path = Paths.get(resource.getURI());
	
		List<String> lines = Files.readAllLines(path);
	
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
	
		System.out.println(jsonData);
	
		ObjectMapper objectMapper = new ObjectMapper();
	
		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, IfMcCs005_O.class);
		IfTelegram<IfMcCs005_O> responseTelegram = null;
		responseTelegram = objectMapper.readValue(jsonData, javaType);
	
		System.out.println(responseTelegram);
	}
	*/
}
