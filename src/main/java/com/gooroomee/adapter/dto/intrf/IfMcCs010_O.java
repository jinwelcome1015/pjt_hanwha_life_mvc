package com.gooroomee.adapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs010_O {
	
	// XXX 확인
	/** 데이터헤더 */
	private String dataHeader;
	
	/** 서비스ID */
	private String SRVC_ID;
	
	/** 화면ID */
	private String SCRN_ID;
	
	/** 처리코드 */
	private String CRTF_RTCD;
	
	/** 처리결과메세지 */
	private String DLRE_MSG;
	
	// XXX 확인
	/** 데이터바디 */
	private String dataBody;
	
	/** 액세스토큰 */
	private String access_token;
	
	/** 토큰타입 */
	private String token_type;
	
	/** 토큰유효시간 */
	private String expires_in;

}
