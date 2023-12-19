package com.gooroomee.backbone.external.dto.client;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc020ResDto {

	/** 고객ID */
	private String custId;

	/** 고객정보 진위 여부 (고객정보 일치 여부) */
	private String custInfoAuthenticityYn;

	/** 고객정보 진위확인 결과 코드 */
	private String custInfoAuthenticityResultCode;

	/** 고객정보 진위확인 결과 메시지 */
	private String custInfoAuthenticityResultMessage;

	/** 고객개인정보 유출여부 */
	private String custPersonalInfoleakYn;

	/** 고객개인정보 노출여부 */
	private String custPersonalInfoEpsrYn;

	/** 고객휴대폰번호 변경여부 */
	private String custHpnoChngYn;

	/** 싱글뷰 오픈 메세지 push 성공 여부 */
	private String singleViewOpenMessagePushSuccessYn;

}
