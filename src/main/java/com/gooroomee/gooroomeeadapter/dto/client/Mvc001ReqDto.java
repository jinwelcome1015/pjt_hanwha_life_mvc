package com.gooroomee.gooroomeeadapter.dto.client;

import java.util.List;

import com.gooroomee.gooroomeeadapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc001ReqDto extends ReqSuperDto {
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

		/** 기관코드 */
		private String ORGN_CODE;

		/** 사용자 ID */
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
