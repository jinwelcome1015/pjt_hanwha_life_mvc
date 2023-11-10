package com.gooroomee.gooroomeeadapter.dto.client;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc002ReqDto {

	/** 사원번호 */
	public String emnb;

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
//	private Date isncDate;
	private String isncDate;

	// XXX 확인
	/** 생년월일 */
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
//	private Date expyDate;
	private String expyDate;

//	/** 이미지관리번호 */
//	private String imgeMgmtNo;

//	/** 이미지종류코드 */
//	private String imgeKindCode;

//	/** 이미지파일ID */
//	private String imgeFileId;

}
