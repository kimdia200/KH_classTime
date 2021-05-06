package com.kh.mybatis.common;

public class ControllerNotFoundException extends RuntimeException{

	public ControllerNotFoundException() {
		super();
	}

	public ControllerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ControllerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerNotFoundException(String message) {
		super(message);
	}

	public ControllerNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
