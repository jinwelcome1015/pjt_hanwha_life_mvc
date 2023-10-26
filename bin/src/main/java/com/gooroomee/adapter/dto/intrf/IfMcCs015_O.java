package com.gooroomee.gooroomeeadapter.dto.intrf;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs015_O {

	/** 처리결과코드 */
	private String prcsRsltCode;

	/** 처리결과내용 */
	private String prcsRsltCntn;

	// XXX 확인
	/** 안내장발송기초SUID */
	private BigInteger ntfcSendBacsSuid;

}
