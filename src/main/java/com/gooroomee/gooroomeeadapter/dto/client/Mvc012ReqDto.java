package com.gooroomee.gooroomeeadapter.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc012ReqDto {
	
	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	public String emnb;
	
	/** 사용자ID */
	@JsonProperty("USER_ID")
	private String USER_ID;
	
	/** 이니텍인증토큰 */
	private String initechOAuthToken;
	
	/** 트랜잭션ID */
	private String reqTxId;
	
}
