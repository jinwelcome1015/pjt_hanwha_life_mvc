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
public class Mvc013ResDto {
    
    /** 조회검색조건값 */
    private String inqySrchCndtVal;
    
    /** 고객ID */
    private String custId;
    
//    /** 고객주민등록번호 */
//    private String custRrno;
    
    /** 고객명 */
    private String custNm;
    
    /** 휴대폰사업자번호 */
    private String hpBsno;
    
    /** 휴대폰국번호 */
    private String hpOfno;
    
    /** 휴대폰개별번호 */
    private String hpInno;

}
