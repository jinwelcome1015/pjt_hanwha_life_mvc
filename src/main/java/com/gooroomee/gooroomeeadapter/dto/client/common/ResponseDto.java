package com.gooroomee.gooroomeeadapter.dto.client.common;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDto<T> {

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static enum Result {

		SUCCESS("SUCCESS"), FAIL("FAIL");

		private final String value;
	}

	/** 결과 (열거형) */
	private final Result result;

	/** HttpStatus (열거형) */
	private final HttpStatus httpStatus;

	/** 데이터 */
	private final T data;
}
