package kh.java.randum.number;

import java.util.Random;
import java.util.Scanner;

public class RandomNumberTest {

	public static void main(String[] args) {

		RandomNumberTest r = new RandomNumberTest();
//		r.test1();
//		r.test2();
//		r.test3();
		r.test4();
		
	}
	
	/**
	 * java.util.Random
	 */
	public void test1() {
		Random rnd = new Random();
		int num = rnd.nextInt();//int 범위
		System.out.println(num);
		
		//경우의 수, 시작값(최솟값)
		num = rnd.nextInt(10)+1;//(0~9) +1
		System.out.println(num);
		
		//101~200 : 경우의수 100, 시작값 101
		num = rnd.nextInt(100)+101;
		System.out.println(num);
		
		//0.0(포함) ~1.0(미포함)의 난수
		double dnum = rnd.nextDouble();
		System.out.println(dnum);
		
		boolean bool = rnd.nextBoolean();
		System.out.println(bool);
	}
	/**
	 * Math.random() = java.lang 패키지 임포트없이 바로 사용가능
	 * 
	 * 0.0(포함) ~ 1.0(미포함) 실수를 하나 리턴
	 */
	public void test2() {
		double num = Math.random();
		System.out.println(num);
		
		//정수형 난수
		//Math.random() * 경우의수 + 최소값
		int i = (int)(num*10)+1;
		System.out.println(i);
	}
	
	public void test3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("동전 앞/뒤를 입력 (1.앞 2.뒤) > ");
		int user = sc.nextInt();
//		int coin = (int)(Math.random()*2) +1;
		Random rnd = new Random();
		int coin = rnd.nextBoolean() ? 1 : 2;
		
		
		String result = user == coin ? "정답" : "오답";
		System.out.println(result);
		System.out.println("user = " + user + ", coin = " +coin);
	}
	/**
	 * 올림 ceiling : double을 리턴
	 * 버림 floor : double을 리턴
	 * 반올림 round : long을 리턴
	 */
	public void test4() {
		
		//올림
		double num =1.1;
		double result = Math.ceil(num);
		System.out.println(result);
		
		//버림
		num = 1.9;
		result = Math.floor(num);
		System.out.println(result);
		
		//올림해서 소수점 첫번째 자리까지 표현(소수점 두번째에서 올림 하고싶은경우)
		num = 1.23;
		result = Math.ceil(num*10)/10;
		System.out.println(result);
		
		//반올림
		num = 3.75;
		long res = Math.round(num*10);
		System.out.println(res/10.0);
		
	}
	
}
