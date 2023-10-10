package com.gooroomee.adapter.dto.client;

import com.gooroomee.adapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc003ReqDto extends ReqSuperDto{
	/**
	 * 고객 ID
	 */
	private String custId;
	
	/**
	 * 푸시 수신자 사원 번호
	 */
	private String pushRcvrEmnb;
	
	/**
	 * 일치 여부
	 */
	private String csnsYn;
}
