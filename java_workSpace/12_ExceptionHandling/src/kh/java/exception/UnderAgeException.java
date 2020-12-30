package kh.java.exception;
/**
 * 커스텀 예외 클래스를 작성해보자~
 * 
 * 의미전달을 명확히 함.
 * 
 * 방법 두가지
 * 1. checked Exception : extends Exception (강제화)
 * 2. unchecked Exception : extends RuntimeException (강제아님)
 */
public class UnderAgeException extends Exception{

	//부모생성자 따라만들어주기
	public UnderAgeException() {
		super();
	}

	public UnderAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnderAgeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnderAgeException(String message) {
		super(message);
	}

	public UnderAgeException(Throwable cause) {
		super(cause);
	}
}
