package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
