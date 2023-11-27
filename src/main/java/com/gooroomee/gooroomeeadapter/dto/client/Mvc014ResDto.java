package com.gooroomee.gooroomeeadapter.dto.client;

import java.math.BigInteger;
import java.util.List;

import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc014ResDto {
    
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

}
