package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc015ResDto {

	/**
	 * SMS전송결과값
	 * 성공 : 0 
	 * 실패 : 음수(소수점 없음)
	 */
    private int smsTrnmRsltVal;

    /** 처리결과내용 */
    private String prcsRsltCntn;
    
    /** 요청서비스ID */
    private String rqstSrvcId;
    
    /** 체크리스트내용 */
    private String chekLstCntn;
    
    /**
     * 구분결과값
     * 성공 : 0.0
     * 실패 : 음수(소수점 포함)
     */
    private double dvsnRsltVal;
    
    /** 구분결과값 Y/N (성공여부 Y/N) */
    private String dvsnRsltValYn;

}
