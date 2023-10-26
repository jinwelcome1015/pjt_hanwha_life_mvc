package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc007ReqDto extends ReqSuperDto {
	
	/** 고객ID */
	private String custId;
	
	/** 증권번호 */
	private String polyNo;
	
	/** 계약구분코드 */
	private String cntcDvsnCode;
	
	/** 고객구분코드 */
	private String custDvsnCode;

}
