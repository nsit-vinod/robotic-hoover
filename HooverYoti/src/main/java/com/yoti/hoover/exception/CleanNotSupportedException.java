package com.yoti.hoover.exception;
/*
 * Exception class used when hoover do not support clean behaviour
 */
public class CleanNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	public CleanNotSupportedException() {
		super();
	}
	
	public CleanNotSupportedException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}
	
}
