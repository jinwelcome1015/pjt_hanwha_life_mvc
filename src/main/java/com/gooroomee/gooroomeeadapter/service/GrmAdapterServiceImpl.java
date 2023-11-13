package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
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
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.util.IfUtil;

@Service
public class GrmAdapterServiceImpl implements GrmAdapterService {

	@Autowired
	private RestTemplate restTemplate;

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

	public <I, O> O ifmccsCommon(String emnb, IfSpec ifSpec, I ifInputDto, Class<O> ifOutputDtoClass) throws JsonProcessingException, URISyntaxException {

		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);

		IfTelegramHeader inputTelegramHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

		IfTelegram<O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputTelegramHeader, ifInputDto, ifOutputDtoClass);

		O ifOutputDto = outputTelegram.getPayload();

		return ifOutputDto;
	}

}
