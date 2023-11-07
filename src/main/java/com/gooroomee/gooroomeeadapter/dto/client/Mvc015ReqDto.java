package com.gooroomee.gooroomeeadapter.dto.client;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc015ReqDto {
	
	
	/** URL_PC */
	private String url_pc;

	/** URL_MOBILE */
	private String url_mobile;

//	/** 거래프로그램ID */
//	private String trnnPrgmId;

	/** 발신자부서코드 */
	private String sndeDeptCode;

//	/** 발송예약일시 */
//	private Timestamp sendRsvtDttm;

	/** 발신자전화번호 */
	private String sndeTlno;

	/** 고객ID */
	private String custId;

	/** 수신자명 */
	private String rcvrNm;

	/** 대체발송메시지내용 */
	private String sbsnSendMsgeCntn;

	/** 발신자전화지역번호 */
	private String sndeTlphArcd;

	/** 발신자전화국번호 */
	private String sndeTlphOfno;

	/** 발신자전화개별번호 */
	private String sndeTlphInno;

	/** 휴대폰전화통신사번호 */
	private String hpTlphTlcmNo;

	/** 휴대폰전화국번호 */
	private String hpTlphOfno;

	/** 휴대폰전화가입자번호 */
	private String hpTlphSbno;



}
