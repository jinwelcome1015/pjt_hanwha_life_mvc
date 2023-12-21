package com.gooroomee.backbone.external.dto.ifprovider.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 실제로 사용하지 않는 DTO. 
 * 요청(OtpDto_I)과 응답(OtpDto_O)의 쌍을 맞추기 위해 생성.
 * OTP의 경우, 실제 응답은 String 타입이다.
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Grm001Dto_O {
	
	/** 구분값 */
	private String code;
	
	/** 결과메시지내용 */
	private String message;
	
	/** 결과데이터값 */
	private String data;
	
}
