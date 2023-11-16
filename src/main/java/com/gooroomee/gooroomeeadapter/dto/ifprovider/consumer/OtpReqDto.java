package com.gooroomee.gooroomeeadapter.dto.ifprovider.consumer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpReqDto {
	
	/** 고객 이름 */
	private String custNm;
	
	/** 고객 ID */
	private String custId;
	
	/** 고객 전화번호 */
	private String custTlno;
	
	/** 요청 기관 */
	private String requestOrgnCode;

}
