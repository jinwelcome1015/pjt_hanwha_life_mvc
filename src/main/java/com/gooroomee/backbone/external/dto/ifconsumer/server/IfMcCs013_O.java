package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.math.BigInteger;
import java.util.List;

import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfMcCs013_O {
    
    /** 조회검색조건값 */
    private String inqySrchCndtVal;
    
    /** 고객ID */
    private String custId;
    
    /** 고객주민등록번호 */
    private String custRrno;
    
    /** 고객명 */
    private String custNm;
    
    /** 증권번호 */
    private String polyNo;
    
    /** 고객나이 */
    private int custAg;
    
    /** 성별구분코드 */
    private String gndrDvsnCode;
    
    /** VIP등급명 */
    private String vipGradNm;
    
    /** 생년월일 */
    private String btdt;
    
    /** 생년월일값 */
    private String btdtVal;
    
    /** 고객생년월일 */
    private String custBtdt;
    
    /** 생년월일음력양력구분코드 */
    private String btdtLunrSlcdDvsnCode;
    
    /** 휴대폰사업자번호 */
    private String hpBsno;
    
    /** 휴대폰국번호 */
    private String hpOfno;
    
    /** 휴대폰개별번호 */
    private String hpInno;
    
    /** 전화식별코드 */
    private String tlphIdnfCode;
    
    /** 전화국번호 */
    private String tlphOfno;
    
    /** 전화가입자번호 */
    private String tlphSbno;
    
    /** 우편번호 */
    private String pscd;
    
    /** 기본주소 */
    private String baseAddr;
    
    /** 상세주소 */
    private String detlAddr;
    
    /** 이메일주소 */
    private String mailAddr;
    
    /** 이메일수신동의여부 */
    private String mailRcveAgrmYn;
    
    /** 현재비밀번호입력일시 */
    private String crntPswdInptDttm;
    
    /** 현재비밀번호입력기관명 */
    private String crntPswdInptOrgnNm;
    
    /** 보안카드등록일시 */
    private String scutCardRgstDttm;
    
    /** 보안카드발급기관코드명 */
    private String scutCardIsncOrgnCodeNm;
    
    /** 보험카드발급일자 */
    private String isrnCardIsncDate;
    
    /** 보험카드발급기관명 */
    private String isrnCardIsncOrgnNm;
    
    /** 처리기관명 */
    private String prcsOrgnNm;
    
    /** 폐기상세코드 */
    private String duseDetlCode;
    
    /** 카드상태코드 */
    private String cardSttsCode;
    
    /** 처리일자 */
    private String prcsDate;
    
    /** 고객확인일자 */
    private String custCnfmDate;
    
    /** 고객확인결과코드 */
    private String custCnfmRsltCode;
    
    /** 인터넷창구접속일자 */
    private String intnWndwCnncDate;
    
    /** 보험월렛접속일자 */
    private String isrnWaltCnncDate;
    
    /** 가상계좌사용여부 */
    private String vrtlAcntUseYn;
    
    /** 수신여부 */
    private String rcveYn;
    
    /** 보험계약대출건수 */
    private int polnCont;
    
    /** 퇴직연금건수 */
    private int rtpnCont;
    
    /** 계약건수 */
    private int cntcCont;
    
    /** 연락처관계구분코드 */
    private String cnplRltsDvsnCode;
    
    /** 마케팅동의여부 */
    private String mrktAgrmYn;
    
    /** 조회건수 */
    private int inqyCont;
    
    /** 비밀번호유효여부 */
    private String pswdValdYn;
    
    /** 고객비밀번호처리구분코드 */
    private String custPswdPrcsDvsnCode;
    
    /** 조회오류건수 */
    private int inqyErorCont;
    
    /** 조회결과코드 */
    private String inqyRsltCode;
    
    /** 조회결과문구내용 */
    private String inqyRsltSntnCntn;
    
    /** 사용여부 */
    private String useYn;
    
    /** 파기여부 */
    private String dstcYn;
    
    /** 1번째우편번호 */
    private String odn1Pscd;
    
    /** 2번째우편번호 */
    private String odn2Pscd;
    
    /** 3번째우편번호 */
    private String odn3Pscd;
    
    /** 도로명주소여부 */
    private String roadNmAddrYn;
    
    
    
    
    /** 휴대폰사업자번호 (등록된) */
    private String registeredHpNo1;
    
    /** 휴대폰국번호 (등록된) */
    private String registeredHpNo2;
    
    /** 휴대폰개별번호 (등록된) */
    private String registeredHpNo3;

    /** 등록된 휴대폰 번호와 일치여부 Y/N */
    private String registeredHpNoMatchYn;
    
    
    
    
    
    /** 진위확인이력 */
    private TrflCnfmHist trflCnfmHist;
    


	@Getter
	@Setter
	@ToString
	public static class TrflCnfmHist {
        
        /** 진위확인이력SUID */
        private BigInteger trflCnfmHistSuid;
        
        /** 처리일자 */
        private String prcsDate;
        
        /** 처리자사원번호 */
        private String hndrEmnb;
        
        /** 처리자명 */
        private String hndrNm;
        
        /** 진위확인구분코드 */
        private String trflCnfmDvsnCode;
        
        /** 전문추적번호 */
        private String tlgrTrcgNo;
        
        /** 진위확인작업코드 */
        private String trflCnfmJobCode;
        
        /** 진위확인채널코드 */
        private String trflCnfmChnlCode;
        
        /** 고객ID */
        private String custId;
        
        /** 고객명 */
        private String custNm;
        
        /** 고객식별자값 */
        private String custIdnrVal;
        
        /** 관리번호 */
        private String mgmtNo;
        
        /** 주민등록번호 */
        private String rrno;
        
        /** 운전면허번호 */
        private String drvnLcnsNo;
        
        /** 외국인등록번호 */
        private String frnrRgstNo;
        
        /** 여권번호 */
        private String psprNo;
        
        /** 암호화대상항목명 */
        private String encrTrgtItmNm;
        
        /** 1번째데이터항목명 */
        private String odn1DatItmNm;
        
        /** 2번째데이터항목명 */
        private String odn2DatItmNm;
        
        /** 3번째데이터항목명 */
        private String odn3DatItmNm;
        
        /** 4번째데이터항목명 */
        private String odn4DatItmNm;
        
        /** 5번째데이터항목명 */
        private String odn5DatItmNm;
        
        /** 6번째데이터항목명 */
        private String odn6DatItmNm;
        
        /** 7번째데이터항목명 */
        private String odn7DatItmNm;
        
        /** 8번째데이터항목명 */
        private String odn8DatItmNm;
        
        /** 9번째데이터항목명 */
        private String odn9DatItmNm;
        
        /** 10번째데이터항목명 */
        private String on10DatItmNm;
        
        /** 연속오류건수 */
        private int cnnsErorCont;
        
        /** 일치여부 */
        private String csnsYn;
        
        /** 진위확인결과값 */
        private String trflCnfmRsltVal;
        
        /** 결과메시지내용 */
        private String rsltMsgeCntn;
        
        /** 진위확인업무구분코드 */
        private String trflCnfmBswrDvsnCode;
        
        /** 처리업무화면ID */
        private String prcsBswrScrnId;
        
        /** 조회일시 */
        private String inqyDttm;
        
        /** 시작일자 */
        private String starDate;
        
        /** 종료일자 */
        private String endDate;

	}

}
