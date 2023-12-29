package com.gooroomee.backbone.external.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.backbone.external.constant.IfConstant;
import com.gooroomee.backbone.external.constant.IfConstant.EzCertSrvcId;
import com.gooroomee.backbone.external.constant.IfConstant.IfSpec;
import com.gooroomee.backbone.external.constant.IfConstant.OcrIdType;
import com.gooroomee.backbone.external.constant.IfConstant.TrflCnfmBswrDvsnCode;
import com.gooroomee.backbone.external.constant.IfConstant.TrflCnfmJobCode;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc000ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc001ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc001ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc002ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc002ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc003ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc003ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc004ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc004ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc005ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc005ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc006ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc006ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc007ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc007ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc008ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc008ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc009ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc009ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc010ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc010ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc011ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc011ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc012ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc012ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc013ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc013ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc014ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc014ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc015ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc015ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc016ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc016ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc017ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc017ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc018ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc018ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc019ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc019ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc020ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc020ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc023ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc023ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc999ReqDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc999ResDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.common.ResponseDto;
import com.gooroomee.backbone.external.dto.ifconsumer.client.common.ResponseDto.Result;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs001_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs001_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs002_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs002_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs003_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs003_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs004_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs004_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs005_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs005_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs007_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs007_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs008_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs008_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs009_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs009_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs010_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs010_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs011_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs011_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs012_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs012_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs013_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs013_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs014_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs014_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs015_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs015_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs016_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs016_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs017_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs017_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs018_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs018_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs019_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs019_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs023_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs023_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs999_I;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs999_O;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs001_I.DataBody.Image;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs004_O.User;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs007_O.CustCntcInfoInqyRslt;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs011_I.DataBody.Callback;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs015_I.NttkButnCntn.Button;
import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs023_O.DateInfo;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegram;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegramHeader;
import com.gooroomee.backbone.external.dto.ifconsumer.server.common.IfTelegramHeaderResponseMessage;
import com.gooroomee.backbone.external.dto.ifprovider.client.Req001Dto;
import com.gooroomee.backbone.external.dto.ifprovider.client.Res001Dto;
import com.gooroomee.backbone.external.dto.ifprovider.server.Grm001Dto_I;
import com.gooroomee.backbone.external.dto.ifprovider.server.common.IfProviderResponseCommonDto;
import com.gooroomee.backbone.external.exception.IfException;
import com.gooroomee.backbone.external.service.GrmExternalBackboneService;
import com.gooroomee.backbone.external.util.AesUtil;
import com.gooroomee.backbone.external.util.CommonUtil;
import com.gooroomee.backbone.external.util.MockUtil;

import korealife.uv.com.cm.SHA256CmCrypt;
import lombok.extern.slf4j.Slf4j;



/**
 * 컨트롤러 클래스
 * @author 신용진
 */
@Controller
@Slf4j
public class GrmExternalBackboneController {

	/** api auth enabled */
	@Value(value = "${api.auth.enabled}")
	private String apiAuthEnabled;

	/** api auth token */
	@Value(value = "${api.auth.key}")
	private String apiAuthKey;

	/** aes-key */
	@Value(value = "${interface.encrypt.aes-key}")
	private String aesKey;

	/** aes-iv */
	@Value(value = "${interface.encrypt.aes-iv}")
	private String aesIv;

	/** ocr secret-key */
	@Value(value = "${interface.ocr.secret-key}")
	private String ocrSecretKey;
		
	/** API 테스트 페이지 활성화 여부 */
	@Value(value = "${api.test-page.enabled:false}")
	private boolean apiTestPageEnabled;
	
	/** 이 어플리케이션에 요청 데이터로 전달되는 패스워드가 암호화 되었는지 여부 */
	@Value(value = "${login.password.request-is-encrypted:false}")
	private boolean loginPasswordRequestIsEncrypted;

	/** GrmExternalBackboneService 객체 */
	@Autowired
	private GrmExternalBackboneService grmExternalBackboneService;

	/** ObjectMapper 객체 */
	@Autowired
	private ObjectMapper objectMapper;

	/** ModelMapper 객체 */
	@Autowired
	private ModelMapper modelMapper;

	/** 전역 예외 처리를 위한 컨트롤러의 path */
	public static final String EXCEPTION_CONTROLLER_PATH = "/exception";

	/** 전역 예외 처리를 위한 컨트롤러에 전달될 예외를 담고 있는 attribute 이름 */
	public static final String EXCEPTION_ATTRIBUTE_NAME = "exception";

	/** 구루미 코어, 보험 코어에 응답을 주기 위한 API임을 나타내는 token (API의 URI에 공통적으로 들어감) */
	public static final String API_URL_TOKEN = "/intrf";
	
	/** 이 어플리케이션이 인터페이스의 프로바이더가 되어, 화상상담 서비스를 위해 구르미 코어에 요청을 API임을 나타내는 token */
	public static final String IF_PROVIDER_FOR_GRM_SERVICE_URL_TOKEN = "/counselling";

	/** 요청 모조 데이터를 응답으로 주기 위한 API의 URI */
	private static final String URI_FOR_REQUEST_MOCK_DATA = "/test/api/mockData/req";

//	/** 요청 모조 이미지를 응답으로 주기 위한 API의 URI */
//	private static final String URI_FOR_REQUEST_MOCK_IMAGE = "/test/api/mockImage/req";

	/** 테스트 화면에서 모조데이터를 요청할때, 모조데이터를 요청할 API의 URI를 담는 파라미터 */
	private static final String PARAM_NAME_FOR_REQUEST_MOCK_DATA = "apiPath";

	/** 테스트 화면에서 모조이미지를 요청할때, 모조이미지를 요청할 API의 URI를 담는 파라미터 */
//	private static final String PARAM_NAME_FOR_REQUEST_MOCK_IMAGE = "imageName";

	/** 개인번호(주민등록번호, 외국인등록번호)의 앞번호와 뒷번호를 구분하기위한 구분자 */
	private static final String PERSONAL_NUMBER_DELIMITER = "-";
	
	/** 운전면허증의 앞번호와 뒷번호를 구분하기위한 구분자 */
	private static final String DRIVER_LICENSE_NUMBER_DELIMITER = "-";

	/** 모조 이미지 파일들을 모아놓은 root 경로 */
	private static final String MOCK_IMAGE_ROOT_PATH = "/assets/mockIdCardImage";

	/** 이미지 데이터 파일의 확장자 */
	private static final String IMAGE_DATA_FILE_EXTENSION = "dat";

	/** yyyyMMdd 형식을 담고 있는 SimpleDateFormat 객체 */
	private static final SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	/** 요청 파라미터로 Base64 데이터를 갖는 API 임을 나타내는 token */
	private static final String[] TOKENS_OF_URI_WITH_BASE64_REQUEST_PARAM = new String[] { "idcdOcrRqst", "idcdOcrRqst2", "entry2" };

	/** 기본 sub case 경로 */
	private static final String DEFAULT_SUBCASE_PATH = "/1";
	
	/** path variable token */
	private static final String PATH_VARIABLE_TOKEN = "\\{\\w+\\}";
	
	/** image system token */
	private static final String IMAGE_SYSTEM_TOKEN = "/edms";
	
	
	/**
	 * 신분증 타입으로부터 진위확인구분코드를 도출해서 반환한다.
	 * @param idType 신분증 타입
	 * @return 진위확인구분코드
	 */
	private String getTrflCnfmDvsnCodeFromIdType(String idType) {
		String trflCnfmDvsnCode = null;
		OcrIdType[] ocrIdTypes = IfConstant.OcrIdType.values();
		for (OcrIdType ocrIdType : ocrIdTypes) {
			if (ocrIdType.getName().equals(idType)) {
				trflCnfmDvsnCode = ocrIdType.getTrflCnfmDvsnCode();
				break;
			}
		}
		return trflCnfmDvsnCode;
	}

	/**
	 * 신분증 타입을 인자로 받아, OCR결과 json에서 각 신분증 타입을 나타내는 필드 이름을 반환한다. 
	 * @param idType 신분증 타입
	 * @return OCR결과 json에서 각 신분증 타입을 나타내는 필드 이름
	 */
	private String getIdTypeFieldNameFromIdType(String idType) {
		String fieldName = null;
		if (idType.equals(IfConstant.OcrIdType.IdCard.getName())) {
			fieldName = "ic";
		} else if (idType.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			fieldName = "dl";
		} else if (idType.equals(IfConstant.OcrIdType.Passport.getName())) {
			fieldName = "pp";
		} else if (idType.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			fieldName = "ac";
		}
		return fieldName;
	}
	
	/**
	 * OCR결과 json에서, 각 신분증 타입을 나타내는 필드 이름을 인자로 받아서, 각 신분증 타입별로 신분증 상의 한글 이름을 값으로 갖고 있는 필드이름을 반환한다.
	 * @param idTypeFieldName OCR결과 json에서, 각 신분증 타입을 나타내는 필드 이름
	 * @return 각 신분증 타입별로 신분증 상의 한글 이름을 값으로 갖고 있는 필드이름
	 */
	private String getNameNodeName(String idTypeFieldName) {
		String nameNodeName;
		if("pp".equals(idTypeFieldName)) {
			nameNodeName = "fullNameKor";
		}else {
			nameNodeName = "name";
		}
		return nameNodeName;
	}

	
	/**
	 * OCR결과 JsonNode 객체를 인자로 받아서, 그 안에 있는 고객이름을 찾아 반환한다. 
	 * @param ocrResultReadTree OCR결과 JsonNode 객체
	 * @return 고객이름
	 */
	private String getCustNm(JsonNode ocrResultReadTree) {
		String custNm = null;
		
		try {
			JsonNode idCardJsonNode = ocrResultReadTree.get("idCard");
			JsonNode resultJsonNode = idCardJsonNode.get("result");
			JsonNode idTypeJsonNode = resultJsonNode.get("idtype");
			String idType = idTypeJsonNode.asText();
			String idTypeFieldName = this.getIdTypeFieldNameFromIdType(idType);
			log.info("idTypeFieldName : {}", idTypeFieldName);
			JsonNode idTypeFieldJsonNode = resultJsonNode.get(idTypeFieldName);
			
			String nameNodeName = this.getNameNodeName(idTypeFieldName);
			
			JsonNode nameListJsonNode = idTypeFieldJsonNode.get(nameNodeName);
			if(nameListJsonNode == null) {
				log.warn("idCard.result.idtype.{}.{} 노드가 없습니다.", idTypeFieldName, nameNodeName);
			}
			
			JsonNode firstNameJsonNode = nameListJsonNode.get(0);
			JsonNode firstNameTextJsonNode = firstNameJsonNode.get("text");
			custNm = firstNameTextJsonNode.asText();
		} catch (RuntimeException e) {
			log.warn("custNm 을 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			custNm = "";
		}

		return custNm;
	}
	
	
	/**
	 * OCR결과 JsonNode 객체를 인자로 받아서, 발행일자(yyyyMMdd 형식)를 반환한다. 
	 * @param ocrResultReadTree OCR결과 JsonNode 객체
	 * @return 발행일자(yyyyMMdd형식)
	 */
	private String getIsncDate(JsonNode ocrResultReadTree) {
		String issueDate = null;

		String beforeFormattedIssueDate = null;
		
		try {
			JsonNode idCardJsonNode = ocrResultReadTree.get("idCard");
			JsonNode resultJsonNode = idCardJsonNode.get("result");
			JsonNode idTypeJsonNode = resultJsonNode.get("idtype");
			String idType = idTypeJsonNode.asText();
			String idTypeFieldName = this.getIdTypeFieldNameFromIdType(idType);
			log.info("idTypeFieldName : {}", idTypeFieldName);
			JsonNode idTypeFieldJsonNode = resultJsonNode.get(idTypeFieldName);
			
			JsonNode issueDateListJsonNode = idTypeFieldJsonNode.get("issueDate");
			JsonNode firstIssueDateJsonNode = issueDateListJsonNode.get(0);
			
			JsonNode firstIssueDateTextJsonNode = firstIssueDateJsonNode.get("text");
			beforeFormattedIssueDate = firstIssueDateTextJsonNode.asText();
			log.debug("[포맷팅 이전 issueDate] : {}", beforeFormattedIssueDate);

			JsonNode firstIssueDateFromattedJsonNode = firstIssueDateJsonNode.get("formatted");
			
			JsonNode yearJsonNode = firstIssueDateFromattedJsonNode.get("year");
			String issueYear = yearJsonNode.asText();
			
			JsonNode monthJsonNode = firstIssueDateFromattedJsonNode.get("month");
			String issueMonth = monthJsonNode.asText();
			
			JsonNode dayJsonNode = firstIssueDateFromattedJsonNode.get("day");
			String issueDay = dayJsonNode.asText();
			
			issueDate = issueYear + issueMonth + issueDay;
			
		}catch (RuntimeException e) {
			log.warn("issueDate({}) 를 포맷팅 하는데 실패했습니다. \"\"로 초기화 합니다.", beforeFormattedIssueDate);
			issueDate = "";
		}
		return issueDate;
	}
	
	
	/**
	 * OCR결과 JsonNode 객체를 인자로 받아서, 만료일자(yyyyMMdd 형식)를 반환한다. 
	 * @param ocrResultReadTree OCR결과 JsonNode 객체
	 * @return 만료일자(yyyyMMdd형식)
	 */
	private String getExpyDate(JsonNode ocrResultReadTree) {
		String expyDate = null;
		String beforeFormattedExpireDate = null;
		
		try {
			JsonNode idCardJsonNode = ocrResultReadTree.get("idCard");
			JsonNode resultJsonNode = idCardJsonNode.get("result");
			JsonNode idTypeJsonNode = resultJsonNode.get("idtype");
			String idType = idTypeJsonNode.asText();
			
			if(IfConstant.OcrIdType.IdCard.getName().equalsIgnoreCase(idType) 
					||	IfConstant.OcrIdType.AlienRegistrationCard.getName().equalsIgnoreCase(idType) 
					) {
				return "";
			}
			
			String idTypeFieldName = this.getIdTypeFieldNameFromIdType(idType);
			log.info("idTypeFieldName : {}", idTypeFieldName);
			JsonNode idTypeFieldJsonNode = resultJsonNode.get(idTypeFieldName);
			
			JsonNode expireDateListJsonNode = null;
			if(IfConstant.OcrIdType.DriverLicense.getName().equalsIgnoreCase(idType)) {
				expireDateListJsonNode = idTypeFieldJsonNode.get("renewEndDate");
			}else if(IfConstant.OcrIdType.Passport.getName().equalsIgnoreCase(idType)) {
				expireDateListJsonNode = idTypeFieldJsonNode.get("expireDate");
			}
			
			JsonNode firstExpireDateJsonNode = expireDateListJsonNode.get(0);
			
			JsonNode firstExpireDateTextJsonNode = firstExpireDateJsonNode.get("text");
			beforeFormattedExpireDate = firstExpireDateTextJsonNode.asText();
			log.debug("[포맷팅 이전 expireDate] : {}", beforeFormattedExpireDate);
			
			JsonNode firstExpireDateFromattedJsonNode = firstExpireDateJsonNode.get("formatted");
			
			JsonNode yearJsonNode = firstExpireDateFromattedJsonNode.get("year");
			String expireYear = yearJsonNode.asText();
			
			
			JsonNode monthJsonNode = firstExpireDateFromattedJsonNode.get("month");
			String expireMonth = monthJsonNode.asText();
			
			
			JsonNode dayJsonNode = firstExpireDateFromattedJsonNode.get("day");
			String expireDay = dayJsonNode.asText();
			
			expyDate = expireYear + expireMonth + expireDay;
		} catch (RuntimeException e) {
			log.warn("expireDate({}) 를 포맷팅 하는데 실패했습니다. \"\"로 초기화 합니다.", beforeFormattedExpireDate);
			expyDate = "";
		}

		return expyDate;
	}

	
	/**
	 * OCR결과 JsonNode 객체를 인자로 받아서, 생년월일(yyyyMMdd 형식)을 반환한다. 
	 * @param ocrResultReadTree OCR결과 JsonNode 객체
	 * @return 생년월일(yyyyMMdd형식)
	 */
	private String getBtdt(JsonNode ocrResultReadTree) {
		String btdt = null;
		
		try {
			JsonNode idCardJsonNode = ocrResultReadTree.get("idCard");
			JsonNode resultJsonNode = idCardJsonNode.get("result");
			JsonNode idTypeJsonNode = resultJsonNode.get("idtype");
			String idType = idTypeJsonNode.asText();
			String idTypeFieldName = this.getIdTypeFieldNameFromIdType(idType);
			log.info("idTypeFieldName : {}", idTypeFieldName);
			JsonNode idTypeFieldJsonNode = resultJsonNode.get(idTypeFieldName);
			
			if (idType.equals(IfConstant.OcrIdType.IdCard.getName())) {
				JsonNode personalNumListJsonNode = idTypeFieldJsonNode.get("personalNum");
				JsonNode firstPersonalNumJsonNode = personalNumListJsonNode.get(0);
				JsonNode firstPersonalNumTextJsonNode = firstPersonalNumJsonNode.get("text");
				String personalNum = firstPersonalNumTextJsonNode.asText();
				String[] personalNumTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
				btdt = this.getFirst2digitsOfBirthYearFromPersonalNumFirst1digit(personalNumTokens[1].substring(0, 1)) + personalNumTokens[0];
			} else if (idType.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
				JsonNode personalNumListJsonNode = idTypeFieldJsonNode.get("personalNum");
				JsonNode firstPersonalNumJsonNode = personalNumListJsonNode.get(0);
				JsonNode firstPersonalNumTextJsonNode = firstPersonalNumJsonNode.get("text");
				String personalNum = firstPersonalNumTextJsonNode.asText();
				String[] personalNumTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
				btdt = this.getFirst2digitsOfBirthYearFromPersonalNumFirst1digit(personalNumTokens[1].substring(0, 1)) + personalNumTokens[0];
			} else if (idType.equals(IfConstant.OcrIdType.Passport.getName())) {
				JsonNode birthDateJsonNode = idTypeFieldJsonNode.get("birthDate");
				JsonNode firstBirthDateJsonNode = birthDateJsonNode.get(0);
				JsonNode firstBirthDateFormattedJsonNode = firstBirthDateJsonNode.get("formatted");
				if (firstBirthDateFormattedJsonNode == null) {
					throw new IfException(HttpStatus.OK, "[OCR 실패] : idCard.result.pp.birthDate[0].formatted 가 없습니다.");
				}
				JsonNode birthDateYearJsonNode = firstBirthDateFormattedJsonNode.get("year");
				JsonNode birthDateMonthJsonNode = firstBirthDateFormattedJsonNode.get("month");
				JsonNode birthDateDayJsonNode = firstBirthDateFormattedJsonNode.get("day");
				String birthDateYear = birthDateYearJsonNode.asText();
				String birthDateMonth = birthDateMonthJsonNode.asText();
				String birthDateDay = birthDateDayJsonNode.asText();
				btdt = birthDateYear + birthDateMonth + birthDateDay;
			} else if (idType.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
				JsonNode alienRegNumListJsonNode = idTypeFieldJsonNode.get("alienRegNum");
				JsonNode firstAlienRegNumJsonNode = alienRegNumListJsonNode.get(0);
				JsonNode firstAlienRegNumTextJsonNode = firstAlienRegNumJsonNode.get("text");
				String alienRegNum = firstAlienRegNumTextJsonNode.asText();
				String[] alienRegNumTokens = alienRegNum.split(PERSONAL_NUMBER_DELIMITER);
				btdt = this.getFirst2digitsOfBirthYearFromPersonalNumFirst1digit(alienRegNumTokens[1].substring(0, 1)) + alienRegNumTokens[0];
			}
			
			/*
			if(StringUtils.defaultString(btdt).length() != 8) {
				throw new IfException(HttpStatus.OK, String.format("[OCR 실패] 인식된 생년월일이 8자리가 아닙니다. (인식결과 : %s)", btdt));
			}
			*/
		}catch (RuntimeException e) {
			log.warn("btdt({}) 를 포맷팅 하는데 실패했습니다. \"\"로 초기화 합니다.", btdt);
			btdt = "";
		}

		return btdt;
	}

	
	
	/**
	 * 개인번호(주민등록번호, 외국인등록번호) 뒷자리의 첫번째 숫자를 인자로 받아서, 생년월일에서 생년의 첫번째, 두번째 숫자 (예: 1988년의 경우, 19)를 반환한다.
	 * @param personalNumFirst1digit 개인번호(주민등록번호, 외국인등록번호) 뒷자리의 첫번째 숫자
	 * @return 생년월일에서 생년의 첫번째, 두번째 숫자 (예: 1988년의 경우, 19)
	 */
	private String getFirst2digitsOfBirthYearFromPersonalNumFirst1digit(String personalNumFirst1digit) {
		String first2digitsOfBirthYear = "";

		switch (personalNumFirst1digit) {
		case "9":
		case "0":
			first2digitsOfBirthYear = "18";
		case "1":
		case "2":
		case "5":
		case "6":
			first2digitsOfBirthYear = "19";
			break;
		case "3":
		case "4":
		case "7":
		case "8":
			first2digitsOfBirthYear = "20";
			break;
		default:
			break;
		}
		return first2digitsOfBirthYear;
	}
	
	
	/**
	 * 개인번호(주민등록번호, 외국인등록번호)로부터 생년월일을 도출한 후, yyyyMMdd 형식으로 반환한다.
	 * @param personalNum 개인번호(주민등록번호, 외국인등록번호)
	 * @return 개인번호(주민등록번호, 외국인등록번호)로부터 생년월일(yyyyMMdd 형식)
	 */
	private String getBirthYyyyMMddFromPersonalNum(String personalNum) {
		
		String refinedPersonalNum = personalNum.replaceAll("\\D", "");
		
		if(StringUtils.defaultString(refinedPersonalNum).length() < 7) {
			throw new RuntimeException("개인번호가 7자리 미만입니다.");
		}
		
		String yyMMdd = refinedPersonalNum.substring(0, 6);
		String regNumFirst1digit = refinedPersonalNum.substring(6, 6 + 1);
		String yy = getFirst2digitsOfBirthYearFromPersonalNumFirst1digit(regNumFirst1digit);
		String yyyyMMdd = yy + yyMMdd;
		return yyyyMMdd;
	}

	
	
	/**
	 * OCR결과 JsonNode 객체를 인자로 받아서, 성별(M/F)을 반환한다. 
	 * @param ocrResultReadTree OCR결과 JsonNode 객체
	 * @return 성별(M/F)
	 */
	private String getSex(JsonNode ocrResultReadTree) {
		String sex = null;
		
		try {
			
			JsonNode idCardJsonNode = ocrResultReadTree.get("idCard");
			JsonNode resultJsonNode = idCardJsonNode.get("result");
			JsonNode idTypeJsonNode = resultJsonNode.get("idtype");
			String idType = idTypeJsonNode.asText();
			String idTypeFieldName = this.getIdTypeFieldNameFromIdType(idType);
			log.info("idTypeFieldName : {}", idTypeFieldName);
			JsonNode idTypeFieldJsonNode = resultJsonNode.get(idTypeFieldName);

			if (idType.equals(IfConstant.OcrIdType.IdCard.getName())) {
				JsonNode personalNumListJsonNode = idTypeFieldJsonNode.get("personalNum");
				JsonNode firstPersonalNumJsonNode = personalNumListJsonNode.get(0);
				JsonNode firstPersonalNumTextJsonNode = firstPersonalNumJsonNode.get("text");
				String personalNum = firstPersonalNumTextJsonNode.asText();
				String[] personalNumberTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
				sex = (Integer.parseInt(personalNumberTokens[1].substring(0, 1)) % 2) == 1 ? "M" : "F";
			} else if (idType.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
				JsonNode personalNumListJsonNode = idTypeFieldJsonNode.get("personalNum");
				JsonNode firstPersonalNumJsonNode = personalNumListJsonNode.get(0);
				JsonNode firstPersonalNumTextJsonNode = firstPersonalNumJsonNode.get("text");
				String personalNum = firstPersonalNumTextJsonNode.asText();
				String[] personalNumberTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
				sex = (Integer.parseInt(personalNumberTokens[1].substring(0, 1)) % 2) == 1 ? "M" : "F";
			} else if (idType.equals(IfConstant.OcrIdType.Passport.getName())) {
				JsonNode sexListjsonNode = idTypeFieldJsonNode.get("sex");
				JsonNode firstSexjsonNode = sexListjsonNode.get(0);
				JsonNode firstSexTextJsonNode = firstSexjsonNode.get("text");
				sex = firstSexTextJsonNode.asText();
			} else if (idType.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
				JsonNode sexListjsonNode = idTypeFieldJsonNode.get("sex");
				JsonNode firstSexjsonNode = sexListjsonNode.get(0);
				JsonNode firstSexTextJsonNode = firstSexjsonNode.get("text");
				sex = firstSexTextJsonNode.asText();
			}
			
		}catch (RuntimeException e) {
			log.warn("sex({}) 를 포맷팅 하는데 실패했습니다. \"\"로 초기화 합니다.", sex);
			sex = "";
		}

		return sex;
	}

	
	/**
	 * yyyyMMdd 형식의 년월일 문자열을 인자로 받아서 이에 대응하는 java.sql.Date 객체를 생성해 반환한다.
	 * @param date yyyyMMdd 형식의 년월일 문자열
	 * @return java.sql.Date 객체
	 * @throws ParseException
	 */
	public Date transformDate(String date) throws ParseException {
		SimpleDateFormat beforeFormat = DATE_FORMAT_YYYYMMDD;

		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");

		java.util.Date tempDate = null;

		tempDate = beforeFormat.parse(date);

		String transDate = afterFormat.format(tempDate);

		Date d = Date.valueOf(transDate);

		return d;
	}

	
	/**
	 * DateInfo 객체의 List 를 인자로 받아서, 이 DateInfo 들 중, 휴무일인 DateInfo 만 담은 List 를 생성해 반환한다.
	 * @param dateInfoList DateInfo 객체의 List
	 * @return 휴무일인 DateInfo 만 담은 List
	 */
	private List<DateInfo> filterClosedDays(List<DateInfo> dateInfoList){
		return dateInfoList.stream().filter(dateInfo -> {
			boolean isRemainCase = false;
			if(
				"Y".equalsIgnoreCase(dateInfo.getAcdtIsrnHldyYn())
				|| "Y".equalsIgnoreCase(dateInfo.getBsnsOfdyYn())
				|| "Y".equalsIgnoreCase(dateInfo.getElctAppnOfdyYn())
				|| "Y".equalsIgnoreCase(dateInfo.getHldyYn())
				|| "Y".equalsIgnoreCase(dateInfo.getStckOfdyYn())
			) {
				isRemainCase = true;
			}
			return isRemainCase;
		}).collect(Collectors.toList());
	} 
	
	
	/**
	 * <pre>
	 * [00] 
	 * API - 진입2
	 * 
	 * [01, 02, 09, 03]
	 *	01.신분증OCR요청
	 *	02.진위확인결과조회
	 *	09.개인정보유출노출여부조회
	 *	03.신분증스캔후처리
	 * </pre>
	 * @param mvc001ReqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/entry2"), (API_URL_TOKEN + "/entry2" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "00. 진입2")
	public @ResponseBody ResponseDto<Mvc000ResDto> entry2(@RequestBody Mvc001ReqDto mvc001ReqDto, HttpServletRequest request)
			throws URISyntaxException, IOException, ParseException {

		// XXX [01] 신분증OCR요청
		ResponseDto<Mvc001ResDto> response001dto = this.idcdOcrRqst2(mvc001ReqDto, request);
		Mvc001ResDto mvc001ResDto = response001dto.getData();

//		Mvc001ResDto.DataHeader mvc001ResDtoDataHeader = mvc001ResDto.getDataHeader();
		Mvc001ResDto.DataBody mvc001ResDtoDataBody = mvc001ResDto.getDataBody();

		String ocrResultJson = mvc001ResDtoDataBody.getImages();

		JsonNode ocrResultReadTrees = objectMapper.readTree(ocrResultJson);

		/*
		// [01] 신분증OCR요청 으로 로직 이동
		if (ocrResultReadTrees.size() != 1) {
			String message = String.format("신분증 OCR 결과가 1건이 아닙니다. (%d건)", ocrResultReadTrees.size());
			throw new IfException(message);
		}
		*/

		JsonNode ocrResultReadTree = ocrResultReadTrees.get(0);

		/*
		// [01] 신분증OCR요청 으로 로직 이동
		if(!IfConstant.OcrInferResult.SUCCESS.getResultValue().equals(ocrResultReadTree.get("inferResult").asText())) {
			String message = ocrResultReadTree.get("message").asText();
			message = String.format("이미지 인식을 실패했습니다." + System.lineSeparator() + "(message : %s)", message);
			throw new IfException(message);
		}
		*/

		Mvc000ResDto mvc000ResDto = new Mvc000ResDto();

		String emnb = mvc001ReqDto.getEmnb();
		mvc000ResDto.setEmnb(emnb);

		String idType = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();
		String trflCnfmDvsnCode = this.getTrflCnfmDvsnCodeFromIdType(idType);

		String custNm = this.getCustNm(ocrResultReadTree);
		String btdt = this.getBtdt(ocrResultReadTree);
		String isncDate = this.getIsncDate(ocrResultReadTree);
		String sex = this.getSex(ocrResultReadTree);

		mvc000ResDto.setCustBirthDate(btdt);
//		mvc000ResDto.setCustBirthDate(this.transformDate(btdt));
		mvc000ResDto.setCustNm(custNm);
		mvc000ResDto.setIdType(idType);
		mvc000ResDto.setCustGender(sex);

		// XXX [02] 진위확인결과조회
		Mvc002ReqDto mvc002ReqDto = new Mvc002ReqDto();

		mvc002ReqDto.setEmnb(mvc001ReqDto.getEmnb());

		mvc002ReqDto.setTrflCnfmDvsnCode(trflCnfmDvsnCode);

		mvc002ReqDto.setTrflCnfmBswrDvsnCode(TrflCnfmBswrDvsnCode.other.getCode());

		mvc002ReqDto.setTrflCnfmChnlCode(IfConstant.TrflCnfmChnlCode.INDIVIDUAL.getCode());

		mvc002ReqDto.setPrcsBswrScrnId(mvc001ReqDto.getSCRN_ID());

		mvc002ReqDto.setTrflCnfmJobCode(TrflCnfmJobCode.CUSTOMER_IDENTIFICATION.getCode());

		mvc002ReqDto.setCustId("");

		mvc002ReqDto.setMgmtNo("");
		mvc002ReqDto.setCustNm(custNm);
//		mvc002ReqDto.setBtdt(this.transformDate(btdt));
		mvc002ReqDto.setBtdt(btdt);
		
//		mvc002ReqDto.setIsncDate(this.transformDate(isncDate));
		mvc002ReqDto.setIsncDate(isncDate);
		mvc002ReqDto.setExpyDate(null);

		if (idType.equals(IfConstant.OcrIdType.IdCard.getName())) {
			String rrno = ocrResultReadTree.get("idCard").get("result").get("ic").get("personalNum").get(0).get("text").asText();
			rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");

			mvc002ReqDto.setRrno(rrno);
		} else if (idType.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String driverLicenseNumber = ocrResultReadTree.get("idCard").get("result").get("dl").get("num").get(0).get("text").asText();
			driverLicenseNumber = driverLicenseNumber.replaceAll(DRIVER_LICENSE_NUMBER_DELIMITER, "");

			String driverLicenseSequenceNumber = ocrResultReadTree.get("idCard").get("result").get("dl").get("code").get(0).get("text").asText();
			
//			mvc002ReqDto.setDrvnLcnsSqno("");
			mvc002ReqDto.setDrvnLcnsSqno(driverLicenseSequenceNumber);
			mvc002ReqDto.setDrvnLcnsNo(driverLicenseNumber);
			
			
			String rrno = ocrResultReadTree.get("idCard").get("result").get("dl").get("personalNum").get(0).get("text").asText();
			rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");

			mvc002ReqDto.setRrno(rrno);
			
		} else if (idType.equals(IfConstant.OcrIdType.Passport.getName())) {
			String passportNumber = ocrResultReadTree.get("idCard").get("result").get("pp").get("num").get(0).get("text").asText();

			mvc002ReqDto.setPsprNo(passportNumber);
		} else if (idType.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			String alienRegistrationNumber = ocrResultReadTree.get("idCard").get("result").get("ac").get("alienRegNum").get(0).get("text").asText();

			mvc002ReqDto.setFrnrRgstNo(alienRegistrationNumber);
		}

		ResponseDto<Mvc002ResDto> response002dto = this.trflCnfm(mvc002ReqDto, request);

		Mvc002ResDto mvc002ResDto = response002dto.getData();

		String csnsYn = mvc002ResDto.getCsnsYn();
		mvc000ResDto.setCustInfoAuthenticityYn(csnsYn);

		String trflCnfmRsltVal = mvc002ResDto.getTrflCnfmRsltVal();
		mvc000ResDto.setCustInfoAuthenticityResultCode(trflCnfmRsltVal);

		String rsltMsgeCntn = mvc002ResDto.getRsltMsgeCntn();
		mvc000ResDto.setCustInfoAuthenticityResultMessage(rsltMsgeCntn);

		String custId = mvc002ResDto.getCustId();
		mvc000ResDto.setCustId(custId);

		// XXX [09] 개인정보유출노출여부조회
		Mvc009ReqDto mvc009ReqDto = new Mvc009ReqDto();
		mvc009ReqDto.setEmnb(mvc001ReqDto.getEmnb());
		mvc009ReqDto.setCustId(mvc002ResDto.getCustId());

		ResponseDto<Mvc009ResDto> response009dto = this.prsnInfoLeakMgmt(mvc009ReqDto, request);
		Mvc009ResDto mvc009ResDto = response009dto.getData();

		String leakYn = mvc009ResDto.getLeakYn();
		String epsrYn = mvc009ResDto.getEpsrYn();
		String hpnoChngYn = mvc009ResDto.getHpnoChngYn();

		mvc000ResDto.setCustPersonalInfoleakYn(leakYn);
		mvc000ResDto.setCustPersonalInfoEpsrYn(epsrYn);
		mvc000ResDto.setCustHpnoChngYn(hpnoChngYn);

		// [03] 신분증스캔후처리
		Mvc003ReqDto mvc003ReqDto = new Mvc003ReqDto();
		mvc003ReqDto.setCsnsYn(mvc002ResDto.getCsnsYn());
		mvc003ReqDto.setCustId(mvc002ResDto.getCustId());
		mvc003ReqDto.setEmnb(mvc001ReqDto.getEmnb());

		ResponseDto<Mvc003ResDto> response003dto = this.itfcIdcdScan(mvc003ReqDto, request);
		Mvc003ResDto mvc003ResDto = response003dto.getData();
		String prcsSucsYn = mvc003ResDto.getPrcsSucsYn();

		mvc000ResDto.setSingleViewOpenMessagePushSuccessYn(prcsSucsYn);

		ResponseDto<Mvc000ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, mvc000ResDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [01] 
	 * API - 신분증OCR요청2
	 * </pre> 
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/idcdOcrRqst2"), (API_URL_TOKEN + "/idcdOcrRqst2" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "01. 신분증OCR요청2")
	public @ResponseBody ResponseDto<Mvc001ResDto> idcdOcrRqst2(@RequestBody Mvc001ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

//		IfMcCs001_I cs001_I = modelMapper.map(reqDto, IfMcCs001_I.class);

		IfMcCs001_I.DataHeader dataHeader = new IfMcCs001_I.DataHeader();

		dataHeader.setSRVC_ID(IfConstant.SRVC_ID);

		// TODO 확인 필요
//		dataHeader.setSCRN_ID(IfConstant.TRNM_SYS_CODE);
		dataHeader.setSCRN_ID(reqDto.getSCRN_ID());

		dataHeader.setX_OCR_SECRET(ocrSecretKey);

//		dataHeader.setCRTF_RTCD("");

//		dataHeader.setDLRE_MSG("");

		Image image = new IfMcCs001_I.DataBody.Image();

		String imageFormat = reqDto.getFormat();
		image.setFormat(imageFormat);

		String imageBase64Data = reqDto.getData();
		String regexBase64prefix = "^.+\\,";
		String refinedImageBase64Data = imageBase64Data.replaceAll(regexBase64prefix, "");
		image.setData(refinedImageBase64Data);

		String imageName = reqDto.getName();
		image.setName(imageName);

		List<Image> imageList = new ArrayList<>();
		imageList.add(image);

		IfMcCs001_I.DataBody dataBody = new IfMcCs001_I.DataBody();

		dataBody.setORGN_CODE(IfConstant.BELN_ORGN_CODE);

		String userId = reqDto.getUSER_ID();
		dataBody.setUSER_ID(userId);

		dataBody.setImages(imageList);

		IfMcCs001_I ifInputDto = new IfMcCs001_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs001;
		Class<IfMcCs001_O> ifOutputDtoClass = IfMcCs001_O.class;
		IfMcCs001_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String ocrResultJson = ifOutputDto.getDataBody().getImages();
		log.info("[OCR RESULT] : {}", ocrResultJson);

		JsonNode ocrResultReadTrees = objectMapper.readTree(ocrResultJson);

		if (ocrResultReadTrees.size() != 1) {
			String message = String.format("신분증 OCR 결과가 1건이 아닙니다. (%d건)", ocrResultReadTrees.size());
			throw new IfException(HttpStatus.OK, message);
		}

		JsonNode ocrResultReadTree = ocrResultReadTrees.get(0);

		if (!IfConstant.OcrInferResult.SUCCESS.getResultValue().equals(ocrResultReadTree.get("inferResult").asText())) {
			String message = ocrResultReadTree.get("message").asText();
			message = String.format("이미지 인식을 실패했습니다." + System.lineSeparator() + "(message : %s)", message);
			throw new IfException(HttpStatus.INTERNAL_SERVER_ERROR, message);
		}

		Mvc001ResDto resDto = modelMapper.map(ifOutputDto, Mvc001ResDto.class);

		ResponseDto<Mvc001ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;

	}

	
	
	/**
	 * <pre>
	 * [02] 
	 * API - 진위확인결과조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/trflCnfm"), (API_URL_TOKEN + "/trflCnfm" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "02. 진위확인결과조회")
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs002_I ifInputDto = modelMapper.map(reqDto, IfMcCs002_I.class);
		
		String rrno = StringUtils.defaultString(ifInputDto.getRrno());
		rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
		ifInputDto.setRrno(rrno);
		
		String driverLicenseNumber = StringUtils.defaultString(ifInputDto.getDrvnLcnsNo());
		driverLicenseNumber = driverLicenseNumber.replaceAll(DRIVER_LICENSE_NUMBER_DELIMITER, "");
		driverLicenseNumber = driverLicenseNumber.replaceAll("\\s+", "");
		ifInputDto.setDrvnLcnsNo(driverLicenseNumber);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;
		Class<IfMcCs002_O> ifOutputDtoClass = IfMcCs002_O.class;
		IfMcCs002_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		
		Mvc002ResDto resDto = modelMapper.map(ifOutputDto, Mvc002ResDto.class);

		ResponseDto<Mvc002ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [03] 
	 * API - 신분증스캔후처리
	 * </pre> 
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcIdcdScan"), (API_URL_TOKEN + "/itfcIdcdScan" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "03. 신분증스캔후처리")
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs003_I ifInputDto = modelMapper.map(reqDto, IfMcCs003_I.class);
		ifInputDto.setPushRcvrEmnb(reqDto.getEmnb());

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;
		Class<IfMcCs003_O> ifOutputDtoClass = IfMcCs003_O.class;
		IfMcCs003_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		/*
		if (!"Y".equalsIgnoreCase(ifOutputDto.getPrcsSucsYn())) {
			throw new IfException("싱글뷰 오픈 메세지 전송에 실패했습니다.");
		}
		*/

		Mvc003ResDto resDto = modelMapper.map(ifOutputDto, Mvc003ResDto.class);

		ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	/**
	 * <pre>
	 * [04] 
	 * API - 권한별사용자조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/atrtSrch"), (API_URL_TOKEN + "/atrtSrch" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "04. 권한별사용자조회")
	public @ResponseBody ResponseDto<Mvc004ResDto> atrtSrch(@RequestBody Mvc004ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
	
		IfMcCs004_I ifInputDto = modelMapper.map(reqDto, IfMcCs004_I.class);
		
		if("".equals(StringUtils.defaultString(ifInputDto.getIsrnCoreAtrtId()))) {
			ifInputDto.setIsrnCoreAtrtId(IfConstant.ISRN_CORE_ATRT_ID);
		}
		
		if(ifInputDto.getPageSize() <= 0) {
			ifInputDto.setPageSize(50);
		}
		
		String nextKey = "".equals(StringUtils.defaultString(ifInputDto.getNextKey())) ? "0" : ifInputDto.getNextKey();
		
		List<User> totalUserList = new ArrayList<>();
		List<User> pagingUserList = null;
		
		IfMcCs004_O ifOutputDto = null;
		
		do {
			
			ifInputDto.setNextKey(nextKey);
			
			String emnb = reqDto.getEmnb();
			IfSpec ifSpec = IfConstant.IfSpec.IfMcCs004;
			Class<IfMcCs004_O> ifOutputDtoClass = IfMcCs004_O.class;
			ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
			
			nextKey = ifOutputDto.getNextKey();
			
			pagingUserList = ifOutputDto.getUserLstList();
			if(pagingUserList != null && pagingUserList.size() != 0) {
				totalUserList.addAll(pagingUserList);
			}
			
		} while (!"0".equals(nextKey) && (pagingUserList != null && pagingUserList.size() > 0));
		
		ifOutputDto.setUserLstList(totalUserList);
	
	
		Mvc004ResDto resDto = modelMapper.map(ifOutputDto, Mvc004ResDto.class);
	
		ResponseDto<Mvc004ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	
	
	/**
	 * <pre>
	 * [05]
	 * API - SSO대체로그인인증
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcUserCtfn"), (API_URL_TOKEN + "/itfcUserCtfn" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "05. SSO대체로그인인증")
	public @ResponseBody ResponseDto<Mvc005ResDto> itfcUserCtfn(@RequestBody Mvc005ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

//		IfMcCs005_I cs005_I = modelMapper.map(reqDto, IfMcCs005_I.class);

		String emnb = reqDto.getEmnb();

//		String lognPswd = reqDto.getLognPswd();
//		String encLognPswd = SHA256CmCrypt.SHA256_getEncString(lognPswd);
		
		String encLognPswd = null;
		
		if(loginPasswordRequestIsEncrypted && reqDto.isLognPswdEncrypted()) {
			encLognPswd = reqDto.getLognPswd();	// 요청 DTO에 이미 encrypt 된 패스워드가 담겨 있다.
		}else {
			encLognPswd = SHA256CmCrypt.SHA256_getEncString(reqDto.getLognPswd());
		}

		IfMcCs005_I ifInputDto005 = new IfMcCs005_I();
		ifInputDto005.setEmnb(emnb);
		ifInputDto005.setLognPswd(encLognPswd);

		IfSpec ifSpec005 = IfConstant.IfSpec.IfMcCs005;
		Class<IfMcCs005_O> ifOutputDtoClass005 = IfMcCs005_O.class;
		IfMcCs005_O ifOutputDto005 = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec005, ifInputDto005, ifOutputDtoClass005);

		String rspnCodeVal = ifOutputDto005.getRspnCodeVal(); // "00":오류, "01":정상
		
		if (!"01".equalsIgnoreCase(rspnCodeVal)) {
			throw new IfException(HttpStatus.OK, String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", ifOutputDto005.getRspnMsgeCntn(), ifOutputDto005.getRspnMsgeUniqId()));
		}

		Mvc005ResDto resDto = modelMapper.map(ifOutputDto005, Mvc005ResDto.class);
		
		
		// XXX
		Mvc004ReqDto mvc004ReqDto = new Mvc004ReqDto();
		mvc004ReqDto.setIsrnCoreAtrtId(IfConstant.ISRN_CORE_ATRT_ID);
		mvc004ReqDto.setOrgnCode(ifOutputDto005.getBelnOrgnCode());
		
		ResponseDto<Mvc004ResDto> mvc004ResponseDto = this.atrtSrch(mvc004ReqDto, request);
		List<com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc004ResDto.User> userLstList = mvc004ResponseDto.getData().getUserLstList();
		
		if(userLstList == null) {
			log.info("userLstList가 null 입니다.");
		}else if(userLstList.size() == 0) {
			log.info("userLstList가 0개 입니다.");
		}else {
			String hasAuthorityYn = "N";
			for (com.gooroomee.backbone.external.dto.ifconsumer.client.Mvc004ResDto.User user : userLstList) {
				String authorityEmnb = user.getEmnb();
				if(authorityEmnb.equals(reqDto.getEmnb())) {
					hasAuthorityYn = "Y";
					break;
				}
			}
			resDto.setHasAuthorityYn(hasAuthorityYn);
		}
		
		
		
		/*
		// XXX
		IfMcCs004_I ifInputDto021 = new IfMcCs004_I();
		ifInputDto021.setIsrnCoreAtrtId(IfConstant.ISRN_CORE_ATRT_ID);
		ifInputDto021.setOrgnCode(ifOutputDto005.getBelnOrgnCode());
		
		IfSpec ifSpec021 = IfConstant.IfSpec.IfMcCs004;
		Class<IfMcCs004_O> ifOutputDtoClass021 = IfMcCs004_O.class;
		IfMcCs004_O ifOutputDto021 = grmAdapterService.ifmccsCommon(emnb, ifSpec021, ifInputDto021, ifOutputDtoClass021);
		
		
		List<User> userLstList = ifOutputDto021.getUserLstList();
		if(userLstList == null) {
			log.info("userLstList가 null 입니다.");
		}else if(userLstList.size() == 0) {
			log.info("userLstList가 0개 입니다.");
		}else {
			String hasAuthorityYn = "N";
			for (User user : userLstList) {
				String authorityEmnb = user.getEmnb();
				if(authorityEmnb.equals(reqDto.getEmnb())) {
					hasAuthorityYn = "Y";
					break;
				}
			}
			resDto.setHasAuthorityYn(hasAuthorityYn);
		}
		*/

		ResponseDto<Mvc005ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	
	/**
	 * <pre>
	 * [06]
	 * API - 사원목록조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/empeInqy"), (API_URL_TOKEN + "/empeInqy" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "06. 사원목록조회")
	public @ResponseBody ResponseDto<Mvc006ResDto> empeInqy(@RequestBody Mvc006ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs006_I ifInputDto = modelMapper.map(reqDto, IfMcCs006_I.class);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;
		Class<IfMcCs006_O> ifOutputDtoClass = IfMcCs006_O.class;
		IfMcCs006_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		/*
		if (ifOutputDto.getEmpeInfoList() == null || ifOutputDto.getEmpeInfoList().size() == 0) {
			throw new IfException("조회된 사원이 없습니다.");
		}
		*/

		if (ifOutputDto.getEmpeInfoList() == null) {
			ifOutputDto.setEmpeInfoList(new ArrayList<>());
		}

		Mvc006ResDto resDto = modelMapper.map(ifOutputDto, Mvc006ResDto.class);

		ResponseDto<Mvc006ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [07]
	 * API - 고객계약정보조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCustInqyMgmt"), (API_URL_TOKEN + "/intgCustInqyMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "07. 고객계약정보조회")
	public @ResponseBody ResponseDto<Mvc007ResDto> intgCustInqyMgmt(@RequestBody Mvc007ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs007_I ifInputDto = modelMapper.map(reqDto, IfMcCs007_I.class);
		ifInputDto.setCntcDvsnCode(IfConstant.ContractClassificationCode.INSURANCE.getCode());
		ifInputDto.setCustDvsnCode(IfConstant.CustomerDivisionCode.INDIVIDUAL.getCode());

		if(ifInputDto.getPageSize() <= 0) {
			ifInputDto.setPageSize(50);
		}
		
		String isLastPageYn = "N";
		String nextKey = "".equals(StringUtils.defaultString(ifInputDto.getNextKey())) ? "0" : ifInputDto.getNextKey();
		
		List<CustCntcInfoInqyRslt> totalContractList = new ArrayList<>();
		List<CustCntcInfoInqyRslt> pagingContractList = null;
		
		IfMcCs007_O ifOutputDto = null;
		
		do {
			ifInputDto.setNextKey(nextKey);
			
			String emnb = reqDto.getEmnb();
			IfSpec ifSpec = IfConstant.IfSpec.IfMcCs007;
			Class<IfMcCs007_O> ifOutputDtoClass = IfMcCs007_O.class;
			ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		
			isLastPageYn = ifOutputDto.getNextPageYn();		// 보험코어 소스, 실제 응답결과를 보면 파라미터 이름이 nextPageYn 이지만, 사실상 isLastPageYn(마지막 페이지 여부)임.
			pagingContractList = ifOutputDto.getCustCntcInfoInqyRsltList();
			
			if(pagingContractList != null) {
				totalContractList.addAll(pagingContractList);
				int custCntcInfoInqyRsltListSize = pagingContractList.size();
				nextKey = String.valueOf(Integer.parseInt(nextKey) + custCntcInfoInqyRsltListSize);
			}
		
		} while ("N".equalsIgnoreCase(isLastPageYn) && (pagingContractList != null && pagingContractList.size() > 0));
		
		ifOutputDto.setCustCntcInfoInqyRsltList(totalContractList);
		ifOutputDto.setNextPageYn(isLastPageYn);
		ifOutputDto.setTotCont(totalContractList.size());

		Mvc007ResDto resDto = modelMapper.map(ifOutputDto, Mvc007ResDto.class);

		ResponseDto<Mvc007ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}


	
	/**
	 * <pre>
	 * [08]
	 * API - 고객계좌목록조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCust"), (API_URL_TOKEN + "/intgCust" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "08. 고객계좌목록조회")
	public @ResponseBody ResponseDto<Mvc008ResDto> intgCust(@RequestBody Mvc008ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs008_I ifInputDto = modelMapper.map(reqDto, IfMcCs008_I.class);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs008;
		Class<IfMcCs008_O> ifOutputDtoClass = IfMcCs008_O.class;
		IfMcCs008_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		/*
		if (ifOutputDto.getCustAcntListInqyList() == null || ifOutputDto.getCustAcntListInqyList().size() == 0) {
			throw new IfException("조회된 고객계좌정보가 없습니다.");
		}
		*/
		if (ifOutputDto.getCustAcntListInqyList() == null) {
			ifOutputDto.setCustAcntListInqyList(new ArrayList<>());
		}

		Mvc008ResDto resDto = modelMapper.map(ifOutputDto, Mvc008ResDto.class);

		ResponseDto<Mvc008ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}


	
	/**
	 * <pre>
	 * [09]
	 * API - 개인정보유출노출여부조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/prsnInfoLeakMgmt"), (API_URL_TOKEN + "/prsnInfoLeakMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "09. 개인정보유출노출여부조회")
	public @ResponseBody ResponseDto<Mvc009ResDto> prsnInfoLeakMgmt(@RequestBody Mvc009ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs009_I ifInputDto = modelMapper.map(reqDto, IfMcCs009_I.class);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs009;
		Class<IfMcCs009_O> ifOutputDtoClass = IfMcCs009_O.class;
		IfMcCs009_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		Mvc009ResDto resDto = modelMapper.map(ifOutputDto, Mvc009ResDto.class);

		ResponseDto<Mvc009ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}


	
	/**
	 * <pre>
	 * [10]
	 * API - 간편인증 토큰발급
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/initechToken"), (API_URL_TOKEN + "/initechToken" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "10. 간편인증 토큰발급")
	public @ResponseBody ResponseDto<Mvc010ResDto> initechToken(@RequestBody Mvc010ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

//		IfMcCs010_I cs010_I = modelMapper.map(reqDto, IfMcCs010_I.class);

		IfMcCs010_I.DataHeader dataHeader = new IfMcCs010_I.DataHeader();
//		dataHeader.setCRTF_RTCD("");
//		dataHeader.setDLRE_MSG("");
		dataHeader.setORGN_CODE(IfConstant.BELN_ORGN_CODE);

		// TODO 확인 필요
//		dataHeader.setSCRN_ID(IfConstant.TRNM_SYS_CODE);
		dataHeader.setSCRN_ID(reqDto.getSCRN_ID());

		dataHeader.setSRVC_ID(IfConstant.SRVC_ID);

		// TODO 확인 필요
		dataHeader.setUSER_ID(reqDto.getUSER_ID());

		IfMcCs010_I.DataBody dataBody = new IfMcCs010_I.DataBody();
		dataBody.setGrant_type(IfConstant.EzCertSrvcGrantType.client_credentials.getValue());

		IfMcCs010_I ifInputDto = new IfMcCs010_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs010;
		Class<IfMcCs010_O> ifOutputDtoClass = IfMcCs010_O.class;
		IfMcCs010_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String crtf_RTCD = ifOutputDto.getDataHeader().getCRTF_RTCD(); // 처리결과코드 ("0000":정상, 그외:실패)
		String dlre_MSG = ifOutputDto.getDataHeader().getDLRE_MSG();
		if (!"0000".equalsIgnoreCase(crtf_RTCD)) {
			throw new IfException(HttpStatus.OK, String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", dlre_MSG, crtf_RTCD));
		}

		Mvc010ResDto resDto = modelMapper.map(ifOutputDto, Mvc010ResDto.class);

		ResponseDto<Mvc010ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [11]
	 * API - 간편인증 요청
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체 
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/initechRequest"), (API_URL_TOKEN + "/initechRequest" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "11. 간편인증 요청")
	public @ResponseBody ResponseDto<Mvc011ResDto> initechRequest(@RequestBody Mvc011ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {

		// IfMcCs011_I cs011_I = modelMapper.map(reqDto, IfMcCs011_I.class);

		IfMcCs011_I.DataHeader dataHeader = new IfMcCs011_I.DataHeader();
		// dataHeader.setCRTF_RTCD("");
		// dataHeader.setDLRE_MSG("");
		dataHeader.setORGN_CODE(IfConstant.BELN_ORGN_CODE);

		// TODO 확인 필요
//		dataHeader.setSCRN_ID(IfConstant.TRNM_SYS_CODE);
		dataHeader.setSCRN_ID(reqDto.getSCRN_ID());

		dataHeader.setSRVC_ID(IfConstant.SRVC_ID);

		// TODO 확인 필요
		dataHeader.setUSER_ID(reqDto.getUSER_ID());

		IfMcCs011_I.DataBody dataBody = new IfMcCs011_I.DataBody();
		
		String token_type = reqDto.getToken_type();
		token_type = StringUtils.defaultString(token_type).trim();
		
		String access_token = reqDto.getAccess_token();
		access_token = StringUtils.defaultString(access_token).trim();
				
		String initechOAuthToken = token_type + " " + access_token;
		dataBody.setInitechOAuthToken(initechOAuthToken);

		String pid = reqDto.getPid();
		dataBody.setPid(pid);

		String uname = reqDto.getUname();
		uname = AesUtil.encrypt(uname, aesKey, aesIv);
		dataBody.setUname(uname);

		String ubirthday = reqDto.getUbirthday();
		ubirthday = AesUtil.encrypt(ubirthday, aesKey, aesIv);
		dataBody.setUbirthday(ubirthday);

		String ugender = reqDto.getUgender();
		ugender = AesUtil.encrypt(ugender, aesKey, aesIv);
		dataBody.setUgender(ugender);

		String uphone = reqDto.getUphone();
		uphone = AesUtil.encrypt(uphone, aesKey, aesIv);
		dataBody.setUphone(uphone);

		String op = IfConstant.EzCertSrvcOp.auth.getValue();
		dataBody.setOp(op);

		IfMcCs011_I.DataBody.Sign sign = new IfMcCs011_I.DataBody.Sign();
		sign.setContents("");
		dataBody.setSign(sign);

		String deviceCode = IfConstant.EzCertSrvcDeviceCode.MOBILE.getValue();
		dataBody.setDeviceCode(deviceCode);

		String deviceBrowser = IfConstant.EzCertSrvcDeviceBrowser.webBrowser.getValue();
		dataBody.setDeviceBrowser(deviceBrowser);

		Callback callback = new IfMcCs011_I.DataBody.Callback();
		callback.setFailCallbackUrl("");
		callback.setMobileOs("");
		callback.setSuccessCallbackUrl("");
		callback.setTelcoTycd("");
		dataBody.setCallback(callback);

		dataBody.setChannel("");

		IfMcCs011_I ifInputDto = new IfMcCs011_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs011;
		Class<IfMcCs011_O> ifOutputDtoClass = IfMcCs011_O.class;
		IfMcCs011_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String resCode = ifOutputDto.getDataBody().getResCode(); // 응답코드 ("1200":성공, 그외:실퍠)
		String errorMessage = ifOutputDto.getDataBody().getErrorMessage(); // 에러메세지
		if (!"1200".equalsIgnoreCase(resCode)) {
			throw new IfException(HttpStatus.OK, String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", errorMessage, resCode));
		}

		String crtf_RTCD = ifOutputDto.getDataHeader().getCRTF_RTCD(); // 처리결과코드 ("0000":정상, 그외:실패)
		String dlre_MSG = ifOutputDto.getDataHeader().getDLRE_MSG();
		if (!"0000".equalsIgnoreCase(crtf_RTCD)) {
			throw new IfException(HttpStatus.OK, String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", dlre_MSG, crtf_RTCD));
		}

		Mvc011ResDto resDto = modelMapper.map(ifOutputDto, Mvc011ResDto.class);

		ResponseDto<Mvc011ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [12]
	 * API - 간편인증 상태 조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/initechStatus"), (API_URL_TOKEN + "/initechStatus" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "12. 간편인증 상태 조회")
	public @ResponseBody ResponseDto<Mvc012ResDto> initechStatus(@RequestBody Mvc012ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

//		IfMcCs010_I cs010_I = modelMapper.map(reqDto, IfMcCs010_I.class);

		IfMcCs012_I.DataHeader dataHeader = new IfMcCs012_I.DataHeader();
//		dataHeader.setCRTF_RTCD("");
//		dataHeader.setDLRE_MSG("");
		dataHeader.setORGN_CODE(IfConstant.BELN_ORGN_CODE);

		// TODO 확인 필요
//		dataHeader.setSCRN_ID(IfConstant.TRNM_SYS_CODE);
		dataHeader.setSCRN_ID(reqDto.getSCRN_ID());

		dataHeader.setSRVC_ID(IfConstant.SRVC_ID);

		// TODO 확인 필요
		dataHeader.setUSER_ID(reqDto.getUSER_ID());

		IfMcCs012_I.DataBody dataBody = new IfMcCs012_I.DataBody();

		String token_type = reqDto.getToken_type();
		token_type = StringUtils.defaultString(token_type).trim();
		
		String access_token = reqDto.getAccess_token();
		access_token = StringUtils.defaultString(access_token).trim();
				
		String initechOAuthToken = token_type + " " + access_token;
		dataBody.setInitechOAuthToken(initechOAuthToken);

		String reqTxId = reqDto.getReqTxId();
		dataBody.setReqTxId(reqTxId);
		
		dataBody.setOp(IfConstant.EzCertSrvcOp.auth.getValue());

		IfMcCs012_I ifInputDto = new IfMcCs012_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs012;
		Class<IfMcCs012_O> ifOutputDtoClass = IfMcCs012_O.class;
		IfMcCs012_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String resCode = ifOutputDto.getDataBody().getResCode(); // 응답코드 ("1200":성공, 그외:실퍠)
		String errorMessage = ifOutputDto.getDataBody().getErrorMessage(); // 에러메세지
		if (!"1200".equalsIgnoreCase(resCode)) {
			throw new IfException(HttpStatus.OK, String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", errorMessage, resCode));
		}

		String crtf_RTCD = ifOutputDto.getDataHeader().getCRTF_RTCD(); // 처리결과코드 ("0000":정상, 그외:실패)
		String dlre_MSG = ifOutputDto.getDataHeader().getDLRE_MSG();
		if (!"0000".equalsIgnoreCase(crtf_RTCD)) {
			throw new IfException(HttpStatus.OK, String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", dlre_MSG, crtf_RTCD));
		}

		Mvc012ResDto resDto = modelMapper.map(ifOutputDto, Mvc012ResDto.class);

		ResponseDto<Mvc012ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	
	/**
	 * <pre>
	 * [13] 
	 * API - 고객휴대폰번호조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/cstmIntgBscInfrInqr"), (API_URL_TOKEN + "/cstmIntgBscInfrInqr" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "13. 고객휴대폰번호조회")
	public @ResponseBody ResponseDto<Mvc013ResDto> cstmIntgBscInfrInqr(@RequestBody Mvc013ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs013_I ifInputDto = modelMapper.map(reqDto, IfMcCs013_I.class);
		
		if("".equals(StringUtils.defaultString(ifInputDto.getCustId()))) {
			throw new IfException(HttpStatus.OK, "custId 는 필수 입력값 입니다.");
		}
		
		String rrno = ifInputDto.getRrno();
		rrno = StringUtils.defaultString(rrno);
		ifInputDto.setRrno(rrno);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs013;
		Class<IfMcCs013_O> ifOutputDtoClass = IfMcCs013_O.class;
		IfMcCs013_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		
		Mvc013ResDto resDto = modelMapper.map(ifOutputDto, Mvc013ResDto.class);

		String ifOutputHpBsno = ifOutputDto.getHpBsno();
		String ifOutputHpOfno = ifOutputDto.getHpOfno();
		String ifOutputHpInno = ifOutputDto.getHpInno();
		
		resDto.setRegisteredHpNo1(ifOutputHpBsno);
		resDto.setRegisteredHpNo2(ifOutputHpOfno);
		resDto.setRegisteredHpNo3(ifOutputHpInno);
		
		String registeredHpNoMatchYn;
		
		if(
			"".equals(StringUtils.defaultString(reqDto.getHpNo1()))
			&& "".equals(StringUtils.defaultString(reqDto.getHpNo2()))
			&& "".equals(StringUtils.defaultString(reqDto.getHpNo3()))
		) {
			registeredHpNoMatchYn = null;
		}else if(
			ifOutputHpBsno.equals(reqDto.getHpNo1())
			&& ifOutputHpOfno.equals(reqDto.getHpNo2())
			&& ifOutputHpInno.equals(reqDto.getHpNo3())
		) {
			registeredHpNoMatchYn = "Y";
		}else {
			registeredHpNoMatchYn = "N";
		}
		
		resDto.setRegisteredHpNoMatchYn(registeredHpNoMatchYn);
		
		ResponseDto<Mvc013ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	
	/**
	 * <pre>
	 * [14] 
	 * API - SMS 메세지 발송
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/sendSmsMsg"), (API_URL_TOKEN + "/sendSmsMsg" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "14. SMS 메세지 발송")
	public @ResponseBody ResponseDto<Mvc014ResDto> sendSmsMsg(@RequestBody Mvc014ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

//		IfMcCs014_I ifInputDto = modelMapper.map(reqDto, IfMcCs014_I.class);
		/*
		String payloadJson = "{\r\n" + 
		        "    \"sendCont\": 1,\r\n" + 
		        "    \"jobMsgeCntn\": \"인증번호 999를 입력해 주세요.\",\r\n" + 
		        "    \"sendEmnb\": \"1077593\",\r\n" + 
		        "    \"sendEmpeNm\": \"홍길동\",\r\n" + 
		        "    \"sndeDeptCode\": \"00630\",\r\n" + 
		        "    \"sndeDeptNm\": \"보험서비스팀\",\r\n" + 
		        "    \"belnOrgnCode\": \"00630\",\r\n" + 
		        "    \"nttkTmplNm\": \"SZAU000002\",\r\n" + 
		        "    \"trnnPrgmId\": \"111\",\r\n" + 
		        "    \"bswrNm\": \"모바일 화상상담 서비스 문자인증\",\r\n" + 
		        "    \"rcveCnfmYn\": \"Y\",\r\n" + 
		        "    \"custId\": \"0000099995\",\r\n" + 
		        "    \"hpTlphSbno\": \"3661\",\r\n" + 
		        "    \"hpTlphOfno\": \"2889\",\r\n" + 
		        "    \"hpTlphTlcmNo\": \"010\",\r\n" + 
		        "    \"sndeTlphArcd\": \"\",\r\n" + 
		        "    \"sndeTlphOfno\": \"1588\",\r\n" + 
		        "    \"sndeTlphInno\": \"6363\",\r\n" + 
		        "    \"ntfcKindCode\": \"ZAU0008\",\r\n" + 
		        "    \"rcvrNm\": \"임꺽정\"\r\n" + 
		        "}";
		*/
		
		String authenticationNumber = reqDto.getAuthenticationNumber();
		String jobMsgeCntn = String.format(IfConstant.SEND_SMS_MSG_JOB_MSGE_CNTN, authenticationNumber);
		
		IfMcCs014_I ifInputDto = new IfMcCs014_I();
		ifInputDto.setApctInfoCntn(null);
		ifInputDto.setBelnOrgnCode(IfConstant.BELN_ORGN_CODE);
		ifInputDto.setBswrNm(IfConstant.SEND_SMS_MSG_BSWR_NM);
		ifInputDto.setCmntCntn(null);
		// XXX
		ifInputDto.setCustId(reqDto.getCustId());
		ifInputDto.setCustTlno(null);
		// XXX
		ifInputDto.setHpTlphOfno(reqDto.getHpTlphOfno());
		// XXX
		ifInputDto.setHpTlphSbno(reqDto.getHpTlphSbno());
		// XXX
		ifInputDto.setHpTlphTlcmNo(reqDto.getHpTlphTlcmNo());
		// XXX
		ifInputDto.setJobMsgeCntn(jobMsgeCntn);
		ifInputDto.setJoinTrgtPolyNo(null);
		ifInputDto.setLoanNo(null);
		ifInputDto.setMailTmplNm(null);
		ifInputDto.setNtfcKindCode(IfConstant.SEND_SMS_MSG_NTFC_KIND_CODE);
		ifInputDto.setNttkTmplNm(IfConstant.SEND_SMS_MSG_NTTK_TMPL_NM);
		ifInputDto.setRcveCnfmYn("Y");
		// XXX
		ifInputDto.setRcvrNm(reqDto.getRcvrNm());
		ifInputDto.setSbsnSendRsvtTme(null);
		ifInputDto.setSendCont(BigInteger.valueOf(1));
		// XXX
		ifInputDto.setSendEmnb(reqDto.getEmnb());
		// XXX
		ifInputDto.setSendEmpeNm(reqDto.getSendEmpeNm());
		ifInputDto.setSndeDeptCode(IfConstant.BELN_ORGN_CODE);
		ifInputDto.setSndeDeptNm("보험서비스팀");
		ifInputDto.setSndeMailAddr(null);
		ifInputDto.setSndeTlphArcd("");
		ifInputDto.setSndeTlphOfno("1588");
		ifInputDto.setSndeTlphInno("6363");
		// XXX
		ifInputDto.setTrnnPrgmId("MVC010101");

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs014;
		Class<IfMcCs014_O> ifOutputDtoClass = IfMcCs014_O.class;
		IfMcCs014_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		
		Mvc014ResDto resDto = modelMapper.map(ifOutputDto, Mvc014ResDto.class);
		
		String dvsnRsltValYn = Double.compare(resDto.getDvsnRsltVal(), 0.0) == 0 ? "Y" : "N";
		resDto.setDvsnRsltValYn(dvsnRsltValYn);
		
		ResponseDto<Mvc014ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	

	
	/**
	 * <pre>
	 * [15]
	 * API - 카카오알림톡발송_챗버블
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/cspdDvlmUmsSendMgmt"), (API_URL_TOKEN + "/cspdDvlmUmsSendMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "15. 카카오알림톡발송_챗버블")
	public @ResponseBody ResponseDto<Mvc015ResDto> cspdDvlmUmsSendMgmt(@RequestBody Mvc015ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		String rcvrNm = reqDto.getRcvrNm();

		String url_mobile = reqDto.getUrl_mobile();
//		url_mobile = "https://" + url_mobile.replaceAll("^http://|^https://", "");

		String jobMsgeCntn = String.format(IfConstant.JOB_MSGE_CNTN, rcvrNm);
		String sbsnSendMsgeCntn = String.format(IfConstant.SBSN_SEND_MSGE_CNTN, rcvrNm, url_mobile);

		IfMcCs015_I.NttkButnCntn.Button button = new IfMcCs015_I.NttkButnCntn.Button();

		button.setName(IfConstant.NTTK_BUTN_CNTN_BUTTON_NAME);
		button.setTarget(IfConstant.NTTK_BUTN_CNTN_BUTTON_TARGET);
		button.setType(IfConstant.NTTK_BUTN_CNTN_BUTTON_TYPE);

		button.setUrl_mobile(url_mobile);
		button.setUrl_pc("");

		List<Button> buttonList = new ArrayList<>();
		buttonList.add(button);

		IfMcCs015_I.NttkButnCntn nttkButnCntn = new IfMcCs015_I.NttkButnCntn();
		nttkButnCntn.setButton(buttonList);

		IfMcCs015_I ifInputDto = new IfMcCs015_I();
		ifInputDto.setBtchPrcsYn("1"); // (1:Y), (7:N)
		ifInputDto.setButnDvsnCode("2"); // (1:Fomrmat String, 2:JSON, 3:XML)
		ifInputDto.setCustId(reqDto.getCustId());
		ifInputDto.setDutySendYn("Y");	// 의무발송(고객 핸드폰 수신여부 무시)
		ifInputDto.setHpTlphOfno(reqDto.getHpTlphOfno());
		ifInputDto.setHpTlphSbno(reqDto.getHpTlphSbno());
		ifInputDto.setHpTlphTlcmNo(reqDto.getHpTlphTlcmNo());
		ifInputDto.setJobMsgeCntn(jobMsgeCntn);
		ifInputDto.setMsgeTitlNm(IfConstant.MSGE_TITL_NM);
		ifInputDto.setNtfcKindCode(IfConstant.NTFC_KIND_CODE);
		ifInputDto.setNtfcMdiaDvsnCode(IfConstant.NTFC_MDIA_DVSN_CODE);
		ifInputDto.setNtfcTmplCode(IfConstant.NTFC_TMPL_CODE);
		ifInputDto.setNttkButnCntn(nttkButnCntn);
		ifInputDto.setOnlnBtchDvsnCode(IfConstant.ONLN_BTCH_DVSN_CODE);
		ifInputDto.setRcvrNm(rcvrNm);
		ifInputDto.setRcvrTlno("");
		ifInputDto.setSbsnSendMsgeCntn(sbsnSendMsgeCntn);
		ifInputDto.setSbsnSendYn("Y");
		ifInputDto.setSendCont(1);
		ifInputDto.setSendRsvtDttm(null);
		ifInputDto.setSndeDeptCode(IfConstant.BELN_ORGN_CODE);
		ifInputDto.setSndeTlno("");
		ifInputDto.setSndeTlphArcd("");
		ifInputDto.setSndeTlphOfno("1588");
		ifInputDto.setSndeTlphInno("6363");
		ifInputDto.setTmplCode("");
		ifInputDto.setTrnnPrgmId(null);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015;
		Class<IfMcCs015_O> ifOutputDtoClass = IfMcCs015_O.class;
		IfMcCs015_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		double dvsnRsltVal = ifOutputDto.getDvsnRsltVal();
		int smsTrnmRsltVal = ifOutputDto.getSmsTrnmRsltVal();

		if (0.0 != dvsnRsltVal) {
			throw new IfException(HttpStatus.OK, "알림톡 발송에 실패했습니다.");
		} else if (0 != smsTrnmRsltVal) {
			throw new IfException(HttpStatus.OK, "SMS 발송에 실패했습니다.");
		}

		Mvc015ResDto resDto = modelMapper.map(ifOutputDto, Mvc015ResDto.class);
		
		String dvsnRsltValYn = Double.compare(resDto.getDvsnRsltVal(), 0.0) == 0 ? "Y" : "N";
		resDto.setDvsnRsltValYn(dvsnRsltValYn);

		ResponseDto<Mvc015ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [16]
	 * API - 대체키별연락처저장
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/prsnCustMgmt"), (API_URL_TOKEN + "/prsnCustMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "16. 대체키별연락처저장")
	public @ResponseBody ResponseDto<Mvc016ResDto> prsnCustMgmt(@RequestBody Mvc016ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs016_I ifInputDto = modelMapper.map(reqDto, IfMcCs016_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;
		Class<IfMcCs016_O> ifOutputDtoClass = IfMcCs016_O.class;
		IfMcCs016_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		Mvc016ResDto resDto = modelMapper.map(ifOutputDto, Mvc016ResDto.class);

		ResponseDto<Mvc016ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [17]
	 * API - 대체키별연락처조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/cnplSbsnKeyMgmt"), (API_URL_TOKEN + "/cnplSbsnKeyMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "17. 대체키별연락처조회")
	public @ResponseBody ResponseDto<Mvc017ResDto> cnplSbsnKeyMgmt(@RequestBody Mvc017ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs017_I ifInputDto = modelMapper.map(reqDto, IfMcCs017_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs017;
		Class<IfMcCs017_O> ifOutputDtoClass = IfMcCs017_O.class;
		IfMcCs017_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		/*
		if (ifOutputDto.getCnplSuid() == null) {
			throw new IfException("조회된 연락처가 없습니다.");
		}
		*/
		Mvc017ResDto resDto = modelMapper.map(ifOutputDto, Mvc017ResDto.class);

		ResponseDto<Mvc017ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	
	/**
	 * <pre>
	 * [18]
	 * API - 우편번호조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/addrMgmt"), (API_URL_TOKEN + "/addrMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "18. 우편번호조회")
	public @ResponseBody ResponseDto<Mvc018ResDto> addrMgmt(@RequestBody Mvc018ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs018_I ifInputDto = modelMapper.map(reqDto, IfMcCs018_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs018;
		Class<IfMcCs018_O> ifOutputDtoClass = IfMcCs018_O.class;
		IfMcCs018_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		/*
		if (ifOutputDto.getPscdList() == null || ifOutputDto.getPscdList().size() == 0) {
			throw new IfException("조회된 우편번호가 없습니다.");
		}
		*/

		if (ifOutputDto.getPscdList() == null) {
			ifOutputDto.setPscdList(new ArrayList<>());
		}

		Mvc018ResDto resDto = modelMapper.map(ifOutputDto, Mvc018ResDto.class);

		ResponseDto<Mvc018ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	

	
	
	/**
	 * <pre>
	 * [19] 
	 * API - 신분증OCR요청
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/idcdOcrRqst"), (API_URL_TOKEN + "/idcdOcrRqst" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK + "/{idcdCase}") }, method = { RequestMethod.POST }, name = "19. 신분증OCR요청")
	public @ResponseBody ResponseDto<Mvc019ResDto> idcdOcrRqst(@RequestBody Mvc019ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
		IfMcCs019_I cs019_I = modelMapper.map(reqDto, IfMcCs019_I.class);

		IfMcCs019_I.DataHeader dataHeader = new IfMcCs019_I.DataHeader();

		dataHeader.setSRVC_ID(IfConstant.SRVC_ID);

		// TODO 확인 필요
//		dataHeader.setSCRN_ID(IfConstant.TRNM_SYS_CODE);
		dataHeader.setSCRN_ID(reqDto.getSCRN_ID());

		dataHeader.setX_OCR_SECRET(ocrSecretKey);

//		dataHeader.setCRTF_RTCD("");

//		dataHeader.setDLRE_MSG("");

		com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs019_I.DataBody.Image image = new IfMcCs019_I.DataBody.Image();

		String imageFormat = reqDto.getFormat();
		image.setFormat(imageFormat);

		String imageBase64Data = reqDto.getData();
		String regexBase64prefix = "^.+\\,";
		String refinedImageBase64Data = imageBase64Data.replaceAll(regexBase64prefix, "");
		image.setData(refinedImageBase64Data);

		String imageName = reqDto.getName();
		image.setName(imageName);

		List<com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs019_I.DataBody.Image> imageList = new ArrayList<>();
		imageList.add(image);

		IfMcCs019_I.DataBody dataBody = new IfMcCs019_I.DataBody();

		dataBody.setORGN_CODE(IfConstant.BELN_ORGN_CODE);

		String userId = reqDto.getUSER_ID();
		dataBody.setUSER_ID(userId);

		dataBody.setImages(imageList);

		IfMcCs019_I ifInputDto = new IfMcCs019_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);


		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs001;	// 신분증OCR요청 인터페이스
		Class<IfMcCs019_O> ifOutputDtoClass = IfMcCs019_O.class;
		IfMcCs019_O ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String ocrResultJson = ifOutputDto.getDataBody().getImages();
		log.info("[OCR RESULT] : {}", ocrResultJson);
		
		if(ocrResultJson == null || "".equals(ocrResultJson)) {
			String message = String.format("신분증 OCR 결과가 %s 입니다.", ocrResultJson);
			throw new IfException(HttpStatus.OK, message);
		}

		JsonNode ocrResultReadTrees = objectMapper.readTree(ocrResultJson);

		if (ocrResultReadTrees.size() != 1) {
			String message = String.format("신분증 OCR 결과가 1건이 아닙니다. (%d건)", ocrResultReadTrees.size());
			throw new IfException(HttpStatus.OK, message);
		}

		JsonNode ocrResultReadTree = ocrResultReadTrees.get(0);

		if (!IfConstant.OcrInferResult.SUCCESS.getResultValue().equals(ocrResultReadTree.get("inferResult").asText())) {
			String message = ocrResultReadTree.get("message").asText();
			message = String.format("이미지 인식을 실패했습니다." + System.lineSeparator() + "(message : %s)", message);
			throw new IfException(HttpStatus.OK, message);
		}

//		Mvc019ResDto resDto = modelMapper.map(ifOutputDto, Mvc019ResDto.class);
		Mvc019ResDto resDto = new Mvc019ResDto();
		
		// 공통
		String idType = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();
		String custNm = this.getCustNm(ocrResultReadTree);
		String custGender = this.getSex(ocrResultReadTree);
		String btdt = this.getBtdt(ocrResultReadTree);
		String isncDate = this.getIsncDate(ocrResultReadTree);
		String expyDate = this.getExpyDate(ocrResultReadTree);
		
		resDto.setIdType(idType);
		resDto.setCustNm(custNm);
		resDto.setCustGender(custGender);
		resDto.setCustBirthDate(btdt);
		resDto.setIsncDate(isncDate);
		resDto.setExpyDate(expyDate);

		
		// A. 주민등록증 경우
		if (idType.equals(IfConstant.OcrIdType.IdCard.getName())) {
			try {
				String rrno = ocrResultReadTree.get("idCard").get("result").get("ic").get("personalNum").get(0).get("text").asText();
				rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
				resDto.setRrno(rrno);
			}catch (RuntimeException e) {
				log.warn("rrno (idCard.result.ic.personalNum[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}
			
		// B. 운전면허증 경우
		} else if (idType.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			try {
				String driverLicenseNumber = ocrResultReadTree.get("idCard").get("result").get("dl").get("num").get(0).get("text").asText();
				driverLicenseNumber = driverLicenseNumber.replaceAll(DRIVER_LICENSE_NUMBER_DELIMITER, "");
				
				driverLicenseNumber = driverLicenseNumber.replaceAll("\\s+", "");
				
				resDto.setDrvnLcnsNo(driverLicenseNumber);
			}catch (RuntimeException e) {
				log.warn("drvnLcnsNo (idCard.result.dl.num[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}

			try {
				String driverLicenseSequenceNumber = ocrResultReadTree.get("idCard").get("result").get("dl").get("code").get(0).get("text").asText();
				resDto.setDrvnLcnsSqno(driverLicenseSequenceNumber);
			}catch (RuntimeException e) {
				log.warn("drvnLcnsSqno (idCard.result.dl.code[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}
			
			try {
				String rrno = ocrResultReadTree.get("idCard").get("result").get("dl").get("personalNum").get(0).get("text").asText();
				rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
				resDto.setRrno(rrno);
			}catch (RuntimeException e) {
				log.warn("rrno (idCard.result.dl.personalNum[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}

			
		// C. 여권 경우
		} else if (idType.equals(IfConstant.OcrIdType.Passport.getName())) {
			try {
				String passportNumber = ocrResultReadTree.get("idCard").get("result").get("pp").get("num").get(0).get("text").asText();
				resDto.setPsprNo(passportNumber);
			}catch (RuntimeException e) {
				log.warn("psprNo (idCard.result.pp.num[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}
			
			try {
				String personalNum = ocrResultReadTree.get("idCard").get("result").get("pp").get("personalNum").get(0).get("text").asText();
				String rrno = btdt.substring(2) + personalNum;
				resDto.setRrno(rrno);
			}catch (RuntimeException e) {
				log.warn("rrno (idCard.result.pp.personalNum[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}
			
		// D. 외국인등록증 경우
		} else if (idType.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			try {
				String alienRegistrationNumber = ocrResultReadTree.get("idCard").get("result").get("ac").get("alienRegNum").get(0).get("text").asText();
				resDto.setFrnrRgstNo(alienRegistrationNumber);
			}catch (RuntimeException e) {
				log.warn("frnrRgstNo (idCard.result.ac.alienRegNum[0].text) 를 얻는데 실패했습니다. \"\"로 초기화 합니다.");
			}
		}

		ResponseDto<Mvc019ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	
	
	
	
	/**
	 * <pre>
	 * [20]
	 * API - 진입
	 * 
	 * [02, 09, 03]
	 *	02.진위확인결과조회
	 *	09.개인정보유출노출여부조회
	 *	03.신분증스캔후처리
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/entry"), (API_URL_TOKEN + "/entry" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "20. 진입")
	public @ResponseBody ResponseDto<Mvc020ResDto> entry(@RequestBody Mvc020ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException, ParseException {
		
		Mvc020ResDto resDto = new Mvc020ResDto();

		String idType = reqDto.getIdType();
		String trflCnfmDvsnCode = this.getTrflCnfmDvsnCodeFromIdType(idType);

		String custNm = reqDto.getCustNm();
//		String btdt = reqDto.getBtdt();
//		String btdt = null;
		String isncDate = reqDto.getIsncDate();
		String expyDate = reqDto.getExpyDate();
		

		// XXX [02] 진위확인결과조회
		Mvc002ReqDto mvc002ReqDto = new Mvc002ReqDto();

		mvc002ReqDto.setEmnb(reqDto.getEmnb());

		mvc002ReqDto.setTrflCnfmDvsnCode(trflCnfmDvsnCode);

		mvc002ReqDto.setTrflCnfmBswrDvsnCode(TrflCnfmBswrDvsnCode.other.getCode());

		mvc002ReqDto.setTrflCnfmChnlCode(IfConstant.TrflCnfmChnlCode.INDIVIDUAL.getCode());

		mvc002ReqDto.setPrcsBswrScrnId(reqDto.getSCRN_ID());

		mvc002ReqDto.setTrflCnfmJobCode(TrflCnfmJobCode.CUSTOMER_IDENTIFICATION.getCode());

		mvc002ReqDto.setCustId("");

		mvc002ReqDto.setMgmtNo("");
		mvc002ReqDto.setCustNm(custNm);
//		mvc002ReqDto.setBtdt(this.transformDate(btdt));
//		mvc002ReqDto.setBtdt(btdt);
		
//		mvc002ReqDto.setIsncDate(this.transformDate(isncDate));
		mvc002ReqDto.setIsncDate(isncDate);
		mvc002ReqDto.setExpyDate(expyDate);
		
		
		String rrno = StringUtils.defaultString(reqDto.getRrno());
		rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
		mvc002ReqDto.setRrno(rrno);
		
//		String btdt = this.getBirthYyyyMMddFromPersonalNum(rrno);
//		mvc002ReqDto.setBtdt(btdt);
		

		if (idType.equals(IfConstant.OcrIdType.IdCard.getName())) {
//			String rrno = StringUtils.defaultString(reqDto.getRrno());
//			rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
//			mvc002ReqDto.setRrno(rrno);
			
			String btdt = this.getBirthYyyyMMddFromPersonalNum(rrno);
			mvc002ReqDto.setBtdt(btdt);
			
		} else if (idType.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String driverLicenseNumber = StringUtils.defaultString(reqDto.getDrvnLcnsNo());
			
			driverLicenseNumber = driverLicenseNumber.replaceAll(DRIVER_LICENSE_NUMBER_DELIMITER, "");
			driverLicenseNumber = driverLicenseNumber.replaceAll("\\s+", "");
			
			String driverLicenseSequenceNumber = reqDto.getDrvnLcnsSqno();
			
			mvc002ReqDto.setDrvnLcnsSqno(driverLicenseSequenceNumber);
			mvc002ReqDto.setDrvnLcnsNo(driverLicenseNumber);
			
//			String rrno = reqDto.getRrno();
//			rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
//			mvc002ReqDto.setRrno(rrno);
			
			String btdt = this.getBirthYyyyMMddFromPersonalNum(rrno);
			mvc002ReqDto.setBtdt(btdt);
			
		} else if (idType.equals(IfConstant.OcrIdType.Passport.getName())) {
			String passportNumber = StringUtils.defaultString(reqDto.getPsprNo());

			mvc002ReqDto.setPsprNo(passportNumber);
			/*
			String btdt = reqDto.getBtdt();
			mvc002ReqDto.setBtdt(btdt);
			*/
			
			String btdt = this.getBirthYyyyMMddFromPersonalNum(rrno);
			mvc002ReqDto.setBtdt(btdt);
			
		} else if (idType.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			String alienRegistrationNumber = StringUtils.defaultString(reqDto.getFrnrRgstNo());

			mvc002ReqDto.setFrnrRgstNo(alienRegistrationNumber);
			
			String btdt = this.getBirthYyyyMMddFromPersonalNum(alienRegistrationNumber);
			mvc002ReqDto.setBtdt(btdt);
		}

		ResponseDto<Mvc002ResDto> response002dto = this.trflCnfm(mvc002ReqDto, request);
		
		Mvc002ResDto mvc002ResDto = response002dto.getData();
		
		if("".equals(StringUtils.defaultString(mvc002ResDto.getCustId()))) {
			throw new IfException(HttpStatus.OK, "진위확인서비스에서 조회된 고객아이디가 없습니다.");
		}
		
		String csnsYn = mvc002ResDto.getCsnsYn();
		resDto.setCustInfoAuthenticityYn(csnsYn);

		String trflCnfmRsltVal = mvc002ResDto.getTrflCnfmRsltVal();
		resDto.setCustInfoAuthenticityResultCode(trflCnfmRsltVal);

		String rsltMsgeCntn = mvc002ResDto.getRsltMsgeCntn();
		resDto.setCustInfoAuthenticityResultMessage(rsltMsgeCntn);

		String custId = mvc002ResDto.getCustId();
		resDto.setCustId(custId);

		// XXX [09] 개인정보유출노출여부조회
		Mvc009ReqDto mvc009ReqDto = new Mvc009ReqDto();
		mvc009ReqDto.setEmnb(reqDto.getEmnb());
		mvc009ReqDto.setCustId(mvc002ResDto.getCustId());

		ResponseDto<Mvc009ResDto> response009dto = this.prsnInfoLeakMgmt(mvc009ReqDto, request);
		Mvc009ResDto mvc009ResDto = response009dto.getData();

		String leakYn = mvc009ResDto.getLeakYn();
		String epsrYn = mvc009ResDto.getEpsrYn();
		String hpnoChngYn = mvc009ResDto.getHpnoChngYn();

		resDto.setCustPersonalInfoleakYn(leakYn);
		resDto.setCustPersonalInfoEpsrYn(epsrYn);
		resDto.setCustHpnoChngYn(hpnoChngYn);

		// [03] 신분증스캔후처리
		Mvc003ReqDto mvc003ReqDto = new Mvc003ReqDto();
		mvc003ReqDto.setCsnsYn(mvc002ResDto.getCsnsYn());
		mvc003ReqDto.setCustId(mvc002ResDto.getCustId());
		mvc003ReqDto.setEmnb(reqDto.getEmnb());

		ResponseDto<Mvc003ResDto> response003dto = this.itfcIdcdScan(mvc003ReqDto, request);
		Mvc003ResDto mvc003ResDto = response003dto.getData();
		String prcsSucsYn = mvc003ResDto.getPrcsSucsYn();

		resDto.setSingleViewOpenMessagePushSuccessYn(prcsSucsYn);

		ResponseDto<Mvc020ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	/**
	 * <pre>
	 * [23]
	 * API - 휴일목록조회
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/hldyInfoMgmt"), (API_URL_TOKEN + "/hldyInfoMgmt" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "23. 휴일목록조회")
	public @ResponseBody ResponseDto<Mvc023ResDto> hldyInfoMgmt(@RequestBody Mvc023ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
		if(reqDto.getStndYymm() == null) {
			String message = "파라미터 stndYymm 값이 없습니다.";
			throw new IfException(HttpStatus.OK, message);
		}
		
		int stndYymmLength = reqDto.getStndYymm().length();
		if(stndYymmLength != 4 && stndYymmLength != 6) {
			String message = "stndYymm 값이 \"yyyy\" 또는 \"yyyyMM\" 형식이 아닙니다.";
			throw new IfException(HttpStatus.OK, message);
		}

		IfMcCs023_I ifInputDto = modelMapper.map(reqDto, IfMcCs023_I.class);

		String emnb = reqDto.getEmnb();
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs023;
		Class<IfMcCs023_O> ifOutputDtoClass = IfMcCs023_O.class;
		
		IfMcCs023_O ifOutputDto = null;
		
		List<DateInfo> pagingDateInfoList = null;
		List<DateInfo> totalDateInfoList = new ArrayList<>();
		
		if(stndYymmLength == 4) {
			for(int i = 1; i <= 12; i++) {
				String yyyy = reqDto.getStndYymm();
				String MM = StringUtils.leftPad(String.valueOf(i), 2, "0");
				
				String yyyyMM = yyyy + MM;
				ifInputDto.setStndYymm(yyyyMM);
				
				ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
				pagingDateInfoList = ifOutputDto.getDateInfoList();
				pagingDateInfoList = this.filterClosedDays(pagingDateInfoList);
				
				if(pagingDateInfoList != null) {
					totalDateInfoList.addAll(pagingDateInfoList);
				}
			}
		}else if(stndYymmLength == 6) {
			
			ifOutputDto = grmExternalBackboneService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
			pagingDateInfoList = ifOutputDto.getDateInfoList();
			pagingDateInfoList = this.filterClosedDays(pagingDateInfoList);
			
			if(pagingDateInfoList != null) {
				totalDateInfoList.addAll(pagingDateInfoList);
			}
		}
		
		ifOutputDto.setDateInfoList(totalDateInfoList);
		
		Mvc023ResDto resDto = modelMapper.map(ifOutputDto, Mvc023ResDto.class);

		ResponseDto<Mvc023ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	
	/**
	 * <pre>
	 * [99]
	 * API - 이미지 시스템 등록
	 * </pre>
	 * @param reqDto 요청 DTO 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/edmsRgstr"), (API_URL_TOKEN + "/edmsRgstr" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "99. 이미지 시스템 등록")
	public @ResponseBody ResponseDto<Mvc999ResDto> edmsRgstr(@ModelAttribute Mvc999ReqDto reqDto, HttpServletRequest request) throws URISyntaxException, IOException, IllegalArgumentException, IllegalAccessException{
		
		Map<String,Object> mapForLogging = new LinkedHashMap<>();
		
		final String multipartFileFieldName = "file";	// reqDto 의 MultipartFile 타입 필드 이름 
		
		for (Field field : reqDto.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			String fieldName = field.getName();
			
			if(fieldName.equalsIgnoreCase(multipartFileFieldName)) {
				continue;
			}
			Object fieldValue = field.get(reqDto);
			mapForLogging.put(fieldName, fieldValue);
		} 
		log.info("[IMAGE-SYSTEM-REQUET-PARAM] : {}", objectMapper.writeValueAsString(mapForLogging));
		
		MultipartFile file = reqDto.getFile();
		String originalFilename = file.getOriginalFilename();
		
		IfMcCs999_I imgSysInput = new IfMcCs999_I();
		imgSysInput.setAcfmAltrYn(null);
		imgSysInput.setAppnJdgnTypeVal(null);
		imgSysInput.setBncaAcfmYn(null);
		
		imgSysInput.setBswrvsnCode(reqDto.getBswrvsnCode());
		imgSysInput.setCntcBefrObdsMatrYn(null);
		imgSysInput.setDcfmBrcd(null);
		imgSysInput.setDcfmCode(reqDto.getDcfmCode());
		imgSysInput.setDcmtTypeCode(reqDto.getDcmtTypeCode());
		imgSysInput.setFile(file);
		imgSysInput.setFileNm(originalFilename);
		imgSysInput.setImgeDocuNo(reqDto.getImgeDocuNo());
		imgSysInput.setImgePrefixVal(null);
		imgSysInput.setOgtxFileNm(null);
		imgSysInput.setSysCode(IfConstant.SYS_CODE_FOR_SUBMIT_TO_IMG_SYS);
		imgSysInput.setUserId(IfConstant.USER_ID_FOR_SUBMIT_TO_IMG_SYS);
		
		IfMcCs999_O imgSysOutput = grmExternalBackboneService.rgstrImgSys(imgSysInput);
		
		Mvc999ResDto resDto = modelMapper.map(imgSysOutput, Mvc999ResDto.class);
		/*
		Result result = null;
		if("Y".equalsIgnoreCase(resDto.getRsltYn())) {
			result = Result.SUCCESS;
		}else {
			result = Result.FAIL;
		}
		ResponseDto<Mvc999ResDto> responseDto = new ResponseDto<>(result, HttpStatus.OK, resDto);
		*/
		if(!"Y".equalsIgnoreCase(resDto.getRsltYn())) {
			String failDataJson = objectMapper.writeValueAsString(resDto);
			String message = String.format("이미지 제출에 실패했습니다. (상세정보 : %s)", failDataJson);
			throw new IfException(HttpStatus.OK, message);
		}
		
		ResponseDto<Mvc999ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
		
		return responseDto;
	}
	
	
	
	
	/**
	 * ControllerAdvice 에 Exception 을 throw 하기 위한 컨트롤러 메서드
	 * @param request HttpServletRequest 객체
	 * @throws Exception
	 */
	@RequestMapping(path = { EXCEPTION_CONTROLLER_PATH })
	public void exception(HttpServletRequest request) throws Exception {
		Exception exception = (Exception) request.getAttribute(EXCEPTION_ATTRIBUTE_NAME);
		throw exception;
	}

	

	/**
	 * <pre>
	 * [가]
	 * INTERFACE PROVIDER - 구루미 입장 URI 발급요청
	 * </pre>
	 * @param inputTelegram 요청 IfTelegram 객체
	 * @param request HttpServletRequest 객체
	 * @return 응답 IfTelegram 객체
	 * @throws URISyntaxException
	 */
	@RequestMapping(path = { (IF_PROVIDER_FOR_GRM_SERVICE_URL_TOKEN + "/otp"), (IF_PROVIDER_FOR_GRM_SERVICE_URL_TOKEN + "/otp" + MockUtil.REQUEST_URI_SUFFIX_FOR_MOCK) }, name = "[가]. 인터페이스 PROVIDER - 구루미 입장 URI 발급요청")
	public @ResponseBody IfTelegram<Res001Dto> counsellingOtp(@RequestBody IfTelegram<Req001Dto> inputTelegram, HttpServletRequest request) throws URISyntaxException {
		
//		log.debug("[counsellingOtp] : {}", objectMapper.writeValueAsString(inputTelegram));
		
		Req001Dto inputTelegramPayload = inputTelegram.getPayload();
//		OtpDto_I dto_I = modelMapper.map(inputTelegramPayload, OtpDto_I.class);
		Grm001Dto_I dto_I = new Grm001Dto_I();
		dto_I.setUsername(inputTelegramPayload.getCustNm());
		dto_I.setApiUserId(inputTelegramPayload.getCustId());
		
		dto_I.setPhoneNumber1(inputTelegramPayload.getHpTlphTlcmNo());
		dto_I.setPhoneNumber2(inputTelegramPayload.getHpTlphOfno());
		dto_I.setPhoneNumber3(inputTelegramPayload.getHpTlphSbno());
		
		dto_I.setRequestAgency(inputTelegramPayload.getRqstOrgnCode());
		
		IfProviderResponseCommonDto<String> dto_O;
		
		try {
			dto_O = grmExternalBackboneService.counsellingOtp(dto_I);
		} catch (URISyntaxException | IOException | RuntimeException e) {
			IfProviderResponseCommonDto<String> exceptionDto = new IfProviderResponseCommonDto<String>();
			exceptionDto.setCode("RT_FAIL");
			exceptionDto.setMessage(e.getMessage());
			exceptionDto.setData("");
			
			dto_O = exceptionDto;
		}
		
		Res001Dto otpResDto = new Res001Dto();
		otpResDto.setDvsnVal(dto_O.getCode());
		otpResDto.setRsltMsgeCntn(dto_O.getMessage());
		otpResDto.setRsltDatVal(dto_O.getData());
		
		IfTelegram outputTelegram = modelMapper.map(inputTelegram, IfTelegram.class);
		IfTelegramHeader outputTelegramHeader = outputTelegram.getHeader();
		
		List<IfTelegramHeaderResponseMessage> msgeList = new ArrayList<>();
		
		String prcsRsltDvsnCode = "0";
		if(!"RT_SUCCESS".equalsIgnoreCase(dto_O.getCode())) {
			prcsRsltDvsnCode = "1";
			
			IfTelegramHeaderResponseMessage ifTelegramHeaderResponseMessage = new IfTelegramHeaderResponseMessage();
			ifTelegramHeaderResponseMessage.setMsgeCntn(dto_O.getMessage());	
			ifTelegramHeaderResponseMessage.setMsgeCode(outputTelegramHeader.getTrnmSysCode() + "E" + "0001");
			ifTelegramHeaderResponseMessage.setMsgeOtptDvsnCode("");;
			
			msgeList.add(ifTelegramHeaderResponseMessage);
		}
		
		outputTelegramHeader.setMsgeList(msgeList);
		outputTelegramHeader.setMsgeListCont(msgeList.size());
		
		outputTelegramHeader.setPrcsRsltDvsnCode(prcsRsltDvsnCode);
		
		outputTelegram.setPayload(otpResDto);
		
		return outputTelegram;
	}
	
	
	
	/**
	 * [A]
	 * API - 인증서비스ID 목록 조회
	 * @return 인증서비스ID 목록
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/pids") }, method = { RequestMethod.POST }, name = "A. 인증서비스ID")
	public @ResponseBody List<Map<String, String>> getPids() {

		List<Map<String, String>> ezCertSrvcIdList = new ArrayList<Map<String, String>>();

		EzCertSrvcId[] ezCertSrvcIds = IfConstant.EzCertSrvcId.values();
		for (EzCertSrvcId ezCertSrvcId : ezCertSrvcIds) {
			Map<String, String> map = new HashMap<>();
			map.put("name", ezCertSrvcId.getName());
			map.put("value", ezCertSrvcId.getValue());
			ezCertSrvcIdList.add(map);
		}

		return ezCertSrvcIdList;
	}

	
	/**
	 * <pre>
	 * API 테스트 화면을 클라이언트에 반환한다.
	 * </pre>
	 * @param model Model 객체
	 * @return API 테스트 화면
	 * @throws IOException
	 */
	@RequestMapping(path = { "/test/apis" }, method = { RequestMethod.GET })
	public String getApiTestView(Model model) throws IOException {

		model.addAttribute("apiAuthEnabled", apiAuthEnabled);
		model.addAttribute("apiAuthKey", apiAuthKey);
		model.addAttribute("apiInfoList", this.getApiInfoList());
		model.addAttribute("idCardMockImageInfoList", this.getIdCardMockImageInfoList());
		model.addAttribute("urlForRequestMockData", URI_FOR_REQUEST_MOCK_DATA);
		model.addAttribute("paramNameForRequestMockData", PARAM_NAME_FOR_REQUEST_MOCK_DATA);
		model.addAttribute("apiTestPageEnabled", apiTestPageEnabled);

		return "test/apis";
	}

	
	
	/**
	 * <pre>
	 * 모조 요청 데이터를 반환한다.
	 * </pre>
	 * @param map 요청 파라미터
	 * @return API 모조 요청 데이터
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(path = { URI_FOR_REQUEST_MOCK_DATA }, method = { RequestMethod.GET })
	public @ResponseBody String getApiTestRequestMockData(@RequestParam MultiValueMap<String, String> map) throws ClassNotFoundException, IOException {
		String mockData = null;

		String firstParamValue = map.getFirst(PARAM_NAME_FOR_REQUEST_MOCK_DATA);
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if (requestMapping != null) {
				String[] paths = requestMapping.path();
				for (String path : paths) {
					if (path.equalsIgnoreCase(firstParamValue)) {
						String methodName = method.getName();

						Parameter[] parameters = method.getParameters();
						for (Parameter parameter : parameters) {
							RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
							ModelAttribute modelAttribute = parameter.getAnnotation(ModelAttribute.class);
							if (requestBody != null || modelAttribute != null) {
								ObjectMapper objectMapper = new ObjectMapper();

								Class<?> parameterType = null;
								
								if(path.startsWith(API_URL_TOKEN)) {
									parameterType = parameter.getType();
									Object mockRequestDataObject = MockUtil.getMockRequestData(methodName, parameterType, null);
									Map<String, Object> mockRequestDataMap = objectMapper.convertValue(mockRequestDataObject,
											new TypeReference<Map<String, Object>>() {
											});
									
										mockRequestDataMap.put("emnb", "1077123");

									mockData = objectMapper.writeValueAsString(mockRequestDataMap);
								} else {
									String typeName = parameter.getParameterizedType().getTypeName();
									String typeParameterClassName = CommonUtil.extractTypeParameterClassName(typeName);
									parameterType = Class.forName(typeParameterClassName);
									
									IfTelegram<Map> ifRequestTelegram = MockUtil.getMockRequestIfTelegram(methodName, null);
									mockData = objectMapper.writeValueAsString(ifRequestTelegram);
								}
							}
						}
					}
				}
			}
		}
		return mockData;
	}

	
	
	/**
	 * API 들의 name 과 path 를 담은 Map 객체의 List 를 반환한다.
	 * @return API 들의 name 과 path 를 담은 Map 객체의 List
	 */
	private List<Map<String, Object>> getApiInfoList() {
		List<Map<String, Object>> apiInfoList = new ArrayList<>();

		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			RequestMapping requestMappingAnnotation = method.getAnnotation(RequestMapping.class);
			if (requestMappingAnnotation != null) {
				String name = requestMappingAnnotation.name();
				String[] paths = requestMappingAnnotation.path();
				for (String path : paths) {
//					if (path.startsWith(API_URL_TOKEN)) {
					if (
						path.startsWith(API_URL_TOKEN)
						|| path.startsWith(IF_PROVIDER_FOR_GRM_SERVICE_URL_TOKEN)
					) {
						Map<String, Object> map = new HashMap<>();
						
						String modifiedPath = path.replaceAll("/\\{\\w+\\}", DEFAULT_SUBCASE_PATH);
//						map.put("path", path);
						
						map.put("path", modifiedPath);
						map.put("name", name);
						map.put("hasSubcase", Pattern.compile(PATH_VARIABLE_TOKEN).matcher(path).find());
						map.put("subcase", DEFAULT_SUBCASE_PATH);
						map.put("isImageSystemPath", Pattern.compile(IMAGE_SYSTEM_TOKEN).matcher(path).find());
						apiInfoList.add(map);
					}
				}
			}
		}

		apiInfoList.sort((o1, o2) -> {
			String name_o1 = (String) o1.get("name");
			String name_o2 = (String) o2.get("name");
			int compareToIgnoreCase = name_o1.compareToIgnoreCase(name_o2);
			return compareToIgnoreCase;
		});

		return apiInfoList;
	}

	
	
	/**
	 * 모조 신분증 이미지 정보 목록을 반환한다.
	 * @return 모조 신분증 이미지 정보 목록
	 * @throws IOException
	 */
	public List<Map<String, String>> getIdCardMockImageInfoList() throws IOException {
		List<Map<String, String>> idCardMockImageInfoList = new ArrayList<>();

		String cpth = "classpath*:" + MOCK_IMAGE_ROOT_PATH + "/*." + IMAGE_DATA_FILE_EXTENSION;

		Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader()).getResources(cpth);

		List<Resource> asList = Arrays.asList(resources);
		asList.sort((o1, o2) -> o1.getFilename().compareTo(o2.getFilename()));

		for (Resource resource : asList) {
			Stream<String> lineStream = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8")).lines();
			List<String> lineList = lineStream.collect(Collectors.toList());
			String idCardMockImageBase64Data = String.join("", lineList);
			String mockImageDataFileName = resource.getFilename();
			Map<String, String> idCardMockImageInfoMap = new HashMap<>();
			String mockImageName = mockImageDataFileName.replaceAll("." + IMAGE_DATA_FILE_EXTENSION + "$", "");
			idCardMockImageInfoMap.put("mockImageName", mockImageName);
			idCardMockImageInfoMap.put("mockImageDataFileName", mockImageDataFileName);
			idCardMockImageInfoMap.put("idCardMockImageBase64Data", idCardMockImageBase64Data);
			idCardMockImageInfoList.add(idCardMockImageInfoMap);
		}
		return idCardMockImageInfoList;
	}
	
	
	
	
	/**
	 * <pre>
	 * String 값을 인자로 받아 그 값으로 컨트롤러의 path가 시작이 되는 컨트롤러를 찾고, 
	 * 그 찾은 컨트롤러의 name 을 반환한다.
	 * </pre>
	 * @param controllerPath 컨트롤러의 path
	 * @return 컨트롤러의 name
	 */
	public String findControllerName(String controllerPath) {
		String controllerName = null;
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		rootFor : for (Method method : declaredMethods) {
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if (requestMapping != null) {
				String[] paths = requestMapping.path();
				String name = requestMapping.name();
				for (String path : paths) {
					if (path.startsWith(controllerPath)) {
						controllerName = name;
						break rootFor;
					}
				}
			}
		}
		return controllerName;
	}
	
	
	
	/**
	 * <pre>
	 * 요청 URI 를 인자로 받아서, 그 요청 URI를 path로 갖는 컨트롤러가 Base64데이터를 취급하는 컨트롤러인지 여부를 반환한다.
	 * (Base64데이터를 취급하는 컨트롤러의 path 에는 TOKENS_OF_URI_WITH_BASE64_REQUEST_PARAM 의 문자열 값을 포함하고 있다.)
	 * </pre>
	 * @param requestUri 요청 URI
	 * @return 요청 URI를 path로 갖는 컨트롤러가 Base64데이터를 취급하는 컨트롤러인지 여부
	 * @throws IOException
	 */
	public boolean isRequestThatHasBase64Data(String requestUri) throws IOException {
		boolean isRequestThatHasBase64Data = false;
		String[] tokens = TOKENS_OF_URI_WITH_BASE64_REQUEST_PARAM;
		for (String token : tokens) {
			if(requestUri.contains(token)) {
				isRequestThatHasBase64Data = true;
				break;
			}
		}
		return isRequestThatHasBase64Data;
	}
}
