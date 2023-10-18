package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc005ReqDto {

	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	private String emnb;

	/** 로그인비밀번호 */
	private String lognPswd;

}
