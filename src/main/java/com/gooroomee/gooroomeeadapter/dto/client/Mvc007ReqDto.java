package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc007ReqDto {

	/** 사원번호 */
	public String emnb;

	/** 고객ID */
	private String custId;

	/** 증권번호 */
	private String polyNo;

//	/** 계약구분코드 */
//	private String cntcDvsnCode;

//	/** 고객구분코드 */
//	private String custDvsnCode;

//	/** nextKey */
//	private String nextKey;

//	/** pageSize */
//	private int pageSize;

}
