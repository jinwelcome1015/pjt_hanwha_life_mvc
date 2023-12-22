package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 고객계약정보조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc007ReqDto {

	/** 사원번호 */
	private String emnb;

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
