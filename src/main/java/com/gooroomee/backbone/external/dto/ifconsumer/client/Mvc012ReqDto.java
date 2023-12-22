package com.gooroomee.backbone.external.dto.ifconsumer.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 간편인증 상태 조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc012ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 화면 ID */
	@JsonProperty("SCRN_ID")
	private String SCRN_ID;

	/** 사용자ID */
	@JsonProperty("USER_ID")
	private String USER_ID;

//	/** 이니텍인증토큰 */
//	private String initechOAuthToken;

	/** 트랜잭션ID */
	private String reqTxId;
	
	/** 액세스토큰 */
	private String access_token;

	/** 토큰타입 */
	private String token_type;

}
