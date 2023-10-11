package com.gooroomee.adapter.constant;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * 처리결과구분코드
 */
public enum IfPrcsRsltDvsnCode {
	
	/**
	 * 처리결과구분코드 : 정상
	 */
	NORMAL("1"),
	/**
	 * 처리결과구분코드 : 비정상
	 */
	ABNORMAL("0");

	private final String value;

	private IfPrcsRsltDvsnCode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
