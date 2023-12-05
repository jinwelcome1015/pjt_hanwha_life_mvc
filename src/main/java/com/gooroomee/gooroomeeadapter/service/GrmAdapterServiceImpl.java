package com.gooroomee.gooroomeeadapter.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.dto.ifprovider.provider.OtpDto_I;
import com.gooroomee.gooroomeeadapter.dto.ifprovider.provider.common.IfProviderResponseCommonDto;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs999_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs999_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.util.IfUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrmAdapterServiceImpl implements GrmAdapterService {

	@Autowired
	@Qualifier(value = "restTemplateForInterface")
	private RestTemplate restTemplateForInterface;
	
	@Autowired
	@Qualifier(value = "restTemplateForCommon")
	private RestTemplate restTemplateForCommon;
	
	@Autowired
	private ObjectMapper objectMapper;

	/** 인터페이스 엔드포인트 URL */
	@Value(value = "${spring.profiles.active}")
	private String activeProfile;

	/** 인터페이스 엔드포인트 URL */
	@Value(value = "${interface.endpoint.url}")
	private String ifEndpointUrl;

	/** 인터페이스 엔드포인트 IP */
	@Value(value = "${interface.endpoint.ip}")
	private String ifEndpointIp;

	/** 인터페이스 엔드포인트 PORT */
	@Value(value = "${interface.endpoint.port}")
	private String ifEndpointPort;

	/** OCR URL */
	@Value(value = "${interface.ocr.url}")
	private String ocrUrl;

	/** OCR SECRET KEY */
	@Value(value = "${interface.ocr.secret-key}")
	private String ocrSecretKey;

	/** 암호화 AES_KEY */
	@Value(value = "${interface.encrypt.aes-key}")
	private String encryptAesKey;

	/** 암호화 AES_IV */
	@Value(value = "${interface.encrypt.aes-iv}")
	private String encryptAesIv;
	
	/** MVC시스템입장URI를 발행해주는 서비스의 URI */
	@Value(value = "${mvc.entry-uri-issue-service.uri}")
	private String uriOfMvcEntryUriIssueService;
	
	/** MVC시스템입장URI를 발행해주는 서비스의 API 키 이름 */
	@Value(value = "${mvc.entry-uri-issue-service.api-key-header-name}")
	private String uriOfMvcEntryUriIssueServiceApiKeyHeaderName;
	
	/** MVC시스템입장URI를 발행해주는 서비스의 API 키 값 */
	@Value(value = "${mvc.entry-uri-issue-service.api-key-header-value}")
	private String uriOfMvcEntryUriIssueServiceApiKeyHeaderValue;
	
	/** 이미지 시스템 서비스 SCHEME */
	@Value(value = "${edms.server.service_scheme}")
	private String edmsServerServiceScheme;
	
	/** 이미지 시스템 서비스 URI */
	@Value(value = "${edms.server.service_uri}")
	private String edmsServerServiceUri;
	
	/** 이미지 시스템 서비스 DOMAIN */
	@Value(value = "${edms.server.internal.domain}")
	private String edmsServerServiceDomain;
	
	/** 이미지 시스템 서비스 PORT */
	@Value(value = "${edms.server.internal.port}")
	private String edmsServerServicePort;
	

	public <I, O> O ifmccsCommon(String emnb, IfSpec ifSpec, I ifInputDto, Class<O> ifOutputDtoClass) throws JsonProcessingException, URISyntaxException {

		IfUtil ifUtil = new IfUtil(restTemplateForInterface, emnb, activeProfile, ifEndpointUrl);

		IfTelegramHeader inputTelegramHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

		IfTelegram<O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputTelegramHeader, ifInputDto, ifOutputDtoClass);

		O ifOutputDto = outputTelegram.getPayload();

		return ifOutputDto;
	}
	

	@Override
	public IfProviderResponseCommonDto<String> counsellingOtp(OtpDto_I dto_I) throws JsonMappingException, JsonProcessingException, URISyntaxException {
		IfProviderResponseCommonDto<String> responseCommonDto = null;

		String requestJson = objectMapper.writeValueAsString(dto_I);

		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON));
//		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		httpHeaders.set("Content-type", "application/json;charset=UTF-8");
		
		httpHeaders.set(uriOfMvcEntryUriIssueServiceApiKeyHeaderName, uriOfMvcEntryUriIssueServiceApiKeyHeaderValue);

		String targetFullUrl = uriOfMvcEntryUriIssueService;

		RequestEntity<String> requestEntity = new RequestEntity<>(requestJson, httpHeaders, HttpMethod.POST, new URI(targetFullUrl));

		ResponseEntity<String> responseEntity = restTemplateForCommon.exchange(requestEntity, String.class);
		String responseBody = responseEntity.getBody();

		if (responseBody != null) {
			JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfProviderResponseCommonDto.class, String.class);
			responseCommonDto = objectMapper.readValue(responseBody, javaType);
		}

		return responseCommonDto;
	}
	
	@Override
	public IfMcCs999_O edmsRgstr(IfMcCs999_I edmsInput) throws URISyntaxException, IOException {
		
		String edmsServiceUri = edmsServerServiceScheme + "://" + edmsServerServiceDomain + ":" + edmsServerServicePort + edmsServerServiceUri;
		
		MultipartFile multipartFile = edmsInput.getFile();
		ByteArrayResource resource = new ByteArrayResource(multipartFile.getBytes()) {
			@Override
			public String getFilename() {
				return multipartFile.getOriginalFilename();
			};
		};
		
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		Map<String, Object> map = objectMapper.convertValue(edmsInput, new TypeReference<Map<String, Object>>() {});
		multiValueMap.setAll(map);
		multiValueMap.set("file", resource);
		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		RequestEntity<MultiValueMap<String, Object>> requestEntity = new RequestEntity<>(multiValueMap, httpHeaders, HttpMethod.POST, new URI(edmsServiceUri));
		ResponseEntity<IfMcCs999_O> responseEntity = restTemplateForCommon.exchange(requestEntity, IfMcCs999_O.class);
		IfMcCs999_O edmsOutput = responseEntity.getBody();
		/*
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		RequestEntity<IfMcCs999_I> requestEntity = new RequestEntity<>(edmsInput, httpHeaders, HttpMethod.POST, new URI(edmsServiceUri));
		
		ResponseEntity<IfMcCs999_O> responseEntity = restTemplateForCommon.exchange(requestEntity, IfMcCs999_O.class);
		
		IfMcCs999_O edmsOutput = responseEntity.getBody();
		*/
		return edmsOutput;
	}

}
