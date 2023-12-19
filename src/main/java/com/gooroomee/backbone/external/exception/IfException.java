package com.gooroomee.backbone.external.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 다른 서버, 클라이언트들과 통신 과정에 발생하는 예외 정보를 담는 클래스
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfException extends RuntimeException {

	/** 직렬화, 역직렬화를 위한  serialVersionUID */
	private static final long serialVersionUID = -5230759107385844663L;
	
	/** HttpStatus 객체 */
	private HttpStatus httpStatus;
	
	/**
	 * IfException 객체 생성자
	 * @param httpStatus HttpStatus 객체
	 * @param message 예외 메세지
	 */
	public IfException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}
