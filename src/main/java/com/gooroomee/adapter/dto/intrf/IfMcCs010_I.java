package com.gooroomee.adapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs010_I {
	
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
	
	/** 기관코드 */
	private String ORGN_CODE;
	
	/** 사용자ID */
	private String USER_ID;
	
	// XXX 확인
	/** 데이터바디 */
	private String dataBody;
	
	/** 고객자격 */
	private String grant_type;

}
