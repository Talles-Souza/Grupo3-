package com.residencia.commerce.exception;

import java.io.Serializable;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private String details;

	public ExceptionResponse(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}