package product.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.exception.ProductException;
import product.model.vo.Product_io;
import product.model.vo.Product_stock;

public class Menu {
	Scanner sc = new Scanner(System.in);
	ProductController controller = new ProductController();
	
	public void mainMenu() {
		String menu = "***** 상품재고관리프로그램 *****\r\n" + 
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
			List<Product_stock> list1;
			Product_stock pStock;
			String productId;
			int result;
			System.out.print(menu);
			
			String choice = sc.nextLine();
			char choice2;
			
			switch(choice) {
			//전체상품 조회
			case "1" : 
				System.out.println("===전체 상품 조회===");
				list1 = controller.selectAllStock();
				display(list1);
				break;
			//상품 아이디 검색
			case "2" : 
				System.out.println("===상품 아이디 검색===");
				System.out.print("상품 아이디 : ");
				productId = sc.nextLine();
				pStock = controller.selectIdStock(productId);
				display(pStock);
				break;
			//상품명 검색
			case "3" : 
				System.out.println("===상품명 검색===");
				System.out.print("상품명 : ");
				String productName = sc.nextLine();
				list1 = controller.selectNameStock(productName);
				display(list1);
				break;
			//상품 추가
			case "4" : 
				System.out.println("===상품 추가===");
				pStock = inputStock();
				result = controller.insertProduct(pStock);
				if(result>0) 
					System.out.println("상품 추가 성공!!!");
				else
					System.out.println("상품 추가 실패...");
				break;
			//상품 정보 변경 메뉴
			case "5" : 
				System.out.println("===상품 정보 변경===");
				System.out.print("정보 변경 할 상품 번호 : ");
				productId = sc.nextLine();
				pStock = controller.selectIdStock(productId);
				display(pStock);
				if(pStock==null) {
					System.out.println("조회 된 상품 없음.");
					break;
				}
				modifyStock(pStock);
				break;
			//상품 삭제(조건1)
			case "6" : 
				System.out.println("===상품 삭제===");
				System.out.print("정보 변경 할 상품 번호 : ");
				productId = sc.nextLine();
				pStock = controller.selectIdStock(productId);
				display(pStock);
				System.out.print("삭제할 상품이 위의 상품이 맞습니까? (y/n) : ");
				choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 != 'Y')
					return;
				result = controller.deleteIO(pStock);
				if(result>0) 
					System.out.println("IO 테이블에서 상품 삭제 성공!!!");
				else
					System.out.println("IO 테이블에서 삭제할 값 없음 or 상품 삭제 실패...");
				result = controller.deleteStock(pStock);
				if(result>0) 
					System.out.println("STOCK 테이블에서 상품 삭제 성공!!!");
				else
					System.out.println("STOCK 테이블에서 상품 삭제 실패...");				
				break;
			//상품 입출고 메뉴(조건2)
			case "7" : 
				IOMenu();
				break;
			//프로그램 종료
			case "9" : 
				System.out.print("정말 종료 하시겠습니까? (y/n) : ");
				choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 == 'Y')
					return;
				break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
			
		}while(true);
		
	}
	

	private void IOMenu() {
		List<Product_io> list;
		Product_io IOStock;
		Product_stock pStock;
		String productId;
		int num;
		int result;
		String menu = "***** 상품입출고메뉴*****\n" + 
				"1. 전체입출고내역조회\n" + 
				"2. 상품입고\n" + 
				"3. 상품출고\n" + 
				"9. 메인메뉴로 돌아가기\n"
				+ "입력 : ";
		do {
			System.out.print(menu);
			String choice = sc.nextLine();
			char choice2;
			
			switch(choice) {
			//전체입출고 내역 조회
			case "1" : 
				System.out.println("===전체 입출고 내역 조회===");
				list = controller.selectAllIO();
				display(list);
				break;
			//상품 입고
			case "2" : 
				System.out.println("===상품 입고===");
				System.out.print("입고 할 상품 아이디 : ");
				productId = sc.nextLine();
				pStock = controller.selectIdStock(productId);
				if(pStock==null) {
					System.out.println("조회 된 상품이 없습니다.");
					break;
				}
				display(pStock);
				System.out.print("입고 할 상품이 위의 것이 맞습니까? (y/n)");
				choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 != 'Y')
					break;
				System.out.println("입고 수량 : ");
				num = sc.nextInt();
				sc.nextLine();
				result = controller.input(pStock.getProductId(),num);
				if(result>0)
					System.out.println("상품 입고 성공!!!");
				else
					System.out.println("상품 입고 실패...");
				break;
			//상품 출고
			case "3" : 
				System.out.println("===상품 출고===");
				System.out.print("출고 할 상품 아이디 : ");
				productId = sc.nextLine();
				pStock = controller.selectIdStock(productId);
				if(pStock==null) {
					System.out.println("조회 된 상품이 없습니다.");
					break;
				}
				display(pStock);
				System.out.print("출고 할 상품이 위의 것이 맞습니까? (y/n)");
				choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 != 'Y')
					break;
				System.out.println("출고 수량 : ");
				num = sc.nextInt();
				sc.nextLine();
				if(pStock.getStock()<num) {
					//에러 메시지를 보이라는게 어떤것을 사용하라는것인가
					//실행중지 하라는건 이 메서드를 중지하라는것인가 프로그램을 중지하라는것인가
					System.err.println("출고수량이 재고보다 많습니다.");
					throw new ProductException("출고수량이 재고보다 많습니다");
				}
				result = controller.output(pStock.getProductId(),num);
				if(result>0)
					System.out.println("상품 출고 성공!!!");
				else
					System.out.println("상품 출고 실패...");
				break;
			//메인메뉴로 돌아가기
			case "9" : 
				System.out.print("메인메뉴로 돌아 가시겠습니까? (y/n) : ");
				choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 == 'Y')
					return;
				break;
			default : System.out.println("잘 못 입력하셨습니다.");
			}
		}while(true);
	}


	


	private void modifyStock(Product_stock pStock) {
		
		String menu = "***** 상품정보변경메뉴 *****\r\n" + 
				"1.상품명변경\r\n" + 
				"2.가격변경\r\n" + 
				"3.설명변경\r\n" + 
				"9.메인메뉴로 돌아가기\n"
				+ "입력 : ";
		do {
			System.out.print(menu);
			String choice = sc.nextLine();
			
			switch(choice) {
			//상품명 변경
			case "1" : 
				System.out.println("===상품명 변경===");
				System.out.print("변경 할 PRODUCT_NAME : ");
				pStock.setProductName(sc.nextLine());
				modify(pStock);
				break;
			//가격변경
			case "2" : 
				System.out.println("===가격 변경===");
				System.out.print("변경 할 PRICE : ");
				pStock.setPrice(sc.nextInt());
				sc.nextLine();
				modify(pStock);
				break;
			//설명 변경
			case "3" : 
				System.out.println("===설명 변경===");
				System.out.print("변경 할 DESCRIPTION : ");
				pStock.setDescription(sc.nextLine());
				modify(pStock);
				break;
			case "9" : 
				System.out.print("메인메뉴로 돌아 가시겠습니까? (y/n) : ");
				char choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 == 'Y')
					return;
				break;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}while(true);
	}
	
	//상품정보변경 메서드
	private void modify(Product_stock pStock) {
		int result = 0;
		result = controller.modifyProduct(pStock);
		if(result>0) 
			System.out.println("상품 정보 변경 성공!!!");
		else
			System.out.println("상품 정보 변경 실패...");
	}
	
	//Stock객체 입력받아서 생성후 리턴
	private Product_stock inputStock() {
		Product_stock temp = new Product_stock();
		System.out.print("PRODUCT_ID : ");
		temp.setProductId(sc.next());
		System.out.print("PRODUCT_NAME : ");
		temp.setProductName(sc.next());
		System.out.print("PRICE : ");
		temp.setPrice(sc.nextInt());
		System.out.print("DESCRIPTION : ");
		temp.setDescription(sc.next());
		System.out.println("STOCK : ");
		temp.setStock(sc.nextInt());
		sc.nextLine();
		return temp;
	}

	//Product_stock 객체 출력
	private void display(Product_stock stock) {
		if(stock != null) { 
			System.out.println(stock);
			return;
		}
		System.out.println("조회된 값이 없슶니다.");
		return;
	}

	//List출력
	private <T> void display(List<T> list1) {
		if(list1==null) {
			System.out.println("조회된 값이 없습니다.");
			return;
		}
		Iterator<T> it = list1.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	//에러메시지 표시
	public void displayErr(String message) {
		System.err.println(message);
	}
}
