package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc008ReqDto extends ReqSuperDto {
	
	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	public String emnb;

	/** 고객ID */
	private String custId;

}
