package com.gooroomee.gooroomeeadapter.dto.intrf;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfMcCs015_I {

	/** 안내장종류코드 */
	private String ntfcKindCode;

	/** 안내장템플릿코드 */
	private String ntfcTmplCode;

	// XXX 확인
	/** 안내장기준일자 */
	private Date ntfcRfdt;

	/** 발송담당기관코드 */
	private String sendOrchOrgnCode;

	/** 발송담당사원번호 */
	private String sendOrchEmnb;

	/** 발행유형구분코드 */
	private String isneTypeDvsnCode;

	/** 요청자사원번호 */
	private String rqsrEmnb;

	/** 실데이터여부 */
	private String actlDatYn;

	// XXX 확인
	/** 발신자전화번호연락처SUID */
	private long sndeTlnoCnplSuid;

	/** 발신자전화지역번호 */
	private String sndeTlphArcd;

	/** 발신자전화국번호 */
	private String sndeTlphOfno;

	/** 발신자전화개별번호 */
	private String sndeTlphInno;

	// XXX 확인
	/** 수령자이동전화연락처SUID */
	private long rpntMvmnTlphCnplSuid;

	/** 전화식별코드 */
	private String tlphIdnfCode;

	/** 전화국번호 */
	private String tlphOfno;

	/** 전화가입자번호 */
	private String tlphSbno;

	/** 고객ID */
	private String custId;

	/** 주민등록번호 */
	private String rrno;

	/** 고객명 */
	private String custNm;

	/** 메시지입력여부 */
	private String msgeInptYn;

	/** 메시지내용 */
	private String msgeCntn;

	/** 대체메시지내용 */
	private String sbsnMsgeCntn;

	// XXX 확인
	/** 발송항목목록 */
	private List<SendItm> sendItmList;

	@Getter
	@Setter
	@ToString
	public static class SendItm {
		// XXX 확인
		/** 순번 */
		private long sqnb;

		/** UMS항목값 */
		private String umsItmVal;

		/** 메시지제목명 */
		private String msgeTitlNm;

		/** 알림톡버튼내용 */
		private String nttkButnCntn;

		// XXX 확인
		/** 발송예약일시 */
		private Timestamp sendRsvtDttm;

		/** 관리번호구분코드 */
		private String mgmtNoDvsnCode;

		/** 참조업무번호 */
		private String rfrnBwno;

		/** 단체고객ID */
		private String grouCustId;

		/** 수금기관코드 */
		private String cltnOrgnCode;

		/** 수금인사원번호 */
		private String aentEmnb;

		// XXX 확인
		/** 기준통화소수자리수 */
		private int stndCrnyDcmlPsitCnt;

		/** 통화코드 */
		private String crnyCode;
	}

}
