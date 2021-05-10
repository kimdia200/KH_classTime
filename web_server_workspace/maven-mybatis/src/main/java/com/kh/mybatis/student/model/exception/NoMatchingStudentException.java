package com.kh.mybatis.student.model.exception;

public class NoMatchingStudentException extends RuntimeException {

	public NoMatchingStudentException() {
		super();
	}

	public NoMatchingStudentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoMatchingStudentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoMatchingStudentException(String message) {
		super(message);
	}

	public NoMatchingStudentException(Throwable cause) {
		super(cause);
	}
}
