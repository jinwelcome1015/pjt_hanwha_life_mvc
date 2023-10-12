package com.gooroomee.adapter.dto.client;

import com.gooroomee.adapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc012ReqDto extends ReqSuperDto{
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
