package com.gooroomee.backbone.external.dto.ifconsumer.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * INTERFACE - 간편인증 요청 
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs011_I {

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

		/** 인증전자서명구분값 */
		private String op;
		
		/** 요청환경코드 */
		private String deviceCode;

		/** 요청브라우저타입 */
		private String deviceBrowser;
		
		/** 채널코드 */
		private String channel;

		// XXX 확인
		/** 전자서명 */
		private Sign sign;

		// XXX 확인
		/** 콜백 */
		private Callback callback;

		@Getter
		@Setter
		@ToString
		public static class Sign {
			/** 전자서명원문 */
			private String contents;
			
		}

		@Getter
		@Setter
		@ToString
		public static class Callback {

			/** 성공콜백URL */
			private String successCallbackUrl;

			/** 실패콜백URL */
			private String failCallbackUrl;

			/** 통신사구분코드 */
			private String telcoTycd;

			/** 모바일OS */
			private String mobileOs;
		}
	}
}