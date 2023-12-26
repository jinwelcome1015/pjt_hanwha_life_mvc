package com.gooroomee.backbone.external.dto.ifconsumer.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * INTERFACE - 신분증OCR요청 
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs001_O {
	
	/** 데이터헤더 */
	private DataHeader dataHeader;
	
	/** 데이터바디 */
	private DataBody dataBody;
	
	/**
	 * 데이터헤더 DTO 클래스
	 * @author 신용진
	 */
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
	
	/**
	 * 데이터바디 DTO 클래스
	 * @author 신용진
	 */
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
