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
import product.model.vo.Product_io;
import product.model.vo.Product_stock;

import static common.Template.*;
public class ProductDao {
	Properties prop = new Properties();
	
	
	public ProductDao() {
		String fileName = "resources/product-query.properties";
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new ProductException("파일 찾을 수 없음 in Dao",e);
		} catch (IOException e) {
			throw new ProductException("파일 읽을 수 없음 in Dao",e);
		}
	}
	
	//1번 상품 전체조회
	public List<Product_stock> selectAllStock(Connection conn) {
		List<Product_stock> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllStock");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				String productId = rset.getString("product_id");
				String productName = rset.getString("product_name");
				int price = rset.getInt("price");
				String description = rset.getString("description");
				int stock = rset.getInt("stock");
				list.add(new Product_stock(productId, productName, price, description, stock));
			}
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//2번. 상품 아이디 검색
	public Product_stock selectIdStock(Connection conn, String productId) {
		Product_stock pStock = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectIdStock");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				productId = rset.getString("product_id");
				String productName = rset.getString("product_name");
				int price = rset.getInt("price");
				String description = rset.getString("description");
				int stock = rset.getInt("stock");
				pStock = new Product_stock(productId, productName, price, description, stock); 
			}
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return pStock;
	}
	
	//3번. 상품명 검색
	public List<Product_stock> selectNameStock(Connection conn, String productName) {
		List<Product_stock> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNameStock");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				String productId = rset.getString("product_id");
				productName = rset.getString("product_name");
				int price = rset.getInt("price");
				String description = rset.getString("description");
				int stock = rset.getInt("stock");
				list.add(new Product_stock(productId, productName, price, description, stock));
			}
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//4번 상품추가
	public int insertProduct(Connection conn, Product_stock pStock) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pStock.getProductId());
			pstmt.setString(2, pStock.getProductName());
			pstmt.setInt(3, pStock.getPrice());
			pstmt.setString(4, pStock.getDescription());
			pstmt.setInt(5, pStock.getStock());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	//5번 상품 정보 수정
	public int modifyProduct(Connection conn, Product_stock pStock) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("modifyProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pStock.getProductName());
			pstmt.setInt(2, pStock.getPrice());
			pstmt.setString(3, pStock.getDescription());
			pstmt.setString(4, pStock.getProductId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	//IO테이블에서 삭제
	public int deleteIO(Connection conn, Product_stock pStock) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteIO");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pStock.getProductId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//Stock테이블에서 삭제
	public int deleteStock(Connection conn, Product_stock pStock) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteStock");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pStock.getProductId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	//7.1전체 입출고 내역 조회
	public List<Product_io> selectAllIO(Connection conn) {
		List<Product_io> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllIO");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				int ioNo = rset.getInt("io_no");
				String product_id = rset.getString("product_id");
				Date ioDate = rset.getDate("iodate");
				int amount = rset.getInt("amount");
				String status = rset.getString("status");
				list.add(new Product_io(ioNo, product_id, ioDate, amount, status));
			}
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int input(Connection conn, String productId, int num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("InputOutput");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			pstmt.setInt(2, num);
			pstmt.setString(3, "I");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int output(Connection conn, String productId, int num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("InputOutput");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			pstmt.setInt(2, num);
			pstmt.setString(3, "O");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("SQL예외발생 in Dao",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

}
