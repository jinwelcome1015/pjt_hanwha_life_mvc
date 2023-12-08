package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
