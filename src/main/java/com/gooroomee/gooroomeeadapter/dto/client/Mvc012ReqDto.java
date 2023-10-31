package com.gooroomee.gooroomeeadapter.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc012ReqDto {
	
	/** 사원번호 */
	public String emnb;
	
	/** 화면 ID */
	@JsonProperty("SCRN_ID")
	private String SCRN_ID;
	
	/** 사용자ID */
	@JsonProperty("USER_ID")
	private String USER_ID;
	
	/** 이니텍인증토큰 */
	private String initechOAuthToken;
	
	/** 트랜잭션ID */
	private String reqTxId;
	
}
