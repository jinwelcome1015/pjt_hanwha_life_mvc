package com.gooroomee.adapter.constant;

public enum IfType {
	
	MCI("MCI"), ESB("ESB"), FEB("FEB");
	
	private String value;

	private IfType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
