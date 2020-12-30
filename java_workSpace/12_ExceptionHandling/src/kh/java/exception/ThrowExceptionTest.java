package kh.java.exception;

import java.util.Scanner;

public class ThrowExceptionTest {

	public static void main(String[] args) {
		ThrowExceptionTest t = new ThrowExceptionTest();
		t.test2();
	}
	/**
	 * 성인인증해야 가능한 게임
	 * 1. 성인인증 기능
	 * 2. 성인이 확인된 경우만 게임 시작하고 나머지는 돌려보내!
	 */
	public void test1() {
		boolean isAdult = checkAge();
		
		if(!isAdult){
			System.out.println("성인이 아닙니다. 게임을 종료합니다.");
			return;
		}
		
		System.out.println("게임이 진행되요~");
	}
	
	public boolean checkAge() {
		Scanner sc = new Scanner(System.in);
		System.out.print("나이를 입력하세요 : ");
		if(sc.nextInt()>=19) 
			return true;
		else
			return false;
	}
	/**
	 * 예외를 통한 프로그램 흐름 분기
	 */
	public void test2() {
		try {
		checkAgeAndThrowException();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("게임이 진행되요~");
	}
	/**
	 * 나이를 입력받고,
	 * 미성년자인ㅇ 경우에는 예외를 던지는 메서드
	 */
	public void checkAgeAndThrowException() throws UnderAgeException{
											//UnderAgeException은 Exception상속이라
											//그냥 Exception해줘도 되지만 명확하게 의미부여				
		Scanner sc = new Scanner(System.in);
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		
		if(age<20)
//			throw new Exception("미성년자 : " + age);
//			throw new RuntimeException("미성년자 : " + age); 이거하면 throws안해도됨
			//checked, unchecked 차이
			throw new UnderAgeException(String.valueOf(age)); //이렇게 커스텀 Exception을 만들어서 던져도됨
	}
}
