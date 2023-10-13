package com.gooroomee.adapter.dto.intrf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 응답 메세지
 * @author T440s
 *
 */
@Getter 
@Setter
@ToString
public class HlicpResponseMessage {
	/**
	 * 메시지 코드
	 */
	private String msgeCode;
	
	/**
	 * 메시지 내용
	 */
	private String msgeCntn;
	
	/**
	 * 출력방법
	 */
	private String msgeOtptDvsnCode;
	
	

	
	
	public HlicpResponseMessage() {
		super();
	}
	
	public HlicpResponseMessage(String msgeCode, String msgeCntn, String msgeOtptDvsnCode) {
		super();
		this.msgeCode = msgeCode;
		this.msgeCntn = msgeCntn;
		this.msgeOtptDvsnCode = msgeOtptDvsnCode;
	}

}
