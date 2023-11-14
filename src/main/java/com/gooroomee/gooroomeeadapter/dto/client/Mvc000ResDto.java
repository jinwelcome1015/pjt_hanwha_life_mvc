package com.gooroomee.gooroomeeadapter.dto.client;

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
public class Mvc000ResDto {

	/** 사원번호 */
	public String emnb;

	/**
	 * 신분증 타입
	 * 
	 * <pre>
	 * "ID Card"
	 * "Driver's License"
	 * "Passport"
	 * "Alien Registration Card"
	 * </pre>
	 */
	private String idType;

	/** 고객명 */
	private String custNm;

	/** 고객생년월일 */
	@JsonFormat(pattern = "yyyyMMdd")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date custBirthDate;
//	private String custBirthDate;

	/**
	 * <pre>
	 * 고객성별 
	 * 	남 : "M"
	 * 	녀 : "F"
	 * </pre>
	 */
	private String custGender;

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
