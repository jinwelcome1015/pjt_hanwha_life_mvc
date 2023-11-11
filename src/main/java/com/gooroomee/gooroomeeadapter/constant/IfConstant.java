package com.gooroomee.gooroomeeadapter.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class IfConstant {

	/** 송신 시스템 코드 */ 
	public static final String TRNM_SYS_CODE = "MVC";

	/** 소속 기관 코드 */
	public static final String BELN_ORGN_CODE = "00630";
	
	/** 서비스 ID */
	public static final String SRVC_ID = "SVC028";
	
	/** DEFAULT_IP_ADDRESS */
	public static final String DEFAULT_IP_ADDRESS = "127.0.0.1";
	
	/** 계약구분코드 */
	public static final String CNTC_DVSN_CODE = "01";	// 01 : 보험
	
	/** 고객구분코드 */
	public static final String CUST_DVSN_CODE = "1";	// 1 : 개인
	
	/** [카카오알림톡발송_쳇버블] 안내장종류코드 */
	public static final String NTFC_KIND_CODE = "ZAU0006";
	
	/** [카카오알림톡발송_쳇버블] 안내장템플릿코드 */
	public static final String NTFC_TMPL_CODE = "AZAU000001";
	
	/** [카카오알림톡발송_쳇버블] 안내장매체구분코드 */
	public static final String NTFC_MDIA_DVSN_CODE = "LMS";
	
	/** [카카오알림톡발송_쳇버블] 메시지제목명 */
	public static final String MSGE_TITL_NM = "모바일 화상상담 서비스 사용 안내";
	
	/** [카카오알림톡발송_쳇버블] 작업메시지내용 */
	public static final String JOB_MSGE_CNTN = "[한화생명] 모바일 화상상담 서비스 사용 안내\r\n" + 
            "\r\n" + 
            "%s 고객님 안녕하세요. \r\n" + 
            "모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \r\n" + 
            "하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \r\n" + 
            "\r\n" + 
            "서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \r\n" + 
            "감사합니다.\r\n" + 
            "\r\n" + 
            "* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.";
	
	
	public static final String SBSN_SEND_MSGE_CNTN = "[한화생명] 모바일 화상상담 서비스 사용 안내\r\n" + 
            "\r\n" + 
            "%s 고객님 안녕하세요. \r\n" + 
            "모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \r\n" + 
            "하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \r\n" + 
            "\r\n" + 
            "주소링크 : %s \r\n" +
            "\r\n" + 
            "서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \r\n" + 
            "감사합니다.\r\n" + 
            "\r\n" + 
            "* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.";
	
	/** [카카오알림톡발송_쳇버블] 알림톡 버튼 텍스트 */
	public static final String NTTK_BUTN_CNTN_BUTTON_NAME = "모바일 화상상담 바로가기";
	
	/** [카카오알림톡발송_쳇버블] 알림톡 버튼 텍스트 */
	public static final String NTTK_BUTN_CNTN_BUTTON_TYPE = "WL";
	
	/** [카카오알림톡발송_쳇버블] 알림톡 버튼 TARGET */
	public static final String NTTK_BUTN_CNTN_BUTTON_TARGET = "out";
	
	/** [카카오알림톡발송_쳇버블] 온라인배치구분코드 */
	public static final String ONLN_BTCH_DVSN_CODE = "R";
	
	/** base64 데이터 기록용 별도 logger의 이름을 위한 suffix */
	public static final String LOGGER_NAME_SUFFIX_FOR_BASE64 = "._BASE64";
	
	
	/** 채널유형코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfChnlTypeCode {

		/** 채널유형코드 : PC */
		PC("UI"),
		
		/** 채널유형코드 : SERVER */
		SERVER("SVR"),
		
		/** 채널유형코드 : DEFAULT */
		DEFAULT("");

		private final String value;
	}

	
	
	/** 당타발 구분코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfExtlDvsnCode {

		/** 당타발 구분코드 : 타발 */
		OUTWARD("1"),
		
		/** 당타발 구분코드 : 당발 */
		INWARD("2");

		private final String value;
	}

	/**
	 * 처리결과구분코드
	 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfPrcsRsltDvsnCode {

		/** 처리결과구분코드 : 정상 */
		NORMAL("0"),
		
		/** 처리결과구분코드 : 비정상 */
		ABNORMAL("1");

		private final String value;
	}

	/** 요청 응답 구분 코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfRspnDvsnCode {

		/** 요청응답 구분코드 : 요청 */
		SEND("S"),
		
		/** 요청응답 구분코드 : 응답 */
		RECEIVE("R"),
		
		/** 요청응답 구분코드 : 요청 더미 응답 */
		SEND_DUMMY("Q"),
		
		/** 요청응답 구분코드 : 응답 더미 응답 */
		RECEIVE_DUMMY("P");

		private final String value;
	}

	
	/** 서버유형 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfServerType {

		/** 서버유형 : 로컬 */
		LOCAL("L"),
		
		/** 서버유형 : 임시서버 */
		TEMP("T"),
		
		/** 서버유형 : 개발서버 */
		DEV("D"),
		
		/** 서버유형 : QA서버 */
		QA("Q"),
		
		/** 서버유형 : 운영서버 */
		PROD("P"),
		
		/** 서버유형 : etc */
		ETC("");

		private final String value;
	}

	/** 인터페이스 명세 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfSpec {
		/** 인터페이스 명세 : 신분증OCR요청 */
		IfMcCs001("HLIMVC00016", "hcsIdcdOcrRqst", "HCS"),
		
		/** 인터페이스 명세 : 진위확인결과조회 */
		IfMcCs002("HLIMVC00017", "icsTrflCnfmPSI002r", "ICS"),
		
		/** 인터페이스 명세 : 신분증스캔후처리 */
		IfMcCs003("HLIMVC00018", "comItfcIdcdScanPSI001c", "COM"),
		
		/** 인터페이스 명세 : SSO대체로그인인증 */
		IfMcCs005("HLIMVC00019", "comItfcUserCtfnPSI001c", "COM"),
		
		/** 인터페이스 명세 : 사원목록조회 */
		IfMcCs006("HLIMVC00020", "comEmpeInqyPSI001r", "COM"),
		
		/** 인터페이스 명세 : 고객계약정보조회 */
		IfMcCs007("HLIMVC00023", "icsIntgCustInqyMgmtPSI001r", "ICS"),
		
		/** 인터페이스 명세 : 고객계좌목록조회 */
		IfMcCs008("HLIMVC00024", "icsIntgCustPSI008r", "ICS"),
		
		/** 인터페이스 명세 : 개인정보유출노출여부조회 */
		IfMcCs009("HLIMVC00025", "icsPrsnInfoLeakMgmtPSI005r", "ICS"),
		
		/** 인터페이스 명세 : 간편인증 토큰발급 */
		IfMcCs010("HLIMVC00027", "hcsInitechToken", "HCS"),
		
		/** 인터페이스 명세 : 간편인증 요청 */
		IfMcCs011("HLIMVC00028", "hcsInitechRequest", "HCS"),
		
		/** 인터페이스 명세 : 간편인증 상태 조회 */
		IfMcCs012("HLIMVC00029", "hcsInitechStatus", "HCS"),
		
		/** 인터페이스 명세 : 카카오알림톡발송_챗버블 */
		IfMcCs015("HLIMVC00035", "iniCspdDvlmUmsSendMgmtPSI004c", "INI"),
		
		/** 인터페이스 명세 : 대체키별연락처저장 */
		IfMcCs016("HLIMVC00031", "icmCnplSbsnKeyMgmtPSI001c", "ICM"),
		
		/** 인터페이스 명세 : 대체키별연락처조회 */
		IfMcCs017("HLIMVC00032", "icmCnplSbsnKeyMgmtPSI002r", "ICM"),
		
		/** 인터페이스 명세 : 우편번호조회 */
		IfMcCs018("HLIMVC00033", "icmAddrMgmtPSI001r", "ICM");

		private final String itfcId;
		private final String rcveSrvcId;
		private final String rcveSysCode;
	}

	/** 인터페이스 타입 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum IfType {
		/** 인터페이스 타입 : MCI */
		MCI("MCI"),
		
		/** 인터페이스 타입 : ESB */
		ESB("ESB"),
		
		/** 인터페이스 타입 : FEB */
		FEB("FEB");
		
		private final String value;
	}
	
	
	/** 간편인증서비스ID */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcId{
		
		/** 간편인증서비스ID : EZ_KAKAOV2 */
		EZ_KAKAOV2("EZ_KAKAOV2", "카카오"),
		
		/** 간편인증서비스ID : EZ_NAVERV2 */
		EZ_NAVERV2("EZ_NAVERV2", "네이버"),
		
		/** 간편인증서비스ID : EZ_PASS */
		EZ_PASS("EZ_PASS", "패스"),
		
		/** 간편인증서비스ID : EZ_TOSS */
		EZ_TOSS("EZ_TOSS", "토스"),
		
		/** 간편인증서비스ID : EZ_PAYCO */
		EZ_PAYCO("EZ_PAYCO", "페이코");

		
		private final String value;
		private final String name;

	}
	
	
	/** 간편인증서비스OP */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcOp{
		
		/** 간편인증서비스OP : auth(인증) */
		auth("auth"),
		
		/** 간편인증서비스OP : sign(전자서명) */
		sign("sign");
		
		private final String value;

	}
	
	
	/** 간편인증서비스 요청환경타입 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcDeviceCode {

		/** 간편인증서비스 요청환경타입 : PC(PC) */
		PC("PC"),

		/** 간편인증서비스 요청환경타입 : TB(태블릿) */
		TABLET("TB"),

		/** 간편인증서비스 요청환경타입 : MO(모바일) */
		MOBILE("MO");

		private final String value;

	}

	
	/** 간편인증서비스 요청브라우저타입 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcDeviceBrowser{
		
		/** 간편인증서비스 요청브라우저타입 : WB(웹브라우저) */
		webBrowser("WB"),
		
		/** 간편인증서비스 요청브라우저타입 : NA(네이티브앱) */
		nativeApp("NA"),
		
		/** 간편인증서비스 요청브라우저타입 : HY(하이브리드앱) */
		hybridApp("HY");

		private final String value;

	}
	
	
	/** 간편인증서비스 통신사 구분 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcTelcoTycd{
		
		/** 간편인증서비스 통신사 구분 : S(SKT) */
		SKT("S"),
		
		/** 간편인증서비스 통신사 구분 : K(KT) */
		KT("K"),
		
		/** 간편인증서비스 통신사 구분 : L(LGU+) */
		LG_U_PLUS("L");
		

		private final String value;
	}
	
	
	/** 간편인증서비스 모바일 OS 구분 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcMobileOS{
		
		/** 간편인증서비스 모바일 OS 구분 : ANDROID */
		ANDROID("ANDROID"),
		
		/** 간편인증서비스 모바일 OS 구분 : IOS */
		IOS("IOS");

		private final String value;

	}
	
	
	/** 간편인증서비스 권한부여유형 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcGrantType{
		
		/** 간편인증서비스 권한부여유형 : client_credentials */
		client_credentials("client_credentials");

		private final String value;
	}
	
	
	/** 간편인증서비스 인증진행상태 코드*/
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum EzCertSrvcStatus{
		
		/** 간편인증서비스 인증진행상태 코드 : IN_PROGRESS */
		IN_PROGRESS("IN_PROGRESS"),
		
		/** 간편인증서비스 인증진행상태 코드 : COMPLETE */
		COMPLETE("COMPLETE");
		
		private final String value;
	}
	
	
	/** OCR 신분증 타입 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum OcrIdType{
		
		/** OCR 신분증 타입 : 주민등록증 */
		IdCard("ID Card", "004"),
		
		// XXX 확인 : 운전면허증(도로교통공단), 운전면허증(경찰청) 등등 진위확인구분코드 확인 필요
		/** OCR 신분증 타입 : 운전면허증 */
		DriverLicense("Driver's License", "011"),
		
		/** OCR 신분증 타입 : 여권 */
		Passport("Passport", "911"),
		
		/** OCR 신분증 타입 : 외국인등록증 */
		AlienRegistrationCard("Alien Registration Card", "017");
		
		/** 신분증 타입 이름 */
		private final String name;
		
		/** 진위확인구분코드 */
		private final String trflCnfmDvsnCode;
		
	}
	
	
	
	/** 진위확인업무구분코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum TrflCnfmBswrDvsnCode {
		/** 진위확인업무구분코드 : 신계약 */
		newContract("신계약", "01"),
		
		/** 진위확인업무구분코드 : 제지급 */
		payment("제지급", "02"),
		
		/** 진위확인업무구분코드 : 신계약 */
		deposit("신계약", "03"),
		
		/** 진위확인업무구분코드 : 사고 */
		accident("사고", "04"),
		
		/** 진위확인업무구분코드 : 정정변경 */
		changeCorrection("정정변경", "05"),
		
		/** 진위확인업무구분코드 : 융자 */
		loan("융자", "11"),
		
		/** 진위확인업무구분코드 : 수익증권 */
		beneficiaryCertificates("수익증권", "21"),
		
		/** 진위확인업무구분코드 : 신탁 */
		trust("신탁", "31"),
		
		/** 진위확인업무구분코드 : 기타 */
		other("기타", "99");
		
		
		/** 이름 */
		private final String name;

		/** 진위확인업무구분코드 */
		private final String code;
	}
	
	
	/** 진위확인채널코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum TrflCnfmChnlCode {
		
		/** 진위확인채널코드 : 개인 */
		INDIVIDUAL("개인", "01"),
		
		/** 진위확인채널코드 : 법인 */
		CORPORATION("법인", "02"),
		
		/** 진위확인채널코드 : TM */
		TM("TM", "03"),
		
		/** 진위확인채널코드 : CM */
		CM("CM", "04"),
		
		/** 진위확인채널코드 : 방카 */
		BANCASSURANCE("방카", "05"),
		
		/** 진위확인채널코드 : 온슈어 */
		ONSURE("온슈어", "06"),
		
		/** 진위확인채널코드 : 융자 */
		LOAN("융자", "07"),
		
		/** 진위확인채널코드 : 펀드 */
		FUND("펀드", "08"),
		
		/** 진위확인채널코드 : 수익증권 */
		BENEFICIARY_CERTIFICATES("수익증권", "09"),
		
		/** 진위확인채널코드 : 기타 */
		ETC("기타", "99");
		
		
		/** 이름 */
		private final String name;

		/** 진위확인업무구분코드 */
		private final String code;
	}
	
	
	/** 신분증진위작업코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum TrflCnfmJobCode {
		
		/** 신분증진위작업코드 : 고객신원확인 */
		CUSTOMER_IDENTIFICATION("고객신원확인", "CS001"),
		
		/** 신분증진위작업코드 : 융자신용대출 */
		CREDIT_LOAN("융자신용대출", "FN001"),
		
		/** 신분증진위작업코드 : FP대리접수 */
		FP_PROXY_ACCEPTANCE("FP대리접수", "PA001");
		
		
		/** 이름 */
		private final String name;

		/** 진위확인업무구분코드 */
		private final String code;
	}
	
	/** 고객구분코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum CustomerDivisionCode {
		
		/** 고객구분코드 : 개인 */
		INDIVIDUAL("개인", "01"),
		
		/** 고객구분코드 : 단체 */
		GROUP("단체", "02");
		
		/** 이름 */
		private final String name;

		/** 고객구분코드 */
		private final String code;
		
	}
	
	
	/** 계약구분코드 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum ContractClassificationCode {
		
		/** 계약구분코드 : 보험 */
		INSURANCE("보험", "01"),
		
		/** 계약구분코드 : 융자 */
		LOAN("융자", "02"),
		
		/** 계약구분코드 : 퇴직연금 */
		RETIRING_PENSION("퇴직연금", "03"),
		
		/** 계약구분코드 : 펀드 */
		FUND("펀드", "04"),
		
		/** 계약구분코드 : 신수탁 */
		NEW_TRUSTEE("신수탁", "05"),
		
		/** 계약구분코드 : 기타 */
		ETC("기타", "99");
		
		/** 이름 */
		private final String name;

		/** 계약구분코드 */
		private final String code;
		
	}
	
	
	/** OCR 이미지 추론 결과 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum OcrInferResult {
		
		/** OCR 이미지 추론 결과 : 이미지 인식 성공 */
		SUCCESS("SUCCESS", "이미지 인식 성공"),
		
		/** OCR 이미지 추론 결과 : 이미지 인식 실패 */
		FAILURE("FAILURE", "이미지 인식 실패"),
		
		/** OCR 이미지 추론 결과 : 이미지 인식 예외 사항 */
		ERROR("ERROR", "이미지 인식 예외 사항");
		
		
		/** 이름 */
		private final String resultValue;

		/** 계약구분코드 */
		private final String resultMessage;
		
	}
	
}