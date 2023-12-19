package com.gooroomee.backbone.external.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 인증, 인가 과정에 발생하는 예외 정보를 담는 클래스
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class AuthException extends RuntimeException {
	
	/** 직렬화, 역직렬화를 위한  serialVersionUID */
	private static final long serialVersionUID = -4974572770122200387L;

	/**
	 * AuthException 객체 생성자
	 * @param message 예외 메세지
	 */
	public AuthException(String message) {
		super(message);
	}
}
