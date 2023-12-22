package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * API - 카카오알림톡발송_챗버블
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc015ReqDto {

	/** 사원번호 */
	private String emnb;

	/** URL_MOBILE */
	private String url_mobile;

	/** 고객ID */
	private String custId;

	/** 수신자명 */
	private String rcvrNm;

	/** 휴대폰전화통신사번호 */
	private String hpTlphTlcmNo;

	/** 휴대폰전화국번호 */
	private String hpTlphOfno;

	/** 휴대폰전화가입자번호 */
	private String hpTlphSbno;

}
