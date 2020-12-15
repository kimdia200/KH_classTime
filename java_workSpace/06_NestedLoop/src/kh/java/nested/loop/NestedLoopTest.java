package kh.java.nested.loop;

import java.util.Scanner;

public class NestedLoopTest {
	public static void main(String[] args) {
		NestedLoopTest n = new NestedLoopTest();
		n.test8();
	}
	public void test() {
		
		for(int i = 0; i<3 ; i++) {			
			for(int j = 0; j < 5; j++) {
//				System.out.print(i+","+j+" ");
				System.out.print("★");
			}
			System.out.println();
		}
		
	}
	/**
	 * 사용자로부터 행과 열을 입력받아 별로 출력하세요
	 */
	public void test2() {
		int a,b;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("몇 행으로 만들까요 ? : ");
		a = sc.nextInt();
		
		System.out.print("몇 열로 만들까요 ? : ");
		b = sc.nextInt();
		
		for(int i = 0; i<a ; i++) {			
			for(int j = 0; j < b; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
	}
	/**
	 * 사용자로부터 행과 열을 입력받고 해당크기의 별을 출력하세요
	 * 1열의 별은 ☆, 나머지 열은 ★로 출력하세요
	 */
	public void test3() {
		int a,b;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("몇 행으로 만들까요 ? : ");
		a = sc.nextInt();
		
		System.out.print("몇 열로 만들까요 ? : ");
		b = sc.nextInt();
		
		for(int i = 0; i<a ; i++) {			
			for(int j = 0; j < b; j++) {
				if(j==0)
					System.out.print("☆");
				else
					System.out.print("★");
			}
			System.out.println();
		}
	}
	/**
	 * 구구단 전체 출력
	 */
	
	public void test4() {
		for(int i = 2; i<10; i++ ) {
			System.out.println("---------- "+i+"단출력 ----------");
			for(int j = 1; j<10; j++) {
				System.out.println("* "+i+" * "+j+" = "+ (i*j));
			}
			System.out.println();
		}
	}
	/**
	 * i*j가 300이 넘어가면 구구단 프로그램 바로 종료하기
	 * 라벨을 사용
	 */
	public void test5() {
		outter: //반복문 라벨!!!
		for(int i = 2; i<100; i++ ) {
			System.out.println("---------- "+i+"단출력 ----------");
			for(int j = 1; j<10; j++) {
				if(i*j>=300) break outter;
				System.out.println("* "+i+" * "+j+" = "+ (i*j));
			}
			System.out.println();
		}
	}
	/**
	 * label 이 있는 continue문
	 * 
	 * 각 단에서 dan, su가 동일한 때 까지 출력 
	 */
	public void test6() {
		outter:
		for(int i = 2;i<10;i++) {
			System.out.println("---------- "+i+"단출력 ----------");
			for(int j = 1; j<10; j++) {
				System.out.println("* "+i+" * "+j+" = "+ (i*j));
				if(i==j) {
					System.out.println();
					continue outter;
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * label 이 있는 break문
	 * 
	 * 실습문제 : 
	 * 0:●
	 * 1:●●
	 * 2:●●●
	 * 3:●●●●
	 * 4:●●●●●
	 */
	public void test7() {
			for(int i = 0;i<5;i++) {
				System.out.print(i+": ");
				for(int j = 0; j<5; j++) {
					System.out.print("●");
					if(i==j) {
						System.out.println();
						break;
					}
				}
			}
			System.out.println();
			
			//강사님풀이
			for(int i =0; i<5; i++) {
				for(int j=0; j<(i+1);j++) {
					System.out.print("●");
				}
				System.out.println();
			}
	}
	/**
	 * ●●●
	 * ●
	 * ●●●
	 * ●
	 * ●●●
	 */
	public void test8() {
		outter:
		for(int i = 0;i<5;i++) {
			for(int j = 0; j<3; j++) {
				System.out.print("●");
				if(i%2==1) {
					System.out.println();
					continue outter;
				}
			}
			System.out.println();
		}
	}
}