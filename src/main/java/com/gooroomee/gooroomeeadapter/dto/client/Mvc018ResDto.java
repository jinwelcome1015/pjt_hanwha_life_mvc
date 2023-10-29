package com.gooroomee.gooroomeeadapter.dto.client;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc018ResDto {

	// XXX 확인
	/** 우편번호 */
	private List<Pscd> pscdList;

	@Getter
	@Setter
	@ToString
	public static class Pscd {

		/** 1번째우편번호 */
		private String odn1Pscd;

		/** 2번째우편번호 */
		private String odn2Pscd;

		/** 도로명우편번호일련번호 */
		private String roadNmPscdSqno;

		/** 도로명기본주소 */
		private String roadNmBaseAddr;

		/** 도로명상세주소 */
		private String roadNmDetlAddr;

		/** 주소참고항목내용 */
		private String addrRfrcItmCntn;

		/** 지번우편번호일련번호 */
		private String ldnbPscdSqno;

		/** 지번기본주소 */
		private String ldnbBaseAddr;

		/** 주건물명 */
		private String mnbdNm;

		/** 지번상세주소 */
		private String ldnbDetlAddr;

		/** 1번째구분값 */
		private String odn1DvsnVal;

		/** 2번째구분값 */
		private String odn2DvsnVal;

	}

}