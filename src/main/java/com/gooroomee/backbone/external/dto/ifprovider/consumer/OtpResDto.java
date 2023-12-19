package com.gooroomee.backbone.external.dto.ifprovider.consumer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpResDto {
	
	/** 구분값 */
	private String dvsnVal;
	
	/** 결과메시지내용 */
	private String rsltMsgeCntn;
	
	/** 결과데이터값 */
	private String rsltDatVal;
	
}
