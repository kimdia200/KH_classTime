package product.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import product.model.exception.ProductException;
import product.model.vo.Product;
import product.model.vo.ProductLog;

import static common.Template.*;
public class ProductDao {
	private Properties prop = new Properties();
	
	//생성자
	public ProductDao() {
		String fileName = "resources/product-query.properties";
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new ProductException("프로퍼티 파일을 찾을 수 없음 in ProductDao",e);
		} catch (IOException e) {
			throw new ProductException("프로퍼티 파일을 읽을 수 없음 in ProductDao",e);
		}
	}
	
	//1번메뉴. 현재상품 및 재고 현황 보기
	public List<Product> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = null;
		String sql = prop.getProperty("selectAll");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				int no = rset.getInt("no");
				String product = rset.getString("product");
				int quantity = rset.getInt("quantity");
				Product p = new Product(no, product, quantity);
				list.add(p);
			}
		} catch (SQLException e) {
			throw new ProductException("SelectAll 쿼리 실행 실패 in ProductDao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Product selectNo(Connection conn,int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String sql = prop.getProperty("selectNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String product = rset.getString("product");
				int quantity = rset.getInt("quantity");
				p = new Product(no, product, quantity);
			}
		} catch (SQLException e) {
			throw new ProductException("SelectNo 쿼리 실행 실패 in ProductDao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}
	
	//3번메뉴. 품목명으로 조회
	public List<Product> selectName(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectName");
		List<Product> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				int no = rset.getInt("no");
				String product = rset.getString("product");
				int quantity = rset.getInt("quantity");
				Product p = new Product(no, product, quantity);
				list.add(p);
			}
		} catch (SQLException e) {
			throw new ProductException("SelectName 쿼리 실행 실패 in ProductDao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//4번메뉴. 상품추가 기능
	public int insertOne(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProduct());
			pstmt.setInt(2, product.getQuantity());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("insertOne 쿼리 실행 실패 in ProductDao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//5번메뉴. 상품 재고 변경
	public int updateQuantity(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateQuantity");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getQuantity());
			pstmt.setInt(2, product.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("updateQuantity 쿼리 실행 실패 in ProductDao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//6번메뉴. 변경기록 확인
	public List<ProductLog> selectAllLog(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductLog> list = null;
		String sql = prop.getProperty("selectAllLog");
		try {
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				int no = rset.getInt("no");
				int productNo = rset.getInt("product_no");
				String product = rset.getString("product");
				int quantity = rset.getInt("quantity");
				Date modificationDate = rset.getDate("modification_date");
				ProductLog p = new ProductLog(no, productNo, product, quantity, modificationDate);
				list.add(p);
			}
		} catch (SQLException e) {
			throw new ProductException("selectAllLog 쿼리 실행 실패 in ProductDao",e);		}
		return list;
	}
	
}
