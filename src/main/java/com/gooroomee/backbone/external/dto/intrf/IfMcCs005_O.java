package com.gooroomee.backbone.external.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs005_O {
	
	
	/** 응답코드값 */
	private String rspnCodeVal;
	
	/** 응답메시지내용 */
	private String rspnMsgeCntn;
	
	/** 예약컬럼값 */
	private String rsvtClmnVal;

	/** 응답메시지고유ID */
	private String rspnMsgeUniqId;

	/** 사원명 */
	private String empeNm;

	/** 주민등록번호 */
	private String rrno;

	/** 사용자구분코드 */
	private String userDvsnCode;

	/** 직책코드 */
	private String rsofCode;

	/** 직급코드 */
	private String clpsCode;

	/** 소속기관코드 */
	private String belnOrgnCode;

	/** 소속기관명 */
	private String belnOrgnNm;

	/** 영업소기관코드 */
	private String bnofOrgnCode;

	/** 영업소명 */
	private String bnofNm;

	/** 지점기관코드 */
	private String brocOrgnCode;

	/** 지점명 */
	private String brocNm;

	/** 지역단기관코드 */
	private String lcscOrgnCode;

	/** 지역단명 */
	private String lcscNm;

	/** 휴대폰번호 */
	private String hpno;

	/** 이메일주소 */
	private String mailAddr;

	/** 상위기관코드 */
	private String ogztFormDvsnCode;

	/** 조직형태구분코드 */
	private String hgrnOrgnCode;

	/** 기관전화번호 */
	private String orgnTlno;

	/** 기관축약명 */
	private String orgnAbrvNm;

	/** 상위기관명 */
	private String hgrnOrgnNm;

	
	
	/** 시스템 이용권한 소유여부 Y/N */
	private String hasAuthorityYn;
}
