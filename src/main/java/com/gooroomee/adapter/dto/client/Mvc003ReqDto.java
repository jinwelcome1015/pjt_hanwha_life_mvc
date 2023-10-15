package com.gooroomee.adapter.dto.client;

import com.gooroomee.adapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc003ReqDto extends ReqSuperDto{
	
	/** 고객ID */
	private String custId;
	
	/** 푸시수신자사원번호 */
	private String pushRcvrEmnb;
	
	/** 일치여부 */
	private String csnsYn;

}
