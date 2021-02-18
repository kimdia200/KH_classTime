package product.view;

import java.util.Scanner;

import product.controller.ProductController;

public class IOMenu {
	private Scanner sc = new Scanner(System.in);
	private ProductController controller = new ProductController();
	
	public void menu() {
		String menu = "***** 상품입출고메뉴*****\r\n" + 
				"1. 전체입출고내역조회\r\n" + 
				"2. 상품입고\r\n" + 
				"3. 상품출고\r\n" + 
				"9. 메인메뉴로 돌아가기\n"
				+ "입력 : ";
		
		do {
			String choice;
			System.out.print(menu);
			choice = sc.nextLine();
			
			switch(choice) {
			//전체 입출고 내역 조회
			case "1" : 
				break;
			//상품 입고
			case "2" : 
				break;
			//상품 출고 
			case "3" : 
				break;
			//메인메뉴로 돌아가기
			case "9" : 
				System.out.print("메인메뉴로 돌아가시겠습니까? (y/n) : ");
				char choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 == 'Y')
					return;
				break;
			default : System.out.println("잘 못 입력 하셨습니다.");
			}
			
		}while(true);
	}
}
