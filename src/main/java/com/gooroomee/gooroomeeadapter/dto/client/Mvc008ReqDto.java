package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc008ReqDto {
	
	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	public String emnb;

	/** 고객ID */
	private String custId;

}
