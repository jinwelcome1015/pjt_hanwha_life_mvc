package com.gooroomee.gooroomeeadapter.dto.ifprovider.consumer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpReqDto {
	
	/** 요청 기관 */
	private String rqstOrgnCode;

	/** 고객 이름 */
	private String custNm;
	
	/** 고객 ID */
	private String custId;
	
	/** 휴대폰전화가입자번호 */
    private String hpTlphSbno;
    
    /** 휴대폰전화국번호 */
    private String hpTlphOfno;
    
    /** 휴대폰전화통신사번호 */
    private String hpTlphTlcmNo;

}
