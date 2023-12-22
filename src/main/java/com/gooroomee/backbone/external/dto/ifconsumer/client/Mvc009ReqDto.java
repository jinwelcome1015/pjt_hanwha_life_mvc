package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 개인정보유출노출여부조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc009ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 고객ID */
	private String custId;

}
