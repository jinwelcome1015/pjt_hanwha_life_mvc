package com.gooroomee.backbone.external.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc016ReqDto {
	
	/** 사원번호 */
	private String emnb;

	// XXX 확인
	/** SNS */
	private Sns sns;

	/** 전화번호 */
	// XXX 확인
	private Tlno tlno;

	// XXX 확인
	/** 주소 */
	private Addr addr;

	// XXX 확인
	/** 전자위치 */
	private ElctLoct elctLoct;

	@Getter
	@Setter
	@ToString
	public static class Sns {

		/** 연락처SUID */
		private String cnplSuid;

		/** SNS값 */
		private String snsVal;

		/** SNS비고내용 */
		private String snsRmrkCntn;
	}

	@Getter
	@Setter
	@ToString
	public static class Tlno {
		/** 연락처SUID */
		private String cnplSuid;

		/** 국가코드 */
		private String cntyCode;

		/** 전화식별코드 */
		private String tlphIdnfCode;

		/** 전화국번호 */
		private String tlphOfno;

		/** 전화가입자번호 */
		private String tlphSbno;

		/** 전화내선번호 */
		private String tlphExno;

		/** 전화번호비고내용 */
		private String tlnoRmrkCntn;
	}

	@Getter
	@Setter
	@ToString
	public static class Addr {
		/** 연락처SUID */
		private String cnplSuid;

		/** 국가코드 */
		private String cntyCode;

		/** 기본주소 */
		private String baseAddr;

		/** 상세주소 */
		private String detlAddr;

		/** 건물번호값 */
		private String bldnNoVal;

		/** 1번째우편번호 */
		private String odn1Pscd;

		/** 2번째우편번호 */
		private String odn2Pscd;

		/** 3번째우편번호 */
		private String odn3Pscd;

		/** 도로명 */
		private String roadNm;

		/** 도로명주소여부 */
		private String roadNmAddrYn;

		/** 정제여부 */
		private String clsgYn;

		/** 시도구분코드 */
		private String ctprDvsnCode;

		/** 주소비고내용 */
		private String addrRmrkCntn;

	}

	@Getter
	@Setter
	@ToString
	public static class ElctLoct {
		/** 연락처SUID */
		private String cnplSuid;

		/** 전자위치구분코드 */
		private String elctLoctDvsnCode;

		/** 전자위치값 */
		private String elctLoctVal;

		/** 이메일도메인코드 */
		private String mailDmanCode;

		/** 전자위치비고내용 */
		private String elctLoctRmrkCntn;
	}
}
