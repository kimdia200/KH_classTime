package kh.java.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Exception 예외처리를 통해서 정상복구될수 있는 오류
 * 
 * 1. checked exception : RuntimeException의 후손클래스가 아님. 예외처리 강제화. 2. unchecked
 * exception : RuntimeException의 후손클래스
 * 
 *
 */
public class ExceptionTest {

	public static void main(String[] args) throws IOException { //test5() 메서드에서 받은거 또 던지기
		ExceptionTest e = new ExceptionTest();
		e.test1();
		e.test2();
		e.test3();
		e.test4();
		e.test5();
		//test5에서 throws를 받았으니까 try catch를 하던가 또 throws
		System.out.println("----- 정상 종료 -----");
	}

	/**
	 * try절 catch절 여러개의 예외클래스를 처리 할 수 있도록 catch절을 여러개 작성 할 수 있다. 부모 예외클래스 타입의
	 * catch절로 통합해서 처리 할 수 도 있다.(다형성) 여러개의 예외클래스타입의 catch절을 사용하는 경우, 자식 - 부모타입순으로
	 * 작성한다 (if elseif else 같은 실행순서이기 떄문)
	 */
	public void test1() {

		// 예외처리
		try {
			// 예외가 발생할 수 있는 코드
			String s = null;
			System.out.println(s.length()); // java.lang.NullPointerException

			Scanner sc = new Scanner(System.in);
			System.out.print("정수 입력 : ");
			int num = sc.nextInt(); // java.util.InputMismatchException

			System.out.println(num / 0); // 수를 0으로 나눌수 없다.
			// java.lang.ArithmeticException: / by zero

			System.out.println("test1 메서드 정상종료");

//		} catch(InputMismatchException e) {
//			//해당예외가 발생했을 경우 처리코드
//			System.out.println("정확한 값을 입력해주세요.");
//			
//		} catch(ArithmeticException e) {
//			
//			System.out.println("수를 0으로 나눌수 없습니다.");
//			
//		} catch(NullPointerException e) {
//			
//			System.out.println("NPE 발생!");
//			
////			System.out.println(e);
//			e.printStackTrace(); //예외가 발생했을때의 콜스택의 상황 출력
//			
//		}
			// catch절에도 다형성이 적용된다.
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 처리순서
	 * 
	 * finally 절 : try절에서 예외가 던져지든 아니든 무조건 실행되는 코드 사용한 자원반납 등의 목적으로 사용합니다~! 정말
	 * 강력한점!!!! 조기 리턴을 해도 finally는 실행된다!
	 */
	public void test2() {
		System.out.println(1);
		try {
			System.out.println(2);
			if (true)
				return; // finally절은 실행된다~!
			int[] arr = new int[3];
			System.out.println(arr[3]);
			// java.lang.ArrayIndexOutOfBoundsException
			System.out.println(3);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(4);
		} finally {
			System.out.println(5);
		}
		System.out.println(6);
	}

	/**
	 * 사용자의 입력에 대해 예외 처리하기
	 */
	public void test3() {

//		Scanner sc = new Scanner(System.in);
//		스캐너가 바깥에 있으면 위험함 버퍼에 Scanner 값이 남아있기때문에! 계속 가져오고 에러나고 반복
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("정수 : ");
				System.out.println(sc.nextInt());
				
				//정상 입력한 경우
				break;
			} catch (Exception e) {
				System.out.println("올바른 정수를 입력하세요!");
//				sc.next();//스캐너가 밖에 있다면  사용자의 잘못된 입력값을 비워주는 것
			}
		}
	}
	
	/**
	 * 사용자로부터 정수 2개를 입력받고, 합을 출력하는 프로그램을 만드세요
	 * 부정입력이 있다면 , 처음부터 다시 입력 받으세요.
	 */
	public void test4() {
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("정수 두개를 입력하세요~! : ");
				int a = sc.nextInt();
				int b=  sc.nextInt();
				System.out.println("정수("+a+", "+b+")의 합 = "+(a+b));
				break;
			}catch(Exception e){
				System.out.println("올바른 정수입력이 아닙니다. 다시 시작하세요.");
			}
		}
	}
	/**
	 * CheckedException
	 * 예외처리 강제화!
	 * 1. try catch로 예외처리하던지
	 * 2. throws 절을 통해 회피하던지!
	 * 		throws절은 복수개의 여러개를 던지는게 가능하다 그런데 Exception으로 던지면 쉽다
	 * @throws FileNotFoundException 
	 * 
	 */
	public void test5() throws FileNotFoundException, IOException{ //2. throws로 던저보리기~
		//1. try~catch로 처리하기
//		try {
//			FileReader fr = new FileReader("test.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
			FileReader fr = new FileReader("test.txt");
			
	}
}
