package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 진위확인결과조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs002_O {
	
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
	
	/** 거래고유번호 */
	private String trnnUniqNo;
}
