package com.gooroomee.backbone.external.dto.ifconsumer.client;

import java.math.BigInteger;
import java.util.List;

import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc013ResDto {
    
    /** 조회검색조건값 */
    private String inqySrchCndtVal;
    
    /** 고객ID */
    private String custId;
    
//    /** 고객주민등록번호 */
//    private String custRrno;
    
    /** 고객명 */
    private String custNm;
    
    /** 휴대폰사업자번호 (등록된) */
    private String registeredHpNo1;
    
    /** 휴대폰국번호 (등록된) */
    private String registeredHpNo2;
    
    /** 휴대폰개별번호 (등록된) */
    private String registeredHpNo3;

    /** 등록된 휴대폰 번호와 일치여부 Y/N */
    private String registeredHpNoMatchYn;
}
