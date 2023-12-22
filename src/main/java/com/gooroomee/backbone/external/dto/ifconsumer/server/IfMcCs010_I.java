package com.gooroomee.backbone.external.dto.ifconsumer.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 간편인증 토큰발급
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs010_I {

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

		/** 고객자격 */
		private String grant_type;
	}

}
