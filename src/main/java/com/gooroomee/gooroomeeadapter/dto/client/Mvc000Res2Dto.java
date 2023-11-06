package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Data;

import java.sql.Date;

@Data
public class Mvc000Res2Dto {
    /** 사원번호 */
    public String emnb;
    /** 신분증 타입 */
    private String idType;
    /** 고객명 */
    private String custNm;
    /** 생년월일 */
    private Date btdt;
    /** 일치여부 */
    private String csnsYn;
    /** 진위확인결과값 */
    private String trflCnfmRsltVal;
    /** 결과메시지내용 */
    private String rsltMsgeCntn;
    /** 고객ID */
    private String custId;
    /** 처리성공여부 */
    private String prcsSucsYn;
    /** 유출여부 */
    private String leakYn;
    /** 노출여부 */
    private String epsrYn;
    /** 휴대폰번호변경여부 */
    private String hpnoChngYn;
}
