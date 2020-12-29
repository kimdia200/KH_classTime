package kh.java.polymorphism.animal;
/**
 * 자식클래스에 대해 메소드 구현 강제화
 * 객체화 할 수 없다. new 추상클래스(), new 인터페이스() - > 객체생성불가
 * 자식클래스에 대한 규격을 제공! 부모클래스 타입으로도 override할 메서드를 모두 사용가능
 *
 * 1. 추상클래스
 * 		추상메서드 1개이상 가지고 있다면, 해당클래스는 반드시 추상클래스 여야한다.
 * 		객체생성을 방지하기 위해 추상메서드가 없어도 추상클래스로 만들수 있다.
 * 		추상메서드(미완성메서드)는 abstract키워드를 가지며, 메서드 몸통부 없음
 * 		자식클래스에 반드시 완ㅇ성(재작성)해야할 메서드 - > 메서드구현 강제화
 * 
 * 2. 인터페이스 (추상메서드 구현강제화만을 목적으로 하는 부모타입) implements(구현)
 * 		추상메서드, 상수 필드만 가질수 있다.
 * 		다중상속이 가능하다(다중구현 가능)
 * 		인터페이스간 상속은 extends키워드를 사용
 * 		일반클래스에서 인터페이스 구현시는 implements사용
 */
public abstract class Animal {
	public void say() {
		System.out.println("안녕하세요, 저는 동물입니다.");
	}
	public Animal() {
		super();
	}
	public abstract void attack();
}
