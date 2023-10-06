package com.gooroomee.adapter.dto.common;

import lombok.Data;

@Data
public class Request012Dto {
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
