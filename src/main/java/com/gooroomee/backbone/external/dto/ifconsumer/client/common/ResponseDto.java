package com.gooroomee.backbone.external.dto.ifconsumer.client.common;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * API 응답을 위한 공통 wrapping 용 DTO
 * @author 신용진
 *
 * @param <T> 응답 데이터 타입
 */
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
