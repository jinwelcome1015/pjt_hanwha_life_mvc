package com.gooroomee.backbone.external.dto.intrf;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs001_O {
	
	// XXX 확인
	/** 데이터헤더 */
	private DataHeader dataHeader;
	
	// XXX 확인
	/** 데이터바디 */
	private DataBody dataBody;
	
	
	@Getter 
	@Setter
	@ToString
	public static class DataHeader {
		
		/** 서비스ID */
		@JsonProperty("SRVC_ID")
		private String SRVC_ID;

		/** 화면 ID */
		@JsonProperty("SCRN_ID")
		private String SCRN_ID;

		/** 네이버 OCR Secret Key */
		@JsonProperty("X_OCR_SECRET")
		private String X_OCR_SECRET;

		/** 처리코드 */
		@JsonProperty("CRTF_RTCD")
		private String CRTF_RTCD;

		/** 처리결과메세지 */
		@JsonProperty("DLRE_MSG")
		private String DLRE_MSG;
	}
	
	@Getter 
	@Setter
	@ToString
	public static class DataBody {
		
		/** API 호출 UUID */
		private String requestId;

		/** V2로 고정 */
		private String version;
		
		/** API 호출 Timestamp 값 */
		private String timestamp;

		/** OCR 인식 결과 */
		private String images;
		
	}
}
