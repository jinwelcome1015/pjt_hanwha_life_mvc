package com.gooroomee.adapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs001_O {
	// XXX 확인
	/** 데이터헤더 */
	private String dataHeader;

	/** 서비스ID */
	private String SRVC_ID;

	/** 화면 ID */
	private String SCRN_ID;

	/** 네이버 OCR Secret Key */
	private String X_OCR_SECRET;

	/** 처리코드 */
	private String CRTF_RTCD;

	/** 처리결과메세지 */
	private String DLRE_MSG;

	// XXX 확인
	/** 데이터바디 */
	private String dataBody;

	/** API 호출 UUID */
	private String requestId;

	/** V2로 고정 */
	private String version;
	
	/** API 호출 Timestamp 값 */
	private String timestamp;

	/** OCR 인식 결과 */
	private String images;

}
