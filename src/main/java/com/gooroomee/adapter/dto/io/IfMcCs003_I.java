package com.gooroomee.adapter.dto.io;

import java.util.Date;

public class IfMcCs003_I {
	
	/**
	 * 고객 ID
	 */
	private String custId;
	
	/**
	 * 푸시 수신자 사원 번호
	 */
	private String pushRcvrEmnb;
	
	/**
	 * 일치 여부
	 */
	private String csnsYn;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getPushRcvrEmnb() {
		return pushRcvrEmnb;
	}

	public void setPushRcvrEmnb(String pushRcvrEmnb) {
		this.pushRcvrEmnb = pushRcvrEmnb;
	}

	public String getCsnsYn() {
		return csnsYn;
	}

	public void setCsnsYn(String csnsYn) {
		this.csnsYn = csnsYn;
	}

	@Override
	public String toString() {
		return "IfMcCs003_I [custId=" + custId + ", pushRcvrEmnb=" + pushRcvrEmnb + ", csnsYn=" + csnsYn + "]";
	}
	
	
}
