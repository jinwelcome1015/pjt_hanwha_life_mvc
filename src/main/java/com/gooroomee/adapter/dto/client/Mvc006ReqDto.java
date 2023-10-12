package com.gooroomee.adapter.dto.client;

import com.gooroomee.adapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc006ReqDto extends ReqSuperDto{

	/** 검색어명 */
	private String scwdNm;

	/** 기관코드 */
	private String orgnCode;
	
	/** 사원구분코드 */
	private String empeDvsnCode;
	
	/** 전체사원조회여부 */
	private String wholEmpeInqyYn;
	
	/** FP제외여부 */
	private String fpExlsYn;
	
	/** 재직구분코드 */
	private String tnofDvsnCode;

}
