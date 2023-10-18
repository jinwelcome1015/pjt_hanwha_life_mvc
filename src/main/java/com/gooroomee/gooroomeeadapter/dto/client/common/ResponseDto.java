package com.gooroomee.gooroomeeadapter.dto.client.common;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class ResponseDto<T> {
	
	
	public static enum Result{
		
		SUCCESS("SUCCESS"), FAIL("FAIL");
		
		private final String value;

		private Result(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	/** 결과 (열거형) */
	private final Result result;
	
	/** HttpStatus (열거형) */
	private final HttpStatus httpStatus;
	
	/** 데이터 */
	private final T data;
}
