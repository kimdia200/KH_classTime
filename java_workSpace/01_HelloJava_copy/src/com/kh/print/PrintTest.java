package com.kh.print;

public class PrintTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PrintTest p = new PrintTest();
//		p.test1();
//		p.test2();
		p.test3();
	}
	
	/**
	 * escape 문자
	 * (\ + 특정문자)
	 * 
	 * \n 개행문자(윈도우 \r\n)
	 * \t 탭과 같은효과
	 * \\ 역슬래시를 출력할때
	 * \" 쌍따옴표 출력
	 * 
	 */
	
	public void test1() {
		System.out.println("안녕");
		System.out.print("하\n");
		System.out.print("하");
		System.out.print("하");
		
		//그는 말했다. "춥다....."
		System.out.println("그는말했다. \"춥다.......\"");
		//윈도우 디렉토리 구분자는 역슬래시를 사용한다
		System.out.println("C:\\dev\\eclipse\\eclipse.exe");
		System.out.println("C:/dev/eclipse/eclipse.exe");
		
		System.out.println("		\"ShakeIt 알람\"\n--------------------------------------------\n	흔들기	쏴리질러	터치하기	원터치\n--------------------------------------------");
		System.out.println("홍길동 \n\t\"신사임당\"\n\t\t세종대왕\\\"\\\'\\/");
	}
	
	public void test2() {
		
		System.out.println(
				"\t\"Shake It 알람\"\n"
				+"--------------------------------------------\n"
				+"\t흔들기\t쏴리질러\t터치하기\t원터치\n"
				+"--------------------------------------------");
		System.out.println(
				"홍길동 \n"+
				"\t\"신사임당\"\n"+
				"\t\t세종대왕\\\"\\\'\\/");
	}
	
	/**
	 * 문자열 더하기연산
	 * 1. 문자열 + 문자열 = 문자열
	 * 2. 문자열 + 숫자 = 문자열
	 * 3. 숫자 + 숫자 = 숫자
	 */
	public void test3() {
		
		System.out.println("가나다" + "라마바사");
		System.out.println("abc" + 123);
		System.out.println(123+123);
		System.out.println(12+34);
		
		System.out.println(12+"34"+56);
		System.out.println(12+34+"56");
		System.out.println("12"+3*7);
		
	}

}
