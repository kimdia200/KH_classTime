package kh.java.oop.access.modifier.field;

public class KiwiRun {

	public static void main(String[] args) {
		
		Kiwi kw = new Kiwi();
		System.out.println(kw.publicNum);
		System.out.println(kw.protectedNum); //같은 패키지 Or 자식클래스인 경우 OK
		System.out.println(kw.defaultNum);
//		System.out.println(kw.privateNum); //private는 접근불가~

	}

}
