package kh.java.inner.class_;
/**
 * 내부클래스
 * 1.static 내부클래스
 * 2.non_static 내부클래스
 * 3.지역 내부클래스
 * 변수랑 종류가 같네?? 변수처럼 쉽게 사용 할 수 있다.
 * 
 * 목적
 * 1.외부클래스 안에서만 사용할때.(굳이 온전한 클래스를 만들지 안아도 될때) 
 * 2.외부클래스의 자원(private 자원)에 쉽게 접근 하기 위해.
 * 
 *
 */
public class OuterClass {
	
	private int num=100;
	private static int snum=99;
	
	public static void main(String[] args) {
		new OuterClass();
	}
	
	public OuterClass() {
		new A().aaa();
		
		new B().bbb();
		
		test();
	}
	//내부클래스는 접근제어자를 네개 모두 사용가능하다
	public class A{
		public void aaa() {
			System.out.println(num);
			System.out.println(snum);
		}
	}
	
	public static class B{
		public void bbb() {
//			System.out.println(num);//non_static자원접근불가
			System.out.println(snum);
		}
	}
	/**
	 * 지역내부클래스
	 * 지역변수와 마찬가지로 접근제한자를 가질 수 없다.
	 */
	public void test() {
		class C{
			public void ccc() {
				System.out.println(num);
				System.out.println(snum);
			}
		}
		C c = new C();
		c.ccc();
	}
	
	
}
