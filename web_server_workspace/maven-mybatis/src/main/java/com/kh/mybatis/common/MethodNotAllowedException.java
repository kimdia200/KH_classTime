package com.kh.mybatis.common;

public class MethodNotAllowedException extends RuntimeException {

	public MethodNotAllowedException() {
		super();
	}

	public MethodNotAllowedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNotAllowedException(String message) {
		super(message);
	}

	public MethodNotAllowedException(Throwable cause) {
		super(cause);
	}
}
