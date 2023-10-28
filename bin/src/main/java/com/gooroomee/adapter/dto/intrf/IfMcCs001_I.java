package com.gooroomee.gooroomeeadapter.dto.intrf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs001_I {
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
		
		/** 기관코드 */
		@JsonProperty("ORGN_CODE")
		private String ORGN_CODE;

		/** 사용자 ID */
		@JsonProperty("USER_ID")
		private String USER_ID;
		
		// XXX 확인
		/** 이미지 배열 Json Array */
		private List<Image> images;

		@Getter 
		@Setter
		@ToString
		public static class Image {
			/** 이미지 포맷 */
			private String format;

			/** 이미지 데이터 */
			private String data;

			/** 이미지 이름 */
			private String name;
		}
		
	}
}