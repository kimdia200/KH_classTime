package product.model.service;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ProductDao;
import product.model.vo.Product;
import product.model.vo.ProductLog;

import static common.Template.*;
public class ProductService {
	ProductDao dao = new ProductDao();
	
	//1번메뉴. 현재상품 및 재고 현황 보기
	public List<Product> selectAll() {
		Connection conn = getConnection();
		List<Product> list = dao.selectAll(conn);
		close(conn);
		return list;
	}
	
	//2번메뉴. 품목 번호로 조회
	public Product selectNo(int no) {
		Connection conn = getConnection();
		Product product = dao.selectNo(conn, no);
		close(conn);
		return product;
	}
	
	//3번메뉴. 품목명으로 조회
	public List<Product> selectName(String name) {
		Connection conn = getConnection();
		List<Product> list = dao.selectName(conn,name);
		close(conn);
		return list;
	}

	//4번메뉴. 상품추가
	public int insertOne(Product product) {
		Connection conn = getConnection();
		int result = dao.insertOne(conn, product);
		close(conn);
		return result;
	}

	//5번메뉴. 상품 재고 변경
	public int updateQuantity(Product product) {
		Connection conn = getConnection();
		int result = dao.updateQuantity(conn, product);
		close(conn);
		return result;
	}
	
	//6번메뉴. 변경기록 확인
	public List<ProductLog> selectAllLog() {
		Connection conn = getConnection();
		List<ProductLog> list = dao.selectAllLog(conn);
		close(conn);
		return list;
	}
	
}
