package com.gooroomee.gooroomeeadapter.dto.client;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Mvc000ResDto {
	
	OcrResult ocrResult;
	
	Mvc002ResDto verification;
	
	Mvc003ResDto itfcIdcdScan;
	
	Mvc007ResDto contract;
	
	Mvc008ResDto account;
	
	Mvc009ResDto personalInfoLeak;
	
	@Getter
	@Setter
	@ToString
	public static class OcrResult {
		/** 사원번호 */
		public String emnb;
		
		/** 화면 ID */
		@JsonProperty("SCRN_ID")
		private String SCRN_ID;
		
		/** 사용자 ID */
		private String USER_ID;
		
		/** API 호출 UUID */
		private String requestId;

		/** V2로 고정 */
		private String version;
		
		/** API 호출 Timestamp 값 */
		private String timestamp;
		
		/** 신분증 타입 */
		/*
		IdCard("ID Card", "004"),
		DriverLicense("Driver's License", "011"),
		Passport("Passport", "911"),
		AlienRegistrationCard("Alien Registration Card", "017");
		*/
		private String idType;
		
		/** 고객명 */
		private String custNm;

		// XXX 확인
		/** 발급일자 */
		private Date isncDate;

		// XXX 확인
		/** 생년월일 */
		private Date btdt;

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
		private Date expyDate;
	}
	
	
}
