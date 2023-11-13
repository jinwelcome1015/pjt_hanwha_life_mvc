package com.gooroomee.gooroomeeadapter.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.EzCertSrvcId;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.OcrIdType;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.TrflCnfmBswrDvsnCode;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.TrflCnfmJobCode;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc000ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc001ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc001ResDto;
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
import com.gooroomee.gooroomeeadapter.dto.client.Mvc015ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc015ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc016ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc016ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc017ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc017ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc018ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc018ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataBody.Image;
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
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_I.NttkButnCntn.Button;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs018_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs018_O;
import com.gooroomee.gooroomeeadapter.exception.IfException;
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

//	private static final String URL_FOR_REQUEST_MOCK_IMAGE = "/test/api/mockImage/req";

	private static final String PARAM_NAME_FOR_REQUEST_MOCK_DATA = "apiPath";

//	private static final String PARAM_NAME_FOR_REQUEST_MOCK_IMAGE = "imageName";

	private static final String PERSONAL_NUMBER_DELIMITER = "-";
	private static final String DRIVER_LICENSE_NUMBER_DELIMITER = "-";

	private static final String MOCK_IMAGE_ROOT_PATH = "/assets/mockIdCardImage";

	private static final String IMAGE_DATA_FILE_EXTENSION = "dat";

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
			if (ocrIdType.getName().equals(idType)) {
				trflCnfmDvsnCode = ocrIdType.getTrflCnfmDvsnCode();
				break;
			}
		}
		return trflCnfmDvsnCode;
	}

	private String getCustNm(JsonNode ocrResultReadTree) {
		String custNm = null;

		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();

		if (idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("ic").get("name").get(0).get("text").asText();
		} else if (idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("dl").get("name").get(0).get("text").asText();
		} else if (idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("pp").get("fullNameKor").get(0).get("text").asText();
		} else if (idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			custNm = ocrResultReadTree.get("idCard").get("result").get("ac").get("name").get(0).get("text").asText();
		}
		return custNm;
	}

	private String getIsncDate(JsonNode ocrResultReadTree) {
		String issueDate = null;

		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();

		if (idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("ic").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("ic").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("ic").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		} else if (idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("dl").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("dl").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("dl").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		} else if (idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("pp").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("pp").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("pp").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		} else if (idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			String issueYear = ocrResultReadTree.get("idCard").get("result").get("ac").get("issueDate").get(0).get("formatted").get("year").asText();
			String issueMonth = ocrResultReadTree.get("idCard").get("result").get("ac").get("issueDate").get(0).get("formatted").get("month").asText();
			String issueDay = ocrResultReadTree.get("idCard").get("result").get("ac").get("issueDate").get(0).get("formatted").get("day").asText();
			issueDate = issueYear + issueMonth + issueDay;
		}

		return issueDate;
	}

	private String getBtdt(JsonNode ocrResultReadTree) {
		String btdt = null;

		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();

		if (idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			String personalNum = ocrResultReadTree.get("idCard").get("result").get("ic").get("personalNum").get(0).get("text").asText();
			String[] personalNumTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
			btdt = this.getFirst2digitsOfBirthYearFromRegNumFirst1digit(personalNumTokens[1].substring(0, 1)) + personalNumTokens[0];
		} else if (idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String personalNum = ocrResultReadTree.get("idCard").get("result").get("dl").get("personalNum").get(0).get("text").asText();
			String[] personalNumTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
			btdt = this.getFirst2digitsOfBirthYearFromRegNumFirst1digit(personalNumTokens[1].substring(0, 1)) + personalNumTokens[0];
		} else if (idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			String birthDateYear = ocrResultReadTree.get("idCard").get("result").get("pp").get("birthDate").get(0).get("formatted").get("year").asText();
			String birthDateMonth = ocrResultReadTree.get("idCard").get("result").get("pp").get("birthDate").get(0).get("formatted").get("month").asText();
			String birthDateDay = ocrResultReadTree.get("idCard").get("result").get("pp").get("birthDate").get(0).get("formatted").get("day").asText();
			btdt = birthDateYear + birthDateMonth + birthDateDay;
		} else if (idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			String alienRegNum = ocrResultReadTree.get("idCard").get("result").get("ac").get("alienRegNum").get(0).get("text").asText();
			String[] alienRegNumTokens = alienRegNum.split(PERSONAL_NUMBER_DELIMITER);
			btdt = this.getFirst2digitsOfBirthYearFromRegNumFirst1digit(alienRegNumTokens[1].substring(0, 1)) + alienRegNumTokens[0];
		}

		return btdt;
	}

	private String getFirst2digitsOfBirthYearFromRegNumFirst1digit(String regNumFirst1digit) {
		String first2digitsOfBirthYear = "";

		switch (regNumFirst1digit) {
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

	private String getSex(JsonNode ocrResultReadTree) {
		String sex = null;

		String idtype = ocrResultReadTree.get("idCard").get("result").get("idtype").asText();

		if (idtype.equals(IfConstant.OcrIdType.IdCard.getName())) {
			String personalNum = ocrResultReadTree.get("idCard").get("result").get("ic").get("personalNum").get(0).get("text").asText();
			String[] personalNumberTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
			sex = (Integer.parseInt(personalNumberTokens[1].substring(0, 1)) % 2) == 1 ? "M" : "F";
		} else if (idtype.equals(IfConstant.OcrIdType.DriverLicense.getName())) {
			String personalNum = ocrResultReadTree.get("idCard").get("result").get("dl").get("personalNum").get(0).get("text").asText();
			String[] personalNumberTokens = personalNum.split(PERSONAL_NUMBER_DELIMITER);
			sex = (Integer.parseInt(personalNumberTokens[1].substring(0, 1)) % 2) == 1 ? "M" : "F";
		} else if (idtype.equals(IfConstant.OcrIdType.Passport.getName())) {
			sex = ocrResultReadTree.get("idCard").get("result").get("pp").get("sex").get(0).get("text").asText();
		} else if (idtype.equals(IfConstant.OcrIdType.AlienRegistrationCard.getName())) {
			sex = ocrResultReadTree.get("idCard").get("result").get("ac").get("sex").get(0).get("text").asText();
		}

		return sex;
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
	 * <pre>
	 * [01, 02, 09, 03]
	 * 
	 *	01.신분증OCR요청
	 *	02.진위확인결과조회
	 *	09.개인정보유출노출여부조회
	 *	03.신분증스캔후처리
	 * </pre>
	 * 
	 * @param mvc001ReqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/entry"), (API_URL_TOKEN + "/entry" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "00. 진입")
	public @ResponseBody ResponseDto<Mvc000ResDto> entry(@RequestBody Mvc001ReqDto mvc001ReqDto, HttpServletRequest request)
			throws URISyntaxException, IOException, ParseException {

		// XXX [01] 신분증OCR요청
		ResponseDto<Mvc001ResDto> response001dto = this.idcdOcrRqst(mvc001ReqDto, request);
		Mvc001ResDto mvc001ResDto = response001dto.getData();

//		Mvc001ResDto.DataHeader mvc001ResDtoDataHeader = mvc001ResDto.getDataHeader();
		Mvc001ResDto.DataBody mvc001ResDtoDataBody = mvc001ResDto.getDataBody();

		String ocrResultJson = mvc001ResDtoDataBody.getImages();

		JsonNode ocrResultReadTrees = objectMapper.readTree(ocrResultJson);

		/*
		if (ocrResultReadTrees.size() != 1) {
			String message = String.format("신분증 OCR 결과가 1건이 아닙니다. (%d건)", ocrResultReadTrees.size());
			throw new IfException(message);
		}
		*/

		JsonNode ocrResultReadTree = ocrResultReadTrees.get(0);

		/*
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

			mvc002ReqDto.setDrvnLcnsSqno("");
			mvc002ReqDto.setDrvnLcnsNo(driverLicenseNumber);
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
	 * 신분증OCR요청
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/idcdOcrRqst"), (API_URL_TOKEN + "/idcdOcrRqst" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "01. 신분증OCR요청")
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
		IfMcCs001_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String ocrResultJson = ifOutputDto.getDataBody().getImages();

		JsonNode ocrResultReadTrees = objectMapper.readTree(ocrResultJson);

		if (ocrResultReadTrees.size() != 1) {
			String message = String.format("신분증 OCR 결과가 1건이 아닙니다. (%d건)", ocrResultReadTrees.size());
			throw new IfException(message);
		}

		JsonNode ocrResultReadTree = ocrResultReadTrees.get(0);

		if (!IfConstant.OcrInferResult.SUCCESS.getResultValue().equals(ocrResultReadTree.get("inferResult").asText())) {
			String message = ocrResultReadTree.get("message").asText();
			message = String.format("이미지 인식을 실패했습니다." + System.lineSeparator() + "(message : %s)", message);
			throw new IfException(message);
		}

		Mvc001ResDto resDto = modelMapper.map(ifOutputDto, Mvc001ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/trflCnfm"), (API_URL_TOKEN + "/trflCnfm" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "02. 진위확인결과조회")
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs002_I ifInputDto = modelMapper.map(reqDto, IfMcCs002_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;
		Class<IfMcCs002_O> ifOutputDtoClass = IfMcCs002_O.class;
		IfMcCs002_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
		/*
		if (!"Y".equalsIgnoreCase(ifOutputDto.getCsnsYn())) {
			throw new IfException(String.format("%s", ifOutputDto.getRsltMsgeCntn()));
		}
		*/
		Mvc002ResDto resDto = modelMapper.map(ifOutputDto, Mvc002ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/itfcIdcdScan"), (API_URL_TOKEN + "/itfcIdcdScan" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "03. 신분증스캔후처리")
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs003_I ifInputDto = modelMapper.map(reqDto, IfMcCs003_I.class);
		ifInputDto.setPushRcvrEmnb(reqDto.getEmnb());

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;
		Class<IfMcCs003_O> ifOutputDtoClass = IfMcCs003_O.class;
		IfMcCs003_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);
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

		IfMcCs005_I ifInputDto = new IfMcCs005_I();
		ifInputDto.setEmnb(emnb);
		ifInputDto.setLognPswd(encLognPswd);

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;
		Class<IfMcCs005_O> ifOutputDtoClass = IfMcCs005_O.class;
		IfMcCs005_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String rspnCodeVal = ifOutputDto.getRspnCodeVal(); // "00":오류, "01":정상

		if (!"01".equalsIgnoreCase(rspnCodeVal)) {
			throw new IfException(String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", ifOutputDto.getRspnMsgeCntn(), ifOutputDto.getRspnMsgeUniqId()));
		}

		Mvc005ResDto resDto = modelMapper.map(ifOutputDto, Mvc005ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/empeInqy"), (API_URL_TOKEN + "/empeInqy" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "06. 사원목록조회")
	public @ResponseBody ResponseDto<Mvc006ResDto> empeInqy(@RequestBody Mvc006ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs006_I ifInputDto = modelMapper.map(reqDto, IfMcCs006_I.class);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;
		Class<IfMcCs006_O> ifOutputDtoClass = IfMcCs006_O.class;
		IfMcCs006_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		if (ifOutputDto.getEmpeInfoList() == null || ifOutputDto.getEmpeInfoList().size() == 0) {
			throw new IfException("조회된 사원이 없습니다.");
		}

		Mvc006ResDto resDto = modelMapper.map(ifOutputDto, Mvc006ResDto.class);

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

		IfMcCs007_I ifInputDto = modelMapper.map(reqDto, IfMcCs007_I.class);
		ifInputDto.setCntcDvsnCode(IfConstant.ContractClassificationCode.INSURANCE.getCode());
		ifInputDto.setCustDvsnCode(IfConstant.CustomerDivisionCode.INDIVIDUAL.getCode());
		ifInputDto.setNextKey("1");
		ifInputDto.setPageSize(50);

		String emnb = reqDto.getEmnb();

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs007;
		Class<IfMcCs007_O> ifOutputDtoClass = IfMcCs007_O.class;
		IfMcCs007_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

//		if (ifOutputDto.getCustCntcInfoInqyRsltList() == null || ifOutputDto.getCustCntcInfoInqyRsltList().size() == 0 || ifOutputDto.getTotCont() == 0) {
		if (ifOutputDto.getCustCntcInfoInqyRsltList() == null || ifOutputDto.getCustCntcInfoInqyRsltList().size() == 0) {
			throw new IfException("조회된 고객계약정보가 없습니다.");
		}

		Mvc007ResDto resDto = modelMapper.map(ifOutputDto, Mvc007ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/intgCust"), (API_URL_TOKEN + "/intgCust" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "08. 고객계좌목록조회")
	public @ResponseBody ResponseDto<Mvc008ResDto> intgCust(@RequestBody Mvc008ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs008_I ifInputDto = modelMapper.map(reqDto, IfMcCs008_I.class);

		String emnb = reqDto.getEmnb();

//		IfMcCs008_O cs008_O = gooroomeeAdapterService.ifmccs008(emnb, cs008_I);
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs008;
		Class<IfMcCs008_O> ifOutputDtoClass = IfMcCs008_O.class;
		IfMcCs008_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		if (ifOutputDto.getCustAcntListInqyList() == null || ifOutputDto.getCustAcntListInqyList().size() == 0) {
			throw new IfException("조회된 고객계좌정보가 없습니다.");
		}

		Mvc008ResDto resDto = modelMapper.map(ifOutputDto, Mvc008ResDto.class);

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

		IfMcCs009_I ifInputDto = modelMapper.map(reqDto, IfMcCs009_I.class);

		String emnb = reqDto.getEmnb();

//		IfMcCs009_O cs009_O = gooroomeeAdapterService.ifmccs009(emnb, cs009_I);
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs009;
		Class<IfMcCs009_O> ifOutputDtoClass = IfMcCs009_O.class;
		IfMcCs009_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		Mvc009ResDto resDto = modelMapper.map(ifOutputDto, Mvc009ResDto.class);

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

		IfMcCs010_I ifInputDto = new IfMcCs010_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

//		IfMcCs010_O cs010_O = gooroomeeAdapterService.ifmccs010(emnb, cs010_I);
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs010;
		Class<IfMcCs010_O> ifOutputDtoClass = IfMcCs010_O.class;
		IfMcCs010_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String crtf_RTCD = ifOutputDto.getDataHeader().getCRTF_RTCD(); // 처리결과코드 ("0000":정상, 그외:실패)
		String dlre_MSG = ifOutputDto.getDataHeader().getDLRE_MSG();
		if (!"0000".equalsIgnoreCase(crtf_RTCD)) {
			throw new IfException(String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", dlre_MSG, crtf_RTCD));
		}

		Mvc010ResDto resDto = modelMapper.map(ifOutputDto, Mvc010ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/initechRequest"), (API_URL_TOKEN + "/initechRequest" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
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

//		IfMcCs011_O cs011_O = gooroomeeAdapterService.ifmccs011(emnb, cs011_I);
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs011;
		Class<IfMcCs011_O> ifOutputDtoClass = IfMcCs011_O.class;
		IfMcCs011_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String resCode = ifOutputDto.getDataBody().getResCode(); // 응답코드 ("1200":성공, 그외:실퍠)
		String errorMessage = ifOutputDto.getDataBody().getErrorMessage(); // 에러메세지
		if (!"1200".equalsIgnoreCase(resCode)) {
			throw new IfException(String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", errorMessage, resCode));
		}

		String crtf_RTCD = ifOutputDto.getDataHeader().getCRTF_RTCD(); // 처리결과코드 ("0000":정상, 그외:실패)
		String dlre_MSG = ifOutputDto.getDataHeader().getDLRE_MSG();
		if (!"0000".equalsIgnoreCase(crtf_RTCD)) {
			throw new IfException(String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", dlre_MSG, crtf_RTCD));
		}

		Mvc011ResDto resDto = modelMapper.map(ifOutputDto, Mvc011ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/initechStatus"), (API_URL_TOKEN + "/initechStatus" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
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

		String initechOAuthToken = reqDto.getInitechOAuthToken();
		dataBody.setInitechOAuthToken(initechOAuthToken);

		String reqTxId = reqDto.getReqTxId();
		dataBody.setReqTxId(reqTxId);

		IfMcCs012_I ifInputDto = new IfMcCs012_I();
		ifInputDto.setDataHeader(dataHeader);
		ifInputDto.setDataBody(dataBody);

		String emnb = reqDto.getEmnb();

//		IfMcCs012_O cs012_O = gooroomeeAdapterService.ifmccs012(emnb, cs012_I);
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs012;
		Class<IfMcCs012_O> ifOutputDtoClass = IfMcCs012_O.class;
		IfMcCs012_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		String resCode = ifOutputDto.getDataBody().getResCode(); // 응답코드 ("1200":성공, 그외:실퍠)
		String errorMessage = ifOutputDto.getDataBody().getErrorMessage(); // 에러메세지
		if (!"1200".equalsIgnoreCase(resCode)) {
			throw new IfException(String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", errorMessage, resCode));
		}

		String crtf_RTCD = ifOutputDto.getDataHeader().getCRTF_RTCD(); // 처리결과코드 ("0000":정상, 그외:실패)
		String dlre_MSG = ifOutputDto.getDataHeader().getDLRE_MSG();
		if (!"0000".equalsIgnoreCase(crtf_RTCD)) {
			throw new IfException(String.format("%s" + System.lineSeparator() + "(에러코드 : %s)", dlre_MSG, crtf_RTCD));
		}

		Mvc012ResDto resDto = modelMapper.map(ifOutputDto, Mvc012ResDto.class);

		ResponseDto<Mvc012ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	/**
	 * 
	 * <pre>
	 * [15]
	 * 카카오알림톡발송_챗버블
	 * </pre>
	 * 
	 * @param reqDto
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(path = { (API_URL_TOKEN + "/cspdDvlmUmsSendMgmt"), (API_URL_TOKEN + "/cspdDvlmUmsSendMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
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
		ifInputDto.setDutySendYn("Y");
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
		IfMcCs015_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		double dvsnRsltVal = ifOutputDto.getDvsnRsltVal();
		int smsTrnmRsltVal = ifOutputDto.getSmsTrnmRsltVal();

		if (0.0 != dvsnRsltVal) {
			throw new IfException("알림톡 발송에 실패했습니다.");
		} else if (0 != smsTrnmRsltVal) {
			throw new IfException("SMS 발송에 실패했습니다.");
		}

		Mvc015ResDto resDto = modelMapper.map(ifOutputDto, Mvc015ResDto.class);

		ResponseDto<Mvc015ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/prsnCustMgmt"), (API_URL_TOKEN + "/prsnCustMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "16. 대체키별연락처저장")
	public @ResponseBody ResponseDto<Mvc016ResDto> prsnCustMgmt(@RequestBody Mvc016ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs016_I ifInputDto = modelMapper.map(reqDto, IfMcCs016_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;
		Class<IfMcCs016_O> ifOutputDtoClass = IfMcCs016_O.class;
		IfMcCs016_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		Mvc016ResDto resDto = modelMapper.map(ifOutputDto, Mvc016ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/cnplSbsnKeyMgmt"), (API_URL_TOKEN + "/cnplSbsnKeyMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "17. 대체키별연락처조회")
	public @ResponseBody ResponseDto<Mvc017ResDto> cnplSbsnKeyMgmt(@RequestBody Mvc017ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs017_I ifInputDto = modelMapper.map(reqDto, IfMcCs017_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs017;
		Class<IfMcCs017_O> ifOutputDtoClass = IfMcCs017_O.class;
		IfMcCs017_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		if (ifOutputDto.getCnplSuid() == null) {
			throw new IfException("조회된 연락처가 없습니다.");
		}

		Mvc017ResDto resDto = modelMapper.map(ifOutputDto, Mvc017ResDto.class);

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
	@RequestMapping(path = { (API_URL_TOKEN + "/addrMgmt"), (API_URL_TOKEN + "/addrMgmt" + MockUtil.URL_SUFFIX_FOR_MOCK) }, method = {
			RequestMethod.POST }, name = "18. 우편번호조회")
	public @ResponseBody ResponseDto<Mvc018ResDto> addrMgmt(@RequestBody Mvc018ReqDto reqDto, HttpServletRequest request)
			throws URISyntaxException, IOException {

		IfMcCs018_I ifInputDto = modelMapper.map(reqDto, IfMcCs018_I.class);

		String emnb = reqDto.getEmnb();
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs018;
		Class<IfMcCs018_O> ifOutputDtoClass = IfMcCs018_O.class;
		IfMcCs018_O ifOutputDto = gooroomeeAdapterService.ifmccsCommon(emnb, ifSpec, ifInputDto, ifOutputDtoClass);

		if (ifOutputDto.getPscdList() == null || ifOutputDto.getPscdList().size() == 0) {
			throw new IfException("조회된 우편번호가 없습니다.");
		}

		Mvc018ResDto resDto = modelMapper.map(ifOutputDto, Mvc018ResDto.class);

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
	 * @throws IOException
	 */
	@RequestMapping(path = { "/test/apis" }, method = { RequestMethod.GET })
	public String getApiTestView(Model model) throws IOException {

		model.addAttribute("apiAuthEnabled", apiAuthEnabled);
		model.addAttribute("apiAuthKey", apiAuthKey);
		model.addAttribute("apiInfoList", this.getApiInfoList());
		model.addAttribute("idCardMockImageInfoList", this.getIdCardMockImageInfoList());
		model.addAttribute("urlForRequestMockData", URL_FOR_REQUEST_MOCK_DATA);
		model.addAttribute("paramNameForRequestMockData", PARAM_NAME_FOR_REQUEST_MOCK_DATA);

		return "test/apis";
	}

	@RequestMapping(path = { URL_FOR_REQUEST_MOCK_DATA }, method = { RequestMethod.GET })
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
							if (requestBody != null) {
								ObjectMapper objectMapper = new ObjectMapper();

								Class<?> parameterType = parameter.getType();

								Object mockRequestDataObject = MockUtil.getMockRequestData(methodName, parameterType);
								Map<String, Object> mockRequestDataMap = objectMapper.convertValue(mockRequestDataObject,
										new TypeReference<Map<String, Object>>() {
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

	public List<Map<String, String>> getIdCardMockImageInfoList() throws IOException {
		List<Map<String, String>> idCardMockImageInfoList = new ArrayList<>();

		/*
		String mockImageRootPath = MOCK_IMAGE_ROOT_PATH;
		File mockImageRootPathDirectory = new ClassPathResource(mockImageRootPath).getFile();
		String[] mockImageDataFileNames = mockImageRootPathDirectory.list((dir, name) -> {
			
			boolean endsWith = name.endsWith(IMAGE_DATA_FILE_EXTENSION);
			
			return endsWith;
		});
		
		List<String> mockImageDataFileNameList = Arrays.asList(mockImageDataFileNames);
		mockImageDataFileNameList.sort(new Comparator<String>() {
			@Override
			public int compare(String mockImageDataFileName1, String mockImageDataFileName2) {
				return mockImageDataFileName1.compareTo(mockImageDataFileName2);
			}
		});
		
		for (String mockImageDataFileName : mockImageDataFileNameList) {
			File mockImageDataFile = new File(mockImageRootPath, mockImageDataFileName);
			ClassPathResource resource = new ClassPathResource(mockImageDataFile.toString());
			
			Stream<String> lineStream = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8")).lines();
			List<String> lineList = lineStream.collect(Collectors.toList());
			String idCardMockImageBase64Data = String.join("", lineList);
			
			Map<String, String> idCardMockImageInfoMap = new HashMap<>();
			String mockImageName = mockImageDataFileName.replaceAll("." + IMAGE_DATA_FILE_EXTENSION + "$", "");
			idCardMockImageInfoMap.put("mockImageName", mockImageName);
			idCardMockImageInfoMap.put("mockImageDataFileName", mockImageDataFileName);
			idCardMockImageInfoMap.put("idCardMockImageBase64Data", idCardMockImageBase64Data);
			
			idCardMockImageInfoList.add(idCardMockImageInfoMap);
		}
		*/

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

}
