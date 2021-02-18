package product.controller;

import java.util.List;

import product.model.exception.ProductException;
import product.model.service.ProductService;
import product.model.vo.Product_io;
import product.model.vo.Product_stock;
import product.view.Menu;

public class ProductController {
	ProductService service = new ProductService();
	
	
	//1번. 전체상품 조회
	public List<Product_stock> selectAllStock() {
		List<Product_stock> list = null;
		try {
			list = service.selectAllStock();
		} catch (ProductException e) {
			new Menu().displayErr(e.getMessage());
		}
		return list;
	}

	//2번. 상품 아이디 검색
	public Product_stock selectIdStock(String productId) {
		Product_stock stock = null;
		try {
			stock = service.selectIdStock(productId);
		} catch (ProductException e) {
			new Menu().displayErr(e.getMessage());
		}
		return stock;
	}
	
	//3번. 상품명 검색
	public List<Product_stock> selectNameStock(String productName) {
		List<Product_stock> list = null;
		try {
			list = service.selectNameStock(productName);
		} catch (ProductException e) {
			new Menu().displayErr(e.getMessage());
		}
		return list;
	}
	
	//4번. 상품 추가
	public int insertProduct(Product_stock pStock) {
		int result = 0;
		try {
			result = service.insertProduct(pStock);
		} catch (Exception e) {
			new Menu().displayErr(e.getMessage());
		}
		return result;
	}
	
	//5번. 상품정보 변경
	public int modifyProduct(Product_stock pStock) {
		int result = 0;
		try {
			result = service.modifyProduct(pStock);
		} catch (Exception e) {
			new Menu().displayErr(e.getMessage());
		}
		return result;
	}
	
	//6.1. 상품정보삭제 IO에서 먼저 삭제
	public int deleteIO(Product_stock pStock) {
		int result = 0;
		try {
			result = service.deleteIO(pStock);
		} catch (Exception e) {
			new Menu().displayErr(e.getMessage());
		}
		return result;
	}
	//6.2. 상품정보삭제 Stock테이블에서 
	public int deleteStock(Product_stock pStock) {
		int result = 0;
		try {
			result = service.deleteStock(pStock);
		} catch (Exception e) {
			new Menu().displayErr(e.getMessage());
		}
		return result;
	}
	
	//7.1 입출력 내역 테이블 전체 출력
	public List<Product_io> selectAllIO() {
		List<Product_io> list = null;
		try {
			list = service.selectAllIO();
		} catch (ProductException e) {
			new Menu().displayErr(e.getMessage());
		}
		return list;
	}
	
	//7.2 입고
	public int input(String productId, int num) {
		int result = 0;
		try {
			result = service.input(productId, num);
		} catch (Exception e) {
			new Menu().displayErr(e.getMessage());
		}
		return result;
	}

	//7.3출고
	public int output(String productId, int num) {
		int result = 0;
		try {
			result = service.output(productId, num);
		} catch (Exception e) {
			new Menu().displayErr(e.getMessage());
		}
		return result;
	}
	
	

}
