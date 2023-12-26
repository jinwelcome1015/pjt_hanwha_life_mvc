package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 간편인증 상태 조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc012ResDto {

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
		private String SRVC_ID;

		/** 화면ID */
		private String SCRN_ID;

		/** 처리코드 */
		private String CRTF_RTCD;

		/** 처리결과메세지 */
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
