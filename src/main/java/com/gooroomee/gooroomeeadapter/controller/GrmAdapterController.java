package com.gooroomee.gooroomeeadapter.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc002ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc002ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc005ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc005ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc006ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc006ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc007ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc007ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc008ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc008ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs007_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs007_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs008_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs008_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.service.GrmAdapterService;
import com.gooroomee.gooroomeeadapter.util.MockUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GrmAdapterController {
	
	/** api auth enabled */
	@Value(value = "#{propertiesFactoryBean['api.auth.enabled']}")
	private String apiAuthEnabled;

	/** api auth token */
	@Value(value = "#{propertiesFactoryBean['api.auth.key']}")
	private String apiAuthKey;

	@Autowired
	private GrmAdapterService gooroomeeAdapterService;
	
	@Autowired
	private ModelMapper modelMapper;

	public static final String API_URL_TOKEN = "/intrf";
	
	private static final String URL_FOR_REQUEST_MOCK_DATA = "/test/api/mockData/req";
	
	private static final String PARAM_NAME_FOR_REQUEST_MOCK_DATA = "apiPath";

	/**
	 * [02]
	 * 진위확인결과조회
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/trflCnfm") }, method = { RequestMethod.POST }, name = "진위확인결과조회")
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto)
			throws URISyntaxException, IOException {

		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if ("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {
			}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs002_O mockResponseData = MockUtil.getMockResponseData(thisMethodName, IfMcCs002_O.class);

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
	 * [03]
	 * 신분증스캔후처리
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcIdcdScan") }, method = { RequestMethod.POST }, name = "신분증스캔후처리")
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto)
			throws URISyntaxException, IOException {

		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if ("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {
			}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs003_O mockResponseData = MockUtil.getMockResponseData(thisMethodName, IfMcCs003_O.class);

			Mvc003ResDto resDto = modelMapper.map(mockResponseData, Mvc003ResDto.class);
			ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}

		IfMcCs003_I cs003_I = modelMapper.map(reqDto, IfMcCs003_I.class);
		cs003_I.setPushRcvrEmnb(reqDto.getEmnb());

		String emnb = reqDto.getEmnb();

		IfMcCs003_O cs003_O = gooroomeeAdapterService.ifmccs003(emnb, cs003_I);

		Mvc003ResDto resDto = modelMapper.map(cs003_O, Mvc003ResDto.class);

		ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * [05]
	 * SSO대체로그인인증
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcUserCtfn") }, method = { RequestMethod.POST }, name = "SSO대체로그인인증")
	public @ResponseBody ResponseDto<Mvc005ResDto> itfcUserCtfn(@RequestBody Mvc005ReqDto reqDto)
			throws URISyntaxException, IOException {

		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if ("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {
			}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs005_O mockResponseData = MockUtil.getMockResponseData(thisMethodName, IfMcCs005_O.class);

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
	 * [06]
	 * 사원목록조회
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/empeInqy") }, method = { RequestMethod.POST }, name = "사원목록조회")
	public @ResponseBody ResponseDto<Mvc006ResDto> empeInqy(@RequestBody Mvc006ReqDto reqDto)
			throws URISyntaxException, IOException {

		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if ("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {
			}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs006_O mockResponseData = MockUtil.getMockResponseData(thisMethodName, IfMcCs006_O.class);

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
	 * [07]
	 * 고객계약정보조회
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCustInqyMgmt") }, method = { RequestMethod.POST }, name = "고객계약정보조회")
	public @ResponseBody ResponseDto<Mvc007ResDto> intgCustInqyMgmt(@RequestBody Mvc007ReqDto reqDto)
			throws URISyntaxException, IOException {

		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if ("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {
			}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs007_O mockResponseData = MockUtil.getMockResponseData(thisMethodName, IfMcCs007_O.class);

			Mvc007ResDto resDto = modelMapper.map(mockResponseData, Mvc007ResDto.class);
			ResponseDto<Mvc007ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}
		
		IfMcCs007_I cs007_I = modelMapper.map(reqDto, IfMcCs007_I.class);
		cs007_I.setCntcDvsnCode(IfConstant.CNTC_DVSN_CODE);
		cs007_I.setCustDvsnCode(IfConstant.CUST_DVSN_CODE);

		String emnb = reqDto.getEmnb();

		IfMcCs007_O cs007_O = gooroomeeAdapterService.ifmccs007(emnb, cs007_I);

		Mvc007ResDto resDto = modelMapper.map(cs007_O, Mvc007ResDto.class);

		ResponseDto<Mvc007ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	
	/**
	 * [08]
	 * 고객계좌목록조회
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCust") }, method = { RequestMethod.POST }, name = "고객계좌목록조회")
	public @ResponseBody ResponseDto<Mvc008ResDto> intgCust(@RequestBody Mvc008ReqDto reqDto)
			throws URISyntaxException, IOException {

		String useMockResponseYn = reqDto.getUseMockResponseYn();
		if ("Y".equalsIgnoreCase(useMockResponseYn)) {
			Method thisMethod = new Object() {
			}.getClass().getEnclosingMethod();
			String thisMethodName = thisMethod.getName();
			IfMcCs008_O mockResponseData = MockUtil.getMockResponseData(thisMethodName, IfMcCs008_O.class);

			Mvc008ResDto resDto = modelMapper.map(mockResponseData, Mvc008ResDto.class);
			ResponseDto<Mvc008ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
			return responseDto;
		}
		
		IfMcCs008_I cs008_I = modelMapper.map(reqDto, IfMcCs008_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs008_O cs008_O = gooroomeeAdapterService.ifmccs008(emnb, cs008_I);

		Mvc008ResDto resDto = modelMapper.map(cs008_O, Mvc008ResDto.class);

		ResponseDto<Mvc008ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	

	/**
	 * API 테스트 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = { "/test/apis" }, method = { RequestMethod.GET })
	public String getApiTestView(Model model) {

		model.addAttribute("apiAuthEnabled", apiAuthEnabled);
		model.addAttribute("apiAuthKey", apiAuthKey);
		model.addAttribute("apiInfoList", this.getApiInfoList());
		
		model.addAttribute("urlForRequestMockData", URL_FOR_REQUEST_MOCK_DATA);
		model.addAttribute("paramNameForRequestMockData", PARAM_NAME_FOR_REQUEST_MOCK_DATA);

		return "/test/apis";
	}
	
	
	@RequestMapping(path = { URL_FOR_REQUEST_MOCK_DATA }, method = { RequestMethod.GET })
	public @ResponseBody String getApiTestRequestMockData(@RequestParam MultiValueMap<String, String> map) throws ClassNotFoundException, IOException {
		String mockData = null;

		String firstParamValue = map.getFirst(PARAM_NAME_FOR_REQUEST_MOCK_DATA);
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if(requestMapping != null) {
				String[] paths = requestMapping.path();
				for (String path : paths) {
					if (path.equalsIgnoreCase(firstParamValue)) {
						String methodName = method.getName();
						
						Parameter[] parameters = method.getParameters();
						for (Parameter parameter : parameters) {
							RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
							if(requestBody != null) {
								ObjectMapper objectMapper = new ObjectMapper();

								Class<?> parameterType = parameter.getType();
								
								Object mockRequestDataObject = MockUtil.getMockRequestData(methodName, parameterType);
								Map<String, Object> mockRequestDataMap = objectMapper.convertValue(mockRequestDataObject, new TypeReference<Map<String, Object>>() {});
								mockRequestDataMap.put("emnb", "1077123");
								mockRequestDataMap.put("useMockResponseYn", "Y");
								
								mockData = objectMapper.writeValueAsString(mockRequestDataMap);
							}
						}
					}
				}
			}
		}
		return mockData;
	}

	private List<Map<String, String>> getApiInfoList() {
		List<Map<String, String>> apiInfoList = new ArrayList<>();

		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			RequestMapping requestMappingAnnotation = method.getAnnotation(RequestMapping.class);
			if (requestMappingAnnotation != null) {
				String name = requestMappingAnnotation.name();
				String[] paths = requestMappingAnnotation.path();
				for (String path : paths) {
					if (path.startsWith(API_URL_TOKEN)) {
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

	/*
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
	*/

	
	/*
	public static void main(String[] args) {
		Method[] declaredMethods = GrmAdapterController.class.getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			if("itfcIdcdScan".equals(declaredMethod.getName())) {
				Parameter[] parameters = declaredMethod.getParameters();
				for (Parameter parameter : parameters) {
					RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
					System.out.println(parameter.getType());
					System.out.println(requestBody == null);
				}
			}
		}
	}
	*/
	
	/*
	public static void main(String[] args) throws ClassNotFoundException {
		GrmAdapterController grmAdapterController = new GrmAdapterController();
		Class<? extends GrmAdapterController> class1 = grmAdapterController.getClass();
		Method[] declaredMethods = class1.getDeclaredMethods();
		for (Method method : declaredMethods) {
	//			System.out.println("====================== " + method.getName());
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if(requestMapping != null) {
				if("trflCnfm".equals(method.getName())) {
	//					System.out.println("method.getName() : " + method.getName());
	//					System.out.println("method.getReturnType() : " + method.getReturnType());
					System.out.println("method.getGenericReturnType() : " + method.getGenericReturnType());
					System.out.println("method.getGenericReturnType().getTypeName() : " + method.getGenericReturnType().getTypeName());
					Parameter[] parameters = method.getParameters();
					for (Parameter parameter : parameters) {
						RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
						if(requestBody != null) {
							System.out.println("%%%%%%%%%%%%%%% " + parameter.getType().getName());
						}
					}
					
					String typeName = method.getGenericReturnType().getTypeName();
					String s = typeName;
					
					String from = "^(.)+ResponseDto\\<";
					String to = ">$";		
					
					s = s.replaceAll(from, "");
					s = s.replaceAll(to, "");
					
					System.out.println("sss : " + s);
					
					Class<?> forName = Class.forName(s);
					String simpleName = forName.getSimpleName();
					System.out.println(simpleName);
				}
				
			}
		}
	}
	*/
	
	
	/*
	public static void main(String[] args) throws ClassNotFoundException {
		GrmAdapterController grmAdapterController = new GrmAdapterController();
		Class<? extends GrmAdapterController> class1 = grmAdapterController.getClass();
		Method[] declaredMethods = class1.getDeclaredMethods();
		for (Method method : declaredMethods) {
	//			System.out.println("====================== " + method.getName());
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if(requestMapping != null) {
				if("trflCnfm".equals(method.getName())) {
	//					System.out.println("method.getName() : " + method.getName());
	//					System.out.println("method.getReturnType() : " + method.getReturnType());
					System.out.println("method.getGenericReturnType() : " + method.getGenericReturnType());
					System.out.println("method.getGenericReturnType().getTypeName() : " + method.getGenericReturnType().getTypeName());
					
					
					String typeName = method.getGenericReturnType().getTypeName();
					String s = typeName;
					
					String from = "^(.)+ResponseDto\\<";
					String to = ">$";		
					
					s = s.replaceAll(from, "");
					s = s.replaceAll(to, "");
					
					System.out.println("sss : " + s);
					
					Class<?> forName = Class.forName(s);
					String simpleName = forName.getSimpleName();
					System.out.println(simpleName);
				}
				
			}
		}
	}
	*/
	/*
	public static void main(String[] args) throws IOException {
		GrmAdapterController grmAdapterController = new GrmAdapterController();
		Mvc003ReqDto mockRequestData = grmAdapterController.getMockRequestData("itfcIdcdScan", Mvc003ReqDto.class);
		System.out.println(mockRequestData);
	}
	*/
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
