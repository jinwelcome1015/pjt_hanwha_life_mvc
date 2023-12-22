package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * API - 신분증스캔후처리
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc003ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 고객ID */
	private String custId;

//	/** 푸시수신자사원번호 */
//	private String pushRcvrEmnb;

	/** 일치여부 */
	private String csnsYn;

}
