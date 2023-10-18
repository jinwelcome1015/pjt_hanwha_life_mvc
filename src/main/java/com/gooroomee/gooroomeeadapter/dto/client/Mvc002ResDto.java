package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ResSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc002ResDto extends ResSuperDto {
	
	/** 일치여부 */
	private String csnsYn;

	/** 진위확인결과값 */
	private String trflCnfmRsltVal;
	
	/** 결과메시지내용 */
	private String rsltMsgeCntn;
	
	/** 전문추적번호 */
	private String tlgrTrcgNo;
	
	/** 고객ID */
	private String custId;
}