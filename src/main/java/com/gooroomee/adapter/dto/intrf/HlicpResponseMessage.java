package com.gooroomee.adapter.dto.intrf;

/**
 * 응답 메세지
 * @author T440s
 *
 */
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

	public String getMsgeCode() {
		return msgeCode;
	}

	public void setMsgeCode(String msgeCode) {
		this.msgeCode = msgeCode;
	}

	public String getMsgeCntn() {
		return msgeCntn;
	}

	public void setMsgeCntn(String msgeCntn) {
		this.msgeCntn = msgeCntn;
	}

	public String getMsgeOtptDvsnCode() {
		return msgeOtptDvsnCode;
	}

	public void setMsgeOtptDvsnCode(String msgeOtptDvsnCode) {
		this.msgeOtptDvsnCode = msgeOtptDvsnCode;
	}

	@Override
	public String toString() {
		return "HlicpResponseMessage [msgeCode=" + msgeCode + ", msgeCntn=" + msgeCntn + ", msgeOtptDvsnCode="
				+ msgeOtptDvsnCode + "]";
	}
	
}
