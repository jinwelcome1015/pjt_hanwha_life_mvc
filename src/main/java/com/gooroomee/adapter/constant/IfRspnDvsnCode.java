package com.gooroomee.adapter.constant;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * 요청 응답 구분 코드
 */
public enum IfRspnDvsnCode {
	
	/**
	 * 요청응답 구분코드 : 요청
	 */
	SEND("S"),
	/**
	 * 요청응답 구분코드 : 응답
	 */
	RECEIVE("R"),
	/**
	 * 요청응답 구분코드 : 요청 더미 응답
	 */
	SEND_DUMMY("Q"),
	/**
	 * 요청응답 구분코드 : 응답 더미 응답
	 */
	RECEIVE_DUMMY("P");

	private final String value;

	private IfRspnDvsnCode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
