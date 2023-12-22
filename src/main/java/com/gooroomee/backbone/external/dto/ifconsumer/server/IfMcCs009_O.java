package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * INTERFACE - 개인정보유출노출여부조회 
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs009_O {
	
	/** 고객ID */
	private String custId;

	/** 유출여부 */
	private String leakYn;
	
	/** 노출여부 */
	private String epsrYn;
	
	/** 휴대폰번호변경여부 */
	private String hpnoChngYn;

}
