package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * API - 진위확인결과조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc002ResDto {

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
