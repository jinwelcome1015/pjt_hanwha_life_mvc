package com.gooroomee.backbone.external.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc005ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 로그인비밀번호 */
	private String lognPswd;

}
