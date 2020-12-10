package kh.java.loop;

import java.util.Scanner;

/**
 * 
 * for(초기식 ; 조건식 ; 증감식){
 *	//반복실행할 코드
 *	}
 * 
 * -초기식 : for문이 시작하면서 단한번 실행. 증감변수 선언
 * -조건식 : true면 반복문 1회 실행, false면 반복문 중지
 * -증감식 : 증감변수 처리식(증가/감소)
 * 
 * 처리순서
 * 1. 초기식
 * 2. 조건식 - > true
 * 3. 반복실행 코드 
 * 4. 증감식
 * 5. 조건식 - > true면 반복 , false면 종료
 * 
 */
public class ForLoopTest {

	public static void main(String[] args) {
		
		ForLoopTest f = new ForLoopTest();
//		f.test1();
//		f.test2();
//		f.test3();
//		f.test4();
//		f.test5();
		f.test6();
		
	}
	
	public void test1(){
		
		for(int i=1 ; i<=100 ; i++) {
			System.out.println("안녕 " + i);
		}
		
	}
	public void test2() {
		for(int i=11 ; i<=20 ; i++) {
			System.out.print(i+ " ");
		}
	}
	
	//구구단 
	public void test3() {
		int dan;
		System.out.print("몇단을 출력할까요 ? : ");
		Scanner sc = new Scanner(System.in);
		dan=sc.nextInt();
		for(int i = 1 ; i<10 ; i++) {
			System.out.println(dan+" * "+i+" = "+dan*i);
		}
	}
	/**
	 * 사용자에게 이름을 입력받고 숫자를 입력받아서
	 * 해당이름을 해당 횟수만큼 반복출력하세요
	 */
	public void test4() {
		String name;
		int num;
		Scanner sc = new Scanner(System.in);
		System.out.print("사용자 이름 : ");
		name = sc.nextLine();
		System.out.print("반복 횟수 : ");
		num = sc.nextInt();
		
		for(int i = 0; i<num;i++) {
			System.out.println(name);
		}
	}
	/**
	 * 1~10까지 합계 구하기
	 * 1+2+3.....+10
	 */
	public void test5() {
		int sum=0;
		for(int i=1 ; i<11 ;i++) {
			sum+=i;
		}		
		System.out.println(sum);
	}
	/*
	 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
	 */
	public void test6() {
		for(int i=1;i<=10;i++) {
			if(i!=10)
				System.out.print(i + ", ");
			else
				System.out.print(i);
		}
	}
}
