package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ResSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc015ResDto extends ResSuperDto {

	/** 처리결과코드 */
	private String prcsRsltCode;

	/** 처리결과내용 */
	private String prcsRsltCntn;

	// XXX 확인
	/** 안내장발송기초SUID */
	private long ntfcSendBacsSuid;

}
