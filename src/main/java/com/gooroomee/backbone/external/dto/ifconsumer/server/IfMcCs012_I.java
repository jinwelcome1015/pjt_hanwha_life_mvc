package com.gooroomee.backbone.external.dto.ifconsumer.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs012_I {
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
		
		/** 기관코드 */
		@JsonProperty("ORGN_CODE")
		private String ORGN_CODE;
		
		/** 사용자ID */
		@JsonProperty("USER_ID")
		private String USER_ID;
		
	}

	
	
	@Getter 
	@Setter
	@ToString
	public static class DataBody {
		
		/** 이니텍인증토큰 */
		private String initechOAuthToken;

		/** 트랜잭션ID */
		private String reqTxId;
		
		/** 인증서명구분 */
		private String op;
	}
	
}
