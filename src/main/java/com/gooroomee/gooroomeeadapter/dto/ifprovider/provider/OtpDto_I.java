package com.gooroomee.gooroomeeadapter.dto.ifprovider.provider;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpDto_I {
	
	/** 요청 기관 */
	private String requestAgency;
	
	/** 고객 이름 */
	private String username;
	
	/** 고객 ID */
	private String apiUserId;
	
	/** 고객 전화번호 1 */
	private String phoneNumber1;
	
	/** 고객 전화번호 2 */
	private String phoneNumber2;
	
	/** 고객 전화번호 3 */
	private String phoneNumber3;

}
