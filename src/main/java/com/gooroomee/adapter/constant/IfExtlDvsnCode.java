package com.gooroomee.adapter.constant;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * 당타발 구분코드
 */
public enum IfExtlDvsnCode {
	
	/**
	 * 당타발 구분코드 : 타발
	 */
	OUTWARD("1"),
	/**
	 * 당타발 구분코드 : 당발
	 */
	INWARD("2");

	private final String value;

	private IfExtlDvsnCode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
