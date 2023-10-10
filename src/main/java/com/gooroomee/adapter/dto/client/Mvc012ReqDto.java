package com.gooroomee.adapter.dto.client;

import lombok.Data;

@Data
public class Mvc012ReqDto {
	/**
	 * 사용자ID
	 */
	private String userId;
	
	/**
	 * 이니텍인증토큰
	 */
	private String initechOAuthToken;
	
	/**
	 * 트랜잭션ID
	 */
	private String reqTxId;
}
