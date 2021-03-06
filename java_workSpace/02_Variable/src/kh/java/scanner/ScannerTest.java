package kh.java.scanner;

import java.util.Scanner;

/**
 * 
 * Scanner
 * 1.next계열
 * 	- next() : String 값 읽음
 * 	- nextInt() : int 값 읽음
 * 	- nextDouble() : double 값 읽음
 * 	- nextBoolean() : boolean 값 읽음
 * 
 * 2.nextLine계열
 * 	- nextLine() : String 값 읽음 한줄
 *
 */
public class ScannerTest {

	public static void main(String[] args) {
		ScannerTest s = new ScannerTest();
//		s.test1();
//		s.test2();
		s.test3();
	}
	
	
	/**
	 * next계열
	 * 	- 입력버퍼에서 공백이나 개행문자 전까지 읽어서 반환한다.
	 * 	- 사용자입력값 이전의 공백/개행문자는 무시한다.
	 */
	public void test1() {
		//System.out 표준출력(콘솔)
		//System.in 표준입력(키보드)
		Scanner sc = new Scanner(System.in);
		
		//String 입력연습
//		System.out.print("이름 : ");
//		String name = sc.next();
//		System.out.println("name = " + name);
		
		//int 입력연습
//		System.out.print("정수 : ");
//		int num = sc.nextInt();
//		System.out.println("num = " +num);
		
		//double 입력연습
//		System.out.print("실수 : ");
//		double dNum = sc.nextDouble();
//		System.out.println("dnum = " + dNum);
	
//		System.out.print("참 / 거짓 (true/false) : ");
//		boolean bool = sc.nextBoolean();
//		System.out.println("bool = " + bool);
		
//		System.out.print(" 딱 한글자만 입력 : ");
//		char ch = sc.next().charAt(0);
//		System.out.println("ch  = " + ch);
		
//		System.out.print("주소를 입력하세요 : ");
//		String addr = sc.nextLine();
//		System.out.println("[addr = "+addr + "]");
		
		
		
	}
	
	/**
	 * nextLine 계열
	 * 	- 입력버퍼에서 개행문자까지 읽어온후, 개행문자를 제외하고 반환한다
	 * 
	 * next 계열이 쓰인뒤 바로 nextLine계열이 쓰이면 next계열은 개행문자 전까지 읽기때문에 남은 개행문자(\n)를 읽어와서 
	 * 오류가 발생하므로 nextLine()을 할번 사이에 그냥 넣어주면 잘 작동함
	 */
	
	public void test2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.println("name = " + name);
		
		//개행문자 날리기용
		sc.nextLine();
		
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		System.out.println("name = " + addr);
	}
	
	/**
	 * 사용자로부터 이름, 나이, 주소, 키(175cm), 몸무게(70.3kg)를 입력받고 출력하세요
	 * 
	 */
	public void test3() {
		Scanner sc = new Scanner(System.in);
		String name;
		int age;
		String addr;
		int height;
		double weight;
		
		System.out.print("이름 : ");
		name = sc.nextLine();
		
		System.out.print("나이 : ");
		age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("주소 : ");
		addr = sc.nextLine();
		
		System.out.print("키 : ");
		height = sc.nextInt();
		sc.nextLine();
		
		System.out.print("몸무게(실수형) : ");
		weight = sc.nextDouble();
		sc.nextLine();
		System.out.println();
		
		System.out.println(
				"이름 : " + name
				+"\n나이 : " + age
				+"\n주소 : " + addr
				+"\n키 : " + height
				+"\n몸무게 : "+ weight);
		
		
	}
}
