package kh.java.args;

import java.util.Scanner;

/**
 * 프로그램 시작과 동시에 사용자 입력값 받아 처리 가능하다.
 * 사용자 입력값을 " "(공백)을 구분자로 끊어서  String[]로 만들어
 * main method에 전달함
 * 
 * main의 String[] args 파라미터 입력법
 * run - run configurations - 클래서 확인 - arguments 탭 확인
 * 직접 입력해줘도 되고 Variable - String prompt 를 사용해줘도 좋다
 * 
 * ex )        abc def 123
 * 					=====> {"abc", "def", "123"}
 * @author family
 *
 */
public class CommandLineArgumentsTest {

	public static void main(String[] args) {
		
		
//		for(int i=0; i<args.length; i++) {
//			System.out.println(args[i]);
//		}
		CommandLineArgumentsTest c = new CommandLineArgumentsTest();
//		c.test1(args);
//		c.test2(1);
//		c.test2(1,2);
//		c.test2(1,2,3);
//		c.test2(1,2,3,4);
		c.test3();
		
		System.out.println("프로그램 종료!");
	}

	/**
	 * "1 2 3 4" => "1", "2", "3", "4"
	 * @param args
	 */
	public void test1(String[] args) {
		int sum=0;
		for(int i=0; i<args.length; i++) {
			sum+= Integer.parseInt(args[i]);
		}
		System.out.println(sum);
	}
	
	
	
	/**
	 * 가변인자
	 * 타입이 동일(중요)하고, 개수가 정해지지 않은 매개 인자를 처리 할 수 있다.
	 * 
	 * @param arr
	 */
	public void test2(int... arr) {
		int sum=0;
		for(int i=0; i<arr.length; i++) {
			sum+=arr[i];
		}
		System.out.println(sum);
	}
	
	/**
	 * 사용자에게 정수를 입력받고, 입력한 정수에 따라 다음과 같이 처리
	 * 정수 : 5
	 * 
	 * 0행 길이 5
	 * 1행 길이 4
	 * 2행 길이 3
	 * 3행 길이 2
	 * 4행 길이 1
	 * 
	 * 1 2 3 4 5
	 * 6 7 8 9
	 * 10 11 12
	 * 13 14
	 * 15
	 */
	public void test3() {
		int len;
		int count=1;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 : ");
		len=sc.nextInt();
		
		//2차원 배열 선언, 입력받은 정수에 따라 배열 크기 할당
		int[][] arr = new int[len][];
		
		
		for(int i=0; i<arr.length; i++) {
			//파악된 규칙에 따라 배열의 나머지 크기 할당
			arr[i] = new int[len-i];
			for(int j=0; j<arr[i].length; j++) {
				//2차원배열의 값 대입
				arr[i][j]=count++;
				//2차원배열의 값 출력
				System.out.print(arr[i][j]);
				System.out.print(j!=arr[i].length-1 ? ", " : " ");
			}
			//열이 바뀔때 줄바꿈
			System.out.println();
		}
	}
}
