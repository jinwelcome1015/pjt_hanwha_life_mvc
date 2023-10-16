package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ResSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc009ResDto extends ResSuperDto {
	
	/** 고객ID */
	private String custId;

	/** 유출여부 */
	private String leakYn;
	
	/** 노출여부 */
	private String epsrYn;
	
	/** 휴대폰번호변경여부 */
	private String hpnoChngYn;

}
