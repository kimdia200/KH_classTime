package product.controller;

import java.util.List;

import product.model.exception.MyException;
import product.model.service.ProductService;
import product.model.vo.Stock;
import product.view.MainMenu;

public class ProductController {
	private ProductService service = new ProductService();
	
	//1번. 전체 목록 조회
	public List<Stock> selectAll() {
//		return service.selectAll();
		
		List<Stock> list = null;
		try {
			list = service.selectAll();
		} catch (MyException e) {
			new MainMenu().displayErr(e.getMessage());
		}
		return list;
	}

	//2번. 아이디 조회
	public Stock selectID(String id) {
		Stock s = null;
		
		try {
			s = service.selectID(id);
		} catch (MyException e) {
			new MainMenu().displayErr(e.getMessage());
		}
		return s;
	}

}
