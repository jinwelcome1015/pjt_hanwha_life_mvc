package com.gooroomee.adapter.dto.io;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs012_I {
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
		
		/**
		 * 기관 코드
		 */
		private String ORGN_CODE;
		
		/**
		 * 사용자 ID
		 */
		private String USER_ID;
		
	}

	
	
	/**
	 * 데이터 바디
	 * @author T440s
	 *
	 */
	@Getter 
	@Setter
	@ToString
	public static class DataBody {
		/**
		 * 이니텍 인증 토큰
		 */
		private String initechOAuthToken;
		
		/**
		 * 트랜잭션 ID
		 */
		private String reqTxId;
		
		/**
		 * 인증서명구분
		 */
		private String op;
		
	}
}
