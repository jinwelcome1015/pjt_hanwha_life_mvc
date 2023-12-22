package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * INTERFACE - 고객통합기본정보조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs013_I {
    
    /** 조회검색조건값 */
    private String inqySrchCndtVal;
    
    /** 고객ID */
    private String custId;
    
    /** 증권번호 */
    private String polyNo;
    
    /** 주민등록번호 */
    private String rrno;
}
