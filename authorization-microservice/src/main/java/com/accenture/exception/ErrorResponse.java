package com.accenture.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	public ErrorResponse() {
		super();
	}
	public ErrorResponse(String message, int statusCode, Long exceptionTime) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.exceptionTime = exceptionTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Long getExceptionTime() {
		return exceptionTime;
	}
	public void setExceptionTime(Long exceptionTime) {
		this.exceptionTime = exceptionTime;
	}
	private String message;
	private int statusCode;
	private Long exceptionTime;

}
