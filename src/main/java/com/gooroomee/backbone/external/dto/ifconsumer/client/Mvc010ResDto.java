package com.gooroomee.backbone.external.dto.ifconsumer.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 간편인증 토큰발급
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc010ResDto {

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

	/**
	 * 데이터바디 DTO 클래스
	 * @author 신용진
	 */
	@Getter
	@Setter
	@ToString
	public static class DataBody {

		/** 액세스토큰 */
		private String access_token;

		/** 토큰타입 */
		private String token_type;

		/** 토큰유효시간 */
		private String expires_in;
	}
}
