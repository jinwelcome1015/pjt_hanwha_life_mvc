package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.math.BigInteger;
import java.util.List;

import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * INTERFACE - SMS 메세지 발송 
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs014_O {
    
    /** 요청서비스ID */
    private String rqstSrvcId;
    
    /** 체크리스트내용 */
    private String chekLstCntn;
    
    /** 처리결과내용 */
    private String prcsRsltCntn;
    
    /** 구분결과값 */
    private double dvsnRsltVal;
    
    /** SMS전송결과값 */
    private int smsTrnmRsltVal;

    /** 구분결과값 Y/N (성공여부 Y/N) */
    private String dvsnRsltValYn;
    
}
