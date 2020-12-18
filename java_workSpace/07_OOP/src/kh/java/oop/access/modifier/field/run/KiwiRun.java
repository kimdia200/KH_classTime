package kh.java.oop.access.modifier.field.run;

import kh.java.oop.access.modifier.field.Kiwi;

public class KiwiRun {

	public static void main(String[] args) {
		
		Kiwi kw = new Kiwi();
		System.out.println(kw.publicNum);
//		System.out.println(kw.protectedNum); //같은패키지 Or 자식클래스인 경우 OK해주지만 지금은 갈라지는 tree라서
//		System.out.println(kw.defaultNum);
//		System.out.println(kw.privateNum);
		
	}

}
