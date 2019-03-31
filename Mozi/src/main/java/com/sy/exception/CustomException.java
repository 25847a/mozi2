package com.sy.exception;

/**
 * 系统自定义异常类，针对预期异常，需要在程序中抛出此类异常
 * 
 * @author DDM
 *
 */
@SuppressWarnings("serial")
public class CustomException extends Exception {
	public String message;

	public CustomException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
