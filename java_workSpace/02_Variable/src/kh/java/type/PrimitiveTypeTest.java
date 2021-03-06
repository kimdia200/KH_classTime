package kh.java.type;


/**
 * 
 * 자료형 Data Type
 * 1. 기본형 Primitive Type
 * 	- 값(Literal)을 저장
 * 	- 8사지(문자형char, 논리형boolean, 실수형byte short int long, 정수형 float double)
 * 
 * 2. 참조형 Reference Type
 * 	- 주소값을 저장(4Byte)
 * 	- 기본형을 제외한 모든 타입
 * 		String, System, Date, Calendar
 * 		모든 클래스는 자료형이다.
 * 
 * bit
 * byte
 * kilo byte
 * mega byte
 * tera byte
 * peta byte
 * exa byte
 * zetta byte
 * yotta byte
 *
 */
public class PrimitiveTypeTest {

	public static void main(String[] args) {

		PrimitiveTypeTest p = new PrimitiveTypeTest();
		
//		p.test1();
//		p.test2();
//		p.test3();
		p.test4();
		
	}
	
	/**
	 * 기본형
	 * 1. 논리형
	 * 		boolean(1byte) : true | false 
	 * 2. 문자형
	 * 		char(2byte) : 문자열 하나 'a' 65536가지 문자열 표현가능(0~65535)
	 * 3. 정수형
	 * 		byte(1byte) -128 ~ 127 (256)
	 * 		short(2byte) -32768 ~ 32767 (65536)
	 * 		int(4byte) -21억 ~ 21억 (42억개)
	 * 		long(8byte) -922경 ~ 922경 ( 1800경)
	 * 4. 실수형
	 * 		float(4byte) 소수점 이하 7자리
	 * 		double(8byte) 소수점 이하 16자리
	 */
	
	public void test1() {
		
		//1. 변수 선언
		boolean bool;
		
		char ch;
		
		byte b;
		short sh;
		int i;
		long l;
		
		float f;
		double d;
		
		
		//2. 값대입
		bool = true;
		bool = false;
		bool = (5 > 2); // > < >= <= != ==
		bool = !bool; //윗줄까진 true 였으나 !bool 을 함으로써 false 로 변경
		
		ch = 'a';
		
		b = 120;
//		b = 130; //Type mismatch : cannot convert from int
		sh = 32000;
		i = 1000000000;
		l = 1234567894561231311L; //Long 형 변수 값을 넣어줄때는 L을 붙여서 Long타입임을 알려줘야함
		
		f= 0.1234567890f; // Float 형 변수 값을 넣어줄때는 F를 붙여서 Float타입임을 알려줘야함
		d= 0.12345678901234567890;
		
		//3. 변수 사용(출력)
		System.out.println("bool = " + bool);
		System.out.println();
		
		System.out.println("ch = " + ch);
		System.out.println();
		
		System.out.println("b = " + b);
		System.out.println("sh = " + sh);
		System.out.println("i = " + i);
		System.out.println("l = " + l);
		System.out.println();
		
		System.out.println("f = " + f);
		System.out.println("d = " + d);
		
		
	}
	
	/**
	 * 초기화
	 * 변수 선언과 값대입을 한줄에서 처리
	 */
	
	public void test2() {
		
		int num = 123;
		System.out.println(num);
		
		//변수는 값대입 없이 사용 할 수 없다.
		
//		double d;
//		d = d + 1.23; 변수는 값대입 없이 사용 할 수 없다.
//		System.out.println(d);
		
		double d;
		d=0.1;
		d = d + 1.23;
		System.out.println(d);
		
		//같은 타입인 경우, 한줄에 여러개 변수 선언, 초기화가 가능하다.
		int a, b;
		a = 1;
		b = 2;
		
		int c = 3, e = 4;
		
		//정수연산
		int i = 10;
		int j = 3;
		
		System.out.println("i + j = " + (i+j));
		System.out.println("i - j = " + (i-j));
		System.out.println("i * j = " +(i*j));
		System.out.println("i / j = " + (i/j));
		System.out.println("i % j = " + (i%j));
		
	}
	
	/**
	 * 상수
	 * 한번 값 대입 후에 값을 더 이상 변경 할 수 없는 변수
	 * final
	 */
	
	public void test3() {
		
		final int AGE = 20;
//		age = 30;  //이런식으로 final 로 선언된 변수는 값의 변경이 불가능하다
		
		System.out.println("AGE = "+AGE);
		
		int myAge = AGE + 1;
		System.out.println("myAge = "+myAge);
		
		//JDK에서 
		System.out.println(10*10*Math.PI);
		System.out.println(Byte.MIN_VALUE + " ~ " + +Byte.MAX_VALUE);
		System.out.println(Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE + " ~ "+ Integer.MAX_VALUE);
		System.out.println(Long.MIN_VALUE + " ~ "+ Long.MAX_VALUE);
		
		
		
	}
	
	public void test4() {
		
		int i = Integer.MAX_VALUE;
		System.out.println(i);
		
		i = i+1;
		System.out.println(i);
		
		
		
	}
	
	

}
