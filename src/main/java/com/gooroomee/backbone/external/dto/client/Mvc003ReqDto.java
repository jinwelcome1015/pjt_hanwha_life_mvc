package com.gooroomee.backbone.external.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc003ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 고객ID */
	private String custId;

//	/** 푸시수신자사원번호 */
//	private String pushRcvrEmnb;

	/** 일치여부 */
	private String csnsYn;

}
