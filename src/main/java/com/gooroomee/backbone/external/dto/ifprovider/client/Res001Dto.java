package com.gooroomee.backbone.external.dto.ifprovider.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * PROVIDER INTERFACE - 화상상담서비스 진입 URI 조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Res001Dto {
	
	/** 구분값 */
	private String dvsnVal;
	
	/** 결과메시지내용 */
	private String rsltMsgeCntn;
	
	/** 결과데이터값 */
	private String rsltDatVal;
	
}
