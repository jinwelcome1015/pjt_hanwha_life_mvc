package com.gooroomee.adapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs002_O {
	
	/**
	 * 일치 여부
	 */
	private String csnsYn;
	
	/**
	 * 진위 확인 결과 값
	 */
	private String trflCnfmRsltVal;
	
	/**
	 * 결과 메시지 내용
	 */
	private String rsltMsgeCntn;
	
	/**
	 * 전문 추적 번호
	 */
	private String tlgrTrcgNo;
	
	/**
	 * 고객 ID
	 */
	private String custId;
	
}
