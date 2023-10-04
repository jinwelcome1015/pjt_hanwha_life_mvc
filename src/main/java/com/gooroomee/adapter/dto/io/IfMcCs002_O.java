package com.gooroomee.adapter.dto.io;

public class IfMcCs002_O {
	
	/**
	 * 일치 여부
	 */
	private String csnsYn;
	
	/**
	 * 진위 확인 결과 값
	 */
	private String trflCnfmRsltVal;
	
	/**
	 * 결과 메시지 내용
	 */
	private String rsltMsgeCntn;
	
	/**
	 * 전문 추적 번호
	 */
	private String tlgrTrcgNo;
	
	/**
	 * 고객 ID
	 */
	private String custId;

	public String getCsnsYn() {
		return csnsYn;
	}

	public void setCsnsYn(String csnsYn) {
		this.csnsYn = csnsYn;
	}

	public String getTrflCnfmRsltVal() {
		return trflCnfmRsltVal;
	}

	public void setTrflCnfmRsltVal(String trflCnfmRsltVal) {
		this.trflCnfmRsltVal = trflCnfmRsltVal;
	}

	public String getRsltMsgeCntn() {
		return rsltMsgeCntn;
	}

	public void setRsltMsgeCntn(String rsltMsgeCntn) {
		this.rsltMsgeCntn = rsltMsgeCntn;
	}

	public String getTlgrTrcgNo() {
		return tlgrTrcgNo;
	}

	public void setTlgrTrcgNo(String tlgrTrcgNo) {
		this.tlgrTrcgNo = tlgrTrcgNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "IfMcCs002_O [csnsYn=" + csnsYn + ", trflCnfmRsltVal=" + trflCnfmRsltVal + ", rsltMsgeCntn="
				+ rsltMsgeCntn + ", tlgrTrcgNo=" + tlgrTrcgNo + ", custId=" + custId + "]";
	}
	
}
