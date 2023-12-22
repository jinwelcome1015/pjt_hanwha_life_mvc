package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 고객휴대폰번호조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc013ReqDto {
	
	/** 사원번호 */
	private String emnb;
	
//    /** 조회검색조건값 */
//    private String inqySrchCndtVal;
    
    /** 고객ID */
    private String custId;
    
//    /** 증권번호 */
//    private String polyNo;
    
    /** 주민등록번호 */
    private String rrno;
    
    /** 휴대폰사업자번호  */
    private String hpNo1;
    
    /** 휴대폰국번호 */
    private String hpNo2;
    
    /** 휴대폰개별번호 */
    private String hpNo3;
}
