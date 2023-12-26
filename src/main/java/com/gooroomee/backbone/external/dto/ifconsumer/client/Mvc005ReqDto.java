package com.gooroomee.backbone.external.dto.ifconsumer.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * API - SSO대체로그인인증 
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc005ReqDto {

	/** 사원번호 */
	private String emnb;

	/** 로그인비밀번호 */
	private String lognPswd;

	/** 로그인비밀번호가 암호화 되었는지 여부(파라미터로 제어하기 위해 true로 초기화함.) */
	private boolean isLognPswdEncrypted = true;
}
