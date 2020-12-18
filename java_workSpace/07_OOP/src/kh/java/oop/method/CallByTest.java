package kh.java.oop.method;

/**
 * 
 * call by value : 값을 전달 (기본형)
 * 
 * call by reference : 주소값을 전달 (참조형)
 * 
 *
 */
public class CallByTest {

	public static void main(String[] args) {
		
		int a = 10;
		int[] arr = {1,2,3,4,5};
		
		CallByTest c = new CallByTest();
		c.test1(a); // call by value 
		System.out.println(a);
		
		c.test2(arr); // call by reference, 배열은 heap영역에 생성되기 때문
		System.out.println(arr[0]);
		
		String s = "안녕";
		c.test3(s); //call by reference 이지만 String 은 immutable class라서 안변함
		System.out.println(s);
		
		String[] sa = {"안녕"};
		c.test4(sa); //call by reference 이고 배열인 String이기 때문에(배열이중요) 값변경됨
		System.out.println(sa[0]);
	}

	public void test1(int x) {
//		System.out.println(x);
		x = 20;
	}
	public void test2(int[] arr) {
		arr[0] *= 100;
	}
	public void test3(String str) {
		str = "잘가";
	}
	public void test4(String[] str) {
		str[0]="잘가";
	}
}






