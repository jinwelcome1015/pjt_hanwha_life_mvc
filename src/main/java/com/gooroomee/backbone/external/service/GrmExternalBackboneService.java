package com.gooroomee.backbone.external.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gooroomee.backbone.external.constant.IfConstant.IfSpec;
import com.gooroomee.backbone.external.dto.ifprovider.provider.OtpDto_I;
import com.gooroomee.backbone.external.dto.ifprovider.provider.common.IfProviderResponseCommonDto;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs999_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs999_O;

/**
 * 서비스 인터페이스
 * @author 신용진
 */
public interface GrmExternalBackboneService {

	/**
	 * 이 어플리케이션이 consumer가 되어, 보험코어 인터페이스 엔드포인트에 요청을 하기 위한 공통 메서드
	 * @param <I> 요청 DTO 타입
	 * @param <O> 응답 DTO 타입
	 * @param emnb 사원번호
	 * @param ifSpec 인터페이스 명세
	 * @param ifInputDto 요청 DTO 객체
	 * @param ifOutputDtoClass 응답 DTO 클래스객체
	 * @return 응답 DTO 객체
	 * @throws JsonProcessingException
	 * @throws URISyntaxException
	 */
	<I, O> O ifmccsCommon(String emnb, IfSpec ifSpec, I ifInputDto, Class<O> ifOutputDtoClass) throws JsonProcessingException, URISyntaxException;

	
	
	/**
	 * 이 어플리케이션이 provider가 되어, 구루미 코어로 부터 발급받은 구루미 입장 URI를 consumer에 응답으로 전달하기 위한 인터페이스
	 * @param dto_I 구루미 입장 URI 발급 요청 DTO 객체
	 * @return 구루미 입장 URI 발급 응답 DTO 객체
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	IfProviderResponseCommonDto<String> counsellingOtp(OtpDto_I dto_I) throws JsonMappingException, JsonProcessingException, URISyntaxException, IOException;

	
	
	/**
	 * 이미지 시스템에 이미지를 등록하기 위한 인터페이스
	 * @param imgSysInput 이미지 시스템 등록 요청 DTO 객체
	 * @return 이미지 시스템 등록 응답 DTO 객체
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	IfMcCs999_O rgstrImgSys(IfMcCs999_I imgSysInput) throws URISyntaxException, IOException;

}