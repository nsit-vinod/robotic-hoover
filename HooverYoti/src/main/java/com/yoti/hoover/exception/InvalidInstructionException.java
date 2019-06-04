package com.yoti.hoover.exception;

/*
 * Exception class used when hoover move instructions are not valid.
 */
public class InvalidInstructionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidInstructionException() {
		super();
	}

	public InvalidInstructionException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
