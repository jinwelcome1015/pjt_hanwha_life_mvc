package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 간편인증 요청
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc011ResDto {

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
		private String SRVC_ID;

		/** 화면ID */
		private String SCRN_ID;

		/** 처리코드 */
		private String CRTF_RTCD;

		/** 처리결과메세지 */
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

		/** 트랜잭션ID */
		private String reqTxId;

		/** iOS앱스킴 */
		private String iosAppSchemeUrl;

		/** AOS앱스킴 */
		private String aosAppSchemeUrl;
	}

}
