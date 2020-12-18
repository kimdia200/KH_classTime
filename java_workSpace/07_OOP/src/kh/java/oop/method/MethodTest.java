package kh.java.oop.method;

/**
 * 
 * static method : class메소드
 * 				  클래스명.메소드명 호출
 * 				 static 자원(field, method)만 접근 가능하다.
 * 
 * non static method : member메소드 혹은 instance메소드 라고 한다
 * 					  객체.메소드명 호출
 * 					 non-static, static 자원 모드 접근 가능하다.
 * 
 */
public class MethodTest {
	
	private int num=100;
	private static int snum=99;
	
	public static void main(String[] args) {
		MethodTest.a();
		
		MethodTest m = new MethodTest();
		m.b();
	}
	public static void a() {
		System.out.println("a!");
//		System.out.println("num = " + num); //static 메소드에서는 static 변수만 접근 가능
		System.out.println("snum = "+snum);
		System.out.println();
	}
	public void b() {
		System.out.println("b!");
		System.out.println("num = " + num); //인스턴스 메소드에서는 static 변수도 접근 가능
		System.out.println("snum = "+snum);
		System.out.println();
		MethodTest.a();
	}

}
