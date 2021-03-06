package com.kh.java;

import com.kh.java.other.OtherJava;

/**
 * 
 * 실제 클래스명 : package + class

com.kh.java.HelloWorld

같은 패키지안에 동일한 이름의 클래스는 존재 할 수 없다.
다른 패키지안의 동일한 이름의 클래스는 존재 할 수 있다.

패키지란?
보통 성격이 같은 클래스의 모음
패키지는 겹치지 않도록 보통 도메인을 거꾸로 사용한다. 
com.kh.HelloWorld 이런식으로
패키지는 고유하게 관리하기 위해 3레벨 이상을 추천한다
com.naver.www 이런식
 *
 */




public class HelloWorld {

	/**
	 * 	프로그램의 첫 실행 메소드
	 * 객체 지향 언어 Object Oriented Language
	 * 객체란 메모리에 적재된 데이터 저장 공간
	 *
	 *
	 *	객체 레시피
	 *	클래스명 변수명 = new 클래스명();
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("Hello World~");
//		System.out.println("안녕");
		HelloWorld hw = new HelloWorld();
		hw.test1();
		hw.test2();
		
		KHJava khj = new KHJava();
		khj.welcome();
		
//		System.out.println("메인 메소드 출력!");
		
		OtherJava oj = new OtherJava();
		
		oj.callMe();
		
		//jdk 제공 클래스 사용하기
		java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
		System.out.println(today);
		
		
		
	}
	public void test1(){
		System.out.println("test1 호출");
	}
	public void test2(){
		System.out.println("test2 호출");
	}

}
