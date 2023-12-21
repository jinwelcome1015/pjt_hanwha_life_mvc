package com.gooroomee.backbone.external.dto.ifconsumer.client;

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
public class Mvc020ReqDto {

	/** 사원번호 */
	private String emnb;
	
	/**
	 * <pre>
	 * 신분증 타입
	 * ID Card (주민등록증)
	 * Driver's License (운전면허증)
	 * Passport (여권)
	 * Alien Registration Card (외국인등록증)
	 * </pre>
	 */
	private String idType;
	
	/** 화면 ID */
	@JsonProperty("SCRN_ID")
	private String SCRN_ID;

	/** 고객명 */
	private String custNm;

	// XXX 확인
	/** 발급일자 */
//	@JsonFormat(pattern = "yyyyMMdd")
//	@DateTimeFormat(pattern = "yyyyMMdd")
//	private Date isncDate;
	private String isncDate;

	// XXX 확인
	/** 생년월일 */
//	@JsonFormat(pattern = "yyyyMMdd")
//	@DateTimeFormat(pattern = "yyyyMMdd")
//	private Date btdt;
	private String btdt;

	/** 운전면허일련번호 */
	private String drvnLcnsSqno;

	/** 주민등록번호 */
	private String rrno;

	/** 운전면허번호 */
	private String drvnLcnsNo;

	/** 외국인등록번호 */
	private String frnrRgstNo;

	/** 여권번호 */
	private String psprNo;

	// XXX 확인
	/** 만료일자 */
//	@JsonFormat(pattern = "yyyyMMdd")
//	@DateTimeFormat(pattern = "yyyyMMdd")
//	private Date expyDate;
	private String expyDate;

}
