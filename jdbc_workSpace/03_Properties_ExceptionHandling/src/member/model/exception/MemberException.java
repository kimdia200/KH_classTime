package member.model.exception;

/**
 * CheckedException : extends Exception
 * UnCheckedException : extends RuntimeException
 * @author family
 *
 */
public class MemberException extends RuntimeException {

	public MemberException() {
		super();
	}

	public MemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	//현재 사용할 생성자
	//cause는 애초에 발생한 exception
	public MemberException(String message, Throwable cause) {
		super(message, cause);
	}

	public MemberException(String message) {
		super(message);
	}

	public MemberException(Throwable cause) {
		super(cause);
	}
	
}
