package com.gooroomee.gooroomeeadapter.dto.intrf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfMcCs999_O {
	
	/** 트랜잭션ID */
	private String trscId;
	
	/** 수행성공여부(Y/N) */
	private String rsltYn;
	
	/** 결과메시지. 특별한 경우를 제외한 일반적으로 rsltYn="N"일 경우에 있음. */
	private String rsltMsge;
	
	/** 결과 에러 코드. 특별한 경우를 제외한 일반적으로 rsltYn="N"일 경우에 있음. */
	private String rsltErroCode;
	
}
