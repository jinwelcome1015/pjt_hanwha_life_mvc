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
		IfMcCs015("HLIMVC00030", "iniUmsSendMgmtPSI004c", "INI"),
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
}