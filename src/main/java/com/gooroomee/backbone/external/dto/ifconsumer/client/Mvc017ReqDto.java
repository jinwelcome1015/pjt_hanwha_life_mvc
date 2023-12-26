package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * API - 카카오알림톡발송_챗버블
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc017ReqDto {
	
	/** 사원번호 */
	private String emnb;

	/** 대체키SUID */
	private long sbsnKeySuid;
}
