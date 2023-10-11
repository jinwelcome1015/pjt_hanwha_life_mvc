package com.gooroomee.adapter.constant;

/**
 * 서버유형
 */
public enum IfServerType {
	
	/**
	 * 서버유형 : 로컬
	 */
	LOCAL("L"),
	/**
	 * 서버유형 : 임시서버
	 */
	TEMP("T"),
	/**
	 * 서버유형 : 개발서버
	 */
	DEV("D"),
	/**
	 * 서버유형 : QA서버
	 */
	QA("Q"),
	/**
	 * 서버유형 : 운영서버
	 */
	PROD("P");

	private final String value;

	private IfServerType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
