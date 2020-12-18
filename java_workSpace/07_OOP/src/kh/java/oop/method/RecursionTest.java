package kh.java.oop.method;

public class RecursionTest {

	public static void main(String[] args) {
		RecursionTest r = new RecursionTest();
		int result = r.factorial(5);
		System.out.println("result : " + result);
	}

	/**
	 * 5! = 5*4*3*2*1;
	 * 
	 * 재귀 함수는 반드시 종료 조건을 기술 해야한다.
	 */
	public int factorial(int i) {
		if (i > 1) {
			System.out.println(i);
			return i*factorial(i - 1);
		}else
			return 1;
	}

}
