package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc003ReqDto {

	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	public String emnb;

	/** 고객ID */
	private String custId;
	
	/** 푸시수신자사원번호 */
//	private String pushRcvrEmnb;

	/** 일치여부 */
	private String csnsYn;

}
