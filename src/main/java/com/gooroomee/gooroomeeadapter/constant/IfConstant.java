package com.gooroomee.gooroomeeadapter.constant;

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
	

	
	/** 채널유형코드 */
	public enum IfChnlTypeCode {

		/** 채널유형코드 : PC */
		PC("UI"),
		
		/** 채널유형코드 : SERVER */
		SERVER("SVR"),
		
		/** 채널유형코드 : DEFAULT */
		DEFAULT("");

		
		private final String value;

		private IfChnlTypeCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/** 당타발 구분코드 */
	public enum IfExtlDvsnCode {

		/** 당타발 구분코드 : 타발 */
		OUTWARD("1"),
		
		/** 당타발 구분코드 : 당발 */
		INWARD("2");

		
		private final String value;

		private IfExtlDvsnCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/**
	 * 처리결과구분코드
	 */
	public enum IfPrcsRsltDvsnCode {

		/** 처리결과구분코드 : 정상 */
		NORMAL("1"),
		
		/** 처리결과구분코드 : 비정상 */
		ABNORMAL("0");

		
		private final String value;

		private IfPrcsRsltDvsnCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/** 요청 응답 구분 코드 */
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

		private IfRspnDvsnCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/** 서버유형 */
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

		private IfServerType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/** 인터페이스 명세 */
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
		
		/** 인터페이스 명세 : 알림톡전송 */
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

		private IfSpec(String itfcId, String rcveSrvcId, String rcveSysCode) {
			this.itfcId = itfcId;
			this.rcveSrvcId = rcveSrvcId;
			this.rcveSysCode = rcveSysCode;
		}

		public String getItfcId() {
			return this.itfcId;
		}

		public String getRcveSrvcId() {
			return this.rcveSrvcId;
		}

		public String getRcveSysCode() {
			return this.rcveSysCode;
		}
	}

	/** 인터페이스 타입 */
	public enum IfType {
		/** 인터페이스 타입 : MCI */
		MCI("MCI"),
		
		/** 인터페이스 타입 : ESB */
		ESB("ESB"),
		
		/** 인터페이스 타입 : FEB */
		FEB("FEB");

		
		private final String value;

		private IfType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}
	
	/** 간편인증서비스ID */
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

		private EzCertSrvcId(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return this.value;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	
	/** 간편인증서비스OP */
	public enum EzCertSrvcOp{
		
		/** 간편인증서비스OP : auth(인증) */
		auth("auth"),
		
		/** 간편인증서비스OP : sign(전자서명) */
		sign("sign");
		

		private final String value;

		private EzCertSrvcOp(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/** 간편인증서비스 요청환경타입 */
	public enum EzCertSrvcDeviceCode {

		/** 간편인증서비스 요청환경타입 : PC(PC) */
		PC("PC"),

		/** 간편인증서비스 요청환경타입 : TB(태블릿) */
		TABLET("TB"),

		/** 간편인증서비스 요청환경타입 : MO(모바일) */
		MOBILE("MO");

		private final String value;

		private EzCertSrvcDeviceCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	
	/** 간편인증서비스 요청브라우저타입 */
	public enum EzCertSrvcDeviceBrowser{
		
		/** 간편인증서비스 요청브라우저타입 : WB(웹브라우저) */
		webBrowser("WB"),
		
		/** 간편인증서비스 요청브라우저타입 : NA(네이티브앱) */
		nativeApp("NA"),
		
		/** 간편인증서비스 요청브라우저타입 : HY(하이브리드앱) */
		hybridApp("HY");
		

		private final String value;

		private EzCertSrvcDeviceBrowser(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/** 간편인증서비스 통신사 구분 */
	public enum EzCertSrvcTelcoTycd{
		
		/** 간편인증서비스 통신사 구분 : S(SKT) */
		SKT("S"),
		
		/** 간편인증서비스 통신사 구분 : K(KT) */
		KT("K"),
		
		/** 간편인증서비스 통신사 구분 : L(LGU+) */
		LG_U_PLUS("L");
		

		private final String value;

		private EzCertSrvcTelcoTycd(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/** 간편인증서비스 모바일 OS 구분 */
	public enum EzCertSrvcMobileOS{
		
		/** 간편인증서비스 모바일 OS 구분 : ANDROID */
		ANDROID("ANDROID"),
		
		/** 간편인증서비스 모바일 OS 구분 : IOS */
		IOS("IOS");
		

		private final String value;

		private EzCertSrvcMobileOS(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/** 간편인증서비스 권한부여유형 */
	public enum EzCertSrvcGrantType{
		
		/** 간편인증서비스 권한부여유형 : client_credentials */
		client_credentials("client_credentials");

		private final String value;

		private EzCertSrvcGrantType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/** 간편인증서비스 인증진행상태 코드*/
	public enum EzCertSrvcStatus{
		
		/** 간편인증서비스 인증진행상태 코드 : IN_PROGRESS */
		IN_PROGRESS("IN_PROGRESS"),
		
		/** 간편인증서비스 인증진행상태 코드 : COMPLETE */
		COMPLETE("COMPLETE");
		

		private final String value;

		private EzCertSrvcStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/** OCR 신분증 타입 */
	public enum OcrIdType{
		
		/** OCR 신분증 타입 : 주민등록증 */
		IdCard("ID_Card"),
		
		/** OCR 신분증 타입 : 운전면허증 */
		DriverLicense("Driver's_License"),
		
		/** OCR 신분증 타입 : 여권 */
		Passport("Passport"),
		
		/** OCR 신분증 타입 : 외국인등록증 */
		AlienRegistrationCard("Alien Registration Card");

		private final String value;

		private OcrIdType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}