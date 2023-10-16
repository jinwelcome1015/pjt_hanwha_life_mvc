package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc005ReqDto extends ReqSuperDto {
	
	/** 사원번호 */
	private String emnb;

	/** 로그인비밀번호 */
	private String lognPswd;

}
