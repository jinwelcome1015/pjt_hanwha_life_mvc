package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc019ResDto {

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
	
	/** 고객명 */
	private String custNm;
	
	/**
	 * <pre>
	 * 고객성별 
	 * M (남자)
	 * F (여자)
	 * </pre> 
	 */
	private String custGender;
	
	/** 생년월일 */
//	@JsonFormat(pattern = "yyyyMMdd")
//	@DateTimeFormat(pattern = "yyyyMMdd")
//	private Date btdt;
	private String custBirthDate;
	
	/** 발급일자 */
//	@JsonFormat(pattern = "yyyyMMdd")
//	@DateTimeFormat(pattern = "yyyyMMdd")
//	private Date isncDate;
	private String isncDate;
	
	/** 만료일자 */
//	@JsonFormat(pattern = "yyyyMMdd")
//	@DateTimeFormat(pattern = "yyyyMMdd")
//	private Date expyDate;
	private String expyDate;

	/** 주민등록번호 */
	private String rrno;
	
	/** 운전면허번호 */
	private String drvnLcnsNo;
	
	/** 운전면허일련번호 */
	private String drvnLcnsSqno;
	
	/** 여권번호 */
	private String psprNo;
	
	/** 외국인등록번호 */
	private String frnrRgstNo;

}
