package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * API - 신분증OCR요청2
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 *
 */
@Getter
@Setter
@ToString
public class Mvc001ResDto {

	/** 데이터헤더 */
	private DataHeader dataHeader;

	/** 데이터바디 */
	private DataBody dataBody;

	@Getter
	@Setter
	@ToString
	public static class DataHeader {

		/** 서비스ID */
		private String SRVC_ID;

		/** 화면 ID */
		private String SCRN_ID;

		/** 네이버 OCR Secret Key */
		private String X_OCR_SECRET;

		/** 처리코드 */
		private String CRTF_RTCD;

		/** 처리결과메세지 */
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
