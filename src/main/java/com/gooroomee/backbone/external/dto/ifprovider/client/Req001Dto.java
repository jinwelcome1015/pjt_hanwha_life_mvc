package com.gooroomee.backbone.external.dto.ifprovider.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * PROVIDER INTERFACE - 화상상담서비스 진입 URI 조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Req001Dto {
	
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
