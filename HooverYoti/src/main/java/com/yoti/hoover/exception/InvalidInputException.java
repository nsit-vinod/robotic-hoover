package com.yoti.hoover.exception;
/*
 * Exception class used when hoover input is not valid.
 */
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidInputException() {
		super();
		
	}
	public InvalidInputException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
