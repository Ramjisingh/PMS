package com.accenture.processpension.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 210649836231358204L;
	private String message;
	public TokenException() {
		super();
	}
	public TokenException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}