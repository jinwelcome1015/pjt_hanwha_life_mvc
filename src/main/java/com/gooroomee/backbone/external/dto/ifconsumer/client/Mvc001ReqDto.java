package com.gooroomee.backbone.external.dto.ifconsumer.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * API - 진입2
 * API - 신분증OCR요청2
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 *
 */
@Getter
@Setter
@ToString
public class Mvc001ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 화면 ID */
	@JsonProperty("SCRN_ID")
	private String SCRN_ID;

	/** 사용자 ID */
	private String USER_ID;

	/** 이미지 포맷 */
	private String format;

	/** 이미지 데이터 */
	private String data;

	/** 이미지 이름 */
	private String name;

}
