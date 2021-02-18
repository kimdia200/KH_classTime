package product.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import product.model.dao.ProductDao;
import product.model.vo.Stock;

import static common.Template.*;

/**
 * Connection 객체를 쉽게 사용 할 수 있게
 */
public class ProductService {
	private ProductDao dao = new ProductDao();
	
	//1번. 전체상품 조회
	public List<Stock> selectAll() {
		List<Stock> list = null;
		Connection conn = getConnection();
		list = dao.selectAll(conn);
		close(conn);
		return list;
	}

	//2번. 아이디 조회
	public Stock selectID(String id) {
		Stock s = null;
		Connection conn = getConnection();
		s = dao.selectID(conn, id);
		close(conn);
		return s;
	}

}
