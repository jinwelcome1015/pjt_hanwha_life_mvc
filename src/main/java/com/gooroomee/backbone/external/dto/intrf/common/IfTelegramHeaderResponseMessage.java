package com.gooroomee.backbone.external.dto.intrf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfTelegramHeaderResponseMessage {
	/** 메시지 코드 */
	private String msgeCode;
	
	/** 메시지 내용 */
	private String msgeCntn;
	
	/** 출력방법 */
	private String msgeOtptDvsnCode;
	
	public IfTelegramHeaderResponseMessage() {
		super();
	}
	
	public IfTelegramHeaderResponseMessage(String msgeCode, String msgeCntn, String msgeOtptDvsnCode) {
		super();
		this.msgeCode = msgeCode;
		this.msgeCntn = msgeCntn;
		this.msgeOtptDvsnCode = msgeOtptDvsnCode;
	}

}
