package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 권한별사용자조회 
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs004_I {
    
    /** 보험코어권한ID */
    private String isrnCoreAtrtId;

    /** 기관코드 */
    private String orgnCode;
    
    /** nextKey */
    private String nextKey;
    
    /** pageSize */
    private int pageSize;

}
