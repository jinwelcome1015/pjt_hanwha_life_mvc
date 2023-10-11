package com.gooroomee.adapter.constant;

/**
 * 인터페이스 타입
 */
public enum IfType {
	/**
	 * 인터페이스 타입 : MCI
	 */
	MCI("MCI"),
	/**
	 * 인터페이스 타입 : ESB
	 */
	ESB("ESB"), 
	/**
	 * 인터페이스 타입 : FEB
	 */
	FEB("FEB");
	
	private final String value;

	private IfType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
}
