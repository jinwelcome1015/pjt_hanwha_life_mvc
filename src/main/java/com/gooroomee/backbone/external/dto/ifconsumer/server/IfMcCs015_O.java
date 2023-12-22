package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 카카오알림톡발송_챗버블 
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs015_O {

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

}
