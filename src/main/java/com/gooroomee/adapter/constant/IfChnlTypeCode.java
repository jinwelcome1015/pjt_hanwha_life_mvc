package com.gooroomee.adapter.constant;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * 채널유형코드
 */
public enum IfChnlTypeCode {
	
	/**
	 * 채널유형코드 : PC
	 */
	PC("UI"),
	/**
	 * 채널유형코드 : SERVER
	 */
	SERVER("SVR"),
	/**
	 * 채널유형코드 : DEFAULT
	 */
	DEFAULT("");

	private final String value;

	private IfChnlTypeCode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
