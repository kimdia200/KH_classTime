package kh.java.type;

/**
 * 
 * 컴퓨터 작동원리
 * 1. 같은 자료형 끼리만 연산 할 수 있다.
 * 2. 연산 결과 역시 동일한 자료형이다.
 * 3. 같은 자료형의 변수에만 대입 할 수 있다.
 * 
 *위의 조건을 만족하기 위해서 암묵적/명시적 형변환이 필요하다.
 *
 */


public class CastingTest {

	public static void main(String[] args) {

		CastingTest c = new CastingTest();
//		c.test1();
//		c.test2();
//		c.test3();
		c.test4();
		
	}
	
	
	/**
	 * 자동 형변환
	 * 
	 * 	- 크기가 작은 타입에서 큰 타입으로 변환은 자동으로 처리
	 * 	- 정수에서 실수로의 변환은 자동처리 가능 
	 * 	- byte(1바이트) -> short(2바이트) -> int(4바이트) -> long(8바이트) - > float(4바이트) -> double(8바이트)
	 */
	public void test1() {
		
		int num = 300;
		long lNum = num;
		System.out.println("lNum = " + lNum);
		
		int a = 3;
		double b = 1.5;
		System.out.println(a+b); //int(3) + double(1.5) 가 아닌
								//double(3.0) + double(1.5)로 처리되며 결과값도 double
		
		int c = '0'; // char 은 int 로 변경가능(아스키 코드)
		System.out.println(c);
		
		System.out.println('c'+1);
	}

	
	/**
	 * 명시적 형변환
	 * 1. 크기가 작은 타입으로 형변환 - 데이터 손실
	 * 2. 크기가 큰 타입으로 형변환 시에도 특이경우(정수, 실수)
	 */
	public void test2() {
		//1. 데이터 손실이 있는 경우
		int num = (int)3.7;
		System.out.println(num);
		
		
		int a = 10;
		int b = 4;
		System.out.println(a/b);
		System.out.println((double)a/b);
		
		System.out.println(a*1.0/b); //*1.0 하면서 (double) 명시적 형변환과 같은효과를 낸다
		
		//2147483847 + 1
		
		int i = Integer.MAX_VALUE;
		System.out.println((long)i+1);
		System.out.println(i+1L);
		
	}
	
	
	/**
	 * 
	 * 형변환 유의사항
	 * 	- byte, short, char 타입들은 연산시 자동으로 int(정수 기본형)으로 변환되어 처리됨
	 * 
	 */
	public void test3() {
		
		byte b1 = 10;
		byte b2 = 20;
		byte result = (byte)(b1+b2);  //byte 끼리 연산시에도 int로 변환되어 계산되기 때문에 결과값도 int 따라서 byte로 다시 캐스팅
		System.out.println(result);
		
		boolean bool = 'a' >(b1+b2);
		//				char > (byte + byte)
		//				char > (int + int ) byte끼리 연산시 int 로 변환되어 연산됨
		//				char > int 결과값도 int
		//				int(97) > int(30) char이 int 값으로 변경되면서 a의 int값 97로 변경(아스키코드)
		System.out.println(bool);
		
		//char 변수에 int 대입
		char ch = 65;
		System.out.println(ch);
		
		ch = '\ud734';
		System.out.println(ch);
		
	}
	
	/**
	 * 
	 * java.lang.String 문자열
	 * 	- 참조형 (객체 만들어서 사용)
	 * 	- 기본형처럼 사용가능 (리터럴을 대입하는 형태)
	 * 
	 */
	public void test4() {
		
		String name = "홍길동";
		String name2 = new String ("홍길동");
		
		//더하기 연산
		System.out.println(name + " 만세!");
		
		//char
		System.out.println(name + 'a');
		System.out.println(123+'a');
		System.out.println("abc"+ 'z'+123);
		System.out.println(123+'z'+"abc");
	}
}
