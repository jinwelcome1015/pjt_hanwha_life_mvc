package com.gooroomee.adapter.dto.intrf;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs002_I {
	
	/**
	 * 진위 확인 구분 코드
	 */
	private String trflCnfmDvsnCode;
	
	/**
	 * 진위 확인 업무 구분 코드
	 */
	private String trflCnfmBswrDvsnCode;
	
	/**
	 * 진위 확인 채널 코드
	 */
	private String trflCnfmChnlCode;
	
	/**
	 * 처리 업무 화면 ID
	 */
	private String prcsBswrScrnId;
	
	/**
	 * 진위 확인 작업 코드
	 */
	private String trflCnfmJobCode;

	/**
	 * 고객 ID
	 */
	private String custId;
	
	/**
	 * 관리 번호
	 */
	private String mgmtNo;
	
	/**
	 * 고객 이름
	 */
	private String custNm;
	
	/**
	 * 발급 일자
	 */
	private Date isncDate;
	
	/**
	 * 생년 월일
	 */
	private Date btdt;
	
	/**
	 * 운전 면허 일련 번호 
	 */
	private String drvnLcnsSqno;
	
	/**
	 * 주만 등록 번호
	 */
	private String rrno;
	
	/**
	 * 운전 면허 일련 번호
	 */
	private String drvnLcnsNo;
	
	/**
	 * 외국인 등록 번호
	 */
	private String fmrRgstNo;
	
	/**
	 * 여권 번호
	 */
	private String psprNo;
	
	/**
	 * 만료 일자
	 */
	private Date expyDate;
	
	/**
	 * 이미지 관리 번호
	 */
	private String imgeMgmtNo;
	
	/**
	 * 이미지 종류 코드
	 */
	private String imgeKindCode;
	
	/**
	 * 이미지 파일 ID
	 */
	private String imgeField;

}
