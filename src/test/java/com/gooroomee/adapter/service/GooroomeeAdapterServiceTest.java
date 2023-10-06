package com.gooroomee.adapter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gooroomee.adapter.dto.io.IfMcCs012_I;

@SpringBootTest
public class GooroomeeAdapterServiceTest {
	/**
	 * 인터페이스 엔드포인트 URL
	 */
	@Value(value = "${spring.profiles.active}")
	private String activeProfile;

	/**
	 * 인터페이스 엔드포인트 URL
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.url']}")
	private String ifEndpointUrl;

	/**
	 * 인터페이스 엔드포인트 IP
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.ip']}")
	private String ifEndpointIp;

	/**
	 * 인터페이스 엔드포인트 PORT
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.port']}")
	private String ifEndpointPort;

	/**
	 * OCR URL
	 */
	@Value(value = "#{propertiesFactoryBean['interface.ocr.url']}")
	private String ocrUrl;

	/**
	 * OCR SECRET KEY
	 */
	@Value(value = "#{propertiesFactoryBean['interface.ocr.secret-key']}")
	private String ocrSecretKey;

	/**
	 * 암호화 AES_KEY
	 */
	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-key']}")
	private String encryptAesKey;

	/**
	 * 암호화 AES_IV
	 */
	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-iv']}")
	private String encryptAesIv;

	/**
	 * 기관 코드
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.orgn-code']}")
	private String orgnCode;

	/**
	 * 서비스 ID
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.srvc-id']}")
	private String srvcId;

	/**
	 * 송신 시스템 코드
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.send-system-code']}")
	private String sendSystemCode;

	/**
	 * 화면 ID
	 */
	private static final String SCRN_ID = "";
	
	
	
	
	
	@Autowired
	private GooroomeeAdapterService gooroomeeAdapterService;
	
	@Test
	public void doTest1() throws JsonProcessingException {
		System.out.println("111");
		IfMcCs012_I cs012_I = new IfMcCs012_I();
		gooroomeeAdapterService.ifmccs012(cs012_I);
	}
}
