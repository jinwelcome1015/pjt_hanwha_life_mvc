package com.gooroomee.backbone.external.dto.ifconsumer.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * INTERFACE - SSO대체로그인인증 
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs005_I {
	
	/** 사원번호 */
	private String emnb;

	/** 로그인비밀번호 */
	private String lognPswd;

}
