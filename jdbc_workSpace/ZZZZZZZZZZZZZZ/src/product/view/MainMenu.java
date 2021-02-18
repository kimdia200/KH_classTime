package product.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Stock;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private ProductController controller = new ProductController();
	
	public void Menu() {
		String choice;
		List<Stock> list = null;
		Stock p = null;
		String id;
		String menu = "***** 상품재고관리프로그램 *****\n" + 
				"1. 전체상품조회\n" + 
				"2. 상품아이디검색\n" + 
				"3. 상품명검색\n" + 
				"4. 상품추가\n" + 
				"5. 상품정보변경\n" + 
				"6. 상품삭제\n" + 
				"7. 상품입/출고 메뉴\n" + 
				"9. 프로그램종료\n"
				+ "입력 : ";
		do {
			System.out.print(menu);
			choice = sc.nextLine();
			switch(choice) {
			//전체상품조회
			case "1" : 
				list = controller.selectAll();
				display(list);
				break;
			//상품아이디 검색
			case "2" : 
				System.out.println("==아이디 조회===");
				System.out.print("조회 할 product_id : ");
				id = sc.nextLine();
				p = controller.selectID(id);
				display(p);
				break;
			//상품명 검색
			case "3" : break;
			//상품 추가
			case "4" : break;
			//상품정보변경
			case "5" : break;
			//상품 삭제 (조건1)
			case "6" : break;
			//상품 입출고 메뉴 (조건2)
			case "7" : 
				new IOMenu().menu();
				break;
			//프로그램 종료
			case "9" : 
				System.out.print("정말 종료 하시겠습니까? (y/n) : ");
				char choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 == 'Y')
					return;
				break;
			default : System.out.println("잘 못 입력하셨습니다.");
			}
		}while(true);
	}

	private void display(Stock p) {
		if(p==null) {
			System.out.println("조회 실패");
			return;
		}
		System.out.println(p);
	}


	private void display(List<Stock> list) {
		if(list==null) {
			System.out.println("전체 상품 조회 실패");
			return;
		}
		//List출력 방법 3개 for, forEach, Iterator
		Iterator<Stock> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void displayErr(String message) {
		System.err.println(message);
	}
	
}
