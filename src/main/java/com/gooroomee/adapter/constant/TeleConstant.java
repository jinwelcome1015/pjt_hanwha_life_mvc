package com.gooroomee.adapter.constant;

public class TeleConstant {
	
	public enum TargetType {
		MCI, ESB, FEB;
	}
	
	public enum IfSpec {
		/**
		 * 신분증OCR요청
		 */
		IfMcCs001("001", "hcsIdcdOcrRqst", "HCS"), 
		/**
		 * 진위확인결과조회
		 */
		IfMcCs002("002", "icsTrflCnfmPSI002r", "ICS"),
		/**
		 * 신분증스캔후처리
		 */
		IfMcCs003("003", "comItfcIdcdScanPSI001c", "COM"),
		/**
		 * SSO대체로그인인증
		 */
		IfMcCs005("005", "comItfcUserCtfnPSI001c", "COM"),
		/**
		 * 사원목록조회
		 */
		IfMcCs006("006", "comEmpeInqyPSI001r", "COM"),
		/**
		 * 고객계약정보조회
		 */
		IfMcCs007("007", "icsIntgCustInqyMgmtPSI001r", "ICS"),
		/**
		 * 고객계좌목록조회
		 */
		IfMcCs008("008", "icsIntgCustPSI008r", "ICS"),
		/**
		 * 개인정보유출노출여부조회
		 */
		IfMcCs009("009", "icsPrsnInfoLeakMgmtPSI005r", "ICS"),
		/**
		 * 간편인증 토큰발급
		 */
		IfMcCs010("010", "hcsInitechToken", "HCS"),
		/**
		 * 간편인증 요청
		 */
		IfMcCs011("011", "hcsInitechRequest", "HCS"),
		/**
		 * 간편인증 상태 조회
		 */
		IfMcCs012("012", "hcsInitechStatus", "HCS")
		;
		
		private String itfcId;
		private String rcveSrvcId;
		private String rcveSysCode;
		
		private IfSpec(String itfcId, String rcveSrvcId, String rcveSysCode) {
			this.itfcId = itfcId;
			this.rcveSrvcId = rcveSrvcId;
			this.rcveSysCode = rcveSysCode;
		}

		public String getItfcId() {
			return itfcId;
		}
		public String getRcveSrvcId() {
			return rcveSrvcId;
		}
		public String getRcveSysCode() {
			return rcveSysCode;
		}
	}
	
	/**
	 * 채널 유형 코드
	 */
	private static final String CHNL_TYPE_CODE = "SVR";
	
	/**
	 * 송신 시스템 코드
	 */
	private static final String TRNM_SYS_CODE = "MVC";
	
	/**
	 * 소속 기관 코드
	 */
	private static final String BELN_ORGN_CODE = "00630";
	
	/**
	 * 처리결과 구분코드 : 성공
	 */
	public static final String PRCS_RSLT_DVSN_CODE_SUCCESS = "0";
	
	/**
	 * 처리결과 구분코드 : 실패
	 */
	public static final String PRCS_RSLT_DVSN_CODE_FAIL = "1";
	
	/**
	 * 요청응답 구분코드 : 요청
	 */
	public static final String RSPN_DVSN_SEND = "S";
	
	/**
	 * 요청응답 구분코드 : 응답
	 */
	public static final String RSPN_DVSN_RECEIVE = "R";
	
	// 요청응답 구분코드 : 요청 더미
	
	/**
	 * 요청응답 구분코드 : 요청 더미
	 */
	public static final String RSPN_DVSN_SEND_DUMMY = "Q";
	
	/**
	 * 요청응답 구분코드 : 응답 더미
	 */
	public static final String RSPN_DVSN_RECEIVE_DUMMY = "P";
	
	/**
	 * 운영서버
	 */
	public static final String SERVER_TYPE_PROD = "PROD";
	
	/**
	 * QA서버
	 */
	public static final String SERVER_TYPE_QA = "QA";
		
	/**
	 * 개발서버
	 */
	public static final String SERVER_TYPE_DEV = "DEV";
		
	/**
	 * 로컬서버
	 */
	public static final String SERVER_TYPE_LOCAL = "LOCAL";
		
		
	
	public static final String INTERNAL_COM = "COM";	// 보험코어-업무공통
	
	
}