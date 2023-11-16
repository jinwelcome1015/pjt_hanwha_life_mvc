package com.gooroomee.gooroomeeadapter.dto.ifprovider.provider;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpDto_I {
	
	/** 고객 이름 */
	private String username;
	
	/** 고객 ID */
	private String apiUserId;
	
	/** 고객 전화번호 */
	private String phoneNumber;
	
	/** 요청 기관 */
	private String requestAgency;

}
