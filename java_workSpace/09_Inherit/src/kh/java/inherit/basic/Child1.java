package kh.java.inherit.basic;

public class Child1 extends Parent{
	/**
	 * super() 부모생성자를 호출
	 * 생략해도 jvm이 자동으로 생성해 호출
	 * 맨 첫줄에 작성되어야 함.
	 * this와 동시 사용불가!
	 */
	public Child1() {
		System.out.println("Child1 자식 클래스 생성자 호출");
	}
	
	@Override
	public void say() {
		System.out.println("제가, 첫째 입니다.");
	}
	
	public void game() {
		System.out.println("자식은 게임중");
	}
}
