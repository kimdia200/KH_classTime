package kh.java.condition;

import java.util.Scanner;

public class ifConditionTest {

	public static void main(String[] args) {

		ifConditionTest ict = new ifConditionTest();
//		ict.test1();
//		ict.test2();
//		ict.ageCheck();
//		ict.test3();
//		ict.test4();
//		ict.test6();
		ict.test7();
	}
	
	/**
	 * if
	 */
	public void test1() {
		int n = 10;
		
		if(n == n++) {
			System.out.println("if절이 실행되었습니다.");
		}
		
		System.out.println("--- test1 종료 ---");
	}
	
	/**
	 * if ... else
	 * - true: if절 실행
	 * - false : else절 실행
	 */
	public void test2() {
		//사용자로부터 수를 입력받고, 그 수가 짝수인지 홀수 인지 출력!
		int n;
		Scanner	sc = new Scanner(System.in);
		System.out.print("정수 입력  : ");
		n = sc.nextInt();
		
		
		if(n%2 ==1) {
			System.out.println("입력값은 홀수입니다.");
		}
		else {
			System.out.println("입력값은 짝수입니다.");
		}
		
		System.out.println("--- test2 종료 ---");
	}
	
	/**
	 * 사용자로부터 나이를 입력받고,
	 * 20세 이상이면, 성인인증되었습니다.
	 * 20세 미만이면, 미성년자입니다.
	 * 
	 */
	public void ageCheck() {
		Scanner input = new Scanner(System.in);
		System.out.print("나이입력 : ");
		int age = input.nextInt();
		
		if(age>=20) {
			System.out.println("성인 인증 되었습니다.");
		}else if(age <20) {
			System.out.println("미성년자입니다.");
		}
	
	}
	/**
	 * 삼항연산자 - 중첩사용
	 * 1. 가위
	 * 2. 바위
	 * 3. 보
	 */
	public void test3() {
		int num = (int)(Math.random()*3)+1;
		System.out.println(num);
		String rPS = num ==1 ? "가위" : num == 2 ? "바위" : "보";
		System.out.println(rPS);
	}
	/**
	 * if... else if ... [else ...]
	 * 1. 조건식1 : 참인경우 해당 if절 실행, 거짓인 경우 다음 조건식 검사
	 * 2. 조건식2
	 * 90~100 : A
	 * 80~89 : B
	 * 70~79 : C
	 * 60~69 : D
	 * 60미만 : F
	 */
	
	public void test4() {
		int num = 90;
		char grade = 'F';//char 기본값 공백
		
		if(num >=90) {
			grade = 'A';
		}else if(num >=80) {
			grade = 'B';
		}else if(num >=70){
			grade = 'C';
		}else if(num >=60) {
			grade = 'D';
		}
		System.out.println(grade);
	}
	
	/**
	 * test4에 비해 메모리상 비효율적
	 */
	public void test5() {
		int num = 53;
		char grade = 'F'; 
		
		if (num >= 90) {
			grade = 'A';
		}
		
		if (num >= 80 && num < 90) {
			grade = 'B';
		}
		
		if (num >= 70 && num < 80) {
			grade = 'C';
		}
		
		if (num >= 60 && num < 70) {
			grade = 'D';
		}
		
		System.out.println("학점 : " + grade);
	}
	/**
	 * block scope(블럭 범위)
	 *  - 블럭내에 변수를 선언하면, 블럭외부에서 접근할 수 없다.
	 */
	public void test6() {
		int num;
		if(true) {
//			int num = 100;
			num = 100;
		}
		System.out.println(num);
	}
	/**
	 * @실습문제 : 사용자로부터 문자를 입력받고, 숫자인지 아닌지 출력, 영문자인지, 한글인지 출력하세요
	 * 
	 */
	public void test7() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 하나를 입력하세요 : ");
		char c = sc.nextLine().charAt(0);
		
		if(c >='0' && c <='9') {
			System.out.println("숫자 입니다");
		}else if(c>='A' && c <= 'Z' ) {
			System.out.println("영문자 입니다.");
		}else if(c>='a' && c <= 'z' ) {
			System.out.println("영문자 입니다.");
		}else if(c>='가' && c <= '힣' ) {
			System.out.println("한글 입니다.");
		}else {
			System.out.println("특수문자 입니다.");
		}
	}
}
