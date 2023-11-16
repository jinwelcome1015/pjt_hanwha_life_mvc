package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc017ReqDto {
	
	/** 사원번호 */
	private String emnb;

	// XXX 확인
	/** 대체키SUID */
	private long sbsnKeySuid;
}
