package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.math.BigInteger;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 사원목록조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter 
@Setter
@ToString
public class IfMcCs006_O {
	
	/** 사원정보 */
	private List<EmpeInfo> empeInfoList;
	
	/**
	 * 사원정보 DTO 클래스
	 * @author 신용진
	 */
	@Getter 
	@Setter
	@ToString
	public static class EmpeInfo {
		/** 기관명 */
		private String orgnNm;

		/** 기관전화번호 */
		private String orgnTlno;

		/** 기관코드 */
		private String orgnCode;

		/** 사원구분코드 */
		private String empeDvsnCode;

		/** 사원명 */
		private String empeNm;

		/** 사원번호 */
		private String emnb;

		/** 재직구분코드 */
		private String tnofDvsnCode;

		/** 전화내선번호 */
		private String tlphExno;

		/** 주민등록번호통합식별자SUID */
		private BigInteger rrnoInidSuid;

		/** 고객ID */
		private String custId;

		/** 주민등록번호 */
		private String rrno;

		/** 직급코드 */
		private String clpsCode;

		/** 직책코드 */
		private String rsofCode;

		/** 직위코드 */
		private String pstnCode;

		/** FP직책코드 */
		private String fpRsofCode;

		/** 내근여부 */
		private String wkisYn;

		/** 개인법인판매구분코드 */
		private String prsnCorpSaleDvsnCode;
	}

}
