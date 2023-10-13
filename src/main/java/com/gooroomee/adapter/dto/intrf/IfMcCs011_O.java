package com.gooroomee.adapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs011_O {
	
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
	
	/** 데이터바디 */
	private String dataBody;
	
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
