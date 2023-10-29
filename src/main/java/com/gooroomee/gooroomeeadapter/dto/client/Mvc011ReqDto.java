package com.gooroomee.gooroomeeadapter.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc011ReqDto {
	
	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	public String emnb;
	
	/** 사용자ID */
	@JsonProperty("USER_ID")
	private String USER_ID;
	
	/** 이니텍인증토큰 */
	private String initechOAuthToken;
	
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
	
	/** 요청환경코드 */
	private String deviceCode;

	/** 요청브라우저타입 */
	private String deviceBrowser;
	
}