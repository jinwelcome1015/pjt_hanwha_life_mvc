package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfMcCs015_I {
    
    /** 발송건수 */
    private long sendCont;
    
    /** 작업메시지내용 */
    private String jobMsgeCntn;
    
    /** 거래프로그램ID */
    private String trnnPrgmId;
    
    /** 발신자부서코드 */
    private String sndeDeptCode;
    
    /** 발송예약일시 */
    private Timestamp sendRsvtDttm;
    
    /** 안내장템플릿코드 */
    private String ntfcTmplCode;
    
    /** 수신자전화번호 */
    private String rcvrTlno;
    
    /** 배치처리여부 */
    private String btchPrcsYn;
    
    /** 메시지제목명 */
    private String msgeTitlNm;
    
    /** 발신자전화번호 */
    private String sndeTlno;
    
    /** 대체발송여부 */
    private String sbsnSendYn;
    
    /** 온라인배치구분코드 */
    private String onlnBtchDvsnCode;
    
    /** 안내장매체구분코드 */
    private String ntfcMdiaDvsnCode;
    
    /** 고객ID */
    private String custId;
    
    /** 템플릿코드 */
    private String tmplCode;
    
    /** 수신자명 */
    private String rcvrNm;
    
    /** 대체발송메시지내용 */
    private String sbsnSendMsgeCntn;
    
    /** 의무발송여부 */
    private String dutySendYn;
        
    /** 버튼구분코드 */
    private String butnDvsnCode;
    
    /** 알림톡버튼내용 */
    private NttkButnCntn nttkButnCntn;
    
    /** 발신자전화지역번호 */
    private String sndeTlphArcd;
    
    /** 발신자전화국번호 */
    private String sndeTlphOfno;
    
    /** 발신자전화개별번호 */
    private String sndeTlphInno;
    
    /** 휴대폰전화통신사번호 */
    private String hpTlphTlcmNo;
    
    /** 휴대폰전화국번호 */
    private String hpTlphOfno;
    
    /** 휴대폰전화가입자번호 */
    private String hpTlphSbno;
    
    /** 안내장종류코드 */
    private String ntfcKindCode;
    
    
    @Getter
    @Setter
    @ToString
    public static class NttkButnCntn {
        
        private List<Button> button;
        
        @Getter
        @Setter
        @ToString
        public static class Button {
        	/** 버튼명 */
            private String name;
            
            /** 버튼타입 */
            private String type;
            
            /** URL_PC */
            private String url_pc;
            
            /** URL_MOBILE */
            private String url_mobile;
            
            /** TARGET */
            private String target;
        }
    }

}
