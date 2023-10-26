package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ResSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc010ResDto extends ResSuperDto {

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

		/** 액세스토큰 */
		private String access_token;

		/** 토큰타입 */
		private String token_type;

		/** 토큰유효시간 */
		private String expires_in;
	}
}
