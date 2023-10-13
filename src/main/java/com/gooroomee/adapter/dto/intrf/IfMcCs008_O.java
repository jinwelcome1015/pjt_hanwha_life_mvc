package com.gooroomee.adapter.dto.intrf;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs008_O {

	/** 고객명 */
	private String custNm;
	
	/** 주소 */
	private String addr;
	
	/** 법인등록번호 */
	private String corpRgstNo;
	
	/** 전화번호 */
	private String tlno;
	
	/** 고객식별자값 */
	private String custIdnrVal;
	
	/** 전화식별번호 */
	private String tlphIdno;
	
	/** 전화국번호 */
	private String tlphOfno;
	
	/** 전화가입자번호 */
	private String tlphSbno;
	
	// XXX 확인
	/** 고객계좌목록조회목록 */
	private String custAcntListInqyList;
	
	// XXX 확인
	/** 등록일시 */
	private Timestamp rgstDttm;
	
	/** 금융기관코드 */
	private String fnncOrgnCode;
	
	/** 금융기관코드명 */
	private String fnncOrgnCodeNm;
	
	/** 계좌번호 */
	private String acntNo;
	
	/** 예금주명 */
	private String achrNm;
	
	/** 예금주주민등록번호 */
	private String achrRrno;
	
	/** 결제수단관계유형코드 */
	private String stlmMeanRltsTypeCode;
	
	/** 결제수단관계유형코드명 */
	private String stlmMeanRltsTypeCodeNm;
	
	/** 계좌사용여부내용 */
	private String acntUseYnCntn;

}
