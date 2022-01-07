package com.tis.in.BanX.exception;

import java.util.List;

public class ValidationsFatalException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Throwable cause;
	private List<String> details;

	public ValidationsFatalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationsFatalException(String message, Throwable cause, List<String> details) {
		super(message, cause);
		this.details = details;
	}

	public ValidationsFatalException(String message, List<String> details) {
		super(message);
		this.details = details;
	}

	public List<String> getDetails() {
		return details;
	}
}