package product.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Product;
import product.model.vo.ProductLog;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private List<Product> list = null;
	private List<ProductLog> list2 = null;
	private ProductController controller = new ProductController();
	private Product product = null;
	int result = 0;
	int no = 0;
	
	public void mainMenu() {
		String menu = "==============================\n"
				+ "                 재고관리 프로그램\n"
				+ "==============================\n"
				+ "1. 현재 상품 및 재고 현황 보기\n"
				+ "2. 품목 번호로 상품 조회\n"
				+ "3. 품목명으로 상품 조회\n"
				+ "4. 상품 추가 \n"
				+ "5. 상품 재고 변경(이름으로 검색) \n"
				+ "6. 상품 재고 변경기록 확인\n"
				+ "7. 프로그램 종료\n"
				+ "메뉴 선택 : ";
		
		do {
			System.out.print(menu);
			String choice = sc.nextLine();
			switch(choice) {
			//현재 상품 및 재고 현황 보기
			case "1" : 
				System.out.println("=현재 상품 및 재고 현황 보기 선택=");
				list = null;
				list = controller.selectAll();
				display(list);
				break;
			//품목번호로 조회
			case "2" : 
				System.out.println("=품목 번호로 조회=");
				System.out.print("조회 할 품목 번호 : ");
				no = sc.nextInt();
				sc.nextLine();
				product = null;
				product = controller.selectNo(no);
				display(product);
				break;
			//품목으로 조회
			case "3" : 
				System.out.println("=품목명으로 조회=");
				System.out.print("조회 할 품목명 : ");
				String name = sc.nextLine();
				list = null;
				list = controller.selectName(name);
				display(list);
				break;
			//상품추가
			case "4" : 
				System.out.println("=상품 추가=");
				product = null;
				result = 0;
				product = inputProduct();
				result = controller.insertOne(product);
				if(result>0)
					System.out.println("상품 추가 성공!!!");
				else
					System.out.println("상품 추가 실패...");
				break;
			//상품 재고 변경(이름으로 검색해서 변경)
			case "5" : 
				System.out.println("=상품재고 변경=");
				System.out.print("재고변경 할 품목 번호 : ");
				no = sc.nextInt();
				sc.nextLine();
				product = null;
				product = controller.selectNo(no);
				if(product == null) {
					System.out.println("해당 품목번호의 상품이 존재하지 않습니다.");
					break;
				}
				System.out.print("변경할 재고 수량 : ");
				product.setQuantity(sc.nextInt());
				sc.nextLine();
				result = 0;
				result = controller.updateQuantity(product);
				if(result>0)
					System.out.println("재고 변경 성공!!!");
				else
					System.out.println("재고 변경 실패...");
				break;
			//변경기록 확인
			case "6" :
				System.out.println("=상품 재고 기록 확인=");
				list2 = null;
				list2 = controller.selectAllLog();
				display2(list2);
				break;
			//종료
			case "7" : 
				System.out.print("정말 종료 하시겠습니까? (종료하려면 y) : ");
				char choice2 = sc.nextLine().toUpperCase().charAt(0);
				if(choice2 == 'Y')
					return;
				break;
			//문자 잘못 입력시
			default : 
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}while(true);
	}
	
	//Product객체 생성후 리턴
	private Product inputProduct() {
		Product product = new Product();
		System.out.print("상품명 : ");
		product.setProduct(sc.nextLine());
		System.out.print("재고 : ");
		product.setQuantity(sc.nextInt());
		sc.nextLine();
		return product;
	}

	private void display(Product product) {
		if(product == null) {
			System.out.println("조회된 값이 없습니다.");
			return;
		}
		System.out.println(product);
	}

	//List가 null이 아니면 화면에 출력해주는 메서드
	private void display(List<Product> list) {
		if(list == null) {
			System.out.println("조회된 값이 없습니다.");
			return;
		}
		Iterator<Product> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	private void display2(List<ProductLog> list) {
		if(list == null) {
			System.out.println("조회된 값이 없습니다.");
			return;
		}
		Iterator<ProductLog> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	//에러표시 메서드
	public void displayException(String message) {
		System.err.println(message);
	}
}
