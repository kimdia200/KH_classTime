package kh.java.method;

import java.util.Scanner;

import javax.print.attribute.standard.PrinterInfo;

public class MethodTest {

	public static void main(String[] args) {
		
		MethodTest m = new MethodTest();
//		m.test1();
		m.test2();
	}
	
	public void test1() {
		//데이터 할당부
		String name = "홍길동";
		int age = 30;
		//데이터 출력부
		//현재 객체를 가리키는 참조변수
		this.printInfo(name, age);

		name = "신사임당";
		age = 50;
		printInfo(name, age);

		name = "세종대왕";
		age = 60;
		printInfo(name, age);
	}

	
	/**
	 * 매개인수(인자) : 메소드 호출에 전달된 값 (name, age)
	 * arguments
	 * 매개변수 : 메소드 호출시 전달된 값이 보관될 변수 공간 (name2, age2)
	 * parameter
	 */
	public void printInfo(String name2, int age2) {
//		System.out.println("안녕하세요!!! " + age2 + "살, "+ name2 + "입니다.");
		
		System.out.println("name = " + name2);
		System.out.println("age = " +age2);
		
	}
	
	public void test2() {
		int num1, num2;
		num1 = inputNumber();
		num2 = inputNumber();
		
		System.out.println(num1 + " + " + num2 + " = " + (num1+num2));
	}
	/**
	 * int형 값을 리턴하는 메소드
	 */
	public int inputNumber() {
		Scanner	sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int temp = sc.nextInt();
		
		return temp;
		
	}
	
}
