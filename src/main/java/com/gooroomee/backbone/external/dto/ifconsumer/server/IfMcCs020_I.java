package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 사용안함
 * 
 * 요청 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs020_I {
	
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

	/** 진위확인구분코드 */
	private String trflCnfmDvsnCode;

	/** 진위확인업무구분코드 */
	private String trflCnfmBswrDvsnCode;

	/** 진위확인채널코드 */
	private String trflCnfmChnlCode;

	/** 처리업무화면ID */
	private String prcsBswrScrnId;

	/** 진위확인작업코드 */
	private String trflCnfmJobCode;

	/** 고객ID */
	private String custId;

	/** 관리번호 */
	private String mgmtNo;

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

//	/** 이미지관리번호 */
//	private String imgeMgmtNo;

//	/** 이미지종류코드 */
//	private String imgeKindCode;

//	/** 이미지파일ID */
//	private String imgeFileId;
	
}
