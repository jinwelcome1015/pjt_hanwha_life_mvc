package com.gooroomee.adapter.constant;

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
	IfMcCs012("012", "hcsInitechStatus", "HCS");

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
