package kh.java.oop.method;

/**
 * 
 * Method Overloading
 * 메소드명은 직관적으로 작성할 것
 * 동사(+ 명사)
 * setUserName
 * getUserName
 * printInfo
 * 
 * 
 * 
 * add(int, int)
 * add(double, double)
 * 위의 두개의 메소드가 받는 파라미터 형태만 다르고 하는일이 같다면
 * 하나의 이름을 쓰게 해주자 ~! 이거지
 * 
 * 
 */
public class OverloadingTest {

	public static void main(String[] args) {
		
		System.out.println(123);
		System.out.println(true);
		System.out.println('a');
		System.out.println(123.456);

	}
	
	public void test() {}
	public void test(int i) {}
	public void test(char ch) {}
	public void test(int i, int j) {}
//	public void test(int j, int i) {} //매개변수명이 다른 것은 오버로딩이 아니다.
	public void test(String s) {}
//	private void test(String s) {}//접근제한자가 다른 것은 오버로딩이 아니다.
//	public String test(String s) {}//리턴타입이 다른 것은 오버로딩이 아니다.
	public void test(String s, int i, int j) {}
	public void test(boolean b) {}
	public void test(int[] arr) {}
	public void test(int[][] arr) {}
	public void test(double d) {}

}
