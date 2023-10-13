package com.gooroomee.adapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs011_I {
	// XXX 확인
	/** 데이터헤더 */
	private String dataHeader;
	
	/** 서비스ID */
	private String SRVC_ID;
	
	/** 화면ID */
	private String SCRN_ID;
	
	/** 처리코드 */
    private String CRTF_RTCD;
    
    /** 처리결과메세지 */
    private String DLRE_MSG;
    
    /** 기관코드 */
    private String ORGN_CODE;
    
    /** 사용자ID */
    private String USER_ID;
    
    // XXX 확인
    /** 데이터바디 */
    private String dataBody;
    
    /** 이니텍인증토큰 */
    private String initechOAuthToken;
    
    /** 인증서비스ID */
    private String pid;
    
    /** 사용자이름 */
    private String uname;
    
    /** 사용자생년월일 */
    private String ubirthday;
    
    /** 사용자성별 */
    private String ugender;
    
    /** 사용자휴대폰번호 */
    private String uphone;
    
    /** 인증전자서명구분값 */
    private String op;
    
    // XXX 확인
    /** 전자서명 */
    private String sign;
    
    /** 전자서명원문 */
    private String contents;
    
    /** 요청환경코드 */
    private String deviceCode;
    
    /** 요청브라우저타입 */
    private String deviceBrowser;
    
    // XXX 확인
    /** 콜백 */
    private String callback;
    
    /** 성공콜백URL */
    private String successCallbackUrl;
    
    /** 실패콜백URL */
    private String failCallbackUrl;
    
    /** 통신사구분코드 */
    private String telcoTycd;
    
    /** 모바일OS */
    private String mobileOs;
    
    /** 채널코드 */
    private String channel;
    
 }