package com.gooroomee.gooroomeeadapter.dto.client;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc007ResDto {

	/** 총건수 */
	private int totCont;

	/** 다음페이지여부 */
	private String nextPageYn;

	// XXX 확인
	/** 고객계약정보조회결과 */
	private List<CustCntcInfoInqyRslt> custCntcInfoInqyRsltList;

	@Getter
	@Setter
	@ToString
	public static class CustCntcInfoInqyRslt {
		/** 적용이율 */
		private double aplyIrrt;

		/** 보험기간 */
		private int isrnTerm;

		/** 계약상태명 */
		private String cntcSttsNm;

		/** 증권번호 */
		private String polyNo;

		/** 계약자명 */
		private String polrNm;

		/** 계약자고객ID */
		private String polrCustId;

		/** 지급보류여부 */
		private String otpySspdYn;

		/** 수금방법명 */
		private String cltnMthdNm;

		/** 피보험자명 */
		private String inurNm;

		/** 피보험자고객ID */
		private String inurCustId;

		/** 최종납입회차 */
		private int lastPaymTmth;

		/** 최종납입년월 */
		private String lastPaymYymm;

		// XXX 확인
		/** 계약일자 */
		@JsonFormat(pattern = "yyyyMMdd")
		@DateTimeFormat(pattern = "yyyyMMdd")
		private Date ctdt;

		// XXX 확인
		/** 만기일자 */
		@JsonFormat(pattern = "yyyyMMdd")
		@DateTimeFormat(pattern = "yyyyMMdd")
		private Date mtrtDate;

		/** 보험만기구분값 */
		private String isrnMtrtDvsnVal;

		/** 갱신구분명 */
		private String rnwlDvsnNm;

		/** 납입회수 */
		private int pycn;

		/** 보험종류명 */
		private String isrnKindNm;

		/** 상품구분명 */
		private String prodDvsnNm;

		/** 한정납입회수 */
		private int lmttPycn;

		/** 청구보험료 */
		private double bingPrem;

		/** 수금보험료 */
		private double cltnPrem;

		/** 납입주기코드 */
		private String paymCyclCode;

		/** 조회권한여부 */
		private String inqyAtrtYn;

		/** 메시지내용 */
		private String msgeCntn;

		/** 보험종류세코드 */
		private String isrnKindDtcd;

		/** 보험종류목코드 */
		private String isrnKindItcd;

		/** 구보험종류코드 */
		private String oldIsrnKindCode;

		/** 보험종류축약명 */
		private String isrnKindAbrvNm;

		/** 계약상태코드 */
		private String cntcSttsCode;

		/** 사망수익자여부 */
		private String dethBnfcYn;

		/** 고객정보조회가능여부 */
		private String custInfoInqyPssbYn;

		/** 납입기간 */
		private int paymTerm;

		/** 고객추가요청메시지내용 */
		private String custAddnRqstMsgeCntn;

		/** 수금기관코드 */
		private String cltnOrgnCode;

		/** 수금FP번호 */
		private String cltnFpNo;

		/** 계약납입상태코드 */
		private String cntcPaymSttsCode;

		/** 납입주기값 */
		private int paymCyclVal;

		/** 실납입기간 */
		private int acpyTerm;

		/** 실납입기간구분코드 */
		private String acpyTermDvsnCode;

		/** 실보험기간 */
		private int actlIsrnTerm;

		/** 실보험기간구분코드 */
		private String actlIsrnTermDvsnCode;

		/** 금융정보조회제한여부 */
		private String fnncInfoInqyLimtYn;

		/** 금융정보조회제한메시지내용 */
		private String fnncInfoInqyLimtMsgeCntn;

		/** 납입기간주기내용 */
		private String paymTermCyclCntn;

		/** 계약상태상세사유코드명 */
		private String cntcSttsDetlResnCodeNm;
	}

}
