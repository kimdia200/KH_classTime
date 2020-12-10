package kh.java.condition;

import java.util.Scanner;

/**
 * byte short char int String 열거형으로 
 * 처리되는 변수 또는 계산식
 * (boolean float double long은 올수 없다.)
 * 
 * switch(변수 또는 계산식){
 * 	case 값1 : 처리구문1; break;
 * 	case 값2 : 처리구문2; break;
 * 	...
 * 
 * 	default : 기본 처리구문;
 * }
 * 
 * 계산식의 값이 일치하는 해당 case문이 실행되고, 
 * break문을 만나면 switch문을 빠져나온다.
 * 
 * 
 */
public class SwitchConditionTest {

	public static void main(String[] args) {
		SwitchConditionTest s = new SwitchConditionTest();
//		s.test1();
//		s.test2();
//		s.test3();
//		s.test4();
		s.test5();
	}

	public void test1() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("어떤 과일을 좋아하세요?\n "
					   + "1.사과  2.바나나 3.오렌지 > ");
		
		int num = sc.nextInt();
		
		switch(num) {
			case 1 : System.out.println("사과는 빨개!"); break;
			case 2 : System.out.println("바나나는 맛있어!"); break;
			case 3 : System.out.println("오렌지는 셔!"); break;
			default : System.out.println("잘못 입력하셨습니다.");
		}
		
		//범위처리는 switch문 사용이 불편하다.
		int mark = 100;
		char grade = ' ';
		switch(mark) {
		case 100 : grade = 'A'; break;
		case 99 : grade = 'A'; break;
		case 98 : grade = 'A'; break;
		case 97 : grade = 'A'; break;
		case 96 : grade = 'A'; break;
		case 95 : grade = 'A'; break;
		}
		
	}
	
	public void test2() {
		Scanner sc = new Scanner(System.in);
		String menu = "================\n"
					+ "\t오늘의 메뉴\n"
					+ "----------------\n"
					+ "1.순대국\n"
					+ "2.미역국\n"
					+ "3.김치찌게\n"
					+ "-----------------\n"
					+ "선택 : ";
		
		System.out.println(menu);
		int num = sc.nextInt();
		
		int price = 0;
		
		switch(num) {
		case 1 :
			System.out.println("case 1 실행!");
			price = 7000; 
			break;
		case 2 :
			System.out.println("case 2 실행!");
			price = 8000;
			break;
		case 3 :
			System.out.println("case 3 실행!");
			price = 6000;
			break;
		//잘못입력했을때 밑에 감사합니다가 뜨게 하지 않게 하려면 return
		default : 
			System.out.println("잘못 입력하셨습니다.");
			return; //조기리턴 : 이하 코드는 실행하지 않고, 메소드 호출부로 돌아간다
		}
		
		System.out.println(price + "원을 결제해 주세요, 감사합니다!");
		
	}
	/**
	 * switch fall-through
	 */
	public void test3() {
		//범위처리는 switch문 사용이 불편하다.
		//break 나올때까지 쭉 돌아감
		int mark = 100;
		char grade = ' ';
		switch(mark) {
		case 100 : 
		case 99 : 
		case 98 :
		case 97 : 
		case 96 : 
		case 95 : 
		case 94 : 
		case 93 : 
		case 92 : 
		case 91 :
		case 90 : grade = 'A'; break;
		case 89 : 
		case 88 : 
		case 87 : 
		case 86 :
		case 85 : 
		case 84 : 
		case 83 :
		case 82 :
		case 81 : 
		case 80 : grade = 'B'; break;
		}
		
	}
	public void test4() {
		System.out.print("점수 입력 : ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		char grade = ' ';
		
		switch(n / 10) {
		case 10 :
		case 9 : grade = 'A'; break;
		case 8 : grade = 'B'; break;
		case 7 : grade = 'C'; break;
		case 6 : grade = 'D'; break;
		default : grade = 'F'; break;
		}
		System.out.println("학점  : " + grade);
	}
	/**
	 * @실습문제 : 메뉴를 출력하고 사용자 선택값을 리턴하는 
	 * menu메소드를 생성하세요
	 * 선택값이 메뉴에 없다면 (-1)을 리턴하세요
	 */
	public void test5() {
		int choice = menu();
		switch(choice) {
		case 1 : System.out.println("순대국 = 7000원");break;
		case 2 : System.out.println("미역국 = 8000원");break;
		case 3 : System.out.println("김치찌개 = 6000원");break;
		default : System.out.println("선택한 메뉴가 없습니다.");
		}
	}
	public int menu() {
		int choice;
		Scanner sc = new Scanner(System.in);
		String menu = "================\n"
				+ "\t오늘의 메뉴\n"
				+ "----------------\n"
				+ "1.순대국\n"
				+ "2.미역국\n"
				+ "3.김치찌게\n"
				+ "-----------------\n"
				+ "선택 : ";
		
		System.out.print(menu);
		choice = sc.nextInt();
		
		switch(choice) {
		case 1 : return choice;
		case 2 : return choice;
		case 3 : return choice;
		default : return (-1);
		}
	}
	
	
}
