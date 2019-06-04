package com.yoti.hoover.exception;
/*
 * Exception class used when hoover do not support move behaviour
 */
public class MoveNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoveNotSupportedException() {
		super();
	}
	
	public MoveNotSupportedException(String message) {
		super(message);
	}
}
