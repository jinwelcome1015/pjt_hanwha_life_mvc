package com.gooroomee.gooroomeeadapter.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfException extends RuntimeException {

	private static final long serialVersionUID = -5230759107385844663L;
	
//	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	private HttpStatus httpStatus;

	/*
	public IfException(String message) {
		super(message);
	}
	*/
	
	public IfException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}
