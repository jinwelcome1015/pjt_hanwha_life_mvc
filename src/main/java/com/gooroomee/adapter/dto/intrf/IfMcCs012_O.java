package com.gooroomee.adapter.dto.intrf;

import com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataBody;
import com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter
@ToString
public class IfMcCs012_O {
	/**
	 * 데이터 헤더
	 */
	private DataHeader dataHeader;

	/**
	 * 데이터 바디
	 */
	private DataBody dataBody;

	
	/**
	 * 데이터 헤더
	 * 
	 * @author T440s
	 *
	 */
	@Getter 
	@Setter
	@ToString
	public static class DataHeader {
		/**
		 * 서비스 ID
		 */
		private String SRVC_ID;

		/**
		 * 화면 ID
		 */
		private String SCRN_ID;

		/**
		 * 처리 코드
		 */
		private String CRTF_RTCD;

		/**
		 * 처리 결과 메세지
		 */
		private String DLRE_MSG;

	}

	
	
	/**
	 * 데이터 바디
	 * 
	 * @author T440s
	 *
	 */
	@Getter 
	@Setter
	@ToString
	public static class DataBody {
		/**
		 * 응답 코드
		 */
		private String resCode;

		/**
		 * 오류 메시지
		 */
		private String errorMessage;

		/**
		 * 인증 진행 상태 코드
		 */
		private String status;

		/**
		 * 페이로드
		 */
		private Payload payload;


		/**
		 * 페이로드
		 * 
		 * @author T440s
		 *
		 */
		@Getter 
		@Setter
		@ToString
		public static class Payload {
			/**
			 * 인증 서명 구분
			 */
			private String op;

			/**
			 * 인증 서비스 ID
			 */
			private String pid;

			/**
			 * 사용자 이름
			 */
			private String uname;

			/**
			 * 사용자 생년월일
			 */
			private String ubirthday;

			/**
			 * 사용자 성별
			 */
			private String ugender;

			/**
			 * 사용자 휴대폰 번호
			 */
			private String uphone;

			/**
			 * 사용자 CI
			 */
			private String uci;

			/**
			 * 전자 서명 데이터
			 */
			private String signedData;

		}
	}	
}
