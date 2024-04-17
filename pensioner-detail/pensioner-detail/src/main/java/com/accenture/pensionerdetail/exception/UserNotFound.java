package com.accenture.pensionerdetail.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFound extends RuntimeException {

	private static final long serialVersionUID = 210649836231358204L;
	private String message;
	public UserNotFound() {
		super();
	}
	public UserNotFound(String message) {
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
