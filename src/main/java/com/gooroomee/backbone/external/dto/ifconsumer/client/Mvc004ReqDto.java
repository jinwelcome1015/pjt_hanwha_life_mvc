package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 권한별사용자조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc004ReqDto {
	
	/** 사원번호 */
	private String emnb;

	/** 보험코어권한ID */
    private String isrnCoreAtrtId;

    /** 기관코드 */
    private String orgnCode;
    
    /** nextKey */
    private String nextKey;

    /** pageSize */
    private int pageSize;

}
