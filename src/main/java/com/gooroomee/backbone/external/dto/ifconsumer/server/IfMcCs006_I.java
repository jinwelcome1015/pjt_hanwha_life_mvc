package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 사원목록조회
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs006_I {
	
	/** 검색어명 */
	private String scwdNm;

	/** 기관코드 */
	private String orgnCode;
	
	/** 사원구분코드 */
	private String empeDvsnCode;
	
	/** 전체사원조회여부 */
	private String wholEmpeInqyYn;
	
	/** FP제외여부 */
	private String fpExlsYn;
	
	/** 재직구분코드 */
	private String tnofDvsnCode;

}
