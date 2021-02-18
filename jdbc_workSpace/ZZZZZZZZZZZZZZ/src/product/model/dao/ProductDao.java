package product.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import product.model.vo.Stock;

import static common.Template.*;

public class ProductDao {
	private Properties prop = new Properties();
	
	public ProductDao() {
		try {
			String fileName = "resources/product-query.properties";
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//1번. 재고 전체 조회
	public List<Stock> selectAll(Connection conn) {
		//1. DML인가 pre, int값
		//2. DQL인가 이거 pre, rset, 현재 리턴값 list
		List<Stock> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				//값을 받아오는것
				String productID = rset.getString("product_id");
				String productName = rset.getString("product_name");
				int price = rset.getInt("price");
				String description = rset.getString("description");
				int stock = rset.getInt("stock");
				
				//객체 생성
				Stock s = new Stock(productID, productName, price, description, stock);
				
				//리트에다가 넣어줌
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	//2번. 아이디 조회
	public Stock selectID(Connection conn, String id) {
		//DML DQL
		Stock s = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectID");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			rset.next();
			// 값을 받아오는것
			String productID = rset.getString("product_id");
			String productName = rset.getString("product_name");
			int price = rset.getInt("price");
			String description = rset.getString("description");
			int stock = rset.getInt("stock");

			// 객체 생성
			s = new Stock(productID, productName, price, description, stock);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return s;
	}
	
}
