package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
