package com.gooroomee.backbone.external.dto.intrf;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter
@ToString
public class IfMcCs012_O {
	
	/** 데이터헤더 */
	private DataHeader dataHeader;

	/** 데이터바디 */
	private DataBody dataBody;

	
	
	@Getter 
	@Setter
	@ToString
	public static class DataHeader {

		/** 서비스ID */
		@JsonProperty("SRVC_ID")
		private String SRVC_ID;
		
		/** 화면ID */
		@JsonProperty("SCRN_ID")
		private String SCRN_ID;
		
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
		
		/** 응답코드 */
		private String resCode;

		/** 오류메시지 */
		private String errorMessage;
		
		/** 인증진행상태코드 */
		private String status;

		/** 페이로드 */
		private Payload payload;


		@Getter 
		@Setter
		@ToString
		public static class Payload {
			
			/** 인증서명구분 */
			private String op;

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

			/** 사용자CI */
			private String uci;
			
			/** 전자서명데이터 */
			private String signedData;

		}
	}	
}
