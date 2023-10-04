package com.gooroomee.adapter.dto;

import org.springframework.http.HttpStatus;

public class ResponseDto<T> {
	
	HttpStatus httpStatus;
	T data;
	
	public ResponseDto(HttpStatus httpStatus, T data) {
		super();
		this.httpStatus = httpStatus;
		this.data = data;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDto [httpStatus=" + httpStatus + ", data=" + data + "]";
	}

}
