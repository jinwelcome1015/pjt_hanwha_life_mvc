package com.gooroomee.gooroomeeadapter.dto.ifprovider.provider.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfProviderResponseCommonDto<T> {
	
	/** 코드 */
	private String code;
	
	/** 메세지 */
	private String message;
	
	/** 데이터 */
	private T data;
	
}
