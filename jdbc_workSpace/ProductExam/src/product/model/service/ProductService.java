package product.model.service;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ProductDao;
import product.model.vo.Product_io;
import product.model.vo.Product_stock;

import static common.Template.*;
public class ProductService {
	ProductDao dao = new ProductDao();
	
	//1번 전체상품 조회
	public List<Product_stock> selectAllStock() {
		Connection conn = getConnection();
		List<Product_stock> list = dao.selectAllStock(conn);
		close(conn);
		return list;
	}
	
	//2번 상품 아이디 검색
	public Product_stock selectIdStock(String productId) {
		Connection conn = getConnection();
		Product_stock stock = dao.selectIdStock(conn,productId);
		close(conn);
		return stock;
	}
	
	//3번 상품명 검색
	public List<Product_stock> selectNameStock(String productName) {
		Connection conn = getConnection();
		List<Product_stock> list = dao.selectNameStock(conn, productName);
		close(conn);
		return list;
	}

	//4번 상품 추가
	public int insertProduct(Product_stock pStock) {
		Connection conn = getConnection();
		int result = dao.insertProduct(conn,pStock);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//5번 상품 정보 변경
	public int modifyProduct(Product_stock pStock) {
		Connection conn = getConnection();
		int result = dao.modifyProduct(conn,pStock);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//6.1. 상품정보삭제 IO에서 먼저 삭제
	public int deleteIO(Product_stock pStock) {
		Connection conn = getConnection();
		int result = dao.deleteIO(conn,pStock);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	//6.2 상품정보삭제 stock테이블에서
	public int deleteStock(Product_stock pStock) {
		Connection conn = getConnection();
		int result = dao.deleteStock(conn,pStock);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	//7.1 전체 입출고 내역 조회
	public List<Product_io> selectAllIO() {
		Connection conn = getConnection();
		List<Product_io> list = dao.selectAllIO(conn);
		close(conn);
		return list;
	}

	//7.2 입고
	public int input(String productId, int num) {
		Connection conn = getConnection();
		int result = dao.input(conn,productId,num);
		close(conn);
		return result;
	}

	//7.3 출고
	public int output(String productId, int num) {
		Connection conn = getConnection();
		int result = dao.output(conn,productId,num);
		close(conn);
		return result;
	}

}
