package com.gooroomee.backbone.external.dto.ifconsumer.client;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - SMS 메세지 발송
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
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
