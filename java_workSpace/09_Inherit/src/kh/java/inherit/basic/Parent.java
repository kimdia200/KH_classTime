package kh.java.inherit.basic;
/**
 * 상속 Inheritance 
 * 부모 클래스가 가지고 있는 member변수/메소드를 자식 클래스에서 선언하지 않고,
 *사용할 수 있게 하는것.
 *
 *public class 자식 클래스 extends 부모클래스{} : 확장하다
 *
 *
 *이점
 *중복을 제거해서 효율적으로 코드를 관리 할 수 있다.
 *
 *
 *특징
 *1.부모 클래스의 필드/메소드는 자식클래스에서 선언 없이 사용, 접근 가능하다
 *2.모든 클래스의 최상위 부모클래스는 Object
 *  아무 클래스도 상속하지 않았다면, extends Object가 생략된것
 *3.부모클래스 생성자, 초기화 블럭은 상속되지 않음
 *  부모클래스 생성자 호출 super()
 *4.부모클래스의 메소드는 자식클래스에서 재작성(오버라이드)가능
 *5.부모 클래스의 private필드/메소드는 상속되지만, 직접접근할수 없다
 *	getter, setter를 이용
 *	값대입시에 부모생성자를 호출해서 부모클래스 안에서 값대입.
 */
public class Parent{
	public String name;
	public int age;
	
	public Parent() {
		System.out.println("Parent 부모클래스 생성자 호출!");
	}
	
	
	public void say() {
		System.out.println("제가 애빕니다.");
	}
	
	public void printInfo() {
		System.out.println(name + ", " + age);
	}
	
}
