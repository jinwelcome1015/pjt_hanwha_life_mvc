package com.gooroomee.gooroomeeadapter.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.EzCertSrvcId;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.OcrIdType;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.TrflCnfmBswrDvsnCode;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.TrflCnfmJobCode;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc000ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc001ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc001ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc001ResDto.DataHeader;
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
import com.gooroomee.gooroomeeadapter.dto.client.Mvc009ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc009ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc010ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc010ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc011ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc011ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc012ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc012ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc016ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc016ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc017ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc017ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc018ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc018ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_O;
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
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_I.DataBody.Callback;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.exception.IfException;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs018_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs018_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataBody;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataBody.Image;
import com.gooroomee.gooroomeeadapter.service.GrmAdapterService;
import com.gooroomee.gooroomeeadapter.util.AesUtil;
import com.gooroomee.gooroomeeadapter.util.MockUtil;

import korealife.uv.com.cm.SHA256CmCrypt;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GrmAdapterController {

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
	

	
	
	@Autowired
	private GrmAdapterService gooroomeeAdapterService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;
	
	public static final String EXCEPTION_CONTROLLER_PATH = "/exception";

	public static final String EXCEPTION_ATTRIBUTE_NAME = "exception";
	
	public static final String API_URL_TOKEN = "/intrf";

	private static final String URL_FOR_REQUEST_MOCK_DATA = "/test/api/mockData/req";

	private static final String PARAM_NAME_FOR_REQUEST_MOCK_DATA = "apiPath";
	
	private static final String PERSONAL_NUMBER_DELIMITER = "-";
	private static final String DRIVER_LICENSE_NUMBER_DELIMITER = "-";
	
	/*
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true)
				.setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		String responseBody = "{ \"header\": { \"trnmSysCode\": \"MVC\", \"ipAddr\": \"010252005065\", \"tlgrCretDttm\": \"20231026135528604\", \"rndmNo\": \"5825\", \"hsno\": 2, \"prsnInfoIncsYn\": \"N\", \"itfcId\": \"HLIMVC00016\", \"rcveSrvcId\": \"hcsIdcdOcrRqst\", \"rcveSysCode\": \"HCS\", \"mciNodeNo\": \"11\", \"serverType\": \"D\", \"rspnDvsnCode\": \"S\", \"emnb\": \"1077593\", \"belnOrgnCode\": \"00630\", \"chnlTypeCode\": \"SVR\", \"rqsrIp\": \"10.252.5.65\", \"totalCount\": 0, \"msgeListCont\": 0 }, \"payload\": { \"dataHeader\": { \"SRVC_ID\": \"SVC028\", \"SCRN_ID\": \"\", \"X_OCR_SECRET\": \"ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=\", \"CRTF_RTCD\": \"0000\", \"DLRE_MSG\": \"SUCCESS\" }, \"dataBody\": { \"requestId\": \"3aa6f56f-35d2-4ec8-a3ff-705f08b65027\", \"version\": \"V2\", \"timestamp\": \"1698296134190\", \"images\": \"[{\\\"uid\\\":\\\"a513711d3b514f6fb11ad3dc3848df1f\\\",\\\"validationResult\\\":{\\\"result\\\":\\\"NO_REQUESTED\\\"},\\\"inferResult\\\":\\\"SUCCESS\\\",\\\"idCard\\\":{\\\"result\\\":{\\\"idtype\\\":\\\"ID Card\\\",\\\"rois\\\":[{\\\"vertices\\\":[{\\\"x\\\":3.1388545,\\\"y\\\":3.9956706},{\\\"x\\\":754.52325,\\\"y\\\":-5.092327},{\\\"x\\\":747.84045,\\\"y\\\":473.14856},{\\\"x\\\":2.264223,\\\"y\\\":474.4076}]}],\\\"ic\\\":{\\\"address\\\":[{\\\"boundingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":51.675,\\\"y\\\":235.1875},{\\\"x\\\":192.7875,\\\"y\\\":235.1875},{\\\"x\\\":192.7875,\\\"y\\\":272.2875},{\\\"x\\\":51.675,\\\"y\\\":272.2875}]},{\\\"vertices\\\":[{\\\"x\\\":53.6625,\\\"y\\\":268.3125},{\\\"x\\\":263.67496,\\\"y\\\":268.3125},{\\\"x\\\":263.67496,\\\"y\\\":304.0875},{\\\"x\\\":53.6625,\\\"y\\\":304.0875}]},{\\\"vertices\\\":[{\\\"x\\\":203.3875,\\\"y\\\":235.85},{\\\"x\\\":387.5625,\\\"y\\\":235.85},{\\\"x\\\":387.5625,\\\"y\\\":270.9625},{\\\"x\\\":203.3875,\\\"y\\\":270.9625}]},{\\\"vertices\\\":[{\\\"x\\\":271.625,\\\"y\\\":268.975},{\\\"x\\\":347.15,\\\"y\\\":268.975},{\\\"x\\\":347.15,\\\"y\\\":302.7625},{\\\"x\\\":271.625,\\\"y\\\":302.7625}]}],\\\"formatted\\\":{\\\"value\\\":\\\"서울특별시 가산디지털1로 (대륭테크노타운, 18차)\\\"},\\\"text\\\":\\\"서울특별시 가산디지털1로 (대륭테크노타운 18차)\\\",\\\"maskingPolys\\\":[]}],\\\"authority\\\":[{\\\"boundingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":146.53316,\\\"y\\\":381.33884},{\\\"x\\\":326.93195,\\\"y\\\":383.12497},{\\\"x\\\":326.47778,\\\"y\\\":428.99695},{\\\"x\\\":146.07898,\\\"y\\\":427.21082}]},{\\\"vertices\\\":[{\\\"x\\\":343.17493,\\\"y\\\":382.92496},{\\\"x\\\":524.6999,\\\"y\\\":382.92496},{\\\"x\\\":524.6999,\\\"y\\\":427.31247},{\\\"x\\\":343.17493,\\\"y\\\":427.31247}]},{\\\"vertices\\\":[{\\\"x\\\":525.3625,\\\"y\\\":374.975},{\\\"x\\\":622.75,\\\"y\\\":374.975},{\\\"x\\\":622.75,\\\"y\\\":429.3},{\\\"x\\\":525.3625,\\\"y\\\":429.3}]}],\\\"formatted\\\":{\\\"value\\\":\\\"서울특별시 금천구청장\\\"},\\\"text\\\":\\\"서울특별시 금천구청장 청바\\\",\\\"maskingPolys\\\":[]}],\\\"name\\\":[{\\\"boundingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":72.2125,\\\"y\\\":116.6},{\\\"x\\\":367.6875,\\\"y\\\":116.6},{\\\"x\\\":367.6875,\\\"y\\\":165.625},{\\\"x\\\":72.2125,\\\"y\\\":165.625}]}],\\\"formatted\\\":{\\\"value\\\":\\\"홍길동\\\"},\\\"text\\\":\\\"홍길동\\\",\\\"maskingPolys\\\":[]}],\\\"personalNum\\\":[{\\\"boundingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":65.5875,\\\"y\\\":181.52498},{\\\"x\\\":363.71246,\\\"y\\\":181.52498},{\\\"x\\\":363.71246,\\\"y\\\":216.63748},{\\\"x\\\":65.5875,\\\"y\\\":216.63748}]}],\\\"formatted\\\":{\\\"value\\\":\\\"800101-2345678\\\"},\\\"text\\\":\\\"800101-2345678\\\",\\\"maskingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":211.13873,\\\"y\\\":178.01373},{\\\"x\\\":367.22372,\\\"y\\\":178.01373},{\\\"x\\\":367.22372,\\\"y\\\":220.14873},{\\\"x\\\":211.13873,\\\"y\\\":220.14873}]}]}],\\\"issueDate\\\":[{\\\"boundingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":242.475,\\\"y\\\":351.7875},{\\\"x\\\":374.3125,\\\"y\\\":351.7875},{\\\"x\\\":374.3125,\\\"y\\\":381.6},{\\\"x\\\":242.475,\\\"y\\\":381.6}]},{\\\"vertices\\\":[{\\\"x\\\":372.98746,\\\"y\\\":352.44995},{\\\"x\\\":422.01245,\\\"y\\\":352.44995},{\\\"x\\\":422.01245,\\\"y\\\":380.93747},{\\\"x\\\":372.98746,\\\"y\\\":380.93747}]}],\\\"formatted\\\":{\\\"month\\\":\\\"08\\\",\\\"year\\\":\\\"2020\\\",\\\"day\\\":\\\"16\\\"},\\\"text\\\":\\\"2020.08.16\\\",\\\"maskingPolys\\\":[{\\\"vertices\\\":[{\\\"x\\\":239.52966,\\\"y\\\":349.0218},{\\\"x\\\":424.9578,\\\"y\\\":349.0217},{\\\"x\\\":424.9578,\\\"y\\\":384.3657},{\\\"x\\\":239.52968,\\\"y\\\":384.36578}]}]}]},\\\"isConfident\\\":true},\\\"meta\\\":{\\\"estimatedLanguage\\\":\\\"ko\\\"}},\\\"name\\\":\\\"test_idcard\\\",\\\"message\\\":\\\"SUCCESS\\\"}]\" } } }";
		Class<?> outputDtoClass = IfMcCs001_O.class;
		
		IfTelegram<IfMcCs001_O> responseTelegram = null;
		
		if (responseBody != null) {
			JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, outputDtoClass);
			responseTelegram = OBJECT_MAPPER.readValue(responseBody, javaType);
		}
		IfMcCs001_O cs001_O = responseTelegram.getPayload();
		
		Mvc001ResDto resDto = modelMapper.map(cs001_O, Mvc001ResDto.class);
		com.gooroomee.gooroomeeadapter.dto.client.Mvc001ResDto.DataBody dataBody = resDto.getDataBody();
		String images = dataBody.getImages();
		JsonNode ocrResultReadTree = OBJECT_MAPPER.readTree(images);
		for (int i = 0; i < ocrResultReadTree.size(); i++) {
			String idType = ocrResultReadTree.get(i).get("idCard").get("result").get("idtype").asText();
			System.out.println("idType : " + idType);
		}
		
	}
	*/
	
	/**
	 * 
	 * 
	 * @param idType 신분증 타입
	 * @return 진위확인구분코드
	 */
	private String getTrflCnfmDvsnCodeFromIdType(String idType) {
		String trflCnfmDvsnCode = null;
		OcrIdType[] ocrIdTypes = IfConstant.OcrIdType.values();
		for (OcrIdType ocrIdType : ocrIdTypes) {
			if(ocrIdType.getName().equals(idType)) {
				trflCnfmDvsnCode = ocrIdType.getTrflCnfmDvsnCode();
				break;
			}
		}
		return trflCnfmDvsnCode;
	}
	
	
	private String getCustNm(JsonNode ocrResultReadTree) {
		String custNm = null;
		
		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();
		
		if(idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("ic").get("name").get(0).get("text").asText();
		}else if(idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("dl").get("name").get(0).get("text").asText();
		}else if(idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("pp").get("fullNameKor").get(0).get("text").asText();
		}else if(idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("ac").get("name").get(0).get("text").asText();
		}
		return custNm;
	}
	
	
	private String getIsncDate(JsonNode ocrResultReadTree) {
		String issueDate = null;
		
		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();
		
		if(idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("ic").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("ic").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("ic").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		}else if(idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("dl").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("dl").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("dl").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		}else if(idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("pp").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("pp").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("pp").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		}else if(idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("ac").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("ac").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("ac").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		}
		
		return issueDate;
	}
	
	
	private String getBirthDayFromPersonalNum(String str1, String str2) {
		int divisionCode = Integer.parseInt(str2.substring(0, 1));
		String dateOfBirth = null;
		if (divisionCode == 1 || divisionCode == 2 || divisionCode == 5 || divisionCode == 6) {
			dateOfBirth = "19" + str1;
		} else if (divisionCode == 3 || divisionCode == 4 || divisionCode == 7 || divisionCode == 8) {
			dateOfBirth = "20" + str1;
		} else if (divisionCode == 9 || divisionCode == 0) {
			dateOfBirth = "18" + str1;
		}
		return dateOfBirth;
	}
	
	
	
	private String getBtdt(JsonNode ocrResultReadTree) {
		String btdt = null;
		
		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();
		
		if(idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			String personalNum = ocrResultReadTree.get("idCard").get("result").get("ic").get("personalNum").get(0).get("text").asText();
			String[] personalNumberTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
			btdt = this.getBirthDayFromPersonalNum(personalNumberTokens[0], personalNumberTokens[1]);
		}else if(idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String personalNum = ocrResultReadTree.get("idCard").get("result").get("dl").get("personalNum").get(0).get("text").asText();
			String[] personalNumberTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
			btdt = this.getBirthDayFromPersonalNum(personalNumberTokens[0], personalNumberTokens[1]);
		}else if(idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			String birthDateYear = ocrResultReadTree.get("idCard").get("result").get("pp").get("birthDate").get(0).get("formatted").get("year").asText();
			String birthDateMonth = ocrResultReadTree.get("idCard").get("result").get("pp").get("birthDate").get(0).get("formatted").get("month").asText();
			String birthDateDay = ocrResultReadTree.get("idCard").get("result").get("pp").get("birthDate").get(0).get("formatted").get("day").asText();
			btdt = birthDateYear + birthDateMonth + birthDateDay;
		}else if(idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			btdt = "";
		}
		
		return btdt;
	}
	
	
	public Date transformDate(String date) throws ParseException {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");

		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");

		java.util.Date tempDate = null;

		tempDate = beforeFormat.parse(date);

		String transDate = afterFormat.format(tempDate);

		Date d = Date.valueOf(transDate);

		return d;
	}
	
	/**
	 * [01, 02, 07, 08, 09, 03] 
	 * 
	 *	01.신분증OCR요청
	 *	02.진위확인결과조회
	 *	07.고객계약정보조회
	 *	08.고객계좌목록조회
	 *	09.개인정보유출노출여부조회
	 *	03.신분증스캔후처리
	 * 
	 * @param mvc001ReqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/entry"), (API_URL_TOKEN + "/entry" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "00. 진입")
	public @ResponseBody ResponseDto<Mvc000ResDto> entry(@RequestBody Mvc001ReqDto mvc001ReqDto, HttpServletRequest request)
			throws URISyntaxException, IOException, ParseException {
		
		List<Mvc000ResDto> entryResultList = new ArrayList<>();
		
		// XXX [01] 신분증OCR요청
		ResponseDto<Mvc001ResDto> response001dto = this.idcdOcrRqst(mvc001ReqDto, request);
		Mvc001ResDto mvc001ResDto = response001dto.getData();
		
		Mvc001ResDto.DataHeader mvc001ResDtoDataHeader = mvc001ResDto.getDataHeader();
		Mvc001ResDto.DataBody mvc001ResDtoDataBody = mvc001ResDto.getDataBody();
		
		String ocrResultJson = mvc001ResDtoDataBody.getImages();
		
		JsonNode ocrResultReadTrees = objectMapper.readTree(ocrResultJson);
		
		for (int i = 0; i < ocrResultReadTrees.size(); i++) {
			
			Mvc000ResDto mvc000ResDto = new Mvc000ResDto();
			
			/**
			 * ID Card
			 * Driver's License
			 * Passport
			 * Alien Registration Card
			 */
			
			JsonNode ocrResultReadTree = ocrResultReadTrees.get(i);
			String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();
			String trflCnfmDvsnCode = this.getTrflCnfmDvsnCodeFromIdType(idtype);
			
			String custNm = this.getCustNm(ocrResultReadTree);
			String btdt = this.getBtdt(ocrResultReadTree);
			String isncDate = this.getIsncDate(ocrResultReadTree);
			
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
			mvc002ReqDto.setBtdt(this.transformDate(btdt));
			mvc002ReqDto.setIsncDate(this.transformDate(isncDate));
			mvc002ReqDto.setExpyDate(null);
			
			if(idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
				String rrno = ocrResultReadTree.get("idCard").get("result").get("ic").get("personalNum").get(0).get("text").asText();
				rrno = rrno.replaceAll(PERSONAL_NUMBER_DELIMITER, "");
				
				mvc002ReqDto.setRrno(rrno);
			}else if(idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
				String driverLicenseNumber = ocrResultReadTree.get("idCard").get("result").get("dl").get("num").get(0).get("text").asText();
				driverLicenseNumber = driverLicenseNumber.replaceAll(DRIVER_LICENSE_NUMBER_DELIMITER, "");
				
				mvc002ReqDto.setDrvnLcnsSqno("");
				mvc002ReqDto.setDrvnLcnsNo(driverLicenseNumber);
			}else if(idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
				String passportNumber = ocrResultReadTree.get("idCard").get("result").get("pp").get("num").get(0).get("text").asText();
				
				mvc002ReqDto.setPsprNo(passportNumber);
			}else if(idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
				String alienRegistrationNumber = ocrResultReadTree.get("idCard").get("result").get("ac").get("alienRegNum").get(0).get("text").asText();
				
				mvc002ReqDto.setFrnrRgstNo(alienRegistrationNumber);
			}
			
			ResponseDto<Mvc002ResDto> response002dto = this.trflCnfm(mvc002ReqDto, request);
			
			Mvc002ResDto mvc002ResDto = response002dto.getData();
			
			mvc000ResDto.setVerification(mvc002ResDto);
			
			
			// XXX [07] 고객계약정보조회
			Mvc007ReqDto mvc007ReqDto = new Mvc007ReqDto();
			mvc007ReqDto.setEmnb(mvc001ReqDto.getEmnb());
			mvc007ReqDto.setCustId(mvc002ResDto.getCustId());
			
			ResponseDto<Mvc007ResDto> response007dto = this.intgCustInqyMgmt(mvc007ReqDto, request);
			Mvc007ResDto mvc007ResDto = response007dto.getData();
			
			mvc000ResDto.setContract(mvc007ResDto);
			
			
			// XXX [08] 고객계좌목록조회
			Mvc008ReqDto mvc008ReqDto = new Mvc008ReqDto();
			mvc008ReqDto.setEmnb(mvc001ReqDto.getEmnb());
			mvc008ReqDto.setCustId(mvc002ResDto.getCustId());
			
			ResponseDto<Mvc008ResDto> response008dto = this.intgCust(mvc008ReqDto, request);
			Mvc008ResDto mvc008ResDto = response008dto.getData();
			
			mvc000ResDto.setAccount(mvc008ResDto);
			

			// XXX [09] 개인정보유출노출여부조회
			Mvc009ReqDto mvc009ReqDto = new Mvc009ReqDto();
			mvc009ReqDto.setEmnb(mvc001ReqDto.getEmnb());
			mvc009ReqDto.setCustId(mvc002ResDto.getCustId());
			
			ResponseDto<Mvc009ResDto> response009dto = this.prsnInfoLeakMgmt(mvc009ReqDto, request);
			Mvc009ResDto mvc009ResDto = response009dto.getData();
			
			mvc000ResDto.setPersonalInfoLeak(mvc009ResDto);
			
			
			// [03] 신분증스캔후처리
			Mvc003ReqDto mvc003ReqDto = new Mvc003ReqDto();
			mvc003ReqDto.setCsnsYn(mvc002ResDto.getCsnsYn());
			mvc003ReqDto.setCustId(mvc002ResDto.getCustId());
			mvc003ReqDto.setEmnb(mvc001ReqDto.getEmnb());
			
			ResponseDto<Mvc003ResDto> response003dto = this.itfcIdcdScan(mvc003ReqDto, request);
			Mvc003ResDto mvc003ResDto = response003dto.getData();
			
//			String prcsSucsYn = mvc003ResDto.getPrcsSucsYn();
			
			mvc000ResDto.setItfcIdcdScan(mvc003ResDto);
			
			entryResultList.add(mvc000ResDto);
		}
		
		
		if (entryResultList.size() == 1) {
			Mvc000ResDto mvc000ResDto = entryResultList.get(0);
			ResponseDto<Mvc000ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, mvc000ResDto);
			return responseDto;
		} else {
			String message = String.format("신분증 OCR 결과가 1건이 아닙니다. (%d건)", entryResultList.size());
			throw new IfException(message);
		}
	}
	
	
	
	/**
	 * <pre>
	 * [01] 
	 * 신분증OCR요청
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/idcdOcrRqst"), (API_URL_TOKEN + "/idcdOcrRqst" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "01. 신분증OCR요청")
	public @ResponseBody ResponseDto<Mvc001ResDto> idcdOcrRqst(@RequestBody Mvc001ReqDto reqDto, HttpServletRequest request)
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
		
		/*
		String emnb = reqDto.getEmnb();
		
		IfMcCs001_O cs001_O = gooroomeeAdapterService.ifmccs001(emnb, cs001_I);
		
		Mvc001ResDto resDto = modelMapper.map(cs001_O, Mvc001ResDto.class);
		*/
		
		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs001;
		Class<IfMcCs001_O> ifOutputDtoClass = IfMcCs001_O.class;
		IfMcCs001_O cs001_O = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
	
		Mvc001ResDto resDto = modelMapper.map(cs001_O, Mvc001ResDto.class);
		

		ResponseDto<Mvc001ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto; 
		
	}
	
	
	

	/**
	 * <pre>
	 * [02] 
	 * 진위확인결과조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	/*
	@RequestMapping(path = { (API_URL_TOKEN + "/trflCnfm"), (API_URL_TOKEN + "/trflCnfm" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "02. 진위확인결과조회")
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
	
		IfMcCs002_I cs002_I = modelMapper.map(reqDto, IfMcCs002_I.class);
	
		String emnb = reqDto.getEmnb();
	
		IfMcCs002_O cs002_O = gooroomeeAdapterService.ifmccs002(emnb, cs002_I);
	
		Mvc002ResDto resDto = modelMapper.map(cs002_O, Mvc002ResDto.class);
	
		ResponseDto<Mvc002ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	*/
	@RequestMapping(path = { (API_URL_TOKEN + "/trflCnfm"), (API_URL_TOKEN + "/trflCnfm" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "02. 진위확인결과조회")
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
	
		IfMcCs002_I ifInputDto = modelMapper.map(reqDto, IfMcCs002_I.class);
	
		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;
		Class<IfMcCs002_O> ifOutputDtoClass = IfMcCs002_O.class;
		IfMcCs002_O cs002_O = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
	
		Mvc002ResDto resDto = modelMapper.map(cs002_O, Mvc002ResDto.class);
	
		ResponseDto<Mvc002ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	
	
	
	/**
	 * <pre>
	 * [03] 
	 * 신분증스캔후처리
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	/*
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcIdcdScan"), (API_URL_TOKEN + "/itfcIdcdScan" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "03. 신분증스캔후처리")
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto)
			throws URISyntaxException, IOException {
	
		IfMcCs003_I cs003_I = modelMapper.map(reqDto, IfMcCs003_I.class);
		cs003_I.setPushRcvrEmnb(reqDto.getEmnb());
	
		String emnb = reqDto.getEmnb();
	
		IfMcCs003_O cs003_O = gooroomeeAdapterService.ifmccs003(emnb, cs003_I);
	
		Mvc003ResDto resDto = modelMapper.map(cs003_O, Mvc003ResDto.class);
	
		ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	*/
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcIdcdScan"), (API_URL_TOKEN + "/itfcIdcdScan" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "03. 신분증스캔후처리")
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
		IfMcCs003_I ifInputDto = modelMapper.map(reqDto, IfMcCs003_I.class);
		ifInputDto.setPushRcvrEmnb(reqDto.getEmnb());
	
		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;
		Class<IfMcCs003_O> ifOutputDtoClass = IfMcCs003_O.class;
		IfMcCs003_O cs003_O = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
	
		Mvc003ResDto resDto = modelMapper.map(cs003_O, Mvc003ResDto.class);
	
		ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	
	
	
	/**
	 * <pre>
	 * [05]
	 * SSO대체로그인인증
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcUserCtfn"), (API_URL_TOKEN + "/itfcUserCtfn" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "05. SSO대체로그인인증")
	public @ResponseBody ResponseDto<Mvc005ResDto> itfcUserCtfn(@RequestBody Mvc005ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
//		IfMcCs005_I cs005_I = modelMapper.map(reqDto, IfMcCs005_I.class);

		String emnb = reqDto.getEmnb();

		String lognPswd = reqDto.getLognPswd();
		String encLognPswd = SHA256CmCrypt.SHA256_getEncString(lognPswd);

		IfMcCs005_I cs005_I = new IfMcCs005_I();
		cs005_I.setEmnb(emnb);
		cs005_I.setLognPswd(encLognPswd);

		IfMcCs005_O cs005_O = gooroomeeAdapterService.ifmccs005(emnb, cs005_I);

		Mvc005ResDto resDto = modelMapper.map(cs005_O, Mvc005ResDto.class);

		ResponseDto<Mvc005ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [06]
	 * 사원목록조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/empeInqy"), (API_URL_TOKEN + "/empeInqy" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "06. 사원목록조회")
	public @ResponseBody ResponseDto<Mvc006ResDto> empeInqy(@RequestBody Mvc006ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
		IfMcCs006_I cs006_I = modelMapper.map(reqDto, IfMcCs006_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs006_O cs006_O = gooroomeeAdapterService.ifmccs006(emnb, cs006_I);

		Mvc006ResDto resDto = modelMapper.map(cs006_O, Mvc006ResDto.class);

		ResponseDto<Mvc006ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [07]
	 * 고객계약정보조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCustInqyMgmt"), (API_URL_TOKEN + "/intgCustInqyMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "07. 고객계약정보조회")
	public @ResponseBody ResponseDto<Mvc007ResDto> intgCustInqyMgmt(@RequestBody Mvc007ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
		IfMcCs007_I cs007_I = modelMapper.map(reqDto, IfMcCs007_I.class);
		cs007_I.setCntcDvsnCode(IfConstant.ContractClassificationCode.INSURANCE.getCode());
		cs007_I.setCustDvsnCode(IfConstant.CustomerDivisionCode.INDIVIDUAL.getCode());

		String emnb = reqDto.getEmnb();

		IfMcCs007_O cs007_O = gooroomeeAdapterService.ifmccs007(emnb, cs007_I);

		Mvc007ResDto resDto = modelMapper.map(cs007_O, Mvc007ResDto.class);

		ResponseDto<Mvc007ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [08]
	 * 고객계좌목록조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCust"), (API_URL_TOKEN + "/intgCust" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "08. 고객계좌목록조회")
	public @ResponseBody ResponseDto<Mvc008ResDto> intgCust(@RequestBody Mvc008ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs008_I cs008_I = modelMapper.map(reqDto, IfMcCs008_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs008_O cs008_O = gooroomeeAdapterService.ifmccs008(emnb, cs008_I);

		Mvc008ResDto resDto = modelMapper.map(cs008_O, Mvc008ResDto.class);

		ResponseDto<Mvc008ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [09]
	 * 개인정보유출노출여부조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/prsnInfoLeakMgmt"), (API_URL_TOKEN + "/prsnInfoLeakMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "09. 개인정보유출노출여부조회")
	public @ResponseBody ResponseDto<Mvc009ResDto> prsnInfoLeakMgmt(@RequestBody Mvc009ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {
		
		IfMcCs009_I cs009_I = modelMapper.map(reqDto, IfMcCs009_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs009_O cs009_O = gooroomeeAdapterService.ifmccs009(emnb, cs009_I);

		Mvc009ResDto resDto = modelMapper.map(cs009_O, Mvc009ResDto.class);

		ResponseDto<Mvc009ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [10]
	 * 간편인증 토큰발급
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/initechToken"), (API_URL_TOKEN + "/initechToken" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
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

		IfMcCs010_I cs010_I = new IfMcCs010_I();
		cs010_I.setDataHeader(dataHeader);
		cs010_I.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

		IfMcCs010_O cs010_O = gooroomeeAdapterService.ifmccs010(emnb, cs010_I);

		Mvc010ResDto resDto = modelMapper.map(cs010_O, Mvc010ResDto.class);

		ResponseDto<Mvc010ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [11]
	 * 간편인증 요청
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InvalidAlgorithmParameterException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/initechRequest"), (API_URL_TOKEN + "/initechRequest" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "11. 간편인증 요청")
	public @ResponseBody ResponseDto<Mvc011ResDto> initechRequest(@RequestBody Mvc011ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
	//		IfMcCs011_I cs011_I = modelMapper.map(reqDto, IfMcCs011_I.class);
	    
		IfMcCs011_I.DataHeader dataHeader = new IfMcCs011_I.DataHeader();
	//		dataHeader.setCRTF_RTCD("");
	//		dataHeader.setDLRE_MSG("");
		dataHeader.setORGN_CODE(IfConstant.BELN_ORGN_CODE);
	
		// TODO 확인 필요
//		dataHeader.setSCRN_ID(IfConstant.TRNM_SYS_CODE);
		dataHeader.setSCRN_ID(reqDto.getSCRN_ID());
		
		dataHeader.setSRVC_ID(IfConstant.SRVC_ID);
		
		// TODO 확인 필요
		dataHeader.setUSER_ID(reqDto.getUSER_ID());
		
		
	    
	    IfMcCs011_I.DataBody dataBody = new IfMcCs011_I.DataBody();
	    
	    String initechOAuthToken = reqDto.getInitechOAuthToken();
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
		dataBody.setDeviceBrowser(deviceBrowser);;
	    
	    Callback callback = new IfMcCs011_I.DataBody.Callback();
	    callback.setFailCallbackUrl("");
	    callback.setMobileOs("");
	    callback.setSuccessCallbackUrl("");
	    callback.setTelcoTycd("");
	    dataBody.setCallback(callback);
	    
	    dataBody.setChannel("");
	    
	    
	    
	    IfMcCs011_I cs011_I = new IfMcCs011_I();
	    cs011_I.setDataHeader(dataHeader);
	    cs011_I.setDataBody(dataBody);
	    
		
		String emnb = reqDto.getEmnb();
	
		IfMcCs011_O cs011_O = gooroomeeAdapterService.ifmccs011(emnb, cs011_I);
	
		Mvc011ResDto resDto = modelMapper.map(cs011_O, Mvc011ResDto.class);
	
		ResponseDto<Mvc011ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	

	/**
	 * 
	 * <pre>
	 * [12]
	 * 간편인증 상태 조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/initechStatus"), (API_URL_TOKEN + "/initechStatus" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "12. 간편인증 상태 조회")
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
		
	    String initechOAuthToken = reqDto.getInitechOAuthToken();
		dataBody.setInitechOAuthToken(initechOAuthToken);
		
		String reqTxId = reqDto.getReqTxId();
		dataBody.setReqTxId(reqTxId);

		IfMcCs012_I cs012_I = new IfMcCs012_I();
		cs012_I.setDataHeader(dataHeader);
		cs012_I.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

		IfMcCs012_O cs012_O = gooroomeeAdapterService.ifmccs012(emnb, cs012_I);

		Mvc012ResDto resDto = modelMapper.map(cs012_O, Mvc012ResDto.class);

		ResponseDto<Mvc012ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * [16]
	 * 대체키별연락처저장
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/prsnCustMgmt"), (API_URL_TOKEN + "/prsnCustMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "16. 대체키별연락처저장")
	public @ResponseBody ResponseDto<Mvc016ResDto> prsnCustMgmt(@RequestBody Mvc016ReqDto reqDto, HttpServletRequest request) throws URISyntaxException, IOException {
		
		IfMcCs016_I ifInputDto = modelMapper.map(reqDto, IfMcCs016_I.class);
	
		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;
		Class<IfMcCs016_O> ifOutputDtoClass = IfMcCs016_O.class;
		IfMcCs016_O cs016_O = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
	
		Mvc016ResDto resDto = modelMapper.map(cs016_O, Mvc016ResDto.class);
	
		ResponseDto<Mvc016ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	
	
	/**
	 * 
	 * <pre>
	 * [17]
	 * 대체키별연락처조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/cnplSbsnKeyMgmt"), (API_URL_TOKEN + "/cnplSbsnKeyMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "17. 대체키별연락처조회")
	public @ResponseBody ResponseDto<Mvc017ResDto> cnplSbsnKeyMgmt(@RequestBody Mvc017ReqDto reqDto, HttpServletRequest request) throws URISyntaxException, IOException {
		
		IfMcCs017_I ifInputDto = modelMapper.map(reqDto, IfMcCs017_I.class);
	
		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs017;
		Class<IfMcCs017_O> ifOutputDtoClass = IfMcCs017_O.class;
		IfMcCs017_O cs017_O = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
	
		Mvc017ResDto resDto = modelMapper.map(cs017_O, Mvc017ResDto.class);
	
		ResponseDto<Mvc017ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * [18]
	 * 우편번호조회
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/addrMgmt"), (API_URL_TOKEN + "/addrMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = { RequestMethod.POST }, name = "18. 우편번호조회")
	public @ResponseBody ResponseDto<Mvc018ResDto> addrMgmt(@RequestBody Mvc018ReqDto reqDto, HttpServletRequest request) throws URISyntaxException, IOException {
		
		IfMcCs018_I ifInputDto = modelMapper.map(reqDto, IfMcCs018_I.class);
	
		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs018;
		Class<IfMcCs018_O> ifOutputDtoClass = IfMcCs018_O.class;
		IfMcCs018_O cs018_O = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
	
		Mvc018ResDto resDto = modelMapper.map(cs018_O, Mvc018ResDto.class);
	
		ResponseDto<Mvc018ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
	
		return responseDto;
	}
	
	
	
	@RequestMapping(path = { EXCEPTION_CONTROLLER_PATH })
	public void exception(HttpServletRequest request) throws Exception {
		Exception exception = (Exception) request.getAttribute(EXCEPTION_ATTRIBUTE_NAME);
		throw exception;
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * [A]
	 * 인증서비스ID 목록
	 * </pre>
	 * 
	 * @param model
	 * @return
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

		return "test/apis";
	}

	@RequestMapping(path = { URL_FOR_REQUEST_MOCK_DATA }, method = { RequestMethod.GET })
	public @ResponseBody String getApiTestRequestMockData(@RequestParam MultiValueMap<String, String> map)
			throws ClassNotFoundException, IOException {
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
							if (requestBody != null) {
								ObjectMapper objectMapper = new ObjectMapper();

								Class<?> parameterType = parameter.getType();

								Object mockRequestDataObject = MockUtil.getMockRequestData(methodName, parameterType);
								Map<String, Object> mockRequestDataMap = objectMapper
										.convertValue(mockRequestDataObject, new TypeReference<Map<String, Object>>() {
										});
								mockRequestDataMap.put("emnb", "1077123");

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

		apiInfoList.sort((o1, o2) -> {

			String name_o1 = o1.get("name");
			String name_o2 = o2.get("name");

			int compareToIgnoreCase = name_o1.compareToIgnoreCase(name_o2);

			return compareToIgnoreCase;
		});

		return apiInfoList;
	}

	
}
