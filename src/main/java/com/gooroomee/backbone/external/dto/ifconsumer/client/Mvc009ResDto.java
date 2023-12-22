package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 개인정보유출노출여부조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc009ResDto {

	/** 고객ID */
	private String custId;

	/** 유출여부 */
	private String leakYn;

	/** 노출여부 */
	private String epsrYn;

	/** 휴대폰번호변경여부 */
	private String hpnoChngYn;

}
