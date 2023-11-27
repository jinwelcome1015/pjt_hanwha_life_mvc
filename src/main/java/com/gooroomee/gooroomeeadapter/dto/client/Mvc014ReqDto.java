package com.gooroomee.gooroomeeadapter.dto.client;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc014ReqDto {
	
	/** 사원번호 */
	private String emnb;
	
	/** 인증번호 */
	private String authenticationNumber;
    
    /** 발송사원명 */
    private String sendEmpeNm;
    
    /** 고객ID */
    private String custId;
    
    /** 휴대폰전화가입자번호 */
    private String hpTlphSbno;
    
    /** 휴대폰전화국번호 */
    private String hpTlphOfno;
    
    /** 휴대폰전화통신사번호 */
    private String hpTlphTlcmNo;
    
    /** 수신자명 */
    private String rcvrNm;

}
