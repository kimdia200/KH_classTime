package kh.java.error;

/**
 * Error란?!
 * 예외처리 등 프로그램적으로 해결할 수 없는 심각한 오류
 * 
 * @author kimYS
 *
 */
public class ErrorTest {
	public static void main(String[] args) {
		ErrorTest e = new ErrorTest();
//		e.test1();
		e.test2();
		System.out.println("=========프로그램 정상종료=========");
	}
	public void test1() {
		long[] arr = new long[Integer.MAX_VALUE];
		//java.lang.OutOfMemoryError: Requested array size exceeds VM limit
		
	}
	public void test2() {
		System.out.println("test2 호출");
		test2(); //종료조건 없는 재귀호출
		//java.lang.StackOverflowError
	}
}
