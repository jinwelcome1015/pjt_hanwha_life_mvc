package com.gooroomee.adapter.dto.common;

import org.springframework.http.HttpStatus;

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
	
	Result result;
	HttpStatus httpStatus;
	T data;
	
	public ResponseDto(Result result, HttpStatus httpStatus, T data) {
		super();
		this.result = result;
		this.httpStatus = httpStatus;
		this.data = data;
	}
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
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
		return "ResponseDto [result=" + result + ", httpStatus=" + httpStatus + ", data=" + data + "]";
	}

}
