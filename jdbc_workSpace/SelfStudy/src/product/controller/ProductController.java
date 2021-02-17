package product.controller;

import java.nio.channels.MembershipKey;
import java.util.List;

import product.model.exception.ProductException;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductLog;
import product.view.MainMenu;

public class ProductController {
	ProductService service = new ProductService();
	//1번메뉴. 현재상품 및 재고 현황 보기
	public List<Product> selectAll() {
		List<Product> list = null;
		try {
			list = service.selectAll();
		} catch (ProductException e) {
			new MainMenu().displayException(e.getMessage());
		}
		return list;
	}
	//2번메뉴. 품목 번호로 조회
	public Product selectNo(int no) {
		Product product = null;
		try {
			product = service.selectNo(no);
		} catch (ProductException e) {
			new MainMenu().displayException(e.getMessage());
		}
		return product;
	}
	//3번메뉴. 품목명으로 조회
	public List<Product> selectName(String name) {
		List<Product> list = null;
		try {
			list = service.selectName(name);
		} catch (ProductException e) {
			new MainMenu().displayException(e.getMessage());
		}
		return list;
	}
	//4번메뉴. 상품 추가
	public int insertOne(Product product) {
		int result = 0;
		try {
			result = service.insertOne(product);
		} catch (ProductException e) {
			new MainMenu().displayException(e.getMessage());
		}
		return result;
	}
	//5번메뉴. 상품 재고 변경
	public int updateQuantity(Product product) {
		int result = 0;
		try {
			result = service.updateQuantity(product);
		} catch (ProductException e) {
			new MainMenu().displayException(e.getMessage());
		}
		return result;
	}
	//6번메뉴. 변경기록 확인
	public List<ProductLog> selectAllLog() {
		List<ProductLog> list = null;
		try {
			list = service.selectAllLog();
		} catch (ProductException e) {
			new MainMenu().displayException(e.getMessage());
		}
		return list;
	}

}
