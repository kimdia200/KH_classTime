package kh.java.loop;

import java.util.Scanner;

/**
 * 초기식
 * whle(조건식){
 * 	반복실행구문
 * 	증감식
 * }
 * @author family
 *
 */
public class WhileLoopTest {

	public static void main(String[] args) {
		
		new WhileLoopTest().test8();
		
	}
	
	public void test() {
		int i = 0;
		while (i < 10 ) {
			i++;
			System.out.println("Hello World " +i);
		}
	}
	
	public void test2() {
		
		//1 2 3 4 5 6 7 8 9 10
		int i=0;
		while(i<10) {
			i++;
			System.out.print(i+" ");
		}
		System.out.println();
		
		//1 3 5 7 9 11 13 15 17 19 
		i=1;
		while(i<20) {
			System.out.print(i+" ");
			i+=2;
		}
		System.out.println();
		
		//2 4 6 8 10 12 14 16 18 20 
		i=0;
		while(i<20) {
			i+=2;
			System.out.print(i+" ");
		}
		System.out.println();
		
		//10 9 8 7 6 5 4 3 2 1 
		i=10;
		while(i>0) {
			System.out.print(i+" ");
			i--;
		}
		System.out.println();
		
		//20 18 16 14 12 10 8 6 4 2 
		i=20;
		while(i>0) {
			System.out.print(i + " ");
			i-=2;
		}
		System.out.println();
	}
	
	public void testtt() {
		//자리를 비워둘수있음, 그럴때에도 세공간의 구분은 해야함
	
		for (;;) {
			break;
		}
		
		//위의 무한반복 for문하고 똑같음
		while(true) {
			break;
		}
		
	}
	
	//구구단 8단 출력
	public void test3() {
		int dan = 8;
		int i=1;
		
		System.out.println("구구단 "+dan+"단 출력!");
		while(i<=9) {
			System.out.println(dan + " * "+i+ " = " + (dan*i++));
		}
	}
	
	/**
	 * 실습문제 : 사용자에게 양수하나를 입력받고
	 * 1부터 입력한 수 사이의 3의 수 개수를 출력하세요;
	 */
	
	public void test4() {
		Scanner sc = new Scanner(System.in);
		System.out.print("양수 하나를 입력하세요 : ");
		int temp = sc.nextInt();
		if(!(temp>=0)) {
			System.out.println("양수입력이 아님");
			return;
		}
		
		int i =1;
		int count=0;
		
		while(i<=temp) {
			if(i%3==0)
				count++;
			i++;
		}
		System.out.println("3의 배수 개수는 : "+count+ " 개");
		
	}
	/**
	 * do while문의 최고장점은
	 * 최소 한번은 실행할수있는것
	 */
	public void test5() {
		Scanner sc = new Scanner(System.in);
		char yn = ' ';
		do {
			System.out.println("게임시작");
			//게임중
			System.out.println("게임종료");
			
			System.out.print("한판더 하시겠습니까? (y/n) : ");
			yn=sc.nextLine().charAt(0);
		}while(yn=='y');
		System.out.println("게임종료");
	}
	
	public void test6() {
		String menu = "===============\n"
				+"1.참치김밥 --- 2500원\n"
				+"2.멸치김밥 --- 3500원\n"
				+"3.김치김밥 --- 2000원\n"
				+"=================\n"
				+"선택 : ";
		
		char yn = ' ';
		int sum = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.print(menu);
			int num = sc.nextInt();
			System.out.println(num + "을 선택 하셨습니다");
			
			//금액 누적하기
			switch(num) {
			case 1: sum+=2500; break;
			case 2: sum+=3500; break;
			case 3: sum+=2000; break;
			default : System.out.println("잘못입력하셨습니다.");
			}
			
			System.out.print("추가 주문 하시겠습니까?(y/n) : ");
			yn = sc.next().charAt(0);
		}while(yn=='y');
		System.out.println("결제할 금액은 ["+sum+"]원 입니다.");
		System.out.println("주문완료!!!");
	}
	/**
	 * 사용자에게 2~9사이의 단수를 입력받고
	 * 해당 단수의 구구단을 출력하세요
	 * 계속 할지 여부를 물어보고 반복실행합니다.
	 * 단, 2~9사이의 수가 아니라면 잘못입렸했다고 말해주기
	 */
	public void test7() {
		Scanner sc = new Scanner(System.in);
		int dan,i;
		char ch= ' ';
		do {
			
			//몇단을 외울지 입력받음
			//잘못입력시 반복해서 물어봄
			do{
				System.out.print("몇단을 외울까요? : ");
				dan = sc.nextInt();
				if(dan>=2 && dan<=9)
					break;
				System.out.println("잘못 입력하셨습니다. 다시입력해주세요.");
			}while(true);
			
			//입력받은단수로 구구단 출력하는 부분
			i=1;
			do {
				System.out.println(dan+" * "+i +" = " + (dan*i));
				i++;
			}while(i<10);
			
			//계속할지 물어봄
			System.out.print("계속 하시겠습니까?!(y/n) : ");
			ch = sc.next().charAt(0);
		}while(ch=='y');
		
		System.out.println("종료되었습니다!");
	}
	
	/**
	 * 강사님꺼 7번
	 * 
	 */
	public void test7_1() {
		Scanner sc = new Scanner(System.in);
		char yn = 'y';
		do {
			//1.단수 입력
			System.out.print("구구단 몇단을 출력할까요?(2~9) : ");
			int dan = sc.nextInt();
			//2.유효성검사
			if(dan >= 2 && dan <= 9) {
				//3.구구단 출력
				int i = 1;
				while(i < 10) {
					System.out.println(dan + " * " + i + " = " + (dan * i));
					i++;
				}
				//4.계속실행여부 입력
				System.out.print("계속 콜?(y/n) : ");
				yn = sc.next().charAt(0);
			}
			else {
				System.out.println("잘못 입력하셨습니다.");
			}
		} while(yn == 'y');
		System.out.println("프로그램 종료!");
	}
	
	/**
	 * 메뉴 관리
	 */
	public void test8() {
		Scanner sc = new Scanner(System.in);
		String mainMenu = "==========\n"
						+ "1.저장\n"
						+ "2.조회\n"
						+ "3.수정\n"
						+ "4.삭제\n"
						+ "0.프로그램 종료\n"
						+ "==========\n"
						+ "선택 : ";
		int choice = 0;
		
		do {
			System.out.print(mainMenu);
			choice = sc.nextInt();
			
			switch(choice) {
			case 1 : save(); break;
			case 2 : selectList(); break;
			case 3 : update(); break;
			case 4 : delete(); break;
			case 0 : 
				System.out.println("프로그램을 종료합니다."); 
				return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		} while(choice != 0);
	}
	
	public void save() {System.out.println("save를 선택하셨습니다.");}
	public void selectList() { System.out.println("selectList를 선택하셨습니다.");}
	public void delete() {System.out.println("delete를 선택하셨습니다.");}
	public void update() {
		System.out.println("update를 선택하셨습니다.");
		Scanner sc = new Scanner(System.in);
		String updateMenu = "==========\n"
						+ "1.이름수정\n"
						+ "2.전화번호수정\n"
						+ "3.주소수정\n"
						+ "0.메인메뉴로 나가기\n"
						+ "==========\n"
						+ "선택 : ";
		int choice = 0;
		
		do {
			System.out.print(updateMenu);
			choice = sc.nextInt();
			
			switch(choice) {
			case 1 : System.out.println("이름을 수정하겠습니다."); break;
			case 2 : System.out.println("전화번호를 수정하겠습니다."); break;
			case 3 : System.out.println("주소를 수정하겠습니다."); break;
			case 0 : 
				System.out.println("메인 메뉴로 돌아갑니다"); 
				return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		} while(choice != 0);
	}
}
