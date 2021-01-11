package kh.java.inner.class_;

public class Main {
	public static void main(String[] args) {
		OuterClass outer = new OuterClass();
		OuterClass.A a = outer.new A(); //멤버 내부클래스
//		a.aaa();
		
		OuterClass.B b = new OuterClass.B();//static내부클래스 = 클래스명으로 접근
//		b.bbb();
		
		//지역내부클래스는 외부에서 절대 접근 할 수 없다.
		outer.test();
	}
}
