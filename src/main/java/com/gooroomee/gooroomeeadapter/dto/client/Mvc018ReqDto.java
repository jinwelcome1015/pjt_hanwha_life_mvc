package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc018ReqDto {
	
	/** 사원번호 */
	public String emnb;

	/** 검색어명 */
	private String scwdNm;
}
