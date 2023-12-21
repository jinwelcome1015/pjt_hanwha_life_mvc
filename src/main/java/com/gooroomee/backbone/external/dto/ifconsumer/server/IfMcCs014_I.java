package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfMcCs014_I {
    
    /** 발송건수 */
    private BigInteger sendCont;
    
    /** 작업메시지내용 */
    private String jobMsgeCntn;
    
    /** 발송사원번호 */
    private String sendEmnb;
    
    /** 발송사원명 */
    private String sendEmpeNm;
    
    /** 발신자부서코드 */
    private String sndeDeptCode;
    
    /** 발신자부서명 */
    private String sndeDeptNm;
    
    /** 소속기관코드 */
    private String belnOrgnCode;
    
    /** 고객전화번호 */
    private String custTlno;
    
    /** 발신자이메일주소 */
    private String sndeMailAddr;
    
    /** 이메일템플릿명 */
    private String mailTmplNm;
    
    /** 알림톡템플릿명 */
    private String nttkTmplNm;
    
    /** 거래프로그램ID */
    private String trnnPrgmId;
    
    /** 주석내용 */
    private String cmntCntn;
    
    /** 대체발송예약시간 */
    private String sbsnSendRsvtTme;
    
    /** 신청자정보내용 */
    private String apctInfoCntn;
    
    /** 업무명 */
    private String bswrNm;
    
    /** 수신확인여부 */
    private String rcveCnfmYn;
    
    /** 고객ID */
    private String custId;
    
    /** 가입대상증권번호 */
    private String joinTrgtPolyNo;
    
    /** 대출번호 */
    private String loanNo;
    
    /** 휴대폰전화가입자번호 */
    private String hpTlphSbno;
    
    /** 휴대폰전화국번호 */
    private String hpTlphOfno;
    
    /** 휴대폰전화통신사번호 */
    private String hpTlphTlcmNo;
    
    /** 발신자전화지역번호 */
    private String sndeTlphArcd;
    
    /** 발신자전화국번호 */
    private String sndeTlphOfno;
    
    /** 발신자전화개별번호 */
    private String sndeTlphInno;
    
    /** 안내장종류코드 */
    private String ntfcKindCode;
    
    /** 수신자명 */
    private String rcvrNm;

}
