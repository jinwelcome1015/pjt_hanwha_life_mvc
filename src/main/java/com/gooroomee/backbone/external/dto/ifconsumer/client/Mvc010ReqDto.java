package com.gooroomee.backbone.external.dto.ifconsumer.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 간편인증 토큰발급
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc010ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 화면 ID */
	@JsonProperty("SCRN_ID")
	private String SCRN_ID;

	/** 사용자ID */
	@JsonProperty("USER_ID")
	private String USER_ID;

}
